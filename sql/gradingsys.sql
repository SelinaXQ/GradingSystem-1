/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : gradingsys

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2019-12-14 22:40:13
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
INSERT INTO `courses` VALUES ('1ba98beb6f0682d0016f0683098d0000', 'CS 611', 'Fall 2019', 'GRS', '1');
INSERT INTO `courses` VALUES ('2', 'CS 600', 'Fall 2019', 'GRS', '1');

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
  CONSTRAINT `coursesstudents_ibfk_2` FOREIGN KEY (`buid`) REFERENCES `students` (`buid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Information of students enrolled in all the courses, including their status, grade and comment';

-- ----------------------------
-- Records of coursesstudents
-- ----------------------------
INSERT INTO `coursesstudents` VALUES ('1ba9d93c6eebc54f016eebc597410000', '1', 'U96796201', '', null, null);
INSERT INTO `coursesstudents` VALUES ('1ba9d93c6eebc54f016eebc5979f0003', '1', 'U96796202', '', null, null);
INSERT INTO `coursesstudents` VALUES ('1ba9d93c6eebc54f016eebc597d70006', '1', 'U96796203', '', null, null);
INSERT INTO `coursesstudents` VALUES ('1ba9d93c6eebc54f016eebc598030009', '1', 'U96796204', '', null, null);
INSERT INTO `coursesstudents` VALUES ('1ba9d93c6eebc54f016eebc5982a000c', '1', 'U96796205', '', null, null);

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
INSERT INTO `detailedcriteria` VALUES ('1ba98beb6f0682d0016f068622ce0002', '1ba98beb6f0682d0016f068622ca0001', 'a1', '0.50', '100.00');
INSERT INTO `detailedcriteria` VALUES ('1ba98beb6f0682d0016f068622d20003', '1ba98beb6f0682d0016f068622ca0001', 'a2', '0.50', '900.00');
INSERT INTO `detailedcriteria` VALUES ('1ba98beb6f0682d0016f068622dc0005', '1ba98beb6f0682d0016f068622d80004', 'dh1', '0.50', '0.00');
INSERT INTO `detailedcriteria` VALUES ('1ba98beb6f0682d0016f068622df0006', '1ba98beb6f0682d0016f068622d80004', 'dh2', '0.50', '0.00');
INSERT INTO `detailedcriteria` VALUES ('1ba98beb6f06a4e3016f06a514e70001', '1ba98beb6f06a4e3016f06a514dc0000', 'a1', '0.50', '100.00');
INSERT INTO `detailedcriteria` VALUES ('1ba98beb6f06a4e3016f06a514eb0002', '1ba98beb6f06a4e3016f06a514dc0000', 'a2', '0.50', '900.00');
INSERT INTO `detailedcriteria` VALUES ('1ba98beb6f06a4e3016f06a514f60004', '1ba98beb6f06a4e3016f06a514f30003', 'dh1', '0.50', '0.00');
INSERT INTO `detailedcriteria` VALUES ('1ba98beb6f06a4e3016f06a514f90005', '1ba98beb6f06a4e3016f06a514f30003', 'dh2', '0.50', '0.00');
INSERT INTO `detailedcriteria` VALUES ('1ba98beb6f06a791016f06a99bf70001', '1ba98beb6f06a4e3016f06a56d3e0006', 'quiz2', '1.00', '50.00');

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
INSERT INTO `generalcriteria` VALUES ('1ba98beb6f0682d0016f068622ca0001', '1', 'final', '0.50');
INSERT INTO `generalcriteria` VALUES ('1ba98beb6f0682d0016f068622d80004', '1', 'assignment', '0.50');
INSERT INTO `generalcriteria` VALUES ('1ba98beb6f06a4e3016f06a514dc0000', '1ba98beb6f0682d0016f0683098d0000', 'final', '0.50');
INSERT INTO `generalcriteria` VALUES ('1ba98beb6f06a4e3016f06a514f30003', '1ba98beb6f0682d0016f0683098d0000', 'assignment', '0.30');
INSERT INTO `generalcriteria` VALUES ('1ba98beb6f06a4e3016f06a56d3e0006', '1ba98beb6f0682d0016f0683098d0000', 'quiz', '0.20');
INSERT INTO `generalcriteria` VALUES ('1ba98beb6f06a791016f06a7f3430000', '1ba98beb6f0682d0016f0683098d0000', 'bonus', '0.00');

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
  CONSTRAINT `studentdetailedgrade_ibfk_1` FOREIGN KEY (`csid`) REFERENCES `coursesstudents` (`csid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studentdetailedgrade_ibfk_2` FOREIGN KEY (`dCriID`) REFERENCES `detailedcriteria` (`dCriID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Students’ grade for each assignment, quiz etc.';

-- ----------------------------
-- Records of studentdetailedgrade
-- ----------------------------

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
INSERT INTO `students` VALUES ('U96796202', 'haha', 'hey', 'Li');
INSERT INTO `students` VALUES ('U96796203', 'Victoria', '', 'Ming');
INSERT INTO `students` VALUES ('U96796204', 'Jimin', 'King', 'Park');
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
INSERT INTO `templatedetailedcriteria` VALUES ('1', 'abcc', 'a1', '0.50', '100.00');
INSERT INTO `templatedetailedcriteria` VALUES ('2', 'abcc', 'a2', '0.50', '900.00');
INSERT INTO `templatedetailedcriteria` VALUES ('8a80803d6ed40e84016ed40e870a0000', 'abcd', 'dh1', '0.50', '0.00');
INSERT INTO `templatedetailedcriteria` VALUES ('8a80803d6ed40e84016ed40e873d0001', 'abcd', 'dh2', '0.50', '0.00');

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
INSERT INTO `templategeneralcriteria` VALUES ('abcd', '1', 'assignment', '0.50');
