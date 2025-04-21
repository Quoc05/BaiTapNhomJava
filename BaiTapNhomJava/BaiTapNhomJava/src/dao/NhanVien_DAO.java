package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.*;

public class NhanVien_DAO {
    public NhanVien_DAO() {
    }

    public boolean checkLogin(String maNV, String matKhau) {
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM NhanVien WHERE maNV = ? AND matKhau = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maNV);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<NhanVien> getAllNhanVien() throws Exception {
        List<NhanVien> dsNhanVien = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();

        String sql = "SELECT * FROM NhanVien";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String maNV = rs.getString(1);
            String matKhau = rs.getString(2);
            String tenNV = rs.getString(3);
            LocalDate ngaySinh = rs.getDate(4).toLocalDate();
            String SDT = rs.getString(5);
            String cccd = rs.getString(6);
			NhanVien nv = new NhanVien(maNV, matKhau, tenNV, ngaySinh, SDT, cccd);
            dsNhanVien.add(nv);
        }
        return dsNhanVien;
    }

    public NhanVien getNhanVienByID(String maNV) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "SELECT * FROM NhanVien WHERE maNV = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maNV);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String matKhau = rs.getString("matKhau");
            String tenNV = rs.getString("tenNV");
            LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
            String SDT = rs.getString("SDT");

            String cccd = null;
			return new NhanVien(maNV, matKhau, tenNV, ngaySinh, SDT, cccd);
        }
        return null;
    }

    public void addNhanVien(NhanVien nv) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "INSERT INTO NhanVien (maNV, matKhau, tenNV, ngaySinh, SDT, CCCD) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nv.getMaNV());
        stmt.setString(2, nv.getMatKhau());
        stmt.setString(3, nv.getTenNV());
        stmt.setDate(4, java.sql.Date.valueOf(nv.getNgaySinh()));
        stmt.setString(5, nv.getSDT());
        stmt.setString(6, nv.getCCCD());
        stmt.executeUpdate();
    }

    public void updateNhanVien(NhanVien nv) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "UPDATE NhanVien SET matKhau = ?, tenNV = ?, ngaySinh = ?, SDT = ? WHERE maNV = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nv.getMatKhau());
        stmt.setString(2, nv.getTenNV());
        stmt.setDate(3, java.sql.Date.valueOf(nv.getNgaySinh()));
        stmt.setString(4, nv.getSDT());
        stmt.setString(5, nv.getMaNV());
        stmt.executeUpdate();
    }

    public void deleteNhanVien(String maNV) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "DELETE FROM NhanVien WHERE maNV = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maNV);
        stmt.executeUpdate();
    }

	public void editNhanVienByID(String currentMaNV, NhanVien nv) {
		// TODO Auto-generated method stub
		
	}

	public void deleteNhanVienByID(String string) {
		// TODO Auto-generated method stub
		
	}
}