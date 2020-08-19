package org.example.dao;

import org.example.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoomDAOTest {

    @Autowired
    RoomDAO roomDAO;

    @Test
    void findAllByCategory() {
        Room room1 = Room.builder().id(1).number(1).category("SINGLE").price(new BigDecimal(10)).build();
        Room room2 = Room.builder().id(2).number(2).category("SINGLE").price(new BigDecimal(20)).build();
        Room room3 = Room.builder().id(3).number(3).category("DOUBLE").price(new BigDecimal(30)).build();
        roomDAO.save(room1);
        roomDAO.save(room2);
        roomDAO.save(room3);
        List<Room> list = roomDAO.findAllByCategory("SINGLE");
        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
    }
}