package com.vj.service;

import com.vj.dto.*;
import com.vj.exception.NotFoundException;
import com.vj.model.Booking;
import com.vj.model.BookingStatus;
import com.vj.model.Payment;
import com.vj.model.PaymentStatus;
import com.vj.model.Room;
import com.vj.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class BookingService {
	private final BookingRepository bookings;
	private final RoomService roomService;
	private final PaymentService paymentService;

	public BookingService(BookingRepository bookings, RoomService roomService, PaymentService paymentService) {
		this.bookings = bookings;
		this.roomService = roomService;
		this.paymentService = paymentService;
	}

	public Booking create(BookingRequest r) {
		if (!r.checkOut().isAfter(r.checkIn()))
			throw new IllegalArgumentException("checkOut must be after checkIn");
		if (r.checkIn().isBefore(LocalDate.now()))
			throw new IllegalArgumentException("checkIn cannot be in the past");

		Room room = roomService.get(r.roomId());
		if (r.guests() > room.getCapacity())
			throw new IllegalArgumentException("Room capacity is " + room.getCapacity() + " guests");
		if (bookings.findBookedRoomIds(r.checkIn(), r.checkOut(), BookingStatus.CANCELLED).contains(room.getId()))
			throw new IllegalStateException(
					"Room " + room.getRoomNumber() + " is not available for the selected dates");

		long nights = ChronoUnit.DAYS.between(r.checkIn(), r.checkOut());
		Booking b = new Booking();
		b.setRoom(room);
		b.setGuestName(r.guestName());
		b.setGuestEmail(r.guestEmail());
		b.setGuests(r.guests());
		b.setCheckIn(r.checkIn());
		b.setCheckOut(r.checkOut());
		b.setTotalAmount(room.getPricePerNight().multiply(BigDecimal.valueOf(nights)));
		return bookings.save(b);
	}

	public Booking pay(Long id, PaymentRequest req) {
		Booking b = get(id);
		if (b.getStatus() == BookingStatus.CANCELLED)
			throw new IllegalStateException("Booking is cancelled");
		if (b.getStatus() == BookingStatus.CONFIRMED)
			throw new IllegalStateException("Booking is already paid");
		Payment p = paymentService.process(b.getTotalAmount(), req);
		b.setPayment(p);
		if (p.getStatus() == PaymentStatus.SUCCESS)
			b.setStatus(BookingStatus.CONFIRMED);
		return bookings.save(b);
	}

	public Booking cancel(Long id) {
		Booking b = get(id);
		if (b.getStatus() == BookingStatus.CANCELLED)
			throw new IllegalStateException("Booking already cancelled");
		if (b.getPayment() != null && b.getPayment().getStatus() == PaymentStatus.SUCCESS)
			b.getPayment().setStatus(PaymentStatus.REFUNDED);
		b.setStatus(BookingStatus.CANCELLED);
		return bookings.save(b);
	}

	@Transactional(readOnly = true)
	public Booking get(Long id) {
		return bookings.findById(id).orElseThrow(() -> new NotFoundException("Booking " + id + " not found"));
	}

	@Transactional(readOnly = true)
	public List<Booking> list(String email) {
		return email == null ? bookings.findAll() : bookings.findByGuestEmailIgnoreCase(email);
	}
}
