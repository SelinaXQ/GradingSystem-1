/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : gradingsys

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2019-12-17 18:37:43
*/

SET FOREIGN_KEY_CHECKS=0;

create database gradingsys;

use gradingsys;

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
INSERT INTO `courses` VALUES ('1', 'CS 591', 'Fall 2018', 'CAS', '0');
INSERT INTO `courses` VALUES ('ff8080816f16128d016f1613a3f70000', 'CS 611', 'Fall 2019', 'CAS', '0');

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
  CONSTRAINT `coursesstudents_ibfk_2` FOREIGN KEY (`buid`) REFERENCES `students` (`buid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Information of students enrolled in all the courses, including their status, grade and comment';

-- ----------------------------
-- Records of coursesstudents
-- ----------------------------
INSERT INTO `coursesstudents` VALUES ('1ba9faa66f15b775016f15b819270006', '1', 'U96796201', '', 'A', null);
INSERT INTO `coursesstudents` VALUES ('1ba9faa66f15b775016f15b8194a000b', '1', 'U96796202', '', 'A-', null);
INSERT INTO `coursesstudents` VALUES ('1ba9faa66f15b775016f15b819680010', '1', 'U96796203', '', 'A-', null);
INSERT INTO `coursesstudents` VALUES ('1ba9faa66f15b775016f15b819870015', '1', 'U96796204', '', 'B+', null);
INSERT INTO `coursesstudents` VALUES ('1ba9faa66f15e19e016f15e6fa620014', '1ba9faa66f15d9ce016f15de442b0000', 'U96796201', '', 'A', null);
INSERT INTO `coursesstudents` VALUES ('1ba9faa66f15e19e016f15e6fa88001b', '1ba9faa66f15d9ce016f15de442b0000', 'U96796202', '', 'A-', null);
INSERT INTO `coursesstudents` VALUES ('1ba9faa66f15e19e016f15e6faaa0022', '1ba9faa66f15d9ce016f15de442b0000', 'U96796203', '', 'B+', null);
INSERT INTO `coursesstudents` VALUES ('1ba9faa66f15e19e016f15e6facb0029', '1ba9faa66f15d9ce016f15de442b0000', 'U96796204', 'w', null, null);
INSERT INTO `coursesstudents` VALUES ('ff8080816f16128d016f1615e5900013', 'ff8080816f16128d016f1613a3f70000', 'U96796201', '', null, null);
INSERT INTO `coursesstudents` VALUES ('ff8080816f16128d016f1615e5fb001a', 'ff8080816f16128d016f1613a3f70000', 'U96796202', '', null, null);
INSERT INTO `coursesstudents` VALUES ('ff8080816f16128d016f1615e6300021', 'ff8080816f16128d016f1613a3f70000', 'U96796203', '', null, null);
INSERT INTO `coursesstudents` VALUES ('ff8080816f16128d016f1615e6670028', 'ff8080816f16128d016f1613a3f70000', 'U96796204', 'w', null, null);

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
  CONSTRAINT `detailedcriteria_ibfk_1` FOREIGN KEY (`gCriID`) REFERENCES `generalcriteria` (`gCriID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Detailed Criteria (Grading Criteria Level 1)';

-- ----------------------------
-- Records of detailedcriteria
-- ----------------------------
INSERT INTO `detailedcriteria` VALUES ('1ba9faa66f15f836016f15f8bc590001', '1ba9faa66f15f836016f15f8bc3f0000', 'e1', '0.50', '100.00');
INSERT INTO `detailedcriteria` VALUES ('1ba9faa66f15f836016f15f8bc8e0006', '1ba9faa66f15f836016f15f8bc3f0000', 'e2', '0.50', '100.00');
INSERT INTO `detailedcriteria` VALUES ('1ba9faa66f15f836016f15f8bcbe000c', '1ba9faa66f15f836016f15f8bcb2000b', 'assi1', '0.50', '100.00');
INSERT INTO `detailedcriteria` VALUES ('1ba9faa66f15f836016f15f8bce60011', '1ba9faa66f15f836016f15f8bcb2000b', 'assi2', '0.50', '100.00');
INSERT INTO `detailedcriteria` VALUES ('ff8080816f16128d016f16140a960002', 'ff8080816f16128d016f16140a5e0001', 'a1', '0.50', '100.00');
INSERT INTO `detailedcriteria` VALUES ('ff8080816f16128d016f16140a9c0003', 'ff8080816f16128d016f16140a5e0001', 'a2', '0.50', '100.00');
INSERT INTO `detailedcriteria` VALUES ('ff8080816f16128d016f16140aae0005', 'ff8080816f16128d016f16140aa50004', 'dh1', '0.50', '100.00');
INSERT INTO `detailedcriteria` VALUES ('ff8080816f16128d016f16140ab70006', 'ff8080816f16128d016f16140aa50004', 'dh2', '0.50', '100.00');
INSERT INTO `detailedcriteria` VALUES ('ff8080816f16128d016f16156ed90008', 'ff8080816f16128d016f161484380007', 'final project', '0.50', '100.00');
INSERT INTO `detailedcriteria` VALUES ('ff8080816f16128d016f16156f130009', 'ff8080816f16128d016f161484380007', 'gorup project', '0.50', '100.00');

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
  CONSTRAINT `generalcriteria_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `courses` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='General Criteria (Grading Criteria Level 1)';

-- ----------------------------
-- Records of generalcriteria
-- ----------------------------
INSERT INTO `generalcriteria` VALUES ('1ba9faa66f15f836016f15f8bc3f0000', '1', 'exam', '0.50');
INSERT INTO `generalcriteria` VALUES ('1ba9faa66f15f836016f15f8bcb2000b', '1', 'assignment', '0.50');
INSERT INTO `generalcriteria` VALUES ('ff8080816f16128d016f16140a5e0001', 'ff8080816f16128d016f1613a3f70000', 'exam', '0.50');
INSERT INTO `generalcriteria` VALUES ('ff8080816f16128d016f16140aa50004', 'ff8080816f16128d016f1613a3f70000', 'assignment', '0.20');
INSERT INTO `generalcriteria` VALUES ('ff8080816f16128d016f161484380007', 'ff8080816f16128d016f1613a3f70000', 'project', '0.30');

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
  CONSTRAINT `studentdetailedgrade_ibfk_1` FOREIGN KEY (`csid`) REFERENCES `coursesstudents` (`csid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studentdetailedgrade_ibfk_2` FOREIGN KEY (`dCriID`) REFERENCES `detailedcriteria` (`dCriID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Students’ grade for each assignment, quiz etc.';

-- ----------------------------
-- Records of studentdetailedgrade
-- ----------------------------
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bc6a0002', '1ba9faa66f15b775016f15b819270006', '1ba9faa66f15f836016f15f8bc590001', '0.90', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bc720003', '1ba9faa66f15b775016f15b8194a000b', '1ba9faa66f15f836016f15f8bc590001', '0.80', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bc790004', '1ba9faa66f15b775016f15b819680010', '1ba9faa66f15f836016f15f8bc590001', '0.70', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bc810005', '1ba9faa66f15b775016f15b819870015', '1ba9faa66f15f836016f15f8bc590001', '0.60', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bc960007', '1ba9faa66f15b775016f15b819270006', '1ba9faa66f15f836016f15f8bc8e0006', '0.90', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bc9d0008', '1ba9faa66f15b775016f15b8194a000b', '1ba9faa66f15f836016f15f8bc8e0006', '0.80', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bca40009', '1ba9faa66f15b775016f15b819680010', '1ba9faa66f15f836016f15f8bc8e0006', '0.70', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bcab000a', '1ba9faa66f15b775016f15b819870015', '1ba9faa66f15f836016f15f8bc8e0006', '0.60', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bcc5000d', '1ba9faa66f15b775016f15b819270006', '1ba9faa66f15f836016f15f8bcbe000c', '0.90', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bccb000e', '1ba9faa66f15b775016f15b8194a000b', '1ba9faa66f15f836016f15f8bcbe000c', '0.80', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bcd3000f', '1ba9faa66f15b775016f15b819680010', '1ba9faa66f15f836016f15f8bcbe000c', '0.70', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bcda0010', '1ba9faa66f15b775016f15b819870015', '1ba9faa66f15f836016f15f8bcbe000c', '0.60', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bcec0012', '1ba9faa66f15b775016f15b819270006', '1ba9faa66f15f836016f15f8bce60011', '0.90', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bcf30013', '1ba9faa66f15b775016f15b8194a000b', '1ba9faa66f15f836016f15f8bce60011', '0.80', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bcf90014', '1ba9faa66f15b775016f15b819680010', '1ba9faa66f15f836016f15f8bce60011', '0.70', null);
INSERT INTO `studentdetailedgrade` VALUES ('1ba9faa66f15f836016f15f8bd000015', '1ba9faa66f15b775016f15b819870015', '1ba9faa66f15f836016f15f8bce60011', '0.60', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e5d00014', 'ff8080816f16128d016f1615e5900013', 'ff8080816f16128d016f16140a960002', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e5d50015', 'ff8080816f16128d016f1615e5900013', 'ff8080816f16128d016f16140a9c0003', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e5de0016', 'ff8080816f16128d016f1615e5900013', 'ff8080816f16128d016f16140aae0005', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e5e30017', 'ff8080816f16128d016f1615e5900013', 'ff8080816f16128d016f16140ab70006', '0.50', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e5ed0018', 'ff8080816f16128d016f1615e5900013', 'ff8080816f16128d016f16156ed90008', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e5f10019', 'ff8080816f16128d016f1615e5900013', 'ff8080816f16128d016f16156f130009', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e60a001b', 'ff8080816f16128d016f1615e5fb001a', 'ff8080816f16128d016f16140a960002', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e60e001c', 'ff8080816f16128d016f1615e5fb001a', 'ff8080816f16128d016f16140a9c0003', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e616001d', 'ff8080816f16128d016f1615e5fb001a', 'ff8080816f16128d016f16140aae0005', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e61a001e', 'ff8080816f16128d016f1615e5fb001a', 'ff8080816f16128d016f16140ab70006', '-10.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e622001f', 'ff8080816f16128d016f1615e5fb001a', 'ff8080816f16128d016f16156ed90008', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e6260020', 'ff8080816f16128d016f1615e5fb001a', 'ff8080816f16128d016f16156f130009', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e63f0022', 'ff8080816f16128d016f1615e6300021', 'ff8080816f16128d016f16140a960002', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e6440023', 'ff8080816f16128d016f1615e6300021', 'ff8080816f16128d016f16140a9c0003', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e64d0024', 'ff8080816f16128d016f1615e6300021', 'ff8080816f16128d016f16140aae0005', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e6510025', 'ff8080816f16128d016f1615e6300021', 'ff8080816f16128d016f16140ab70006', '-20.00', 'nice!');
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e6580026', 'ff8080816f16128d016f1615e6300021', 'ff8080816f16128d016f16156ed90008', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e65c0027', 'ff8080816f16128d016f1615e6300021', 'ff8080816f16128d016f16156f130009', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e6750029', 'ff8080816f16128d016f1615e6670028', 'ff8080816f16128d016f16140a960002', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e67a002a', 'ff8080816f16128d016f1615e6670028', 'ff8080816f16128d016f16140a9c0003', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e681002b', 'ff8080816f16128d016f1615e6670028', 'ff8080816f16128d016f16140aae0005', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e686002c', 'ff8080816f16128d016f1615e6670028', 'ff8080816f16128d016f16140ab70006', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e68e002d', 'ff8080816f16128d016f1615e6670028', 'ff8080816f16128d016f16156ed90008', '0.00', null);
INSERT INTO `studentdetailedgrade` VALUES ('ff8080816f16128d016f1615e692002e', 'ff8080816f16128d016f1615e6670028', 'ff8080816f16128d016f16156f130009', '0.00', null);

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
INSERT INTO `students` VALUES ('U96796201', 'Qian', '', 'Xiang');
INSERT INTO `students` VALUES ('U96796202', 'Xueyan', '', 'Xia');
INSERT INTO `students` VALUES ('U96796203', 'Kaijie', '', 'Chen');
INSERT INTO `students` VALUES ('U96796204', 'Feng', '', 'Jia');
INSERT INTO `students` VALUES ('U96796205', 'Lin', 'A', 'oo');

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
INSERT INTO `templatedetailedcriteria` VALUES ('1ba9faa66f15e129016f15e161330000', '1ba9faa66f15e129016f15e161490004', 'a1', '0.50', '100.00');
INSERT INTO `templatedetailedcriteria` VALUES ('1ba9faa66f15e129016f15e1613c0001', '1ba9faa66f15e129016f15e161490004', 'a2', '0.50', '100.00');
INSERT INTO `templatedetailedcriteria` VALUES ('1ba9faa66f15e129016f15e161420002', '1ba9faa66f15e129016f15e1614d0005', 'dh1', '0.50', '100.00');
INSERT INTO `templatedetailedcriteria` VALUES ('1ba9faa66f15e129016f15e161460003', '1ba9faa66f15e129016f15e1614d0005', 'dh2', '0.50', '100.00');
INSERT INTO `templatedetailedcriteria` VALUES ('ff8080816f16128d016f16159701000a', 'ff8080816f16128d016f16140a5e0001', 'a1', '0.50', '100.00');
INSERT INTO `templatedetailedcriteria` VALUES ('ff8080816f16128d016f16159736000b', 'ff8080816f16128d016f16140a5e0001', 'a2', '0.50', '100.00');
INSERT INTO `templatedetailedcriteria` VALUES ('ff8080816f16128d016f16159741000c', 'ff8080816f16128d016f16140aa50004', 'dh1', '0.50', '100.00');
INSERT INTO `templatedetailedcriteria` VALUES ('ff8080816f16128d016f16159747000d', 'ff8080816f16128d016f16140aa50004', 'dh2', '0.50', '100.00');
INSERT INTO `templatedetailedcriteria` VALUES ('ff8080816f16128d016f16159752000e', 'ff8080816f16128d016f161484380007', 'final project', '0.50', '100.00');
INSERT INTO `templatedetailedcriteria` VALUES ('ff8080816f16128d016f16159758000f', 'ff8080816f16128d016f161484380007', 'gorup project', '0.50', '100.00');

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
INSERT INTO `templategeneralcriteria` VALUES ('1ba9faa66f15e129016f15e161490004', '1', 'exam', '0.50');
INSERT INTO `templategeneralcriteria` VALUES ('1ba9faa66f15e129016f15e1614d0005', '1', 'assignment', '0.50');
INSERT INTO `templategeneralcriteria` VALUES ('ff8080816f16128d016f1615975e0010', 'ff8080816f16128d016f1613a3f70000', 'exam', '0.50');
INSERT INTO `templategeneralcriteria` VALUES ('ff8080816f16128d016f161597640011', 'ff8080816f16128d016f1613a3f70000', 'assignment', '0.20');
INSERT INTO `templategeneralcriteria` VALUES ('ff8080816f16128d016f161597690012', 'ff8080816f16128d016f1613a3f70000', 'project', '0.30');
