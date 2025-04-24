package com.example.marathon.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class UserLoginVo {

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "登录名")
    private String userName;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "登录状态",example = "0：未登录  1：已登录")
    private Integer loginStatus;

    private String token;
}
