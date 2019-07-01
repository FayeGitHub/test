/*
Navicat MySQL Data Transfer

Source Server         : mysql-127.0.0.1
Source Server Version : 80016
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-07-01 16:35:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(80) NOT NULL,
  `password` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `valid` int(11) DEFAULT NULL,
  `Email` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('ace', '123456', '1', null);
INSERT INTO `user` VALUES ('cff', '123456', '1', null);
