package org.example.service.impl;

import org.example.dao.RoomOptionDAO;
import org.example.model.RoomOption;
import org.example.service.RoomOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomOptionServiceImpl implements RoomOptionService {

    @Autowired
    private RoomOptionDAO roomOptionDAO;


    @Override
    public RoomOption save(RoomOption roomOption) throws Exception {
        List<RoomOption> list = findAllByOrderId(roomOption.getOrderId());
        if (roomOption.getId() != null) {
            throw new Exception("room option with this id already exists");
        } else if (!list.isEmpty()) {
            for (RoomOption option : list) {
                if (option.getTitle().equals(roomOption.getTitle())) {
                    throw new Exception("room option option with this id already add");
                }
            }
        }
        return roomOptionDAO.save(roomOption);
    }

    @Override
    public RoomOption update(RoomOption roomOption) throws Exception {
        if (roomOption.getId() == null) {
            throw new Exception("without Id it is not possible to update room option");
        }
        return roomOptionDAO.save(roomOption);
    }

    @Override
    public RoomOption findById(Integer id) throws Exception {
        return roomOptionDAO.findById(id).orElseThrow(() -> new Exception("room option not found"));
    }

    @Override
    public List<RoomOption> findAll() {
        return roomOptionDAO.findAll();
    }

    @Override
    public List<RoomOption> findAllByOrderId(Integer id) {
        return roomOptionDAO.findAllByOrderId(id);
    }

    @Override
    public void delete(RoomOption roomOption) {
        roomOptionDAO.delete(roomOption);
    }
}
