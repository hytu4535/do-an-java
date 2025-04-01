package view;

import javax.swing.*;

import view.nguoidung.*;

import java.awt.*;
import java.util.ArrayList;

public class HeaderPanel extends JPanel {
    public HeaderPanel() {
        // create Header
        setPreferredSize(new Dimension(getWidth(), 50)); //height 50px
        setLayout(new BorderLayout()); // Sử dụng BorderLayout để phân chia
        
        setBackground(Color.decode("#6a1b9a"));
        setOpaque(true);
        
        
        // Thêm các tab điều hướng
        JPanel tabPanel = new JPanel();
        tabPanel.setOpaque(false);
        tabPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        ArrayList<String> tabs = new ArrayList<>();
        tabs.add("Thông tin");
        tabs.add("Số liệu");
        tabs.add("Kết nối");
        for (String tab : tabs) {
            JButton tabButton = new JButton(tab);
            tabButton.setBackground(Color.decode("#e1bee7")); 
            tabButton.setForeground(Color.BLACK);
            tabButton.setBorderPainted(false);
            tabButton.setFocusPainted(false);
            tabButton.setFont(new Font("Arial", Font.PLAIN, 14));
            tabButton.setMargin(new Insets(5, 10, 5, 10)); // Padding cho tab
            tabPanel.add(tabButton);

            // Hieu ung khi cham vao tab
            tabButton.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    tabButton.setBackground(Color.decode("#ce93d8"));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    tabButton.setBackground(Color.decode("#e1bee7")); // Trở lại màu ban đầu
                }
            });

        }
        add(tabPanel, BorderLayout.EAST); // chi dinh nam ben phai
    }

    // Class con để vẽ gradient background
    private class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            GradientPaint gradient = new GradientPaint(
                0, 0, Color.decode("#9c27b0"), // Màu tím đậm
                width, 0, Color.decode("#e1bee7")); // Màu tím nhạt
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height);
            super.paintComponent(g);
        }
    }
}
