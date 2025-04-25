/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 127.0.0.1:3306
 Source Schema         : marathon

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 25/04/2025 09:04:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for event
-- ----------------------------
DROP TABLE IF EXISTS `event`;
CREATE TABLE `event` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '赛事主键，自增',
  `name` varchar(100) NOT NULL COMMENT '赛事名称',
  `event_date` date NOT NULL COMMENT '赛事日期',
  `start_location` varchar(255) DEFAULT NULL COMMENT '起点位置',
  `end_location` varchar(255) DEFAULT NULL COMMENT '终点位置',
  `route_map_url` varchar(255) DEFAULT NULL COMMENT '路线地图图片URL或路径（静态图）',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='赛事基础信息表';

-- ----------------------------
-- Table structure for event_news
-- ----------------------------
DROP TABLE IF EXISTS `event_news`;
CREATE TABLE `event_news` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '动态主键，自增',
  `event_id` int(10) unsigned NOT NULL COMMENT '所属赛事ID，外键',
  `content` text COMMENT '动态内容，如天气、进程等',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '动态发布时间',
  PRIMARY KEY (`id`),
  KEY `event_id` (`event_id`),
  CONSTRAINT `event_news_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='赛事动态信息表';

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '反馈主键，自增',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `volunteer_id` bigint(20) unsigned NOT NULL COMMENT '反馈者用户ID，外键',
  `content` text NOT NULL COMMENT '反馈内容',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '反馈时间',
  `status` varchar(255) NOT NULL DEFAULT '未处理' COMMENT '反馈处理状态',
  `reply_content` varchar(255) DEFAULT NULL COMMENT '回复内容',
  PRIMARY KEY (`id`),
  KEY `volunteer_id` (`volunteer_id`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`volunteer_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='志愿者反馈信息表';

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '通知主键，自增',
  `title` varchar(100) NOT NULL COMMENT '通知标题',
  `content` text NOT NULL COMMENT '通知内容',
  `type` enum('SYSTEM','ANNOUNCEMENT') NOT NULL DEFAULT 'SYSTEM' COMMENT '类型：SYSTEM-系统通知，ANNOUNCEMENT-管理员公告',
  `created_by` bigint(20) unsigned DEFAULT NULL COMMENT '发布者用户ID，NULL 表示系统自动',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知与公告表';

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '任务主键，自增',
  `name` varchar(100) NOT NULL COMMENT '任务名称',
  `description` text COMMENT '任务描述',
  `category` varchar(255) DEFAULT NULL COMMENT '任务类别',
  `start_time` datetime DEFAULT NULL COMMENT '任务开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '任务结束时间',
  `location` varchar(255) DEFAULT NULL COMMENT '任务地点',
  `required_number` int(10) unsigned DEFAULT '1' COMMENT '所需志愿者人数',
  `confirmed_number` int(10) unsigned DEFAULT '0' COMMENT '已确认志愿者人数',
  `status` varchar(255) DEFAULT '未分配' COMMENT '分配状态',
  `created_by` bigint(20) unsigned DEFAULT NULL COMMENT '发布任务的管理员用户ID',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  PRIMARY KEY (`id`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='任务发布表';

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户主键，自增',
  `username` varchar(50) NOT NULL COMMENT '登录用户名，唯一',
  `password` varchar(255) NOT NULL COMMENT '登录密码（加密存储）',
  `role` varchar(255) DEFAULT 'VOLUNTEER' COMMENT '用户角色：ADMIN-管理员，VOLUNTEER-志愿者',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `age` varchar(255) DEFAULT NULL COMMENT '年龄',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别：男，女',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `id_card` varchar(30) DEFAULT NULL COMMENT '身份证号',
  `emergency_contact` varchar(20) DEFAULT NULL COMMENT '紧急联系人电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='系统用户表（含管理员和志愿者）';

-- ----------------------------
-- Table structure for volunteer_task
-- ----------------------------
DROP TABLE IF EXISTS `volunteer_task`;
CREATE TABLE `volunteer_task` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录主键，自增',
  `task_id` bigint(20) unsigned NOT NULL COMMENT '任务ID，外键',
  `volunteer_id` bigint(20) unsigned NOT NULL COMMENT '志愿者用户ID，外键',
  `assign_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '管理员分配时间',
  `confirmed` int(3) DEFAULT '0' COMMENT '志愿者确认  0待确认，1.已接受，2已拒绝',
  `confirmed_time` datetime DEFAULT NULL COMMENT '确认时间',
  `completed` tinyint(1) DEFAULT '0' COMMENT '志愿者是否已完成',
  `completed_time` datetime DEFAULT NULL COMMENT '完成时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_task_volunteer` (`task_id`,`volunteer_id`),
  KEY `volunteer_id` (`volunteer_id`),
  CONSTRAINT `volunteer_task_ibfk_1` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`) ON DELETE CASCADE,
  CONSTRAINT `volunteer_task_ibfk_2` FOREIGN KEY (`volunteer_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='志愿者任务分配表';

SET FOREIGN_KEY_CHECKS = 1;
