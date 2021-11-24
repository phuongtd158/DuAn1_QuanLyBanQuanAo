﻿CREATE DATABASE DuAn1_QuanLyBanQuanAo1
GO
USE DuAn1_QuanLyBanQuanAo1
GO


CREATE TABLE NHANVIEN(
	MaNV VARCHAR(10) NOT NULL,
	TenNV NVARCHAR(30) NOT NULL,
	DiaChi NVARCHAR(50) NOT NULL,
	GioiTinh BIT DEFAULT 1 NOT NULL,
	Email VARCHAR(30) NOT NULL,
	SoDienThoai VARCHAR(12) NOT NULL,
	NgaySinh DATE NOT NULL,
	VaiTro BIT DEFAULT 1 NOT NULL,
	MatKhau VARCHAR(15) NOT NULL, 
	TrangThai BIT DEFAULT 1 NOT NULL


	PRIMARY KEY(MaNV)
)

INSERT INTO NHANVIEN 
VALUES ('admin', N'Quản lý', N'HN', DEFAULT, 'abc@gmail.com','0385606568', '2021-01-01', DEFAULT, '123', DEFAULT)

CREATE TABLE KHACHHANG(
	MaKH INT IDENTITY(1, 1),
	TenKH NVARCHAR(30) NOT NULL,
	NgaySinh DATE,
	GioiTinh BIT DEFAULT 1 NULL,
	SoDienThoai VARCHAR(12) NOT NULL,
	DiaChi NVARCHAR(50),
	TrangThai BIT DEFAULT 1 NOT NULL

	PRIMARY KEY(MaKH)
)


CREATE TABLE HINHTHUCTHANHTOAN(
	MaHTTT INT IDENTITY(1, 1)NOT NULL,
	TenHTTT NVARCHAR(30) NOT NULL,
	TrangThai BIT DEFAULT 1 NOT NULL

	PRIMARY KEY(MaHTTT)
)

INSERT INTO dbo.HINHTHUCTHANHTOAN
(
    TenHTTT,
    TrangThai
)
VALUES
(   N'Tiền mặt', -- TenHTTT - nvarchar(30)
    DEFAULT -- TrangThai - bit
    ),
	(   N'Quẹt thẻ', -- TenHTTT - nvarchar(30)
    DEFAULT -- TrangThai - bit
    ),
	(   N'Chuyển khoản', -- TenHTTT - nvarchar(30)
    DEFAULT -- TrangThai - bit
    )


CREATE TABLE SANPHAM(
	MaSP INT IDENTITY(1, 1) NOT NULL,
	TenSp nvarchar(50) NOT NULL,
	TrangThai BIT DEFAULT 1 NOT NULL

	PRIMARY KEY(MaSP)
)



CREATE TABLE LOAISP(
	MaLoai INT IDENTITY(1, 1)NOT NULL,
	TenLoai NVARCHAR(30) NOT NULL,
	TrangThai BIT DEFAULT 1 NOT NULL

	PRIMARY KEY(MaLoai)
   
)


CREATE TABLE MAUSAC(
	MaMauSac int IDENTITY(1, 1) NOT NULL,
	TenMauSac NVARCHAR(15) NOT NULL,
	TrangThai BIT DEFAULT 1 NOT NULL

	PRIMARY KEY(MaMauSac)
)

CREATE TABLE CHATLIEU(
	MaChatLieu INT IDENTITY(1, 1) NOT NULL,
	TenChatLieu NVARCHAR(15) NOT NULL,
	TrangThai BIT DEFAULT 1 NOT NULL
	
	PRIMARY KEY(MaChatLieu)
)


CREATE TABLE KICHTHUOC(
	MaKichThuoc INT IDENTITY(1, 1) NOT NULL,
	KichThuoc VARCHAR(15) NOT NULL,
	TrangThai BIT DEFAULT 1 NOT NULL

	PRIMARY KEY(MaKichThuoc)
)


