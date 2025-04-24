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
 * 通知与公告表
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Getter
@Setter
@TableName("notification")
@ApiModel(value = "Notification对象", description = "通知与公告表")
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "通知主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "通知标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "通知内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "类型：SYSTEM-系统通知，ANNOUNCEMENT-管理员公告")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "发布者用户ID，NULL 表示系统自动")
    @TableField("created_by")
    private Long createdBy;

    @ApiModelProperty(value = "发布时间")
    @TableField("created_at")
    private Date createdAt;
}
