/*
 * @ (#) HoaDonDialog.java    1.0   Apr 6, 2025
 *
 * Copyright (c) 2025 IUH. All rights reserved.  
 */
package ui.forms;
/*
* @description:
* @author: Quoc Nguyen
* @date:  Apr 6, 2025
* @version:   1.0
*/

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import dao.KhachHang_DAO;
import entity.*;

public class HoaDonDialog extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HoaDon hd;
    private String maKH;
    private String hoTen;
    private String sdt;
    private String tongTien;
    private String ngayLap;

    public HoaDonDialog(HoaDon hd) {
        setTitle("Thông tin hóa đơn " + hd.getMaHD());
        setModal(true);
        setSize(600, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        this.hd = hd;
        getData();

        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 600, 315);
        getContentPane().add(mainPanel);
        mainPanel.setLayout(null);

        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(0, 0, 600, 40);
        mainPanel.add(panelTitle);
        panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JLabel lblThongTinHD = new JLabel("Thông tin hóa đơn " + hd.getMaHD());
        lblThongTinHD.setForeground(Color.BLUE);
        panelTitle.add(lblThongTinHD);
        lblThongTinHD.setFont(new Font("Dialog", Font.BOLD, 25));

        JPanel panelKhachHang = new JPanel();
        panelKhachHang.setBounds(0, 50, 600, 265);
        mainPanel.add(panelKhachHang);
        panelKhachHang.setLayout(new BoxLayout(panelKhachHang, BoxLayout.Y_AXIS));

        Box bTop = Box.createVerticalBox();
        bTop.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Thông tin khách hàng",
                TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
        panelKhachHang.add(bTop);

        Box b1Top = Box.createHorizontalBox();
        b1Top.setAlignmentX(LEFT_ALIGNMENT);
        bTop.add(b1Top);

        JLabel lblMaKH = new JLabel("Mã khách hàng: " + maKH);
        b1Top.add(lblMaKH);
        lblMaKH.setFont(new Font("Dialog", Font.BOLD, 20));

        Component horizontalGlue = Box.createHorizontalGlue();
        b1Top.add(horizontalGlue);

        Box b2Top = Box.createHorizontalBox();
        b2Top.setAlignmentX(0.0f);
        bTop.add(b2Top);

        JLabel lblHoTen = new JLabel("Họ tên khách hàng: " + hoTen);
        lblHoTen.setFont(new Font("Dialog", Font.BOLD, 20));
        b2Top.add(lblHoTen);

        Box b3Top = Box.createHorizontalBox();
        b3Top.setAlignmentX(0.0f);
        bTop.add(b3Top);

        JLabel lblSDT = new JLabel("Số điện thoại: " + sdt);
        lblSDT.setFont(new Font("Dialog", Font.BOLD, 20));
        b3Top.add(lblSDT);

        Component verticalStrut = Box.createVerticalStrut(20);
        panelKhachHang.add(verticalStrut);

        Box bBottom = Box.createVerticalBox();
        bBottom.setBorder(new TitledBorder(null, "Thông tin hóa đơn", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        panelKhachHang.add(bBottom);

        Box b1Bottom = Box.createHorizontalBox();
        b1Bottom.setAlignmentX(0.0f);
        bBottom.add(b1Bottom);

        JLabel lblTongTien = new JLabel("Tổng tiền: " + tongTien);
        lblTongTien.setFont(new Font("Dialog", Font.BOLD, 20));
        b1Bottom.add(lblTongTien);

        Box b2Bottom = Box.createHorizontalBox();
        b2Bottom.setAlignmentX(0.0f);
        bBottom.add(b2Bottom);

        JLabel lblNgayLap = new JLabel("Ngày lập hóa đơn: " + ngayLap);
        lblNgayLap.setFont(new Font("Dialog", Font.BOLD, 20));
        b2Bottom.add(lblNgayLap);
    }

    private void getData() {
        KhachHang_DAO khDAO = new KhachHang_DAO();
        try {
            KhachHang kh = khDAO.getKhachHangByID(hd.getMaKH());
            @SuppressWarnings("deprecation")
			NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

            this.maKH = hd.getMaKH();
            this.hoTen = kh.getTenKH();
            this.sdt = kh.getSDT();
            this.tongTien = nf.format(hd.getTongTien());
            this.ngayLap = hd.getNgayLapHD().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}