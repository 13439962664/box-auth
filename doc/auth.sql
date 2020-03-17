/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50521
Source Host           : 127.0.0.1:3306
Source Database       : box

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2020-02-29 21:19:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_permissions
-- ----------------------------
DROP TABLE IF EXISTS `auth_permissions`;
CREATE TABLE `auth_permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissions_code` varchar(50) DEFAULT NULL,
  `permissions_name` varchar(50) DEFAULT NULL,
  `del_` int(1) DEFAULT NULL,
  `version_` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `last_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_auth_permissions_code` (`permissions_code`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_permissions
-- ----------------------------
INSERT INTO `auth_permissions` VALUES ('1', 'index', '欢迎', '0', '0');
INSERT INTO `auth_permissions` VALUES ('2', 'auth_user_add', '添加用户', '0', '0');
INSERT INTO `auth_permissions` VALUES ('3', 'auth_user_upd', '修改用户', '0', '0');
INSERT INTO `auth_permissions` VALUES ('4', 'auth_user_del', '删除用户', '0', '0');
INSERT INTO `auth_permissions` VALUES ('5', 'auth_user_sel', '查询用户', '0', '0');
INSERT INTO `auth_permissions` VALUES ('6', 'auth_user_manage', '用户管理', '0', '0');
INSERT INTO `auth_permissions` VALUES ('7', 'auth_role_add', '添加角色', '0', '0');
INSERT INTO `auth_permissions` VALUES ('8', 'auth_role_upd', '修改角色', '0', '0');
INSERT INTO `auth_permissions` VALUES ('9', 'auth_role_del', '删除角色', '0', '0');
INSERT INTO `auth_permissions` VALUES ('10', 'auth_role_sel', '查询角色', '0', '0');
INSERT INTO `auth_permissions` VALUES ('11', 'auth_role_manage', '角色管理', '0', '0');
INSERT INTO `auth_permissions` VALUES ('12', 'auth_permissions_add', '添加权限', '0', '0');
INSERT INTO `auth_permissions` VALUES ('13', 'auth_permissions_upd', '修改权限', '0', '0');
INSERT INTO `auth_permissions` VALUES ('14', 'auth_permissions_del', '删除权限', '0', '0');
INSERT INTO `auth_permissions` VALUES ('15', 'auth_permissions_sel', '查询权限', '0', '0');
INSERT INTO `auth_permissions` VALUES ('16', 'auth_permissions_manage', '权限管理', '0', '0');

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(50) DEFAULT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `del_` int(1) DEFAULT NULL,
  `version_` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `last_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_auth_role_code` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('1', 'admin', '管理员', '0', '0');

-- ----------------------------
-- Table structure for auth_role_permissions
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_permissions`;
CREATE TABLE `auth_role_permissions` (
  `role_id` int(11) NOT NULL,
  `permissions_id` int(11) NOT NULL,
  UNIQUE KEY `i_auth_role_permissions` (`role_id`,`permissions_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_role_permissions
-- ----------------------------
INSERT INTO `auth_role_permissions` VALUES ('1', '1');
INSERT INTO `auth_role_permissions` VALUES ('1', '2');
INSERT INTO `auth_role_permissions` VALUES ('1', '3');
INSERT INTO `auth_role_permissions` VALUES ('1', '4');
INSERT INTO `auth_role_permissions` VALUES ('1', '5');
INSERT INTO `auth_role_permissions` VALUES ('1', '6');
INSERT INTO `auth_role_permissions` VALUES ('1', '7');
INSERT INTO `auth_role_permissions` VALUES ('1', '8');
INSERT INTO `auth_role_permissions` VALUES ('1', '9');
INSERT INTO `auth_role_permissions` VALUES ('1', '10');
INSERT INTO `auth_role_permissions` VALUES ('1', '11');
INSERT INTO `auth_role_permissions` VALUES ('1', '12');
INSERT INTO `auth_role_permissions` VALUES ('1', '13');
INSERT INTO `auth_role_permissions` VALUES ('1', '14');
INSERT INTO `auth_role_permissions` VALUES ('1', '15');
INSERT INTO `auth_role_permissions` VALUES ('1', '16');

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `del_` int(1) DEFAULT NULL,
  `version_` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` int(11) DEFAULT NULL,
  `last_time` datetime DEFAULT NULL,
  `last_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_auth_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES ('1', 'admin', '4a95737b032e98a50c056c41f2fa9ec6', '0', '0');

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  UNIQUE KEY `i_auth_user_role` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
INSERT INTO `auth_user_role` VALUES ('1', '1');
