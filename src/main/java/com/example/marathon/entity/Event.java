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
 * 赛事基础信息表
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Getter
@Setter
@TableName("event")
@ApiModel(value = "Event对象", description = "赛事基础信息表")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "赛事主键，自增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "赛事名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "赛事日期")
    @TableField("event_date")
    private Date eventDate;

    @ApiModelProperty(value = "起点位置")
    @TableField("start_location")
    private String startLocation;

    @ApiModelProperty(value = "终点位置")
    @TableField("end_location")
    private String endLocation;

    @ApiModelProperty(value = "路线地图图片URL或路径（静态图）")
    @TableField("route_map_url")
    private String routeMapUrl;

    @ApiModelProperty(value = "记录创建时间")
    @TableField("created_at")
    private Date createdAt;
}
