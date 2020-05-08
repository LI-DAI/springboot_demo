/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : springboot_shiro

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 29/04/2020 13:53:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dashboard
-- ----------------------------
DROP TABLE IF EXISTS `dashboard`;
CREATE TABLE `dashboard`  (
  `job_id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `method_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务方法',
  `method_params` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '方法参数',
  `cron_expression` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'cron执行表达式',
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务状态（0正常 1暂停）',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'dashboard' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '父菜单ID',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '请求地址',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限标识',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1000', '用户管理', '0', '#', 'M', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('1001', '用户新增', '1000', '/user/add', 'C', 'user:create', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('1002', '用户查询', '1000', '/user/all', 'C', 'user:get', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('1003', '用户修改', '1000', '/user/update', 'C', 'user:update', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('1004', '用户删除', '1000', '/user/delete', 'C', 'user:delete', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('1005', '分配角色', '1000', '/userRole/add', 'C', 'userRole:add', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('1006', '移除分配角色', '1000', '/userRole/delete', 'C', 'userRole:delete', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('2000', '角色管理', '0', '#', 'M', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('2001', '角色新增', '2000', '/role/add', 'C', 'role:create', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('2002', '角色查询', '2000', '/role/all', 'C', 'role:get', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('2003', '角色删除', '2000', '/role/delete', 'C', 'role:delete', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('2004', '角色修改', '2000', '/role/update', 'C', 'role:update', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('3000', '菜单管理', '0', '#', 'M', '', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('3001', '查询所有菜单', '3000', '/menu/all', 'C', 'menu:get', '', NULL, '', NULL);
INSERT INTO `sys_menu` VALUES ('9999', 'hello', '0', '/user/no', '', 'user::no', '', NULL, '', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `delete_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'N' COMMENT '删除标志',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', 'admin', 'N', '', NULL, '', NULL);
INSERT INTO `sys_role` VALUES ('2', 'test', 'test', 'N', '', NULL, '', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_role_menu` VALUES ('1', '9999');
INSERT INTO `sys_role_menu` VALUES ('2', '2002');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'MALE' COMMENT '用户性别（MALE男 FEMALE女 ）',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '盐加密',
  `delete_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '删除标志',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `label` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0e7c76279b65449488f361e7d48dfbff', 'testBigData', '123@qq.com', '13131313555', '0', '123456', 'b5f92dc9b506', NULL, 'admin', '2019-05-02 23:04:46', 'amdin', '2019-05-02 23:04:46', '');
INSERT INTO `sys_user` VALUES ('123456', 'admin', '123@qq.com', '18888888888', '0', '2924bf023a606c036d5239129bd12860', '071d2ac3423f', '0', '', '2018-08-28 14:35:57', '', '2018-08-28 14:35:36', NULL);
INSERT INTO `sys_user` VALUES ('654321', 'test', 'test@fdj.com', '12345645601', '0', 'b995d53bdbe9f7eb8858631dcb7c5162', '0b418086e144', '0', '', '2018-10-31 15:34:54', '', '2018-11-01 09:22:37', NULL);
INSERT INTO `sys_user` VALUES ('cfa849ebd748448eadf7c8e796aa2cf7', 'ttt', 'test@fdj.com', '12345645601', '0', 'daf2c11f419a9c27690d7fcb6f05d73c', '7f629b03af1c', '0', '', '2019-01-11 20:14:30', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('123456', '1');
INSERT INTO `sys_user_role` VALUES ('654321', '2');

SET FOREIGN_KEY_CHECKS = 1;
