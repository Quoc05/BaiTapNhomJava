/*
 * @ (#) SanPham_DAO.java    1.0   Apr 5, 2025
 *
 * Copyright (c) 2025 IUH. All rights reserved.  
 */
package dao;
/*
* @description:
* @author: Quoc Nguyen
* @date:  Apr 5, 2025
* @version:   1.0
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.*;

public class SanPham_DAO {
    public SanPham_DAO() {
    }

    public List<SanPham> getAllSanPham() throws Exception {
        List<SanPham> dsSanPham = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();

        String sql = "SELECT * FROM SanPham";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String maSP = rs.getString("maSP");
            String tenSP = rs.getString("tenSP");
            double giaBan = rs.getDouble("giaBan");
            String loaiSP = rs.getString("loaiSP");

            SanPham sp = new SanPham(maSP, tenSP, giaBan, loaiSP);
            dsSanPham.add(sp);
        }
        return dsSanPham;
    }

    public SanPham getSanPhamByID(String maSP) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "SELECT * FROM SanPham WHERE maSP = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maSP);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String tenSP = rs.getString("tenSP");
            double giaBan = rs.getDouble("giaBan");
            String loaiSP = rs.getString("loaiSP");

            return new SanPham(maSP, tenSP, giaBan, loaiSP);
        }
        return null;
    }

    public void addSanPham(SanPham sp) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "INSERT INTO SanPham (maSP, tenSP, giaBan, loaiSP) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, sp.getMaSP());
        stmt.setString(2, sp.getTenSP());
        stmt.setDouble(3, sp.getGiaBan());
        stmt.setString(4, sp.getLoaiSP());
        stmt.executeUpdate();
    }

    public void updateSanPham(SanPham sp) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "UPDATE SanPham SET tenSP = ?, giaBan = ?, loaiSP = ? WHERE maSP = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, sp.getTenSP());
        stmt.setDouble(2, sp.getGiaBan());
        stmt.setString(3, sp.getLoaiSP());
        stmt.setString(4, sp.getMaSP());
        stmt.executeUpdate();
    }

    public void deleteSanPham(String maSP) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "DELETE FROM SanPham WHERE maSP = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maSP);
        stmt.executeUpdate();
    }

	public void editSanPhamByID(String string, SanPham sp) {
		// TODO Auto-generated method stub
		
	}

	public void deleteSanPhamByID(String string) {
		// TODO Auto-generated method stub
		
	}

}