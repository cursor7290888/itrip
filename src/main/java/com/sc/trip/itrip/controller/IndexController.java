package com.sc.trip.itrip.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sc.trip.itrip.entity.User;
import com.sc.trip.itrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired

    private UserService userService;
    @GetMapping("/index")
    public String  test(){
        Wrapper<User> wrapper = new EntityWrapper<>();
        return userService.selectList(wrapper).toString();


    }
}
