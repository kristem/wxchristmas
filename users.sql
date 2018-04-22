/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : christmas

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-04-22 21:43:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `score` int(11) DEFAULT '0',
  `imgurl` varchar(255) DEFAULT NULL,
  `counts` int(11) DEFAULT '40',
  `dates` varchar(255) DEFAULT '',
  `share` int(11) DEFAULT '0',
  `lastDate` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
