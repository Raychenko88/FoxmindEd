package org.example.service;

import org.example.dao.UserDAO;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserDAO userDAO;


    @Test
    void save() throws Exception {
        User user = User.builder().
                firstName("test_fn").
                lastName("test_ln").
                build();
        when(userDAO.save(any(User.class))).thenReturn(user);
        User user1 = userService.save(user);
        assertNotNull(user1);
        assertEquals(user.getFirstName(), user1.getFirstName());
        verify(userDAO, times(1)).save(any(User.class));
    }

    @Test
    void update() throws Exception {
        User user = User.builder().
                firstName("test_fn").
                lastName("test_ln").
                build();
        user.setId(1);
        when(userDAO.findById(anyInt())).thenReturn(of(user));
        when(userDAO.save(any(User.class))).thenReturn(user);
        User user1 = userService.update(user);
        assertNotNull(userDAO.findById(user1.getId()));
        verify(userDAO, times(1)).findById(anyInt());
        verify(userDAO, times(1)).save(any(User.class));
    }

    @Test
    void findById() throws Exception {
        User user = User.builder().
                firstName("test_fn").
                lastName("test_ln").
                build();
        user.setId(1);
        when(userDAO.findById(anyInt())).thenReturn(of(user));
        when(userDAO.save(any(User.class))).thenReturn(user);
        User user1 = userService.update(user);
        assertNotNull(userService.findById(user1.getId()));
        verify(userDAO, times(1)).findById(anyInt());
        verify(userDAO, times(1)).save(any(User.class));
    }

    @Test
    void delete() {
        User user = User.builder().
                firstName("test_fn").
                lastName("test_ln").
                build();
        user.setId(1);
        when(userDAO.findById(anyInt())).thenReturn(of(user)).thenReturn(null);
        when(userDAO.save(any(User.class))).thenReturn(user);
        User user1 = null;
        try {
            user1 = userService.update(user);
        } catch (Exception e) {
            fail("update was crashed");
        }
        try {
            assertNotNull(userService.findById(user1.getId()));
            userService.delete(user1);
            assertNull(userService.findById(user1.getId()));
        } catch (Exception e) {
            verify(userDAO, times(2)).findById(anyInt());
            verify(userDAO, times(1)).save(any(User.class));
        }
    }
}