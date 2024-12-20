package com.zking.xiongda.serializer;

import com.zking.xiongda.model.User;

/**
 * 用户服务
 */
public interface UserService {

    /**
     * 获取用户
     *
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * mock服务-获取数字
     */
    default  short getNumber(){
        return 1;
    }
}
