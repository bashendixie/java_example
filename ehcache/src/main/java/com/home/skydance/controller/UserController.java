package com.home.skydance.controller;

import com.home.skydance.service.CacheForPojoService;
import com.home.skydance.service.CacheForXmlService;
import com.home.skydance.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final CacheForXmlService cacheForXmlService;
    private final UserService userService;

    public UserController(CacheForXmlService cacheForXmlService, UserService userService) {
        this.cacheForXmlService = cacheForXmlService;
        this.userService = userService;
    }

    /**
     * 添加缓存
     */
    @PostMapping("/addCache")
    public void addCache()
    {
        this.cacheForXmlService.addMenuListToCache("小猫");
    }


    @PostMapping("/getCache")
    public void getCache()
    {
        String str = this.cacheForXmlService.getMenuListToCache();
        System.out.println(str);
    }
}
