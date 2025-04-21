/*
 * @ (#) PanelThongKe.java    1.0   Apr 6, 2025
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

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import dao.HoaDon_DAO;
import entity.HoaDon;
import ui.forms.HoaDonDialog;
import ui.forms.TopKhachHangDialog;
import ui.forms.TopSanPhamDialog;

public class PanelThongKe extends JPanel implements ActionListener {
    private double tongTien;
    private JTable tbl;
    private DefaultTableModel model;
    private JTextField txtTuNgay;
    private JTextField txtDenNgay;
    private JTextField txtNhanVien;
    private JLabel lblSoLuongHoaDon = new JLabel("");
    private JLabel lblTongTien = new JLabel("");
    private JButton btnXoaBoLoc = new JButton("Xóa mọi bộ lọc");
    private JButton btnXemTopKH = new JButton("Xem top khách hàng");
    private JButton btnXemTopSP = new JButton("Xem top sản phẩm");
    private TableRowSorter<DefaultTableModel> rowSorter;
    private RowFilter<DefaultTableModel, Integer> dateFilter = null;
    private RowFilter<DefaultTableModel, Integer> staffFilter = null;

    public PanelThongKe() {
        setBounds(0, 0, 925, 669);
        setBackground(UIManager.getColor("Button.background"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        createTable();

        Box b = Box.createVerticalBox();
        add(b);

        Component verticalStrut = Box.createVerticalStrut(20);
        b.add(verticalStrut);

        Box b1 = Box.createHorizontalBox();
        b.add(b1);

        Component horizontalStrut_2 = Box.createHorizontalStrut(20);
        b1.add(horizontalStrut_2);

        JLabel lblTuNgay = new JLabel("Từ ngày:");
        lblTuNgay.setFont(new Font("Dialog", Font.BOLD, 20));
        b1.add(lblTuNgay);

        Component horizontalStrut = Box.createHorizontalStrut(20);
        b1.add(horizontalStrut);

        txtTuNgay = new JTextField();
        txtTuNgay.setFont(new Font("Dialog", Font.PLAIN, 20));
        b1.add(txtTuNgay);
        txtTuNgay.setColumns(10);

        Component horizontalStrut_7 = Box.createHorizontalStrut(24);
        b1.add(horizontalStrut_7);

        JLabel lblDenNgay = new JLabel("Đến ngày: ");
        lblDenNgay.setFont(new Font("Dialog", Font.BOLD, 20));
        b1.add(lblDenNgay);

        txtDenNgay = new JTextField();
        txtDenNgay.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtDenNgay.setColumns(10);
        b1.add(txtDenNgay);

        Component horizontalStrut_4 = Box.createHorizontalStrut(20);
        b1.add(horizontalStrut_4);

        Component verticalStrut_1_1 = Box.createVerticalStrut(20);
        b.add(verticalStrut_1_1);

        Box b2 = Box.createHorizontalBox();
        b.add(b2);

        Component horizontalStrut_3 = Box.createHorizontalStrut(20);
        b2.add(horizontalStrut_3);

        JLabel lblNhanVien = new JLabel("Nhân viên: ");
        lblNhanVien.setFont(new Font("Dialog", Font.BOLD, 20));
        b2.add(lblNhanVien);

        txtNhanVien = new JTextField();
        txtNhanVien.setFont(new Font("Dialog", Font.PLAIN, 20));
        txtNhanVien.setColumns(10);
        b2.add(txtNhanVien);

        Component horizontalStrut_5 = Box.createHorizontalStrut(20);
        b2.add(horizontalStrut_5);

        Component verticalStrut_1 = Box.createVerticalStrut(20);
        b.add(verticalStrut_1);

        Box boxBtn = Box.createHorizontalBox();
        b.add(boxBtn);

        btnXoaBoLoc.setFont(new Font("Dialog", Font.BOLD, 15));
        boxBtn.add(btnXoaBoLoc);

        btnXemTopKH.setFont(new Font("Dialog", Font.BOLD, 15));
        boxBtn.add(btnXemTopKH);

        btnXemTopSP.setFont(new Font("Dialog", Font.BOLD, 15));
        boxBtn.add(btnXemTopSP);

        Component verticalStrut_1_2 = Box.createVerticalStrut(20);
        b.add(verticalStrut_1_2);

        Box b3 = Box.createHorizontalBox();
        b.add(b3);

        lblSoLuongHoaDon.setFont(new Font("Dialog", Font.BOLD, 20));
        b3.add(lblSoLuongHoaDon);

        Component horizontalStrut_8 = Box.createHorizontalStrut(50);
        b3.add(horizontalStrut_8);

        lblTongTien.setFont(new Font("Dialog", Font.BOLD, 20));
        b3.add(lblTongTien);

        btnXoaBoLoc.addActionListener(this);
        btnXemTopKH.addActionListener(e -> new TopKhachHangDialog(this).setVisible(true));
        btnXemTopSP.addActionListener(e -> new TopSanPhamDialog(this).setVisible(true));

        txtTuNgay.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        String fromDateString = txtTuNgay.getText();
                        String toDateString = txtDenNgay.getText();
                        if (fromDateString.matches("\\d{2}/\\d{2}/\\d{4}")) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date fromDate = sdf.parse(fromDateString);
                            Date toDate = toDateString.matches("\\d{2}/\\d{2}/\\d{4}") ? sdf.parse(toDateString) : null;

                            dateFilter = new RowFilter<DefaultTableModel, Integer>() {
                                public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                                    try {
                                        String dateString = (String) entry.getValue(4);
                                        Date date = sdf.parse(dateString);
                                        if (toDate == null) {
                                            return date.compareTo(fromDate) >= 0;
                                        } else {
                                            return date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0;
                                        }
                                    } catch (Exception e3) {
                                        return false;
                                    }
                                }
                            };
                            rowSorter.setRowFilter(dateFilter);
                        } else if (!fromDateString.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Sai định dạng ngày, vui lòng nhập lại");
                        } else {
                            dateFilter = null;
                            rowSorter.setRowFilter(staffFilter);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });

        txtDenNgay.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        String fromDateString = txtTuNgay.getText();
                        String toDateString = txtDenNgay.getText();
                        if (toDateString.matches("\\d{2}/\\d{2}/\\d{4}")) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date fromDate = fromDateString.matches("\\d{2}/\\d{2}/\\d{4}") ? sdf.parse(fromDateString) : null;
                            Date toDate = sdf.parse(toDateString);

                            dateFilter = new RowFilter<DefaultTableModel, Integer>() {
                                public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                                    try {
                                        String dateString = (String) entry.getValue(4);
                                        Date date = sdf.parse(dateString);
                                        if (fromDate == null) {
                                            return date.compareTo(toDate) <= 0;
                                        } else {
                                            return date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0;
                                        }
                                    } catch (Exception e3) {
                                        return false;
                                    }
                                }
                            };
                            rowSorter.setRowFilter(dateFilter);
                        } else if (!toDateString.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Sai định dạng ngày, vui lòng nhập lại");
                        } else {
                            dateFilter = null;
                            rowSorter.setRowFilter(staffFilter);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });

        txtNhanVien.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String nhanVien = txtNhanVien.getText();
                    if (!nhanVien.isEmpty()) {
                        staffFilter = RowFilter.regexFilter(nhanVien, 2);
                        rowSorter.setRowFilter(staffFilter);
                    } else {
                        staffFilter = null;
                        rowSorter.setRowFilter(dateFilter);
                    }
                }
            }
        });

        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tbl.getSelectedRow() != -1) {
                    HoaDon_DAO hdDAO = new HoaDon_DAO();
                    int row = tbl.getSelectedRow();
                    HoaDon hd = null;
					try {
						hd = hdDAO.getHoaDonByID((String) tbl.getValueAt(row, 0));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    new HoaDonDialog(hd).setVisible(true);
                }
            }
        });

        loadDataToTable();
    }

    private void createTable() {
        String[] tblCols = {"Mã hóa đơn", "Mã khách hàng", "Mã nhân viên", "Tổng tiền", "Ngày tạo"};
        model = new DefaultTableModel(tblCols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tbl = new JTable(model);
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbl.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 20));
        tbl.setFont(new Font("Dialog", Font.PLAIN, 20));

        rowSorter = new TableRowSorter<>(model);
        tbl.setRowSorter(rowSorter);

        tbl.getColumnModel().getColumn(0).setPreferredWidth(70);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(120);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl.setRowHeight(30);

        Box boxTop = Box.createVerticalBox();
        add(boxTop);

        Box hBox = Box.createHorizontalBox();
        boxTop.add(hBox);

        JLabel lblTitle = new JLabel("Thống kê doanh thu");
        lblTitle.setForeground(Color.RED);
        hBox.add(lblTitle);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 25));

        Component verticalStrut = Box.createVerticalStrut(20);
        add(verticalStrut);

        JScrollPane scrollPane = new JScrollPane(tbl);
        add(scrollPane);
    }

    private void loadDataToTable() {
        try {
            model.setRowCount(0);
            HoaDon_DAO hdDAO = new HoaDon_DAO();
            List<HoaDon> list = hdDAO.getAllHoaDon();

            lblSoLuongHoaDon.setText("Số lượng hóa đơn: " + list.size());
            tongTien = 0;
            for (HoaDon h : list) {
                tongTien += h.getTongTien();
            }

            Locale lc = new Locale("vi", "VN");
            NumberFormat nf = NumberFormat.getCurrencyInstance(lc);
            lblTongTien.setText("Tổng tiền: " + nf.format(tongTien));

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            for (HoaDon h : list) {
                model.addRow(new Object[]{
                    h.getMaHD(), 
                    h.getMaKH(), 
                    h.getMaNV(), 
                    nf.format(h.getTongTien()), 
                    dtf.format((TemporalAccessor) h.getNgayLapHD())
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnXoaBoLoc)) {
            txtTuNgay.setText("");
            txtDenNgay.setText("");
            txtNhanVien.setText("");
            rowSorter.setRowFilter(null);
        }
    }
}