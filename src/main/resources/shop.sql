CREATE DATABASE  IF NOT EXISTS `shop` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `shop`;
-- MySQL dump 10.13  Distrib 5.6.11, for Win32 (x86)
--
-- Host: localhost    Database: shop
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt

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
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addresses` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `zip` varchar(10) default NULL,
  `state` varchar(20) default NULL,
  `city` varchar(20) default NULL,
  `street` varchar(30) default NULL,
  `phone` varchar(30) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (1,'610001','UKRAINE','KHARKIV','Kharkivska street 20, 2','0571234567'),(2,'650001','UKRAINE','ODESSA','Odesska street 100, 231','0631234567'),(3,'790001','UKRAINE','LVIV','Lvivska street 5/5, 23','0991234567'),(4,'56001','UKRAINE','KIEV','Kiivska street 20, 2','0977777777'),(5,'2','2','2','2','2'),(6,'2','2','2','2','2'),(7,'123','123','123','123','123'),(8,'12','12','12','12','12'),(9,'1','1','1','1','1'),(10,'123','123','123','123','123'),(11,'222','222','222','222','222'),(12,'222','222','222','222','222'),(13,'22','22','22','22','22'),(14,'6','6','6','6','6'),(15,'111111','asd','asd','asd','1111111'),(16,'111111','asd','asd','asd','1111111'),(17,'111111','asd','asd','asd','1111111'),(18,'111111','sssss','ssssss','sssss','1111111'),(19,'11111','ffff','ffff','fff','1111111'),(20,'11111','asd','as','as','11111111'),(21,'11111','as','as','as','1111111'),(22,'11111','asdfghj','asdfghj','asdfghj','1111111'),(23,'11111','as','as','as','1111111'),(24,'12','as','as','as','11'),(25,'2','2','2','2','2'),(26,'3','3','3','3','3'),(27,'1','1','1','1','1'),(28,'1','1','1','1','1');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(30) default NULL,
  `parent` int(1) unsigned default NULL,
  `root` int(1) unsigned default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Машинки на радиоуправлении',0,1),(2,'Джипы на радиоуправлении',1,0),(3,'Транспорт инерционный',1,0),(4,'Коляски детские',0,1),(5,'Трости',4,0),(6,'Прогулочные',4,0);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `date` date default NULL,
  `time` time default NULL,
  `user_id` int(10) unsigned default NULL,
  `product_id` int(10) unsigned default NULL,
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `description` varchar(1000) default NULL,
  `price` int(11) default NULL,
  `image` varchar(30) default NULL,
  `category_id` int(10) unsigned default NULL,
  PRIMARY KEY  (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Машинка Джип Тарзан на радиоуправлении 9003','Радиоуправляемый внедорожник \"Тарзан\" - это модель в масштабе 1:16, полностью готовая к эксплуатации. Внедорожник Тарзан управляется с помощью пульта дистанционного управления, который имеет рычаговую форму управления.',450,'1',2),(2,'Машина Джип Пикап 6568-310 / 9000 на радиоуправлении','Джип 6568-310/9000 – оригинальная модель внедорожника на радиоуправлении. Благодаря свободной компоновке, машину можно собирать в различных вариантах, так что это ещё и конструктор.',550,'2',2),(3,'Машинка инерционная два цвета Lamborghini JT0230','Очень качественная уменьшенная копия Lamborghini. Два цвета - Оранжевый и Черный! Масштаб 1:24. В коробке 30*16,5*12,5СМ',209,'3',3),(8,'Игровой набор 10339 Гоночный автомобтль Робби гоночная, инер-я, фигурка','- Реалистические звуки роботы двигателя во время движения;\r\n- магнитный фаркоп;\r\nВ комплекте: инерционный спортивный автомобиль,фигурка гонщика',238,'4',3),(9,'Прогулочная коляска-трость Baby TILLY Walker BT-SB-0001 (фиолетовая)','Красивая и удобная коляска-трость Baby-Tilly Walker SB-0001  – отличный вариант для летних прогулок с ребенком. Baby-Tilly Walker SB-0001 выполнена из качественных материалов и достаточно компактно складывается, не занимая много места, что очень удобно при транспортировке, переноске и хранении.',967,'1',5),(10,'Коляска - трость TILLY Rider SB-0002 МАЛИНОВЫЙ','Имеет вместительную корзинку, в которую можно складывать детские вещи, игрушки и покупки. Регулируемая спинка имеет пять различных положений, что позволяет подобрать для ребёнка наиболее удобный вариант, снизу установлена подножка, ручки управления имеют резиновые накладки. В холодную погоду спереди коляски пристёгивается чехол, он защищает ноги ребёнка от ветра и холода, капюшон опускается закрывая верхнюю часть коляски, таким образом ваш ребёнок будет полностью защищён от ветра, снега и дождя.',1232,'2',5),(11,'Коляска прогулочная CARRELLO Quattro CRL-8502 Red футкавер, съемный бампер (Красная) ','Футкавер, съемный бампер (зеленая) - на коляске установлен мягкий бампер безопасности, спереди имеется подножка с регулируемым углом наклона, под сидением расположена корзинка для вещей. Коляска прогулочная CRL-8502 имеет прочный металлический корпус, коляска складывается в виде книжки, благодаря чему не занимает в квартире много места, имеет небольшой вес, благодаря небольшим габаритным размерам вы сможете заехать в любой лифт.',1741,'1',6),(12,'Коляска прогулочная CARRELLO Quattro CRL-8502 Green футкавер, съемный бампер (Зеленый) ','Футкавер, съемный бампер (зеленая) - на коляске установлен мягкий бампер безопасности, спереди имеется подножка с регулируемым углом наклона, под сидением расположена корзинка для вещей. Коляска прогулочная CRL-8502 имеет прочный металлический корпус, коляска складывается в виде книжки, благодаря чему не занимает в квартире много места, имеет небольшой вес, благодаря небольшим габаритным размерам вы сможете заехать в любой лифт. ',1741,'2',6);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` enum('USER','MANDATOR','ADMIN') default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'USER'),(2,'MANDATOR'),(3,'ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `login` varchar(10) default NULL,
  `password` varchar(10) default NULL,
  `firstName` varchar(20) default NULL,
  `lastName` varchar(20) default NULL,
  `email` varchar(30) default NULL,
  `account` bigint(20) default NULL,
  `age` varchar(3) default NULL,
  `gender` tinyint(1) default NULL,
  `role_id` int(10) unsigned default NULL,
  `address_id` int(10) unsigned default NULL,
  `enabled` tinyint(1) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `role_id` (`role_id`),
  KEY `address_id` (`address_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `users_ibfk_2` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'1','1','FirstName1','LastName1','email1@gmail.com',111111,'20',1,1,1,1),(2,'2','2','FirstName2','LastName2','email2@gmail.com',222222,'19',0,2,2,1),(3,'3','3','FirstName3','LastName3','email3@gmail.com',333333,'30',1,3,3,1),(4,'4','4','FirstName3','FirstName3','FirstName3',324,'43',0,1,1,0),(5,'5','4','1','1','1',1,'30',1,3,9,0),(6,'123','123','123','123','123',123,'18',0,1,10,0),(7,'aaaaaa','aaaaaa','aaaa','aaaa','a@m',111111,'18',1,1,17,1),(8,'ssssss','ssssss','sssss','sssss','s@s',111111,'37',1,1,18,1),(9,'ffffff','ffffff','ffffff','fffff','f@f',111111,'18',1,1,19,1),(12,'asdfgh','123456','as','as','as@as',11111,'18',1,1,21,1),(13,'asdfghj','123456','asdfghj','asdfghj','asdfghj@asdfghj',111111,'18',1,1,22,1),(15,'11111','2','a','a','aa',11,'18',1,NULL,NULL,0),(18,'23','1','1','1','1',1,'20',1,2,27,1),(19,'24','1','1','1','1',1,'30',1,3,28,0);
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

-- Dump completed on 2015-04-15 12:06:41
