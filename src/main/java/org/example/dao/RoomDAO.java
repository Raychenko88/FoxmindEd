package org.example.dao;

import org.example.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomDAO extends JpaRepository<Room, Integer> {

    List<Room> findAllByCategory(String category);

    @Query(value = "SELECT " +
            "r.id, r.number, r.category, r.price " +
            "FROM rooms r " +
            "WHERE r.id NOT IN " +
            "(SELECT o.room_id " +
            "FROM orders o " +
            "WHERE (:startDate BETWEEN o.start_date AND o.end_date) " +
            "OR (:endDate BETWEEN o.start_date AND o.end_date)" +
            ")",
            nativeQuery = true)
    List<Room> findAllAvailableRoomsForDates(LocalDate startDate, LocalDate endDate);
}
