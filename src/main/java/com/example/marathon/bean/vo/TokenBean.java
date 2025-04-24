package com.example.marathon.bean.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* 用户基本信息
* */
@Data
public class TokenBean implements Serializable {




    String username;


    String role;


    String token;

    @ApiModelProperty(value = "用户id")
    private Long userId;



}
