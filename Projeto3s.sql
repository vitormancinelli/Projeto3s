CREATE DATABASE  IF NOT EXISTS `projeto3s` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `projeto3s`;
-- MySQL dump 10.13  Distrib 5.7.28, for Win64 (x86_64)
--
-- Host: localhost    Database: projeto3s
-- ------------------------------------------------------
-- Server version	5.7.28-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra` (
  `id` bigint(31) NOT NULL AUTO_INCREMENT,
  `parceiro` bigint(31) NOT NULL,
  `periodo` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_compra_parceiro_idx` (`parceiro`),
  CONSTRAINT `fk_compra_parceiro` FOREIGN KEY (`parceiro`) REFERENCES `parceiro` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
INSERT INTO `compra` VALUES (1,1,'2021-03-11 20:00:00'),(2,3,'2021-03-11 20:01:00'),(5,1,'2021-03-11 20:10:00'),(7,1,'2021-04-11 10:10:00'),(8,1,'2021-10-11 21:01:00'),(9,3,'2021-05-11 21:01:00'),(10,1,'2021-04-11 10:10:00'),(11,1,'2021-04-11 10:10:00'),(12,1,'2021-04-11 10:10:00');
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `icompra`
--

DROP TABLE IF EXISTS `icompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `icompra` (
  `id` bigint(31) NOT NULL AUTO_INCREMENT,
  `compra` bigint(31) NOT NULL,
  `item` bigint(31) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_icompra_compra_idx` (`compra`),
  KEY `fk_icompra_item_idx` (`item`),
  CONSTRAINT `fk_icompra_compra` FOREIGN KEY (`compra`) REFERENCES `compra` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_icompra_item` FOREIGN KEY (`item`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `icompra`
--

LOCK TABLES `icompra` WRITE;
/*!40000 ALTER TABLE `icompra` DISABLE KEYS */;
INSERT INTO `icompra` VALUES (2,2,6,5,5),(3,8,6,13,6.99),(4,5,6,5,5.99);
/*!40000 ALTER TABLE `icompra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` bigint(31) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `descricao` varchar(60) NOT NULL,
  `valor` double NOT NULL,
  `estoque` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (2,'abacaxi','fruta',5,10),(3,'perâ','fruta',3,18),(4,'limão','fruta',4,6),(5,'perâ','fruta',4,6),(6,'melão','fruta',15,25);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ivenda`
--

DROP TABLE IF EXISTS `ivenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ivenda` (
  `id` bigint(31) NOT NULL AUTO_INCREMENT,
  `venda` bigint(31) NOT NULL,
  `item` bigint(31) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ivenda_venda_idx` (`venda`),
  KEY `fk_ivenda_item_idx` (`item`),
  CONSTRAINT `fk_ivenda_item` FOREIGN KEY (`item`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ivenda_venda` FOREIGN KEY (`venda`) REFERENCES `venda` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ivenda`
--

LOCK TABLES `ivenda` WRITE;
/*!40000 ALTER TABLE `ivenda` DISABLE KEYS */;
INSERT INTO `ivenda` VALUES (8,13,2,4,2);
/*!40000 ALTER TABLE `ivenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meio_pagamento`
--

DROP TABLE IF EXISTS `meio_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meio_pagamento` (
  `id` bigint(31) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meio_pagamento`
--

LOCK TABLES `meio_pagamento` WRITE;
/*!40000 ALTER TABLE `meio_pagamento` DISABLE KEYS */;
INSERT INTO `meio_pagamento` VALUES (1,'dinheiro'),(2,'cartão de crédito'),(3,'cartão de débito'),(4,'cheque');
/*!40000 ALTER TABLE `meio_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parceiro`
--

DROP TABLE IF EXISTS `parceiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parceiro` (
  `id` bigint(31) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `documento` varchar(14) NOT NULL,
  `endereco` varchar(60) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parceiro`
--

LOCK TABLES `parceiro` WRITE;
/*!40000 ALTER TABLE `parceiro` DISABLE KEYS */;
INSERT INTO `parceiro` VALUES (1,'Marcelo','25896314719','Rua da Mooca','marceloCunha@gmail.com'),(3,'José','14795135759','Avenida Paes de Barros','joséSilva@hotmail.com');
/*!40000 ALTER TABLE `parceiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` bigint(31) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'análise'),(2,'em produção'),(3,'proto para envio'),(4,'enviado'),(5,'recebido'),(6,'encerrado'),(8,'recusado');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venda` (
  `id` bigint(31) NOT NULL AUTO_INCREMENT,
  `parceiro` bigint(31) NOT NULL,
  `periodo` datetime NOT NULL,
  `status` bigint(31) NOT NULL,
  `endereco` varchar(60) NOT NULL,
  `pagamento` bigint(31) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_venda_parceiro_idx` (`parceiro`),
  KEY `fk_venda_status_idx` (`status`),
  KEY `fk_venda_pagamento` (`pagamento`),
  CONSTRAINT `fk_venda_pagamento` FOREIGN KEY (`pagamento`) REFERENCES `meio_pagamento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_parceiro` FOREIGN KEY (`parceiro`) REFERENCES `parceiro` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_status` FOREIGN KEY (`status`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` VALUES (13,1,'2021-03-12 21:00:00',8,'av Dom Pedro',1);
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'projeto3s'
--

--
-- Dumping routines for database 'projeto3s'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-14 14:04:37
