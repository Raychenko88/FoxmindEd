package org.example.service;

import org.example.model.Order;
import org.example.model.Room;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RoomService {

    Room save(Room room) throws Exception;

    Room update(Room room) throws Exception;

    Room findById(Integer id) throws Exception;

    List<Room> findAllByCategory(String category);

    BigDecimal costServices(Order order);

    BigDecimal costIncludingAdditionalServices(Order order) throws Exception;

    void delete(Room room);
}
