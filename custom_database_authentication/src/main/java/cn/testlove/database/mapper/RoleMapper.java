package cn.testlove.database.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author admin
 */
@Mapper
public interface RoleMapper {
    String selectRoleNameByRoleId(Integer roleId);
}
