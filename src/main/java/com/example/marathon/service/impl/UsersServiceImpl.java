package com.example.marathon.service.impl;

import com.example.marathon.entity.Users;
import com.example.marathon.mapper.UsersMapper;
import com.example.marathon.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表（含管理员和志愿者） 服务实现类
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
