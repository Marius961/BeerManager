CREATE DATABASE  IF NOT EXISTS `productmanager` /*!40100 DEFAULT CHARACTER SET cp1251 */;
USE `productmanager`;
-- MySQL dump 10.13  Distrib 5.7.23, for Win64 (x86_64)
--
-- Host: localhost    Database: productmanager
-- ------------------------------------------------------
-- Server version	5.7.23-log

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
-- Table structure for table `group_authorities`
--

DROP TABLE IF EXISTS `group_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_authorities` (
  `group_id` int(11) NOT NULL,
  `authority` varchar(50) CHARACTER SET utf8 NOT NULL,
  KEY `fk_id_idx` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_authorities`
--

LOCK TABLES `group_authorities` WRITE;
/*!40000 ALTER TABLE `group_authorities` DISABLE KEYS */;
INSERT INTO `group_authorities` VALUES (1,'ROLE_USER'),(2,'ROLE_USER'),(2,'ROLE_ADMIN');
/*!40000 ALTER TABLE `group_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_members`
--

DROP TABLE IF EXISTS `group_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_members` (
  `id` int(11) DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `group_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_members`
--

LOCK TABLES `group_members` WRITE;
/*!40000 ALTER TABLE `group_members` DISABLE KEYS */;
INSERT INTO `group_members` VALUES (50,'marius96',2),(NULL,'user1',1),(NULL,'user2',1),(NULL,'user3',1);
/*!40000 ALTER TABLE `group_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL,
  `group_name` varchar(50) CHARACTER SET utf8mb4 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'users'),(2,'admins');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `id` int(11) DEFAULT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `volume` decimal(10,0) NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `fk_order_id_order_idx` (`id`),
  KEY `fk_product_id_product_idx` (`product_id`),
  CONSTRAINT `fk_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (85,48,1,20),(86,49,1,50),(87,49,3,60),(88,50,1,25),(89,50,3,40),(90,51,1,50),(91,51,3,50),(92,52,1,20),(93,53,1,5),(94,54,1,35),(95,54,3,50),(96,54,39,5),(97,54,40,40),(98,54,41,20),(99,54,42,10),(100,54,43,35),(101,55,1,20),(102,56,1,20),(103,57,1,25),(104,58,1,20),(105,59,1,20),(106,60,1,20),(107,61,1,20),(108,62,1,20),(109,63,1,20),(110,64,1,20),(111,65,1,20),(112,66,1,20),(113,67,1,5),(114,68,1,20),(115,69,1,20),(116,69,39,25),(117,69,40,5),(118,69,42,10),(119,69,43,40),(120,70,1,35),(NULL,71,3,50),(NULL,72,39,50),(NULL,72,43,30),(NULL,73,39,50),(NULL,74,3,50),(NULL,75,3,50),(NULL,76,39,50),(NULL,77,3,50);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_stats`
--

