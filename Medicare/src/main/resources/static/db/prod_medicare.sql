-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: visharya_medicare_db
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `appointment_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) DEFAULT NULL,
  `referred_by_doctor_id` int(11) DEFAULT NULL,
  `service_type_id` int(11) DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `is_modified` tinyint(1) DEFAULT '0',
  `is_cancled` tinyint(1) DEFAULT '0',
  `token` int(11) DEFAULT '1',
  `date_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_date_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(20) DEFAULT NULL,
  `slot` int(11) DEFAULT '1',
  PRIMARY KEY (`appointment_id`),
  KEY `fk_patient_id` (`patient_id`),
  KEY `fk_referred_by_doctor_id` (`referred_by_doctor_id`),
  KEY `fk_service_type_id` (`service_type_id`),
  KEY `fk_created_by_user_id` (`created_by_user_id`),
  CONSTRAINT `FK4apif2ewfyf14077ichee8g06` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`),
  CONSTRAINT `FK525s7ulw6y3ohbau7tovbl837` FOREIGN KEY (`created_by_user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKks95bqub7kirixp2ww0axqjap` FOREIGN KEY (`referred_by_doctor_id`) REFERENCES `doctor` (`doctor_id`),
  CONSTRAINT `fk_created_by_user_id` FOREIGN KEY (`created_by_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_patient_id` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_referred_by_doctor_id` FOREIGN KEY (`referred_by_doctor_id`) REFERENCES `doctor` (`doctor_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_service_type_id` FOREIGN KEY (`service_type_id`) REFERENCES `service_type` (`service_type_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,1,1,1,2,0,0,0,123456,'2017-05-25 14:56:59','2017-05-25 14:56:59','1',1),(2,1,1,2,2,0,0,0,123434,'2017-05-25 14:56:59','2017-05-25 14:56:59','1',1),(3,2,2,1,2,0,0,0,123578,'2017-05-25 14:56:59','2017-05-25 14:56:59','1',2),(4,3,4,4,2,0,0,0,234555,'2017-05-25 14:56:59','2017-05-25 14:56:59','1',2),(5,4,3,2,2,0,0,0,234344,'2017-05-25 14:56:59','2017-05-25 14:56:59','1',3);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment_services`
--

DROP TABLE IF EXISTS `appointment_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment_services` (
  `appointment_id` int(11) NOT NULL,
  `service_type_id` int(11) NOT NULL,
  PRIMARY KEY (`appointment_id`,`service_type_id`),
  KEY `FK_con_app_ser2` (`service_type_id`),
  CONSTRAINT `FK72x43d7p1loquoy930j7okshl` FOREIGN KEY (`service_type_id`) REFERENCES `service_type` (`service_type_id`),
  CONSTRAINT `FK_con_app_ser1` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`appointment_id`),
  CONSTRAINT `FK_con_app_ser2` FOREIGN KEY (`service_type_id`) REFERENCES `service_type` (`service_type_id`),
  CONSTRAINT `FKkv6gwfscv4td54g96ra0p0gn0` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`appointment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_services`
--

LOCK TABLES `appointment_services` WRITE;
/*!40000 ALTER TABLE `appointment_services` DISABLE KEYS */;
INSERT INTO `appointment_services` VALUES (1,1),(2,1),(3,2),(4,4);
/*!40000 ALTER TABLE `appointment_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commission`
--

DROP TABLE IF EXISTS `commission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commission` (
  `commission_id` int(11) NOT NULL AUTO_INCREMENT,
  `total_commission` bigint(11) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`commission_id`),
  KEY `fk_doctor_id` (`doctor_id`),
  CONSTRAINT `fk_doctor_id` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commission`
--

LOCK TABLES `commission` WRITE;
/*!40000 ALTER TABLE `commission` DISABLE KEYS */;
/*!40000 ALTER TABLE `commission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `condyn`
--

DROP TABLE IF EXISTS `condyn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `condyn` (
  `condyn_id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`condyn_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `condyn`
--

LOCK TABLES `condyn` WRITE;
/*!40000 ALTER TABLE `condyn` DISABLE KEYS */;
/*!40000 ALTER TABLE `condyn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `doctor_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `contact_no` bigint(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `hospital_name` varchar(20) DEFAULT NULL,
  `qualification` varchar(20) DEFAULT NULL,
  `speciality` varchar(20) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  `commission` bigint(11) DEFAULT NULL,
  `added_by_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`doctor_id`),
  KEY `fk_added_by_user_id` (`added_by_user_id`),
  CONSTRAINT `added_by_user_id` FOREIGN KEY (`added_by_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (1,'Ankit ',9876543210,'ankit@gmail.com','BTM','MBBS`','Bone','BTM',0,500,1),(2,'Vishal',9876543210,'vishal@gmail.com','HSR','MBBS','Neuro','HSR',0,300,1),(3,'Mohit',9876543210,'mohit@gmail.com','madivala','MBBS','skin','Madivala',0,400,1),(4,'Sanjay',9876543210,'sanjay@gmail.com','banashankri','MBBSd','tooth','banshankari',0,600,1),(7,'ererywyer',235252352,'qrqr@sdgsgds.com','sgsgsdgd','lihlkh','kk','kjkj',0,2424242,1);
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `invoice_id` int(11) NOT NULL AUTO_INCREMENT,
  `appointment_id` int(11) DEFAULT NULL,
  `total_invoice` int(11) DEFAULT NULL,
  `created_date_time` datetime DEFAULT NULL,
  `modified_date_time` datetime DEFAULT NULL,
  `created_by_user_id` int(11) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`invoice_id`),
  KEY `fk_inv_created_by_user_id` (`created_by_user_id`),
  KEY `fk_inv_appointment_id` (`appointment_id`),
  CONSTRAINT `fk_inv_appointment_id` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`appointment_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_inv_created_by_user_id` FOREIGN KEY (`created_by_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,1,5500,'2017-05-22 14:56:59','2017-05-23 14:56:59',2,'1'),(2,2,11000,'2017-05-22 14:56:59','2017-05-23 14:56:59',2,'1'),(3,3,5500,'2017-05-22 14:56:59','2017-05-23 14:56:59',2,'1'),(4,4,8750,'2017-05-22 14:56:59','2017-05-23 14:56:59',2,'1'),(5,5,11000,'2017-05-22 14:56:59','2017-05-23 14:56:59',2,'1');
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `patient_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `contact_number` bigint(10) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'rani','mukari',44,'Mumbai','rani@gmail.com',9876543210,'F'),(2,'mahesh','batt',44,'Mumbai','mahes@gmail.com',9876543210,'M'),(3,'Akil','Bharti',40,'Mumbai','akhil@gmail.com',9876543210,'F'),(4,'Amit','Sharma',55,'Mumbai','amit@gmail.com',9742085972,'M');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `report_id` int(11) NOT NULL AUTO_INCREMENT,
  `report_type` varchar(20) DEFAULT NULL,
  `report_created_date_time` datetime DEFAULT NULL,
  `generated_by_user_id` int(11) DEFAULT NULL,
  `total_appointment` int(11) DEFAULT NULL,
  `total_services` int(11) DEFAULT NULL,
  `total_doctors` int(11) DEFAULT NULL,
  `total_patient` int(11) DEFAULT NULL,
  `total_commission` int(11) DEFAULT NULL,
  `total_service_charge` int(11) DEFAULT NULL,
  `total_users` int(11) DEFAULT NULL,
  `total_canceled_appointment` int(11) DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `fk_report_generated_by_user_id` (`generated_by_user_id`),
  CONSTRAINT `fk_report_generated_by_user_id` FOREIGN KEY (`generated_by_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'SUPERADMIN'),(2,'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_type`
--

DROP TABLE IF EXISTS `service_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service_type` (
  `service_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_type` varchar(255) NOT NULL,
  `price` double DEFAULT NULL,
  `created_date_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_date_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`service_type_id`),
  KEY `fk_created_by_user_id` (`created_by_user_id`),
  CONSTRAINT `created_by_user_id` FOREIGN KEY (`created_by_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_type`
--

LOCK TABLES `service_type` WRITE;
/*!40000 ALTER TABLE `service_type` DISABLE KEYS */;
INSERT INTO `service_type` VALUES (1,'X-ray',5000,'2017-05-22 14:56:59','2017-05-23 14:56:59',1),(2,'CT-Scan',10000,'2017-05-22 14:56:59','2017-05-23 14:56:59',1),(4,'CT-Scan',9000,'2017-05-22 14:56:59','2017-05-23 14:56:59',1),(6,'Blood-Test',25223535,NULL,NULL,NULL),(7,'Blood-Test',99999999999999,NULL,NULL,NULL),(8,'dfdhfhfd',99999000,NULL,NULL,NULL),(9,'Blood-Test',8989,NULL,NULL,NULL),(10,'CT-Scan',11111222,NULL,NULL,NULL),(11,'cxnxgjgs',5255322,NULL,NULL,NULL);
/*!40000 ALTER TABLE `service_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `active` char(1) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'1','condigence@gmail.com','Saini','Ankit','$2a$10$j7wqSscsLrPTVEffNhSBt.Dm6.U2R8tUX/unPpoKb9ICUnqe1OW9m'),(2,'0','condigenceadmin@gmail.com','Aryans','Vishal','$2a$10$avBlWj510fh6v266ngCVNuFBzr/2LAJ2RKSZy.Qy9ZzOUwA.LnpHa'),(3,'1','medicare@condigence.com','Aryan','Vishal','$2a$10$BshymWucZ7RbVqkDjvYotuqFJLAv8W/DzpRZx42U1QX2pU0rufKzK'),(4,'1','dggf@sdgd.com','hjgvhgh','hghkgk','$2a$10$iBrBalZfMNmxl3wA9v712OWmOjqicLR7zYfnQ7033iDthqykp35Ra'),(5,'1','test@test.com','Vishal','Aryan','$2a$10$drXg3yBi27sShM3BXxCFWe5ZNw8y6S1DYtD.FLIPj0TVsErVGaZjq'),(6,'1','sg@test.com','Garg','Suresh','$2a$10$FFm/jcZHqWouoFti4LmeGekVTFRyRrt3J2aS6JQqOkgpp4bYMEh1i'),(7,'1','admin@test.com','Aryan','Vishal','$2a$10$jZ3D4WC2DNTWNKyQPl3GtuIfXeFebE72lucCLnGkT0SQWZc/60WUC'),(8,'1','admin@medicare.com','admin','admin','$2a$10$UH8L9G5HtGA5SlzkZpRlBODrCLOVSs8NqXOBNelTShJTJUeJWlw1W');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(3,1),(5,1),(6,1),(7,1),(8,1),(2,2),(4,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-28 22:06:57
