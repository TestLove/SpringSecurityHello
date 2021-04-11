package cn.testlove.database.controller;

import cn.testlove.database.aspect.SystemLog;
import cn.testlove.database.entity.TokenModel;
import cn.testlove.database.entity.UserDO;
import cn.testlove.database.service.LoginService;
import cn.testlove.database.service.UserService;
import cn.testlove.database.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogInController {
    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;
    @SystemLog
    @PostMapping("/successLogIn")
    public String successLogIn(String username, String password){
        UserDO user = userService.selectOneUserByUserName(username);

        TokenModel tokenModel = new TokenModel(JwtUtils.generateToken(user));
        tokenModel.setRoleId(user.getRoleId());
        tokenModel.setUserId(user.getUserId());
    return tokenModel.getToken();
}
    @PostMapping("/failureLogIn")
    public String failureLogIn(){
        return "failure";
    }
    @PostMapping("/guest/login")
    public String logIn( String username,String password){

        if(loginService.login(username, password)){
            return successLogIn(username, password);
        }
       else {
           return failureLogIn();
       }

    }
}
