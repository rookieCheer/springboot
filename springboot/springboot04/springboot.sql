/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-12-03 10:33:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_shiro_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_shiro_permission`;
CREATE TABLE `sys_shiro_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) DEFAULT '' COMMENT '权限code',
  `name` varchar(64) DEFAULT '' COMMENT '权限名称',
  `parent_code` varchar(64) DEFAULT '' COMMENT '父节点code',
  `parent_codes` varchar(64) DEFAULT '' COMMENT '节点顺序',
  `value` varchar(64) DEFAULT '' COMMENT '权限标识',
  `path` varchar(64) DEFAULT '' COMMENT '权限地址 url',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态     0 不可用    1 可用',
  `type` tinyint(1) DEFAULT '1' COMMENT '权限类型（1 菜单权限 0  按钮 ）',
  `create_code` varchar(16) DEFAULT '' COMMENT '创建人code',
  `update_code` varchar(16) DEFAULT '' COMMENT '修改人code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_shiro_permission
-- ----------------------------
INSERT INTO `sys_shiro_permission` VALUES ('1', '1', '查看用户list页面', '0', '0/1', 'user', '', '0', '1', '1', '1', '2018-11-19 11:14:02', '2018-11-19 11:14:06');
INSERT INTO `sys_shiro_permission` VALUES ('2', '2', '查看角色list', '0', '0/1', 'role', '', '1', '0', '1', '1', '2018-11-19 11:14:41', '2018-11-19 11:14:44');
INSERT INTO `sys_shiro_permission` VALUES ('3', '1543387385810', '3', '3', '3', '3', '3', '1', '1', '', '', '2018-11-28 14:43:06', '2018-11-28 14:43:06');

-- ----------------------------
-- Table structure for sys_shiro_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_shiro_role`;
CREATE TABLE `sys_shiro_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) DEFAULT '' COMMENT '标识',
  `name` varchar(16) DEFAULT '' COMMENT '角色名称',
  `value` varchar(16) DEFAULT '' COMMENT '角色值',
  `description` varchar(255) DEFAULT '' COMMENT '描述',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态  0冻结   1可用',
  `create_code` varchar(16) DEFAULT '' COMMENT '创建人code',
  `update_code` varchar(16) DEFAULT '' COMMENT '修改人code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_shiro_role
-- ----------------------------
INSERT INTO `sys_shiro_role` VALUES ('1', '1', '测试', 'test', '测试角色', '1', '1', '1', '2018-11-19 11:15:22', '2018-11-29 09:28:48');
INSERT INTO `sys_shiro_role` VALUES ('2', '2', 'ce', 'test1', '111', '0', '1', '1', '2018-11-19 11:15:40', '2018-11-28 17:37:02');
INSERT INTO `sys_shiro_role` VALUES ('5', '1543454961497', '4', '4', '', '1', '', '', '2018-11-29 09:29:21', '2018-11-29 09:29:21');
INSERT INTO `sys_shiro_role` VALUES ('6', '1543502512186', '3', '3', '', '1', '', '', '2018-11-29 22:41:52', '2018-11-29 22:41:52');

-- ----------------------------
-- Table structure for sys_shiro_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_shiro_role_permission`;
CREATE TABLE `sys_shiro_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(16) DEFAULT '' COMMENT '角色code',
  `permission_code` varchar(16) DEFAULT '' COMMENT '权限code',
  `create_code` varchar(16) DEFAULT '' COMMENT '创建人code',
  `update_code` varchar(16) DEFAULT '' COMMENT '修改人code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_shiro_role_permission
-- ----------------------------
INSERT INTO `sys_shiro_role_permission` VALUES ('3', '2', '1', '1', '1', '2018-07-31 13:54:25', '2018-07-31 13:54:28');
INSERT INTO `sys_shiro_role_permission` VALUES ('4', '1543454961497', '1', '1', '1', '2018-11-29 16:59:40', '2018-11-29 17:03:08');
INSERT INTO `sys_shiro_role_permission` VALUES ('8', '1543454961497', '2', '', '', '2018-11-29 18:09:49', '2018-11-29 18:09:49');
INSERT INTO `sys_shiro_role_permission` VALUES ('13', '2', '2', '', '', '2018-11-29 18:36:13', '2018-11-29 18:36:13');
INSERT INTO `sys_shiro_role_permission` VALUES ('14', '1', '1', '', '', '2018-11-29 18:39:03', '2018-11-29 18:39:03');
INSERT INTO `sys_shiro_role_permission` VALUES ('17', '1', '2', '', '', '2018-11-29 22:36:21', '2018-11-29 22:36:21');
INSERT INTO `sys_shiro_role_permission` VALUES ('18', '1', '1543387385810', '', '', '2018-11-29 22:38:37', '2018-11-29 22:38:37');
INSERT INTO `sys_shiro_role_permission` VALUES ('19', '2', '1543387385810', '', '', '2018-11-29 22:40:00', '2018-11-29 22:40:00');

-- ----------------------------
-- Table structure for sys_shiro_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_shiro_user`;
CREATE TABLE `sys_shiro_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) DEFAULT '' COMMENT '用户code 唯一标识',
  `name` varchar(16) DEFAULT '' COMMENT '用户姓名',
  `account` varchar(16) DEFAULT '' COMMENT '用户账号',
  `pwd` varchar(32) DEFAULT '' COMMENT '用户密码',
  `salt` varchar(16) DEFAULT '' COMMENT '盐',
  `status` tinyint(1) DEFAULT '2' COMMENT '账户状态  0 可用  1 待领取  2不可用',
  `last_login_ip` varchar(16) DEFAULT '' COMMENT '最后一次登录ip',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `create_code` varchar(16) DEFAULT '' COMMENT '创建人code',
  `update_code` varchar(16) DEFAULT '' COMMENT '修改人code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_shiro_user
