-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: sys
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `tourdetails`
--

DROP TABLE IF EXISTS `tourdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tourdetails` (
  `traveler_nid` int NOT NULL,
  `hotel_name` varchar(45) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `Destination` varchar(45) NOT NULL,
  `Total_Expenses` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tourdetails`
--

LOCK TABLES `tourdetails` WRITE;
/*!40000 ALTER TABLE `tourdetails` DISABLE KEYS */;
INSERT INTO `tourdetails` VALUES (123456,'Hotel Sunshine','2024-05-20','2024-05-22','Hawaii',1501),(1234321,'ZARIF','2024-06-01','2024-06-06','Dhaka',25000),(1234321,'ZARIF','2024-06-04','2024-06-06','Dhaka',8000),(1234321,'ZARIF','2024-06-04','2024-06-06','Dhaka',10000),(1234321,'ZARIF','2024-06-03','2024-06-06','Dhaka',12000),(1234321,'Armenia','2024-06-01','2024-06-06','Sylhet',37500),(101,'ZARIF','2024-06-01','2026-06-10','Dhaka',3695000),(1234321,'name1','2024-06-01','2024-06-06','Mymensingh',27500),(1234321,'ZARIF','2024-06-01','2024-06-03','Dhaka',8000),(1234321,'ZARIF','2024-06-01','2024-06-02','Dhaka',5000),(1234321,'Armenia','2024-06-02','2024-06-05','Sylhet',22500),(1234321,'name1','2024-06-13','2024-06-20','Mymensingh',38500),(1234321,'Armenia','2024-06-03','2024-06-05','Sylhet',4000),(1234321,'ZARIF','2024-06-02','2024-06-05','Dhaka',15000),(1234321,'ZARIF','2024-06-02','2024-06-06','Dhaka',20000),(1234321,'ZARIF','2024-06-01','2024-06-06','Dhaka',25000),(1234321,'ZARIF','2024-06-02','2024-06-05','Dhaka',12000),(1234321,'Armenia','2024-06-01','2024-06-06','Sylhet',10000),(1234321,'Armenia','2024-06-02','2024-06-05','Sylhet',70269),(1234321,'iui','2024-06-03','2024-06-12','Dhaka',40500);
/*!40000 ALTER TABLE `tourdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-02 14:13:55
