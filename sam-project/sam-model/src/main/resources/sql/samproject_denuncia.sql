-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: samproject
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `denuncia`
--

DROP TABLE IF EXISTS `denuncia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `denuncia` (
  `idDenuncia` int NOT NULL AUTO_INCREMENT,
  `idDenunciante` int NOT NULL,
  `idDenunciado` int NOT NULL,
  `motivo` varchar(255) NOT NULL,
  `detalhes` text,
  `status` varchar(20) NOT NULL DEFAULT 'ABERTA',
  PRIMARY KEY (`idDenuncia`),
  KEY `fk_denuncia_denunciante` (`idDenunciante`),
  KEY `fk_denuncia_denunciado` (`idDenunciado`),
  CONSTRAINT `fk_denuncia_denunciado` FOREIGN KEY (`idDenunciado`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `fk_denuncia_denunciante` FOREIGN KEY (`idDenunciante`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `denuncia`
--

LOCK TABLES `denuncia` WRITE;
/*!40000 ALTER TABLE `denuncia` DISABLE KEYS */;
INSERT INTO `denuncia` VALUES (1,2,1,'Fraude ou má conduta','123','ABERTA'),(2,2,1,'Fraude ou má conduta','123','ABERTA'),(3,2,1,'Fraude ou má conduta','123','ABERTA'),(4,2,1,'Fraude ou má conduta','123','ABERTA'),(5,2,1,'Fraude ou má conduta','123','ABERTA'),(6,2,1,'Spam ou propaganda','123','ABERTA'),(7,2,1,'Spam ou propaganda','123','ABERTA'),(8,2,1,'Spam ou propaganda','123','ABERTA'),(9,2,2,'Fraude ou má conduta','123','ABERTA'),(10,2,2,'Fraude ou má conduta','123','ABERTA'),(11,2,2,'Fraude ou má conduta','123','ABERTA'),(12,2,2,'Fraude ou má conduta','123','ABERTA'),(13,2,2,'Fraude ou má conduta','123','ABERTA'),(14,2,2,'Fraude ou má conduta','123','ABERTA'),(15,2,2,'Fraude ou má conduta','123','ABERTA'),(16,2,1,'Outro','11','ABERTA');
/*!40000 ALTER TABLE `denuncia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-21 20:54:37
