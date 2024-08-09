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

-- Dumping structure for table 2024_BB.tb_bb_line
CREATE TABLE IF NOT EXISTS `tb_bb_line` (
  `blade_unit_id` varchar(100) NOT NULL,
  `id` varchar(100) NOT NULL,
  `period_cnt` varchar(100) DEFAULT NULL,
  `period` varchar(100) DEFAULT NULL,
  `init_price` int(11) DEFAULT NULL,
  `deposit` int(11) DEFAULT NULL,
  `depreciation` int(11) DEFAULT NULL,
  `fee` int(11) DEFAULT NULL,
  `end_price` int(11) DEFAULT NULL,
  `active_status` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_general_ci;

-- Dumping data for table 2024_BB.tb_bb_line: ~3 rows (approximately)
TRUNCATE TABLE `tb_bb_line`;
INSERT INTO `tb_bb_line` (`blade_unit_id`, `id`, `period_cnt`, `period`, `init_price`, `deposit`, `depreciation`, `fee`, `end_price`, `active_status`) VALUES
	('BUT_TMB_ALC_FL_00001', '2024_03_00003', 3, '1M', 3000000, 300000, 100000, 50000, 2700000, 0),
	('BUT_TMB_ALC_FL_00002', '2024_05_00001', 5, '1M', 3000000, 300000, 100000, 50000, 2700000, 0),
	('NTK_ACS_FL_00001', '2024_05_00051', 4, '1M', 4000000, 50000, 50000, 50000, 2000000, 0),
	('NTK_ACS_FL00002', '2024_06_00001', 3, '2W', 1000000, 100000, 100000, 100000, 100000, 0);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
