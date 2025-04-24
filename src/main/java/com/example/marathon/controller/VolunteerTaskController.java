package com.example.marathon.controller;

import com.example.marathon.bean.vo.TokenBean;
import com.example.marathon.util.AppContext;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.marathon.service.IVolunteerTaskService;
import com.example.marathon.entity.VolunteerTask;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 志愿者任务分配表 前端控制器
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Controller
@RequestMapping("/volunteer-task")
public class VolunteerTaskController {


    @Autowired
    private IVolunteerTaskService iVolunteerTaskService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<VolunteerTask>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        TokenBean manageToken = AppContext.getManageToken();
        Page<VolunteerTask> aPage = iVolunteerTaskService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VolunteerTask> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iVolunteerTaskService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody VolunteerTask params) {
        iVolunteerTaskService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iVolunteerTaskService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody VolunteerTask params) {
        iVolunteerTaskService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }



    @ApiOperation(value = "任务确认接收")
    @PostMapping(value = "/confirmReceipt")
    public ResponseEntity<Object> confirmReceipt(@ApiParam("任务id")@RequestParam Integer taskId,@ApiParam("0待确认，1.已接受，2已拒绝") @RequestParam Integer receipt) {
        iVolunteerTaskService.confirmReceipt(taskId,receipt);
        return new ResponseEntity<>("confirmReceipt successfully", HttpStatus.OK);
    }

    @ApiOperation(value = "任务确认完成")
    @PostMapping(value = "/confirmCompletion")
    public ResponseEntity<Object> confirmCompletion( Integer taskId) {
        iVolunteerTaskService.confirmCompletion(taskId);
        return new ResponseEntity<>("confirmCompletion successfully", HttpStatus.OK);
    }
}
