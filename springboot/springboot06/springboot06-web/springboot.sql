/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : springboot

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-12-10 09:45:40
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_shiro_permission
-- ----------------------------
INSERT INTO `sys_shiro_permission` VALUES ('4', '1544061189189', '权限页面跳转', '', '', '/000003/000001', '/000003/000001', '1', '1', '', '', '2018-12-06 09:53:09', '2018-12-06 09:53:09');
INSERT INTO `sys_shiro_permission` VALUES ('5', '1544147390031', '权限信息查看', '', '', '/000003/000002', '/000003/000002', '1', '0', '', '', '2018-12-07 09:49:50', '2018-12-07 09:49:50');
INSERT INTO `sys_shiro_permission` VALUES ('6', '1544147435815', '权限code查询', '', '', '/000003/000003', '/000003/000003', '1', '0', '', '', '2018-12-07 09:50:36', '2018-12-07 09:50:36');
INSERT INTO `sys_shiro_permission` VALUES ('7', '1544147496874', '权限保存/修改', '', '', '/000003/000004', '/000003/000004', '1', '0', '', '', '2018-12-07 09:51:37', '2018-12-07 09:51:37');
INSERT INTO `sys_shiro_permission` VALUES ('8', '1544147605967', '角色页面跳转', '', '', '/000002/000001', '/000002/000001', '1', '1', '', '', '2018-12-07 09:53:26', '2018-12-07 09:53:26');
INSERT INTO `sys_shiro_permission` VALUES ('9', '1544147629166', '角色查询', '', '', '/000002/000002', '/000002/000002', '1', '0', '', '', '2018-12-07 09:53:49', '2018-12-07 09:53:49');
INSERT INTO `sys_shiro_permission` VALUES ('10', '1544147670305', '角色code查询', '', '', '/000002/000003', '/000002/000003', '1', '0', '', '', '2018-12-07 09:54:30', '2018-12-07 09:54:30');
INSERT INTO `sys_shiro_permission` VALUES ('11', '1544147689309', '角色保存', '', '', '/000002/000004', '/000002/000004', '1', '0', '', '', '2018-12-07 09:54:49', '2018-12-07 09:54:49');
INSERT INTO `sys_shiro_permission` VALUES ('12', '1544147713769', '角色删除', '', '', '/000002/000005', '/000002/000005', '1', '0', '', '', '2018-12-07 09:55:14', '2018-12-07 09:55:14');
INSERT INTO `sys_shiro_permission` VALUES ('13', '1544147749790', '角色权限查看', '', '', '/000002/000006/{code}', '/000002/000006/{code}', '1', '0', '', '', '2018-12-07 09:55:50', '2018-12-07 09:55:50');
INSERT INTO `sys_shiro_permission` VALUES ('14', '1544147777567', '角色权限修改', '', '', '/000002/000007', '/000002/000007', '1', '0', '', '', '2018-12-07 09:56:18', '2018-12-07 09:56:18');
INSERT INTO `sys_shiro_permission` VALUES ('15', '1544147812697', '用户页面跳转', '', '', '/000000/000001', '/000000/000001', '1', '1', '', '', '2018-12-07 09:56:53', '2018-12-07 09:56:53');
INSERT INTO `sys_shiro_permission` VALUES ('16', '1544147834975', '用户查看', '', '', '/000000/000002', '/000000/000002', '1', '0', '', '', '2018-12-07 09:57:15', '2018-12-07 09:57:15');
INSERT INTO `sys_shiro_permission` VALUES ('17', '1544147854078', '用户保存', '', '', '/000000/000004', '/000000/000004', '1', '0', '', '', '2018-12-07 09:57:34', '2018-12-07 09:57:34');
INSERT INTO `sys_shiro_permission` VALUES ('18', '1544147888758', '用户code查询', '', '', '/000000/000003', '/000000/000003', '1', '0', '', '', '2018-12-07 09:58:09', '2018-12-07 09:58:09');
INSERT INTO `sys_shiro_permission` VALUES ('19', '1544147910447', '用户删除', '', '', '/000000/000005', '/000000/000005', '1', '0', '', '', '2018-12-07 09:58:30', '2018-12-07 09:58:30');

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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_shiro_role
-- ----------------------------
INSERT INTO `sys_shiro_role` VALUES ('7', '1544002679494', 'admin', 'admin', '', '1', '', '', '2018-12-05 17:37:59', '2018-12-07 10:36:21');
INSERT INTO `sys_shiro_role` VALUES ('8', '1544150863333', 'test', 'test', '', '1', '', '', '2018-12-07 10:47:43', '2018-12-07 10:47:43');

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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_shiro_role_permission
-- ----------------------------
INSERT INTO `sys_shiro_role_permission` VALUES ('21', '1544002679494', '1544061189189', '', '', '2018-12-07 09:59:55', '2018-12-07 09:59:55');
INSERT INTO `sys_shiro_role_permission` VALUES ('22', '1544002679494', '1544147390031', '', '', '2018-12-07 09:59:57', '2018-12-07 09:59:57');
INSERT INTO `sys_shiro_role_permission` VALUES ('23', '1544002679494', '1544147435815', '', '', '2018-12-07 10:00:00', '2018-12-07 10:00:00');
INSERT INTO `sys_shiro_role_permission` VALUES ('24', '1544002679494', '1544147496874', '', '', '2018-12-07 10:00:02', '2018-12-07 10:00:02');
INSERT INTO `sys_shiro_role_permission` VALUES ('25', '1544002679494', '1544147605967', '', '', '2018-12-07 10:00:04', '2018-12-07 10:00:04');
INSERT INTO `sys_shiro_role_permission` VALUES ('26', '1544002679494', '1544147629166', '', '', '2018-12-07 10:00:05', '2018-12-07 10:00:05');
INSERT INTO `sys_shiro_role_permission` VALUES ('27', '1544002679494', '1544147670305', '', '', '2018-12-07 10:00:06', '2018-12-07 10:00:06');
INSERT INTO `sys_shiro_role_permission` VALUES ('28', '1544002679494', '1544147689309', '', '', '2018-12-07 10:00:08', '2018-12-07 10:00:08');
INSERT INTO `sys_shiro_role_permission` VALUES ('29', '1544002679494', '1544147713769', '', '', '2018-12-07 10:00:10', '2018-12-07 10:00:10');
INSERT INTO `sys_shiro_role_permission` VALUES ('30', '1544002679494', '1544147749790', '', '', '2018-12-07 10:00:11', '2018-12-07 10:00:11');
INSERT INTO `sys_shiro_role_permission` VALUES ('31', '1544002679494', '1544147777567', '', '', '2018-12-07 10:00:12', '2018-12-07 10:00:12');
INSERT INTO `sys_shiro_role_permission` VALUES ('32', '1544002679494', '1544147812697', '', '', '2018-12-07 10:00:14', '2018-12-07 10:00:14');
INSERT INTO `sys_shiro_role_permission` VALUES ('33', '1544002679494', '1544147834975', '', '', '2018-12-07 10:00:15', '2018-12-07 10:00:15');
INSERT INTO `sys_shiro_role_permission` VALUES ('34', '1544002679494', '1544147854078', '', '', '2018-12-07 10:00:17', '2018-12-07 10:00:17');
INSERT INTO `sys_shiro_role_permission` VALUES ('35', '1544002679494', '1544147888758', '', '', '2018-12-07 10:00:19', '2018-12-07 10:00:19');
INSERT INTO `sys_shiro_role_permission` VALUES ('37', '1544002679494', '1544147910447', '', '', '2018-12-07 10:01:01', '2018-12-07 10:01:01');
INSERT INTO `sys_shiro_role_permission` VALUES ('38', '1544150863333', '1544147812697', '', '', '2018-12-07 10:47:50', '2018-12-07 10:47:50');
INSERT INTO `sys_shiro_role_permission` VALUES ('39', '1544150863333', '1544147834975', '', '', '2018-12-07 10:47:52', '2018-12-07 10:47:52');
INSERT INTO `sys_shiro_role_permission` VALUES ('40', '1544150863333', '1544147854078', '', '', '2018-12-07 10:47:54', '2018-12-07 10:47:54');
INSERT INTO `sys_shiro_role_permission` VALUES ('41', '1544150863333', '1544147888758', '', '', '2018-12-07 10:47:55', '2018-12-07 10:47:55');
INSERT INTO `sys_shiro_role_permission` VALUES ('42', '1544150863333', '1544147910447', '', '', '2018-12-07 10:47:58', '2018-12-07 10:47:58');

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_account` (`account`) USING BTREE COMMENT '账号唯一'
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_shiro_user
-- ----------------------------
INSERT INTO `sys_shiro_user` VALUES ('1', '1', 'admin', 'admin', '3980e4044675f6339248ee0c735c7d72', '123456', '0', '127.0.0.1', '2018-12-03 14:52:52', '1', '1', '2018-11-19 11:00:21', '2018-12-07 10:48:47');
INSERT INTO `sys_shiro_user` VALUES ('2', '2', 'test', 'test', '3980e4044675f6339248ee0c735c7d72', '123456', '0', '127.0.0.1', '2018-11-19 13:40:42', '1', '1', '2018-11-19 11:00:43', '2018-12-07 10:48:55');

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_shiro_user_role
-- ----------------------------
INSERT INTO `sys_shiro_user_role` VALUES ('26', '1544002679494', '1', '', '', '2018-12-07 10:48:47', '2018-12-07 10:48:47');
INSERT INTO `sys_shiro_user_role` VALUES ('27', '1544150863333', '1', '', '', '2018-12-07 10:48:47', '2018-12-07 10:48:47');
INSERT INTO `sys_shiro_user_role` VALUES ('28', '1544150863333', '2', '', '', '2018-12-07 10:48:55', '2018-12-07 10:48:55');
