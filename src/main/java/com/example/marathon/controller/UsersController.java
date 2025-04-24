package com.example.marathon.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.marathon.bean.query.UserQuery;
import com.example.marathon.exeption.BusinessException;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.marathon.service.IUsersService;
import com.example.marathon.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * <p>
 * 系统用户表（含管理员和志愿者） 前端控制器
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Api(description = "用户api")
@Controller
@RequestMapping("/users")
public class UsersController {


    @Autowired
    private IUsersService iUsersService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Users>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize, UserQuery query) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Users> aPage = iUsersService.page(new Page<>(current, pageSize),Wrappers.lambdaQuery(Users.class)
                .eq(Users::getRole,"VOLUNTEER")
                .eq(StringUtils.isNotBlank(query.getName()),Users::getRealName,query.getName())
                .eq(StringUtils.isNotBlank(query.getPhone()),Users::getPhone,query.getPhone())
        );
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Users> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iUsersService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Users params) {
        Users one = iUsersService.getOne(Wrappers.lambdaQuery(Users.class).eq(Users::getUsername, params.getUsername()));

        if (Objects.nonNull(one)) {
            throw new BusinessException("用户名已存在");
        }
        iUsersService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iUsersService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Users params) {
        iUsersService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
