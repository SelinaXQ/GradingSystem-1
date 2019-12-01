CREATE DATABASE  IF NOT EXISTS `GradingSys` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `GradingSys`;
-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: GradingSys
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Account`
--

DROP TABLE IF EXISTS `Account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Account` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Account';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Account`
--

LOCK TABLES `Account` WRITE;
/*!40000 ALTER TABLE `Account` DISABLE KEYS */;
INSERT INTO `Account` VALUES ('cpk','cpk');
/*!40000 ALTER TABLE `Account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Courses`
--

DROP TABLE IF EXISTS `Courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Courses` (
  `cid` varchar(30) NOT NULL COMMENT 'Course ID',
  `cname` varchar(30) NOT NULL COMMENT 'Course Name',
  `semid` varchar(20) NOT NULL COMMENT 'Semester ID',
  `college` varchar(20) NOT NULL COMMENT 'College Name',
  `status` tinyint(1) NOT NULL COMMENT 'Status: true:Active / false:Closed',
  PRIMARY KEY (`cid`),
  KEY `semid` (`semid`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`semid`) REFERENCES `semesters` (`semid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Courses';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Courses`
--

LOCK TABLES `Courses` WRITE;
/*!40000 ALTER TABLE `Courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `Courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CoursesStudents`
--

DROP TABLE IF EXISTS `CoursesStudents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CoursesStudents` (
  `csid` varchar(30) NOT NULL,
  `cid` varchar(30) NOT NULL,
  `buid` varchar(20) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `grade` varchar(5) DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`csid`),
  KEY `cid` (`cid`),
  KEY `buid` (`buid`),
  CONSTRAINT `coursesstudents_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `courses` (`cid`),
  CONSTRAINT `coursesstudents_ibfk_2` FOREIGN KEY (`buid`) REFERENCES `students` (`buid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Information of students enrolled in all the courses, including their status, grade and comment';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CoursesStudents`
--

LOCK TABLES `CoursesStudents` WRITE;
/*!40000 ALTER TABLE `CoursesStudents` DISABLE KEYS */;
/*!40000 ALTER TABLE `CoursesStudents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DetailedCriteria`
--

DROP TABLE IF EXISTS `DetailedCriteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DetailedCriteria` (
  `dCriID` varchar(30) NOT NULL COMMENT 'detailed criteria id',
  `gCriID` varchar(30) NOT NULL COMMENT 'general criteria id (parent node id)',
  `deCriType` varchar(15) NOT NULL,
  `deCriPer` double NOT NULL,
  PRIMARY KEY (`dCriID`),
  KEY `detailedcriteria_ibfk_1` (`gCriID`),
  CONSTRAINT `detailedcriteria_ibfk_1` FOREIGN KEY (`gCriID`) REFERENCES `generalcriteria` (`gCriID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Detailed Criteria (Grading Criteria Level 1)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DetailedCriteria`
--

LOCK TABLES `DetailedCriteria` WRITE;
/*!40000 ALTER TABLE `DetailedCriteria` DISABLE KEYS */;
/*!40000 ALTER TABLE `DetailedCriteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GeneralCriteria`
--

DROP TABLE IF EXISTS `GeneralCriteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `GeneralCriteria` (
  `gCriID` varchar(30) NOT NULL COMMENT 'general criteria id',
  `cid` varchar(30) NOT NULL COMMENT 'course id',
  `genCriType` varchar(15) NOT NULL COMMENT 'general criteria type',
  `genCriPer` double NOT NULL COMMENT 'general criteria percentage',
  PRIMARY KEY (`gCriID`),
  KEY `generalcriteria_ibfk_1` (`cid`),
  CONSTRAINT `generalcriteria_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `courses` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='General Criteria (Grading Criteria Level 1)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GeneralCriteria`
--

LOCK TABLES `GeneralCriteria` WRITE;
/*!40000 ALTER TABLE `GeneralCriteria` DISABLE KEYS */;
/*!40000 ALTER TABLE `GeneralCriteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Semesters`
--

DROP TABLE IF EXISTS `Semesters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Semesters` (
  `semid` varchar(20) NOT NULL COMMENT 'semester id (year + semester)',
  `year` varchar(4) NOT NULL,
  `semester` varchar(10) NOT NULL COMMENT '1.Spring; 2.Summer; 3.Fall',
  PRIMARY KEY (`semid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Semesters';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Semesters`
--

LOCK TABLES `Semesters` WRITE;
/*!40000 ALTER TABLE `Semesters` DISABLE KEYS */;
/*!40000 ALTER TABLE `Semesters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StudentDetailedGrade`
--

DROP TABLE IF EXISTS `StudentDetailedGrade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `StudentDetailedGrade` (
  `sdgid` varchar(30) NOT NULL COMMENT 'StudentDetailedGrade ID',
  `csid` varchar(30) NOT NULL COMMENT 'course id',
  `dCriID` varchar(30) NOT NULL COMMENT 'detailed criteria id',
  `score` double DEFAULT NULL,
  PRIMARY KEY (`sdgid`),
  KEY `studentdetailedgrade_ibfk_1` (`csid`),
  KEY `studentdetailedgrade_ibfk_2` (`dCriID`),
  CONSTRAINT `studentdetailedgrade_ibfk_1` FOREIGN KEY (`csid`) REFERENCES `coursesstudents` (`csid`),
  CONSTRAINT `studentdetailedgrade_ibfk_2` FOREIGN KEY (`dCriID`) REFERENCES `detailedcriteria` (`dCriID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Students’ grade for each assignment, quiz etc.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StudentDetailedGrade`
--

LOCK TABLES `StudentDetailedGrade` WRITE;
/*!40000 ALTER TABLE `StudentDetailedGrade` DISABLE KEYS */;
/*!40000 ALTER TABLE `StudentDetailedGrade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Students`
--

DROP TABLE IF EXISTS `Students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Students` (
  `buid` varchar(20) NOT NULL COMMENT 'BUID',
  `firstname` varchar(10) NOT NULL COMMENT 'First Name',
  `middlename` varchar(10) DEFAULT NULL COMMENT 'Middle Name',
  `lastname` varchar(10) NOT NULL COMMENT 'Last Name',
  PRIMARY KEY (`buid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Students';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Students`
--

LOCK TABLES `Students` WRITE;
/*!40000 ALTER TABLE `Students` DISABLE KEYS */;
/*!40000 ALTER TABLE `Students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TemplateDetailedCriteria`
--

DROP TABLE IF EXISTS `TemplateDetailedCriteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TemplateDetailedCriteria` (
  `tdcid` varchar(30) NOT NULL,
  `dCriID` varchar(30) NOT NULL,
  `gCriID` varchar(30) NOT NULL,
  `deCriType` varchar(15) NOT NULL,
  `deCriPer` double NOT NULL,
  PRIMARY KEY (`tdcid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Detailed Grading Template';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TemplateDetailedCriteria`
--

LOCK TABLES `TemplateDetailedCriteria` WRITE;
/*!40000 ALTER TABLE `TemplateDetailedCriteria` DISABLE KEYS */;
/*!40000 ALTER TABLE `TemplateDetailedCriteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TemplateGeneralCriteria`
--

DROP TABLE IF EXISTS `TemplateGeneralCriteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `TemplateGeneralCriteria` (
  `tgcid` varchar(30) NOT NULL,
  `gCriID` varchar(30) NOT NULL,
  `cid` varchar(30) NOT NULL,
  `genCriType` varchar(15) NOT NULL,
  `genCriPer` double NOT NULL,
  PRIMARY KEY (`tgcid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='General Grading Template';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TemplateGeneralCriteria`
--

LOCK TABLES `TemplateGeneralCriteria` WRITE;
/*!40000 ALTER TABLE `TemplateGeneralCriteria` DISABLE KEYS */;
/*!40000 ALTER TABLE `TemplateGeneralCriteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'GradingSys'
--

--
-- Dumping routines for database 'GradingSys'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-01  4:05:24
