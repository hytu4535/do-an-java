package view.employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DataTablePanel extends JPanel {
    public DataTablePanel() {
        setLayout(new BorderLayout());

        // Khởi tạo bảng với model rỗng
        String[] columnNames = {"Mã NV", "Họ tên", "Ngày sinh", "Email"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // Khởi tạo model rỗng
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        // Thêm bảng vào JScrollPane để có thanh cuộn
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Kết nối đến cơ sở dữ liệu và lấy dữ liệu
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Kết nối đến cơ sở dữ liệu MySQL
            String url = "jdbc:mysql://localhost:3306/quanlynhanvien"; // Thay đổi tên cơ sở dữ liệu nếu cần
            String user = "root"; // Thay đổi username của bạn
            String password = "thinh2014"; // Thay đổi password của bạn
            conn = DriverManager.getConnection(url, user, password);

            // Truy vấn dữ liệu
            stmt = conn.createStatement();
            String sql = "SELECT * FROM nhanvien";
            rs = stmt.executeQuery(sql);

            // Xóa dữ liệu cũ trong model (nếu có)
            model.setRowCount(0);

            // Đọc dữ liệu từ ResultSet và thêm vào model
            while (rs.next()) {
                String maNV = rs.getString("maNV");
                String hoTen = rs.getString("hoTen");
                String ngaySinh = rs.getString("ngaySinh");
                String email = rs.getString("email");
                model.addRow(new Object[]{maNV, hoTen, ngaySinh, email});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Đóng các tài nguyên
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}