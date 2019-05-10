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

 Date: 10/05/2019 22:42:43
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
