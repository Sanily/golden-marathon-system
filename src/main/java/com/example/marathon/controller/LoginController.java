package com.example.marathon.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.marathon.bean.vm.LoginVm;
import com.example.marathon.bean.vo.TokenBean;
import com.example.marathon.bean.vo.UserLoginVo;
import com.example.marathon.entity.Users;
import com.example.marathon.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
@Api(description = "后台登录api")
@Slf4j
public class LoginController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IUsersService iUsersService;


    /**
     * 登录
     */
    @PostMapping("/in")
    @ApiOperation(value = "登录")
    public ResponseEntity login(@Validated @RequestBody LoginVm loginVm) throws Exception {
        log.info("用户登录");
        //获取userId
        Users login = iUsersService.getOne(Wrappers.lambdaQuery(Users.class).eq(Users::getUsername,loginVm.getName()).eq(Users::getPassword,loginVm.getPassword()));
        if (login == null) {
            throw new Exception("用户名或密码错误");
        }
        //根据封装用户信息到TokenBean并缓存
        TokenBean tokenBean = new TokenBean();
        tokenBean.setUsername(loginVm.getName());
        //这边存储下用户角色
        tokenBean.setRole(login.getRole());
        tokenBean.setUserId(login.getId());
        String k = "tokenBean_" + UUID.randomUUID();
        redisTemplate.boundValueOps(k).set(tokenBean, 12, TimeUnit.HOURS);
        UserLoginVo userLoginVo = new UserLoginVo();
        userLoginVo.setRole(login.getRole());
        userLoginVo.setUserName(login.getUsername());
        userLoginVo.setRealName(login.getRealName());
        userLoginVo.setLoginStatus(1);
        userLoginVo.setToken(k);
        userLoginVo.setUserId(login.getId());
        return ResponseEntity.ok(userLoginVo);
    }

    @GetMapping("/out")
    @ApiOperation(value = "登出")
    public ResponseEntity loginOut(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        redisTemplate.delete(authorization.split(" ")[1]);
        return ResponseEntity.ok("ok");
    }
}






