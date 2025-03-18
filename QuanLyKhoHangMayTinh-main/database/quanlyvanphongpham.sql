-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 17, 2022 at 04:53 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlivanphongpham`
--

-- --------------------------------------------------------

--
-- Table structure for table `Account`
--

CREATE TABLE `Account` (
  `fullName` varchar(50) DEFAULT NULL,
  `userName` varchar(50) NOT NULL,
  `password` varchar(60) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Account`
--

INSERT INTO `Account` (`fullName`, `userName`, `password`, `role`, `status`, `email`) VALUES
('Admin', 'admin', '$2a$12$Y87zSnx.tpFvieylSeXuo.agjb7swi3UVnoo6KVMY9xP5STj4zJhm', 'Admin', 1, 'nguyenanhtu4537@gmail.com'),
('Nguyễn Tường Huy', 'hytu', '$2a$12$PhiTGBbHjHoB3dbS6BmCC.rzdMCBqDrdK9Y8Ae8GPcKe1RpHiWARO', 'Nhân viên xuất', 1, 'nguyentuonghuy@gmail.com'),
('Nhữ Đình Hùng', 'hung1122', '$2a$12$89As1J0AB0yrqGjnQUHtpevc6voGyvzAd8OvzkS1vGDo3YPO2P.Ia', 'Nhân viên nhập', 1, 'dinhhung116@gmail.com'),
('Nguyễn Thiên Ân', 'thienan', '$2a$12$myOaq0kATMzNkbxgzQEkPu8ht2K0pXOGzZMZo6nSBowq6EyoLo7tS', 'Quản lý kho', 1, 'a11611112003@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `ChiTietPhieuNhap`
--

CREATE TABLE `ChiTietPhieuNhap` (
  `maPhieu` varchar(50) NOT NULL,
  `maVatPham` varchar(50) NOT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `donGia` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ChiTietPhieuNhap`
--

INSERT INTO `ChiTietPhieuNhap` (`maPhieu`, `maVatPham`, `soLuong`, `donGia`) VALUES
('PN1', 'PE01', 1, 23490),
('PN1', 'PC01', 1, 19490),
('PN10', 'RU01', 1, 22990),
('PN10', 'ER01', 1, 23490),
('PN10', 'NT01', 4, 16490),
('PN11', 'PE02', 1, 23190),
('PN11', 'PC02', 1, 18390),
('PN11', 'RU02', 8, 7090),
('PN12', 'ER02', 1, 21490),
('PN12', 'NT02', 1, 18390),
('PN13', 'ER02', 1, 21490),
('PN13', 'PE03', 1, 17490),
('PN14', 'PC01', 1, 19490),
('PN14', 'RU03', 1, 20790),
('PN14', 'ER03', 1, 10690),
('PN15', 'NT03', 1, 22490),
('PN15', 'PE03', 1, 17490),
('PN16', 'PC03', 1, 23190),
('PN16', 'RU04', 1, 19290),
('PN16', 'ER04', 1, 9690),
('PN17', 'PC01', 1, 19490),
('PN17', 'ER03', 1, 10690),
('PN18', 'NT04', 1, 25190),
('PN18', 'PC04', 1, 19290),
('PN18', 'PE03', 1, 17490),
('PN18', 'ER04', 1, 9690),
('PN18', 'RU05', 1, 7090),
('PN19', 'NT05', 1, 24990),
('PN19', 'ER02', 1, 21490),
('PN19', 'ER03', 1, 10690),
('PN19', 'RU06', 1, 9690),
('PN2', 'PE04', 1, 20790),
('PN2', 'PC05', 1, 25990),
('PN20', 'RU01', 1, 22990),
('PN20', 'NT06', 10, 18390),
('PN20', 'ER05', 1, 19290),
('PN20', 'PE05', 1, 7090),
('PN21', 'RU01', 45, 22990),
('PN21', 'PC06', 25, 18390),
('PN22', 'PE01', 1, 23490),
('PN22', 'NT04', 1, 25190),
('PN22', 'ER01', 1, 23490),
('PN22', 'PE06', 1, 17490),
('PN23', 'PC01', 2, 19490),
('PN23', 'RU07', 2, 19290),
('PN23', 'ER06', 2, 17490),
('PN24', 'NT07', 20, 116990),
('PN25', 'PE07', 1, 22490),
('PN26', 'NT04', 2, 25190),
('PN27', 'PC07', 10, 19290),
('PN28', 'NT04', 2, 25190),
('PN28', 'ER02', 1, 21490),
('PN29', 'ER03', 1, 10690),
('PN3', 'NT04', 1, 25190),
('PN3', 'ER01', 1, 23490),
('PN3', 'PC08', 1, 18390),
('PN3', 'ER07', 2, 10690),
('PN30', 'RU01', 1, 22990),
('PN30', 'NT08', 1, 21490),
('PN30', 'PE08', 1, 15000),
('PN31', 'NT03', 1, 22490),
('PN31', 'PC02', 1, 18390),
('PN31', 'ER06', 1, 17490),
('PN32', 'RU08', 1, 24990),
('PN32', 'PC04', 1, 19290),
('PN32', 'NT01', 1, 16490),
('PN32', 'ER08', 1, 8290),
('PN32', 'PE09', 1, 11200),
('PN33', 'NT03', 1, 22490),
('PN33', 'ER01', 1, 23490),
('PN33', 'PE08', 1, 15000),
('PN33', 'RU02', 1, 7090),
('PN34', 'PC02', 7, 18390),
('PN34', 'PE03', 20, 17490),
('PN34', 'NT02', 1, 18390),
('PN34', 'ER08', 2, 8290),
('PN35', 'NT08', 1, 21490),
('PN35', 'ER03', 1, 10690),
('PN35', 'PC06', 1, 18390),
('PN35', 'RU09', 1, 9190),
('PN36', 'PC01', 20, 19490),
('PN37', 'PC01', 1, 19490),
('PN37', 'ER03', 1, 10690),
('PN37', 'NT02', 3, 18390),
('PN38', 'RU10', 20, 15690),
('PN38', 'PE03', 30, 17490),
('PN4', 'PC04', 1, 19290),
('PN4', 'ER06', 1, 17490),
('PN4', 'NT01', 1, 16490),
('PN5', 'ER07', 3, 10690),
('PN6', 'PE02', 1, 23190),
('PN6', 'PE08', 1, 15000),
('PN7', 'NT04', 1, 25190),
('PN7', 'PC02', 1, 18390),
('PN7', 'RU05', 5, 18390),
('PN8', 'PC01', 1, 19490),
('PN8', 'ER03', 1, 10690),
('PN8', 'NT01', 1, 16490),
('PN9', 'PE10', 1, 25990),
('PN9', 'PE03', 1, 17490);

-- --------------------------------------------------------

--
-- Table structure for table `ChiTietPhieuXuat`
--

CREATE TABLE `ChiTietPhieuXuat` (
  `maPhieu` varchar(50) NOT NULL,
  `maVatPham` varchar(50) NOT NULL,
  `soLuong` int(11) DEFAULT NULL,
  `donGia` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ChiTietPhieuXuat`
--

INSERT INTO `ChiTietPhieuXuat` (`maPhieu`, `maVatPham`, `soLuong`, `donGia`) VALUES
('PX1', 'PE01', 1, 23490),
('PX1', 'PC01', 13, 19490),
('PX1', 'RU01', 1, 15000),
('PX10', 'NT01', 1, 20790),
('PX10', 'ER01', 1, 10690),
('PX10', 'PE02', 1, 18390),
('PX10', 'PC02', 1, 9690),
('PX10', 'RU02', 1, 8290),
('PX12', 'NT01', 1, 20790),
('PX12', 'ER02', 1, 17490),
('PX12', 'PE03', 1, 7090),
('PX13', 'NT02', 1, 24990),
('PX13', 'PC03', 2, 19290),
('PX13', 'ER02', 1, 17490),
('PX13', 'PE03', 4, 7090),
('PX14', 'NT01', 1, 20790),
('PX14', 'ER03', 1, 21490),
('PX14', 'PE02', 1, 18390),
('PX14', 'PC02', 1, 9690),
('PX14', 'RU02', 1, 8290),
('PX15', 'PE04', 1, 23190),
('PX15', 'NT01', 1, 20790),
('PX15', 'PC03', 1, 19290),
('PX15', 'ER04', 1, 16490),
('PX15', 'PE03', 1, 7090),
('PX16', 'NT03', 4, 22490),
('PX16', 'NT01', 1, 20790),
('PX16', 'PC04', 1, 25990),
('PX17', 'PC04', 2, 25990),
('PX18', 'RU03', 5, 22990),
('PX18', 'PE02', 2, 18390),
('PX19', 'NT02', 1, 24990),
('PX19', 'ER05', 1, 15690),
('PX19', 'PC02', 1, 9690),
('PX19', 'RU04', 1, 8990),
('PX2', 'PC04', 1, 25990),
('PX2', 'ER02', 2, 17490),
('PX2', 'PC02', 1, 9690),
('PX20', 'ER02', 2, 17490),
('PX20', 'ER04', 1, 16490),
('PX20', 'PC02', 1, 9690),
('PX20', 'RU04', 2, 8990),
('PX21', 'ER05', 1, 15690),
('PX21', 'PC05', 1, 17490),
('PX21', 'PC02', 2, 9690),
('PX21', 'RU02', 1, 8290),
('PX22', 'PC03', 1, 19290),
('PX22', 'ER04', 1, 16490),
('PX23', 'ER05', 1, 15690),
('PX23', 'PC02', 1, 9690),
('PX23', 'PE03', 1, 7090),
('PX23', 'RU04', 1, 8990),
('PX24', 'PC01', 1, 19490),
('PX24', 'PE02', 1, 18390),
('PX24', 'ER04', 1, 16490),
('PX24', 'RU04', 1, 8990),
('PX25', 'PC01', 1, 19490),
('PX25', 'PC05', 2, 17490),
('PX25', 'PE03', 1, 7090),
('PX26', 'PC01', 1, 19490),
('PX26', 'PE05', 1, 23490),
('PX26', 'RU01', 1, 15000),
('PX26', 'PC03', 1, 19290),
('PX26', 'ER02', 1, 17490),
('PX3', 'PE05', 1, 23490),
('PX3', 'ER01', 1, 10690),
('PX3', 'PE02', 1, 18390),
('PX4', 'PE04', 1, 23190),
('PX4', 'ER02', 1, 17490),
('PX4', 'PC05', 1, 17490),
('PX5', 'RU03', 1, 22990),
('PX5', 'PC03', 1, 19290),
('PX5', 'PC05', 1, 17490),
('PX5', 'ER04', 1, 16490),
('PX5', 'RU04', 1, 8990),
('PX6', 'NT04', 1, 13090),
('PX6', 'ER06', 1, 9990),
('PX6', 'NT05', 1, 25190),
('PX6', 'PE04', 1, 23190),
('PX7', 'PC04', 1, 25990),
('PX7', 'PC03', 1, 19290),
('PX7', 'PE03', 1, 7090),
('PX8', 'ER03', 1, 21490),
('PX8', 'RU01', 1, 15000),
('PX8', 'PC03', 1, 19290),
('PX8', 'PE02', 1, 18390),
('PX8', 'PE03', 1, 7090),
('PX9', 'PC04', 1, 25990),
('PX9', 'PE05', 1, 23490),
('PX9', 'ER01', 1, 10690);

-- --------------------------------------------------------

--
-- Table structure for table `VanPhongPham`
--

CREATE TABLE `VanPhongPham` (
  `maVatPham` varchar(50) NOT NULL,          -- Mã vật phẩm
  `tenVatPham` varchar(100) DEFAULT NULL,    -- Tên vật phẩm
  `soLuong` int(11) NOT NULL DEFAULT 0,      -- Số lượng
  `loaiVatPham` varchar(50) DEFAULT NULL,    -- Loại vật phẩm (bút, giấy, sổ, v.v.)
  `gia` double NOT NULL DEFAULT 0,           -- Giá
  `thuongHieu` varchar(50) DEFAULT NULL,     -- Thương hiệu
  `chatLieu` varchar(50) DEFAULT NULL,       -- Chất liệu (nhựa, gỗ, giấy)
  `doDay` double DEFAULT NULL,               -- Độ dày (mm, áp dụng cho giấy/sổ)
  `moTa` varchar(100) DEFAULT NULL,          -- Mô tả
  `xuatXu` varchar(50) DEFAULT NULL,         -- Xuất xứ
  `trangThai` int(11) DEFAULT NULL           -- Trạng thái (còn hàng/hết hàng)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `VanPhongPham`
--

INSERT INTO `VanPhongPham` (`maVatPham`, `tenVatPham`, `soLuong`, `loaiVatPham`, `gia`, `thuongHieu`, `chatLieu`, `doDay`, `moTa`, `xuatXu`, `trangThai`) VALUES
('PE01', 'Bút bi cao cấp', 36, 'Bút', 23490, 'Thiên Long', 'Nhựa', NULL, 'Bút bi mực xanh', 'Trung Quốc', 0),
('PE02', 'Bút bi thường', 23, 'Bút', 13090, 'Hồng Hà', 'Nhựa', NULL, 'Bút bi mực đen', 'Trung Quốc', 0),
('PC01', 'Bút chì 2B', 19, 'Bút chì', 9990, 'Sang Hà', 'Gỗ', NULL, 'Bút chì mềm', 'Trung Quốc', 1),
('NT01', 'Sổ tay 100 trang', 3, 'Sổ tay', 22490, 'Thiên Long', 'Giấy', 5.0, 'Sổ tay kẻ ngang', 'Trung Quốc', 1),
('NT02', 'Sổ tay cao cấp', 28, 'Sổ tay', 25190, 'Thiên Long', 'Giấy', 5.0, 'Sổ tay bìa cứng', 'Trung Quốc', 1),
('RU01', 'Thước nhựa 30cm', 62, 'Thước', 22990, 'An Lộc', 'Nhựa', NULL, 'Thước kẻ thẳng', 'Trung Quốc', 1),
('PE03', 'Bút bi mực đỏ', 22, 'Bút', 23190, 'Hồng Hà', 'Nhựa', NULL, 'Bút bi 0.7mm', 'Trung Quốc', 1),
('NT03', 'Sổ tay 80 trang', 23, 'Sổ tay', 24990, 'Bến Nghé', 'Giấy', 4.0, 'Sổ tay bìa mềm', 'Trung Quốc', 1),
('PC02', 'Bút chì HB', 18, 'Bút chì', 19490, 'Sang Hà', 'Gỗ', NULL, 'Bút chì tiêu chuẩn', 'Trung Quốc', 1),
('NT04', 'Sổ tay 60 trang', 19, 'Sổ tay', 20790, 'Hồng Hà', 'Giấy', 3.0, 'Sổ tay kẻ ô', 'Trung Quốc', 1),
('NT05', 'Sổ tay bìa cứng', 16, 'Sổ tay', 25990, 'Bến Nghé', 'Giấy', 5.0, 'Sổ tay cao cấp', 'Trung Quốc', 1),
('PE04', 'Bút bi mực đen', 20, 'Bút', 23490, 'Thiên Long', 'Nhựa', NULL, 'Bút bi 0.5mm', 'Trung Quốc', 1),
('ER01', 'Tẩy mềm', 37, 'Tẩy', 15690, 'Ánh Sáng', 'Cao su', NULL, 'Tẩy trắng mềm', 'Trung Quốc', 1),
('ER02', 'Tẩy lớn', 34, 'Tẩy', 21490, 'Toàn Cầu', 'Cao su', NULL, 'Tẩy hình chữ nhật', 'Trung Quốc', 1),
('NT06', 'Sổ tay 50 trang', 53, 'Sổ tay', 18390, 'Hồng Hà', 'Giấy', 3.0, 'Sổ tay nhỏ', 'Trung Quốc', 1),
('RU02', 'Thước nhựa 20cm', 83, 'Thước', 15000, 'Thiên Long', 'Nhựa', NULL, 'Thước kẻ thẳng', 'Trung Quốc', 1),
('ER03', 'Tẩy nhỏ', 118, 'Tẩy', 10690, 'Thiên Long', 'Cao su', NULL, 'Tẩy tròn', 'Trung Quốc', 1),
('PC03', 'Bút chì mềm', 11, 'Bút chì', 19290, 'Ánh Sáng', 'Gỗ', NULL, 'Bút chì 2B', 'Trung Quốc', 1),
('ER04', 'Tẩy hình vuông', 90, 'Tẩy', 17490, 'Hồng Hà', 'Cao su', NULL, 'Tẩy trắng', 'Trung Quốc', 1),
('PC04', 'Bút chì tiêu chuẩn', 19, 'Bút chì', 17490, 'Bến Nghé', 'Gỗ', NULL, 'Bút chì HB', 'Trung Quốc', 1),
('PE05', 'Bút bi mực xanh', 60, 'Bút', 18390, 'Hồng Hà', 'Nhựa', NULL, 'Bút bi 0.7mm', 'Trung Quốc', 1),
('ER05', 'Tẩy mềm nhỏ', 25, 'Tẩy', 16490, 'Bến Nghé', 'Cao su', NULL, 'Tẩy hình tròn', 'Trung Quốc', 1),
('RU03', 'Thước nhựa 15cm', 19, 'Thước', 9690, 'Thiên An Phước', 'Nhựa', NULL, 'Thước kẻ ngắn', 'Việt Nam', 1),
('PE06', 'Bút bi thường', 16, 'Bút', 7090, 'Thiên An Phước', 'Nhựa', NULL, 'Bút bi mực đen', 'Việt Nam', 1),
('RU04', 'Thước nhựa 20cm', 30, 'Thước', 8290, 'Thiên An Phước', 'Nhựa', NULL, 'Thước kẻ thẳng', 'Việt Nam', 1),
('PC05', 'Bút chì 2B', 19, 'Bút chì', 8990, 'Thiên An Phước', 'Gỗ', NULL, 'Bút chì mềm', 'Việt Nam', 1),
('NT07', 'Sổ tay cao cấp', 1, 'Sổ tay', 25000, 'Bến Nghé', 'Giấy', 5.0, 'Sổ tay bìa cứng', 'Việt Nam', 1),
('RU05', 'Thước nhựa 25cm', 71, 'Thước', 11990, 'Thiên An Phước', 'Nhựa', NULL, 'Thước kẻ dài', 'Việt Nam', 1),
('PC06', 'Bút chì HB', 33, 'Bút chì', 9190, 'Thiên An Phước', 'Gỗ', NULL, 'Bút chì tiêu chuẩn', 'Việt Nam', 1),
('NT08', 'Sổ tay 50 trang', 21, 'Sổ tay', 11200, 'An Lộc', 'Giấy', 3.0, 'Sổ tay bìa mềm', 'Việt Nam', 1);
-- --------------------------------------------------------

--
-- Table structure for table `NhaCungCap`
--

CREATE TABLE `NhaCungCap` (
  `maNhaCungCap` varchar(50) NOT NULL,
  `tenNhaCungCap` varchar(50) DEFAULT NULL,
  `Sdt` varchar(50) DEFAULT NULL,
  `diaChi` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `NhaCungCap`
--

INSERT INTO `NhaCungCap` (`maNhaCungCap`, `tenNhaCungCap`, `Sdt`, `diaChi`) VALUES
('HONGHA', 'Công ty CP Văn Phòng Phẩm Hồng Hà', '02835109735', '86/21 Phan Tây Hồ, P. 7, Q. Phú Nhuận TP. Hồ Chí Minh'),
('ANLOC', 'Công ty TNHH MTV An Lộc Việt', '02838115345', '622/16/5 Cộng Hòa, Phường 13, Quận Tân Bình, TP HCM		'),
('ANHSANG', 'Văn phòng phẩm Ánh Sáng', '02873023456', '261 - 263 Khánh Hội, P2, Q4, TP. Hồ Chí Minh'),
('SANGHA', 'Văn phòng phẩm Sang Hà', '1900 1903', 'Số 129 - 131, phố Lê Thanh Nghị, Phường Đồng Tâm, Quận Hai Bà Trưng, Hà Nội'),
('TANTIEN', 'Công ty VPP Tân Tiến', '02253250888', 'Số 4, Lô 2A Lê Hồng Phong, Ngô Quyền, Tp. Hải Phòng'),
('TOANCAU', 'VPP Kim Biên Toàn Cầu', '0967567567', 'Tầng 5, Số 117-119-121 Nguyễn Du, Phường Bến Thành, Quận 1, Thành Phố Hồ Chí Minh'),
('TAP', 'Văn phòng phẩm Thiên An Phước', '028 38125960', '128 Trần Quang Khải, P. Tân Định, Q.1, TP.Hồ Chí Minh'),
('HOANGHA', 'Văn phòng phẩm Hoàng Hà', '090 469 0212', ' Số 109 Lê Thanh Nghị  TP Hải dương');

-- --------------------------------------------------------

--
-- Table structure for table `PhieuNhap`
--

CREATE TABLE `PhieuNhap` (
  `maPhieu` varchar(50) NOT NULL,
  `thoiGianTao` timestamp NULL DEFAULT NULL,
  `nguoiTao` varchar(50) DEFAULT NULL,
  `maNhaCungCap` varchar(50) DEFAULT NULL,
  `tongTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `PhieuNhap`
--

INSERT INTO `PhieuNhap` (`maPhieu`, `thoiGianTao`, `nguoiTao`, `maNhaCungCap`, `tongTien`) VALUES
('PN1', '2022-12-01 13:59:09', 'admin', 'HONGHA', 42980),
('PN10', '2022-12-07 18:04:19', 'admin', 'ANLOC', 112440),
('PN11', '2022-12-07 18:48:21', 'admin', 'HONGHA', 98300),
('PN12', '2022-12-16 02:19:48', 'admin', 'ANHSANG', 39880),
('PN13', '2022-12-16 02:19:36', 'admin', 'SANGHA', 38980),
('PN14', '2022-12-08 12:28:57', 'admin', 'HONGHA', 50970),
('PN15', '2022-12-08 12:36:25', 'admin', 'HONGHA', 39980),
('PN16', '2022-12-08 16:30:48', 'admin', 'ANLOC', 52170),
('PN17', '2022-12-09 14:29:43', 'admin', 'HONGHA', 30180),
('PN18', '2022-12-09 17:08:19', 'admin', 'HONGHA', 78750),
('PN19', '2022-12-12 07:09:21', 'admin', 'SANGHA', 66860),
('PN2', '2022-12-01 13:59:23', 'admin', 'HONGHA', 46780),
('PN20', '2022-12-13 19:46:37', 'admin', 'SANGHA', 233270),
('PN21', '2022-12-14 11:54:21', 'admin', 'SANGHA', 1536180),
('PN22', '2022-12-14 12:32:09', 'admin', 'HONGHA', 89660),
('PN23', '2022-12-14 14:28:52', 'admin', 'HONGHA', 112540),
('PN24', '2022-12-14 14:41:23', 'admin', 'HONGHA', 2339800),
('PN25', '2022-12-14 15:18:25', 'admin', 'TANTIEN', 22490),
('PN26', '2022-12-14 20:01:37', 'admin', 'TANTIEN', 50380),
('PN27', '2022-12-14 20:02:31', 'admin', 'TANTIEN', 192900),
('PN28', '2022-12-15 10:43:36', 'admin', 'TANTIEN', 71870),
('PN29', '2022-12-16 02:19:55', 'admin', 'TOANCAU', 10690),
('PN3', '2022-12-03 03:58:18', 'admin', 'HONGHA', 88450),
('PN30', '2022-12-15 23:13:22', 'admin', 'TANTIEN', 59480),
('PN31', '2022-12-15 23:14:59', 'dinhhung1122', 'TANTIEN', 58370),
('PN32', '2022-12-16 02:19:27', 'admin', 'ANLOC', 80260),
('PN33', '2022-12-16 13:09:45', 'dinhhung1122', 'TAP', 68070),
('PN34', '2022-12-16 15:31:14', 'admin', 'ANLOC', 513500),
('PN35', '2022-12-16 15:36:48', 'admin', 'ANLOC', 59760),
('PN36', '2022-12-16 15:40:31', 'admin', 'TANTIEN', 389800),
('PN37', '2022-12-17 01:02:09', 'admin', 'TANTIEN', 85350),
('PN38', '2022-12-17 01:08:48', 'admin', 'TANTIEN', 838500),
('PN4', '2022-12-03 03:58:37', 'admin', 'HOANGHA', 53270),
('PN5', '2022-12-06 17:51:02', 'admin', 'HONGHA', 32070),
('PN6', '2022-12-06 18:50:32', 'admin', 'HONGHA', 38190),
('PN7', '2022-12-06 18:59:43', 'admin', 'SANGHA', 135530),
('PN8', '2022-12-06 19:15:01', 'admin', 'HONGHA', 46670),
('PN9', '2022-12-06 19:20:44', 'admin', 'HONGHA', 43480);

-- --------------------------------------------------------

--
-- Table structure for table `PhieuXuat`
--

CREATE TABLE `PhieuXuat` (
  `maPhieu` varchar(50) NOT NULL,
  `thoiGianTao` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `nguoiTao` varchar(50) NOT NULL,
  `tongTien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `PhieuXuat`
--

INSERT INTO `PhieuXuat` (`maPhieu`, `thoiGianTao`, `nguoiTao`, `tongTien`) VALUES
('PX1', '2022-12-01 14:10:44', 'admin', 291860),
('PX10', '2022-12-07 18:41:08', 'admin', 57160),
('PX12', '2022-12-07 19:06:56', 'admin', 45370),
('PX13', '2022-12-08 12:31:48', 'admin', 109420),
('PX14', '2022-12-08 16:30:10', 'admin', 78650),
('PX15', '2022-12-12 22:31:09', 'admin', 86850),
('PX16', '2022-12-14 15:04:47', 'admin', 136740),
('PX17', '2022-12-14 15:34:07', 'admin', 51980),
('PX18', '2022-12-15 20:32:57', 'admin', 151730),
('PX19', '2022-12-16 02:20:15', 'Admin', 59360),
('PX2', '2022-12-04 16:45:43', 'admin', 70660),
('PX20', '2022-12-16 13:26:33', 'Admin', 79140),
('PX21', '2022-12-16 13:36:43', 'Admin', 60850),
('PX22', '2022-12-16 16:39:41', 'Admin', 35780),
('PX23', '2022-12-16 17:18:54', 'Admin', 41460),
('PX24', '2022-12-16 17:25:10', 'Admin', 63360),
('PX25', '2022-12-16 18:51:31', 'Admin', 92550),
('PX26', '2022-12-17 00:19:47', 'Admin', 94760),
('PX3', '2022-12-04 16:45:52', 'admin', 89350),
('PX4', '2022-12-04 16:45:59', 'admin', 58170),
('PX5', '2022-12-06 19:10:13', 'admin', 85250),
('PX6', '2022-12-06 19:19:12', 'admin', 71460),
('PX7', '2022-12-06 19:25:43', 'admin', 52370),
('PX8', '2022-12-07 18:39:54', 'admin', 39880),
('PX9', '2022-12-07 18:40:22', 'admin', 36680);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Account`
--
ALTER TABLE `Account`
  ADD PRIMARY KEY (`userName`) USING BTREE;

--
-- Indexes for table `ChiTietPhieuNhap`
--
ALTER TABLE `ChiTietPhieuNhap`
  ADD PRIMARY KEY (`maPhieu`, `maVatPham`),
  ADD KEY `FK_ChiTietPhieuNhap_VanPhongPham` (`maVatPham`);

--
-- Indexes for table `ChiTietPhieuXuat`
--
ALTER TABLE `ChiTietPhieuXuat`
  ADD PRIMARY KEY (`maPhieu`, `maVatPham`),
  ADD KEY `FK_ChiTietPhieuXuat_VanPhongPham` (`maVatPham`);

--
-- Indexes for table `VanPhongPham`
--
ALTER TABLE `VanPhongPham`
  ADD PRIMARY KEY (`maVatPham`);

--
-- Indexes for table `NhaCungCap`
--
ALTER TABLE `NhaCungCap`
  ADD PRIMARY KEY (`maNhaCungCap`);

--
-- Indexes for table `PhieuNhap`
--
ALTER TABLE `PhieuNhap`
  ADD PRIMARY KEY (`maPhieu`),
  ADD KEY `FK_PhieuNhap_NhaCungCap` (`maNhaCungCap`),
  ADD KEY `FK_PhieuNhap_Account` (`nguoiTao`);

--
-- Indexes for table `PhieuXuat`
--
ALTER TABLE `PhieuXuat`
  ADD PRIMARY KEY (`maPhieu`),
  ADD KEY `FK_PhieuXuat_Account` (`nguoiTao`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `ChiTietPhieuNhap`
--
ALTER TABLE `ChiTietPhieuNhap`
  ADD CONSTRAINT `FK_ChiTietPhieuNhap_VanPhongPham` FOREIGN KEY (`maVatPham`) REFERENCES `VanPhongPham` (`maVatPham`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_ChiTietPhieuNhap_PhieuNhap` FOREIGN KEY (`maPhieu`) REFERENCES `PhieuNhap` (`maPhieu`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `ChiTietPhieuXuat`
--
ALTER TABLE `ChiTietPhieuXuat`
  ADD CONSTRAINT `FK_ChiTietPhieuXuat_VanPhongPham` FOREIGN KEY (`maVatPham`) REFERENCES `VanPhongPham` (`maVatPham`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_ChiTietPhieuXuat_PhieuXuat` FOREIGN KEY (`maPhieu`) REFERENCES `PhieuXuat` (`maPhieu`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `PhieuNhap`
--
ALTER TABLE `PhieuNhap`
  ADD CONSTRAINT `FK_PhieuNhap_Account` FOREIGN KEY (`nguoiTao`) REFERENCES `Account` (`userName`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_PhieuNhap_NhaCungCap` FOREIGN KEY (`maNhaCungCap`) REFERENCES `NhaCungCap` (`maNhaCungCap`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `PhieuXuat`
--
ALTER TABLE `PhieuXuat`
  ADD CONSTRAINT `FK_PhieuXuat_Account` FOREIGN KEY (`nguoiTao`) REFERENCES `Account` (`userName`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
