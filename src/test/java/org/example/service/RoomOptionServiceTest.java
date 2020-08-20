package org.example.service;

import org.example.dao.RoomOptionDAO;
import org.example.model.RoomOption;
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
class RoomOptionServiceTest {

    @Autowired
    private RoomOptionService roomOptionService;

    @MockBean
    private RoomOptionDAO roomOptionDAO;


    @Test
    void findAllByOrderId() {
        RoomOption roomOption1 = RoomOption.builder()
                .id(1).orderId(1)
                .build();
        RoomOption roomOption2 = RoomOption.builder()
                .id(2).orderId(1)
                .build();
        List<RoomOption> list = new ArrayList<>();
        list.add(roomOption1);
        list.add(roomOption2);
        when(roomOptionDAO.findAllByOrderId(1)).thenReturn(list);
        assertEquals(2, roomOptionService.findAllByOrderId(1).size());
        verify(roomOptionDAO, times(1)).findAllByOrderId(anyInt());
    }
}