package com.example.marathon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.marathon.bean.dto.VolunteerTaskMonthlyStatsDTO;
import com.example.marathon.bean.vm.AssignVm;
import com.example.marathon.bean.vo.TokenBean;
import com.example.marathon.entity.Task;
import com.example.marathon.entity.VolunteerTask;
import com.example.marathon.mapper.TaskMapper;
import com.example.marathon.mapper.VolunteerTaskMapper;
import com.example.marathon.service.IVolunteerTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.marathon.util.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 志愿者任务分配表 服务实现类
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Service
public class VolunteerTaskServiceImpl extends ServiceImpl<VolunteerTaskMapper, VolunteerTask> implements IVolunteerTaskService {


    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Boolean assign(AssignVm assignVm) {
        List<VolunteerTask> existingTasks = this.list(
                new LambdaQueryWrapper<VolunteerTask>()
                        .eq(VolunteerTask::getTaskId, assignVm.getTaskId())
                        .in(VolunteerTask::getVolunteerId, assignVm.getVolunteerId())
        );

        Set<Long> existingVolunteerIds = existingTasks.stream()
                .map(VolunteerTask::getVolunteerId)
                .collect(Collectors.toSet());

        List<Long> newVolunteerIds = assignVm.getVolunteerId().stream()
                .filter(id -> !existingVolunteerIds.contains(id))
                .collect(Collectors.toList());

        List<VolunteerTask> insertList = newVolunteerIds.stream().map(id -> {
            VolunteerTask vt = new VolunteerTask();
            vt.setTaskId(assignVm.getTaskId());
            vt.setVolunteerId(id);
            vt.setConfirmed(0);
            vt.setCompleted(false);
            return vt;
        }).collect(Collectors.toList());


        Task task  = new Task();
        task.setId(assignVm.getTaskId());
        task.setStatus("已分配");
        taskMapper.updateById(task);

        return this.saveBatch(insertList);


    }

    @Override
    public Boolean confirmReceipt(Integer taskId,Integer receipt) {
        TokenBean manageToken = AppContext.getManageToken();
        LambdaUpdateWrapper<VolunteerTask> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(VolunteerTask::getTaskId, taskId).eq(VolunteerTask::getVolunteerId,manageToken.getUserId())
                .set(VolunteerTask::getConfirmedTime,new Date())
                .set(VolunteerTask::getConfirmed, receipt);



        //修改任务表确认人数
        Task task = taskMapper.selectById(taskId);
        task.setConfirmedNumber(Objects.isNull(task.getConfirmedNumber())?1:task.getConfirmedNumber()+1);
        taskMapper.updateById(task);
        return this.update(null, wrapper);

    }

    @Override
    public Boolean confirmCompletion(Integer taskId) {
        TokenBean manageToken = AppContext.getManageToken();
        LambdaUpdateWrapper<VolunteerTask> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(VolunteerTask::getTaskId, taskId).eq(VolunteerTask::getVolunteerId,manageToken.getUserId())
                .set(VolunteerTask::getCompleted, true)
                .set(VolunteerTask::getCompletedTime,new Date())
        ;
        return this.update(null, wrapper);
    }

    @Override
    public List<VolunteerTaskMonthlyStatsDTO> getMonthlyStats(Long volunteerId) {
        // 查询接收任务数
        List<VolunteerTaskMonthlyStatsDTO> acceptedList = this.baseMapper.getAcceptedStats(volunteerId);

        // 查询完成任务数
        List<VolunteerTaskMonthlyStatsDTO> completedList = this.baseMapper.getCompletedStats(volunteerId);

        // 使用 LinkedHashMap 保持月份顺序
        Map<String, VolunteerTaskMonthlyStatsDTO> statsMap = new LinkedHashMap<>();

        // 填充接收任务数据
        for (VolunteerTaskMonthlyStatsDTO stats : acceptedList) {
            String month = stats.getMonth();
            statsMap.put(month, new VolunteerTaskMonthlyStatsDTO(month, stats.getAcceptedCount(), 0));
        }

        // 填充完成任务数据
        for (VolunteerTaskMonthlyStatsDTO stats : completedList) {
            String month = stats.getMonth();
            VolunteerTaskMonthlyStatsDTO existingStats = statsMap.get(month);
            if (existingStats == null) {
                statsMap.put(month, new VolunteerTaskMonthlyStatsDTO(month, 0, stats.getCompletedCount()));
            } else {
                existingStats.setCompletedCount(stats.getCompletedCount());
            }
        }

        // 返回合并后的数据
        return new ArrayList<>(statsMap.values());
    }


}
