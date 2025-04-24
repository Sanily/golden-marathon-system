package com.example.marathon.bean.dto;

import lombok.Data;

@Data
public class TaskProgressDTO {
    private Long taskId;           // 任务ID
    private String taskName;       // 任务名称
    private Integer assignedNumber; // 分配人数
    private Integer completedNumber; // 完成人数
}
