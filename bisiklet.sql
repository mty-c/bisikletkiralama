-- --------------------------------------------------------
-- Sunucu:                       127.0.0.1
-- Sunucu sürümü:                10.2.31-MariaDB - mariadb.org binary distribution
-- Sunucu İşletim Sistemi:       Win64
-- HeidiSQL Sürüm:               10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- tablo yapısı dökülüyor bisiklet.atv
CREATE TABLE IF NOT EXISTS `atv` (
  `atv_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `marka` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `kullanici_id` int(11) DEFAULT NULL,
  KEY `kullanici_id` (`kullanici_id`),
  KEY `atv_id` (`atv_id`),
  CONSTRAINT `FK_atv_kullanici` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- bisiklet.atv: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `atv` DISABLE KEYS */;
INSERT INTO `atv` (`atv_id`, `marka`, `model`, `kullanici_id`) VALUES
	(1, 'Piranha', 'Monster', 2);
/*!40000 ALTER TABLE `atv` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.bisiklet
CREATE TABLE IF NOT EXISTS `bisiklet` (
  `bisiklet_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `marka` varchar(50) DEFAULT NULL,
  `ucret` int(11) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `kullanici_id` int(11) DEFAULT NULL,
  KEY `kullanici_id` (`kullanici_id`),
  KEY `bisiklet_id` (`bisiklet_id`),
  CONSTRAINT `FK_bisiklet_kullanici` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

-- bisiklet.bisiklet: ~5 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `bisiklet` DISABLE KEYS */;
INSERT INTO `bisiklet` (`bisiklet_id`, `marka`, `ucret`, `model`, `kullanici_id`) VALUES
	(24, 'Salcano', 275, 'SuperStar', 2),
	(27, 'asdasd', 232, 'asdasd', 1),
	(33, 'fghbfg', 345, 'fgnhfg', 3),
	(34, 'asdas', 0, '', 3),
	(35, 'sasd', 232, 'asdasd', 1);
/*!40000 ALTER TABLE `bisiklet` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.bisiklet_kategori
CREATE TABLE IF NOT EXISTS `bisiklet_kategori` (
  `bisiklet_id` int(11) unsigned DEFAULT NULL,
  `kategori_id` int(11) DEFAULT NULL,
  KEY `bisiklet_id` (`bisiklet_id`),
  KEY `Sütun 2` (`kategori_id`),
  CONSTRAINT `FK2_kategori` FOREIGN KEY (`kategori_id`) REFERENCES `kategori` (`kategori_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK__bisiklet` FOREIGN KEY (`bisiklet_id`) REFERENCES `bisiklet` (`bisiklet_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- bisiklet.bisiklet_kategori: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `bisiklet_kategori` DISABLE KEYS */;
INSERT INTO `bisiklet_kategori` (`bisiklet_id`, `kategori_id`) VALUES
	(35, 1);
/*!40000 ALTER TABLE `bisiklet_kategori` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.kategori
CREATE TABLE IF NOT EXISTS `kategori` (
  `kategori_id` int(11) NOT NULL AUTO_INCREMENT,
  `kategori_ad` varchar(50) DEFAULT NULL,
  KEY `kategori_id` (`kategori_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- bisiklet.kategori: ~4 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kategori` DISABLE KEYS */;
INSERT INTO `kategori` (`kategori_id`, `kategori_ad`) VALUES
	(1, 'DAG'),
	(2, 'camur'),
	(3, 'yol'),
	(4, 'patinaj');
/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.kullanici
CREATE TABLE IF NOT EXISTS `kullanici` (
  `kullanici_id` int(11) NOT NULL AUTO_INCREMENT,
  `pass` int(8) DEFAULT NULL,
  `role` int(2) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  KEY `kullanici_id` (`kullanici_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;

-- bisiklet.kullanici: ~7 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kullanici` DISABLE KEYS */;
INSERT INTO `kullanici` (`kullanici_id`, `pass`, `role`, `name`, `surname`, `gender`) VALUES
	(3, 1234, 1, 'huso', 'alt', b'1'),
	(2, 1234, 1, 'alp', 'alt', b'1'),
	(41, 44, 0, 'Ahmet', 'Can', b'1'),
	(1, 0, 0, 'DEFAULT', 'DEFAULT', b'1'),
	(48, 23, 1, 'Ahmet', 'ASLAN', b'1'),
	(54, 44, 1, 'Mehmet', 'Aslan', b'1'),
	(61, 3, 0, 'asdas', 'dasdasd', b'1');
/*!40000 ALTER TABLE `kullanici` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.kullanici_bisiklet
CREATE TABLE IF NOT EXISTS `kullanici_bisiklet` (
  `kullanici_id` int(11) DEFAULT NULL,
  `bisiklet_id` int(11) unsigned DEFAULT NULL,
  `last_update` timestamp NULL DEFAULT curtime(),
  KEY `kullanici_id` (`kullanici_id`),
  KEY `bisiklet_id` (`bisiklet_id`),
  CONSTRAINT `FK__kullanici` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_kullanici_bisiklet_bisiklet` FOREIGN KEY (`bisiklet_id`) REFERENCES `bisiklet` (`bisiklet_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- bisiklet.kullanici_bisiklet: ~4 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kullanici_bisiklet` DISABLE KEYS */;
INSERT INTO `kullanici_bisiklet` (`kullanici_id`, `bisiklet_id`, `last_update`) VALUES
	(2, 24, '2020-03-28 18:32:36'),
	(3, 33, '2020-05-17 20:28:49'),
	(3, 34, '2020-05-17 21:31:25'),
	(1, 35, '2020-05-17 21:41:38');
/*!40000 ALTER TABLE `kullanici_bisiklet` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.kullanici_motor
CREATE TABLE IF NOT EXISTS `kullanici_motor` (
  `kullanici_id` int(11) DEFAULT NULL,
  `motor_id` int(11) unsigned DEFAULT NULL,
  `last_update` timestamp NULL DEFAULT curtime(),
  KEY `kullanici_id` (`kullanici_id`),
  KEY `motor_id` (`motor_id`),
  CONSTRAINT `FK2_motorid` FOREIGN KEY (`motor_id`) REFERENCES `motor` (`motor_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK__kullanicilan` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- bisiklet.kullanici_motor: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kullanici_motor` DISABLE KEYS */;
INSERT INTO `kullanici_motor` (`kullanici_id`, `motor_id`, `last_update`) VALUES
	(1, 1, '2020-04-06 20:43:42');
/*!40000 ALTER TABLE `kullanici_motor` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.motor
CREATE TABLE IF NOT EXISTS `motor` (
  `motor_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `marka` varchar(50) DEFAULT NULL,
  `ucret` int(11) DEFAULT NULL,
  `motor_hacmi` int(11) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `kullanici_id` int(11) DEFAULT NULL,
  KEY `motor_id` (`motor_id`),
  KEY `FK_motor_kullanici` (`kullanici_id`),
  CONSTRAINT `FK_motor_kullanici` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- bisiklet.motor: ~5 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `motor` DISABLE KEYS */;
INSERT INTO `motor` (`motor_id`, `marka`, `ucret`, `motor_hacmi`, `model`, `kullanici_id`) VALUES
	(1, 'Harley', 87, 250, 'Davidson', 41),
	(2, 'Suzuki', 500, 1200, 'Vitara', 1),
	(3, 'Yamaha', 50, 125, 'YBR-125', 1),
	(4, 'Kawasaki', 700, 1400, 'SuperB', 1),
	(5, 'KTM', 400, 1500, 'RC-125', 1);
/*!40000 ALTER TABLE `motor` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
