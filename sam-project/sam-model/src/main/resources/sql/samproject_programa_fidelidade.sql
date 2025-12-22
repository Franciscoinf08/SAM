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
-- Table structure for table `programa_fidelidade`
--

DROP TABLE IF EXISTS `programa_fidelidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `programa_fidelidade` (
  `idProgramaFidelidade` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `bonusMilhas` double DEFAULT NULL,
  `qtdeMilhasMes` int DEFAULT NULL,
  `duracao` int DEFAULT NULL,
  `empresa_id` int DEFAULT NULL,
  `precoMes` double DEFAULT NULL,
  `avaliacao` varchar(50) DEFAULT NULL,
  `data_expiracao` date DEFAULT NULL,
  PRIMARY KEY (`idProgramaFidelidade`),
  KEY `empresa_id` (`empresa_id`),
  CONSTRAINT `programa_fidelidade_ibfk_1` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`idEmpresa`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `programa_fidelidade`
--

LOCK TABLES `programa_fidelidade` WRITE;
/*!40000 ALTER TABLE `programa_fidelidade` DISABLE KEYS */;
INSERT INTO `programa_fidelidade` VALUES (68,'t',321,1312,1321,29,321,'Muito vantajoso','2026-01-10'),(71,'programa',123,123,123,29,123,'Muito vantajoso','2322-03-12'),(72,'Teste das notificacoes automaticas',123,123,22,29,123,'Muito vantajoso','2025-12-21');
/*!40000 ALTER TABLE `programa_fidelidade` ENABLE KEYS */;
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
