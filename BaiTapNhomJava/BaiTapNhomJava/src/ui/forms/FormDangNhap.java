
package ui.forms;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.NhanVien_DAO;

public class FormDangNhap extends JDialog implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNV;
    private JPasswordField txtMatKhau;
    private JButton btnDangNhap;
    private JButton btnShow = new JButton("");
    private boolean isPasswordShown = false;
    private NhanVien_DAO nhanVienDAO = new NhanVien_DAO();
    public String maNVDangNhap;

    public String getMaNVDangNhap() {
        return maNVDangNhap;
    }

    public FormDangNhap() {
        setTitle("Đăng nhập");
        setSize(600, 550);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
//        setIconImage(new ImageIcon(FormDangNhap.class.getResource("/images/store-icon.png")).getImage());

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLUE);
        panel.setBounds(0, 0, 596, 99);
        mainPanel.add(panel);
        panel.setLayout(null);

        JLabel logoLabel = new JLabel("Quản Lý Cửa Hàng Tiện Lợi");
        logoLabel.setBounds(0, 0, 596, 99);
        panel.add(logoLabel);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setFont(new Font("Dialog", Font.BOLD, 28));
        logoLabel.setForeground(Color.WHITE);
//        logoLabel.setIcon(new ImageIcon(FormDangNhap.class.getResource("/images/store-icon.png")));

        txtMaNV = new JTextField();
        txtMaNV.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtMaNV.setBounds(40, 166, 520, 50);
        mainPanel.add(txtMaNV);
        txtMaNV.setColumns(10);

        txtMatKhau = new JPasswordField();
        txtMatKhau.setEchoChar('*');
        txtMatKhau.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtMatKhau.setColumns(10);
        txtMatKhau.setBounds(40, 278, 472, 50);
        mainPanel.add(txtMatKhau);

//        btnShow.setIcon(new ImageIcon(FormDangNhap.class.getResource("/images/eye-off-custom.png")));
        btnShow.setPreferredSize(new Dimension(40, 30));
        btnShow.setBounds(510, 278, 50, 49);
        mainPanel.add(btnShow);

        JLabel lblMaNV = new JLabel("Mã đăng nhập:");
        lblMaNV.setForeground(Color.BLACK);
        lblMaNV.setFont(new Font("Dialog", Font.BOLD, 20));
        lblMaNV.setBounds(40, 137, 160, 25);
        mainPanel.add(lblMaNV);

        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setForeground(Color.BLACK);
        lblMatKhau.setFont(new Font("Dialog", Font.BOLD, 20));
        lblMatKhau.setBounds(40, 248, 160, 25);
        mainPanel.add(lblMatKhau);

        btnDangNhap = new JButton("Đăng nhập");
        btnDangNhap.setFont(new Font("Dialog", Font.BOLD, 30));
        btnDangNhap.setBounds(40, 364, 520, 87);
        mainPanel.add(btnDangNhap);

        btnDangNhap.addActionListener(this);
        btnShow.addActionListener(this);

        txtMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnDangNhap.doClick();
                }
            }
        });

        txtMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                    btnDangNhap.doClick();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(btnDangNhap)) {
            String maNV = txtMaNV.getText().trim();
            @SuppressWarnings("deprecation")
			String password = txtMatKhau.getText().trim();

            if (maNV.equals("admin") && password.equals("admin")) {
                maNVDangNhap = "admin";
                dispose();
            } else {
                if (nhanVienDAO.checkLogin(maNV, password)) {
                    maNVDangNhap = maNV;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu");
                }
            }
        } else if (o.equals(btnShow)) {
            isPasswordShown = !isPasswordShown;
            if (isPasswordShown) {
//                btnShow.setIcon(new ImageIcon(FormDangNhap.class.getResource("/images/eye-custom.png")));
                txtMatKhau.setEchoChar((char) 0);
            } else {
//                btnShow.setIcon(new ImageIcon(FormDangNhap.class.getResource("/images/eye-off-custom.png")));
                txtMatKhau.setEchoChar('*');
            }
        }
    }
}