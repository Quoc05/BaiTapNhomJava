/*
 * @ (#) PanelThanhToan.java    1.0   Apr 6, 2025
 *
 * Copyright (c) 2025 IUH. All rights reserved.  
 */
package ui.panels;
/*
* @description:
* @author: Quoc Nguyen
* @date:  Apr 6, 2025
* @version:   1.0
*/

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import entity.*;

public class PanelThanhToan extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tbl;
    private DefaultTableModel model;
    private JButton btnThanhToan = new JButton("Thanh toán");
    private String currentMaNV = "";

    public PanelThanhToan(String currentMaNV) {
        this.currentMaNV = currentMaNV;
        setBounds(0, 0, 725, 669);
        setVisible(false);
        setLayout(null);

        JLabel lblThanhToan = new JLabel("Thanh toán");
        lblThanhToan.setBounds(305, 5, 100, 28);
        lblThanhToan.setFont(new Font("Dialog", Font.BOLD, 20));
        add(lblThanhToan);

        btnThanhToan.setFont(new Font("Dialog", Font.BOLD, 20));
        btnThanhToan.setBounds(300, 600, 120, 40);
        add(btnThanhToan);

        createTable();

        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selection = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thanh toán hóa đơn này?",
                        "Thanh toán", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (selection == JOptionPane.YES_OPTION) {
                    handleHoaDon(model.getValueAt(tbl.getSelectedRow(), 0).toString());
                    JOptionPane.showMessageDialog(null, "Thanh toán thành công!", "Thanh toán",
                            JOptionPane.INFORMATION_MESSAGE);
                    refreshTable();
                }
            }
        });

        btnThanhToan.addActionListener(e -> {
            if (tbl.getSelectedRow() != -1) {
                handleHoaDon(model.getValueAt(tbl.getSelectedRow(), 0).toString());
                JOptionPane.showMessageDialog(null, "Thanh toán thành công!", "Thanh toán",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để thanh toán!");
            }
        });
    }

    public void handleHoaDon(String maHD) {
        try {
            HoaDon_DAO hoaDonDAO = new HoaDon_DAO();
            KhachHang_DAO khachHangDAO = new KhachHang_DAO();
            HoaDon hd = hoaDonDAO.getHoaDonByID(maHD);
            KhachHang kh = khachHangDAO.getKhachHangByID(hd.getMaKH());

            File file = new File("./src/txt/HoaDon" + maHD + ".txt");
            FileWriter fw = new FileWriter(file);

            if (!file.exists()) {
                file.createNewFile();
            }

            fw.write("Hóa đơn mua hàng:\n");
            fw.write("Mã hóa đơn: " + hd.getMaHD() + "\n");
            fw.write("----------------------------------------\n");
            fw.write("Mã khách hàng: " + hd.getMaKH() + "\n");
            fw.write("Họ tên khách hàng: " + kh.getTenKH() + "\n");
            fw.write("Ngày sinh: " + kh.getNgaySinh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
            fw.write("Số điện thoại: " + kh.getSDT() + "\n");
            fw.write("Số CCCD: " + kh.getCCCD() + "\n");
            fw.write("----------------------------------------\n");

            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            fw.write("Tổng tiền: " + nf.format(hd.getTongTien()) + "\n");
            fw.write("Ngày tạo: " + hd.getNgayLapHD().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");

            fw.close();

            hoaDonDAO.updateTrangThaiThanhToan(maHD, true); // Giả định có phương thức cập nhật trạng thái
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
    }

    private void createTable() {
        String[] tblCols = {"Mã hóa đơn", "Mã khách hàng", "Tổng tiền", "Ngày tạo", "Trạng thái"};
        model = new DefaultTableModel(tblCols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tbl = new JTable(model);
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tbl.getColumnModel().getColumn(0).setPreferredWidth(70);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(80);
        tbl.setRowHeight(30);
        tbl.setFont(new Font("Arial", Font.PLAIN, 20));

        JScrollPane tblPane = new JScrollPane(tbl);
        tblPane.setBounds(0, 36, 725, 553);
        tblPane.setBorder(BorderFactory.createTitledBorder("Danh sách hóa đơn chưa thanh toán"));
        add(tblPane);

        refreshTable();
    }

    public void refreshTable() {
        model.setRowCount(0);
        HoaDon_DAO hoaDonDAO = new HoaDon_DAO();
        List<HoaDon> list = hoaDonDAO.getHoaDonChuaThanhToan(); // Giả định có phương thức này
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        for (HoaDon hd : list) {
            model.addRow(new Object[]{
                hd.getMaHD(), 
                hd.getMaKH(), 
                nf.format(hd.getTongTien()), 
                dtf.format((TemporalAccessor) hd.getNgayLapHD()), 
                "Chưa thanh toán"
            });
        }
    }
}