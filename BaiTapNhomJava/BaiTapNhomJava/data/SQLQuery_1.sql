create database QuanLyCuaHangTienLoi
go
use QuanLyCuaHangTienLoi
go
CREATE TABLE NhanVien (
    maNV VARCHAR(10) PRIMARY KEY,
    matKhau VARCHAR(255) NOT NULL,
    tenNV VARCHAR(100) NOT NULL,
    ngaySinh DATE NOT NULL,
    SDT VARCHAR(10) NOT NULL,
    cccd VARCHAR(12)
);

INSERT INTO NhanVien (maNV, matKhau, tenNV, ngaySinh, SDT, cccd)
VALUES
('NV001', 'passwordnv1', 'Nguyen Thi Lan', '1990-05-15', '0123456789', '060205001234'),
('NV002', 'passwordnv2', 'Tran Minh Hoang', '1985-08-20', '0123456790', '060205001235'),
('NV003', 'passwordnv3', 'Le Quang Hieu', '1992-03-10', '0123456791', '060205001236'),
('NV004', 'passwordnv4', 'Pham Thu Trang', '1988-12-05', '0123456792', '060205001237'),
('NV005', 'passwordnv5', 'Vu Thanh Mai', '1995-07-25', '0123456793', '060205001238');
go

CREATE TABLE SanPham (
    maSP VARCHAR(10) PRIMARY KEY,
    tenSP NVARCHAR(100) NOT NULL,
    giaBan FLOAT NOT NULL CHECK (giaBan >= 0),
    loaiSP NVARCHAR(50) NOT NULL CHECK (loaiSP IN (N'Thực phẩm', N'Đồ uống', N'Hàng gia dụng'))
);

INSERT INTO SanPham (maSP, tenSP, giaBan, loaiSP)
VALUES
('SP001', N'Mì tôm Hảo Hảo', 3500, N'Thực phẩm'),
('SP002', N'Coca Cola lon 330ml', 9000, N'Đồ uống'),
('SP003', N'Bánh Oreo', 12000, N'Thực phẩm'),
('SP004', N'Nước suối Aquafina 500ml', 6000, N'Đồ uống'),
('SP005', N'Bột giặt Omo 400g', 18000, N'Hàng gia dụng'),
('SP006', N'Kem đánh răng P/S 100g', 15000, N'Hàng gia dụng'),
('SP007', N'Cháo ăn liền Vifon', 8000, N'Thực phẩm'),
('SP008', N'Trà xanh Không độ 455ml', 10000, N'Đồ uống'),
('SP009', N'Nước rửa chén Sunlight 250ml', 14000, N'Hàng gia dụng'),
('SP010', N'Sữa tươi TH True Milk 1L', 28000, N'Đồ uống');
go

CREATE TABLE KhachHang (
    maKH VARCHAR(10) PRIMARY KEY,
    tenKH NVARCHAR(100) NOT NULL,
    SDT VARCHAR(10) NOT NULL,
    diemTichLuy INT DEFAULT 0 CHECK (diemTichLuy >= 0)
);

INSERT INTO KhachHang (maKH, tenKH, SDT, diemTichLuy)
VALUES
('KH001', N'Nguyen Van A', '0912345678', 120),
('KH002', N'Tran Thi B', '0934567890', 80),
('KH003', N'Le Van C', '0976543210', 200),
('KH004', N'Pham Thi D', '0901234567', 50),
('KH005', N'Hoang Van E', '0987654321', 30),
('KH006', N'Nguyen Thi F', '0961122334', 95),
('KH007', N'Vu Van G', '0922233445', 150),
('KH008', N'Do Thi H', '0933444555', 0),
('KH009', N'Tran Van I', '0945566778', 175),
('KH010', N'Pham Thi K', '0911002200', 220);
go