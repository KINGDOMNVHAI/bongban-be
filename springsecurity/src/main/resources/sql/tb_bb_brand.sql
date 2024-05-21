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

-- Dumping structure for table 2024_BB.tb_bb_brand
CREATE TABLE IF NOT EXISTS `tb_bb_brand` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `brand_cd` varchar(3) NOT NULL,
  `brand_nm` char(50) DEFAULT NULL,
  `parent` varchar(50) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`seq`,`brand_cd`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- Dumping data for table 2024_BB.tb_bb_brand: ~7 rows (approximately)
DELETE FROM `tb_bb_brand`;
INSERT INTO `tb_bb_brand` (`seq`, `brand_cd`, `brand_nm`, `parent`, `thumbnail`) VALUES
	(1, 'NTK', 'Nittaku', 'ROOT', NULL),
	(2, 'BUT', 'Butterfly', 'ROOT', NULL),
	(3, 'YSK', 'Yasaka', 'ROOT', NULL),
	(4, 'DON', 'Donic', 'ROOT', NULL),
	(5, 'TIB', 'Tibhar', 'ROOT', NULL),
	(6, 'TMB', 'Timo Boll', 'BUT', 'butterfly-timo-boll-thumbnail.jpg'),
	(7, 'ACS', 'Acoustic', 'NTK', 'nittaku-acoustic-thumbnail.jpg'),
	(8, 'GRS', 'Growster', 'YSK', 'yasaka-growster-thumbnail.jpg');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
