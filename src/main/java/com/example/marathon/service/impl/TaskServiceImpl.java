package com.example.marathon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.marathon.bean.vm.AssignVm;
import com.example.marathon.entity.Task;
import com.example.marathon.entity.VolunteerTask;
import com.example.marathon.mapper.TaskMapper;
import com.example.marathon.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.marathon.service.IVolunteerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 任务发布表 服务实现类
 * </p>
 *
 * @author
 * @since 2025-04-18
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {




}
