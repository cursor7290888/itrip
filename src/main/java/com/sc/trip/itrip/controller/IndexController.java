package com.sc.trip.itrip.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sc.trip.itrip.entity.User;
import com.sc.trip.itrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class IndexController {
    @Autowired

    private UserService userService;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
     RedisScript<Long> redisScript;


    @GetMapping("/index")
    public String  index(){



        Wrapper<User> wrapper = new EntityWrapper<>();
        return userService.selectList(wrapper).toString();


    }
}
