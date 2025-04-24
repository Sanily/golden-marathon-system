package com.example.marathon.bean.dto;

import lombok.Data;

@Data
public class VolunteerTaskStatsDTO {
    private int totalTasks;          // 总任务次数
    private int completedTasks;      // 已完成任务次数
    private int uncompletedTasks;    // 未完成任务次数
}