-- ----------------------------
INSERT INTO `sys_shiro_user` VALUES ('1', '1', 'admin', 'admin', '123456', '1', '0', '127.0.0.1', '2018-11-19 13:37:15', '1', '1', '2018-11-19 11:00:21', '2018-11-27 17:37:39');
INSERT INTO `sys_shiro_user` VALUES ('2', '2', 'test', 'test', '123456', '1', '0', '127.0.0.1', '2018-11-19 13:40:42', '1', '1', '2018-11-19 11:00:43', '2018-11-27 17:25:56');
INSERT INTO `sys_shiro_user` VALUES ('3', '1543311775484', '4', '4', '123456', '1543311775484', '0', '', null, '', '', '2018-11-27 17:42:55', '2018-11-27 17:42:55');
INSERT INTO `sys_shiro_user` VALUES ('4', '1543371395369', '5', '5', '123456', '1543371395369', '0', '', null, '', '', '2018-11-28 10:16:35', '2018-11-28 10:16:35');
INSERT INTO `sys_shiro_user` VALUES ('5', '1543371401524', '6', '6', '123456', '1543371401524', '0', '', null, '', '', '2018-11-28 10:16:42', '2018-11-28 10:16:42');
INSERT INTO `sys_shiro_user` VALUES ('6', '1543371408731', '7', '7', '123456', '1543371408731', '0', '', null, '', '', '2018-11-28 10:16:49', '2018-11-28 10:16:49');
INSERT INTO `sys_shiro_user` VALUES ('7', '1543371414955', '8', '8', '123456', '1543371414955', '0', '', null, '', '', '2018-11-28 10:16:55', '2018-11-28 10:16:55');
INSERT INTO `sys_shiro_user` VALUES ('8', '1543371420581', '9', '9', '123456', '1543371420581', '0', '', null, '', '', '2018-11-28 10:17:01', '2018-11-28 10:17:01');
INSERT INTO `sys_shiro_user` VALUES ('9', '1543371433310', '10', '10', '123456', '1543371433310', '0', '', null, '', '', '2018-11-28 10:17:13', '2018-11-28 10:17:13');
INSERT INTO `sys_shiro_user` VALUES ('10', '1543371441097', '11', '11', '123456', '1543371441097', '0', '', null, '', '', '2018-11-28 10:17:21', '2018-11-28 10:17:21');
INSERT INTO `sys_shiro_user` VALUES ('11', '1543371459701', '12', '12', '123456', '1543371459701', '0', '', null, '', '', '2018-11-28 10:17:40', '2018-11-28 10:17:40');

-- ----------------------------
-- Table structure for sys_shiro_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_shiro_user_role`;
CREATE TABLE `sys_shiro_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(16) DEFAULT '' COMMENT '角色code',
  `user_code` varchar(16) DEFAULT '' COMMENT '用户code',
  `create_code` varchar(16) DEFAULT '' COMMENT '创建人code',
  `update_code` varchar(16) DEFAULT '' COMMENT '修改人code',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_shiro_user_role
-- ----------------------------
INSERT INTO `sys_shiro_user_role` VALUES ('11', '2', '2', '', '', '2018-11-27 17:25:56', '2018-11-27 17:25:56');
INSERT INTO `sys_shiro_user_role` VALUES ('12', '1', '1', '', '', '2018-11-27 17:37:39', '2018-11-27 17:37:39');
INSERT INTO `sys_shiro_user_role` VALUES ('13', '2', '1', '', '', '2018-11-27 17:37:39', '2018-11-27 17:37:39');
INSERT INTO `sys_shiro_user_role` VALUES ('14', '4', '1', '', '', '2018-11-27 17:37:39', '2018-11-27 17:37:39');
INSERT INTO `sys_shiro_user_role` VALUES ('15', '1', '1543311775484', '', '', '2018-11-27 17:42:55', '2018-11-27 17:42:55');
INSERT INTO `sys_shiro_user_role` VALUES ('16', '5', '1543311775484', '', '', '2018-11-27 17:42:55', '2018-11-27 17:42:55');
INSERT INTO `sys_shiro_user_role` VALUES ('17', '1', '1543371395369', '', '', '2018-11-28 10:16:35', '2018-11-28 10:16:35');
INSERT INTO `sys_shiro_user_role` VALUES ('18', '1', '1543371401524', '', '', '2018-11-28 10:16:42', '2018-11-28 10:16:42');
INSERT INTO `sys_shiro_user_role` VALUES ('19', '1', '1543371408731', '', '', '2018-11-28 10:16:49', '2018-11-28 10:16:49');
INSERT INTO `sys_shiro_user_role` VALUES ('20', '1', '1543371414955', '', '', '2018-11-28 10:16:55', '2018-11-28 10:16:55');
INSERT INTO `sys_shiro_user_role` VALUES ('21', '1', '1543371420581', '', '', '2018-11-28 10:17:01', '2018-11-28 10:17:01');
INSERT INTO `sys_shiro_user_role` VALUES ('22', '1', '1543371433310', '', '', '2018-11-28 10:17:13', '2018-11-28 10:17:13');
INSERT INTO `sys_shiro_user_role` VALUES ('23', '3', '1543371441097', '', '', '2018-11-28 10:17:21', '2018-11-28 10:17:21');
INSERT INTO `sys_shiro_user_role` VALUES ('24', '1', '1543371851840', '', '', '2018-11-28 10:24:12', '2018-11-28 10:24:12');
