package org.example.dao;

import org.example.model.RoomOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomOptionDAO extends JpaRepository<RoomOption, Integer> {

    List<RoomOption> findAllByOrderId(Integer id);

}
