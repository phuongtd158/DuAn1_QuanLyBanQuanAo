CREATE DATABASE DuAn1_QuanLyBanQuanAo1
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
	GhiChu NVARCHAR(100) NULL

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
	TrangThai BIT DEFAULT 1 NOT NULL
	

	PRIMARY KEY(MaHDCT)
	FOREIGN KEY(MaHD) REFERENCES dbo.HOADON(MaHD),
	FOREIGN KEY(MaCTSP) REFERENCES dbo.CHITIETSANPHAM(MaCTSP)
)


ALTER TABLE dbo.HOADONCHITIET
ADD GhiChu NVARCHAR(100) NULL

ALTER TABLE HOADON
ADD TienShip money NULL

select * from HOADON
select * from HOADONCHITIET

INSERT INTO dbo.HINHTHUCTHANHTOAN
(
    TenHTTT,
    TrangThai
)
VALUES
(   N'Tiền mặt', -- TenHTTT - nvarchar(30)
    DEFAULT -- TrangThai - bit
    )
	INSERT INTO dbo.HINHTHUCTHANHTOAN
(
    TenHTTT,
    TrangThai
)
VALUES
(   N'Quẹt thẻ', -- TenHTTT - nvarchar(30)
    DEFAULT -- TrangThai - bit
    )
	INSERT INTO dbo.HINHTHUCTHANHTOAN
(
    TenHTTT,
    TrangThai
)
VALUES
(   N'Chuyển khoản', -- TenHTTT - nvarchar(30)
    DEFAULT -- TrangThai - bit
    )
select * from MAUSAC
ALTER TABLE dbo.CHITIETSANPHAM
ALTER COLUMN GiamGia FLOAT NULL

select * from SANPHAM where TenSp = N'hahah'
delete from SANPHAM where MaSP != 1 and MaSP != 2 and MaSP != 5 
select * from CHITIETSANPHAM

delete from CHITIETSANPHAM where MaCTSP between 32 and 42 
update CHITIETSANPHAM set SoLuong = 50 , Gia = 200000 where MaCTSP = 7
INSERT INTO dbo.CHITIETSANPHAM
(
    MaSP,
    MaLoai,
    MaMauSac,
    MaKichThuoc,
    MaChatLieu,
    SoLuong,
    Gia,
    GiamGia,
    TrangThai
)
VALUES
(   0,   -- MaSP - int
    0,   -- MaLoai - int
    0,   -- MaMauSac - int
    0,   -- MaKichThuoc - int
    0,   -- MaChatLieu - int
    0,   -- SoLuong - int
    0.0, -- Gia - float
    0.0, -- GiamGia - float
    NULL -- TrangThai - bit
    )
INSERT INTO dbo.HOADON
(
    MaKH,
    MaNV,
    MaHTTT,
    NgayKhoiTao,
    TrangThai
)
VALUES
(   40,         -- MaKH - int
    'admin',        -- MaNV - varchar(10)
    2,         -- MaHTTT - int
    '2021-10-01', -- NgayKhoiTao - date
    DEFAULT       -- TrangThai - bit
    )
INSERT INTO dbo.HOADONCHITIET
(
    MaHD,
    MaCTSP,
    SoLuong,
    Gia,
    GiamGia,
    ThanhTien,
    TrangThai
)
VALUES
(   1375352,   -- MaHD - int
    4,   -- MaCTSP - int
    10,   -- SoLuong - int
    10, -- Gia - float
    0.0, -- GiamGia - float
    100.0, -- ThanhTien - float
    DEFAULT -- TrangThai - bit
    )
SELECT * FROM dbo.HOADON
INSERT INTO dbo.HOADONCHITIET
(
    MaHD,
    MaCTSP,
    SoLuong,
    Gia,
    GiamGia,
    ThanhTien,
    TrangThai
)
VALUES
(   1375338,   -- MaHD - int
    1,   -- MaCTSP - int
    9,   -- SoLuong - int
    1.0, -- Gia - float
    5, -- GiamGia - float
    9.0, -- ThanhTien - float
    DEFAULT -- TrangThai - bit
    )
SELECT * FROM dbo.CHITIETSANPHAM
SELECT * FROM dbo.HOADON
SELECT * FROM dbo.HOADONCHITIET


--SP doanh thu theo năm
CREATE PROC SP_DOANHTHU(@NAM int)
AS BEGIN
	SELECT MONTH(NgayKhoiTao) AS Thang, 
	SUM(SoLuong) AS SanPhamBan, 
	SUM(SoLuong * Gia) AS TongGiaBan,
	SUM(GiamGia) AS GiamGia, 
	SUM(SoLuong * Gia) - SUM(GiamGia) AS DoanhThu
	FROM dbo.HOADON JOIN dbo.HOADONCHITIET ON HOADONCHITIET.MaHD = HOADON.MaHD
	WHERE YEAR(NgayKhoiTao) = @NAM AND HOADON.TRAN	gThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán'
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

--Sp tổng đơn hàng ngày hiện tại 
CREATE PROC SP_TONGDONHANG_Ngay(@NgayBatDau date)
AS BEGIN
	SELECT COUNT(HOADON.MaHD) AS TongDonHang
	FROM dbo.HOADON 
	WHERE  (HOADON.TrangThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán') AND NgayKhoiTao = @NgayBatDau
END
GO


--SP tổng đơn hàng bị hủy
CREATE PROC SP_TONGDONHANG_BiHuy_Ngay(@NgayBatDau date)
AS BEGIN
	SELECT COUNT(HOADON.MaHD) AS TongDonHang
	FROM dbo.HOADON 
	WHERE  (HOADON.TrangThai = N'Đã hủy' OR dbo.HOADON.TrangThai = N'Đơn hàng âm') AND NgayKhoiTao = @NgayBatDau
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
	WHERE MONTH(NgayKhoiTao) = @Thang AND (HOADON.TrangThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán')
END
GO
---------------------------------------------------------------------------------------------------------------

--SP tổng doanh thu theo năm
CREATE PROC SP_DOANHTHUNAM(@Nam int)
AS BEGIN
	SELECT 
	SUM(SoLuong * Gia) -   SUM(SoLuong * Gia *(GiamGia/100)) AS DoanhThuNam
	FROM dbo.HOADONCHITIET JOIN dbo.HOADON ON HOADON.MaHD = HOADONCHITIET.MaHD
	WHERE YEAR(NgayKhoiTao) = @Nam AND (HOADON.TrangThai = N'Đã giao hàng' OR dbo.HOADON.TrangThai = N'Đã thanh toán')
END
GO


---------------------------------------------------------------------------------------------------------------