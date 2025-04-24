package com.example.marathon.util;



import cn.hutool.json.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.marathon.bean.vo.TokenBean;
import com.example.marathon.exeption.BusinessException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class AppContext implements ServletContextAware, ApplicationContextAware {



	private static final ThreadLocal<TokenBean> TOKEN_BEAN_THREAD_LOCAL = new ThreadLocal<>();

	private static ServletContext servletContext;
	
	private static ApplicationContext applicationContext;

	private AppContext() {}

	public static String getAppRealPath(String path) {
		return servletContext.getRealPath(path);
	}
	
	public static HttpServletRequest getRequest(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}

	public static HttpServletResponse getResponse(){
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getResponse();
		return response;
	}

	/**
	 * 获取上下文路径
	 * @return
	 */
	public static String getContextPath(){
		return servletContext.getContextPath();
	}
	
	/**
	 * 获取上下文路径
	 * @return
	 */
	public static ServletContext getContext(){
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		AppContext.servletContext = servletContext;
	}


	@SneakyThrows
	public static void removeThreadLocal() throws BusinessException {

		TOKEN_BEAN_THREAD_LOCAL.remove();
	}


	public static TokenBean getManageToken() throws BusinessException {

		TokenBean tokenBean = TOKEN_BEAN_THREAD_LOCAL.get();
		if (Objects.isNull(tokenBean)) {
			String authorization = getRequest().getHeader("Authorization");
			if (StringUtils.isBlank(authorization)) {
				throw new BusinessException("未登录", "未登录", 401);
			}
			String[] token = authorization.split(" ");
			RedisTemplate redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
			ValueOperations<String, TokenBean> ops = redisTemplate.opsForValue();
			 tokenBean = ops.get(token[1]);
			if (tokenBean == null) {
				throw new BusinessException("未登录", "未登录", 401);
			}
			TOKEN_BEAN_THREAD_LOCAL.set(tokenBean);
			redisTemplate.expire(token[1], 12, TimeUnit.HOURS);
		}
        return tokenBean;
	}

	/**
	  * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	  */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
	        throws BeansException {
		AppContext.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	  * 获取对象 这里重写了bean方法，起主要作用
	  * @throws BeansException
	  */
	public static Object getBean(String beanName) throws BeansException {
		return applicationContext.getBean(beanName);
	}
}
