package cn.testlove.database.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author admin
 */
@Data
public class UserDO {
    @TableId(type = IdType.AUTO)
    Integer userId;
    String username;
    String password;
    Integer roleId;

}
