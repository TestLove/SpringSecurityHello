package cn.testlove.database.entity;

import cn.testlove.database.util.JwtUtils;
import lombok.Data;

/**
 * @author admin
 */
@Data
public class TokenModel {
    String token;
    int userId;
    int roleId;
    private boolean tokenExpired;
    private boolean tokenExists;
public TokenModel(String token){

    this.token = token;
}

@Override
    public String toString() {
        return userId+":"+token+":"+roleId;
    }
}
