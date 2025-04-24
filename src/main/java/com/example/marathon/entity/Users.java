package com.example.marathon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统用户表（含管理员和志愿者）
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Getter
@Setter
@TableName("users")
@ApiModel(value = "Users对象", description = "系统用户表（含管理员和志愿者）")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "登录用户名，唯一")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "登录密码（加密存储）")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "用户角色：ADMIN-管理员，VOLUNTEER-志愿者")
    @TableField("role")
    private String role;

    @ApiModelProperty(value = "真实姓名")
    @TableField("real_name")
    private String realName;

    @ApiModelProperty(value = "性别：")
    @TableField("gender")
    private String gender;

    @ApiModelProperty(value = "联系电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "身份证号")
    @TableField("id_card")
    private String idCard;

    @ApiModelProperty(value = "紧急联系人电话")
    @TableField("emergency_contact")
    private String emergencyContact;


    @ApiModelProperty(value = "年龄")
    @TableField("age")
    private String age;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "记录创建时间")
    @TableField("created_at")
    private Date createdAt;

    @ApiModelProperty(value = "记录更新时间")
    @TableField("updated_at")
    private Date updatedAt;
}
