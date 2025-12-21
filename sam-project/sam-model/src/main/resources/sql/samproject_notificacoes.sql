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
-- Table structure for table `notificacoes`
--

DROP TABLE IF EXISTS `notificacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notificacoes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) DEFAULT NULL,
  `mensagem` text,
  `dataEnvio` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `destinatario` int DEFAULT NULL,
  `lida` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `destinatario` (`destinatario`),
  CONSTRAINT `notificacoes_ibfk_1` FOREIGN KEY (`destinatario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notificacoes`
--

LOCK TABLES `notificacoes` WRITE;
/*!40000 ALTER TABLE `notificacoes` DISABLE KEYS */;
INSERT INTO `notificacoes` VALUES (11,'teste de nottificacao enviada','123','2025-12-15 10:23:08',1,1),(12,'Programa de fidelidade próximo do vencimento','Um dos seus programas de fidelidade está próximo do vencimento(3 dias).','2025-12-16 11:58:52',1,1),(13,'Programa de fidelidade cadastrado','Um novo programa de fidelidade foi cadastrado com duracao de: 123 e data de expiracao das milhas para dia: 2322-03-12','2025-12-19 15:56:19',2,1),(15,'Programa de fidelidade cadastrado','Um novo programa de fidelidade foi cadastrado com duracao de: 22 e data de expiracao das milhas para dia: 2025-12-21','2025-12-19 15:58:52',2,1),(16,'Milhas do Expirando','As milhas do programa de fidelidade Teste das notificacoes automaticasestao expirando','2025-12-19 15:59:13',2,NULL),(17,'testando na parte de mensagem o envio de email','TESTE DE ENVIO DE EMAIL','2025-12-20 13:02:46',7,0),(18,'amor eu te amo demais','mensagem importante','2025-12-20 13:49:05',8,0);
/*!40000 ALTER TABLE `notificacoes` ENABLE KEYS */;
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
