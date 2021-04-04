package cn.testlove.database.controller;

import cn.testlove.database.entity.UserDO;
import cn.testlove.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
public class TestOneController {
    @Autowired
    UserService userService;
    @PostMapping("/add")
    public String add(@RequestBody UserDO user){

        System.out.println(user.toString());
        userService.insertUser(user);
        return "chengg";
    }
    @GetMapping("/test")
    public String test(String key){

        return key;
    }

}
