package view.nguoidung;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel; // Thêm import này
import java.awt.*;

public class DataTablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel; 

    public DataTablePanel() {
        setLayout(new BorderLayout());

        // Khởi tạo bảng
        String[] columnNames = {"STT", "Tên tài khoản", "Họ tên", "Vai trò", "Trạng thái", "Ngày sinh", "Địa chỉ", "Điện thoại"};
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
    }

    public void adjustColumnWidths() {
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // STT
        columnModel.getColumn(1).setPreferredWidth(100); // Tên tài khoản
        columnModel.getColumn(2).setPreferredWidth(150); // Họ tên
        columnModel.getColumn(3).setPreferredWidth(100); // Vai trò
        columnModel.getColumn(4).setPreferredWidth(100); // Trạng thái
        columnModel.getColumn(5).setPreferredWidth(100); // Ngày sinh
        columnModel.getColumn(6).setPreferredWidth(200); // Địa chỉ
        columnModel.getColumn(7).setPreferredWidth(100); // Điện thoại
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return table;
    }
}