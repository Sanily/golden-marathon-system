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
 * 赛事动态信息表
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Getter
@Setter
@TableName("event_news")
@ApiModel(value = "EventNews对象", description = "赛事动态信息表")
public class EventNews implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "动态主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "所属赛事ID，外键")
    @TableField("event_id")
    private Integer eventId;

    @ApiModelProperty(value = "动态内容，如天气、进程等")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "动态发布时间")
    @TableField("created_at")
    private Date createdAt;
}
