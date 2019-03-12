package com.sc.trip.itrip.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sc.trip.itrip.dao.UserMapper;
import com.sc.trip.itrip.entity.User;
import com.sc.trip.itrip.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {

}
