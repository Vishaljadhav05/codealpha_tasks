package com.vj.service;

import com.vj.exception.NotFoundException;
import com.vj.model.*;
import com.vj.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository rooms;
    private final BookingRepository bookings;

    public RoomService(RoomRepository rooms, BookingRepository bookings) {
        this.rooms = rooms;
        this.bookings = bookings;
    }

    /** Search rooms, optionally by category and by availability for a date range. */
    public List<Room> search(RoomCategory category, LocalDate checkIn, LocalDate checkOut) {
        List<Room> result = category == null ? rooms.findAll() : rooms.findByCategory(category);
        if (checkIn != null && checkOut != null) {
            if (!checkOut.isAfter(checkIn)) throw new IllegalArgumentException("checkOut must be after checkIn");
            List<Long> booked = bookings.findBookedRoomIds(checkIn, checkOut, BookingStatus.CANCELLED);
            result = result.stream().filter(r -> !booked.contains(r.getId())).toList();
        }
        return result;
    }

    public Room get(Long id) {
        return rooms.findById(id).orElseThrow(() -> new NotFoundException("Room " + id + " not found"));
    }
}
