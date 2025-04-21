/*
 * @ (#) PanelThemSanPham.java    1.0   Apr 6, 2025
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
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.SanPham_DAO;
import entity.*;
import ui.forms.FormKhachHang;

public class PanelThemSanPham extends JPanel implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tbl;
    private DefaultTableModel model;
    private String currentMaNV;
    private JComboBox<String> cboFilterLoaiSP = new JComboBox<String>();
    private JComboBox<String> cboFilterGia = new JComboBox<String>();
    private TableRowSorter<DefaultTableModel> rowSorter;

    public PanelThemSanPham(String currentMaNV) throws Exception {
        this.currentMaNV = currentMaNV;
        setBounds(0, 0, 725, 669);
        setLayout(null);

        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(0, 0, 725, 90);
        add(titlePanel);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        Box verticalBox = Box.createVerticalBox();
        titlePanel.add(verticalBox);

        Box horizontalBox = Box.createHorizontalBox();
        verticalBox.add(horizontalBox);

        JLabel mainTitle = new JLabel("Thêm sản phẩm");
        horizontalBox.add(mainTitle);
        mainTitle.setFont(new Font("Dialog", Font.BOLD, 20));
        mainTitle.setHorizontalAlignment(SwingConstants.TRAILING);

        Component verticalStrut_1 = Box.createVerticalStrut(10);
        verticalBox.add(verticalStrut_1);

        Box horizontalBox_1 = Box.createHorizontalBox();
        horizontalBox_1.setBorder(new TitledBorder(null, "Bộ lọc tìm kiếm", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        verticalBox.add(horizontalBox_1);

        JLabel lblLoaiSP = new JLabel("Loại sản phẩm: ");
        lblLoaiSP.setFont(new Font("Dialog", Font.PLAIN, 16));
        horizontalBox_1.add(lblLoaiSP);

        cboFilterLoaiSP.setFont(new Font("Dialog", Font.PLAIN, 15));
        cboFilterLoaiSP.addItem("---");
        cboFilterLoaiSP.addItem("Đồ uống");
        cboFilterLoaiSP.addItem("Thực phẩm");
        cboFilterLoaiSP.addItem("Hàng gia dụng");
        horizontalBox_1.add(cboFilterLoaiSP);

        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalBox_1.add(horizontalStrut);

        JLabel lblGia = new JLabel("Giá: ");
        lblGia.setFont(new Font("Dialog", Font.PLAIN, 16));
        horizontalBox_1.add(lblGia);

        cboFilterGia.setFont(new Font("Dialog", Font.PLAIN, 15));
        cboFilterGia.addItem("---");
        cboFilterGia.addItem("Tăng");
        cboFilterGia.addItem("Giảm");
        horizontalBox_1.add(cboFilterGia);

        createTable();

        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbl.getSelectedRow();
                if (row != -1) {
                    SanPham_DAO spDAO = new SanPham_DAO();
                    String maSP = model.getValueAt(row, 0).toString();
                    FormKhachHang form = new FormKhachHang(currentMaNV); // Mở form để thêm sản phẩm vào hóa đơn
                    form.addSanPhamToHoaDon(maSP); // Giả định FormKhachHang có phương thức này
                    form.setVisible(true);
                    try {
						refreshTable();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                } else {
                    JOptionPane.showMessageDialog(null, "Phải chọn một sản phẩm");
                }
            }
        });

        cboFilterLoaiSP.addActionListener(this);
        cboFilterGia.addActionListener(this);
    }

    public void refreshTable() throws Exception {
        SanPham_DAO spDAO = new SanPham_DAO();
        List<SanPham> listSanPham = spDAO.getAllSanPham();
        model.setRowCount(0);
        for (SanPham sp : listSanPham) {
            model.addRow(new Object[]{sp.getMaSP(), sp.getTenSP(), sp.getGiaBan(), sp.getLoaiSP()});
        }
    }

    private void createTable() throws Exception {
        String[] tblCols = {"Mã sản phẩm", "Tên sản phẩm", "Giá bán", "Loại sản phẩm"};
        model = new DefaultTableModel(tblCols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tbl = new JTable(model);
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        rowSorter = new TableRowSorter<>(model);
        tbl.setRowSorter(rowSorter);

        tbl.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(80);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl.setRowHeight(30);
        tbl.setFont(new Font("Arial", Font.PLAIN, 20));

        JScrollPane tblPane = new JScrollPane(tbl);
        tblPane.setBounds(0, 94, 725, 575);
        tblPane.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
        add(tblPane);

        refreshTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(cboFilterLoaiSP)) {
            if (cboFilterLoaiSP.getSelectedIndex() == 0) {
                rowSorter.setRowFilter(null);
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter(cboFilterLoaiSP.getSelectedItem().toString(), 3));
            }
        } else if (o.equals(cboFilterGia)) {
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            if (cboFilterGia.getSelectedIndex() == 1) {
                sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
            } else if (cboFilterGia.getSelectedIndex() == 2) {
                sortKeys.add(new RowSorter.SortKey(2, SortOrder.DESCENDING));
            } else {
                rowSorter.setSortKeys(null);
            }
            rowSorter.setSortKeys(sortKeys);
        }
    }
}