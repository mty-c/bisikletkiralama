-- --------------------------------------------------------
-- Sunucu:                       127.0.0.1
-- Sunucu sürümü:                10.4.12-MariaDB - mariadb.org binary distribution
-- Sunucu İşletim Sistemi:       Win64
-- HeidiSQL Sürüm:               10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- bisiklet için veritabanı yapısı dökülüyor
CREATE DATABASE IF NOT EXISTS `bisiklet` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `bisiklet`;

-- tablo yapısı dökülüyor bisiklet.atv
CREATE TABLE IF NOT EXISTS `atv` (
  `atv_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `marka` varchar(50) DEFAULT NULL,
  `ucret` int(11) DEFAULT NULL,
  `motor_hacmi` int(11) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `kullanici_id` int(11) DEFAULT NULL,
  KEY `kullanici_id` (`kullanici_id`),
  KEY `atv_id` (`atv_id`),
  CONSTRAINT `FK_atv_kullanici` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- bisiklet.atv: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `atv` DISABLE KEYS */;
INSERT INTO `atv` (`atv_id`, `marka`, `ucret`, `motor_hacmi`, `model`, `kullanici_id`) VALUES
	(14, 'CFMOTO', 279, 850, 'CF800', 1),
	(15, 'Mondial', 129, 178, 'AU200', 1),
	(16, 'CAN-AM', 759, 1000, 'Maverick X3', 87),
	(17, 'YUKI', 389, 400, 'HS400', 1);
/*!40000 ALTER TABLE `atv` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.atv_kategori
CREATE TABLE IF NOT EXISTS `atv_kategori` (
  `atv_id` int(11) unsigned DEFAULT NULL,
  `kategori_id` int(11) DEFAULT NULL,
  KEY `FK_atv` (`atv_id`),
  KEY `FK3_kategori` (`kategori_id`),
  CONSTRAINT `FK3_kategori` FOREIGN KEY (`kategori_id`) REFERENCES `kategori` (`kategori_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_atv` FOREIGN KEY (`atv_id`) REFERENCES `atv` (`atv_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- bisiklet.atv_kategori: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `atv_kategori` DISABLE KEYS */;
INSERT INTO `atv_kategori` (`atv_id`, `kategori_id`) VALUES
	(14, 14),
	(15, 15),
	(16, 16),
	(17, 17);
/*!40000 ALTER TABLE `atv_kategori` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;

-- bisiklet.bisiklet: ~9 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `bisiklet` DISABLE KEYS */;
INSERT INTO `bisiklet` (`bisiklet_id`, `marka`, `ucret`, `model`, `kullanici_id`) VALUES
	(52, 'Salcano', 25, 'NG650 ', 1),
	(53, 'KTM', 59, 'Ultra Race', 83),
	(54, 'Carraro', 65, 'Caravan', 87),
	(55, 'Kron', 45, 'TX100', 1),
	(56, 'Mosso', 50, 'Cavalier Tourney', 1),
	(57, 'RKS', 100, 'XR6', 82),
	(58, 'Mosso', 45, 'Legarda', 1),
	(59, 'Bianchi', 250, 'Scuderia Ferrari SF01', 84);
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

-- bisiklet.bisiklet_kategori: ~9 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `bisiklet_kategori` DISABLE KEYS */;
INSERT INTO `bisiklet_kategori` (`bisiklet_id`, `kategori_id`) VALUES
	(52, 1),
	(53, 2),
	(54, 6),
	(55, 4),
	(56, 3),
	(57, 7),
	(58, 5),
	(59, 3);
/*!40000 ALTER TABLE `bisiklet_kategori` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.document
CREATE TABLE IF NOT EXISTS `document` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `filepath` text CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `filename` text CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  `filetype` text CHARACTER SET utf8 COLLATE utf8_turkish_ci NOT NULL,
  PRIMARY KEY (`document_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- bisiklet.document: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` (`document_id`, `filepath`, `filename`, `filetype`) VALUES
	(33, 'C:\\Users\\Talha Yılmaz\\Desktop\\gorselDuzenleme\\WebDesign\\web\\document\\upload', 'photography-of-person-walking-on-road-1236701.jpg', 'image/jpeg');
/*!40000 ALTER TABLE `document` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.ginger
CREATE TABLE IF NOT EXISTS `ginger` (
  `ginger_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `marka` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ucret` int(11) DEFAULT NULL,
  `model` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `renk` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `kullanici_id` int(11) DEFAULT NULL,
  KEY `ginger_id` (`ginger_id`),
  KEY `FK_ginger_kullanici` (`kullanici_id`),
  CONSTRAINT `FK_ginger_kullanici` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- bisiklet.ginger: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `ginger` DISABLE KEYS */;
INSERT INTO `ginger` (`ginger_id`, `marka`, `ucret`, `model`, `renk`, `kullanici_id`) VALUES
	(10, 'MYPET', 75, 'V3', 'Siyah', 1),
	(11, 'SEGWAY', 89, 'I2SE', 'Siyah', 1);
/*!40000 ALTER TABLE `ginger` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.ginger_kategori
CREATE TABLE IF NOT EXISTS `ginger_kategori` (
  `ginger_id` int(11) unsigned DEFAULT NULL,
  `kategori_id` int(11) DEFAULT NULL,
  KEY `FK_ginger_kategori` (`kategori_id`),
  KEY `FK_ginger` (`ginger_id`),
  CONSTRAINT `FK_ginger` FOREIGN KEY (`ginger_id`) REFERENCES `ginger` (`ginger_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ginger_kategori` FOREIGN KEY (`kategori_id`) REFERENCES `kategori` (`kategori_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- bisiklet.ginger_kategori: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `ginger_kategori` DISABLE KEYS */;
INSERT INTO `ginger_kategori` (`ginger_id`, `kategori_id`) VALUES
	(10, 21),
	(11, 20);
/*!40000 ALTER TABLE `ginger_kategori` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.hoverboard
CREATE TABLE IF NOT EXISTS `hoverboard` (
  `hoverboard_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `renk` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `marka` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ucret` int(11) DEFAULT NULL,
  `model` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `kullanici_id` int(11) DEFAULT NULL,
  KEY `hoverboard_id` (`hoverboard_id`),
  KEY `FK_hoverboard_kullanici` (`kullanici_id`),
  CONSTRAINT `FK_hoverboard_kullanici` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- bisiklet.hoverboard: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `hoverboard` DISABLE KEYS */;
INSERT INTO `hoverboard` (`hoverboard_id`, `renk`, `marka`, `ucret`, `model`, `kullanici_id`) VALUES
	(6, 'Sarı', 'REM', 59, 'W1C', 1),
	(7, 'Siyah', 'SEGWAY', 129, 'OneS2', 1);
/*!40000 ALTER TABLE `hoverboard` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.hoverboard_kategori
CREATE TABLE IF NOT EXISTS `hoverboard_kategori` (
  `hoverboard_id` int(10) unsigned DEFAULT NULL,
  `kategori_id` int(11) DEFAULT NULL,
  KEY `FK_hoverboard_kategori` (`kategori_id`),
  KEY `FK_hoverboard` (`hoverboard_id`),
  CONSTRAINT `FK_hoverboard` FOREIGN KEY (`hoverboard_id`) REFERENCES `hoverboard` (`hoverboard_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_hoverboard_kategori` FOREIGN KEY (`kategori_id`) REFERENCES `kategori` (`kategori_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- bisiklet.hoverboard_kategori: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `hoverboard_kategori` DISABLE KEYS */;
INSERT INTO `hoverboard_kategori` (`hoverboard_id`, `kategori_id`) VALUES
	(6, 23),
	(7, 22);
/*!40000 ALTER TABLE `hoverboard_kategori` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.kategori
CREATE TABLE IF NOT EXISTS `kategori` (
  `kategori_id` int(11) NOT NULL AUTO_INCREMENT,
  `kategori_ad` varchar(50) DEFAULT NULL,
  KEY `kategori_id` (`kategori_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

-- bisiklet.kategori: ~4 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kategori` DISABLE KEYS */;
INSERT INTO `kategori` (`kategori_id`, `kategori_ad`) VALUES
	(1, 'Dag Bisikleti'),
	(2, 'Yaris Bisikleti'),
	(3, 'Yol Bisikleti'),
	(8, 'Yaris Motoru'),
	(9, 'Yol Motoru'),
	(10, 'Offroad Motor'),
	(11, 'Tur Motor'),
	(12, 'Enduro Motor'),
	(13, 'Spor Motor'),
	(4, 'Tur Bisikleti'),
	(5, 'Sehir Bisikleti'),
	(14, '4x4 Atv'),
	(15, '4x2 Atv'),
	(16, '4x4 Utv'),
	(17, '4x2 Utv'),
	(18, 'Elektrikli Scooter'),
	(19, 'Scooter'),
	(20, '2 Tekerli Ginger'),
	(21, '3 Tekerli Ginger'),
	(6, '3 Tekerli Bisiklet'),
	(7, 'Elektrikli Bisiklet'),
	(22, 'Tek Tekerli Hoverboard'),
	(23, 'Hoverboard');
/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.kullanici
CREATE TABLE IF NOT EXISTS `kullanici` (
  `kullanici_id` int(11) NOT NULL AUTO_INCREMENT,
  `pass` varchar(50) DEFAULT NULL,
  `role` int(2) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  KEY `kullanici_id` (`kullanici_id`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=latin1;

-- bisiklet.kullanici: ~7 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kullanici` DISABLE KEYS */;
INSERT INTO `kullanici` (`kullanici_id`, `pass`, `role`, `name`, `surname`, `gender`) VALUES
	(3, '12345', 1, 'admin', 'admin', b'1'),
	(1, '0', 0, 'DEFAULT', 'DEFAULT', b'1'),
	(82, '12345', 1, 'Muhammed Talha', 'Yilmaz', b'1'),
	(83, '12345', 1, 'Selahattin', 'Yildirim', b'1'),
	(84, '12345', 1, 'Ahmet Ikbal', 'Kaymaz', b'1'),
	(87, '12345', 1, 'Hüseyin', 'Altikulac', b'1'),
	(110, 'Deneme', 0, 'Deneme', 'Deneme', b'0'),
	(111, '12345', 0, 'Deneme', 'Test', b'0');
/*!40000 ALTER TABLE `kullanici` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.kullanici_atv
CREATE TABLE IF NOT EXISTS `kullanici_atv` (
  `kullanici_id` int(11) DEFAULT NULL,
  `atv_id` int(11) unsigned DEFAULT NULL,
  `last_update` timestamp NULL DEFAULT curtime(),
  KEY `FK_kullanici` (`kullanici_id`),
  KEY `FK_kullanici_atv` (`atv_id`),
  CONSTRAINT `FK_kullanici` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_kullanici_atv` FOREIGN KEY (`atv_id`) REFERENCES `atv` (`atv_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- bisiklet.kullanici_atv: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kullanici_atv` DISABLE KEYS */;
INSERT INTO `kullanici_atv` (`kullanici_id`, `atv_id`, `last_update`) VALUES
	(1, 14, '2020-06-09 15:54:30'),
	(1, 15, '2020-06-09 15:55:16'),
	(87, 16, '2020-06-09 15:56:20'),
	(1, 17, '2020-06-09 15:57:16');
/*!40000 ALTER TABLE `kullanici_atv` ENABLE KEYS */;

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

-- bisiklet.kullanici_bisiklet: ~8 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kullanici_bisiklet` DISABLE KEYS */;
INSERT INTO `kullanici_bisiklet` (`kullanici_id`, `bisiklet_id`, `last_update`) VALUES
	(1, 52, '2020-06-09 15:28:36'),
	(83, 53, '2020-06-09 15:29:59'),
	(87, 54, '2020-06-09 15:31:12'),
	(1, 55, '2020-06-09 15:31:59'),
	(1, 56, '2020-06-09 15:33:01'),
	(82, 57, '2020-06-09 15:33:58'),
	(1, 58, '2020-06-09 15:34:34'),
	(84, 59, '2020-06-09 15:35:58');
/*!40000 ALTER TABLE `kullanici_bisiklet` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.kullanici_ginger
CREATE TABLE IF NOT EXISTS `kullanici_ginger` (
  `kullanici_id` int(11) DEFAULT NULL,
  `ginger_id` int(11) unsigned DEFAULT NULL,
  `last_update` timestamp NULL DEFAULT curtime(),
  KEY `kullanici_id` (`kullanici_id`),
  KEY `ginger_id` (`ginger_id`),
  CONSTRAINT `FK_kullanici_ginger` FOREIGN KEY (`ginger_id`) REFERENCES `ginger` (`ginger_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_kullanicii` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- bisiklet.kullanici_ginger: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kullanici_ginger` DISABLE KEYS */;
INSERT INTO `kullanici_ginger` (`kullanici_id`, `ginger_id`, `last_update`) VALUES
	(1, 10, '2020-06-09 16:04:51'),
	(1, 11, '2020-06-09 16:06:00');
/*!40000 ALTER TABLE `kullanici_ginger` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.kullanici_hoverboard
CREATE TABLE IF NOT EXISTS `kullanici_hoverboard` (
  `kullanici_id` int(11) DEFAULT NULL,
  `hoverboard_id` int(10) unsigned DEFAULT NULL,
  `last_update` timestamp NULL DEFAULT curtime(),
  KEY `kullanici_id` (`kullanici_id`),
  KEY `hoverboard_id` (`hoverboard_id`),
  CONSTRAINT `FK_hoverboard_kullanicii` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_kullanici_hover` FOREIGN KEY (`hoverboard_id`) REFERENCES `hoverboard` (`hoverboard_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- bisiklet.kullanici_hoverboard: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kullanici_hoverboard` DISABLE KEYS */;
INSERT INTO `kullanici_hoverboard` (`kullanici_id`, `hoverboard_id`, `last_update`) VALUES
	(1, 6, '2020-06-09 16:01:49'),
	(1, 7, '2020-06-09 16:03:07');
/*!40000 ALTER TABLE `kullanici_hoverboard` ENABLE KEYS */;

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
	(1, 19, '2020-06-09 15:38:20'),
	(1, 20, '2020-06-09 15:39:26'),
	(1, 21, '2020-06-09 15:45:10'),
	(1, 22, '2020-06-09 15:48:09'),
	(1, 23, '2020-06-09 15:48:59'),
	(83, 24, '2020-06-09 15:50:49'),
	(82, 25, '2020-06-09 15:52:17'),
	(84, 26, '2020-06-09 15:53:30');
/*!40000 ALTER TABLE `kullanici_motor` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.kullanici_scooter
CREATE TABLE IF NOT EXISTS `kullanici_scooter` (
  `kullanici_id` int(11) DEFAULT NULL,
  `scooter_id` int(10) unsigned DEFAULT NULL,
  `last_update` timestamp NULL DEFAULT curtime(),
  KEY `FK_kullanici_scooter` (`kullanici_id`),
  KEY `FK_kullanici_scoot` (`scooter_id`),
  CONSTRAINT `FK_kullanici_scoot` FOREIGN KEY (`scooter_id`) REFERENCES `scooter` (`scooter_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_kullanici_scooter` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- bisiklet.kullanici_scooter: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kullanici_scooter` DISABLE KEYS */;
INSERT INTO `kullanici_scooter` (`kullanici_id`, `scooter_id`, `last_update`) VALUES
	(1, 10, '2020-06-09 15:58:40'),
	(1, 11, '2020-06-09 15:59:44');
/*!40000 ALTER TABLE `kullanici_scooter` ENABLE KEYS */;

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

-- bisiklet.motor: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `motor` DISABLE KEYS */;
INSERT INTO `motor` (`motor_id`, `marka`, `ucret`, `motor_hacmi`, `model`, `kullanici_id`) VALUES
	(19, 'Honda', 200, 1000, '1000 RR', 1),
	(20, 'Yamaha', 300, 155, 'TRICITY', 1),
	(21, 'Honda', 150, 250, 'CRF250', 1),
	(22, 'Harley-Davidson', 400, 1300, 'Fat Boy FLSTFI', 1),
	(23, 'Honda', 300, 1000, 'CRF1000L', 1),
	(24, 'CFMOTO', 199, 400, '400NK', 83),
	(25, 'Kawasaki', 399, 1000, 'ZX10R', 82),
	(26, 'BMW', 349, 1000, 'S1000RR', 84);
/*!40000 ALTER TABLE `motor` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.motor_kategori
CREATE TABLE IF NOT EXISTS `motor_kategori` (
  `motor_id` int(11) unsigned DEFAULT NULL,
  `kategori_id` int(11) DEFAULT NULL,
  KEY `FK_motor` (`motor_id`),
  KEY `FK_kategori` (`kategori_id`),
  CONSTRAINT `FK_kategori` FOREIGN KEY (`kategori_id`) REFERENCES `kategori` (`kategori_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_motor` FOREIGN KEY (`motor_id`) REFERENCES `motor` (`motor_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- bisiklet.motor_kategori: ~1 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `motor_kategori` DISABLE KEYS */;
INSERT INTO `motor_kategori` (`motor_id`, `kategori_id`) VALUES
	(19, 8),
	(20, 9),
	(21, 10),
	(22, 9),
	(23, 12),
	(24, 12),
	(25, 13),
	(26, 8);
/*!40000 ALTER TABLE `motor_kategori` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.scooter
CREATE TABLE IF NOT EXISTS `scooter` (
  `scooter_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `marka` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `model` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ucret` int(11) DEFAULT NULL,
  `boyut` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `kullanici_id` int(11) DEFAULT NULL,
  KEY `scooter_id` (`scooter_id`),
  KEY `FK_scooter_kullanici` (`kullanici_id`),
  CONSTRAINT `FK_scooter_kullanici` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- bisiklet.scooter: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `scooter` DISABLE KEYS */;
INSERT INTO `scooter` (`scooter_id`, `marka`, `model`, `ucret`, `boyut`, `kullanici_id`) VALUES
	(10, 'FURKAN', 'CoolWheels', 10, 'Küçük', 1),
	(11, 'XIAOMI', 'Mija', 70, 'Büyük', 1);
/*!40000 ALTER TABLE `scooter` ENABLE KEYS */;

-- tablo yapısı dökülüyor bisiklet.scooter_kategori
CREATE TABLE IF NOT EXISTS `scooter_kategori` (
  `scooter_id` int(10) unsigned DEFAULT NULL,
  `kategori_id` int(11) DEFAULT NULL,
  KEY `FK_scooter_kategori` (`kategori_id`),
  KEY `FK_scooter` (`scooter_id`),
  CONSTRAINT `FK_scooter` FOREIGN KEY (`scooter_id`) REFERENCES `scooter` (`scooter_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_scooter_kategori` FOREIGN KEY (`kategori_id`) REFERENCES `kategori` (`kategori_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- bisiklet.scooter_kategori: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `scooter_kategori` DISABLE KEYS */;
INSERT INTO `scooter_kategori` (`scooter_id`, `kategori_id`) VALUES
	(10, 19),
	(11, 18);
/*!40000 ALTER TABLE `scooter_kategori` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
