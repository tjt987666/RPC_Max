package com.zking.xiongda.serializer;

import com.zking.xiongda.model.User;

public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {

        System.out.println("用户名"+user.getName());

        return user;
    }
}
