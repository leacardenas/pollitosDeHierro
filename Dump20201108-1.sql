CREATE DATABASE  IF NOT EXISTS `pollitos_de_hierro` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pollitos_de_hierro`;
-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: pollitos_de_hierro
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

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
-- Table structure for table `pdh_geolocations`
--

DROP TABLE IF EXISTS `pdh_geolocations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pdh_geolocations` (
  `pdh_geolocations_id` int(11) NOT NULL AUTO_INCREMENT,
  `pdh_geolocations_created_by` int(11) DEFAULT NULL,
  `pdh_geolocations_updated_by` int(11) DEFAULT NULL,
  `pdh_geolocations_created_date` timestamp NULL DEFAULT NULL,
  `pdh_geolocations_updated_date` timestamp NULL DEFAULT NULL,
  `pdh_geolocations_name` text,
  `pdh_geolocations_parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`pdh_geolocations_id`),
  KEY `geo_fk_created_by_idx` (`pdh_geolocations_created_by`),
  KEY `geo_fk_updated_by_idx` (`pdh_geolocations_updated_by`),
  CONSTRAINT `geo_fk_created_by` FOREIGN KEY (`pdh_geolocations_created_by`) REFERENCES `pdh_user` (`pdh_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `geo_fk_updated_by` FOREIGN KEY (`pdh_geolocations_updated_by`) REFERENCES `pdh_user` (`pdh_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pdh_geolocations`
--

LOCK TABLES `pdh_geolocations` WRITE;
/*!40000 ALTER TABLE `pdh_geolocations` DISABLE KEYS */;
/*!40000 ALTER TABLE `pdh_geolocations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pdh_headquarter`
--

DROP TABLE IF EXISTS `pdh_headquarter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pdh_headquarter` (
  `pdh_headquarter_id` int(11) NOT NULL AUTO_INCREMENT,
  `pdh_headquarter_code` text,
  `pdh_headquarter_name` text,
  `pdh_headquarter_created_by` int(11) DEFAULT NULL,
  `pdh_headquarter_updated_by` int(11) DEFAULT NULL,
  `pdh_headquarter_created_date` timestamp NULL DEFAULT NULL,
  `pdh_headquarter_updated_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pdh_headquarter_id`),
  KEY `headquarter_fk_created_by_idx` (`pdh_headquarter_created_by`),
  KEY `headquarter_fk_updated_by_idx` (`pdh_headquarter_updated_by`),
  CONSTRAINT `headquarter_fk_created_by` FOREIGN KEY (`pdh_headquarter_created_by`) REFERENCES `pdh_user` (`pdh_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `headquarter_fk_updated_by` FOREIGN KEY (`pdh_headquarter_updated_by`) REFERENCES `pdh_user` (`pdh_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pdh_headquarter`
--

LOCK TABLES `pdh_headquarter` WRITE;
/*!40000 ALTER TABLE `pdh_headquarter` DISABLE KEYS */;
/*!40000 ALTER TABLE `pdh_headquarter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pdh_kid`
--

DROP TABLE IF EXISTS `pdh_kid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pdh_kid` (
  `pdh_kid_id` int(11) NOT NULL AUTO_INCREMENT,
  `pdh_kid_code` text NOT NULL,
  `pdh_kid_name` text NOT NULL,
  `pdh_kid_surname` text,
  `pdh_kid_age` int(11) DEFAULT NULL,
  `pdh_kid_birth_day` timestamp NULL DEFAULT NULL,
  `pdh_kid_tshirt_size` int(11) DEFAULT NULL,
  `pdh_kid_pants_size` int(11) DEFAULT NULL,
  `pdh_kid_footware_size` double DEFAULT NULL,
  `pdh_kid_diaper_size` int(11) DEFAULT NULL,
  `pdh_kid_diagnose` varchar(45) DEFAULT NULL,
  `pdh_kid_setback` tinyint(1) DEFAULT NULL,
  `pdh_kid_current_diagnose` varchar(45) DEFAULT NULL,
  `pdh_kid_attached_epicrisis` tinyint(1) DEFAULT NULL,
  `pdh_kid_headquarters` int(11) DEFAULT NULL,
  `pdh_kid_geolocation` int(11) DEFAULT NULL,
  `pdh_kid_number_of_siblings` int(11) DEFAULT NULL,
  `pdh_kid_last_basket_received` varchar(45) DEFAULT NULL,
  `pdh_kid_number_of_people_living_in_house` int(11) DEFAULT NULL,
  `pdh_kid_created_by` int(11) DEFAULT NULL,
  `pdh_kid_updated_by` int(11) DEFAULT NULL,
  `pdh_kid_created_date` timestamp NULL DEFAULT NULL,
  `pdh_kid_updated_date` timestamp NULL DEFAULT NULL,
  `pdh_kid_helping_people` int(11) DEFAULT NULL,
  PRIMARY KEY (`pdh_kid_id`),
  KEY `kid_fk_created_by_idx` (`pdh_kid_created_by`),
  KEY `kid_fk_updated_by_idx` (`pdh_kid_updated_by`),
  KEY `kid_fk_geolocation_idx` (`pdh_kid_geolocation`),
  KEY `kid_fk_headquarter_idx` (`pdh_kid_headquarters`),
  CONSTRAINT `kid_fk_created_by` FOREIGN KEY (`pdh_kid_created_by`) REFERENCES `pdh_user` (`pdh_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `kid_fk_geolocation` FOREIGN KEY (`pdh_kid_geolocation`) REFERENCES `pdh_geolocations` (`pdh_geolocations_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `kid_fk_headquarter` FOREIGN KEY (`pdh_kid_headquarters`) REFERENCES `pdh_headquarter` (`pdh_headquarter_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `kid_fk_updated_by` FOREIGN KEY (`pdh_kid_updated_by`) REFERENCES `pdh_user` (`pdh_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pdh_kid`
--

LOCK TABLES `pdh_kid` WRITE;
/*!40000 ALTER TABLE `pdh_kid` DISABLE KEYS */;
INSERT INTO `pdh_kid` VALUES (1,'001','David','Ugalde',8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'002','Elmi','Rador',5,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `pdh_kid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pdh_role`
--

DROP TABLE IF EXISTS `pdh_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pdh_role` (
  `pdh_role_id` int(11) NOT NULL,
  `pdh_role_name` text,
  `pdh_role_created_by` int(11) DEFAULT NULL,
  `pdh_role_created_date` timestamp NULL DEFAULT NULL,
  `pdh_role_updated_by` int(11) DEFAULT NULL,
  `pdh_role_updated_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`pdh_role_id`),
  KEY `role_fk_created_by_idx` (`pdh_role_created_by`),
  KEY `role_fk_updated_by_idx` (`pdh_role_updated_by`),
  CONSTRAINT `role_fk_created_by` FOREIGN KEY (`pdh_role_created_by`) REFERENCES `pdh_user` (`pdh_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `role_fk_updated_by` FOREIGN KEY (`pdh_role_updated_by`) REFERENCES `pdh_user` (`pdh_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pdh_role`
--

LOCK TABLES `pdh_role` WRITE;
/*!40000 ALTER TABLE `pdh_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `pdh_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pdh_user`
--

DROP TABLE IF EXISTS `pdh_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pdh_user` (
  `pdh_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `pdh_user_name` text,
  `pdh_user_surname` text,
  `pdh_user_username` text,
  `pdh_user_created_by` int(11) DEFAULT NULL,
  `pdh_user_updated_by` int(11) DEFAULT NULL,
  `pdh_user_created_date` timestamp NULL DEFAULT NULL,
  `pdh_user_updated_date` timestamp NULL DEFAULT NULL,
  `pdh_user_role` int(11) DEFAULT NULL,
  PRIMARY KEY (`pdh_user_id`),
  KEY `user_created_by_idx` (`pdh_user_created_by`),
  KEY `user_updated_by_idx` (`pdh_user_updated_by`),
  KEY `user_fk_role_idx` (`pdh_user_role`),
  CONSTRAINT `user_created_by` FOREIGN KEY (`pdh_user_created_by`) REFERENCES `pdh_user` (`pdh_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_fk_role` FOREIGN KEY (`pdh_user_role`) REFERENCES `pdh_role` (`pdh_role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_fk_updated_by` FOREIGN KEY (`pdh_user_updated_by`) REFERENCES `pdh_user` (`pdh_user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pdh_user`
--

LOCK TABLES `pdh_user` WRITE;
/*!40000 ALTER TABLE `pdh_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `pdh_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-08 13:18:27
