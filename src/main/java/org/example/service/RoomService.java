package org.example.service;

import org.example.model.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface RoomService {

    Room save(Room room) throws Exception;

    Room update(Room room) throws Exception;

    Room findById(Integer id) throws Exception;

    List<Room> findAllByStartDateAndEndDateAndOpenStatus(LocalDate startDate, LocalDate endDate, String status);

    List<Room> findAllByCategory(String category);

    List<Room> findAllByStatus(String status);

    Integer costIncludingAdditionalServices(Room room, Set<String> additionalOptions);

    void delete(Room room);
}
