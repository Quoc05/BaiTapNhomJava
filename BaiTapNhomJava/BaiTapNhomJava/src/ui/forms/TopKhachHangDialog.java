package ui.forms;


import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout; 
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.KhachHang_DAO;
import entity.*;
import ui.gui.GUI_QuanLyCuaHang;
import ui.panels.PanelThongKe;

public class TopKhachHangDialog extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TopKhachHangDialog(GUI_QuanLyCuaHang gui_QuanLyCuaHang) {
        super();
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.BorderLayout());

        JLabel label = new JLabel("Top 5 khách hàng thường xuyên");
        label.setFont(new Font("Dialog", Font.BOLD, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label, java.awt.BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        // Khởi tạo BoxLayout đúng cách với container và hướng bố cục
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        KhachHang_DAO khDAO = new KhachHang_DAO();
        try {
            List<KhachHang> dsKhachHang = khDAO.getAllKhachHang(); // Giả sử lấy top 5 từ DB
            int count = 0;
            for (KhachHang kh : dsKhachHang) {
                if (count >= 5) break;
                JLabel khLabel = new JLabel("Mã KH: " + kh.getMaKH() + " - Tên: " + kh.getTenKH() + " - SĐT: " + kh.getSDT());
                khLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
                contentPanel.add(khLabel);
                contentPanel.add(javax.swing.Box.createVerticalStrut(10));
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        panel.add(scrollPane, java.awt.BorderLayout.CENTER);

        add(panel);
    }

	public TopKhachHangDialog(PanelThongKe panelThongKe) {
		// TODO Auto-generated constructor stub
	}
}