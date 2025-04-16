-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quanlivanphongpham
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `fullName` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `userName` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(60) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `roleGroupId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `status` int DEFAULT NULL,
  `email` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `namsinh` date DEFAULT NULL,
  `diachi` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `dienthoai` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`userName`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES 
('Nguyễn Văn An','admin01',NULL,'RG1',1,'an.nguyen@example.com','2004-05-04','123 Đường Láng, Đống Đa, Hà Nội','0912345678'),
('Nguyễn Minh Phúc','admin02','$2a$12$Y87zSnx.tpFvieylSeXuo.agjb7swi3UVnoo6KVMY9xP5STj4zJhm','RG1',1,'nguyenanhtu4537@gmail.com','2005-05-06','123 Nguyễn Trãi, Phường Bến Thành, Quận 1, TPHCM','0901 234 567'),
('Phạm Quốc Nam','admin03',NULL,'RG1',1,'nam.pham@example.com','1980-05-03','90 Trần Hưng Đạo, Hà Nội','0936789012'),
('Lê Quốc Cường','manager01',NULL,'RG2',1,'cuong.le@example.com','2005-11-10','78 Lê Lợi, Quận 1, TP.HCM','0935123456'),
('Vũ Minh Khang','manager02',NULL,'RG2',1,'khang.vu@example.com','2003-04-25','34 Hùng Vương, Hải Phòng','0923456789'),
('Nhữ Đình Dũng','manager03','hunganime222','RG2',1,'hungan@gmail.com','2005-06-09','15 Nguyễn Thị Tú, Phường Bình Hưng Hòa B, Quận Bình Tân, TPHCM','0977 890 123'),
('Nguyễn Nguyên Bảo','manager04','$2a$12$myOaq0kATMzNkbxgzQEkPu8ht2K0pXOGzZMZo6nSBowq6EyoLo7tS','RG2',1,'a11611112003@gmail.com','2006-09-28','56 Hoàng Diệu, Phường 12, Quận 4, TPHCM','0785 436 658'),
('Phạm Thị Duyên','staff01',NULL,'RG3',1,'duyen.pham@example.com','2006-09-05','12 Trần Phú, Nha Trang, Khánh Hòa','0971234567'),
('Nguyễn Thị Lan','staff02',NULL,'RG4',1,'lan.nguyen@example.com','1997-01-12','67 Lê Đại Hành, Cần Thơ','0967890123'),
('Nhữ Đình Hùng','staff03','$2a$12$89As1J0AB0yrqGjnQUHtpevc6voGyvzAd8OvzkS1vGDo3YPO2P.Ia','RG3',1,'dinhhung116@gmail.com','2005-03-04','78 Trần Phú, Phường 4, Quận 5, TPHCM','0918 345 678'),
('Nguyễn Tường Huy','staff04','$2a$12$PhiTGBbHjHoB3dbS6BmCC.rzdMCBqDrdK9Y8Ae8GPcKe1RpHiWARO','RG4',1,'nguyentuonghuy@gmail.com','2005-02-18','250 Phạm Văn Đồng, Phường 1, Quận Gò Vấp, TPHCM','0786026878'),
('Trần Thị Bình','staff05',NULL,'RG3',1,'binh.tran@example.com','2003-07-22','45 Nguyễn Huệ, TP Huế','0987654321'),
('Hoàng Văn Em','user02',NULL,'RG5',1,'em.hoang@example.com','2004-12-30','56 Nguyễn Trãi, Thanh Hóa','0945678901'),
('Đỗ Thị Hoa','user03',NULL,'RG5',1,'hoa.do@example.com','2003-06-18','89 Phạm Văn Đồng, Đà Nẵng','0908765432');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietphieunhap`
--

