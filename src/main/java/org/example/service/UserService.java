package org.example.service;

import org.example.model.User;

public interface UserService {

    User save(User user) throws Exception;

    User update(User user) throws Exception;

    User findById(Integer id) throws Exception;

    void delete(User user);
}
