package org.example.service;

import org.example.model.RoomOption;

import java.util.List;

public interface RoomOptionService {

    RoomOption save(RoomOption roomOption) throws Exception;

    RoomOption update(RoomOption roomOption) throws Exception;

    RoomOption findById(Integer id) throws Exception;

    List<RoomOption> findAll();

    List<RoomOption> findAllByOrderId(Integer id);

    void delete(RoomOption roomOption);
}
