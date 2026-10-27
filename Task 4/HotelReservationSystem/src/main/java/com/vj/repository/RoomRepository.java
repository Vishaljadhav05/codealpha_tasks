package com.vj.repository;

import com.vj.model.Room;
import com.vj.model.RoomCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByCategory(RoomCategory category);
}
