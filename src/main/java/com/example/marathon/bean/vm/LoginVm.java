package com.example.marathon.bean.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class LoginVm {
    @ApiModelProperty(value = "用户名")
    @NotNull
    private String name;

    @ApiModelProperty(value = "密码")
    @NotNull
    private String password;
}