CREATE TABLE CHITIETSANPHAM(
	MaCTSP INT IDENTITY(1,1),
	MaSP INT NOT NULL,
	MaLoai INT NOT NULL,
	MaMauSac int NOT NULL,
	MaKichThuoc INT NOT NULL,
	MaChatLieu INT NOT NULL,
	SoLuong INT NOT NULL,
	Gia FLOAT NOT NULL,
	GiamGia FLOAT DEFAULT 0 NULL,
	TrangThai BIT DEFAULT 1

	PRIMARY KEY(MaCTSP)
	FOREIGN KEY(MaSP) REFERENCES SanPham(MaSP),
	FOREIGN KEY(MaMauSac) REFERENCES dbo.MAUSAC(MaMauSac),
	FOREIGN KEY(MaChatLieu) REFERENCES dbo.CHATLIEU(MaChatLieu),
	FOREIGN KEY(MaKichThuoc) REFERENCES dbo.KICHTHUOC(MaKichThuoc),
	FOREIGN KEY(MaLoai) REFERENCES dbo.LOAISP(MaLoai)
)


CREATE TABLE HOADON(
	MaHD INT IDENTITY(1375328, 1) NOT NULL,
	MaKH INT NOT NULL,
	MaNV VARCHAR(10) NOT NULL,	
	MaHTTT INT NOT NULL,
	NgayKhoiTao DATE DEFAULT(GETDATE()) NOT NULL,
	TrangThai NVARCHAR(30) NOT NULL,
	GhiChu NVARCHAR(100) NULL,
	TienShip MONEY NULL

	PRIMARY KEY(MaHD)
	FOREIGN KEY(MaKH) REFERENCES dbo.KHACHHANG(MaKH),
	FOREIGN KEY(MaNV) REFERENCES dbo.NHANVIEN(MaNV),
	FOREIGN KEY(MaHTTT) REFERENCES dbo.HINHTHUCTHANHTOAN(MaHTTT)
)


CREATE TABLE HOADONCHITIET(
	MaHDCT INT IDENTITY(1, 1),
	MaHD INT NOT NULL,
	MaCTSP INT NOT NULL,
	SoLuong INT NOT NULL,
	Gia FLOAT NOT NULL,
	GiamGia FLOAT NULL,
	ThanhTien FLOAT NOT NULL,
	TrangThai BIT DEFAULT 1 NOT NULL,
	GhiChu NVARCHAR(100) NULL
	

	PRIMARY KEY(MaHDCT)
	FOREIGN KEY(MaHD) REFERENCES dbo.HOADON(MaHD),
	FOREIGN KEY(MaCTSP) REFERENCES dbo.CHITIETSANPHAM(MaCTSP)
)


<<<<<<< HEAD
SELECT * FROM dbo.HOADON WHERE TrangThai = N'Đơn hàng âm' or TrangThai =  N'Đã hủy'
=======

