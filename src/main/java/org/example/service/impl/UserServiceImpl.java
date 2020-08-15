package org.example.service.impl;

import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public User save(User user) throws Exception {
        if (user.getId() != null) {
            throw new Exception("user with this id already exists");
        }
        return userDAO.save(user);
    }

    @Override
    public User update(User user) throws Exception {
        if (user.getId() == null) {
            throw new Exception("without Id it is not possible to update user");
        }
        return userDAO.save(user);
    }

    @Override
    public User findById(Integer id) throws Exception {
        return userDAO.findById(id).orElseThrow(() -> new Exception("user not found"));
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }
}
