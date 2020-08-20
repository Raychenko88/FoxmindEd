package org.example.dao;

import org.example.model.Order;
import org.example.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
class RoomDAOTest {

    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    private OrderDAO orderDAO;

    @Test
    void findAllByCategory() {
        Room room1 = Room.builder()
                .number(1)
                .category("SINGLE")
                .price(new BigDecimal(10))
                .build();
        Room room2 = Room.builder()
                .number(2)
                .category("SINGLE")
                .price(new BigDecimal(20))
                .build();
        Room room3 = Room.builder()
                .number(3)
                .category("DOUBLE")
                .price(new BigDecimal(30))
                .build();
        roomDAO.save(room1);
        roomDAO.save(room2);
        roomDAO.save(room3);
        List<Room> list = roomDAO.findAllByCategory("SINGLE");
        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
        roomDAO.delete(room1);
        roomDAO.delete(room2);
        roomDAO.delete(room3);
    }

    @Test
    void findAllAvailableRoomsForDates() {
        Room room1 = roomDAO.save(Room.builder()
                .number(1)
                .build());
        Room room2 = roomDAO.save(Room.builder()
                .number(2)
                .build());
        Room room3 = roomDAO.save(Room.builder()
                .number(3)
                .build());
        Order order = Order.builder()
                .userId(1)
                .roomId(room1.getId())
                .startDate(LocalDate.of(2001, 1, 1))
                .endDate(LocalDate.of(2001, 1, 20))
                .statusRoom("OPEN")
                .build();
        Order savedOrder = orderDAO.save(order);
        List<Room> list = roomDAO.findAllAvailableRoomsForDates(savedOrder.getStartDate(), savedOrder.getEndDate());
        assertFalse(list.isEmpty());
        roomDAO.delete(room1);
        roomDAO.delete(room2);
        roomDAO.delete(room3);
        orderDAO.delete(order);
    }
}