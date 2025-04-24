package com.example.marathon.bean.dto;

import lombok.Data;

@Data
public class VolunteerTaskCountDTO {
    private String realName;  // 志愿者名字
    private int taskCount;    // 完成任务的数量
}
