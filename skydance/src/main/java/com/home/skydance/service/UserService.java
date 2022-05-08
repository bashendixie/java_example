package com.home.skydance.service;

import com.home.skydance.dao.UserMapper;
import com.home.skydance.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 添加用户
     * @param userName
     */
    public void addUser(String userName)
    {

    }

    /**
     * 批量添加用户
     * @param users
     */
    public void addUsersList(List<User> users)
    {
        userMapper.insertForeach(users);
    }

}