DROP TABLE IF EXISTS `order_stats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_stats` (
  `id` int(11) NOT NULL,
  `stat_name` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_stats`
--

LOCK TABLES `order_stats` WRITE;
/*!40000 ALTER TABLE `order_stats` DISABLE KEYS */;
INSERT INTO `order_stats` VALUES (0,'not specified');
/*!40000 ALTER TABLE `order_stats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `creation_date` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `exec_date` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `creation_time` time NOT NULL,
  `user_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL DEFAULT '1',
  `price` decimal(10,0) DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (48,'','','13:57:13',52,0,0,''),(49,'','','14:16:00',52,0,0,''),(50,'','','10:12:27',52,0,0,'Тільки свіже і в дубових бочках замість металевих.'),(51,'','','10:20:05',52,0,0,''),(52,'','','10:20:12',52,0,0,''),(53,'','','10:20:22',52,0,0,''),(54,'','','18:00:18',52,0,0,''),(55,'','','10:38:30',52,0,0,''),(56,'','','10:39:49',52,0,0,''),(57,'','','10:41:16',52,0,0,''),(58,'','','10:42:33',52,0,0,''),(59,'','','10:43:32',52,0,0,''),(60,'','','10:44:53',52,0,0,''),(61,'','','10:47:08',52,0,0,''),(62,'','','10:48:30',52,0,0,''),(63,'','','10:50:46',52,0,0,''),(64,'','','11:14:56',52,0,0,''),(65,'','','11:33:46',52,0,0,''),(66,'','','11:35:43',52,0,0,''),(67,'','','11:48:11',52,0,0,''),(68,'','','12:01:23',52,0,0,''),(69,'','','12:05:28',52,0,0,''),(70,'','','12:14:25',52,0,0,''),(71,'2018-09-08','2018-09-11','19:13:10',54,0,0,''),(72,'2018-09-08','2018-09-10','19:13:31',54,0,0,''),(73,'2018-09-08','2018-09-14','19:21:08',55,0,0,'????'),(74,'2018-09-08','2018-09-15','19:27:20',55,0,0,'????'),(75,'2018-09-08','2018-09-09','19:31:33',54,0,0,''),(76,'2018-09-08','2018-09-09','19:31:48',54,0,0,'??????????????'),(77,'2018-09-08','2018-09-09','19:36:25',54,0,0,'Текст\r\n');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` text CHARACTER SET utf8mb4,
  `series` text CHARACTER SET utf8mb4,
  `token` text CHARACTER SET utf8mb4,
  `last_used` text CHARACTER SET utf8mb4
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `description` varchar(256) CHARACTER SET utf8mb4 DEFAULT NULL,
  `image` bit(1) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Чернігівське','Біле пиво',_binary '\0',0),(2,'Львівське','Пиво львівського розливу',_binary '\0',0),(3,'Garage Lemon','Освіжаючий легкий смак лимонів',_binary '\0',1),(39,'Львівське Роберт Домс Gold','',_binary '\0',1),(40,'BeerMix Вишня','Мікс пива зі смаком вишні',_binary '\0',1),(41,'BeerMux Лимон','Мікс пива зі смаком лимонного чаю',_binary '\0',1),(42,'Garage Lemon Tee','Класичний смак \"Гараж\" зі смаком лимону',_binary '\0',1),(43,'Garage Anti Compote','Гараж зі смаком бабусиного компоту',_binary '\0',1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  `email` varchar(32) CHARACTER SET utf8mb4 NOT NULL,
  `tel_number` varchar(10) CHARACTER SET utf8mb4 NOT NULL,
  `full_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `company_name` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `company_addr` varchar(128) CHARACTER SET utf8mb4 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=cp1251;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (52,'marius96','$2a$11$qQVBM7N.NK9Iqy81mn6vge8CNwnhr9.sxtHbpzieQRW98/MiyjqKO',1,'marius.shiman@gmail.com','0673288123','Шіман Маріус Іванович','Галичина Табак','Максимовича 15А'),(53,'test','test',1,'asdweq','0678488484','asdqwewqe','adweqeqeqwe','qweqwrqweqwrqwe'),(54,'user1','$2a$11$4WseiJEV/ZmE0lmjCzHZHOHmSkDaq3ALgxGO/fumFvGRRi2XcynhW',1,'user@gmail.com','0678455787','?????? ?????? ????????','???? ?? ??????','????????? 35?'),(55,'user2','$2a$11$82eQob2WlL7iEEjoFxFpiehbefNaI7qvt29/xDmL0jzcbSl/u59ku',1,'user2@gmail.com','0678844848','?????? ??????????','???? ???? 2','??????????? 22'),(56,'user3','$2a$11$3UA7qtCIQsOo29dIgbX9g.zZ5.zLMkeusO6XeS9zQhenQ7OSHKnbe',1,'test@gmail.com','0678842848','Користувач 2','Тест','Тест 2222');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-08 19:53:19
