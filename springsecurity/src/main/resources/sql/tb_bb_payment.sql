-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.2.0 - MySQL Community Server - GPL
-- Server OS:                    Linux
-- HeidiSQL Version:             12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for 2024_BB
CREATE DATABASE IF NOT EXISTS `2024_BB` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `2024_BB`;

-- Dumping structure for table 2024_BB.tb_bb_payment
CREATE TABLE IF NOT EXISTS `tb_bb_payment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `amount_paid` int DEFAULT NULL,
  `amount_remaining` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `id_payment_platform` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `platform` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `account_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `account_number` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `checkout_url` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `currency` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `order_code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `payment_link_id` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `qrcode` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `signature` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table 2024_BB.tb_bb_payment: ~1 rows (approximately)
TRUNCATE TABLE `tb_bb_payment`;
INSERT INTO `tb_bb_payment` (`id`, `amount`, `amount_paid`, `amount_remaining`, `created_at`, `description`, `id_payment_platform`, `platform`, `status`, `account_name`, `account_number`, `checkout_url`, `currency`, `order_code`, `payment_link_id`, `qrcode`, `signature`) VALUES
	(1, 200000, NULL, NULL, NULL, 'abc', '970422', 'PAYOS', 2, 'CT TNHH CN TD THONG MINH', 'VQRQ0001uig8k', 'https://pay.payos.vn/web/cf27cdefa1544f4bb5dd522f1d7b578d', 'VND', '9.0', 'cf27cdefa1544f4bb5dd522f1d7b578d', '00020101021238570010A000000727012700069704220113VQRQ0001uig8k0208QRIBFTTA530370454062000005802VN62070803abc6304D84C', 'ec7f026960543f76307a65867e407df1702b70073f5db9466ceab1ae161edd1c');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
