-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: easy-cv-dev
-- ------------------------------------------------------
-- Server version	8.0.43

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `curriculum`
--

DROP TABLE IF EXISTS `curriculum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curriculum` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `mobile_phone` varchar(255) DEFAULT NULL,
  `home_address` varchar(255) DEFAULT NULL,
  `work_address` varchar(255) DEFAULT NULL,
  `marital_status` tinyint(1) DEFAULT NULL,
  `driving_license` enum('NONE','A','B','C','D','E') DEFAULT 'NONE',
  `has_car` tinyint(1) DEFAULT NULL,
  `open_for_travel` tinyint(1) DEFAULT NULL,
  `summary` text,
  `last_modified_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_last_modified` (`last_modified_at`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum`
--

LOCK TABLES `curriculum` WRITE;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
INSERT INTO `curriculum` VALUES (2,3,'+1555234567','234 Manager Ln, Townsburg','789 Business Park, Townsburg',0,'B',1,1,'Dynamic Manager (Manager One) focused on team success.','2025-11-04 17:03:25'),(3,4,'+1555345678','345 Leader Rd, Metropolis','789 Business Park, Townsburg',1,'A',0,0,'Strategic Manager (Manager Two) with 10+ years experience.','2025-11-04 17:03:25'),(4,5,'+1555456789','456 Java Ave, Codeville','789 Business Park, Townsburg',0,'NONE',0,1,'Senior Java Developer (emp_m1_1). Expert in Spring Boot.','2025-11-04 17:03:25'),(6,7,'+1555678901','678 Logic Ln, Codeville','789 Business Park, Townsburg',0,'NONE',0,1,'Mid-level Java Developer (emp_m1_3). Strong communicator.','2025-11-04 17:03:25'),(7,8,'+1555789012','789 Byte Rd, Codeville','789 Business Park, Townsburg',0,'C',1,1,'Junior Java Developer (emp_m1_4). Eager to learn.','2025-11-04 17:03:25'),(8,9,'+1555890123','890 Kernel Pl, Codeville','789 Business Park, Townsburg',1,'B',0,0,'Java Backend Specialist (emp_m1_5).','2025-11-04 17:03:25'),(9,10,'+1555901234','901 Docker Way, Infra City','789 Business Park, Townsburg',0,'NONE',0,1,'DevOps Engineer (emp_m1_6). AWS and Kubernetes enthusiast.','2025-11-04 17:03:25'),(10,11,'+1555012345','111 Byte Ct, Codeville','789 Business Park, Townsburg',1,'B',1,1,'Entry-level Java Developer (emp_m1_7).','2025-11-04 17:03:25'),(11,12,'+1555112233','222 Micro Rd, Codeville','789 Business Park, Townsburg',0,'B',1,0,'Java Developer (emp_m1_8) with Spring Boot skills.','2025-11-04 17:03:25'),(12,13,'+1555223344','333 Python Path, Scriptburg','789 Business Park, Townsburg',0,'NONE',0,1,'Senior Python Developer (emp_m2_1). Focus on data science.','2025-11-04 17:03:25'),(13,14,'+1555334455','444 Django Dr, Scriptburg','789 Business Park, Townsburg',1,'B',1,1,'Python/JavaScript Full-Stack Dev (emp_m2_2).','2025-11-04 17:03:25'),(14,15,'+1555445566','555 React Rd, Frontendville','789 Business Park, Townsburg',0,'A',0,0,'Lead Frontend Developer (emp_m2_3). React expert.','2025-11-04 17:03:25'),(15,16,'+1555556677','666 Node Ln, Frontendville','789 Business Park, Townsburg',1,'NONE',0,1,'JavaScript Developer (emp_m2_4). React and Node.','2025-11-04 17:03:25'),(16,17,'+1555667788','777 AI Ave, Scriptburg','789 Business Park, Townsburg',0,'B',1,0,'Data Scientist (emp_m2_5). Python and Problem Solving.','2025-11-04 17:03:25'),(17,18,'+1555778899','888 ML Way, Scriptburg','789 Business Park, Townsburg',1,'B',0,1,'Junior Data Analyst (emp_m2_6).','2025-11-04 17:03:25'),(18,19,'+1555889900','999 Component Ct, Frontendville','789 Business Park, Townsburg',0,'NONE',0,0,'UI/UX Developer (emp_m2_7) with React skills.','2025-11-04 17:03:25'),(19,20,'+1555990011','101 JS St, Frontendville','789 Business Park, Townsburg',1,'B',1,1,'JavaScript Developer (emp_m2_8).','2025-11-04 17:03:25'),(20,21,'242342','14341','141341',1,'C',1,0,'short summary','2025-11-25 10:31:04'),(30,22,'3274533460','via via','via via',0,'B',0,1,'Primo curriculum','2025-12-01 12:58:28');
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `curriculum_id` bigint DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  KEY `idx_curriculum_id` (`curriculum_id`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (2,2,'2021-03-01','2021-12-01','Migration to new Java microservice architecture.'),(3,3,'2022-02-01',NULL,'Ongoing Python Data Science initiative.'),(4,4,'2019-01-15','2019-07-15','E-commerce platform backend (Spring Boot).'),(5,4,'2020-02-01','2020-10-01','Banking API gateway implementation.'),(8,6,'2020-01-10','2020-06-10','Customer support ticket system.'),(9,7,'2022-07-01','2023-01-01','Bug fixing and maintenance for payment module.'),(10,8,'2021-06-01','2022-06-01','High-performance trading engine backend.'),(11,9,'2021-10-01',NULL,'CI/CD pipeline setup using Kubernetes and Git.'),(12,10,'2023-07-01','2023-12-01','Simple inventory management app.'),(13,11,'2022-01-01','2022-07-01','Refactoring Spring Boot monolith to services.'),(14,12,'2018-01-01','2019-01-01','Machine learning model for sales forecasting.'),(15,12,'2020-03-01','2021-03-01','Data processing pipeline with AWS services.'),(16,13,'2019-06-01','2020-06-01','Full-stack web app for internal reporting (Python/JS).'),(17,14,'2019-01-01','2020-01-01','Public-facing marketing website (React).'),(18,14,'2021-02-01',NULL,'React Native mobile application development.'),(19,15,'2022-01-15','2022-08-15','E-commerce frontend redesign (React).'),(20,16,'2019-01-01','2019-12-01','Customer churn prediction model (Python).'),(21,16,'2020-05-01','2021-01-01','A/B testing framework development.'),(22,17,'2023-01-01','2023-06-01','Dashboard for visualizing user metrics.'),(23,18,'2022-06-01','2023-06-01','Component library development in React.'),(24,19,'2021-11-01','2022-05-01','Internal tools portal (JavaScript).'),(50,20,'2025-11-22','2025-11-25','dasfadfs'),(68,30,'2025-12-02','2025-12-07','Aria');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education`
--

DROP TABLE IF EXISTS `education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `education` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `curriculum_id` bigint DEFAULT NULL,
  `school_name_id` bigint DEFAULT NULL,
  `degree_name_id` bigint DEFAULT NULL,
  `grade` decimal(38,2) DEFAULT NULL,
  `max_grade` decimal(38,2) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_curriculum_id` (`curriculum_id`),
  KEY `idx_school_name_id` (`school_name_id`),
  KEY `idx_degree_name_id` (`degree_name_id`),
  CONSTRAINT `education_ibfk_1` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education`
--

LOCK TABLES `education` WRITE;
/*!40000 ALTER TABLE `education` DISABLE KEYS */;
INSERT INTO `education` VALUES (2,2,2,25,98.00,100.00,'2005-09-01','2009-06-01'),(3,3,3,26,4.00,4.00,'2002-09-01','2006-06-01'),(4,4,1,27,4.00,4.00,'2012-09-01','2016-06-01'),(5,4,2,28,4.00,4.00,'2016-09-01','2018-06-01'),(7,6,5,24,90.00,100.00,'2014-09-01','2018-06-01'),(8,7,1,27,85.00,100.00,'2018-09-01','2022-06-01'),(9,8,2,24,92.00,100.00,'2014-09-01','2018-06-01'),(10,9,3,27,4.00,4.00,'2015-09-01','2019-06-01'),(11,10,4,28,88.00,100.00,'2019-09-01','2023-06-01'),(12,11,5,28,91.00,100.00,'2016-09-01','2020-06-01'),(13,12,1,26,4.00,4.00,'2010-09-01','2014-06-01'),(14,13,2,24,94.00,100.00,'2011-09-01','2015-06-01'),(15,14,3,27,97.00,100.00,'2012-09-01','2016-06-01'),(16,14,1,26,4.00,4.00,'2016-09-01','2018-06-01'),(17,15,4,24,89.00,100.00,'2017-09-01','2021-06-01'),(18,16,5,26,93.00,100.00,'2013-09-01','2017-06-01'),(19,17,1,27,87.00,100.00,'2018-09-01','2022-06-01'),(20,18,2,28,90.00,100.00,'2017-09-01','2021-06-01'),(21,19,3,24,95.00,100.00,'2016-09-01','2020-06-01'),(48,20,4,24,111.00,200.00,'2025-11-17','2025-11-24'),(60,30,5,29,100.00,110.00,'2021-09-14',NULL);
/*!40000 ALTER TABLE `education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_domain_option`
--

DROP TABLE IF EXISTS `project_domain_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_domain_option` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `project_id` bigint DEFAULT NULL,
  `domain_id` bigint DEFAULT NULL,
  `domain_option_id` bigint DEFAULT NULL,
  `curriculum_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `grade` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_project_id` (`project_id`),
  KEY `idx_curriculum_id` (`curriculum_id`),
  KEY `idx_domain_id` (`domain_id`),
  KEY `idx_domain_option_id` (`domain_option_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_grade` (`grade`),
  CONSTRAINT `project_domain_option_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`) ON DELETE SET NULL,
  CONSTRAINT `project_domain_option_ibfk_2` FOREIGN KEY (`curriculum_id`) REFERENCES `curriculum` (`id`) ON DELETE CASCADE,
  CONSTRAINT `chk_pdo_grade_range` CHECK (((`grade` >= 0) and (`grade` <= 5)))
) ENGINE=InnoDB AUTO_INCREMENT=270 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_domain_option`
--

LOCK TABLES `project_domain_option` WRITE;
/*!40000 ALTER TABLE `project_domain_option` DISABLE KEYS */;
INSERT INTO `project_domain_option` VALUES (3,2,2,7,2,3,5),(4,2,4,18,2,3,3),(5,2,3,16,2,3,3),(6,3,2,7,3,4,5),(7,3,4,19,3,4,3),(8,4,4,18,4,5,4),(9,4,3,16,4,5,4),(10,4,3,17,4,5,3),(11,5,4,18,4,5,5),(12,5,3,16,4,5,4),(13,5,2,8,4,5,4),(19,8,4,18,6,7,3),(20,8,2,7,6,7,4),(21,9,4,18,7,8,2),(22,9,3,17,7,8,3),(23,10,4,18,8,9,3),(24,10,2,8,8,9,4),(25,11,3,13,9,10,3),(26,11,3,14,9,10,3),(27,11,3,12,9,10,2),(28,12,4,18,10,11,2),(29,12,3,17,10,11,2),(30,13,4,18,11,12,3),(31,13,3,16,11,12,3),(32,14,4,19,12,13,4),(33,14,2,8,12,13,4),(34,15,4,19,12,13,4),(35,15,3,12,12,13,4),(36,15,3,13,12,13,3),(37,16,4,19,13,14,5),(38,16,4,20,13,14,4),(39,16,3,17,13,14,4),(40,17,4,20,14,15,5),(41,17,3,15,14,15,5),(42,18,4,20,14,15,5),(43,18,3,15,14,15,5),(44,18,2,7,14,15,4),(45,19,4,20,15,16,4),(46,19,3,15,15,16,3),(47,20,4,19,16,17,4),(48,20,2,8,16,17,5),(49,20,2,11,16,17,4),(50,21,4,19,16,17,4),(51,21,3,17,16,17,3),(52,22,4,19,17,18,2),(53,22,4,20,17,18,3),(54,23,3,15,18,19,3),(55,23,4,20,18,19,3),(56,23,3,17,18,19,3),(57,24,4,20,19,20,3),(58,24,2,7,19,20,4),(219,50,4,23,20,21,3),(220,50,4,22,20,21,3),(269,68,2,9,30,22,4);
/*!40000 ALTER TABLE `project_domain_option` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-18 12:49:24
