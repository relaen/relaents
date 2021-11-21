/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50516
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50516
File Encoding         : 65001

Date: 2021-01-07 16:03:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_group`
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group
-- ----------------------------
INSERT INTO `t_group` VALUES ('1', '组1');
INSERT INTO `t_group` VALUES ('2', '组2');
INSERT INTO `t_group` VALUES ('3', '组3');
INSERT INTO `t_group` VALUES ('4', null);

-- ----------------------------
-- Table structure for `t_group_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_group_user`;
CREATE TABLE `t_group_user` (
  `group_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`group_user_id`),
  KEY `FK_Reference_3` (`user_id`),
  KEY `FK_Reference_4` (`group_id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`group_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group_user
-- ----------------------------
INSERT INTO `t_group_user` VALUES ('1', '1', '1');
INSERT INTO `t_group_user` VALUES ('2', '2', '1');
INSERT INTO `t_group_user` VALUES ('3', '2', '2');
INSERT INTO `t_group_user` VALUES ('4', '3', '1');
INSERT INTO `t_group_user` VALUES ('5', '1', '3');

-- ----------------------------
-- Table structure for `t_shop`
-- ----------------------------
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL,
  `shop_name` varchar(32) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`shop_id`),
  KEY `FK_Reference_6` (`owner_id`),
  KEY `FK_Reference_7` (`manager_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`manager_id`) REFERENCES `t_user_info` (`user_id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`owner_id`) REFERENCES `t_user_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_shop
-- ----------------------------
INSERT INTO `t_shop` VALUES ('1', '1', '2', 'relaen铺子', null);
INSERT INTO `t_shop` VALUES ('2', '1', '2', 'noomi铺子', null);
INSERT INTO `t_shop` VALUES ('3', '2', '3', 'field铺子', null);

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) DEFAULT NULL,
  `user_password` char(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'relaen', null);
INSERT INTO `t_user` VALUES ('2', 'noomi', null);
INSERT INTO `t_user` VALUES ('3', 'field', null);

-- ----------------------------
-- Table structure for `t_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `user_id` int(11) NOT NULL,
  `real_name` varchar(64) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sexy` char(1) DEFAULT NULL,
  `remarks` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('1', 'RELAEN', '1', 'M', null);
INSERT INTO `t_user_info` VALUES ('2', 'NOOMI', '1', 'M', null);
INSERT INTO `t_user_info` VALUES ('3', 'FIELD', '40', 'M', null);
