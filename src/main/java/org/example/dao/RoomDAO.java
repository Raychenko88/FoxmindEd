package org.example.dao;

import org.example.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomDAO extends JpaRepository<Room, Integer> {

    List<Room> findAllByCategory(String category);

}
