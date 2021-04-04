package cn.testlove.database.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

/**
 * @author testlove
 */
@Data
public class RoleDO {
   @TableId(type = IdType.AUTO)
   Integer roleId;
   String roleName;
   List<String> access;
}
