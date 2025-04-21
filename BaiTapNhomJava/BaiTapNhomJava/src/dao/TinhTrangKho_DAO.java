/*
 * @ (#) TinhTrangKho_DAO.java    1.0   Apr 5, 2025
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.*;

public class TinhTrangKho_DAO {
    public TinhTrangKho_DAO() {
    }

    public List<TinhTrangKho> getAllTinhTrangKho() throws Exception {
        List<TinhTrangKho> dsTinhTrang = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();

        String sql = "SELECT * FROM TinhTrangKho";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String maSP = rs.getString("maSP");
            int soLuong = rs.getInt("soLuong");
            LocalDate ngayNhap = rs.getDate("ngayNhap").toLocalDate();

            TinhTrangKho tt = new TinhTrangKho(maSP, soLuong, ngayNhap);
            dsTinhTrang.add(tt);
        }
        return dsTinhTrang;
    }

    public TinhTrangKho getTinhTrangKhoByMaSP(String maSP) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "SELECT * FROM TinhTrangKho WHERE maSP = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maSP);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int soLuong = rs.getInt("soLuong");
            LocalDate ngayNhap = rs.getDate("ngayNhap").toLocalDate();

            return new TinhTrangKho(maSP, soLuong, ngayNhap);
        }
        return null;
    }

    public void addTinhTrangKho(TinhTrangKho tt) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "INSERT INTO TinhTrangKho (maSP, soLuong, ngayNhap) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, tt.getMaSP());
        stmt.setInt(2, tt.getSoLuong());
        stmt.setDate(3, java.sql.Date.valueOf(tt.getNgayNhap()));
        stmt.executeUpdate();
    }

    public void updateTinhTrangKho(TinhTrangKho tt) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "UPDATE TinhTrangKho SET soLuong = ?, ngayNhap = ? WHERE maSP = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, tt.getSoLuong());
        stmt.setDate(2, java.sql.Date.valueOf(tt.getNgayNhap()));
        stmt.setString(3, tt.getMaSP());
        stmt.executeUpdate();
    }

    public void deleteTinhTrangKho(String maSP) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "DELETE FROM TinhTrangKho WHERE maSP = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maSP);
        stmt.executeUpdate();
    }
}