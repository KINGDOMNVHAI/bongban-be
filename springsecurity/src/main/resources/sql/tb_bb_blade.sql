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

-- Dumping structure for table 2024_BB.tb_bb_blade
CREATE TABLE IF NOT EXISTS `tb_bb_blade` (
  `seq` int(11) NOT NULL AUTO_INCREMENT,
  `brand_cd` char(3) NOT NULL,
  `brand_name` varchar(50) NOT NULL,
  `blade_cd` varchar(50) NOT NULL,
  `blade_name` varchar(100) NOT NULL,
  `blade_full_name` varchar(200) DEFAULT NULL,
  `sub_branch` varchar(50) DEFAULT NULL,
  `paddle_tp` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`seq`,`blade_cd`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- Dumping data for table 2024_BB.tb_bb_blade: ~3 rows (approximately)
DELETE FROM `tb_bb_blade`;
INSERT INTO `tb_bb_blade` (`seq`, `brand_cd`, `brand_name`, `blade_cd`, `blade_name`, `blade_full_name`, `sub_branch`, `paddle_tp`) VALUES
	(1, 'NTK', 'Nittaku', 'NTK_ACS_XXX_FL', 'Acoustic', 'Nittaku Acoustic', 'XXX', 'FL'),
	(2, 'BUT', 'Butterfly', 'BUT_TMB_ALC_FL', 'Timo Boll ALC', 'Butterfly Timo Boll ALC', 'TMB', 'FL'),
	(3, 'NTK', 'Nittaku', 'NTK_ACS_XXX_ST', 'Acoustic', 'Nittaku Acoustic', 'XXX', 'ST');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
