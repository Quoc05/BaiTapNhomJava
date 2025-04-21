/*
 * @ (#) HoaDon_DAO.java    1.0   Apr 5, 2025
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

public class HoaDon_DAO {
    public HoaDon_DAO() {
    }

    public List<HoaDon> getAllHoaDon() throws Exception {
        List<HoaDon> dsHoaDon = new ArrayList<>();
        Connection conn = ConnectDB.getConnection();

        String sql = "SELECT * FROM HoaDon";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String maHD = rs.getString("maHD");
            String maNV = rs.getString("maNV");
            String maKH = rs.getString("maKH");
            double tongTien = rs.getDouble("tongTien");
            LocalDate ngayLapHD = rs.getDate("ngayLapHD").toLocalDate();

            HoaDon hd = new HoaDon(maHD, maNV, maKH, tongTien, ngayLapHD);
            dsHoaDon.add(hd);
        }
        return dsHoaDon;
    }

    public HoaDon getHoaDonByID(String maHD) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "SELECT * FROM HoaDon WHERE maHD = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, maHD);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String maNV = rs.getString("maNV");
            String maKH = rs.getString("maKH");
            double tongTien = rs.getDouble("tongTien");
            LocalDate ngayLapHD = rs.getDate("ngayLapHD").toLocalDate();

            return new HoaDon(maHD, maNV, maKH, tongTien, ngayLapHD);
        }
        return null;
    }

    public void addHoaDon(HoaDon hd) throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "INSERT INTO HoaDon (maHD, maNV, maKH, tongTien, ngayLapHD) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, hd.getMaHD());
        stmt.setString(2, hd.getMaNV());
        stmt.setString(3, hd.getMaKH());
        stmt.setDouble(4, hd.getTongTien());
        stmt.setDate(5, java.sql.Date.valueOf(hd.getNgayLapHD()));
        stmt.executeUpdate();
    }

    public int countHoaDon() throws Exception {
        Connection conn = ConnectDB.getConnection();
        String sql = "SELECT COUNT(*) FROM HoaDon";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

	public void updateTrangThaiThanhToan(String maHD, boolean b) {
		// TODO Auto-generated method stub
		
	}

	public List<HoaDon> getHoaDonChuaThanhToan() {
		// TODO Auto-generated method stub
		return null;
	}
}