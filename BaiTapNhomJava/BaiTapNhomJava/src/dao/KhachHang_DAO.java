/*
 * @ (#) KhachHang_DAO.java    1.0   Apr 5, 2025
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

public class KhachHang_DAO {
    public KhachHang_DAO() {
    }

    public List<KhachHang> getAllKhachHang() throws Exception {
        List<KhachHang> dsKhachHang = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();

        String sql = "SELECT * FROM KhachHang";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String maKH = rs.getString("maKH");
            String tenKH = rs.getString("tenKH");
            String SDT = rs.getString("SDT");

            KhachHang kh = new KhachHang(maKH, tenKH, SDT);
            dsKhachHang.add(kh);
        }
        return dsKhachHang;
    }

    public KhachHang getKhachHangByID(String maKH) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "SELECT * FROM KhachHang WHERE maKH = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maKH);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String tenKH = rs.getString("tenKH");
            String SDT = rs.getString("SDT");

            return new KhachHang(maKH, tenKH, SDT);
        }
        return null;
    }

    public void addKhachHang(KhachHang kh) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "INSERT INTO KhachHang (maKH, tenKH, SDT) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, kh.getMaKH());
        stmt.setString(2, kh.getTenKH());
        stmt.setString(3, kh.getSDT());
        stmt.executeUpdate();
    }

    public void updateKhachHang(KhachHang kh) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "UPDATE KhachHang SET tenKH = ?, SDT = ? WHERE maKH = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, kh.getTenKH());
        stmt.setString(2, kh.getSDT());
        stmt.setString(3, kh.getMaKH());
        stmt.executeUpdate();
    }

    public void deleteKhachHang(String maKH) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "DELETE FROM KhachHang WHERE maKH = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maKH);
        stmt.executeUpdate();
    }
}