package org.example.service;

import org.example.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    Room save(Room room) throws Exception;

    Room update(Room room) throws Exception;

    Room findById(Integer id) throws Exception;

    List<Room> findAllByStartDateAndEndDateAndStatus(LocalDate startDate, LocalDate endDate, String status);

    List<Room> findAllByCategory(String category);

    List<Room> findAllByStatus(String status);

    void delete(Room room);
}
