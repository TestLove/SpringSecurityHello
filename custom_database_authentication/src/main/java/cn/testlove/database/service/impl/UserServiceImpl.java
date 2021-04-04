package cn.testlove.database.service.impl;

import cn.testlove.database.entity.UserDO;
import cn.testlove.database.mapper.UserMapper;
import cn.testlove.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public int insertUser(UserDO user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.insertUser(user);
        return 0;
    }

    @Override
    public int updateUser(UserDO user) {
        return 0;
    }

    @Override
    public UserDO selectOneUserById() {
        return null;
    }

    @Override
    public UserDO selectOneUserByUserName(String userName) {
        return userMapper.selectOneUserByUserName(userName);
    }
}
