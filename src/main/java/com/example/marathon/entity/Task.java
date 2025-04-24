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
 * 任务发布表
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Getter
@Setter
@TableName("task")
@ApiModel(value = "Task对象", description = "任务发布表")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "任务主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "任务名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "任务描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "任务类别")
    @TableField("category")
    private String category;

    @ApiModelProperty(value = "任务开始时间")
    @TableField("start_time")
    private Date startTime;

    @ApiModelProperty(value = "任务结束时间")
    @TableField("end_time")
    private Date endTime;

    @ApiModelProperty(value = "任务地点")
    @TableField("location")
    private String location;

    @ApiModelProperty(value = "所需志愿者人数")
    @TableField("required_number")
    private Integer requiredNumber;

    @ApiModelProperty(value = "已确认志愿者人数")
    @TableField("confirmed_number")
    private Integer confirmedNumber;


    @ApiModelProperty(value = "任务分配状态   已分配  未分配")
    @TableField("status")
    private String status;



    @ApiModelProperty(value = "发布任务的管理员用户ID")
    @TableField("created_by")
    private Long createdBy;

    @ApiModelProperty(value = "记录创建时间")
    @TableField("created_at")
    private Date createdAt;
}
