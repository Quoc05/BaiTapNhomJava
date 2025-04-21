/*
 * @ (#) GUI_QuanLyCuaHang.java    1.0   Apr 6, 2025
 *
 * Copyright (c) 2025 IUH. All rights reserved.  
 */
package ui.gui;
/*
* @description:
* @author: Quoc Nguyen
* @date:  Apr 6, 2025
* @version:   1.0
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.NhanVien_DAO;
import entity.*;
import ui.forms.TopKhachHangDialog;
import ui.forms.TopSanPhamDialog;

public class GUI_QuanLyCuaHang extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel panelNhanVien = new JPanel();
    private final JTextField txtMaNV = new JTextField();
    private final JTextField txtHoTen = new JTextField();
    private final JTextField txtNgaySinh = new JTextField();
    private final JTextField txtMatKhau = new JTextField();
    private final JTextField txtSDT = new JTextField();
    private final JTextField txtCCCD = new JTextField();
    private final JLabel lblMaNV = new JLabel("Mã nhân viên: ");
    private final JLabel lblMatKhau = new JLabel("Mật khẩu: ");
    private final JLabel lblHoTen = new JLabel("Họ tên: ");
    private final JLabel lblNgaySinh = new JLabel("Ngày sinh: ");
    private final JLabel lblSDT = new JLabel("SĐT: ");
    private final JLabel lblCCCD = new JLabel("CCCD: ");
    private final JButton btnThem = new JButton("Thêm");
    private final JButton btnXoa = new JButton("Xóa");
    private final JButton btnLuu = new JButton("Lưu");
    private final JButton btnHuy = new JButton("Xóa trắng");
    private final JButton btnTimKiem = new JButton("Tìm kiếm");
    private JTable tbl;
    private DefaultTableModel model;

    public GUI_QuanLyCuaHang() throws Exception {
        setTitle("Phần mềm quản lý cửa hàng tiện lợi - Quản lý");
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
//        setIconImage(new ImageIcon(GUI_QuanLyCuaHang.class.getResource("/images/store-icon.png")).getImage());
        getContentPane().setLayout(null);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        leftPanel.setBackground(new Color(204, 204, 255));
        leftPanel.setLocation(0, 0);
        leftPanel.setSize(271, 569);
        getContentPane().add(leftPanel, BorderLayout.WEST);
        leftPanel.setLayout(null);

        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(0, 153, 255));
        logoPanel.setBounds(0, 0, 271, 92);
        leftPanel.add(logoPanel);
        logoPanel.setLayout(null);

        JLabel logoLabel = new JLabel("Quản lý cửa hàng");
        logoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        logoLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setBounds(5, 5, 266, 87);
//        logoLabel.setIcon(new ImageIcon(GUI_QuanLyCuaHang.class.getResource("/images/store-icon.png")));
        logoPanel.add(logoLabel);

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(0, 0, 0, 0));
        menuPanel.setBounds(0, 90, 271, 479);
        leftPanel.add(menuPanel);
        menuPanel.setLayout(null);

        JPanel menuPanelInfo = new JPanel();
        menuPanelInfo.setBackground(new Color(36, 31, 49));
        menuPanelInfo.setBounds(0, 34, 271, 80);
        menuPanel.add(menuPanelInfo);
        menuPanelInfo.setLayout(new BorderLayout(0, 0));

        JLabel lblThongTinPhanMem = new JLabel("Thông tin phần mềm");
//        lblThongTinPhanMem.setIcon(new ImageIcon(GUI_QuanLyCuaHang.class.getResource("/images/info-icon.png")));
        lblThongTinPhanMem.setForeground(Color.WHITE);
        lblThongTinPhanMem.setHorizontalAlignment(SwingConstants.CENTER);
        lblThongTinPhanMem.setFont(new Font("Dialog", Font.BOLD, 20));
        menuPanelInfo.add(lblThongTinPhanMem, BorderLayout.CENTER);

        JPanel menuPanelNhanVien = new JPanel();
        menuPanelNhanVien.setBackground(new Color(36, 31, 49));
        menuPanelNhanVien.setBounds(0, 144, 271, 80);
        menuPanel.add(menuPanelNhanVien);
        menuPanelNhanVien.setLayout(new BorderLayout(0, 0));

        JLabel lblQuanLyNhanVien = new JLabel("Quản lý nhân viên");
//        lblQuanLyNhanVien.setIcon(new ImageIcon(GUI_QuanLyCuaHang.class.getResource("/images/magnify-icon.png")));
        lblQuanLyNhanVien.setForeground(Color.WHITE);
        lblQuanLyNhanVien.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuanLyNhanVien.setFont(new Font("Dialog", Font.BOLD, 20));
        menuPanelNhanVien.add(lblQuanLyNhanVien);

        JPanel menuPanelThongKe = new JPanel();
        menuPanelThongKe.setBackground(new Color(36, 31, 49));
        menuPanelThongKe.setBounds(0, 254, 271, 80);
        menuPanel.add(menuPanelThongKe);
        menuPanelThongKe.setLayout(new BorderLayout(0, 0));

        JLabel lblThongKe = new JLabel("Thống kê doanh thu");
//        lblThongKe.setIcon(new ImageIcon(GUI_QuanLyCuaHang.class.getResource("/images/chart-icon.png")));
        lblThongKe.setForeground(Color.WHITE);
        lblThongKe.setHorizontalAlignment(SwingConstants.CENTER);
        lblThongKe.setFont(new Font("Dialog", Font.BOLD, 20));
        menuPanelThongKe.add(lblThongKe);

        JLabel lblWelcome = new JLabel("Welcome, Admin");
        lblWelcome.setFont(new Font("Dialog", Font.BOLD, 20));
        lblWelcome.setBounds(12, 439, 259, 28);
        menuPanel.add(lblWelcome);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(271, 0, 925, 569);
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);

        // Create table
        createTable();

        // Panel Thông tin phần mềm
        JPanel panelInfo = new JPanel();
        panelInfo.setBounds(0, 0, 925, 569);
        panelInfo.setLayout(null);

        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(0, 12, 925, 68);
        panelInfo.add(panelTitle);
        panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel lblPhanMem = new JLabel("Phần mềm quản lý cửa hàng tiện lợi");
        lblPhanMem.setHorizontalAlignment(SwingConstants.CENTER);
        lblPhanMem.setForeground(new Color(0, 153, 255));
        lblPhanMem.setFont(new Font("Dialog", Font.BOLD, 30));
        panelTitle.add(lblPhanMem);

        JPanel panelNhom = new JPanel();
        panelNhom.setBounds(0, 78, 925, 45);
        panelInfo.add(panelNhom);
        panelNhom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel lblNhom = new JLabel("Nhóm 2:");
        lblNhom.setFont(new Font("Dialog", Font.BOLD, 25));
        panelNhom.add(lblNhom);

        String[] thanhVien = {"Trần Ngọc Phát", "Trần Nguyên Vũ", "Mai Nhật Hào", "Võ Phước Hậu"};
        int y = 135;
        for (String tv : thanhVien) {
            JPanel panelTV = new JPanel();
            panelTV.setBounds(0, y, 925, 45);
            panelInfo.add(panelTV);
            panelTV.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            JLabel lblTV = new JLabel(tv);
            lblTV.setFont(new Font("Dialog", Font.BOLD, 25));
            panelTV.add(lblTV);
            y += 57;
        }

        contentPanel.add(panelInfo);

        // Panel Quản lý nhân viên
        panelNhanVien.setBounds(0, 0, 925, 569);
        panelNhanVien.setBackground(UIManager.getColor("Button.background"));
        contentPanel.add(panelNhanVien);
        panelNhanVien.setLayout(null);

        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Thông tin nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
        inputPanel.setBounds(0, 0, 924, 193);
        panelNhanVien.add(inputPanel);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

        Box b = Box.createVerticalBox();
        Box b1 = Box.createHorizontalBox();
        Box b2 = Box.createHorizontalBox();
        Box b3 = Box.createHorizontalBox();
        Box b4 = Box.createHorizontalBox();

        lblMaNV.setFont(new Font("Dialog", Font.PLAIN, 20));
        b1.add(lblMaNV);
        txtMaNV.setFont(new Font("Dialog", Font.PLAIN, 20));
        b1.add(txtMaNV);
        b1.add(Box.createHorizontalStrut(210));
        lblMatKhau.setFont(new Font("Dialog", Font.PLAIN, 20));
        b1.add(lblMatKhau);
        b1.add(Box.createHorizontalStrut(10));
        txtMatKhau.setFont(new Font("Dialog", Font.PLAIN, 20));
        b1.add(txtMatKhau);

        lblHoTen.setFont(new Font("Dialog", Font.PLAIN, 20));
        b2.add(lblHoTen);
        b2.add(Box.createHorizontalStrut(60));
        txtHoTen.setFont(new Font("Dialog", Font.PLAIN, 20));
        b2.add(txtHoTen);
        b2.add(Box.createHorizontalStrut(25));
        lblNgaySinh.setFont(new Font("Dialog", Font.PLAIN, 20));
        b2.add(lblNgaySinh);
        txtNgaySinh.setFont(new Font("Dialog", Font.PLAIN, 20));
        b2.add(txtNgaySinh);

        lblSDT.setFont(new Font("Dialog", Font.PLAIN, 20));
        b3.add(lblSDT);
        b3.add(Box.createHorizontalStrut(85));
        txtSDT.setFont(new Font("Dialog", Font.PLAIN, 20));
        b3.add(txtSDT);
        b3.add(Box.createHorizontalStrut(22));
        lblCCCD.setFont(new Font("Dialog", Font.PLAIN, 20));
        b3.add(lblCCCD);
        b3.add(Box.createHorizontalStrut(40));
        txtCCCD.setFont(new Font("Dialog", Font.PLAIN, 20));
        b3.add(txtCCCD);

        b4.add(btnThem);
        b4.add(Box.createHorizontalStrut(10));
        b4.add(btnXoa);
        b4.add(Box.createHorizontalStrut(10));
        b4.add(btnLuu);
        b4.add(Box.createHorizontalStrut(10));
        b4.add(btnHuy);
        b4.add(Box.createHorizontalStrut(10));
        b4.add(btnTimKiem);

        b.add(b1);
        b.add(Box.createVerticalStrut(10));
        b.add(b2);
        b.add(Box.createVerticalStrut(10));
        b.add(b3);
        b.add(Box.createVerticalStrut(10));
        b.add(b4);
        b.add(Box.createVerticalStrut(20));

        btnThem.setFont(new Font("Arial", Font.BOLD, 15));
        btnXoa.setFont(new Font("Arial", Font.BOLD, 15));
        btnLuu.setFont(new Font("Arial", Font.BOLD, 15));
        btnHuy.setFont(new Font("Arial", Font.BOLD, 15));
        btnTimKiem.setFont(new Font("Arial", Font.BOLD, 15));

        inputPanel.add(b);

        JScrollPane tblPane = new JScrollPane(tbl);
        tblPane.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
        tblPane.setBounds(0, 189, 924, 380);
        panelNhanVien.add(tblPane);

        // Panel Thống kê
        JPanel panelThongKe = new JPanel();
        panelThongKe.setBounds(0, 0, 925, 569);
        panelThongKe.setLayout(new BoxLayout(panelThongKe, BoxLayout.Y_AXIS));
        contentPanel.add(panelThongKe);

        Box bThongKe = Box.createVerticalBox();
        bThongKe.setBorder(new TitledBorder(null, "Thống kê doanh thu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelThongKe.add(bThongKe);

        JButton btnTopKhachHang = new JButton("Top 5 khách hàng");
        btnTopKhachHang.setFont(new Font("Dialog", Font.BOLD, 20));
        bThongKe.add(btnTopKhachHang);
        bThongKe.add(Box.createVerticalStrut(20));

        JButton btnTopSanPham = new JButton("Top 5 sản phẩm");
        btnTopSanPham.setFont(new Font("Dialog", Font.BOLD, 20));
        bThongKe.add(btnTopSanPham);

        btnTopKhachHang.addActionListener(e -> new TopKhachHangDialog(this).setVisible(true));
        btnTopSanPham.addActionListener(e -> new TopSanPhamDialog(this).setVisible(true));

        // Ban đầu chỉ hiển thị panel thông tin
        panelNhanVien.setVisible(false);
        panelThongKe.setVisible(false);

        // Menu handlers
        menuPanelInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelInfo.setVisible(true);
                panelNhanVien.setVisible(false);
                panelThongKe.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                menuPanelInfo.setBackground(new Color(0, 204, 255));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuPanelInfo.setBackground(new Color(36, 31, 49));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        menuPanelNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelInfo.setVisible(false);
                panelNhanVien.setVisible(true);
                panelThongKe.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                menuPanelNhanVien.setBackground(new Color(0, 204, 255));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuPanelNhanVien.setBackground(new Color(36, 31, 49));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        menuPanelThongKe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelInfo.setVisible(false);
                panelNhanVien.setVisible(false);
                panelThongKe.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                menuPanelThongKe.setBackground(new Color(0, 204, 255));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuPanelThongKe.setBackground(new Color(36, 31, 49));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // Action listeners
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnLuu.addActionListener(this);
        btnHuy.addActionListener(this);
        btnTimKiem.addActionListener(this);

        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbl.getSelectedRow();
                txtMaNV.setText(tbl.getValueAt(row, 0).toString());
                txtMatKhau.setText(tbl.getValueAt(row, 1).toString());
                txtHoTen.setText(tbl.getValueAt(row, 2).toString());
                txtNgaySinh.setText(tbl.getValueAt(row, 3).toString());
                txtSDT.setText(tbl.getValueAt(row, 4).toString());
                txtCCCD.setText(tbl.getValueAt(row, 5).toString());
            }
        });
    }

    private void createTable() throws Exception {
        String[] tblCols = {"Mã nhân viên", "Mật khẩu", "Họ tên", "Ngày sinh", "Số điện thoại", "CCCD"};
        model = new DefaultTableModel(tblCols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbl = new JTable(model);
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbl.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(120);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(5).setPreferredWidth(80);
        tbl.setRowHeight(30);
        tbl.setFont(new Font("Arial", Font.PLAIN, 20));

        refreshTable();
    }

    private void refreshTable() throws Exception {
        model.setRowCount(0);
        NhanVien_DAO nvDAO = new NhanVien_DAO();
        List<NhanVien> list = nvDAO.getAllNhanVien();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (NhanVien nv : list) {
            model.addRow(new Object[]{nv.getMaNV(), nv.getMatKhau(), nv.getTenNV(), dtf.format(nv.getNgaySinh()), nv.getSDT(), nv.getCCCD()});
        }
    }

    private void clearInputs() {
        txtMaNV.setText("");
        txtMatKhau.setText("");
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtSDT.setText("");
        txtCCCD.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(btnThem)) {
        	if(validData()) {
        		NhanVien_DAO nvDAO = new NhanVien_DAO();
        		String maNV = txtMaNV.getText();
        		String mk = txtMatKhau.getText();
        		String ten = txtHoTen.getText();
        		LocalDate ngaySinh = LocalDate.parse(txtNgaySinh.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        		String sdt = txtSDT.getText();
        		String cccd = txtCCCD.getText();
        		
        		
        		NhanVien nv = new NhanVien(maNV, mk, ten, ngaySinh, sdt, cccd);
        		try {
					nvDAO.addNhanVien(nv);
					clearInputs();
	                JOptionPane.showMessageDialog(null, "Thêm thành công");
	                refreshTable();
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(this, "Mã nhân viên này đã tồn tại!");
				}
        		
        		
        	}
        } else if (o.equals(btnXoa)) {
            int row = tbl.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Phải chọn một dòng để xóa");
            } else if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa nhân viên " + tbl.getValueAt(row, 0) + " không?", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                NhanVien_DAO nvDAO = new NhanVien_DAO();
                nvDAO.deleteNhanVienByID(tbl.getValueAt(row, 0).toString());
                clearInputs();
                try {
					refreshTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
                JOptionPane.showMessageDialog(this, "Đã xóa thành công");
            }
        } else if (o.equals(btnLuu)) {
            int row = tbl.getSelectedRow();
            if (row != -1) {
                try {
                    if (!txtNgaySinh.getText().matches("\\d{2}/\\d{2}/\\d{4}")) {
                        throw new Exception("Ngày sinh không hợp lệ (dd/MM/yyyy)");
                    }
                    NhanVien_DAO nvDAO = new NhanVien_DAO();
                    NhanVien nv = new NhanVien(txtMaNV.getText(), txtMatKhau.getText(), txtHoTen.getText(),
                            LocalDate.parse(txtNgaySinh.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            txtSDT.getText(), txtCCCD.getText());
                    nvDAO.editNhanVienByID(tbl.getValueAt(row, 0).toString(), nv);
                    clearInputs();
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                    refreshTable();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Lỗi: " + e2.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Chọn một nhân viên để cập nhật");
            }
        } else if (o.equals(btnHuy)) {
            clearInputs();
        } else if (o.equals(btnTimKiem)) {
            String input = JOptionPane.showInputDialog(null, "Nhập mã nhân viên cần tìm: ");
            if (input != null && !input.trim().isEmpty()) {
                NhanVien_DAO nvDAO = new NhanVien_DAO();
                NhanVien nv = null;
				try {
					nv = nvDAO.getNhanVienByID(input);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                if (nv != null) {
                    for (int i = 0; i < tbl.getRowCount(); i++) {
                        if (tbl.getValueAt(i, 0).toString().equalsIgnoreCase(input)) {
                            tbl.setRowSelectionInterval(i, i);
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mã nhân viên không được để trống");
            }
        }
    }

    private boolean validData() {
        // Validate Mã nhân viên
        String maNV = txtMaNV.getText().trim();
        if (maNV.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống!");
            txtMaNV.requestFocus();
            txtMaNV.selectAll();
            return false;
        }
        if (!maNV.matches("^NV\\d{3}$")) {
            JOptionPane.showMessageDialog(this, "Mã nhân viên phải có định dạng NVxxx (xxx là 3 chữ số)!");
            txtMaNV.requestFocus();
            txtMaNV.selectAll();
            return false;
        }

        // Validate Mật khẩu
        String matKhau = txtMatKhau.getText().trim();
        if (matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống!");
            txtMatKhau.requestFocus();
            txtMatKhau.selectAll();
            return false;
        }
        if (matKhau.length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!");
            txtMatKhau.requestFocus();
            txtMatKhau.selectAll();
            return false;
        }

        // Validate Họ tên
        String hoTen = txtHoTen.getText().trim();
        if (hoTen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ tên không được để trống!");
            txtHoTen.requestFocus();
            txtHoTen.selectAll();
            return false;
        }
        if (!hoTen.matches("^[\\p{L}\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Họ tên chỉ được chứa chữ cái và khoảng trắng!");
            txtHoTen.requestFocus();
            txtHoTen.selectAll();
            return false;
        }

        // Validate Ngày sinh
        String ngaySinh = txtNgaySinh.getText().trim();
        if (ngaySinh.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống!");
            txtNgaySinh.requestFocus();
            txtNgaySinh.selectAll();
            return false;
        }
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(ngaySinh, dtf);
            LocalDate today = LocalDate.now();
            if (date.isAfter(today.minusYears(18)) || date.isBefore(today.minusYears(100))) {
                JOptionPane.showMessageDialog(this, "Nhân viên phải từ 18 đến 100 tuổi!");
                txtNgaySinh.requestFocus();
                txtNgaySinh.selectAll();
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ngày sinh phải có định dạng dd/MM/yyyy!");
            txtNgaySinh.requestFocus();
            txtNgaySinh.selectAll();
            return false;
        }

        // Validate SĐT
        String sdt = txtSDT.getText().trim();
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!");
            txtSDT.requestFocus();
            txtSDT.selectAll();
            return false;
        }
        if (!sdt.matches("^0\\d{9}$")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu bằng 0 và có đúng 10 chữ số!");
            txtSDT.requestFocus();
            txtSDT.selectAll();
            return false;
        }

        // Validate CCCD
        String cccd = txtCCCD.getText().trim();
        if (cccd.isEmpty()) {
            JOptionPane.showMessageDialog(this, "CCCD không được để trống!");
            txtCCCD.requestFocus();
            txtCCCD.selectAll();
            return false;
        }
        if (!cccd.matches("^\\d{12}$")) {
            JOptionPane.showMessageDialog(this, "CCCD phải có đúng 12 chữ số!");
            txtCCCD.requestFocus();
            txtCCCD.selectAll();
            return false;
        }

        return true;
    }
}