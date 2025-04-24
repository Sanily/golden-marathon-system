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
 * 志愿者任务分配表
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Getter
@Setter
@TableName("volunteer_task")
@ApiModel(value = "VolunteerTask对象", description = "志愿者任务分配表")
public class VolunteerTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "记录主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "任务ID，外键")
    @TableField("task_id")
    private Long taskId;

    @ApiModelProperty(value = "志愿者用户ID，外键")
    @TableField("volunteer_id")
    private Long volunteerId;

    @ApiModelProperty(value = "管理员分配时间")
    @TableField("assign_time")
    private Date assignTime;

    @ApiModelProperty(value = "志愿者是否已确认")
    @TableField("confirmed")
    private Integer confirmed;

    @ApiModelProperty(value = "确认时间")
    @TableField("confirmed_time")
    private Date confirmedTime;

    @ApiModelProperty(value = "志愿者是否已完成")
    @TableField("completed")
    private Boolean completed;

    @ApiModelProperty(value = "完成时间")
    @TableField("completed_time")
    private Date completedTime;
}
