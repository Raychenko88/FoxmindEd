package org.example.dao;

import org.example.model.Order;
import org.example.model.RoomOption;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
class RoomOptionDAOTest {

    @Autowired
    private RoomOptionDAO roomOptionDAO;

    @Test
    void findAllByOrderId() {
        Order order1 = Order.builder()
                .userId(1)
                .roomId(1)
                .startDate(LocalDate.of(2001, 1, 1))
                .endDate(LocalDate.of(2001, 1, 20))
                .statusRoom("OPEN")
                .build();
        RoomOption roomOption1 = RoomOption.builder()
                .title("test1")
                .price(new BigDecimal(1))
                .orderId(order1.getId())
                .build();
        RoomOption roomOption2 = RoomOption.builder()
                .title("test2")
                .price(new BigDecimal(2))
                .orderId(order1.getId())
                .build();
        roomOptionDAO.save(roomOption1);
        roomOptionDAO.save(roomOption2);
        List<RoomOption> list = roomOptionDAO.findAllByOrderId(order1.getId());
        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
        roomOptionDAO.delete(roomOption1);
        roomOptionDAO.delete(roomOption2);
    }
}