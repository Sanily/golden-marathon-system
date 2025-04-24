package com.example.marathon.mapper;

import com.example.marathon.bean.dto.TaskProgressDTO;
import com.example.marathon.bean.dto.VolunteerTaskCountDTO;
import com.example.marathon.bean.dto.VolunteerTaskMonthlyStatsDTO;
import com.example.marathon.bean.dto.VolunteerTaskStatsDTO;
import com.example.marathon.entity.VolunteerTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 志愿者任务分配表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2025-04-18
 */
public interface VolunteerTaskMapper extends BaseMapper<VolunteerTask> {
    List<VolunteerTaskCountDTO> getTop10Volunteers();


    List<TaskProgressDTO> getTaskProgressList();




    @Select("SELECT DATE_FORMAT(confirmed_time, '%Y-%m') AS month, COUNT(*) AS accepted_count " +
            "FROM volunteer_task WHERE volunteer_id = #{volunteerId} AND confirmed = 1 " +
            "GROUP BY DATE_FORMAT(confirmed_time, '%Y-%m') ORDER BY month")
    List<VolunteerTaskMonthlyStatsDTO> getAcceptedStats(@Param("volunteerId") Long volunteerId);

    @Select("SELECT DATE_FORMAT(completed_time, '%Y-%m') AS month, COUNT(*) AS completed_count " +
            "FROM volunteer_task WHERE volunteer_id = #{volunteerId} AND confirmed = 1 " +
            "AND completed = 1 AND completed_time IS NOT NULL " +
            "GROUP BY DATE_FORMAT(completed_time, '%Y-%m') ORDER BY month")
    List<VolunteerTaskMonthlyStatsDTO> getCompletedStats(@Param("volunteerId") Long volunteerId);


    VolunteerTaskStatsDTO getVolunteerTaskStats(@Param("volunteerId") Long volunteerId);



}
