-- Adminer 4.7.1 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `tb_buku`;
CREATE TABLE `tb_buku` (
  `id_buku` int(11) NOT NULL AUTO_INCREMENT,
  `judul` varchar(45) DEFAULT NULL,
  `penulis` varchar(45) DEFAULT NULL,
  `penerbit` varchar(45) DEFAULT NULL,
  `harga` int(20) DEFAULT NULL,
  `stock` int(20) DEFAULT NULL,
  `kode_buku` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_buku`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_buku` (`id_buku`, `judul`, `penulis`, `penerbit`, `harga`, `stock`, `kode_buku`) VALUES
(1,	'Pemrograman Java',	'Ikhsan Fauji',	'STI Media',	250000,	241,	'B001'),
(2,	'Spring Framework 5',	'Ikhsan Fauji',	'STI Media',	300000,	192,	'B002'),
(3,	'Back End Developer',	'Ikhsan Fauji',	'STI Media',	320000,	196,	'B003'),
(4,	'Belajar Pemrograman',	'Sepriyadi Rizky',	' Enigma',	156000,	65,	'B004'),
(5,	'Node Js Mastering',	'Sepriyadi Rizky',	'Enigma',	57800,	66,	'B005'),
(6,	'Angular 7',	'Sepriyadi Rizky',	'Enigma',	83623,	67,	'B006'),
(7,	'HTMLdan CSS',	'Ikhsan Fauji',	'Enigma',	120000,	45,	'B007'),
(8,	'Botsrap 4',	'Conan',	'Anime Indo',	120000,	12,	'B008');

DROP TABLE IF EXISTS `tb_transaksi`;
CREATE TABLE `tb_transaksi` (
  `id_transaksi` int(11) NOT NULL AUTO_INCREMENT,
  `id_buku` varchar(45) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `total_harga` int(20) DEFAULT NULL,
  `no_penjualan` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_transaksi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tb_transaksi` (`id_transaksi`, `id_buku`, `jumlah`, `total_harga`, `no_penjualan`) VALUES
(1,	'1',	2,	500000,	'TR-503'),
(2,	'2',	3,	900000,	'TR-1714'),
(3,	'1',	1,	250000,	'TR-2609'),
(4,	'2',	1,	300000,	'TR-2609'),
(5,	'3',	1,	320000,	'TR-788'),
(6,	'5',	1,	57800,	'TR-788'),
(7,	'2',	1,	300000,	'TR-3966'),
(8,	'3',	2,	640000,	'TR-3966'),
(9,	'2',	1,	300000,	'TR-656'),
(10,	'3',	1,	320000,	'TR-656'),
(11,	'1',	1,	250000,	'TR-4844'),
(12,	'2',	1,	300000,	'TR-4844'),
(13,	'1',	1,	250000,	'TR-4867'),
(14,	'2',	1,	300000,	'TR-4867');

-- 2019-03-20 00:54:37