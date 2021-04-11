package cn.testlove.database.util;

import cn.testlove.database.aspect.SystemLog;
import cn.testlove.database.entity.TokenModel;
import cn.testlove.database.entity.UserDO;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtils {
    private static String SECRET_KEY = "TestLove";
    private static final long EXPIRE_TIME = 8 * 60 * 60 * 1000;
    private  static final String ROLE_ID="roleId";
    private  static final String USER_ID="userId";
    private  static final String TYPE="type";

    public static String generateToken(UserDO user) {
        return createToken(user);
    }

    private static String createToken(UserDO user) {

        return Jwts
                .builder()
               .claim(ROLE_ID,user.getRoleId())
                .claim(USER_ID, user.getUserId())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setHeaderParam(TYPE,"JWT")
                .compact();
    }
    @SystemLog
    private static boolean validateToken(TokenModel token,Claims claims){
        try {
            System.out.println("验证有效性");
            claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token.getToken())
                    .getBody();

        }catch (Exception e) {
            System.out.println("tokenwuxiao");
            token.setTokenExpired(true);
            return false;
        }
        if(claims==null){
            return false;
        }
        Date expiration = claims.getExpiration();
        if (System.currentTimeMillis() - expiration.getTime()>0) {
            return false;
        }
        return true;



    }
    public static void resolveToken(TokenModel token){
        //不存在token,直接返回
        if (token.getToken() == null) {
            return;
        }
        //存在token,设置为true
        token.setTokenExists(true);
        Claims claims;
        //解析claim
        try {
            System.out.println("验证有效性");
            claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token.getToken())
                    .getBody();

        }catch (Exception e) {
            System.out.println("tokenwuxiao");
            token.setTokenExpired(true);
            return ;
        }
        if(claims==null){
            return ;
        }
//        Claims claims=null;
        //设置有效性
        if(!JwtUtils.validateToken(token,claims)){
            token.setTokenExpired(true);
        }
        //设置roleId与userid
        Integer roleId = (Integer) claims.get(ROLE_ID);
        Integer userId = (Integer) claims.get(USER_ID);
        token.setUserId(userId);
        token.setRoleId(roleId);


    }


}
