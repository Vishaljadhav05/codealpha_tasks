package com.vj.repository;

import com.vj.model.Booking;
import com.vj.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    /** Room ids that have a non-cancelled booking overlapping [checkIn, checkOut). */
    @Query("""
           select b.room.id from Booking b
           where b.status <> :excluded
             and b.checkIn < :checkOut and b.checkOut > :checkIn
           """)
    List<Long> findBookedRoomIds(@Param("checkIn") LocalDate checkIn,
                                 @Param("checkOut") LocalDate checkOut,
                                 @Param("excluded") BookingStatus excluded);

    List<Booking> findByGuestEmailIgnoreCase(String email);
}
