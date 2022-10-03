/*
SQLyog Community v13.1.9 (64 bit)
MySQL - 8.0.29 : Database - hiking_app_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hiking_app_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `hiking_app_db`;

/*Table structure for table `hiker` */

DROP TABLE IF EXISTS `hiker`;

CREATE TABLE `hiker` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `ucin` char(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `date_of_birth` date NOT NULL,
  `years_of_experience` int NOT NULL,
  `place_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ucin_unique` (`ucin`),
  KEY `place_id` (`place_id`),
  CONSTRAINT `hiker_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `years_positive` CHECK ((`years_of_experience` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `hiker` */

/*Table structure for table `hiking_activity` */

DROP TABLE IF EXISTS `hiking_activity`;

CREATE TABLE `hiking_activity` (
  `hiking_group_id` bigint unsigned NOT NULL,
  `order_num` int unsigned NOT NULL,
  `name` varchar(300) NOT NULL,
  `description` varchar(500) NOT NULL,
  `date` date NOT NULL,
  `place_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`hiking_group_id`,`order_num`),
  KEY `place_id` (`place_id`),
  CONSTRAINT `hiking_activity_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `hiking_activity_ibfk_2` FOREIGN KEY (`hiking_group_id`) REFERENCES `hiking_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `hiking_activity` */

/*Table structure for table `hiking_group` */

DROP TABLE IF EXISTS `hiking_group`;

CREATE TABLE `hiking_group` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `crn` char(8) NOT NULL,
  `name` varchar(500) NOT NULL,
  `description` varchar(500) NOT NULL,
  `resources` varchar(500) DEFAULT 'none',
  `has_liscence` tinyint(1) DEFAULT '1',
  `place_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `crn_unique` (`crn`),
  KEY `place_id` (`place_id`),
  CONSTRAINT `hiking_group_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `hiking_group` */

/*Table structure for table `place` */

DROP TABLE IF EXISTS `place`;

CREATE TABLE `place` (
  `id` bigint unsigned NOT NULL,
  `name` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `place` */

insert  into `place`(`id`,`name`) values 
(1,'New York'),
(2,'London'),
(3,'Paris'),
(4,'Washington');

/*Table structure for table `profile` */

DROP TABLE IF EXISTS `profile`;

CREATE TABLE `profile` (
  `hiker_id` bigint unsigned NOT NULL,
  `hiking_group_id` bigint unsigned NOT NULL,
  `date_of_enrollment` date NOT NULL,
  `note` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`hiker_id`,`hiking_group_id`),
  KEY `hiking_group_id` (`hiking_group_id`),
  CONSTRAINT `profile_ibfk_1` FOREIGN KEY (`hiker_id`) REFERENCES `hiker` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `profile_ibfk_2` FOREIGN KEY (`hiking_group_id`) REFERENCES `hiking_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `profile` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `username` varchar(300) NOT NULL,
  `password` varchar(300) NOT NULL,
  `email` varchar(500) NOT NULL,
  `place_id` bigint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_unique` (`username`),
  KEY `place_id` (`place_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
