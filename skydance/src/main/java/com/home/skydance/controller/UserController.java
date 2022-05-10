package com.home.skydance.controller;

import com.home.skydance.entity.User;
import com.home.skydance.service.CronService;
import com.home.skydance.service.UserService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.net.www.http.HttpClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    private final CronService cronService;
    private final UserService userService;

    public UserController(CronService cronService, UserService userService) {
        this.cronService = cronService;
        this.userService = userService;
    }

    /**
     * 添加用户
     */
    @PostMapping("/addUser")
    public void addUser(String userName)
    {
        if(StringUtils.isNotBlank(userName))
        {
            userService.addUser(userName);
        }
    }

    /**
     * 批量添加用户
     */
    @PostMapping("/addUsersList")
    public void addUsersList(List<User> users)
    {
        userService.addUsersList(users);
    }

    /**
     * 模拟批量添加用户
     */
    @PostMapping("/mockAddUsersList")
    public void mockAddUsersList()
    {
        List<User> users = new ArrayList<>();
        for(int i=0; i<300000; i++)
        {
            User user = new User();
            user.setUserName("用户" + i);
            user.setCreateTime(new Date());
            users.add(user);
        }

        userService.addUsersList(users);
    }

    @PostMapping("/checkUsersTimeTask")
    public void checkUsersTimeTask()
    {
        try {
            cronService.checkUsersTimeTask();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
