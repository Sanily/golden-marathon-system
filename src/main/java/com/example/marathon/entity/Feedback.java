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
 * 志愿者反馈信息表
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Getter
@Setter
@TableName("feedback")
@ApiModel(value = "Feedback对象", description = "志愿者反馈信息表")
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "反馈主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;


    @ApiModelProperty(value = "反馈者用户ID，外键")
    @TableField("volunteer_id")
    private Long volunteerId;

    @ApiModelProperty(value = "反馈内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "反馈时间")
    @TableField("created_at")
    private Date createdAt;

    @ApiModelProperty(value = "反馈处理状态")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "回复内容")
    @TableField("reply_content")
    private String replyContent;


}
