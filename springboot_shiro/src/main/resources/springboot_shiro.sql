/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50560
Source Host           : localhost:3306
Source Database       : springboot_shiro

Target Server Type    : MYSQL
Target Server Version : 50560
File Encoding         : 65001

Date: 2018-11-02 15:21:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(32) NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(255) NOT NULL COMMENT '菜单名称',
  `parent_id` varchar(32) DEFAULT '0' COMMENT '父菜单ID',
  `url` varchar(255) DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `perms` varchar(255) DEFAULT '' COMMENT '权限标识',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1000', '用户管理', '0', '#', 'M', '', '', null, '', null);
INSERT INTO `sys_menu` VALUES ('1001', '用户新增', '1000', '/user/add', 'C', 'user:create', '', null, '', null);
INSERT INTO `sys_menu` VALUES ('1002', '用户查询', '1000', '/user/all', 'C', 'user:get', '', null, '', null);
INSERT INTO `sys_menu` VALUES ('1003', '用户修改', '1000', '/user/update', 'C', 'user:update', '', null, '', null);
INSERT INTO `sys_menu` VALUES ('1004', '用户删除', '1000', '/user/delete', 'C', 'user:delete', '', null, '', null);
INSERT INTO `sys_menu` VALUES ('1005', '分配角色', '1000', '/userRole/add', 'C', 'userRole:add', '', null, '', null);
INSERT INTO `sys_menu` VALUES ('1006', '移除分配角色', '1000', '/userRole/delete', 'C', 'userRole:delete', '', null, '', null);
INSERT INTO `sys_menu` VALUES ('2000', '角色管理', '0', '#', 'M', '', '', null, '', null);
INSERT INTO `sys_menu` VALUES ('2001', '角色新增', '2000', '/role/add', 'C', 'role:create', '', null, '', null);
INSERT INTO `sys_menu` VALUES ('2002', '角色查询', '2000', '/role/all', 'C', 'role:get', '', null, '', null);
INSERT INTO `sys_menu` VALUES ('2003', '角色删除', '2000', '/role/delete', 'C', 'role:delete', '', null, '', null);
INSERT INTO `sys_menu` VALUES ('2004', '角色修改', '2000', '/role/update', 'C', 'role:update', '', null, '', null);
INSERT INTO `sys_menu` VALUES ('3000', '菜单管理', '0', '#', 'M', '', '', null, '', null);
INSERT INTO `sys_menu` VALUES ('3001', '查询所有菜单', '3000', '/menu/all', 'C', 'menu:get', '', null, '', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `role_name` varchar(255) NOT NULL COMMENT '角色名称',
  `remark` varchar(255) NOT NULL COMMENT '角色权限字符串',
  `delete_flag` char(1) NOT NULL DEFAULT 'N' COMMENT '删除标志',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', 'admin', 'N', '', null, '', null);
INSERT INTO `sys_role` VALUES ('2', 'test', 'test', 'N', '', null, '', null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `menu_id` varchar(32) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1001');
INSERT INTO `sys_role_menu` VALUES ('1', '1002');
INSERT INTO `sys_role_menu` VALUES ('1', '1003');
INSERT INTO `sys_role_menu` VALUES ('1', '1004');
INSERT INTO `sys_role_menu` VALUES ('1', '1005');
INSERT INTO `sys_role_menu` VALUES ('1', '1006');
INSERT INTO `sys_role_menu` VALUES ('1', '2001');
INSERT INTO `sys_role_menu` VALUES ('1', '2002');
INSERT INTO `sys_role_menu` VALUES ('1', '2003');
INSERT INTO `sys_role_menu` VALUES ('1', '2004');
INSERT INTO `sys_role_menu` VALUES ('1', '3001');
INSERT INTO `sys_role_menu` VALUES ('2', '2002');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `username` varchar(255) NOT NULL COMMENT '用户昵称',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(20) DEFAULT 'MALE' COMMENT '用户性别（MALE男 FEMALE女 ）',
  `password` varchar(255) DEFAULT '' COMMENT '密码',
  `salt` varchar(255) DEFAULT '' COMMENT '盐加密',
  `delete_flag` char(1) DEFAULT 'N' COMMENT '删除标志',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('123456', 'admin', '123@qq.com', '18888888888', 'MALE', '2924bf023a606c036d5239129bd12860', '071d2ac3423f', 'N', '', '2018-08-28 14:35:57', '', '2018-08-28 14:35:36');
INSERT INTO `sys_user` VALUES ('654321', 'test', 'test@fdj.com', '12345645601', 'MALE', 'b995d53bdbe9f7eb8858631dcb7c5162', '0b418086e144', 'N', '', '2018-10-31 15:34:54', '', '2018-11-01 09:22:37');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('123456', '1');
INSERT INTO `sys_user_role` VALUES ('654321', '2');