>>>>>>> 44d16ca4be84d144e290daae75ed25a4f4fdeccb
--SP doanh thu theo năm
CREATE PROC SP_DOANHTHU(@NAM int)
AS BEGIN
	SELECT MONTH(NgayKhoiTao) AS Thang, 
	SUM(SoLuong) AS SanPhamBan, 
	SUM(SoLuong * Gia) AS TongGiaBan,
	SUM(GiamGia) AS GiamGia, 
	SUM(SoLuong * Gia) - SUM(GiamGia) AS DoanhThu
	FROM dbo.HOADON JOIN dbo.HOADONCHITIET ON HOADONCHITIET.MaHD = HOADON.MaHD
	WHERE YEAR(NgayKhoiTao) = @NAM AND (HOADON.TrangThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán') AND HOADONCHITIET.TrangThai = 1
	GROUP BY MONTH(NgayKhoiTao) 
	ORDER BY MONTH(NgayKhoiTao) ASC
END
GO


--Thống kê sản phẩm
CREATE PROC SP_SANPHAM
AS BEGIN
	SELECT 
	ROW_NUMBER() OVER(ORDER BY (SELECT 1)) AS STT,
	CHITIETSANPHAM.MaSP AS MaSP,
	TenLoai AS TenLoai,
	TenSp AS TenSP,
	TenChatLieu AS ChatLieu,
	TenMauSac AS MauSac,
	KichThuoc AS KichThuoc,
	SoLuong AS SoLuong
	FROM dbo.CHITIETSANPHAM 
	JOIN dbo.MAUSAC ON MAUSAC.MaMauSac = CHITIETSANPHAM.MaMauSac
	JOIN dbo.CHATLIEU ON CHATLIEU.MaChatLieu = CHITIETSANPHAM.MaChatLieu
	JOIN dbo.KICHTHUOC ON KICHTHUOC.MaKichThuoc = CHITIETSANPHAM.MaKichThuoc
	JOIN dbo.LOAISP ON LOAISP.MaLoai = CHITIETSANPHAM.MaLoai
	JOIN dbo.SANPHAM ON SANPHAM.MaSP = CHITIETSANPHAM.MaSP
	WHERE CHITIETSANPHAM.TrangThai = 1 AND SoLuong > 0
	ORDER BY SoLuong 
	
END
GO

---------------------------------------------------------------------------------------------------------------

--SP tìm kiếm tổng đơn hàng
CREATE PROC SP_TONGDONHANG(@NgayBatDau date, @NgayKetThuc date)
AS BEGIN
	SELECT COUNT(HOADON.MaHD) AS TongDonHang
	FROM dbo.HOADON
	WHERE NgayKhoiTao >= @NgayBatDau AND  NgayKhoiTao <= @NgayKetThuc AND (HOADON.TrangThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán')
END
GO


CREATE PROC SP_TONGDONHANG_Huy(@NgayBatDau date, @NgayKetThuc date)
AS BEGIN
	SELECT COUNT(HOADON.MaHD) AS TongDonHang
	FROM dbo.HOADON
	WHERE NgayKhoiTao >= @NgayBatDau AND  NgayKhoiTao <= @NgayKetThuc AND (HOADON.TrangThai = N'Đã hủy' OR dbo.HOADON.TrangThai = N'Đơn hàng âm')
END
GO

---------------------------------------------------------------------------------------------------------------

--Sp tổng đơn hàng thành công  ngày hiện tại 
CREATE PROC SP_TONGDONHANG_Ngay(@NgayBatDau date)
AS BEGIN
	SELECT COUNT(HOADON.MaHD) AS TongDonHang
	FROM dbo.HOADON 
	WHERE  (HOADON.TrangThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán') AND NgayKhoiTao = @NgayBatDau
END
GO


--SP tổng đơn hàng bị hủy theo ngày
CREATE PROC SP_TONGDONHANG_BiHuy_Ngay(@NgayBatDau date)
AS BEGIN
	SELECT COUNT(HOADON.MaHD) AS TongDonHang
	FROM dbo.HOADON 
	WHERE  (HOADON.TrangThai = N'Đã hủy' OR dbo.HOADON.TrangThai = N'Đơn hàng âm') AND NgayKhoiTao = @NgayBatDau
END
GO

--SP tổng đơn hàng thành công theo tháng
CREATE PROC SP_TONGDONHANG_Thang(@Thang int)
AS BEGIN
	SELECT COUNT(HOADON.MaHD) AS TongDonHangHuy
	FROM dbo.HOADON 
	WHERE  (HOADON.TrangThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán') AND MONTH(NgayKhoiTao) = @Thang
END
GO


--SP tổng đơn hàng bị hủy theo tháng
CREATE PROC SP_TONGDONHANG_BiHuy_Thang(@Thang int)
AS BEGIN
	SELECT COUNT(HOADON.MaHD) AS TongDonHangHuy
	FROM dbo.HOADON 
	WHERE  (HOADON.TrangThai = N'Đã hủy' OR dbo.HOADON.TrangThai = N'Đơn hàng âm') AND MONTH(NgayKhoiTao) = @Thang
END
GO




---------------------------------------------------------------------------------------------------------------

--SP tìm kiếm tổng doanh thu theo ngày
CREATE PROC SP_TONGDOANHTHU(@NgayBatDau date, @NgayKetThuc date)
AS BEGIN
	SELECT SUM(SoLuong * Gia) - SUM(GiamGia) AS TongDoanhThu
	FROM dbo.HOADON JOIN dbo.HOADONCHITIET ON HOADONCHITIET.MaHD = HOADON.MaHD
	WHERE NgayKhoiTao >= @NgayBatDau AND  NgayKhoiTao <= @NgayKetThuc AND (HOADON.TrangThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán')
END
GO

--SP doanh thu ngày hiện tại
CREATE PROC SP_TONGDOANHTHU_Ngay(@NgayBatDau date)
AS BEGIN
	SELECT SUM(SoLuong * Gia) -   SUM(SoLuong * Gia *(GiamGia/100)) AS TongDoanhThu
	FROM dbo.HOADON JOIN dbo.HOADONCHITIET ON HOADONCHITIET.MaHD = HOADON.MaHD
	WHERE NgayKhoiTao =  @NgayBatDau AND (HOADON.TrangThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán')
END
GO

--Sp tổng đơn hàng ngày hiện tại 
CREATE PROC SP_TONGDONHANG_Thang(@NgayBatDau date)
AS BEGIN
	SELECT COUNT(HOADON.MaHD) AS TongDonHang
	FROM dbo.HOADON 
	WHERE  HOADON.TrangThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán' AND NgayKhoiTao = @NgayBatDau
END
GO


--SP tổng đơn hàng bị hủy
CREATE PROC SP_TONGDONHANG_BiHuy_Thang(@NgayBatDau date)
AS BEGIN
	SELECT COUNT(HOADON.MaHD) AS TongDonHang
	FROM dbo.HOADON 
	WHERE  HOADON.TrangThai = N'Đã hủy' OR dbo.HOADON.TrangThai = N'Đơn hàng âm' AND NgayKhoiTao = @NgayBatDau
END
GO
---------------------------------------------------------------------------------------------------------------


--SP tổng doanh thu theo tháng
CREATE PROC SP_DOANHTHUTHANG(@Thang int)
AS BEGIN
	SELECT 
	SUM(SoLuong * Gia) -   SUM(SoLuong * Gia *(GiamGia/100)) AS DoanhThuThang 
	FROM dbo.HOADONCHITIET JOIN dbo.HOADON ON HOADON.MaHD = HOADONCHITIET.MaHD
	WHERE MONTH(NgayKhoiTao) = @Thang AND (HOADON.TrangThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán') AND HOADONCHITIET.TrangThai = 1
END
GO
--Doanh thu trung bình tháng
CREATE PROC SP_DOANHTHUTHANG_TrungBinh(@Thang int)
AS BEGIN
	SELECT 
	AVG(SUM(SoLuong * Gia) -   SUM(SoLuong * Gia *(GiamGia/100))) AS DoanhThuThang 
	FROM dbo.HOADONCHITIET JOIN dbo.HOADON ON HOADON.MaHD = HOADONCHITIET.MaHD
	WHERE MONTH(NgayKhoiTao) = 11 AND (HOADON.TrangThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán')
	AND HOADONCHITIET.TrangThai = 1
END
GO

---------------------------------------------------------------------------------------------------------------

--SP tổng doanh thu theo năm
CREATE PROC SP_DOANHTHUNAM(@Nam int)
AS BEGIN
	SELECT 
	SUM(SoLuong * Gia) -   SUM(SoLuong * Gia *(GiamGia/100)) AS DoanhThuNam
	FROM dbo.HOADONCHITIET JOIN dbo.HOADON ON HOADON.MaHD = HOADONCHITIET.MaHD
	WHERE YEAR(NgayKhoiTao) = @Nam AND (HOADON.TrangThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán') AND HOADONCHITIET.TrangThai = 1
END
GO

DROP PROC dbo.SP_DOANHTHUTHANG

---------------------------------------------------------------------------------------------------------------