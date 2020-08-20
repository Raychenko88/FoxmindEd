package org.example.service;

import org.example.dao.OrderDAO;
import org.example.model.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderDAO orderDAO;


    @Test
    void findAllByUserId() {
        Order order1 = Order.builder()
                .id(1)
                .userId(1)
                .build();
        Order order2 = Order.builder()
                .id(2)
                .userId(1)
                .build();
        List<Order> list = new ArrayList<>();
        list.add(order1);
        list.add(order2);
        when(orderDAO.findAllByUserId(anyInt())).thenReturn(list);
        assertEquals(2, orderService.findAllByUserId(1).size());
        verify(orderDAO, times(1)).findAllByUserId(anyInt());
    }
}