package com.example.marathon.service.impl;

import com.example.marathon.entity.Feedback;
import com.example.marathon.mapper.FeedbackMapper;
import com.example.marathon.service.IFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 志愿者反馈信息表 服务实现类
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {

}
