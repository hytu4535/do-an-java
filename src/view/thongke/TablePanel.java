package view.thongke;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class TablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane tableScrollPane;

    // Cấu trúc cột
    private final String[] productColumns = {"STT", "Mã vật phẩm", "Tên vật phẩm", "Số lượng nhập", "Số lượng xuất"};
    private final String[] ticketColumns = {"STT", "Mã phiếu", "Loại phiếu", "Ngày tạo", "Tổng tiền"};
    private final String[] userColumns = {"STT", "Tên tài khoản", "Họ tên", "Vai trò", "Trạng thái", "Ngày sinh", "Địa chỉ", "Điện thoại"}; // Đổi "Năm sinh" thành "Ngày sinh"

    public TablePanel() {
        setLayout(new BorderLayout());

        // Khởi tạo bảng
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.setGridColor(new Color(200, 200, 200));

        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 10, 10, 10)
        ));

        add(tableScrollPane, BorderLayout.CENTER);
    }

    // Phương thức điều chỉnh độ rộng cột
    public void adjustColumnWidths(String tableType) {
        TableColumnModel columnModel = table.getColumnModel();

        if (tableType.equals("Sản phẩm")) {
            columnModel.getColumn(0).setPreferredWidth(50);  // STT
            columnModel.getColumn(1).setPreferredWidth(80);  // Mã vật phẩm
            columnModel.getColumn(3).setPreferredWidth(100); // Số lượng nhập
            columnModel.getColumn(4).setPreferredWidth(100); // Số lượng xuất
            columnModel.getColumn(2).setPreferredWidth(400); // Tên máy
        } else if (tableType.equals("Phiếu")) {
            columnModel.getColumn(0).setPreferredWidth(50);  // STT
            columnModel.getColumn(1).setPreferredWidth(80);  // Mã phiếu
            columnModel.getColumn(2).setPreferredWidth(100); // Loại phiếu
            columnModel.getColumn(3).setPreferredWidth(100); // Ngày tạo
            columnModel.getColumn(4).setPreferredWidth(150); // Tổng tiền
        } else if (tableType.equals("Tài khoản")) {
            columnModel.getColumn(0).setPreferredWidth(50);  // STT
            columnModel.getColumn(1).setPreferredWidth(100); // Tên tài khoản
            columnModel.getColumn(2).setPreferredWidth(150); // Họ tên
            columnModel.getColumn(3).setPreferredWidth(100); // Vai trò
            columnModel.getColumn(4).setPreferredWidth(100); // Trạng thái
            columnModel.getColumn(5).setPreferredWidth(100); // Ngày sinh (tăng độ rộng để hiển thị ngày/tháng/năm)
            columnModel.getColumn(6).setPreferredWidth(200); // Địa chỉ
            columnModel.getColumn(7).setPreferredWidth(100); // Điện thoại
        }
    }

    // Getter để các class khác truy cập
    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public String[] getProductColumns() {
        return productColumns;
    }

    public String[] getTicketColumns() {
        return ticketColumns;
    }

    public String[] getUserColumns() {
        return userColumns;
    }
}