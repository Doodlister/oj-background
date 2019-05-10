/*
 Navicat Premium Data Transfer

 Source Server         : Doodux
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 192.168.11.2:3306
 Source Schema         : onlinejudge

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 10/05/2019 22:42:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for contest
-- ----------------------------
DROP TABLE IF EXISTS `contest`;
CREATE TABLE `contest`  (
  `id` int(11) NOT NULL COMMENT '编号',
  `allowed_ip_ranges` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'ip白名单',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '竞赛描述',
  `end_time` datetime(0) DEFAULT NULL COMMENT '竞赛结束时间',
  `last_update_time` datetime(0) DEFAULT NULL COMMENT '竞赛持续时间',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '进入密码',
  `real_time_rank` bit(1) DEFAULT NULL,
  `rule_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `start_time` datetime(0) DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '竞赛题目',
  `visible` bit(1) DEFAULT NULL COMMENT '可见标识',
  `create_by_user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKse7ewwofe8fxaaneu2rssnl11`(`create_by_user_id`) USING BTREE,
  CONSTRAINT `FKse7ewwofe8fxaaneu2rssnl11` FOREIGN KEY (`create_by_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for contest_notice
-- ----------------------------
DROP TABLE IF EXISTS `contest_notice`;
CREATE TABLE `contest_notice`  (
  `id` int(11) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `create_by_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKi2ayl8x7p3q1dttuicex7aajw`(`create_by_user_id`) USING BTREE,
  CONSTRAINT `FKi2ayl8x7p3q1dttuicex7aajw` FOREIGN KEY (`create_by_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for contest_rank
-- ----------------------------
DROP TABLE IF EXISTS `contest_rank`;
CREATE TABLE `contest_rank`  (
  `id` int(11) NOT NULL,
  `accepted_number` int(11) DEFAULT NULL,
  `contest_id` int(11) DEFAULT NULL,
  `submission_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `submission_number` int(11) DEFAULT NULL,
  `total_time` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK6b1k0m3flj5wmd6qb0hewjtwp`(`user_id`) USING BTREE,
  INDEX `FKfapqldexe2c91oilcixo0i47w`(`contest_id`) USING BTREE,
  CONSTRAINT `FK6b1k0m3flj5wmd6qb0hewjtwp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKfapqldexe2c91oilcixo0i47w` FOREIGN KEY (`contest_id`) REFERENCES `contest` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hibernate_sequences
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequences`;
CREATE TABLE `hibernate_sequences`  (
  `sequence_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `next_val` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequences
-- ----------------------------
INSERT INTO `hibernate_sequences` VALUES ('default', 290);

-- ----------------------------
-- Table structure for judge_server
-- ----------------------------
DROP TABLE IF EXISTS `judge_server`;
CREATE TABLE `judge_server`  (
  `id` int(11) NOT NULL,
  `cpu_core` int(11) DEFAULT NULL,
  `cpu_usage` double DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `hostname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_disabled` bit(1) DEFAULT NULL,
  `judger_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `last_heartbeat` datetime(0) DEFAULT NULL,
  `memory_usage` double DEFAULT NULL,
  `service_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `task_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL COMMENT '通知编号',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通知内容',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `last_update_time` datetime(0) DEFAULT NULL COMMENT '上次更新时间',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '通知内容',
  `visible` bit(1) DEFAULT NULL COMMENT '可见标识',
  `create_by_user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKe3hnyn8f097h6dki5lu1rfr3m`(`create_by_user_id`) USING BTREE,
  CONSTRAINT `FKe3hnyn8f097h6dki5lu1rfr3m` FOREIGN KEY (`create_by_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '这是一个很认真写的内容', '2019-02-13 22:17:23', NULL, '猪年快乐', NULL, 130);
INSERT INTO `notice` VALUES (132, '1', '2019-02-04 04:13:09', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (133, '1', '2019-02-04 04:13:19', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (134, '1', '2019-02-04 04:13:20', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (135, '1', '2019-02-04 04:13:20', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (136, '1', '2019-02-04 04:13:20', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (137, '1', '2019-02-04 04:13:20', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (138, '1', '2019-02-04 04:13:20', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (139, '1', '2019-02-04 04:13:21', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (140, '1', '2019-02-04 04:13:21', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (141, '1', '2019-02-04 04:13:21', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (142, '1', '2019-02-04 04:13:21', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (143, '1', '2019-02-04 04:13:21', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (144, '1', '2019-02-04 04:13:22', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (145, '1', '2019-02-04 04:13:22', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (146, '1', '2019-02-04 04:13:22', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (147, '1', '2019-02-04 04:13:22', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (148, '1', '2019-02-04 04:13:22', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (149, '1', '2019-02-04 04:13:23', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (150, '1', '2019-02-04 04:13:23', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (151, '1', '2019-02-04 04:13:23', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (152, '1', '2019-02-04 04:13:23', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (153, '1', '2019-02-04 04:13:23', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (154, '1', '2019-02-04 04:13:23', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (155, '1', '2019-02-04 04:13:24', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (156, '1', '2019-02-04 04:13:24', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (157, '1', '2019-02-04 04:13:24', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (158, '1', '2019-02-04 04:13:24', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (159, '1', '2019-02-04 04:13:24', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (160, '1', '2019-02-04 04:13:25', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (161, '1', '2019-02-04 04:13:25', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (162, '1', '2019-02-04 04:13:25', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (163, '1', '2019-02-04 04:13:25', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (164, '1', '2019-02-04 04:13:26', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (165, '1', '2019-02-04 04:13:26', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (166, '1', '2019-02-04 04:13:26', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (167, '1', '2019-02-04 04:13:26', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (168, '1', '2019-02-04 04:13:26', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (169, '1', '2019-02-04 04:13:27', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (170, '1', '2019-02-04 04:13:27', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (171, '1', '2019-02-04 04:13:27', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (172, '1', '2019-02-04 04:13:27', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (173, '1', '2019-02-04 04:13:27', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (174, '1', '2019-02-04 04:13:28', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (175, '1', '2019-02-04 04:13:28', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (176, '1', '2019-02-04 04:13:28', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (177, '1', '2019-02-04 04:13:28', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (178, '1', '2019-02-04 04:13:28', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (179, '1', '2019-02-04 04:13:29', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (180, '1', '2019-02-04 04:13:29', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (181, '1', '2019-02-04 04:13:29', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (182, '1', '2019-02-04 04:13:29', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (183, '1', '2019-02-04 04:13:30', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (184, '1', '2019-02-04 04:13:30', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (185, '1', '2019-02-04 04:13:30', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (186, '1', '2019-02-04 04:13:30', NULL, '猪年快乐1', b'0', NULL);
INSERT INTO `notice` VALUES (187, '1', '2019-02-04 04:13:30', NULL, '猪年快乐1', b'0', NULL);

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `id` int(11) NOT NULL COMMENT '问题编号',
  `accepted_number` bigint(20) DEFAULT NULL COMMENT '提交计数',
  `contest_id` int(11) DEFAULT NULL COMMENT '竞赛编号',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '问题描述',
  `difficulty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '问题难度',
  `hint` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '问题提示',
  `input_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '输入描述',
  `is_public` bit(1) DEFAULT NULL COMMENT '公开标识',
  `languages` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '题目允许语言',
  `last_update_time` datetime(0) DEFAULT NULL COMMENT '上次更新时间',
  `memory_limit` int(11) DEFAULT NULL COMMENT '内存限制',
  `output_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '输出 限制',
  `rule_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '规则类型',
  `samples` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分数',
  `source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `spj` bit(1) DEFAULT NULL,
  `spj_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `spj_compile_ok` bit(1) DEFAULT NULL,
  `spj_language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `spj_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `statistic_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '\r\n         * 0 ：AC	Accepted	通过\r\n         * 1 :WA	Wrong Answer	答案错误\r\n         * 2 :TLE	Time Limit Exceed	超时\r\n         * 3 :OLE	Output Limit Exceed	超过输出限制\r\n         * 4 :MLE	Memory Limit Exceed	超内存\r\n         * 5 :RE	Runtime Error	运行时错误\r\n         * 6 :PE	Presentation Error	格式错误\r\n         * 7 :CE	Compile Error	无法编译\r\n         ',
  `submission_number` bigint(20) DEFAULT NULL COMMENT '提交数目',
  `template` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '题目模板',
  `test_case_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '测试用例编号',
  `test_case_score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '测试用例得分',
  `time_limit` int(11) DEFAULT NULL COMMENT '时间限制',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `total_score` int(11) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  `create_by_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKs1wi5din805l5a21o9psy4het`(`create_by_user_id`) USING BTREE,
  CONSTRAINT `FKs1wi5din805l5a21o9psy4het` FOREIGN KEY (`create_by_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES (46, 1500, 0, '2019-01-29 08:07:28', '请计算两个整数的和并输出结果。\r\n\r\n注意不要有不必要的输出，比如\"请输入 a 和 b 的值: \"，示例代码见隐藏部分。', '简单', '<p>一些提示</p>', '两个用空格分开的整数.', b'1', 'string', NULL, 0, '两数之和', 'string', '11 11', '经典题目', b'1', 'string', b'1', 'string', 'string', 'string', 12314, 'string', 'string', 'string', 0, 'A + B Problem', 0, b'1', 120);
INSERT INTO `problem` VALUES (47, 1300, 0, '2019-01-29 08:07:28', 'string', '困难', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 1234, 'string', 'string', 'string', 0, 'TESTProleam', 0, b'1', 120);
INSERT INTO `problem` VALUES (49, 0, 0, '2019-01-29 08:07:28', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.7528471953727744', 0, b'1', 120);
INSERT INTO `problem` VALUES (50, 0, 0, '2019-01-29 08:07:29', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.6421521013995186', 0, b'1', 120);
INSERT INTO `problem` VALUES (51, 0, 0, '2019-01-29 08:07:29', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.9522189516129149', 0, b'1', 120);
INSERT INTO `problem` VALUES (52, 0, 0, '2019-01-29 08:07:29', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.8346384845996634', 0, b'1', 120);
INSERT INTO `problem` VALUES (53, 0, 0, '2019-01-29 08:07:29', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.31653559889321736', 0, b'1', 120);
INSERT INTO `problem` VALUES (54, 0, 0, '2019-01-29 08:07:29', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.07876257140074165', 0, b'1', 120);
INSERT INTO `problem` VALUES (55, 0, 0, '2019-01-29 08:07:30', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.44420609105770115', 0, b'1', 120);
INSERT INTO `problem` VALUES (56, 0, 0, '2019-01-29 08:07:30', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.9847427718199256', 0, b'1', 120);
INSERT INTO `problem` VALUES (57, 0, 0, '2019-01-29 08:07:30', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.5910956166601699', 0, b'1', 120);
INSERT INTO `problem` VALUES (58, 0, 0, '2019-01-29 08:07:30', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.0012497985747175278', 0, b'1', 120);
INSERT INTO `problem` VALUES (59, 0, 0, '2019-01-29 08:07:30', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.23296217362672292', 0, b'1', 120);
INSERT INTO `problem` VALUES (60, 0, 0, '2019-01-29 08:07:30', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.16106150314310705', 0, b'1', 120);
INSERT INTO `problem` VALUES (61, 0, 0, '2019-01-29 08:07:31', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.10642102556901148', 0, b'1', 120);
INSERT INTO `problem` VALUES (62, 0, 0, '2019-01-29 08:07:31', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.048920649149381226', 0, b'1', 120);
INSERT INTO `problem` VALUES (63, 0, 0, '2019-01-29 08:07:31', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.9253401997735167', 0, b'1', 120);
INSERT INTO `problem` VALUES (64, 0, 0, '2019-01-29 08:07:31', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.4799390821530848', 0, b'1', 120);
INSERT INTO `problem` VALUES (65, 0, 0, '2019-01-29 08:07:31', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.6236748421785188', 0, b'1', 120);
INSERT INTO `problem` VALUES (66, 0, 0, '2019-01-29 08:07:32', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.6785569951669844', 0, b'1', 120);
INSERT INTO `problem` VALUES (67, 0, 0, '2019-01-29 08:07:32', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.5217604800330107', 0, b'1', 120);
INSERT INTO `problem` VALUES (68, 0, 0, '2019-01-29 08:07:32', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.5731314453977453', 0, b'1', 120);
INSERT INTO `problem` VALUES (69, 0, 0, '2019-01-29 08:07:32', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.3003758176233394', 0, b'1', 120);
INSERT INTO `problem` VALUES (70, 0, 0, '2019-01-29 08:07:32', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.7824847826571062', 0, b'1', 120);
INSERT INTO `problem` VALUES (71, 0, 0, '2019-01-29 08:07:33', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.011296491149157744', 0, b'1', 120);
INSERT INTO `problem` VALUES (72, 0, 0, '2019-01-29 08:07:33', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.7090281385081151', 0, b'1', 120);
INSERT INTO `problem` VALUES (73, 0, 0, '2019-01-29 08:07:33', 'string', 'string', 'string', 'string', b'1', 'string', NULL, 0, 'string', 'string', 'string', 'string', b'1', 'string', b'1', 'string', 'string', 'string', 0, 'string', 'string', 'string', 0, '0.5112512498267472', 0, b'1', 120);

-- ----------------------------
-- Table structure for problem_tag
-- ----------------------------
DROP TABLE IF EXISTS `problem_tag`;
CREATE TABLE `problem_tag`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for problem_tags
-- ----------------------------
DROP TABLE IF EXISTS `problem_tags`;
CREATE TABLE `problem_tags`  (
  `tag_id` int(11) NOT NULL,
  `problem_id` int(11) NOT NULL,
  INDEX `FKiss624kebh6xf9q88ifretjy7`(`problem_id`) USING BTREE,
  INDEX `FKk3uwqoujagpb66nslvg592nka`(`tag_id`) USING BTREE,
  CONSTRAINT `FKiss624kebh6xf9q88ifretjy7` FOREIGN KEY (`problem_id`) REFERENCES `problem` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKk3uwqoujagpb66nslvg592nka` FOREIGN KEY (`tag_id`) REFERENCES `problem_tag` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for submission
-- ----------------------------
DROP TABLE IF EXISTS `submission`;
CREATE TABLE `submission`  (
  `id` int(255) NOT NULL COMMENT '提交编号',
  `code` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '提交代码',
  `contest_id` int(11) DEFAULT NULL COMMENT '竞赛编号',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提价信息',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'ip地址',
  `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '提交语言',
  `result` int(11) DEFAULT NULL COMMENT '判题结果',
  `shared` bit(1) DEFAULT NULL,
  `statistic_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '统计信息',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `submit_by_user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `submit_problem_id` int(11) DEFAULT NULL COMMENT '题目编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKbrd9691e8quhl3xhk3bbaooqv`(`submit_by_user_id`) USING BTREE,
  INDEX `FKsxj2q53an25d8thuah2aquhr0`(`submit_problem_id`) USING BTREE,
  CONSTRAINT `FKbrd9691e8quhl3xhk3bbaooqv` FOREIGN KEY (`submit_by_user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKsxj2q53an25d8thuah2aquhr0` FOREIGN KEY (`submit_problem_id`) REFERENCES `problem` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of submission
-- ----------------------------
INSERT INTO `submission` VALUES (267, 'asdfasdfasdfasdfasdf', NULL, '2019-04-25 07:32:28', NULL, '127.0.0.1', 'C', 0, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (268, 'ASDFASD ', NULL, '2019-04-25 07:59:41', NULL, '127.0.0.1', 'Java', 0, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (269, '阿斯顿发射点', NULL, '2019-04-25 08:00:35', NULL, '127.0.0.1', 'Java', 0, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (270, '阿斯顿发生', NULL, '2019-04-25 08:01:47', NULL, '127.0.0.1', 'C', 0, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (271, '阿斯顿发射点', NULL, '2019-04-25 08:03:12', NULL, '127.0.0.1', 'C', 0, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (272, '阿斯蒂芬撒旦', NULL, '2019-04-25 08:03:32', NULL, '127.0.0.1', 'C', 0, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (273, '阿斯顿发生', NULL, '2019-04-25 08:04:28', NULL, '127.0.0.1', 'C', 0, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (274, '是teste撒打发', NULL, '2019-04-25 08:20:25', NULL, '127.0.0.1', 'C', 0, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (275, 'a啊啊啊啊啊啊啊啊啊啊', NULL, '2019-04-25 08:32:31', NULL, '127.0.0.1', 'C', 0, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (276, '啊手动阀', NULL, '2019-04-25 08:35:53', NULL, '127.0.0.1', 'C', 0, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (277, '123123123', NULL, '2019-04-25 13:18:25', NULL, '127.0.0.1', 'C', 0, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (278, 'vddsfa dsf sd大飒飒的撒打发撒打发撒打发啊手动阀撒打发撒旦撒旦f', NULL, '2019-04-25 13:26:06', NULL, '127.0.0.1', 'C', 1, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (279, '阿斯蒂芬撒旦', NULL, '2019-04-25 13:28:23', NULL, '127.0.0.1', 'C', 1, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (280, '士大夫撒旦撒打发阿斯顿', NULL, '2019-04-25 13:28:34', NULL, '127.0.0.1', 'C', 1, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (281, '士大夫撒旦撒打发阿斯顿', NULL, '2019-04-25 13:28:38', NULL, '127.0.0.1', 'C', 1, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (282, '士大夫撒旦撒打发阿斯顿', NULL, '2019-04-25 13:28:44', NULL, '127.0.0.1', 'C', 1, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (283, '士大夫撒旦撒打发阿斯顿', NULL, '2019-04-25 13:28:50', NULL, '127.0.0.1', 'C', 1, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (284, '士大夫撒旦撒打发阿斯顿', NULL, '2019-04-25 13:29:01', NULL, '127.0.0.1', 'C', 1, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (285, '士大夫撒旦撒打发阿斯顿', NULL, '2019-04-25 13:29:27', NULL, '127.0.0.1', 'C', 1, NULL, NULL, 'test@test', 263, 46);
INSERT INTO `submission` VALUES (287, '#include<stdio.h>\nint main(){\n  return 0;\n}', NULL, '2019-04-25 13:35:22', NULL, '127.0.0.1', 'C', 1, NULL, NULL, '论文演示', 286, 46);
INSERT INTO `submission` VALUES (289, '的说法萨芬撒旦撒反对阿萨撒打发\n', NULL, '2019-04-26 06:04:13', NULL, '127.0.0.1', 'C', 1, NULL, NULL, '演示账号', 288, 46);
INSERT INTO `submission` VALUES (290, '撒旦发生阀撒是否撒打发', NULL, '2019-04-26 06:04:25', NULL, '127.0.0.1', 'Java', 0, NULL, NULL, '演示账号', 288, 46);

-- ----------------------------
-- Table structure for test_case
-- ----------------------------
DROP TABLE IF EXISTS `test_case`;
CREATE TABLE `test_case`  (
  `id` int(11) NOT NULL COMMENT '用例编号',
  `input` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用例输入',
  `output` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用例输出',
  `problem_id` int(11) DEFAULT NULL COMMENT '问题编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKivml84fkfpdwn2mwmt7yhjmro`(`problem_id`) USING BTREE,
  CONSTRAINT `FKivml84fkfpdwn2mwmt7yhjmro` FOREIGN KEY (`problem_id`) REFERENCES `problem` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_case
-- ----------------------------
INSERT INTO `test_case` VALUES (1, '1 1', '2', 46);
INSERT INTO `test_case` VALUES (2, '2 2 ', '4', 46);
INSERT INTO `test_case` VALUES (3, '7 7', '14', 46);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL COMMENT '用户编号',
  `admin_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '管理员信息',
  `create_time` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱地址',
  `is_disabled` bit(1) DEFAULT NULL COMMENT '封禁表示',
  `last_login` datetime(0) DEFAULT NULL COMMENT '上次登录时间',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户密码',
  `reset_password_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码重置token',
  `reset_password_token_expire_time` datetime(0) DEFAULT NULL COMMENT 'token有效期',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码盐',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (103, NULL, '2019-02-01 13:10:18', '12@1.com', b'0', NULL, '123456', NULL, NULL, '', '测试操作1');
INSERT INTO `user` VALUES (104, NULL, '2019-02-01 13:10:32', '12@1.com', b'0', NULL, '123456', NULL, NULL, '', '测试1操作1');
INSERT INTO `user` VALUES (105, NULL, '2019-02-01 13:10:43', '12@1.com', b'0', NULL, '123456', NULL, NULL, '', '12341');
INSERT INTO `user` VALUES (106, NULL, '2019-02-01 13:11:15', '12@1.com', b'0', NULL, '123456', NULL, NULL, '', '测试操作12');
INSERT INTO `user` VALUES (107, NULL, '2019-02-01 13:11:19', '12@1.com', b'0', NULL, '123456', NULL, NULL, '', '测试操作123');
INSERT INTO `user` VALUES (108, NULL, '2019-02-01 13:11:21', '12@1.com', b'0', NULL, '123456', NULL, NULL, '', '测试操作1234');
INSERT INTO `user` VALUES (109, NULL, '2019-02-01 13:13:25', 'asdfasdf@126.com', b'0', NULL, 'asdf asdf ', NULL, NULL, '', '啊手动阀');
INSERT INTO `user` VALUES (110, NULL, '2019-02-01 13:13:33', 'asdfasdf@126.com', b'0', NULL, 'asdf asdf ', NULL, NULL, '', '啊手动阀1');
INSERT INTO `user` VALUES (111, NULL, '2019-02-01 13:19:35', '123@1.com', b'0', NULL, '123123123', NULL, NULL, '', '123123');
INSERT INTO `user` VALUES (112, NULL, '2019-02-01 13:22:21', '123@QQ.COM', b'0', NULL, '21312312321312', NULL, NULL, '', '啊但是发射点');
INSERT INTO `user` VALUES (113, NULL, '2019-02-01 13:22:57', '123@QQ.COM', b'0', NULL, '21312312321312', NULL, NULL, '', '啊但是发射点1');
INSERT INTO `user` VALUES (114, NULL, '2019-02-01 13:23:03', '123@QQ.COM', b'0', NULL, '21312312321312', NULL, NULL, '', '啊但是发射点12');
INSERT INTO `user` VALUES (115, NULL, '2019-02-01 13:23:21', '1234@12.com', b'0', NULL, '12341234', NULL, NULL, '', '13241234');
INSERT INTO `user` VALUES (116, NULL, '2019-02-01 13:24:26', '1@1.com', b'0', NULL, 'asdf asdfasd ', NULL, NULL, '', 'sdfasd大飒飒法');
INSERT INTO `user` VALUES (117, NULL, '2019-02-01 13:24:31', '1@1.com', b'0', NULL, 'asdf asdfasd ', NULL, NULL, '', 'sdfasd大飒飒法1');
INSERT INTO `user` VALUES (118, NULL, '2019-02-01 13:25:37', '111@1.com', b'0', NULL, '123456', NULL, NULL, '', '啊手动阀手动阀1');
INSERT INTO `user` VALUES (119, NULL, '2019-02-01 13:27:50', '122112@123.com', b'0', NULL, '122112@123.com', NULL, NULL, '', 'asdfasdf');
INSERT INTO `user` VALUES (120, NULL, '2019-02-01 13:49:58', 'admin@admin.com', b'0', NULL, '123456', NULL, NULL, '', 'admin');
INSERT INTO `user` VALUES (121, NULL, '2019-02-01 14:03:30', '123456@1233.com', b'0', NULL, 'sdafasd asdfasdfasdf', NULL, NULL, '', '奥多姆，in');
INSERT INTO `user` VALUES (122, NULL, '2019-02-01 14:03:50', 'adsfasdfasd@126.com', b'0', NULL, 'asdfasdf', NULL, NULL, '', 'asdfasdfsasdf');
INSERT INTO `user` VALUES (123, NULL, '2019-02-01 14:09:26', '1621231@126.com', b'0', NULL, 'asdfasdfasdfasdf', NULL, NULL, '', 'asdfasdfasdf');
INSERT INTO `user` VALUES (124, NULL, '2019-02-01 14:09:47', 'qerqew@126.com', b'0', NULL, 'qewrqwerqwer', NULL, NULL, '', '请问人情味人情味');
INSERT INTO `user` VALUES (125, NULL, '2019-02-01 14:11:19', '123123@123.com', b'0', NULL, 'asdf asdf asd', NULL, NULL, '', '阿斯顿发射点');
INSERT INTO `user` VALUES (126, NULL, '2019-02-01 14:12:26', 'asdfasdf@126.com', b'0', NULL, 'asdf@126.', NULL, NULL, '', 'asdfasdfasdfasdfasdf');
INSERT INTO `user` VALUES (127, NULL, '2019-02-01 14:13:12', '426456@132.com', b'0', NULL, 'asdfasdfasd', NULL, NULL, '', 'sdafasdfasdfasdf');
INSERT INTO `user` VALUES (128, NULL, '2019-02-01 14:14:33', 'asddf@126.com', b'0', NULL, 'asdfadsfasdfasd', NULL, NULL, '', 'asdfasdfasd');
INSERT INTO `user` VALUES (129, NULL, '2019-02-01 14:14:50', 'asdfasdfasd@126.com', b'0', NULL, 'sdafas@12', NULL, NULL, '', 'dsfasdfasdfasdd');
INSERT INTO `user` VALUES (130, NULL, '2019-02-01 14:15:33', '1@1.com', b'0', NULL, '123456', NULL, NULL, '', 'test123');
INSERT INTO `user` VALUES (131, NULL, '2019-02-03 07:14:42', 'test@test.com', b'0', NULL, 'szy19960530', NULL, NULL, '', '测试用户2');
INSERT INTO `user` VALUES (189, NULL, '2019-02-04 04:55:38', 'doodlister@111.com', b'0', NULL, 'szy19960530', NULL, NULL, '', '猪年官方运营');
INSERT INTO `user` VALUES (190, NULL, '2019-02-06 14:33:08', '啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊@ttttttttttttttttttt.aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', b'0', NULL, '啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊', NULL, NULL, '', '啊你马勒戈壁');
INSERT INTO `user` VALUES (191, NULL, '2019-02-06 14:37:31', '1@1.com', b'0', NULL, '<h1>测试用户</h1>', NULL, NULL, '', '<h1>测试用户</h1>');
INSERT INTO `user` VALUES (192, NULL, '2019-02-06 14:39:32', '123123@11.com', b'0', NULL, '<script type=\"text/javascript\">alert(\"test\")</script>', NULL, NULL, '', '<script type=\"text/javascript\">alert(\"test\")</script>');
INSERT INTO `user` VALUES (193, NULL, '2019-02-06 14:41:21', '123@134.com', b'0', NULL, '<script>alert(‘foolish!’)</script>', NULL, NULL, '', '<script>alert(‘foolish!’)</script>');
INSERT INTO `user` VALUES (194, NULL, '2019-02-06 14:42:08', '343214@43214423.com', b'0', NULL, '<script>alert(document.cookie)</script>', NULL, NULL, '', '<script>alert(document.cookie)</script>');
INSERT INTO `user` VALUES (195, NULL, '2019-03-09 14:52:37', '1234@1.com', b'0', NULL, '123456', NULL, NULL, '', '东欧的历史特人');
INSERT INTO `user` VALUES (196, NULL, '2019-03-30 05:42:37', 'sdfasdfas@asdfasf.com', b'0', NULL, 'asdfasdfa', NULL, NULL, '', 'sdfasdf');
INSERT INTO `user` VALUES (197, NULL, '2019-03-30 05:43:06', 'admin12121212@admin12121212.com', b'0', NULL, 'admin12121212', NULL, NULL, '', 'admin12121212');
INSERT INTO `user` VALUES (204, NULL, '2019-04-09 14:59:42', 'doodlsiter@126.com', b'0', NULL, 'szy19960530', NULL, NULL, '', 'doodlsiter@126.com');
INSERT INTO `user` VALUES (248, NULL, '2019-04-11 12:45:59', 'ee@q.com', b'0', NULL, 'ceshizhangah', NULL, NULL, '', '测试账号');
INSERT INTO `user` VALUES (249, NULL, '2019-04-11 12:46:31', 'test@t.com', b'0', NULL, 'test123', NULL, NULL, '', 'test123123');
INSERT INTO `user` VALUES (263, NULL, '2019-04-25 07:20:13', 'test@test.com', b'0', NULL, 'test@test.com', NULL, NULL, '', 'test@test');
INSERT INTO `user` VALUES (286, NULL, '2019-04-25 13:34:42', 'yanshi@hbu.edu.cn', b'0', NULL, '123456', NULL, NULL, '', '论文演示');
INSERT INTO `user` VALUES (288, NULL, '2019-04-26 06:03:45', '123@hbu.cn', b'0', NULL, '123456', NULL, NULL, '', '演示账号');

-- ----------------------------
-- Table structure for user_profile
-- ----------------------------
DROP TABLE IF EXISTS `user_profile`;
CREATE TABLE `user_profile`  (
  `id` int(11) NOT NULL COMMENT '资料编号',
  `accepted_number` int(11) DEFAULT NULL COMMENT '提交数量',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像地址',
  `blog` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '博客地址',
  `github` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'github地址',
  `language` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '擅长语言',
  `major` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '专业信息',
  `mood` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '个性签名',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实姓名',
  `school` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学校信息',
  `submission_number` int(11) DEFAULT NULL COMMENT '提交数目',
  `total_score` bigint(20) DEFAULT NULL COMMENT '总分数',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK6kwj5lk78pnhwor4pgosvb51r`(`user_id`) USING BTREE,
  CONSTRAINT `FK6kwj5lk78pnhwor4pgosvb51r` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
