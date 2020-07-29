-- --------------------------------------------------------
-- Host:                         192.168.123.21
-- Server version:               10.5.4-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for hotel_management_app_console
CREATE DATABASE IF NOT EXISTS `hotel_management_app_console` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hotel_management_app_console`;

-- Dumping structure for table hotel_management_app_console.account
CREATE TABLE IF NOT EXISTS `account` (
  `id_account` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `permission` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_account`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='Account management';

-- Data exporting was unselected.

-- Dumping structure for table hotel_management_app_console.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `id_customer` bigint(20) NOT NULL,
  `name_customer` varchar(100) COLLATE utf8_vietnamese_ci NOT NULL,
  `date_of_birth` varchar(50) COLLATE utf8_vietnamese_ci NOT NULL DEFAULT '',
  `address` varchar(500) COLLATE utf8_vietnamese_ci NOT NULL,
  `telephone` bigint(20) NOT NULL,
  `id_account` int(11) DEFAULT 0,
  PRIMARY KEY (`id_customer`),
  KEY `FK_customer_account` (`id_account`),
  CONSTRAINT `FK_customer_account` FOREIGN KEY (`id_account`) REFERENCES `account` (`id_account`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci COMMENT='Customer Information Management';

-- Data exporting was unselected.

-- Dumping structure for table hotel_management_app_console.room_book
CREATE TABLE IF NOT EXISTS `room_book` (
  `id_room_book` int(11) NOT NULL AUTO_INCREMENT,
  `check_in_date` varchar(50) NOT NULL DEFAULT '',
  `check_out_date` varchar(50) NOT NULL DEFAULT '',
  `service` varchar(50) NOT NULL DEFAULT '0',
  `note` varchar(500) DEFAULT NULL,
  `prepayment` float NOT NULL DEFAULT 0,
  `day_of_stay` bigint(20) NOT NULL DEFAULT 0,
  `total` float NOT NULL,
  `id_customer` bigint(20) DEFAULT NULL,
  `number_room` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_room_book`) USING BTREE,
  KEY `FK_room_book_customer` (`id_customer`),
  KEY `FK_room_book_room_info` (`number_room`),
  CONSTRAINT `FK_room_book_customer` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id_customer`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_room_book_room_info` FOREIGN KEY (`number_room`) REFERENCES `room_info` (`number_room`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='Room information management';

-- Data exporting was unselected.

-- Dumping structure for table hotel_management_app_console.room_info
CREATE TABLE IF NOT EXISTS `room_info` (
  `id_room_info` int(11) NOT NULL AUTO_INCREMENT,
  `number_room` int(11) NOT NULL,
  `type_of_room` varchar(50) NOT NULL,
  `price` float NOT NULL,
  `Characteristics` varchar(100) NOT NULL,
  `Status` varchar(100) NOT NULL DEFAULT 'Empty',
  PRIMARY KEY (`number_room`) USING BTREE,
  UNIQUE KEY `id_room_info` (`id_room_info`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
