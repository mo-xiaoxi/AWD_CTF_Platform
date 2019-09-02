/*
Navicat MySQL Data Transfer

Source Server         : 本地MySQL
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : chapter2

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2018-05-02 21:51:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for profile
-- ----------------------------
DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `birthday` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `province` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nation` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `politics_status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IDnumber` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `school` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `grade` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `postcode` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `mail` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `father_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `father_job` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `father_phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mother_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mother_job` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `mother_phone` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `hobby` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of profile
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `salt` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `head_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `checkcode` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `time` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `first` int(11) unsigned zerofill DEFAULT NULL,
  `pdd` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=336 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user   buptctfadmin/admin123
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'buptctfadmin', '556E04CCEFB0021A3242E30CF2346AB0', '@bupt.edu.cn', 'FB4D9BA04997C752656A06', null, null, null, null, '00000000002', 'admin123');
