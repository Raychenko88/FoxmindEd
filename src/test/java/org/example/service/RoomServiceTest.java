package org.example.service;

import org.example.dao.OrderDAO;
import org.example.dao.RoomDAO;
import org.example.dao.RoomOptionDAO;
import org.example.model.Order;
import org.example.model.Room;
import org.example.model.RoomOption;
import org.example.service.impl.RoomOptionServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @MockBean
    private RoomDAO roomDAO;

    @MockBean
    private OrderDAO orderDAO;

    @MockBean
    private RoomOptionDAO roomOptionDAO;

    @MockBean
    private RoomOptionServiceImpl roomOptionServiceImpl;


    @Test
    void findAllByCategory() throws Exception {
        Room room1 = Room.builder()
                .category("SINGLE")
                .build();
        Room room2 = Room.builder()
                .category("SINGLE")
                .build();
        List<Room> list = new ArrayList<>();
        list.add(room1);
        list.add(room2);
        when(roomDAO.findAllByCategory(any(String.class))).thenReturn(list);
        List<Room> result = roomService.findAllByCategory(room1.getCategory());
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        verify(roomDAO, times(1)).findAllByCategory(any(String.class));
    }

    @Test
    void costServices() {
        Order order = Order.builder()
                .id(1)
                .build();
        RoomOption roomOption = RoomOption.builder()
                .price(new BigDecimal(10))
                .id(1)
                .orderId(1)
                .build();
        List<RoomOption> list = new ArrayList<>();
        list.add(roomOption);
        when(roomOptionServiceImpl.findAllByOrderId(anyInt())).thenReturn(list);
        assertEquals(new BigDecimal(10), roomService.costServices(order));
        verify(roomOptionServiceImpl, times(1)).findAllByOrderId(anyInt());
    }

    @Test
    void costIncludingAdditionalServices() throws Exception {
        Order order = Order.builder()
                .id(1)
                .roomId(1)
                .build();
        Room room = Room.builder()
                .price(new BigDecimal(10))
                .build();
        RoomOption roomOption = RoomOption.builder()
                .id(1)
                .orderId(1)
                .price(new BigDecimal(10))
                .build();
        List<RoomOption> list = new ArrayList<>();
        list.add(roomOption);
        when(roomOptionServiceImpl.findAllByOrderId(anyInt())).thenReturn(list);
        when(roomDAO.findById(anyInt())).thenReturn(ofNullable(room));
        assertEquals(new BigDecimal(20), roomService.costIncludingAdditionalServices(order));
        verify(roomOptionServiceImpl, times(1)).findAllByOrderId(anyInt());
    }
}