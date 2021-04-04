package cn.testlove.database.mapper;

import cn.testlove.database.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author admin
 */
@Mapper
public interface UserMapper {
    int insertUser(UserDO user);
    UserDO selectOneUserByUserName(String userName);
    UserDO selectOneUserById(String userName);

}
