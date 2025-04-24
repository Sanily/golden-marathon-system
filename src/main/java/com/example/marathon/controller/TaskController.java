package com.example.marathon.controller;

import com.example.marathon.bean.vm.AssignVm;
import com.example.marathon.service.IVolunteerTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.marathon.service.ITaskService;
import com.example.marathon.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 任务发布表 前端控制器
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Api(description = "任务api")
@Controller
@RequestMapping("/task")
public class TaskController {


    @Autowired
    private ITaskService iTaskService;

    @Autowired
    private IVolunteerTaskService iVolunteerTaskService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Task>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Task> aPage = iTaskService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iTaskService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Task params) {
        iTaskService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iTaskService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Task params) {
        iTaskService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }



    @PostMapping("/assign")
    @ApiOperation(value = "分配任务")
    public ResponseEntity assign(AssignVm assignVm) {
        iVolunteerTaskService.assign(assignVm);
        return ResponseEntity.ok("ok");
    }
}
