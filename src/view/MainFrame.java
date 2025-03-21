package view;

import javax.swing.*;

import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Ứng dụng Quản lý");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Tạo mainPanel để hiển thị nội dung
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        // Tạo SidebarPanel và truyền mainPanel
        SidebarPanel sidebar = new SidebarPanel(mainPanel);

        // Thêm sidebar và mainPanel vào JFrame
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}