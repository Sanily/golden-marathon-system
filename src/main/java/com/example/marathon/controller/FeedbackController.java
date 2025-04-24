package com.example.marathon.controller;

import com.example.marathon.util.AppContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.marathon.service.IFeedbackService;
import com.example.marathon.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 志愿者反馈信息表 前端控制器
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController {


    @Autowired
    private IFeedbackService iFeedbackService;

    @GetMapping(value = "/")
    public ResponseEntity<Page<Feedback>> list(@RequestParam(required = false) Integer current, @RequestParam(required = false) Integer pageSize) {
        if (current == null) {
            current = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        Page<Feedback> aPage = iFeedbackService.page(new Page<>(current, pageSize));
        return new ResponseEntity<>(aPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Feedback> getById(@PathVariable("id") String id) {
        return new ResponseEntity<>(iFeedbackService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@RequestBody Feedback params) {
        params.setVolunteerId(AppContext.getManageToken().getUserId());
        iFeedbackService.save(params);
        return new ResponseEntity<>("created successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        iFeedbackService.removeById(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody Feedback params) {
        iFeedbackService.updateById(params);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }
}
