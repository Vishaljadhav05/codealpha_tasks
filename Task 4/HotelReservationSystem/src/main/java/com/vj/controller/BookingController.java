package com.vj.controller;

import com.vj.dto.*;
import com.vj.model.Booking;
import com.vj.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
	private final BookingService service;

	public BookingController(BookingService service) {
		this.service = service;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Booking create(@Valid @RequestBody BookingRequest req) {
		return service.create(req);
	}

	@PostMapping("/{id}/pay")
	public Booking pay(@PathVariable Long id, @Valid @RequestBody PaymentRequest req) {
		return service.pay(id, req);
	}

	@GetMapping("/{id}")
	public Booking details(@PathVariable Long id) {
		return service.get(id);
	}

	@GetMapping
	public List<Booking> list(@RequestParam(required = false) String email) {
		return service.list(email);
	}

	@DeleteMapping("/{id}")
	public Booking cancel(@PathVariable Long id) {
		return service.cancel(id);
	}
}
