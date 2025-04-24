package com.example.marathon.bean.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VolunteerTaskMonthlyStatsDTO {


    @ApiModelProperty("月份，格式为 yyyy-MM")
    private String month;

    @ApiModelProperty("接收任务数")
    private Integer acceptedCount;

    @ApiModelProperty("完成任务数")
    private Integer completedCount;


    public VolunteerTaskMonthlyStatsDTO(String month, Integer acceptedCount, Integer completedCount) {
        this.month = month;
        this.acceptedCount = acceptedCount;
        this.completedCount = completedCount;
    }

}
