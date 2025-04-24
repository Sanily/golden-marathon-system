package com.example.marathon.service.impl;

import com.example.marathon.entity.Event;
import com.example.marathon.mapper.EventMapper;
import com.example.marathon.service.IEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 赛事基础信息表 服务实现类
 * </p>
 *
 * @author 
 * @since 2025-04-18
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements IEventService {

}
