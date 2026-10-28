package com.vj.controller;

import com.vj.dto.BookingRequest;
import com.vj.dto.PaymentRequest;
import com.vj.exception.NotFoundException;
import com.vj.model.*;
import com.vj.service.BookingService;
import com.vj.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


@Controller
public class PageController {

	private final RoomService roomService;
	private final BookingService bookingService;

	public PageController(RoomService roomService, BookingService bookingService) {
		this.roomService = roomService;
		this.bookingService = bookingService;
	}

	@GetMapping("/")
	public String home() {
		return "redirect:/rooms";
	}

	// ---------- Rooms: search ----------
	@GetMapping("/rooms")
	public String rooms(@RequestParam(required = false) RoomCategory category,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
			Model model) {
		model.addAttribute("categories", RoomCategory.values());
		model.addAttribute("today", LocalDate.now());
		try {
			model.addAttribute("rooms", roomService.search(category, checkIn, checkOut));
		} catch (IllegalArgumentException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("rooms", List.of());
		}
		
		return "rooms";
	}

	// ---------- Book a room ----------
	@GetMapping("/book/{roomId}")
	public String bookForm(@PathVariable Long roomId,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut,
			Model model, RedirectAttributes ra) {
		try {
			model.addAttribute("room", roomService.get(roomId));
		} catch (NotFoundException e) {
			ra.addFlashAttribute("error", e.getMessage());
			return "redirect:/rooms";
		}
		model.addAttribute("today", LocalDate.now());
		model.addAttribute("checkIn", checkIn != null ? checkIn : LocalDate.now().plusDays(1));
		model.addAttribute("checkOut", checkOut != null ? checkOut : LocalDate.now().plusDays(2));
		model.addAttribute("guests", 1);
		return "book";
	}

	@PostMapping("/book")
	public String book(@RequestParam Long roomId, @RequestParam String guestName, @RequestParam String guestEmail,
			@RequestParam int guests, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut, Model model,
			RedirectAttributes ra) {
		try {
			if (guestName.isBlank() || !guestEmail.contains("@"))
				throw new IllegalArgumentException("Please enter a valid name and email");
			Booking b = bookingService
					.create(new BookingRequest(roomId, guestName.trim(), guestEmail.trim(), guests, checkIn, checkOut));
			ra.addFlashAttribute("msg", "Reservation created. Please complete the payment to confirm it.");
			return "redirect:/payment/" + b.getId();
		} catch (NotFoundException e) {
			ra.addFlashAttribute("error", e.getMessage());
			return "redirect:/rooms";
		} catch (IllegalArgumentException | IllegalStateException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("room", roomService.get(roomId));
			model.addAttribute("today", LocalDate.now());
			model.addAttribute("checkIn", checkIn);
			model.addAttribute("checkOut", checkOut);
			model.addAttribute("guests", guests);
			model.addAttribute("guestName", guestName);
			model.addAttribute("guestEmail", guestEmail);
			return "book";
		}
	}

	// ---------- Payment ----------
	@GetMapping("/payment/{id}")
	public String paymentForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
		try {
			Booking b = bookingService.get(id);
			if (b.getStatus() != BookingStatus.PENDING) {
				ra.addFlashAttribute("error", "This booking is " + b.getStatus() + " and cannot be paid.");
				return "redirect:/bookings/" + id;
			}
			model.addAttribute("booking", b);
			return "payment";
		} catch (NotFoundException e) {
			ra.addFlashAttribute("error", e.getMessage());
			return "redirect:/bookings";
		}
	}

	@PostMapping("/payment/{id}")
	public String pay(@PathVariable Long id, @RequestParam PaymentMethod method,
			@RequestParam(required = false) String cardNumber, Model model, RedirectAttributes ra) {
		try {
			Booking b = bookingService.pay(id, new PaymentRequest(method, cardNumber));
			if (b.getStatus() == BookingStatus.CONFIRMED) {
				ra.addFlashAttribute("msg", "Payment successful! Your booking is confirmed.");
				return "redirect:/bookings/" + id;
			}
			model.addAttribute("error", "Payment declined. Please try a different card.");
			model.addAttribute("booking", b);
			return "payment";
		} catch (NotFoundException e) {
			ra.addFlashAttribute("error", e.getMessage());
			return "redirect:/bookings";
		} catch (IllegalStateException e) {
			ra.addFlashAttribute("error", e.getMessage());
			return "redirect:/bookings/" + id;
		}
	}

	// ---------- Bookings ----------
	@GetMapping("/bookings")
	public String bookings(@RequestParam(required = false) String email, Model model) {
		String e = (email == null || email.isBlank()) ? null : email.trim();
		List<Booking> list = bookingService.list(e).stream().sorted(Comparator.comparing(Booking::getId).reversed())
				.toList();
		model.addAttribute("bookings", list);
		return "bookings";
	}

	@GetMapping("/bookings/{id}")
	public String details(@PathVariable Long id, Model model, RedirectAttributes ra) {
		try {
			model.addAttribute("booking", bookingService.get(id));
			return "details";
		} catch (NotFoundException e) {
			ra.addFlashAttribute("error", e.getMessage());
			return "redirect:/bookings";
		}
	}

	@PostMapping("/bookings/{id}/cancel")
	public String cancel(@PathVariable Long id, RedirectAttributes ra) {
		try {
			bookingService.cancel(id);
			ra.addFlashAttribute("msg", "Booking #" + id + " has been cancelled.");
		} catch (NotFoundException | IllegalStateException e) {
			ra.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/bookings";
	}
}
