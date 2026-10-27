package com.vj.controller;

import com.vj.model.*;
import com.vj.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
	private final RoomService service;

	public RoomController(RoomService service) {
		this.service = service;
	}

	@GetMapping
	public List<Room> search(@RequestParam(required = false) RoomCategory category,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut) {
		
		
		return service.search(category, checkIn, checkOut);
	}

	@GetMapping("/{id}")
	public Room get(@PathVariable Long id) {
		return service.get(id);
	}
}
