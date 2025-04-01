package view;

import javax.swing.*;
import view.nguoidung.*;
import java.awt.*;

public class MainContentPanel extends JPanel {
    public MainContentPanel() {
        setLayout(new BorderLayout());

        // Phần trên (40%)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(getWidth(), 240)); // 40% của 600px = 240px
        
        AvatarPanel avatarPanel = new AvatarPanel();
        topPanel.add(avatarPanel, BorderLayout.WEST); // Avatar bên trái

        InfoPanel infoPanel = new InfoPanel();
        topPanel.add(infoPanel, BorderLayout.CENTER); // Thông tin bên phải
        
        // Khởi tạo DataTablePanel trước để lấy JTable
        DataTablePanel tablePanel = new DataTablePanel();
        ButtonPanel buttonPanel = new ButtonPanel(tablePanel.getTable()); // Truyền JTable vào ButtonPanel
        topPanel.add(buttonPanel, BorderLayout.EAST); // Thêm ButtonPanel sát bên phải

        add(topPanel, BorderLayout.NORTH);
    
        // Phần dưới (60%)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(getWidth(), 360)); // 60% của 600px = 360px        
        bottomPanel.add(tablePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.CENTER); // Sửa "customPanel" thành "bottomPanel"
    }
}