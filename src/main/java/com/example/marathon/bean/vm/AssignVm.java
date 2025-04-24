package com.example.marathon.bean.vm;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class AssignVm {
    @ApiModelProperty(value = "任务id")
    private Long taskId;

    @ApiModelProperty(value = "志愿者id")
    private List<Long> volunteerId;
}
