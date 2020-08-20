package org.example.dao;

import org.example.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
class OrderDAOTest {

    @Autowired
    private OrderDAO orderDAO;


    @Test
    void findAllByUserId() {
        Order order1 = Order.builder()
                .userId(1)
                .roomId(1)
                .startDate(LocalDate.of(2001, 1, 1))
                .endDate(LocalDate.of(2001, 1, 20))
                .statusRoom("OPEN").build();
        Order order2 = Order.builder()
                .userId(1).roomId(2)
                .startDate(LocalDate.of(2001, 1, 1))
                .endDate(LocalDate.of(2001, 1, 20))
                .statusRoom("OPEN").build();
        orderDAO.save(order1);
        orderDAO.save(order2);
        List<Order> list = orderDAO.findAllByUserId(1);
        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
        orderDAO.delete(order1);
        orderDAO.delete(order2);
    }
}