package org.example.service.impl;

import org.example.dao.RoomDAO;
import org.example.model.Order;
import org.example.model.Room;
import org.example.model.RoomOption;
import org.example.model.RoomStatus;
import org.example.service.RoomOptionService;
import org.example.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    RoomDAO roomDAO;

    @Autowired
    RoomOptionService roomOptionService;

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
    public List<Room> findAllByStartDateAndEndDateAndOpenStatus(LocalDate startDate, LocalDate endDate, String status) {
        return roomDAO.findAllByStartDateAndEndDateAndStatus(startDate, endDate, RoomStatus.OPEN.getStatus());
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
    public BigDecimal costServices(Order order) {
        BigDecimal sum = new BigDecimal(0);
        for (RoomOption opnion : roomOptionService.findAllByOrderId(order.getId())) {
           sum = sum.add(opnion.getPrice());
        }
        return sum;
    }

    @Override
    public BigDecimal costIncludingAdditionalServices(Order order) throws Exception {
        Room room = findById(order.getRoomId());
        if (room == null){
            throw new Exception("room not found");
        }
     return room.getPrice().add(costServices(order));
    }

    @Override
    public void delete(Room room) {
        roomDAO.delete(room);
    }


}
