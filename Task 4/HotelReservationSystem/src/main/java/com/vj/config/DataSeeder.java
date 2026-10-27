package com.vj.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.vj.repository.RoomRepository;
import com.vj.model.Room;
import com.vj.model.RoomCategory;

@Component
public class DataSeeder implements CommandLineRunner {
    private final RoomRepository rooms;
    public DataSeeder(RoomRepository rooms) { this.rooms = rooms; }

    @Override
    public void run(String... args) {
        if (rooms.count() > 0) return;
        for (int i = 1; i <= 5; i++) rooms.save(new Room("10" + i, RoomCategory.STANDARD));
        for (int i = 1; i <= 3; i++) rooms.save(new Room("20" + i, RoomCategory.DELUXE));
        for (int i = 1; i <= 2; i++) rooms.save(new Room("30" + i, RoomCategory.SUITE));
    }
}
