package view.sanpham;

import controller.SanPhamController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

public class SanPhamPanel extends JPanel {
    private JTable productTable;
    private DefaultTableModel tableModel;
    private SanPhamController controller;
    private JTextField txtSearch;
    private JComboBox<String> searchComboBox;

    public SanPhamPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));

        // Panel chứa các nút chức năng
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(new Color(230, 230, 250));

        JButton btnThem = createStyledButton("Thêm", "/resources/images/icon_plus48.png");
        JButton btnXoa = createStyledButton("Xóa", "/resources/images/icon_delete.png");
        JButton btnSua = createStyledButton("Sửa", "/resources/images/edit_icon.png");
        JButton btnXem = createStyledButton("Xem chi tiết", "/resources/images/view_icon.png");
        JButton btnXuatExcel = createStyledButton("Xuất Excel", "/resources/images/export_icon.png");
        JButton btnNhapExcel = createStyledButton("Nhập Excel", "/resources/images/import_icon.png");

        // Khởi tạo bảng
        String[] columnNames = {"Mã SP", "Tên SP", "Số lượng", "Loại SP", "Giá", "Thương hiệu", "Chất liệu", "Độ dày", "Mô tả", "Xuất xứ", "Trạng thái"};
        tableModel = new DefaultTableModel(new Object[][]{}, columnNames);
        productTable = new JTable(tableModel);
        controller = new SanPhamController(this);

        // Sự kiện cho các nút
        btnThem.addActionListener(e -> {
            SanPhamThemForm themForm = new SanPhamThemForm((Frame) SwingUtilities.getWindowAncestor(this), controller);
            themForm.setVisible(true);
        });

        btnXem.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                Object[] rowData = new Object[tableModel.getColumnCount()];
                for (int i = 0; i < rowData.length; i++) {
                    rowData[i] = tableModel.getValueAt(selectedRow, i);
                }
                new SanPhamXemChiTietForm((Frame) SwingUtilities.getWindowAncestor(this), rowData).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để xem chi tiết!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnSua.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                Object[] rowData = new Object[tableModel.getColumnCount()];
                for (int i = 0; i < rowData.length; i++) {
                    rowData[i] = tableModel.getValueAt(selectedRow, i);
                }
                new SanPhamSuaForm((Frame) SwingUtilities.getWindowAncestor(this), rowData, controller).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnXoa.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                String maSP = tableModel.getValueAt(selectedRow, 0).toString();
                new SanPhamXoaForm((Frame) SwingUtilities.getWindowAncestor(this), controller, maSP).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });

        for (JButton btn : new JButton[]{btnThem, btnXoa, btnSua, btnXem, btnXuatExcel, btnNhapExcel}) {
            buttonPanel.add(btn);
        }

        // Panel chứa thanh tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanel.setBackground(new Color(245, 245, 245));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel lblSearch = new JLabel("Tìm kiếm:");
        lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 180)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JButton btnSearch = new JButton("Tìm kiếm");
        btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSearch.setBackground(new Color(100, 149, 237));
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFocusPainted(false);
        searchComboBox = new JComboBox<>(new String[]{"Mã SP", "Tên SP", "Tất cả"});
        searchComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        btnSearch.addActionListener(e -> {
            String tuKhoa = txtSearch.getText().trim();
            String tieuChi = (String) searchComboBox.getSelectedItem();
            controller.timKiemSanPham(tuKhoa, tieuChi);
        });

        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        searchPanel.add(searchComboBox);

        productTable.setRowHeight(30);
        productTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        productTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        productTable.getTableHeader().setBackground(new Color(135, 206, 250));
        productTable.getTableHeader().setForeground(Color.WHITE);
        productTable.setGridColor(new Color(200, 200, 200));
        productTable.setShowGrid(true);

        JScrollPane tableScrollPane = new JScrollPane(productTable);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(new Color(245, 245, 245));
        contentPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);

        add(buttonPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text, String iconPath) {
        JButton button = new JButton(text, new ImageIcon(getClass().getResource(iconPath)));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setIconTextGap(8);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setPreferredSize(new Dimension(100, 80));
        button.setBackground(new Color(240, 248, 255));
        button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        button.setFocusPainted(false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(173, 216, 230));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(240, 248, 255));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(new Color(135, 206, 250));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(new Color(173, 216, 230));
            }
        });

        return button;
    }

    public JTable getProductTable() {
        return productTable;
    }
}