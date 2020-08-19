package org.example.dao;

import org.example.model.Order;
import org.example.model.Room;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class OrderDAOTest {

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    RoomDAO roomDAO;


    @Test
    void findAllByUserId() {
        Order order1 = Order.builder().id(1).userId(1).roomId(1).startDate(LocalDate.of(2001, 1, 1))
                .endDate(LocalDate.of(2001, 1, 20)).statusRoom("OPEN").build();
        Order order2 = Order.builder().id(2).userId(1).roomId(2).startDate(LocalDate.of(2001, 1, 1))
                .endDate(LocalDate.of(2001, 1, 20)).statusRoom("OPEN").build();
        orderDAO.save(order1);
        orderDAO.save(order2);
        List<Order> list = orderDAO.findAllByUserId(1);
        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
    }

    @Test
    void findAllAvailableRoomsForDates() {
        Room room1 = Room.builder().id(1).number(1).category("SINGLE").price(new BigDecimal(10)).build();
        Room room2 = Room.builder().id(2).number(2).category("SINGLE").price(new BigDecimal(20)).build();
        Room room3 = Room.builder().id(3).number(3).category("DOUBLE").price(new BigDecimal(30)).build();
        Order order1 = Order.builder().id(1).userId(1).roomId(1).startDate(LocalDate.of(2001, 1, 1))
                .endDate(LocalDate.of(2001, 1, 20)).statusRoom("OPEN").build();
        roomDAO.save(room1);
        roomDAO.save(room2);
        roomDAO.save(room3);
        orderDAO.save(order1);
        List<Room> list = orderDAO.findAllAvailableRoomsForDates(LocalDate.of(2001, 1, 1)
                , LocalDate.of(2001, 1, 20) );
    }
}