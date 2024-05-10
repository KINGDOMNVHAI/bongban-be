-- --------------------------------------------------------
-- Host:                         45.76.98.33
-- Server version:               10.1.41-MariaDB-0ubuntu0.18.04.1 - Ubuntu 18.04
-- Server OS:                    debian-linux-gnu
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

-- Dumping structure for table 2024_BB.tb_bb_line_progress
CREATE TABLE IF NOT EXISTS `tb_bb_line_progress` (
  `line_id` varchar(100) NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `deposit_status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`line_id`,`user_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- Dumping data for table 2024_BB.tb_bb_line_progress: ~5 rows (approximately)
DELETE FROM `tb_bb_line_progress`;
INSERT INTO `tb_bb_line_progress` (`line_id`, `user_email`, `deposit_status`) VALUES
	('2024_03_00003', 'huynhan007@gmail.com', NULL),
	('2024_03_00003', 'mh@gmail.com', NULL),
	('2024_03_00003', 'paperheroteam@gmail.com', NULL),
	('2024_06_00001', 'huynhan@gmail.com', 'Y'),
	('2024_06_00001', 'mh@gmail.com', 'N');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
