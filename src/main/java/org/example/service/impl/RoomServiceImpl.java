package org.example.service.impl;

import org.example.dao.RoomDAO;
import org.example.model.Room;
import org.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomDAO roomDAO;

    @Override
    public Room save(Room room) throws Exception {
        if (room.getId() != null) {
            throw new Exception("room with this id already exists");
        }
        return roomDAO.save(room);
    }

    @Override
    public Room update(Room room) throws Exception {
        if (room.getId() == null) {
            throw new Exception("without Id it is not possible to update room");
        }
        return roomDAO.save(room);
    }

    @Override
    public Room findById(Integer id) throws Exception {
        return roomDAO.findById(id).orElseThrow(() -> new Exception("room not found"));
    }

    @Override
    public List<Room> findAllByStartDateAndEndDateAndStatus(LocalDate startDate, LocalDate endDate, String status) {
        return roomDAO.findAllByStartDateAndEndDateAndStatus(startDate, endDate, status);
    }

    @Override
    public List<Room> findAllByCategory(String category) {
        return roomDAO.findAllByCategory(category);
    }

    @Override
    public List<Room> findAllByStatus(String status) {
        return roomDAO.findAllByStatus(status);
    }

    @Override
    public void delete(Room room) {
        roomDAO.delete(room);
    }
}
