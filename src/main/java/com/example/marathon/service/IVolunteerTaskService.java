package com.example.marathon.service;

import com.example.marathon.bean.dto.VolunteerTaskMonthlyStatsDTO;
import com.example.marathon.bean.vm.AssignVm;
import com.example.marathon.entity.VolunteerTask;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 志愿者任务分配表 服务类
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
public interface IVolunteerTaskService extends IService<VolunteerTask> {

    Boolean  assign(AssignVm assignVm);


    Boolean confirmReceipt(Integer taskId,Integer receipt);



    Boolean confirmCompletion(Integer taskId);


    List<VolunteerTaskMonthlyStatsDTO> getMonthlyStats(Long volunteerId);
}
