/*
 Navicat Premium Data Transfer

 Source Server         : testlove
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : testlove.cn:3306
 Source Schema         : SpringSecurityHello

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 11/04/2021 17:13:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleId` int(11) NOT NULL,
  `roleName` varchar(20) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `access` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roleId`) USING BTREE,
  UNIQUE INDEX ```roleName```(`roleName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '/**');
INSERT INTO `role` VALUES (2, 'guest', '/**');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `password` char(70) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `roleId` int(11) NULL DEFAULT NULL,
  `userId` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`userId`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  CONSTRAINT `roleId` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('testlove', '$2a$10$oB1yC0ltAoVuQ7zHoD.iQuZor0GoWwfnIyA9HU8FSXoDHinXQJiEq', 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
