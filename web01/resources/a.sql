SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- 创建日程表
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule`  (
    `sid` int NOT NULL AUTO_INCREMENT,
    `uid` int NULL DEFAULT NULL,
    `title` varchar(20) CHARACTER SET utf8mb4 NULL DEFAULT NULL,
    `completed` int(1) NULL DEFAULT NULL,
    PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4;

-- ----------------------------
-- 插入日程数据
-- ----------------------------

-- ----------------------------
-- 创建用户表
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
    `uid` int NOT NULL AUTO_INCREMENT,
    `username` varchar(10) CHARACTER SET utf8mb4 NULL DEFAULT NULL,
    `user_pwd` varchar(100) CHARACTER SET utf8mb4 NULL DEFAULT NULL,
    PRIMARY KEY (`uid`) USING BTREE,
    UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 ROW_FORMAT = Dynamic;

-- ----------------------------
-- 插入用户数据
-- ----------------------------
INSERT INTO `user` VALUES (1, 'aaa', 'e10adc3949ba59abbe56e057f20f883e');
INSERT INTO `user` VALUES (2, 'bbb', 'e10adc3949ba59abbe56e057f20f883e');

SET FOREIGN_KEY_CHECKS = 1;