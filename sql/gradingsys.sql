/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : gradingsys

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2019-12-05 00:44:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Account';

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('cpk', 'cpk');

-- ----------------------------
-- Table structure for `courses`
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `cid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Course ID',
  `cname` varchar(30) NOT NULL COMMENT 'Course Name',
  `semid` varchar(20) NOT NULL COMMENT 'Semester ID',
  `college` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'College Name',
  `state` int(11) NOT NULL DEFAULT '1' COMMENT 'Status: true:Active / false:Closed',
  PRIMARY KEY (`cid`),
  KEY `semid` (`semid`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`semid`) REFERENCES `semesters` (`semid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Courses';

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES ('1', 'CS 500', 'Fall 2019', 'CAS', '1');
INSERT INTO `courses` VALUES ('123455', 'CS 400', 'Fall 2018', 'GRS', '0');

-- ----------------------------
-- Table structure for `coursesstudents`
-- ----------------------------
DROP TABLE IF EXISTS `coursesstudents`;
CREATE TABLE `coursesstudents` (
  `csid` varchar(32) NOT NULL,
  `cid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `buid` varchar(20) NOT NULL,
  `con` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `grade` varchar(5) DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`csid`),
  KEY `cid` (`cid`),
  KEY `buid` (`buid`),
  CONSTRAINT `coursesstudents_ibfk_2` FOREIGN KEY (`buid`) REFERENCES `students` (`buid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Information of students enrolled in all the courses, including their status, grade and comment';

-- ----------------------------
-- Records of coursesstudents
-- ----------------------------
INSERT INTO `coursesstudents` VALUES ('8a80803d6eca223f016eca2246c50000', '1', 'U8899', '', null, null);

-- ----------------------------
-- Table structure for `detailedcriteria`
-- ----------------------------
DROP TABLE IF EXISTS `detailedcriteria`;
CREATE TABLE `detailedcriteria` (
  `dCriID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'detailed criteria id',
  `gCriID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'general criteria id (parent node id)',
  `deCriType` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `deCriPer` double(32,2) NOT NULL,
  `totalScore` double(32,2) unsigned DEFAULT '0.00',
  PRIMARY KEY (`dCriID`),
  KEY `detailedcriteria_ibfk_1` (`gCriID`),
  CONSTRAINT `detailedcriteria_ibfk_1` FOREIGN KEY (`gCriID`) REFERENCES `generalcriteria` (`gCriID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Detailed Criteria (Grading Criteria Level 1)';

-- ----------------------------
-- Records of detailedcriteria
-- ----------------------------
INSERT INTO `detailedcriteria` VALUES ('8a80803d6ed40deb016ed40dedf70000', 'abcd', 'dh1', '0.50', '100.00');
INSERT INTO `detailedcriteria` VALUES ('8a80803d6ed40deb016ed40dee270001', 'abcd', 'dh2', '0.50', '100.00');

-- ----------------------------
-- Table structure for `generalcriteria`
-- ----------------------------
DROP TABLE IF EXISTS `generalcriteria`;
CREATE TABLE `generalcriteria` (
  `gCriID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'general criteria id',
  `cid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'course id',
  `genCriType` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'general criteria type',
  `genCriPer` double(32,2) NOT NULL COMMENT 'general criteria percentage',
  PRIMARY KEY (`gCriID`),
  KEY `generalcriteria_ibfk_1` (`cid`),
  CONSTRAINT `generalcriteria_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `courses` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='General Criteria (Grading Criteria Level 1)';

-- ----------------------------
-- Records of generalcriteria
-- ----------------------------
INSERT INTO `generalcriteria` VALUES ('abcd', '1', 'assginment', '0.50');

-- ----------------------------
-- Table structure for `semesters`
-- ----------------------------
DROP TABLE IF EXISTS `semesters`;
CREATE TABLE `semesters` (
  `semid` varchar(20) NOT NULL COMMENT 'semester id (year + semester)',
  PRIMARY KEY (`semid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Semesters';

-- ----------------------------
-- Records of semesters
-- ----------------------------
INSERT INTO `semesters` VALUES ('Fall 2018');
INSERT INTO `semesters` VALUES ('Fall 2019');
INSERT INTO `semesters` VALUES ('Spring 2018');
INSERT INTO `semesters` VALUES ('Spring 2019');
INSERT INTO `semesters` VALUES ('Spring 2020');

-- ----------------------------
-- Table structure for `studentdetailedgrade`
-- ----------------------------
DROP TABLE IF EXISTS `studentdetailedgrade`;
CREATE TABLE `studentdetailedgrade` (
  `sdgid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'StudentDetailedGrade ID',
  `csid` varchar(32) NOT NULL COMMENT 'course id',
  `dCriID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'detailed criteria id',
  `score` double(32,2) DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`sdgid`),
  KEY `studentdetailedgrade_ibfk_2` (`dCriID`),
  KEY `studentdetailedgrade_ibfk_1` (`csid`),
  CONSTRAINT `studentdetailedgrade_ibfk_1` FOREIGN KEY (`csid`) REFERENCES `coursesstudents` (`csid`),
  CONSTRAINT `studentdetailedgrade_ibfk_2` FOREIGN KEY (`dCriID`) REFERENCES `detailedcriteria` (`dCriID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Students’ grade for each assignment, quiz etc.';

-- ----------------------------
-- Records of studentdetailedgrade
-- ----------------------------
INSERT INTO `studentdetailedgrade` VALUES ('1', '8a80803d6eca223f016eca2246c50000', '8a80803d6ed40deb016ed40dedf70000', '-50.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('2', '8a80803d6eca223f016eca2246c50000', '8a80803d6ed40deb016ed40dee270001', '-50.00', null);

-- ----------------------------
-- Table structure for `students`
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `buid` varchar(20) NOT NULL COMMENT 'BUID',
  `firstname` varchar(10) NOT NULL COMMENT 'First Name',
  `middlename` varchar(10) DEFAULT NULL COMMENT 'Middle Name',
  `lastname` varchar(10) NOT NULL COMMENT 'Last Name',
  PRIMARY KEY (`buid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Students';

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('B', 'F', 'M', 'L');
INSERT INTO `students` VALUES ('BAA', 'F', 'M', 'L');
INSERT INTO `students` VALUES ('U', 'FAA', 'M', 'L');
INSERT INTO `students` VALUES ('U8899', 'FAA', 'M', 'L');

-- ----------------------------
-- Table structure for `templatedetailedcriteria`
-- ----------------------------
DROP TABLE IF EXISTS `templatedetailedcriteria`;
CREATE TABLE `templatedetailedcriteria` (
  `dCriID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gCriID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `deCriType` varchar(15) NOT NULL,
  `deCriPer` double(32,2) NOT NULL,
  `totalScore` double(32,2) unsigned DEFAULT '0.00',
  PRIMARY KEY (`dCriID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Detailed Grading Template';

-- ----------------------------
-- Records of templatedetailedcriteria
-- ----------------------------
INSERT INTO `templatedetailedcriteria` VALUES ('8a80803d6ed40e84016ed40e870a0000', 'abcd', 'dh1', '0.50', '0.00');
INSERT INTO `templatedetailedcriteria` VALUES ('8a80803d6ed40e84016ed40e873d0001', 'abcd', 'dh2', '0.50', '0.00');
INSERT INTO `templatedetailedcriteria` VALUES ('8a80803d6ed4228c016ed4228f6a0000', 'abcd', 'dh1', '0.50', '0.00');
INSERT INTO `templatedetailedcriteria` VALUES ('8a80803d6ed4228c016ed4228f980001', 'abcd', 'dh2', '0.50', '0.00');

-- ----------------------------
-- Table structure for `templategeneralcriteria`
-- ----------------------------
DROP TABLE IF EXISTS `templategeneralcriteria`;
CREATE TABLE `templategeneralcriteria` (
  `gCriID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `genCriType` varchar(15) NOT NULL,
  `genCriPer` double(32,2) NOT NULL,
  PRIMARY KEY (`gCriID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='General Grading Template';

-- ----------------------------
-- Records of templategeneralcriteria
-- ----------------------------
INSERT INTO `templategeneralcriteria` VALUES ('abcc', '1', 'final', '0.50');
