/*
 * @ (#) TopSanPhamDialog.java    1.0   Apr 6, 2025
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

import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dao.SanPham_DAO;
import entity.*;
import ui.panels.PanelThongKe;

public class TopSanPhamDialog extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TopSanPhamDialog(JFrame parent) {
        setTitle("Top 5 sản phẩm bán chạy");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.BorderLayout());

        JLabel label = new JLabel("Top 5 sản phẩm bán chạy");
        label.setFont(new Font("Dialog", Font.BOLD, 20));
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label, java.awt.BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        // Khởi tạo BoxLayout đúng cách với container và hướng bố cục
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        SanPham_DAO spDAO = new SanPham_DAO();
        try {
            List<SanPham> dsSanPham = spDAO.getAllSanPham(); // Giả sử lấy top 5 từ DB
            int count = 0;
            for (SanPham sp : dsSanPham) {
                if (count >= 5) break;
                JLabel spLabel = new JLabel("Mã SP: " + sp.getMaSP() + " - Tên: " + sp.getTenSP() + " - Giá: " + sp.getGiaBan());
                spLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
                contentPanel.add(spLabel);
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

	public TopSanPhamDialog(PanelThongKe panelThongKe) {
		// TODO Auto-generated constructor stub
	}
}