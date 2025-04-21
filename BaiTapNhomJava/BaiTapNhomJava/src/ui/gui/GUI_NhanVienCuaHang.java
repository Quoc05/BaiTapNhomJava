package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
import dao.SanPham_DAO;
import entity.*;
import ui.forms.FormKhachHang;

public class GUI_NhanVienCuaHang extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel panelSanPham = new JPanel();
    private final JTextField txtMaSP = new JTextField();
    private final JTextField txtTenSP = new JTextField();
    private final JTextField txtGiaBan = new JTextField();
    private final JComboBox<String> cboLoaiSP = new JComboBox<String>();
    private final JLabel lblMaSP = new JLabel("Mã sản phẩm: ");
    private final JLabel lblTenSP = new JLabel("Tên sản phẩm: ");
    private final JLabel lblGiaBan = new JLabel("Giá bán: ");
    private final JLabel lblLoaiSP = new JLabel("Loại sản phẩm: ");
    private final JButton btnThem = new JButton("Thêm");
    private final JButton btnXoa = new JButton("Xóa");
    private final JButton btnLuu = new JButton("Lưu");
    private final JButton btnHuy = new JButton("Xóa trắng");
    private final JButton btnTimKiem = new JButton("Tìm kiếm");
    private final JButton btnXacNhan = new JButton("Xác nhận");
    private final JButton btnShow = new JButton("");
    private boolean isPasswordShown = false;
    private String currentMaNV = "";
    private JTable tbl;
    private DefaultTableModel model;

    private final JTextField txtMaNV_2;
    private final JTextField txtSDT_2;
    private final JTextField txtMaNV_1;
    private final JPasswordField txtMatKhau;
    private final JPasswordField txtMatKhauConfirm;
    private final JTextField txtCCCD;
    private final JPasswordField txtMatKhau_2;
    private final JTextField txtNgaySinh_2;
    private final JTextField txtHoTen_2;
    private final JPasswordField txtMatKhauOld;

    public GUI_NhanVienCuaHang(String maNV) throws Exception {
        setTitle("Phần mềm quản lý cửa hàng tiện lợi - Nhân viên");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
//        setIconImage(new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/store-icon.png")).getImage());

        this.currentMaNV = maNV;

        getContentPane().setLayout(null);

        // Left Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        leftPanel.setBackground(new Color(204, 204, 255));
        leftPanel.setLocation(0, 0);
        leftPanel.setSize(271, 669);
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
//        logoLabel.setIcon(new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/store-icon.png")));
        logoPanel.add(logoLabel);

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(0, 0, 0, 0));
        menuPanel.setBounds(0, 90, 271, 579);
        leftPanel.add(menuPanel);
        menuPanel.setLayout(null);

        JPanel menuPanelInfo = new JPanel();
        menuPanelInfo.setBackground(new Color(36, 31, 49));
        menuPanelInfo.setBounds(0, 30, 271, 80);
        menuPanel.add(menuPanelInfo);
        menuPanelInfo.setLayout(new BorderLayout(0, 0));

        JLabel lblThongTinPhanMem = new JLabel("Thông tin phần mềm");
//        lblThongTinPhanMem.setIcon(new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/info-icon.png")));
        lblThongTinPhanMem.setForeground(Color.WHITE);
        lblThongTinPhanMem.setHorizontalAlignment(SwingConstants.CENTER);
        lblThongTinPhanMem.setFont(new Font("Dialog", Font.BOLD, 20));
        menuPanelInfo.add(lblThongTinPhanMem, BorderLayout.CENTER);

        JPanel menuPanelSanPham = new JPanel();
        menuPanelSanPham.setBackground(new Color(36, 31, 49));
        menuPanelSanPham.setBounds(0, 130, 271, 80);
        menuPanel.add(menuPanelSanPham);
        menuPanelSanPham.setLayout(new BorderLayout(0, 0));

        JLabel lblQuanLySanPham = new JLabel("Quản lý sản phẩm");
