package cn.testlove.database.service;

import cn.testlove.database.entity.UserDO;

import java.util.List;

/**
 * @author admin
 */
public interface UserService {
    int insertUser(UserDO user);
    int updateUser(UserDO user);
    UserDO selectOneUserById();
    UserDO selectOneUserByUserName(String userName);



}
