package com.example.marathon.mapper;

import com.example.marathon.bean.dto.MonthlyTaskStatsDTO;
import com.example.marathon.entity.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 任务发布表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
public interface TaskMapper extends BaseMapper<Task> {

    List<MonthlyTaskStatsDTO> getMonthlyTaskStats();

}