//        lblQuanLySanPham.setIcon(new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/magnify-icon.png")));
        lblQuanLySanPham.setForeground(Color.WHITE);
        lblQuanLySanPham.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuanLySanPham.setFont(new Font("Dialog", Font.BOLD, 20));
        menuPanelSanPham.add(lblQuanLySanPham, BorderLayout.CENTER);

        JPanel menuPanelThanhToan = new JPanel();
        menuPanelThanhToan.setBackground(new Color(36, 31, 49));
        menuPanelThanhToan.setBounds(0, 230, 271, 80);
        menuPanel.add(menuPanelThanhToan);
        menuPanelThanhToan.setLayout(new BorderLayout(0, 0));

        JLabel lblThanhToan = new JLabel("Thanh toán");
//        lblThanhToan.setIcon(new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/cart-icon.png")));
        lblThanhToan.setForeground(Color.WHITE);
        lblThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
        lblThanhToan.setFont(new Font("Dialog", Font.BOLD, 20));
        menuPanelThanhToan.add(lblThanhToan);

        JPanel menuPanelAdjustInfo = new JPanel();
        menuPanelAdjustInfo.setBackground(new Color(36, 31, 49));
        menuPanelAdjustInfo.setBounds(0, 330, 271, 80);
        menuPanel.add(menuPanelAdjustInfo);
        menuPanelAdjustInfo.setLayout(new BorderLayout(0, 0));

        JLabel lblChinhSuaThongTin = new JLabel("Chỉnh sửa thông tin");
