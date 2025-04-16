package view.phanquyen;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class RoleDataTablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public RoleDataTablePanel() {
        setLayout(new BorderLayout());

        // Khởi tạo bảng
        String[] columnNames = {"STT", "Mã nhóm quyền", "Tên nhóm quyền", "Mô tả"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.setGridColor(new Color(200, 200, 200));

        // Thêm bảng vào JScrollPane để có thanh cuộn
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 10, 10)
        ));
        add(scrollPane, BorderLayout.CENTER);

        // Gọi phương thức để tùy chỉnh bảng
        adjustColumnWidths();
    }

    public void adjustColumnWidths() {
        TableColumnModel columnModel = table.getColumnModel();
        // Đặt width cho các cột
        columnModel.getColumn(0).setPreferredWidth(40);  // STT: thu nhỏ thành 40px
        columnModel.getColumn(1).setPreferredWidth(80);  // Mã nhóm quyền: thu nhỏ thành 80px
        columnModel.getColumn(2).setPreferredWidth(120); // Tên nhóm quyền: thu nhỏ thành 120px
        columnModel.getColumn(3).setPreferredWidth(500); // Mô tả: tăng thành 500px

        // Căn giữa nội dung cho các cột STT, Mã nhóm quyền, Tên nhóm quyền
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Áp dụng renderer cho các cột cần căn giữa
        columnModel.getColumn(0).setCellRenderer(centerRenderer); // STT
        columnModel.getColumn(1).setCellRenderer(centerRenderer); // Mã nhóm quyền
        columnModel.getColumn(2).setCellRenderer(centerRenderer); // Tên nhóm quyền

        // Cột Mô tả giữ mặc định (căn trái)
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }
}