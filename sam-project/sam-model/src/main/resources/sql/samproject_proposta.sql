USE `samproject`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)

DROP TABLE IF EXISTS `proposta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

CREATE TABLE `proposta` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `idCliente` int NOT NULL,
                            `idGestor` int NOT NULL,
                            `origem` varchar(100) NOT NULL,
                            `destino` varchar(100) NOT NULL,
                            `dataIda` date NOT NULL,
                            `dataVolta` date NOT NULL,
                            `numAdultos` int NOT NULL,
                            `numCriancas` int NOT NULL,
                            `valorEmDinheiro` double NOT NULL,
                            `valorEmMilhas` bigint DEFAULT NULL,
                            `taxas` double NOT NULL,
                            `observacoes` text,
                            `status` varchar(50) NOT NULL,
                            PRIMARY KEY (`id`),
                            KEY `fk_proposta_cliente` (`idCliente`),
                            KEY `fk_proposta_gestor` (`idGestor`),
                            CONSTRAINT `fk_proposta_cliente`
                                FOREIGN KEY (`idCliente`) REFERENCES `usuarios` (`id`),
                            CONSTRAINT `fk_proposta_gestor`
                                FOREIGN KEY (`idGestor`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `proposta` WRITE;
/*!40000 ALTER TABLE `proposta` DISABLE KEYS */;
/*!40000 ALTER TABLE `proposta` ENABLE KEYS */;
UNLOCK TABLES;

-- Dump completed on 2025-12-01 00:00:00

