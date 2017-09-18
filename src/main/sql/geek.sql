
CREATE DATABASE geek;

USE geek;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for candidate
-- ----------------------------
DROP TABLE IF EXISTS `candidate`;
CREATE TABLE `candidate` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `candidate_id` char(10) NOT NULL COMMENT '学生学号',
  `name` varchar(255) NOT NULL COMMENT '学生姓名',
  `school` varchar(255) DEFAULT NULL COMMENT '学院',
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `direction` varchar(255) DEFAULT NULL COMMENT '所选方向',
  `introduction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `candidate_id` (`candidate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of candidate
-- ----------------------------
INSERT INTO `candidate` VALUES ('1', '1', '1', '1', '1', '1', null);
INSERT INTO `candidate` VALUES ('2', '2', '2', '2', '2', '2', null);

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` char(10) NOT NULL COMMENT '成员id',
  `name` varchar(255) NOT NULL COMMENT '成员姓名',
  `sex` char(1) DEFAULT NULL COMMENT '成员性别',
  `photo` varchar(255) DEFAULT NULL COMMENT '照片地址',
  `direction` varchar(255) DEFAULT NULL COMMENT '方向',
  `introduction` varchar(255) DEFAULT NULL COMMENT '自我介绍',
  `company` varchar(255) DEFAULT NULL COMMENT '就职公司',
  PRIMARY KEY (`id`),
  UNIQUE KEY `member_id` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('2', '2015210991', '凌龙', '男', 'images/members/2015210991.jpg', '后台', '我是一个帅哥', '腾讯科技（深圳）有限公司');
INSERT INTO `member` VALUES ('3', '2015210992', '李文浩', '男', 'images/members/2015210992.jpg', '后台', '你所思考的将是你要到达的地方', '腾讯科技（深圳）有限公司');
INSERT INTO `member` VALUES ('4', '2015210994', '李文浩', '男', 'images/members/2015210994.jpg', '前端', '我是一个帅哥', '网易（杭州）网络有限公司');
INSERT INTO `member` VALUES ('5', '2015211105', '唐雅', '女', 'images/members/2015211105.jpg', '前端', '我是一个美女', '阿里巴巴网络技术有限公司');
INSERT INTO `member` VALUES ('6', '2015211121', '陈俊臣', '男', 'images/members/2015211121.jpg', '前端', '我是一个帅哥', '阿里巴巴网络技术有限公司');
INSERT INTO `member` VALUES ('7', '2015211138', '吴林霏', '', 'images/members/2015211138.jpg', '前端', '我是一个美女', '阿里巴巴网络技术有限公司');
INSERT INTO `member` VALUES ('22', '8484', '88', '', '', '', '', '');
INSERT INTO `member` VALUES ('25', '99', '99', '', '', '', '', '');
INSERT INTO `member` VALUES ('26', '2015210990', '凌龙', '男', 'images/members/2015210991.jpg', '后台', '我是一个帅哥', '腾讯科技（深圳）有限公司');

-- ----------------------------
-- Table structure for production
-- ----------------------------
DROP TABLE IF EXISTS `production`;
CREATE TABLE `production` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '项目名字',
  `effectPicture` varchar(255) DEFAULT NULL COMMENT '项目效果图',
  `introduction` varchar(255) DEFAULT NULL COMMENT '项目介绍',
  `url` varchar(255) DEFAULT NULL COMMENT '项目地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of production
-- ----------------------------
INSERT INTO `production` VALUES ('1', '国际处主页', 'images/productions/InternationalDivision.png', '蓝山工作室负责了重庆邮电大学国际处门户网站的制作。', 'http://gjc.cqupt.edu.cn');
INSERT INTO `production` VALUES ('2', '国际学院主页', 'images/productions/InternationalCollege.png', '蓝山工作室负责了重庆邮电大学国际学院门户网站以及重庆邮电大学英文版网站的制作', 'http://english.cqupt.edu.cn');
INSERT INTO `production` VALUES ('3', '法学院主页', 'images/productions/LawSchool.png', '蓝山工作室负责了重庆邮电大学法学院门户网站及其课程网站、实验中心的制作与维护。', 'http://law.cqupt.edu.cn');
SET FOREIGN_KEY_CHECKS=1;
