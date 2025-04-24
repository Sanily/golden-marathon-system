package com.example.marathon.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.marathon.service.IEventService;
import com.example.marathon.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 赛事基础信息表 前端控制器
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Api(description = "赛事基础信息api")
@Controller
@RequestMapping("/event")
public class EventController {


    @Autowired
    private IEventService iEventService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Event>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Event> aPage = iEventService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Event> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iEventService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Event params) {
        iEventService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iEventService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Event params) {
        iEventService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
