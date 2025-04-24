package com.example.marathon.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.marathon.service.INotificationService;
import com.example.marathon.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 通知与公告表 前端控制器
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Controller
@RequestMapping("/notification")
public class NotificationController {


    @Autowired
    private INotificationService iNotificationService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Notification>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Notification> aPage = iNotificationService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Notification> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iNotificationService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Notification params) {
        iNotificationService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iNotificationService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Notification params) {
        iNotificationService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
