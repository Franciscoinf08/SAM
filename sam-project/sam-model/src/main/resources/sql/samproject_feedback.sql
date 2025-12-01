USE `samproject`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

CREATE TABLE `feedback` (
  `idFeedback` int NOT NULL AUTO_INCREMENT,
  `idAutor` int NOT NULL,
  `idAvaliado` int NOT NULL,
  `comentario` text,
  `nota` int DEFAULT NULL,
  PRIMARY KEY (`idDenuncia`),
KEY `fk_denuncia_denunciante` (`idDenunciante`),
KEY `fk_denuncia_denunciado` (`idDenunciado`),
CONSTRAINT `fk_denuncia_denunciante`
  FOREIGN KEY (`idDenunciante`) REFERENCES `usuarios` (`id`),
CONSTRAINT `fk_denuncia_denunciado`
  FOREIGN KEY (`idDenunciado`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

-- Dump completed on 2025-12-01 00:00:00
