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
-- Table structure for table `userinfo`
--

DROP TABLE IF EXISTS `userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userinfo` (
  `NID` int NOT NULL,
  `Username` varchar(64) NOT NULL,
  `Password` varchar(64) DEFAULT NULL,
  `Contact` varchar(45) NOT NULL,
  PRIMARY KEY (`NID`),
  UNIQUE KEY `NID_UNIQUE` (`NID`),
  UNIQUE KEY `Username_UNIQUE` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinfo`
--

LOCK TABLES `userinfo` WRITE;
/*!40000 ALTER TABLE `userinfo` DISABLE KEYS */;
INSERT INTO `userinfo` VALUES (0,'Zamir','9af15b336e6a9619928537df30b2e6a2376569fcf9d7e773eccede65606529a0','0000'),(1,'m','938db8c9f82c8cb58d3f3ef4fd250036a48d26a712753d2fde5abd03a85cabf4','01'),(11,'11','4fc82b26aecb47d2868c4efbe3581732a3e7cbcc6c2efb32062c08170a05eeb8','11'),(33,'wew','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','22'),(99,'99','8c1f1046219ddd216a023f792356ddf127fce372a72ec9b4cdac989ee5b0b455','99'),(101,'mis','07334386287751ba02a4588c1a0875dbd074a61bd9e6ab7c48d244eacd0c99e0','0101'),(123,'name','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918','231'),(222,'222','9b871512327c09ce91dd649b3f96a63b7408ef267c8cc5710114e629730cb61f','2222'),(312,'Un','865736a1c30a82dc67aba820360a01b1d9d0da5643234cd07c4d60b06eb530c5','312'),(898,'rafid','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918','89728'),(2191,'abcd','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','2191'),(9292,'n','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b','9292'),(102933,'Mihad_','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','108082'),(333333,'rahim','8d23cf6c86e834a7aa6eded54c26ce2bb2e74903538c61bdd5d2197997ab2f72','0101002'),(1234321,'mihad','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','0101'),(1234567,'bool','1234','010101'),(12341234,'ishmaam','08771a4597fc54f461513d02c42185a5b904a281816675bc6009528ca7e0c244','abcd@yahoo.com'),(538548254,'zarif264','b34017e3c9be259aadbda87929f58c9254b97e193eef8a4463a8b99089f45727','islamjarif871@gmail.com');
/*!40000 ALTER TABLE `userinfo` ENABLE KEYS */;
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