//        lblChinhSuaThongTin.setIcon(new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/account-cog-custom.png")));
        lblChinhSuaThongTin.setForeground(Color.WHITE);
        lblChinhSuaThongTin.setHorizontalAlignment(SwingConstants.CENTER);
        lblChinhSuaThongTin.setFont(new Font("Dialog", Font.BOLD, 20));
        menuPanelAdjustInfo.add(lblChinhSuaThongTin);

        JLabel lblWelcome = new JLabel("Welcome, " + currentMaNV);
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setBounds(0, 620, 271, 28);
        lblWelcome.setFont(new Font("Dialog", Font.BOLD, 20));
        leftPanel.add(lblWelcome);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(271, 0, 725, 669);
        getContentPane().add(contentPanel);
        contentPanel.setLayout(null);

        // Create table
        createTable();

        // Panel Thông tin phần mềm
        JPanel panelInfo = new JPanel();
        panelInfo.setBounds(0, 0, 725, 669);
        panelInfo.setLayout(null);

        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(0, 12, 725, 68);
        panelInfo.add(panelTitle);
        panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel lblPhanMem = new JLabel("Phần mềm quản lý cửa hàng tiện lợi");
        lblPhanMem.setForeground(new Color(0, 153, 255));
        lblPhanMem.setFont(new Font("Dialog", Font.BOLD, 30));
        panelTitle.add(lblPhanMem);

        JPanel panelNhom = new JPanel();
        panelNhom.setBounds(0, 72, 725, 45);
        panelInfo.add(panelNhom);
        panelNhom.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JLabel lblNhom = new JLabel("Nhóm 2:");
        lblNhom.setFont(new Font("Dialog", Font.BOLD, 25));
        panelNhom.add(lblNhom);

        String[] thanhVien = {"Trần Ngọc Phát", "Trần Nguyên Vũ", "Mai Nhật Hào", "Võ Phước Hậu"};
        int y = 135;
        for (String tv : thanhVien) {
            JPanel panelTV = new JPanel();
            panelTV.setBounds(0, y, 725, 45);
            panelInfo.add(panelTV);
            panelTV.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            JLabel lblTV = new JLabel(tv);
            lblTV.setFont(new Font("Dialog", Font.BOLD, 25));
            panelTV.add(lblTV);
            y += 70;
        }

        contentPanel.add(panelInfo);

        // Panel Quản lý sản phẩm
        panelSanPham.setBounds(0, 0, 725, 669);
        panelSanPham.setBackground(UIManager.getColor("Button.background"));
        contentPanel.add(panelSanPham);
        panelSanPham.setLayout(null);

        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(new TitledBorder(null, "Thông tin sản phẩm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        inputPanel.setBounds(0, 0, 715, 284);
        panelSanPham.add(inputPanel);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

        Box b = Box.createVerticalBox();
        Box b1 = Box.createHorizontalBox();
        Box b2 = Box.createHorizontalBox();
        Box b3 = Box.createHorizontalBox();
        Box b4 = Box.createHorizontalBox();

        lblMaSP.setFont(new Font("Arial", Font.PLAIN, 15));
        b1.add(lblMaSP);
        txtMaSP.setFont(new Font("Arial", Font.PLAIN, 15));
        b1.add(txtMaSP);

        lblTenSP.setFont(new Font("Arial", Font.PLAIN, 15));
        b2.add(lblTenSP);
        txtTenSP.setFont(new Font("Arial", Font.PLAIN, 15));
        b2.add(txtTenSP);

        lblGiaBan.setFont(new Font("Arial", Font.PLAIN, 15));
        b3.add(lblGiaBan);
        txtGiaBan.setFont(new Font("Arial", Font.PLAIN, 15));
        b3.add(txtGiaBan);

        lblLoaiSP.setFont(new Font("Arial", Font.PLAIN, 15));
        b4.add(lblLoaiSP);
        cboLoaiSP.addItem("Đồ uống");
        cboLoaiSP.addItem("Thực phẩm");
        cboLoaiSP.addItem("Hàng gia dụng");
        cboLoaiSP.setFont(new Font("Arial", Font.PLAIN, 15));
        b4.add(cboLoaiSP);
        b4.add(Box.createHorizontalStrut(10));
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
        tblPane.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
        tblPane.setBounds(0, 285, 715, 384);
        panelSanPham.add(tblPane);

        // Panel Chỉnh sửa thông tin
        JPanel panelAdjustInfo = new JPanel();
        panelAdjustInfo.setBounds(0, 0, 725, 669);
        panelAdjustInfo.setBackground(UIManager.getColor("Button.background"));
        contentPanel.add(panelAdjustInfo);
        panelAdjustInfo.setLayout(new BoxLayout(panelAdjustInfo, BoxLayout.Y_AXIS));

        Box b10_1 = Box.createVerticalBox();
        b10_1.setBorder(new TitledBorder(null, "Chỉnh sửa thông tin", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelAdjustInfo.add(b10_1);

        Box b11_1 = Box.createHorizontalBox();
        JLabel lblMaNV_1 = new JLabel("Mã nhân viên: ");
        lblMaNV_1.setFont(new Font("Dialog", Font.BOLD, 20));
        b11_1.add(lblMaNV_1);
        b11_1.add(Box.createHorizontalStrut(52));
        txtMaNV_1 = new JTextField(currentMaNV);
        txtMaNV_1.setEditable(false);
        txtMaNV_1.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtMaNV_1.setColumns(10);
        b11_1.add(txtMaNV_1);
        b10_1.add(b11_1);
        b10_1.add(Box.createVerticalStrut(20));

        Box b12_1 = Box.createHorizontalBox();
        JLabel lblMatKhauCu = new JLabel("Mật khẩu cũ: ");
        lblMatKhauCu.setFont(new Font("Dialog", Font.BOLD, 20));
        b12_1.add(lblMatKhauCu);
        b12_1.add(Box.createHorizontalStrut(64));
        txtMatKhauOld = new JPasswordField();
        txtMatKhauOld.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtMatKhauOld.setColumns(20);
        b12_1.add(txtMatKhauOld);
        b12_1.add(Box.createHorizontalStrut(20));
        btnShow.setPreferredSize(new Dimension(40, 30));
//        btnShow.setIcon(new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/eye-off-custom.png")));
        b12_1.add(btnShow);
        b10_1.add(b12_1);
        b10_1.add(Box.createVerticalStrut(20));

        Box b13_1 = Box.createHorizontalBox();
        JLabel lblMatKhauMoi = new JLabel("Mật khẩu mới: ");
        lblMatKhauMoi.setFont(new Font("Dialog", Font.BOLD, 20));
        b13_1.add(lblMatKhauMoi);
        b13_1.add(Box.createHorizontalStrut(48));
        txtMatKhau = new JPasswordField();
        txtMatKhau.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtMatKhau.setColumns(20);
        b13_1.add(txtMatKhau);
        b10_1.add(b13_1);
        b10_1.add(Box.createVerticalStrut(20));

        Box b14_1 = Box.createHorizontalBox();
        JLabel lblXacNhanMatKhau = new JLabel("Xác nhận mật khẩu: ");
        lblXacNhanMatKhau.setFont(new Font("Dialog", Font.BOLD, 20));
        b14_1.add(lblXacNhanMatKhau);
        txtMatKhauConfirm = new JPasswordField();
        txtMatKhauConfirm.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtMatKhauConfirm.setColumns(20);
        b14_1.add(txtMatKhauConfirm);
        b10_1.add(b14_1);
        b10_1.add(Box.createVerticalStrut(15));

        Box b15_1 = Box.createHorizontalBox();
        btnXacNhan.setFont(new Font("Dialog", Font.BOLD, 20));
        b15_1.add(btnXacNhan);
        b10_1.add(b15_1);

        Box b10_2 = Box.createVerticalBox();
        b10_2.setBorder(new TitledBorder(null, "Thông tin nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelAdjustInfo.add(b10_2);
        b10_2.add(Box.createVerticalStrut(15));

        Box b11_2 = Box.createHorizontalBox();
        JLabel lblMaNV = new JLabel("Mã nhân viên: ");
        lblMaNV.setFont(new Font("Dialog", Font.BOLD, 20));
        b11_2.add(lblMaNV);
        b11_2.add(Box.createHorizontalStrut(90));
        txtMaNV_2 = new JTextField();
        txtMaNV_2.setEditable(false);
        txtMaNV_2.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtMaNV_2.setColumns(10);
        b11_2.add(txtMaNV_2);
        b10_2.add(b11_2);

        Box b12_2 = Box.createHorizontalBox();
        JLabel lblMatKhau = new JLabel("Mật khẩu: ");
        lblMatKhau.setFont(new Font("Dialog", Font.BOLD, 20));
        b12_2.add(lblMatKhau);
        b12_2.add(Box.createHorizontalStrut(130));
        txtMatKhau_2 = new JPasswordField();
        txtMatKhau_2.setEditable(false);
        txtMatKhau_2.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtMatKhau_2.setColumns(20);
        b12_2.add(txtMatKhau_2);
        b10_2.add(b12_2);

        Box b13_2 = Box.createHorizontalBox();
        JLabel lblHoTen = new JLabel("Họ tên: ");
        lblHoTen.setFont(new Font("Dialog", Font.BOLD, 20));
        b13_2.add(lblHoTen);
        b13_2.add(Box.createHorizontalStrut(164));
        txtHoTen_2 = new JTextField();
        txtHoTen_2.setEditable(false);
        txtHoTen_2.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtHoTen_2.setColumns(20);
        b13_2.add(txtHoTen_2);
        b10_2.add(b13_2);

        Box b14_2 = Box.createHorizontalBox();
        JLabel lblNgaySinh = new JLabel("Ngày sinh: ");
        lblNgaySinh.setFont(new Font("Dialog", Font.BOLD, 20));
        b14_2.add(lblNgaySinh);
        b14_2.add(Box.createHorizontalStrut(130));
        txtNgaySinh_2 = new JTextField();
        txtNgaySinh_2.setEditable(false);
        txtNgaySinh_2.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtNgaySinh_2.setColumns(20);
        b14_2.add(txtNgaySinh_2);
        b10_2.add(b14_2);

        Box b15_2 = Box.createHorizontalBox();
        JLabel lblSDT = new JLabel("Số điện thoại: ");
        lblSDT.setFont(new Font("Dialog", Font.BOLD, 20));
        b15_2.add(lblSDT);
        b15_2.add(Box.createHorizontalStrut(100));
        txtSDT_2 = new JTextField();
        txtSDT_2.setEditable(false);
        txtSDT_2.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtSDT_2.setColumns(20);
        b15_2.add(txtSDT_2);
        b10_2.add(b15_2);

        Box b16_2 = Box.createHorizontalBox();
        JLabel lblCCCD = new JLabel("CCCD: ");
        lblCCCD.setFont(new Font("Dialog", Font.BOLD, 20));
        b16_2.add(lblCCCD);
        b16_2.add(Box.createHorizontalStrut(175));
        txtCCCD = new JTextField();
        txtCCCD.setEditable(false);
        txtCCCD.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtCCCD.setColumns(20);
        b16_2.add(txtCCCD);
        b10_2.add(b16_2);

        // Ban đầu chỉ hiển thị panel thông tin
        panelSanPham.setVisible(false);
        panelAdjustInfo.setVisible(false);

        // Menu handlers
        menuPanelInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelInfo.setVisible(true);
                panelSanPham.setVisible(false);
                panelAdjustInfo.setVisible(false);
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

        menuPanelSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelInfo.setVisible(false);
                panelSanPham.setVisible(true);
                panelAdjustInfo.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                menuPanelSanPham.setBackground(new Color(0, 204, 255));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuPanelSanPham.setBackground(new Color(36, 31, 49));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        menuPanelThanhToan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelInfo.setVisible(false);
                panelSanPham.setVisible(false);
                panelAdjustInfo.setVisible(false);
                FormKhachHang form = new FormKhachHang(currentMaNV);
                form.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                menuPanelThanhToan.setBackground(new Color(0, 204, 255));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuPanelThanhToan.setBackground(new Color(36, 31, 49));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        menuPanelAdjustInfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelInfo.setVisible(false);
                panelSanPham.setVisible(false);
                panelAdjustInfo.setVisible(true);
                try {
					loadDataToPanelAdjustInfo();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                menuPanelAdjustInfo.setBackground(new Color(0, 204, 255));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuPanelAdjustInfo.setBackground(new Color(36, 31, 49));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        // Action listeners
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnLuu.addActionListener(this);
        btnHuy.addActionListener(this);
        btnTimKiem.addActionListener(this);
        btnXacNhan.addActionListener(this);
        btnShow.addActionListener(this);

        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbl.getSelectedRow();
                txtMaSP.setText(tbl.getValueAt(row, 0).toString());
                txtTenSP.setText(tbl.getValueAt(row, 1).toString());
                txtGiaBan.setText(tbl.getValueAt(row, 2).toString());
                cboLoaiSP.setSelectedItem(tbl.getValueAt(row, 3).toString());
            }
        });

        loadDataToPanelAdjustInfo();
    }

    private void createTable() {
        String[] tblCols = {"Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Loại sản phẩm"};
        model = new DefaultTableModel(tblCols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbl = new JTable(model);
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbl.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl.setRowHeight(30);
        tbl.setFont(new Font("Arial", Font.PLAIN, 20));

        refreshTable();
    }

    private void refreshTable() {
        model.setRowCount(0);
        SanPham_DAO spDAO = new SanPham_DAO();
        List<SanPham> list = null;
		try {
			list = spDAO.getAllSanPham();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (SanPham sp : list) {
            model.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getGiaBan(), sp.getLoaiSP()});
        }
    }

    private void loadDataToPanelAdjustInfo() throws Exception {
        NhanVien_DAO nvDAO = new NhanVien_DAO();
        NhanVien nv = nvDAO.getNhanVienByID(currentMaNV);
        if (nv != null) {
            txtMaNV_2.setText(nv.getMaNV());
            txtMatKhau_2.setText(nv.getMatKhau());
            txtHoTen_2.setText(nv.getTenNV());
            txtNgaySinh_2.setText(nv.getNgaySinh().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            txtSDT_2.setText(nv.getSDT());
            txtCCCD.setText(nv.getCCCD()); // Đảm bảo getCCCD() tồn tại
        }
    }

    private void clearInputs() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        txtGiaBan.setText("");
        cboLoaiSP.setSelectedIndex(0);
    }

    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(btnThem)) {
            try {
                SanPham_DAO spDAO = new SanPham_DAO();
                String maSP = txtMaSP.getText().trim();
                if (spDAO.getSanPhamByID(maSP) != null) {
                    throw new Exception("Mã sản phẩm đã tồn tại");
                }
                if (!txtGiaBan.getText().matches("\\d+(\\.\\d+)?")) {
                    throw new Exception("Giá bán phải là số");
                }
                SanPham sp = new SanPham(maSP, txtTenSP.getText(), Double.parseDouble(txtGiaBan.getText()), cboLoaiSP.getSelectedItem().toString());
                spDAO.addSanPham(sp);
                clearInputs();
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                refreshTable();
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Lỗi: " + e2.getMessage());
            }
        } else if (o.equals(btnXoa)) {
            int row = tbl.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Phải chọn một dòng để xóa");
            } else if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa sản phẩm " + tbl.getValueAt(row, 0) + " không?", "Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                SanPham_DAO spDAO = new SanPham_DAO();
                spDAO.deleteSanPhamByID(tbl.getValueAt(row, 0).toString());
                clearInputs();
                refreshTable();
                JOptionPane.showMessageDialog(this, "Đã xóa thành công");
            }
        } else if (o.equals(btnLuu)) {
            int row = tbl.getSelectedRow();
            if (row != -1) {
                try {
                    if (!txtGiaBan.getText().matches("\\d+(\\.\\d+)?")) {
                        throw new Exception("Giá bán phải là số");
                    }
                    SanPham_DAO spDAO = new SanPham_DAO();
                    SanPham sp = new SanPham(txtMaSP.getText(), txtTenSP.getText(), Double.parseDouble(txtGiaBan.getText()), cboLoaiSP.getSelectedItem().toString());
                    spDAO.editSanPhamByID(tbl.getValueAt(row, 0).toString(), sp);
                    clearInputs();
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                    refreshTable();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Lỗi: " + e2.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Chọn một sản phẩm để cập nhật");
            }
        } else if (o.equals(btnHuy)) {
            clearInputs();
        } else if (o.equals(btnTimKiem)) {
            String input = JOptionPane.showInputDialog(null, "Nhập mã sản phẩm cần tìm: ");
            if (input != null && !input.trim().isEmpty()) {
                SanPham_DAO spDAO = new SanPham_DAO();
                SanPham sp = null;
				try {
					sp = spDAO.getSanPhamByID(input);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                if (sp != null) {
                    for (int i = 0; i < tbl.getRowCount(); i++) {
                        if (tbl.getValueAt(i, 0).toString().equalsIgnoreCase(input)) {
                            tbl.setRowSelectionInterval(i, i);
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mã sản phẩm không được để trống");
            }
        } else if (o.equals(btnShow)) {
            isPasswordShown = !isPasswordShown;
            if (isPasswordShown) {
//                btnShow.setIcon(new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/eye-custom.png")));
                txtMatKhauOld.setEchoChar((char) 0);
            } else {
//                btnShow.setIcon(new ImageIcon(GUI_NhanVienCuaHang.class.getResource("/images/eye-off-custom.png")));
                txtMatKhauOld.setEchoChar('*');
            }
        } else if (o.equals(btnXacNhan)) {
            if (txtMatKhau.getText().equals(txtMatKhauConfirm.getText())) {
                NhanVien_DAO nvDAO = new NhanVien_DAO();
                if (nvDAO.checkLogin(currentMaNV, txtMatKhauOld.getText())) {
                    try {
                        NhanVien nv = nvDAO.getNhanVienByID(currentMaNV);
                        nv.setMatKhau(txtMatKhau.getText());
                        nvDAO.editNhanVienByID(currentMaNV, nv);
                        JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
                        txtMatKhauOld.setText("");
                        txtMatKhau.setText("");
                        txtMatKhauConfirm.setText("");
                        loadDataToPanelAdjustInfo();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, e1.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Mật khẩu cũ sai!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mật khẩu xác nhận không khớp!");
            }
        }
    }
}