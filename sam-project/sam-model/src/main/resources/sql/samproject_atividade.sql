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
-- Table structure for table `atividade`
--

DROP TABLE IF EXISTS `atividade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `atividade` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) NOT NULL,
  `descricao` text,
  `usuario_executor_id` int NOT NULL,
  `data_hora` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `usuario_executor_id` (`usuario_executor_id`),
  CONSTRAINT `atividade_ibfk_1` FOREIGN KEY (`usuario_executor_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `atividade`
--

LOCK TABLES `atividade` WRITE;
/*!40000 ALTER TABLE `atividade` DISABLE KEYS */;
INSERT INTO `atividade` VALUES (2,'ASSOCIACAO_CLIENTE_PROGRAMA','Cliente associado ao programa de fidelidade',1,'2025-12-18 09:48:27'),(3,'ASSOCIACAO_CLIENTE_PROGRAMA','Cliente associado ao programa de fidelidade',2,'2025-12-18 09:51:19'),(4,'CADASTRO_EMPRESA','a empresa:empresa testefoi cadastrada',2,'2025-12-18 10:22:39'),(5,'CADASTRO_PROGRAMA','o programa de fidelidade: PROGRAMA TESTEfoi cadastrado na empresa: empresa teste',2,'2025-12-18 10:53:27'),(6,'EXCLUSAO_PROGRAMA','o programa de fidelidade: MUDANCAda empresa: empresa testefoi excluido',2,'2025-12-18 11:05:58'),(7,'EDICAO_EMPRESA','a empresa:teve seus dados alterados de: <br>Nome: empresa teste<br>CNPJ: 14266480000141<br>Milheiro de segurança: 123.0<br>para: <br>Nome: mudanca empresa<br>CNPJ: mudanca empresa<br>Milheiro de segurança: 321.0',2,'2025-12-18 11:06:10'),(8,'CADASTRO_PROGRAMA','o programa de fidelidade: programafoi cadastrado na empresa: Silk lima LTDA',2,'2025-12-19 10:07:23'),(9,'EDICAO_PROGRAMA','o programa de fidelidade: programada empresa: Silk lima LTDAfoi alterado de: <br><br>Nome: programa<br>Bonus de milhas: 123.0<br>Quantidade de milhas por mes: 123<br>Duracao: 123<br>Preco Mensal: 123.0<br>Data de expiracao das milhas: 2026-01-24<br><br>para: <br><br>Nome: programa<br>Bonus de milhas: 123.0<br>Quantidade de milhas por mes: 123<br>Duracao: 123<br>Preco Mensal: 123.0<br>Data de expiracao das milhas: 2026-01-24',2,'2025-12-19 10:40:26'),(10,'EDICAO_PROGRAMA','o programa de fidelidade: programada empresa: Silk lima LTDAfoi alterado de: <br><br>Nome: programa<br>Bonus de milhas: 123.0<br>Quantidade de milhas por mes: 123<br>Duracao: 123<br>Preco Mensal: 123.0<br>Data de expiracao das milhas: 2026-01-24<br><br>para: <br><br>Nome: programa<br>Bonus de milhas: 123.0<br>Quantidade de milhas por mes: 123<br>Duracao: 123<br>Preco Mensal: 123.0<br>Data de expiracao das milhas: 2026-01-31',2,'2025-12-19 10:40:35'),(11,'EXCLUSAO_PROGRAMA','o programa de fidelidade: programada empresa: Silk lima LTDAfoi excluido',2,'2025-12-19 10:41:29'),(12,'CADASTRO_PROGRAMA','o programa de fidelidade: programafoi cadastrado na empresa: Silk lima LTDA',2,'2025-12-19 15:56:19'),(13,'TRANSACAO_COMPRA','Houve uma transacao de COMPRApelo Gestor 2',2,'2025-12-19 15:56:53'),(14,'TRANSACAO_VENDA','Houve uma transacao de VENDApelo Gestor 2',2,'2025-12-19 15:57:08'),(15,'ALTERACAO_PERFIL','o perfil de: 2foi alterado<br>Antigo: <br>Nome: Carlos<br>cpf: 17996278610<br>Email: carlos@gmail.com<br>Novo: <br>Nome: Carlos<br>cpf: 17996278610<br>Email: carlos@gmail.com',2,'2025-12-19 15:57:29'),(16,'ALTERACAO_PERFIL','o perfil de: 2foi alterado<br>Antigo: <br>Nome: Carlos<br>cpf: 17996278610<br>Email: carlos@gmail.com<br>Novo: <br>Nome: 123<br>cpf: 17996278610<br>Email: carlos@gmail.com',2,'2025-12-19 15:57:46'),(17,'ALTERACAO_PERFIL','o perfil de: 2foi alterado<br>Antigo: <br>Nome: 123<br>cpf: 17996278610<br>Email: carlos@gmail.com<br>Novo: <br>Nome: carlos<br>cpf: 17996278610<br>Email: carlos@gmail.com',2,'2025-12-19 15:57:53'),(18,'ALTERACAO_PERFIL','o perfil de: 2foi alterado<br>Antigo: <br>Nome: carlos<br>cpf: 17996278610<br>Email: carlos@gmail.com<br>Novo: <br>Nome: carlos<br>cpf: 17996278610<br>Email: carlos@gmail.com',2,'2025-12-19 15:57:57'),(19,'CADASTRO_PROGRAMA','o programa de fidelidade: Teste das notificacoes automaticasfoi cadastrado na empresa: Silk lima LTDA',2,'2025-12-19 15:58:52'),(20,'ASSOCIACAO_CLIENTE_PROGRAMA','o usuario: 1 foi associado ao programa: 72',2,'2025-12-19 16:17:39'),(21,'DESASSOCIACAO_CLIENTE_PROGRAMA','o usuario: 1 foi desassociado ao programa: 72',1,'2025-12-19 16:17:41'),(22,'CADASTRO_USUARIO','O usuario: yasmim foi cadastrado ao sistema',8,'2025-12-20 13:48:18'),(23,'CADASTRO_EMPRESA','A empresa 12312 foi cadastrada',2,'2025-12-20 14:43:25'),(24,'SOLICITACAO_ASSOCIACAO','o usuario 2 enviou um pedido de associacao como cliente para o usuario 1',2,'2025-12-21 11:35:58'),(25,'ACEITAR_SOLICITACAO_ASSOCIAR','o usuario 1 aceitou a solicitacao de associacao do 2',2,'2025-12-21 11:36:44'),(26,'SOLICITACAO_DESASSOCIACAO','o usuario 2 enviou um pedido de desassociacao como cliente para o usuario 1',2,'2025-12-21 11:38:23'),(27,'ACEITAR_SOLICITACAO_DESASSOCIAR','o usuario 1 aceitou a solicitacao de desassociacao do 2',2,'2025-12-21 11:38:40'),(28,'SOLICITACAO_DESASSOCIACAO','o usuario 2 enviou um pedido de desassociacao como cliente para o usuario 1',2,'2025-12-21 11:38:42'),(29,'SOLICITACAO_DESASSOCIACAO','o usuario 2 enviou um pedido de desassociacao como cliente para o usuario 1',2,'2025-12-21 11:38:43'),(30,'SOLICITACAO_DESASSOCIACAO','o usuario 2 enviou um pedido de desassociacao como cliente para o usuario 1',2,'2025-12-21 11:38:47'),(31,'SOLICITACAO_DESASSOCIACAO','o usuario 2 enviou um pedido de desassociacao como cliente para o usuario 1',2,'2025-12-21 11:38:48'),(32,'SOLICITACAO_DESASSOCIACAO','o usuario 2 enviou um pedido de desassociacao como cliente para o usuario 1',2,'2025-12-21 11:38:48'),(33,'SOLICITACAO_DESASSOCIACAO','o usuario 2 enviou um pedido de desassociacao como cliente para o usuario 1',2,'2025-12-21 11:38:48'),(34,'ACEITAR_SOLICITACAO_DESASSOCIAR','o usuario 1 aceitou a solicitacao de desassociacao do 2',2,'2025-12-21 11:39:08'),(35,'SOLICITACAO_ASSOCIACAO','o usuario 1 enviou um pedido de associacao como cliente para o usuario 1',1,'2025-12-21 11:39:25'),(36,'SOLICITACAO_DESASSOCIACAO','o usuario 2 enviou um pedido de desassociacao como cliente para o usuario 4',2,'2025-12-21 19:41:07'),(37,'SOLICITACAO_DESASSOCIACAO','o usuario 2 enviou um pedido de desassociacao como cliente para o usuario 8',2,'2025-12-21 19:41:14'),(38,'ACEITAR_SOLICITACAO_DESASSOCIAR','o usuario 8 aceitou a solicitacao de desassociacao do 2',2,'2025-12-21 19:41:29'),(39,'RECUSAR_ASSOCIACAO_CLIENTE','o usuario 1 recusou a solicitacao de associacao do 1',1,'2025-12-21 19:45:01'),(40,'SOLICITACAO_ASSOCIACAO','o usuario 2 enviou um pedido de associacao como cliente para o usuario 1',2,'2025-12-21 19:45:49'),(41,'ACEITAR_SOLICITACAO_ASSOCIAR','o usuario 1 aceitou a solicitacao de associacao do 2',2,'2025-12-21 19:46:01'),(42,'SOLICITACAO_DESASSOCIACAO','o usuario 2 enviou um pedido de desassociacao como cliente para o usuario 1',2,'2025-12-21 19:46:40'),(43,'ACEITAR_SOLICITACAO_DESASSOCIAR','o usuario 1 aceitou a solicitacao de desassociacao do 2',2,'2025-12-21 19:47:00'),(44,'SOLICITACAO_DESASSOCIACAO','o usuario 2 enviou um pedido de desassociacao como cliente para o usuario 7',2,'2025-12-21 19:49:54'),(45,'ACEITAR_SOLICITACAO_DESASSOCIAR','o usuario 7 aceitou a solicitacao de desassociacao do 2',2,'2025-12-21 19:50:11'),(46,'ACEITAR_SOLICITACAO_DESASSOCIAR','o usuario 7 aceitou a solicitacao de desassociacao do 2',2,'2025-12-21 19:50:46'),(47,'ACEITAR_SOLICITACAO_DESASSOCIAR','o usuario 7 aceitou a solicitacao de desassociacao do 2',2,'2025-12-21 19:53:11'),(48,'ACEITAR_SOLICITACAO_DESASSOCIAR','o usuario 7 aceitou a solicitacao de desassociacao do 2',2,'2025-12-21 19:53:12'),(49,'ACEITAR_SOLICITACAO_DESASSOCIAR','o usuario 7 aceitou a solicitacao de desassociacao do 2',2,'2025-12-21 19:53:12'),(50,'SOLICITACAO_DESASSOCIACAO','o usuario 2 enviou um pedido de desassociacao como cliente para o usuario 4',2,'2025-12-21 19:53:33'),(51,'SOLICITACAO_ASSOCIACAO','o usuario 2 enviou um pedido de associacao como cliente para o usuario 1',2,'2025-12-21 20:07:16'),(52,'RECUSAR_ASSOCIACAO_CLIENTE','o usuario 1 recusou a solicitacao de associacao do 2',1,'2025-12-21 20:09:26'),(53,'RECUSAR_DESASOCIACAO_CLIENTE','o usuario 4 recusou a solicitacao de desassociacao do 2',4,'2025-12-21 20:10:15'),(54,'RECUSAR_DESASOCIACAO_CLIENTE','o usuario 4 recusou a solicitacao de desassociacao do 2',4,'2025-12-21 20:10:16'),(55,'SOLICITACAO_ASSOCIACAO','o usuario 2 enviou um pedido de associacao como cliente para o usuario 1',2,'2025-12-21 20:34:35'),(56,'SOLICITACAO_DESASSOCIACAO','o usuario 2 enviou um pedido de desassociacao como cliente para o usuario 4',2,'2025-12-21 20:34:39'),(57,'SOLICITACAO_ASSOCIACAO','o usuario 2 enviou um pedido de associacao como cliente para o usuario 7',2,'2025-12-21 20:34:42'),(58,'SOLICITACAO_ASSOCIACAO','o usuario 2 enviou um pedido de associacao como cliente para o usuario 8',2,'2025-12-21 20:34:47'),(59,'TORNAR_CLIENTE','O usuario: 5 voltou a ser cliente',3,'2025-12-21 20:37:52');
/*!40000 ALTER TABLE `atividade` ENABLE KEYS */;
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
