package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


import controller.NguoiDungController;
import controller.PhanQuyenController;
import controller.SupplierController;

import view.nguoidung.NguoiDungPanel;
import view.thongke.StatisticsPanel;
import view.nhaphang.NhaphangPanel;
import view.phanquyen.PhanQuyenPanel;
import view.phieunhap.PhieunhapPanel;
import view.phieuxuat.PhieuxuatPanel;
import view.sanpham.SanPhamPanel;
import view.xuathang.XuathangPanel;
import view.nhacungcap.SupplierPanel;



public class SidebarPanel extends JPanel {
    private JPanel mainPanel; // Khai báo mainPanel
    private JLabel imageLabel; // Label thêm hình ảnh

    // Khai báo các panel và controller
    private NguoiDungPanel nguoiDungPanel;
    private PhanQuyenPanel phanQuyenPanel;
    private NhaphangPanel nhaphangPanel;
    private PhieunhapPanel phieunhapPanel;
    private XuathangPanel xuathangPanel;
    private PhieuxuatPanel phieuxuatPanel;
    private StatisticsPanel statisticsPanel;
    private SanPhamPanel sanphamPanel;
    private SupplierPanel nhacungcapPanel;

    private NguoiDungController nguoiDungController;
    private PhanQuyenController phanQuyenController;
    private SupplierController supplierController;

    private JPanel currentPanel; // Lưu panel hiện tại để tránh làm mới không cần thiết

    // Constructor nhận mainPanel làm tham số
    public SidebarPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel; // Lưu tham chiếu đến mainPanel
        this.currentPanel = null; // Khởi tạo panel hiện tại là null

        // Khởi tạo các panel và controller một lần duy nhất
        initializePanelsAndControllers();

        // Đặt kích thước và layout
        setPreferredSize(new Dimension(200, getHeight()));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Panel cho phần header với hình ảnh
        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false); // Làm trong suốt để hiển thị gradient của sidebar
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
        changeImageButton.setBackground(new Color(220, 235, 255)); // Màu nhạt hơn để nổi bật trên gradient
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

        // Mảng các mục trong sidebar
        ArrayList<String> menuItems = new ArrayList<>();
        menuItems.add("Sản phẩm");
        menuItems.add("Nhà cung cấp");
        menuItems.add("Nhập hàng");
        menuItems.add("Phiếu nhập");
        menuItems.add("Xuất hàng");
        menuItems.add("Phiếu xuất");
        menuItems.add("Tài khoản");
        menuItems.add("Tồn kho");
        menuItems.add("Thống kê");
        menuItems.add("Phân quyền");
        menuItems.add("Đăng xuất");

        // Thêm các nút menu và đường kẻ phân cách
        for (int i = 0; i < menuItems.size(); i++) {
            String item = menuItems.get(i);
            JButton menuButton = new JButton(item);
            menuButton.setOpaque(false); // Làm trong suốt để hiển thị gradient
            menuButton.setBackground(new Color(240, 248, 255, 180)); // Màu nền trong suốt nhẹ
            menuButton.setForeground(new Color(64, 64, 64)); // Dark Gray cho chữ
            menuButton.setBorderPainted(false); // Bỏ viền nút
            menuButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa
            menuButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Chiều cao cố định
            menuButton.setFont(new Font("Arial", Font.PLAIN, 16));

            // Hiệu ứng khi chuột vào
            menuButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    menuButton.setOpaque(true);
                    menuButton.setBackground(new Color(220, 235, 255)); // Màu nhạt hơn khi hover
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    menuButton.setOpaque(false);
                    menuButton.setBackground(new Color(240, 248, 255, 180)); // Trở về màu trong suốt
                }
            });

            // Xử lý sự kiện bấm nút
            menuButton.addActionListener(e -> {
                JPanel newPanel = null;
                switch (item) {
                    case "Sản phẩm":
                        newPanel = sanphamPanel;
                        break;
                    case "Nhà cung cấp":
                        newPanel = nhacungcapPanel;
                        break;
                    case "Nhập hàng":
                        newPanel = nhaphangPanel;
                        break;
                    case "Phiếu nhập":
                        newPanel = phieunhapPanel;
                        break;
                    case "Xuất hàng":
                        newPanel = xuathangPanel;
                        break;
                    case "Phiếu xuất":
                        newPanel = phieuxuatPanel;
                        break;
                    case "Tài khoản":
                        newPanel = nguoiDungPanel;
                        break;
                    case "Tồn kho":
                        newPanel = createPlaceholderPanel("Tồn kho - Chưa triển khai");
                        break;
                    case "Thống kê":
                        newPanel = statisticsPanel;
                        break;
                    case "Phân quyền":
                        newPanel = phanQuyenPanel;
                        break;
                    case "Đăng xuất":
                        System.exit(0);
                        return;
                }

                // Chỉ làm mới nếu panel mới khác với panel hiện tại
                if (newPanel != currentPanel) {
                    mainPanel.removeAll();
                    mainPanel.add(newPanel);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                    currentPanel = newPanel; // Cập nhật panel hiện tại
                }
            });

            // Thêm nút vào sidebar
            add(menuButton);

            // Thêm khoảng cách và đường kẻ phân cách (trừ nút cuối cùng)
            if (i < menuItems.size() - 1) {
                add(Box.createRigidArea(new Dimension(0, 5))); // Khoảng cách nhỏ trước đường kẻ
                JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
                separator.setForeground(new Color(200, 200, 200)); // Màu xám nhạt cho đường kẻ
                separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1)); // Chiều cao đường kẻ
                add(separator);
                add(Box.createRigidArea(new Dimension(0, 5))); // Khoảng cách nhỏ sau đường kẻ
            }
        }
    }

    // Phương thức khởi tạo các panel và controller
    private void initializePanelsAndControllers() {
        // Khởi tạo các panel
        nguoiDungPanel = new NguoiDungPanel();
        phanQuyenPanel = new PhanQuyenPanel();
        nhaphangPanel = new NhaphangPanel();
        phieunhapPanel = new PhieunhapPanel();
        xuathangPanel = new XuathangPanel();
        phieuxuatPanel = new PhieuxuatPanel();
        statisticsPanel = new StatisticsPanel();
        sanphamPanel = new SanPhamPanel();
        nhacungcapPanel = new SupplierPanel();

        // Khởi tạo các controller
        nguoiDungController = new NguoiDungController(nguoiDungPanel);
        phanQuyenController = new PhanQuyenController(phanQuyenPanel);
        supplierController = new SupplierController(nhacungcapPanel);
        

        // Đăng ký NguoiDungController làm listener cho PhanQuyenController
        phanQuyenController.addRoleGroupChangeListener(nguoiDungController);
    }

    // Phương thức tạo panel placeholder cho các mục chưa triển khai
    private JPanel createPlaceholderPanel(String message) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(Color.GRAY);            
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    // Override paintComponent để vẽ gradient
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // Tạo gradient từ trên xuống dưới: từ xanh nhạt đến xanh đậm hơn
        GradientPaint gradient = new GradientPaint(
            0, 0, new Color(240, 248, 255), // Màu bắt đầu (xanh nhạt - AliceBlue)
            0, getHeight(), new Color(180, 220, 255) // Màu kết thúc (xanh đậm hơn)
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}