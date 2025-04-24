package com.example.marathon.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.marathon.service.IEventNewsService;
import com.example.marathon.entity.EventNews;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 赛事动态信息表 前端控制器
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Api(description = " 赛事动态api")
@Controller
@RequestMapping("/event-news")
public class EventNewsController {


    @Autowired
    private IEventNewsService iEventNewsService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<EventNews>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize,Integer eventId) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<EventNews> aPage = iEventNewsService.page(new Page<>(current, pageSize), Wrappers.lambdaQuery(EventNews.class).eq(EventNews::getEventId,eventId));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventNews> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iEventNewsService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody EventNews params) {
        iEventNewsService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iEventNewsService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody EventNews params) {
        iEventNewsService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
