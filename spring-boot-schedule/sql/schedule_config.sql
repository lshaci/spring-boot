/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : schedule

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-07-28 15:39:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for schedule_config
-- ----------------------------
DROP TABLE IF EXISTS `schedule_config`;
CREATE TABLE `schedule_config` (
  `task_name` varchar(255) NOT NULL,
  `corn` varchar(255) DEFAULT NULL,
  `current` varchar(255) DEFAULT NULL,
  `next` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`task_name`),
  UNIQUE KEY `task_name_unique` (`task_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
