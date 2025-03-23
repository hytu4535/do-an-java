package view;

import javax.swing.*;

import view.employee.EmployeePanel;
import view.thongke.StatisticsPanel;
import java.awt.*;
import java.util.ArrayList;

public class SidebarPanel extends JPanel {
    private JPanel mainPanel; // Khai báo mainPanel
    private JLabel imageLabel; //Label thêm hình ảnh

    // Constructor nhận mainPanel làm tham số
    public SidebarPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel; // Lưu tham chiếu đến mainPanel
        
        
     // Đặt nền màu xanh nhạt cực nhẹ
        setBackground(new Color(240, 248, 255)); // AliceBlue - một màu xanh nhạt rất nhẹ
        setPreferredSize(new Dimension(200, getHeight()));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        
     // Panel cho phần header với hình ảnh
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(240, 248, 255)); // Đồng bộ màu với sidebar
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120)); // Chiều cao cố định cho header
        
     // Thêm hình ảnh mặc định
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        ImageIcon defaultIcon = new ImageIcon(getClass().getResource("../resources/images/UIimage.png"));
        if (defaultIcon.getImage() != null) {
            Image scaledImage = defaultIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            imageLabel.setText("No Image");
        }
        
     // Nút để thay đổi hình ảnh
        JButton changeImageButton = new JButton("Change Image");
        changeImageButton.setBackground(new Color(240, 248, 255)); // Đồng bộ màu với sidebar
        changeImageButton.setForeground(new Color(64, 64, 64)); // Dark Gray cho chữ
        changeImageButton.setFont(new Font("Arial", Font.PLAIN, 12));
        changeImageButton.setMaximumSize(new Dimension(100, 25));
        changeImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
     // Xử lý sự kiện thay đổi hình ảnh
        changeImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String imagePath = fileChooser.getSelectedFile().getPath();
                ImageIcon newIcon = new ImageIcon(imagePath);
                Image scaledImage = newIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
                imageLabel.setText(""); // Xóa text nếu có
                revalidate();
                repaint();
            }
        });
        
     // Thêm các thành phần vào headerPanel
        headerPanel.add(imageLabel, BorderLayout.CENTER);
        headerPanel.add(changeImageButton, BorderLayout.SOUTH);

        // Thêm headerPanel vào đầu sidebar
        add(headerPanel);
        add(Box.createRigidArea(new Dimension(0, 20))); // Khoảng cách giữa header và menu

        // create sidebar
        headerPanel.setBackground(new Color(240, 248, 255)); // Đồng bộ màu với sidebar
        setPreferredSize(new Dimension(200, getHeight())); // width 200px
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // sap xep doc

        // mảng các mục trong sidebar
        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("Sản phẩm");
        menuItems.add("Nhà cung cấp");
        menuItems.add("Nhập hàng");
        menuItems.add("Phiếu nhập");
        menuItems.add("Xuất hàng");
        menuItems.add("Phiếu xuất");
        menuItems.add("Người dùng");
        menuItems.add("Nhân viên");
        menuItems.add("Tồn kho");
        menuItems.add("Thống kê");
        menuItems.add("Đăng xuất");

        for (String item : menuItems) {
            JButton menuButton = new JButton(item);
            menuButton.setBackground(new Color(240, 248, 255)); // Đồng bộ màu nền
            menuButton.setForeground(new Color(64, 64, 64)); // Dark Gray cho chữ
            menuButton.setBorderPainted(false); // Bỏ viền nút
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa
            menuButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Chiều cao cố định
            menuButton.setFont(new Font("Arial", Font.PLAIN, 16));

            // Hiệu ứng khi chuột vào
            menuButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                	menuButton.setBackground(new Color(220, 235, 255)); // Màu nhạt hơn khi hover
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                	menuButton.setBackground(new Color(240, 248, 255)); // Trở về màu nền gốc
                }
            });

            // Xử lý sự kiện bấm nút
            menuButton.addActionListener(e -> {
                mainPanel.removeAll(); // Xóa nội dung cũ
                switch (item) {
                    case "Sản phẩm":
                        mainPanel.add(new JLabel("Sản phẩm - Chưa triển khai"));
                        break;
                    case "Nhà cung cấp":
                        mainPanel.add(new JLabel("Nhà cung cấp - Chưa triển khai"));
                        break;
                    case "Nhập hàng":
                        mainPanel.add(new JLabel("Nhập hàng - Chưa triển khai"));
                        break;
                    case "Phiếu nhập":
                        mainPanel.add(new JLabel("Phiếu nhập - Chưa triển khai"));
                        break;
                    case "Xuất hàng":
                        mainPanel.add(new JLabel("Xuất hàng - Chưa triển khai"));
                        break;
                    case "Phiếu xuất":
                        mainPanel.add(new JLabel("Phiếu xuất - Chưa triển khai"));
                        break;
                    case "Người dùng":
                        mainPanel.add(new JLabel("Người dùng - Chưa triển khai"));
                        break;
                    case "Nhân viên":
                        mainPanel.add(new EmployeePanel()); // Hiển thị EmployeePanel từ admin.ui.nhanvien
                        break;
                    case "Tồn kho":
                        mainPanel.add(new JLabel("Tồn kho - Chưa triển khai"));
                        break;
                    case "Thống kê":
                        mainPanel.add(new StatisticsPanel());
                        break;
                    case "Cài đặt":
                        mainPanel.add(new JLabel("Cài đặt - Chưa triển khai"));
                        break;
                    case "Đăng xuất":
                        System.exit(0);
                        break;
                }
                mainPanel.revalidate();
                mainPanel.repaint();
            });

            // Thêm khoảng cách giữa các nút và thêm nút
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(menuButton);
        }
    }
}