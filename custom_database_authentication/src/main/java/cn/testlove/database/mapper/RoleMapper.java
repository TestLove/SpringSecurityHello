package cn.testlove.database.mapper;

import cn.testlove.database.entity.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author admin
 */
@Mapper
public interface RoleMapper {
    String selectRoleNameByRoleId(Integer roleId);
    List<String> selectAllAuthorities();
    List<RoleDO> selectRoleInfoByAccess(String access);
    List<String> selectRoleNameByAccess(String access);
}
