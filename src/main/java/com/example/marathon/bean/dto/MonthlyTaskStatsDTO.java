package com.example.marathon.bean.dto;

import lombok.Data;

@Data
public class MonthlyTaskStatsDTO {

    private String month;
    private Integer totalTasks;
    private Integer completedTasks;
}
