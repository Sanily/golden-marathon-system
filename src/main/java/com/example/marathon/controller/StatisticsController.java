package com.example.marathon.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.marathon.bean.dto.*;
import com.example.marathon.bean.vm.LoginVm;
import com.example.marathon.bean.vo.TokenBean;
import com.example.marathon.bean.vo.UserLoginVo;
import com.example.marathon.entity.EventNews;
import com.example.marathon.entity.Users;
import com.example.marathon.mapper.EventNewsMapper;
import com.example.marathon.mapper.TaskMapper;
import com.example.marathon.mapper.VolunteerTaskMapper;
import com.example.marathon.service.IEventNewsService;
import com.example.marathon.service.IUsersService;
import com.example.marathon.service.IVolunteerTaskService;
import com.example.marathon.util.AppContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/statistics")
@Api(description = "数据统计api")
@Slf4j
public class StatisticsController {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private VolunteerTaskMapper volunteerTaskMapper;


    @Autowired
    private EventNewsMapper eventNewsMapper;

    @Autowired
    private IVolunteerTaskService iVolunteerTaskService;


    @GetMapping("/getMonthlyTaskStats")
    @ApiOperation(value = "管理员端-柱状图")
    public ResponseEntity getMonthlyTaskStats() {
        List<MonthlyTaskStatsDTO> monthlyTaskStats = taskMapper.getMonthlyTaskStats();
        return ResponseEntity.ok(monthlyTaskStats);
    }

    @GetMapping("/getTop10Volunteers")
    @ApiOperation(value = "管理员端-饼图")
    public ResponseEntity getTop10Volunteers() {
        List<VolunteerTaskCountDTO> top10Volunteers = volunteerTaskMapper.getTop10Volunteers();
        return ResponseEntity.ok(top10Volunteers);
    }


    @GetMapping("/getTaskProgressList")
    @ApiOperation(value = "管理员端-进度条列表")
    public ResponseEntity getTaskProgressList() {
        List<TaskProgressDTO> taskProgressList = volunteerTaskMapper.getTaskProgressList();
        return ResponseEntity.ok(taskProgressList);
    }


    @GetMapping("/getAllEventNews")
    @ApiOperation(value = "志愿者端-轮播赛事动态信息")
    public ResponseEntity getAllEventNews() {
        List<EventNews> eventNews = eventNewsMapper.selectList(Wrappers.lambdaQuery(EventNews.class));
        return ResponseEntity.ok(eventNews);
    }


    @GetMapping("/getMonthlyStats")
    @ApiOperation(value = "志愿者端-折线图")
    public ResponseEntity getMonthlyStats() {

        List<VolunteerTaskMonthlyStatsDTO> monthlyStats = iVolunteerTaskService.getMonthlyStats(AppContext.getManageToken().getUserId());
        return ResponseEntity.ok(monthlyStats);
    }



    @GetMapping("/getVolunteerTaskStats")
    @ApiOperation(value = "志愿者端-饼图：统计我参与的总的任务次数和任务完成情况")
    public ResponseEntity getVolunteerTaskStats() {

        VolunteerTaskStatsDTO volunteerTaskStats = volunteerTaskMapper.getVolunteerTaskStats(AppContext.getManageToken().getUserId());
        return ResponseEntity.ok(volunteerTaskStats);
    }
}






