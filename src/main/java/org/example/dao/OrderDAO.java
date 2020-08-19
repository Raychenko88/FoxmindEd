package org.example.dao;

import org.example.model.Order;
import org.example.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {

    List<Order> findAllByUserId(Integer id);

    @Query(value = "SELECT * FROM rooms " +
            "JOIN orders o ON o.room_id = r.id " +
            "WHERE " +
            "(:startDate NOT BETWEEN o.start_date AND o.end_date) " +
            "AND " +
            "(:endDate NOT BETWEEN o.start_date AND o.end_date)",
            nativeQuery = true)
    List<Room> findAllAvailableRoomsForDates(LocalDate startDate, LocalDate endDate);
}