DROP TABLE IF EXISTS `chitietphieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietphieunhap` (
  `maPhieu` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `maVatPham` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `soLuong` int DEFAULT NULL,
  `donGia` double DEFAULT NULL,
  PRIMARY KEY (`maPhieu`,`maVatPham`),
  KEY `FK_ChiTietPhieuNhap_VanPhongPham` (`maVatPham`),
  CONSTRAINT `FK_ChiTietPhieuNhap_PhieuNhap` FOREIGN KEY (`maPhieu`) REFERENCES `phieunhap` (`maPhieu`),
  CONSTRAINT `FK_ChiTietPhieuNhap_VanPhongPham` FOREIGN KEY (`maVatPham`) REFERENCES `vanphongpham` (`maVatPham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietphieunhap`
--

LOCK TABLES `chitietphieunhap` WRITE;
/*!40000 ALTER TABLE `chitietphieunhap` DISABLE KEYS */;
INSERT INTO `chitietphieunhap` VALUES ('PN1','PC01',30,19490),('PN1','PE01',26,23490),('PN10','ER01',24,23490),('PN10','NT01',44,16490),('PN10','RU01',46,22990),('PN10','RU11',35,17490),('PN11','PC02',45,18390),('PN11','PE02',22,23190),('PN11','RU02',67,7090),('PN12','ER02',54,21490),('PN12','NT02',13,18390),('PN13','ER02',23,21490),('PN13','PE03',25,17490),('PN14','ER03',42,10690),('PN14','PC01',15,19490),('PN14','RU03',46,20790),('PN15','NT03',42,22490),('PN15','PE03',32,17490),('PN16','ER04',36,9690),('PN16','PC03',35,23190),('PN16','RU04',34,19290),('PN17','ER03',19,10690),('PN17','PC01',18,19490),('PN18','ER04',27,9690),('PN18','NT04',26,25190),('PN18','PC04',25,19290),('PN18','PE03',20,17490),('PN18','RU05',16,7090),('PN19','ER02',22,21490),('PN19','ER03',21,10690),('PN19','NT05',24,24990),('PN19','RU06',25,9690),('PN2','PC05',16,25990),('PN2','PE04',17,20790),('PN20','ER05',15,19290),('PN20','NT06',10,18390),('PN20','PE05',29,7090),('PN20','RU01',30,22990),('PN21','PC06',25,18390),('PN21','RU01',35,22990),('PN22','ER01',32,23490),('PN22','NT04',36,25190),('PN22','PE01',35,23490),('PN22','PE06',22,17490),('PN23','ER06',32,17490),('PN23','PC01',24,19490),('PN23','RU07',36,19290),('PN24','NT07',20,116990),('PN25','PE07',26,22490),('PN26','NT04',37,25190),('PN27','PC07',15,19290),('PN28','ER02',28,21490),('PN28','NT04',29,25190),('PN29','ER03',21,10690),('PN3','ER01',22,23490),('PN3','ER07',31,10690),('PN3','NT04',35,25190),('PN3','PC08',34,18390),('PN30','NT08',33,21490),('PN30','PE08',25,15000),('PN30','RU01',25,22990),('PN31','ER06',24,17490),('PN31','NT03',22,22490),('PN31','PC02',23,18390),('PN32','ER08',23,8290),('PN32','NT01',24,16490),('PN32','PC04',21,19290),('PN32','PE09',15,11200),('PN32','RU08',17,24990),('PN33','ER01',18,23490),('PN33','NT03',19,22490),('PN33','PE08',16,15000),('PN33','RU02',22,7090),('PN34','ER08',24,8290),('PN34','NT02',21,18390),('PN34','PC02',26,18390),('PN34','PE03',20,17490),('PN35','ER03',20,10690),('PN35','NT08',25,21490),('PN35','PC06',24,18390),('PN35','RU09',26,9190),('PN36','PC01',20,19490),('PN37','ER03',31,10690),('PN37','NT02',32,18390),('PN37','PC01',33,19490),('PN38','PE03',30,17490),('PN38','RU10',20,15690);
/*!40000 ALTER TABLE `chitietphieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietphieuxuat`
--

DROP TABLE IF EXISTS `chitietphieuxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitietphieuxuat` (
  `maPhieu` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `maVatPham` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `soLuong` int DEFAULT NULL,
  `donGia` double DEFAULT NULL,
  PRIMARY KEY (`maPhieu`,`maVatPham`),
  KEY `FK_ChiTietPhieuXuat_VanPhongPham` (`maVatPham`),
  CONSTRAINT `FK_ChiTietPhieuXuat_PhieuXuat` FOREIGN KEY (`maPhieu`) REFERENCES `phieuxuat` (`maPhieu`),
  CONSTRAINT `FK_ChiTietPhieuXuat_VanPhongPham` FOREIGN KEY (`maVatPham`) REFERENCES `vanphongpham` (`maVatPham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietphieuxuat`
--

LOCK TABLES `chitietphieuxuat` WRITE;
/*!40000 ALTER TABLE `chitietphieuxuat` DISABLE KEYS */;
INSERT INTO `chitietphieuxuat` VALUES ('PX1','PC01',13,19490),('PX1','PE01',1,23490),('PX1','RU01',1,15000),('PX10','ER01',1,10690),('PX10','NT01',1,20790),('PX10','PC02',1,9690),('PX10','PE02',1,18390),('PX10','RU02',1,8290),('PX12','ER02',1,17490),('PX12','NT01',1,20790),('PX12','PE03',1,7090),('PX13','ER02',1,17490),('PX13','NT02',1,24990),('PX13','PC03',2,19290),('PX13','PE03',4,7090),('PX14','ER03',1,21490),('PX14','NT01',1,20790),('PX14','PC02',1,9690),('PX14','PE02',1,18390),('PX14','RU02',1,8290),('PX15','ER04',1,16490),('PX15','NT01',1,20790),('PX15','PC03',1,19290),('PX15','PE03',1,7090),('PX15','PE04',1,23190),('PX16','NT01',1,20790),('PX16','NT03',4,22490),('PX16','PC04',1,25990),('PX17','PC04',2,51980),('PX18','PE02',2,18390),('PX18','RU03',5,22990),('PX19','ER05',1,15690),('PX19','NT02',1,24990),('PX19','PC02',1,9690),('PX19','RU04',1,8990),('PX2','ER02',2,17490),('PX2','PC02',1,9690),('PX2','PC04',1,25990),('PX20','ER02',2,17490),('PX20','ER04',1,16490),('PX20','PC02',1,9690),('PX20','RU04',2,8990),('PX21','ER05',1,15690),('PX21','PC02',2,9690),('PX21','PC05',1,17490),('PX21','RU02',1,8290),('PX22','ER04',1,16490),('PX22','PC03',1,19290),('PX23','ER05',1,15690),('PX23','PC02',1,9690),('PX23','PE03',1,7090),('PX23','RU04',1,8990),('PX24','ER04',1,16490),('PX24','PC01',1,19490),('PX24','PE02',1,18390),('PX24','RU04',1,8990),('PX25','PC01',1,19490),('PX25','PC05',2,17490),('PX25','PE03',1,7090),('PX26','ER02',1,17490),('PX26','PC01',1,19490),('PX26','PC03',1,19290),('PX26','PE05',1,23490),('PX26','RU01',1,15000),('PX3','ER01',1,10690),('PX3','PE02',1,18390),('PX3','PE05',1,23490),('PX4','ER02',1,17490),('PX4','PC05',1,17490),('PX4','PE04',1,23190),('PX5','ER04',1,16490),('PX5','PC03',1,19290),('PX5','PC05',1,17490),('PX5','RU03',1,22990),('PX5','RU04',1,8990),('PX6','ER01',10,10690),('PX6','ER06',1,9990),('PX6','NT04',1,13090),('PX6','NT05',1,25190),('PX6','PE04',1,23190),('PX7','PC03',1,19290),('PX7','PC04',1,25990),('PX7','PE03',1,7090),('PX8','ER03',1,21490),('PX8','PC03',1,19290),('PX8','PE02',1,18390),('PX8','PE03',1,7090),('PX8','RU01',1,15000),('PX9','ER01',1,10690),('PX9','PC04',1,25990),('PX9','PE05',1,23490);
/*!40000 ALTER TABLE `chitietphieuxuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhacungcap` (
  `maNhaCungCap` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `tenNhaCungCap` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `Sdt` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `diaChi` varchar(150) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`maNhaCungCap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhacungcap`
--

LOCK TABLES `nhacungcap` WRITE;
/*!40000 ALTER TABLE `nhacungcap` DISABLE KEYS */;
INSERT INTO `nhacungcap` VALUES ('ANHSANG','Van phong pham Anh Sang','02873023456','261 - 263 Khanh Hoi, P2, Q4, TP. Ho Chi Minh'),('ANLOC','Cong ty TNHH MTV An Loc Viet','02838115345','622/16/5 Cong Hoa, Phuong 13, Quan Tan Binh, TP HCM'),('DINHHUNG','Cong ty VPP Dinh Hung','0786026878','572A/13 Tran Hung Dao, P.02, Q.05, TPHCM'),('HOANGHA','Van phong pham Hoang Ha','090 469 0212',' So 109 Le Thanh Nghi  TP Hai duong'),('HONGHA','Cong ty CP Van Phong Pham Hong Ha','02835109735','86/21 Phan Tay Ho, P. 7, Q. Phu Nhuan TP. Ho Chi Minh'),('SANGHA','Van phong pham Sang Ha','1900 1903','So 129 - 131, pho Le Thanh Nghi, Phuong Dong Tam, Quan Hai Ba Trung, Ha Noi'),('TANTIEN','Cong ty VPP Tan Tien','02253250888','So 4, Lo 2A Le Hong Phong, Ngo Quyen, Tp. Hai Phong'),('TAP','Van phong pham Thien An Phuoc','028 38125960','128 Tran Quang Khai, P. Tan Dinh, Q.1, TP.Ho Chi Minh'),('TOANCAU','VPP Kim Bien Toan Cau','0967567567','Tang 5, So 117-119-121 Nguyen Du, Phuong Ben Thanh, Quan 1, Thanh Pho Ho Chi Minh');
/*!40000 ALTER TABLE `nhacungcap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieunhap`
--

DROP TABLE IF EXISTS `phieunhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieunhap` (
  `maPhieu` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `thoiGianTao` timestamp NULL DEFAULT NULL,
  `nguoiTao` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `maNhaCungCap` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `tongTien` double NOT NULL,
  PRIMARY KEY (`maPhieu`),
  KEY `FK_PhieuNhap_NhaCungCap` (`maNhaCungCap`),
  KEY `FK_PhieuNhap_Account` (`nguoiTao`),
  CONSTRAINT `FK_PhieuNhap_Account` FOREIGN KEY (`nguoiTao`) REFERENCES `account` (`userName`),
  CONSTRAINT `FK_PhieuNhap_NhaCungCap` FOREIGN KEY (`maNhaCungCap`) REFERENCES `nhacungcap` (`maNhaCungCap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieunhap`
--

LOCK TABLES `phieunhap` WRITE;
/*!40000 ALTER TABLE `phieunhap` DISABLE KEYS */;
INSERT INTO `phieunhap` VALUES ('PN1','2022-12-01 13:59:09','staff01','HONGHA',42980),('PN10','2022-12-07 18:04:19','staff05','ANLOC',112440),('PN11','2022-12-07 18:48:21','staff01','HONGHA',98300),('PN12','2022-12-16 02:19:48','staff05','ANHSANG',39880),('PN13','2022-12-16 02:19:36','staff01','SANGHA',38980),('PN14','2022-12-08 12:28:57','staff05','HONGHA',50970),('PN15','2022-12-08 12:36:25','staff01','HONGHA',39980),('PN16','2022-12-08 16:30:48','staff05','ANLOC',52170),('PN17','2022-12-09 14:29:43','staff01','HONGHA',30180),('PN18','2022-12-09 17:08:19','staff05','HONGHA',78750),('PN19','2022-12-12 07:09:21','staff01','SANGHA',66860),('PN2','2022-12-01 13:59:23','staff05','HONGHA',46780),('PN20','2022-12-13 19:46:37','staff01','SANGHA',233270),('PN21','2022-12-14 11:54:21','staff05','SANGHA',1536180),('PN22','2022-12-14 12:32:09','staff01','HONGHA',89660),('PN23','2022-12-14 14:28:52','staff03','HONGHA',112540),('PN24','2022-12-14 14:41:23','staff01','HONGHA',2339800),('PN25','2022-12-14 15:18:25','staff03','TANTIEN',22490),('PN26','2022-12-14 20:01:37','staff01','TANTIEN',50380),('PN27','2022-12-14 20:02:31','staff03','TANTIEN',192900),('PN28','2022-12-15 10:43:36','staff01','TANTIEN',71870),('PN29','2022-12-16 02:19:55','staff01','TOANCAU',10690),('PN3','2022-12-03 03:58:18','staff01','HONGHA',88450),('PN30','2022-12-15 23:13:22','staff03','TANTIEN',59480),('PN31','2022-12-15 23:14:59','staff01','TANTIEN',58370),('PN32','2022-12-16 02:19:27','staff03','ANLOC',80260),('PN33','2022-12-16 13:09:45','staff01','TAP',68070),('PN34','2022-12-16 15:31:14','staff03','ANLOC',513500),('PN35','2022-12-16 15:36:48','staff01','ANLOC',59760),('PN36','2022-12-16 15:40:31','staff05','TANTIEN',389800),('PN37','2022-12-17 01:02:09','staff03','TANTIEN',85350),('PN38','2022-12-17 01:08:48','staff01','TANTIEN',838500),('PN4','2022-12-03 03:58:37','staff03','HOANGHA',53270),('PN5','2022-12-06 17:51:02','staff03','HONGHA',32070),('PN6','2022-12-06 18:50:32','staff01','HONGHA',38190),('PN7','2022-12-06 18:59:43','staff03','SANGHA',135530),('PN8','2022-12-06 19:15:01','staff03','HONGHA',46670),('PN9','2022-12-06 19:20:44','staff01','HONGHA',43480);
/*!40000 ALTER TABLE `phieunhap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieuxuat`
--

DROP TABLE IF EXISTS `phieuxuat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieuxuat` (
  `maPhieu` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `thoiGianTao` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nguoiTao` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `tongTien` double NOT NULL,
  PRIMARY KEY (`maPhieu`),
  KEY `FK_PhieuXuat_Account` (`nguoiTao`),
  CONSTRAINT `FK_PhieuXuat_Account` FOREIGN KEY (`nguoiTao`) REFERENCES `account` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieuxuat`
--

LOCK TABLES `phieuxuat` WRITE;
/*!40000 ALTER TABLE `phieuxuat` DISABLE KEYS */;
INSERT INTO `phieuxuat` VALUES ('PX1','2025-04-01 09:42:43','staff02',291860),('PX10','2025-04-01 09:42:43','staff02',57160),('PX12','2025-04-01 09:42:43','staff04',45370),('PX13','2025-04-01 09:42:43','staff02',109420),('PX14','2025-04-01 09:42:43','staff04',78650),('PX15','2025-04-01 09:42:43','staff04',86850),('PX16','2025-04-01 09:42:43','staff02',136740),('PX17','2025-04-01 09:42:43','staff04',51980),('PX18','2025-04-01 09:42:43','staff04',151730),('PX19','2025-04-01 09:42:43','staff02',59360),('PX2','2025-04-01 09:42:43','staff04',70660),('PX20','2025-04-01 09:42:43','staff04',79140),('PX21','2025-04-01 09:42:43','staff02',60850),('PX22','2025-04-01 09:42:43','staff04',35780),('PX23','2025-04-01 09:42:43','staff04',41460),('PX24','2025-04-01 09:42:43','staff02',63360),('PX25','2025-04-01 09:42:43','staff04',92550),('PX26','2025-04-01 09:42:43','staff02',94760),('PX3','2025-04-01 09:42:43','staff02',89350),('PX4','2025-04-01 09:42:43','staff02',58170),('PX5','2025-04-01 09:42:43','staff02',85250),('PX6','2025-04-01 09:42:43','staff04',71460),('PX7','2025-04-01 09:42:43','staff04',52370),('PX8','2025-04-01 09:42:43','staff02',39880),('PX9','2025-04-01 09:42:43','staff02',36680);
/*!40000 ALTER TABLE `phieuxuat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_group`
--

DROP TABLE IF EXISTS `role_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_group` (
  `roleGroupId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `roleGroupName` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`roleGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_group`
--

LOCK TABLES `role_group` WRITE;
/*!40000 ALTER TABLE `role_group` DISABLE KEYS */;
INSERT INTO `role_group` VALUES 
('RG1', 'Quản trị viên', 'Nhóm quyền dành cho quản trị viên, có đầy đủ quyền truy cập'),
('RG2', 'Quản lý kho', 'Nhóm quyền dành cho quản lý kho, có quyền quản lý nhập/xuất hàng và sản phẩm'),
('RG3', 'Nhân viên nhập', 'Nhóm quyền dành cho nhân viên nhập hàng, chỉ có quyền nhập hàng'),
('RG4', 'Nhân viên xuất', 'Nhóm quyền dành cho nhân viên xuất hàng, chỉ có quyền xuất hàng'),
('RG5', 'Người dùng', 'Nhóm quyền dành cho người dùng thông thường, chỉ có quyền xem');
/*!40000 ALTER TABLE `role_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `function`
--

DROP TABLE IF EXISTS `function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `function` (
  `functionId` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `functionName` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`functionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `function`
--

LOCK TABLES `function` WRITE;
/*!40000 ALTER TABLE `function` DISABLE KEYS */;
INSERT INTO `function` VALUES 
('F1', 'Quản lý khách hàng'),
('F2', 'Quản lý khu vực kho'),
('F3', 'Quản lý nhà cung cấp'),
('F4', 'Quản lý nhân viên'),
('F5', 'Quản lý nhập hàng'),
('F6', 'Quản lý nhóm quyền'),
('F7', 'Quản lý sản phẩm'),
('F8', 'Quản lý tài khoản'),
('F9', 'Quản lý thống kê'),
('F10', 'Quản lý thuộc tính'),
('F11', 'Quản lý xuất hàng');
/*!40000 ALTER TABLE `function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_group_function`
--

DROP TABLE IF EXISTS `role_group_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_group_function` (
  `roleGroupId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `functionId` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `action` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`roleGroupId`, `functionId`, `action`),
  KEY `fk_role_group_function_function` (`functionId`),
  CONSTRAINT `fk_role_group_function_function` FOREIGN KEY (`functionId`) REFERENCES `function` (`functionId`) ON DELETE CASCADE,
  CONSTRAINT `fk_role_group_function_role_group` FOREIGN KEY (`roleGroupId`) REFERENCES `role_group` (`roleGroupId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_group_function`
--

LOCK TABLES `role_group_function` WRITE;
/*!40000 ALTER TABLE `role_group_function` DISABLE KEYS */;
INSERT INTO `role_group_function` VALUES 
-- Quyền cho nhóm Quản trị viên (RG1) - có đầy đủ quyền
('RG1', 'F1', 'xem'),
('RG1', 'F1', 'tạo mới'),
('RG1', 'F1', 'cập nhật'),
('RG1', 'F1', 'xóa'),
('RG1', 'F2', 'xem'),
('RG1', 'F2', 'tạo mới'),
('RG1', 'F2', 'cập nhật'),
('RG1', 'F2', 'xóa'),
('RG1', 'F3', 'xem'),
('RG1', 'F3', 'tạo mới'),
('RG1', 'F3', 'cập nhật'),
('RG1', 'F3', 'xóa'),
('RG1', 'F4', 'xem'),
('RG1', 'F4', 'tạo mới'),
('RG1', 'F4', 'cập nhật'),
('RG1', 'F4', 'xóa'),
('RG1', 'F5', 'xem'),
('RG1', 'F5', 'tạo mới'),
('RG1', 'F5', 'cập nhật'),
('RG1', 'F5', 'xóa'),
('RG1', 'F6', 'xem'),
('RG1', 'F6', 'tạo mới'),
('RG1', 'F6', 'cập nhật'),
('RG1', 'F6', 'xóa'),
('RG1', 'F7', 'xem'),
('RG1', 'F7', 'tạo mới'),
('RG1', 'F7', 'cập nhật'),
('RG1', 'F7', 'xóa'),
('RG1', 'F8', 'xem'),
('RG1', 'F8', 'tạo mới'),
('RG1', 'F8', 'cập nhật'),
('RG1', 'F8', 'xóa'),
('RG1', 'F9', 'xem'),
('RG1', 'F9', 'tạo mới'),
('RG1', 'F9', 'cập nhật'),
('RG1', 'F9', 'xóa'),
('RG1', 'F10', 'xem'),
('RG1', 'F10', 'tạo mới'),
('RG1', 'F10', 'cập nhật'),
('RG1', 'F10', 'xóa'),
('RG1', 'F11', 'xem'),
('RG1', 'F11', 'tạo mới'),
('RG1', 'F11', 'cập nhật'),
('RG1', 'F11', 'xóa'),
-- Quyền cho nhóm Quản lý kho (RG2) - có quyền quản lý nhập/xuất hàng và sản phẩm
('RG2', 'F2', 'xem'),
('RG2', 'F2', 'tạo mới'),
('RG2', 'F2', 'cập nhật'),
('RG2', 'F2', 'xóa'),
('RG2', 'F5', 'xem'),
('RG2', 'F5', 'tạo mới'),
('RG2', 'F5', 'cập nhật'),
('RG2', 'F5', 'xóa'),
('RG2', 'F7', 'xem'),
('RG2', 'F7', 'tạo mới'),
('RG2', 'F7', 'cập nhật'),
('RG2', 'F7', 'xóa'),
('RG2', 'F11', 'xem'),
('RG2', 'F11', 'tạo mới'),
('RG2', 'F11', 'cập nhật'),
('RG2', 'F11', 'xóa'),
-- Quyền cho nhóm Nhân viên nhập (RG3) - chỉ có quyền nhập hàng
('RG3', 'F5', 'xem'),
('RG3', 'F5', 'tạo mới'),
('RG3', 'F5', 'cập nhật'),
-- Quyền cho nhóm Nhân viên xuất (RG4) - chỉ có quyền xuất hàng
('RG4', 'F11', 'xem'),
('RG4', 'F11', 'tạo mới'),
('RG4', 'F11', 'cập nhật'),
-- Quyền cho nhóm Người dùng (RG5) - chỉ có quyền xem
('RG5', 'F1', 'xem'),
('RG5', 'F2', 'xem'),
('RG5', 'F3', 'xem'),
('RG5', 'F4', 'xem'),
('RG5', 'F5', 'xem'),
('RG5', 'F6', 'xem'),
('RG5', 'F7', 'xem'),
('RG5', 'F8', 'xem'),
('RG5', 'F9', 'xem'),
('RG5', 'F10', 'xem'),
('RG5', 'F11', 'xem');
/*!40000 ALTER TABLE `role_group_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vanphongpham`
--

DROP TABLE IF EXISTS `vanphongpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vanphongpham` (
  `maVatPham` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `tenVatPham` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `soLuong` int NOT NULL DEFAULT '0',
  `loaiVatPham` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `gia` double NOT NULL DEFAULT '0',
  `thuongHieu` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `chatLieu` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `doDay` double DEFAULT NULL,
  `moTa` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `xuatXu` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `trangThai` int DEFAULT NULL,
  PRIMARY KEY (`maVatPham`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vanphongpham`
--

LOCK TABLES `vanphongpham` WRITE;
/*!40000 ALTER TABLE `vanphongpham` DISABLE KEYS */;
INSERT INTO `vanphongpham` VALUES ('ER01','Tẩy vừa Ánh Sáng',37,'Tay',15690,'Ánh Sáng','Cao su',NULL,'Tẩy tròn, mềm, vừa','Việt Nam',1),('ER02','Tẩy lớn Toàn Cầu',34,'Tay',21490,'Toàn Cầu','Cao su',NULL,'Tẩy mềm, hình chữ nhật, lớn','Việt Nam',1),('ER03','Tẩy nhỏ Thiên Long',118,'Tay',10690,'Thiên Long','Cao su',NULL,'Tẩy tròn, nhỏ','Việt Nam',1),('ER04','Tẩy lớn Hồng Hà',90,'Tay',17490,'Hồng Hà','Cao su',NULL,'Tẩy cứng, vuông, lớn','Việt Nam',1),('ER05','Tẩy nhỏ Bến Nghé',25,'Tay',16490,'Bến Nghé','Cao su',NULL,'Tẩy mềm, tròn, nhỏ','Việt Nam',1),('ER06','Tẩy vừa Toàn Cầu',34,'Tay',21490,'Toàn Cầu','Cao su',NULL,'Tẩy mềm, tròn, vừa','Việt Nam',1),('ER07','Tẩy lớn Ánh Sáng',34,'Tay',21490,'Ánh Sáng','Cao su',NULL,'Tẩy mềm, vuông, lớn','Việt Nam',1),('ER08','Tẩy vừa Hồng Hà',34,'Tay',21490,'Hồng Hà','Cao su',NULL,'Tẩy mềm, hình chữ nhật, vừa','Việt Nam',1),('NT01','Sổ tay 100 trang Thiên Long',3,'So tay',22490,'Thiên Long','Giay',5,'Sổ tay kẻ ngang','Việt Nam',1),('NT02','Sổ tay cao cấp Thiên Long',28,'So tay',25190,'Thiên Long','Giay',5,'Sổ tay bìa cứng','Việt Nam',1),('NT03','Sổ tay 80 trang Bến Nghé',23,'So tay',24990,'Bến Nghé','Giay',4,'Sổ tay bìa mềm','Việt Nam',1),('NT04','Sổ tay 60 trang Hồng Hà',19,'So tay',20790,'Hồng Hà','Giay',3,'Sổ tay kẻ ô','Việt Nam',1),('NT05','Sổ tay bìa cứng Bến Nghé',16,'So tay',25990,'Bến Nghé','Giay',5,'Sổ tay cao cấp','Việt Nam',1),('NT06','Sổ tay 50 trang Hồng Hà',53,'So tay',18390,'Hồng Hà','Giay',3,'Sổ tay nhỏ','Việt Nam',1),('NT07','Sổ tay cao cấp Bến Nghé',1,'So tay',25000,'Bến Nghé','Giay',5,'Sổ tay bìa cứng','Việt Nam',1),('NT08','Sổ tay 50 trang An Lộc',21,'So tay',11200,'An Lộc','Giay',3,'Sổ tay mềm','Việt Nam',1),('PC01','Bút chì 2B Sang Hà',19,'But chi',9990,'Sang Hà','Go',NULL,'Bút chì 2B','Việt Nam',1),('PC02','Bút chì HB Sang Hà',18,'But chi',19490,'Sang Hà','Go',NULL,'Bút chì HB','Việt Nam',1),('PC03','Bút chì mềm Ánh Sáng',11,'But chi',19290,'Ánh Sáng','Go',NULL,'Bút chì mềm','Việt Nam',1),('PC04','Bút chì tiêu chuẩn Bến Nghé',19,'But chi',17490,'Bến Nghé','Go',NULL,'Bút chì HB','Việt Nam',1),('PC05','Bút chì 2B Thiên An Phước',19,'But chi',8990,'Thiên An Phước','Go',NULL,'Bút chì 2B','Việt Nam',1),('PC06','Bút chì HB Thiên An Phước',33,'But chi',9190,'Thiên An Phước','Go',NULL,'Bút chì HB','Việt Nam',1),('PC07','Bút chì cứng Ánh Sáng',11,'But chi',19290,'Ánh Sáng','Go',NULL,'Bút chì cứng','Việt Nam',1),('PC08','Bút chì mềm Hồng Hà',11,'But chi',19290,'Hồng Hà','Go',NULL,'Bút chì mềm','Việt Nam',1),('PE01','Bút bi cao cấp Thiên Long',36,'But',23490,'Thiên Long','Nhua',NULL,'Bút bi mực xanh','Việt Nam',1),('PE02','Bút bi thường Hồng Hà',23,'But',13090,'Hồng Hà','Nhua',NULL,'Bút bi mực đen','Việt Nam',1),('PE03','Bút bi cao cấp Hồng Hà',22,'But',23190,'Hồng Hà','Nhua',NULL,'Bút bi 0.7mm','Việt Nam',1),('PE04','Bút bi mực đen Thiên Long',20,'But',23490,'Thiên Long','Nhua',NULL,'Bút bi 0.5mm','Việt Nam',1),('PE05','Bút bi mực xanh Hồng Hà',60,'But',18390,'Hồng Hà','Nhua',NULL,'Bút bi 0.9mm','Việt Nam',1),('PE06','Bút bi thường Thiên An Phước',16,'But',7090,'Thiên An Phước','Nhua',NULL,'Bút bi mực đen','Việt Nam',1),('PE07','Bút bi mực xanh Hồng Hà',60,'But',18390,'Hồng Hà','Nhua',NULL,'Bút bi 0.8mm','Việt Nam',1),('PE08','Bút bi mực xanh Ánh Sáng',60,'But',18390,'Ánh Sáng','Nhua',NULL,'Bút bi 0.7mm','Việt Nam',1),('PE09','Bút bi mực đỏ Hồng Hà',60,'But',18390,'Hồng Hà','Nhua',NULL,'Bút bi mực đỏ','Việt Nam',1),('PE10','Bút bi mực xanh Bến Nghé',60,'But',18390,'Bến Nghé','Nhua',NULL,'Bút bi 0.7mm','Việt Nam',1),('RU01','Thước nhựa 30cm An Lộc',62,'Thuoc',22990,'An Lộc','Nhua',NULL,'Thước kẻ thẳng','Việt Nam',1),('RU02','Thước nhựa 20cm Thiên Long',83,'Thuoc',15000,'Thiên Long','Nhua',NULL,'Thước kẻ thẳng','Việt Nam',1),('RU03','Thước nhựa 15cm Thiên An Phước',19,'Thuoc',9690,'Thiên An Phước','Nhua',NULL,'Thước kẻ ngắn','Việt Nam',1),('RU04','Thước nhựa 20cm Thiên An Phước',30,'Thuoc',8290,'Thiên An Phước','Nhua',NULL,'Thước kẻ thẳng','Việt Nam',1),('RU05','Thước nhựa 25cm Thiên An Phước',71,'Thuoc',11990,'Thiên An Phước','Nhua',NULL,'Thước kẻ dài','Việt Nam',1),('RU06','Thước nhựa 10cm Thiên An Phước',19,'Thuoc',9690,'Thiên An Phước','Nhua',NULL,'Thước kẻ ngắn','Việt Nam',1),('RU07','Thước nhựa 15cm Hồng Hà',19,'Thuoc',9550,'Hồng Hà','Nhua',NULL,'Thước kẻ ngắn','Việt Nam',1),('RU08','Thước nhựa 20cm An Lộc',22,'Thuoc',7445,'An Lộc','Nhua',NULL,'Thước kẻ ngắn','Việt Nam',1),('RU09','Thước nhựa 30cm Hồng Hà',17,'Thuoc',8566,'Hồng Hà','Nhua',NULL,'Thước kẻ dài','Việt Nam',1),('RU10','Thước nhựa 15cm Ánh Sáng',20,'Thuoc',9544,'Ánh Sáng','Nhua',NULL,'Thước kẻ ngắn','Việt Nam',1),('RU11','Thước nhựa 20cm Hồng Hà',23,'Thuoc',12010,'Hồng Hà','Sat',NULL,'Thước kẻ ngắn','Việt Nam',1);
/*!40000 ALTER TABLE `vanphongpham` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-03 13:26:31