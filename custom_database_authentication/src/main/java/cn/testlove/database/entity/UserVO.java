package cn.testlove.database.entity;

import cn.testlove.database.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author admin
 */
public class UserVO extends UserDO implements UserDetails  {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


    return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public UserVO(UserDO userDO){
        this.username = userDO.getUsername();
        this.password = userDO.getPassword();
        this.userId= userDO.getUserId();
        this.roleId=userDO.roleId;
    }
}
