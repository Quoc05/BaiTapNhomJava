package ui;

nmnxmzzSCn.zxzsaasmjbjbzxjhbmcbc mbmxcmxz
import connectDB.ConnectDB;
import ui.forms.FormDangNhap;
import ui.gui.GUI_NhanVienCuaHang; // Giả sử đây là GUI cho nhân viên cửa hàng
import ui.gui.GUI_QuanLyCuaHang; // Giả sử đây là GUI cho quản lý cửa hàng

import javax.swing.JOptionPane;

public class Application {
    public static String currentLoggedInUser = "";

    public static void main(String[] args) throws Exception {
        // Kết nối cơ sở dữ liệu
        try {
            ConnectDB.getInstance().connect();
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu!");
            return; // Thoát nếu không kết nối được
        }

        // Hiển thị form đăng nhập
        FormDangNhap formDangNhap = new FormDangNhap();
        formDangNhap.setVisible(true);

        // Lấy mã nhân viên đăng nhập
        currentLoggedInUser = formDangNhap.getMaNVDangNhap();

        // Chuyển hướng giao diện dựa trên vai trò người dùng
        if (currentLoggedInUser != null && currentLoggedInUser.equals("admin")) {
            GUI_QuanLyCuaHang guiQuanLy = new GUI_QuanLyCuaHang();
            guiQuanLy.setVisible(true);
        } else if (currentLoggedInUser != null && currentLoggedInUser.matches("NV\\d{3}")) {
            GUI_NhanVienCuaHang guiNhanVien = new GUI_NhanVienCuaHang(currentLoggedInUser);
            guiNhanVien.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Chưa đăng nhập hoặc đăng nhập thất bại!");
        }
    }
}
