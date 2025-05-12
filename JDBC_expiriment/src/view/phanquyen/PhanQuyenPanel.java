package view.phanquyen;

import controller.PhanQuyenController;
import javax.swing.*;
import java.awt.*;

public class PhanQuyenPanel extends JPanel {
    private PhanQuyenController controller;
    private RoleInfoPanel infoPanel;
    private RoleDataTablePanel dataTablePanel;
    private RoleButtonPanel buttonPanel;

    public PhanQuyenPanel() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Tạo các panel con trước
        infoPanel = new RoleInfoPanel();
        dataTablePanel = new RoleDataTablePanel();
        buttonPanel = new RoleButtonPanel(dataTablePanel.getTable()); // Truyền JTable vào ButtonPanel

        // Sắp xếp layout
        // Phần trên: Info và Button
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 5, 0, 5); // Khoảng cách giữa các panel

        // InfoPanel (chiếm phần lớn không gian)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Kéo giãn để chiếm phần còn lại
        gbc.weighty = 1.0;
        topPanel.add(infoPanel, gbc);

        // ButtonPanel (vừa đủ cho các nút)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0; // Không kéo giãn
        gbc.weighty = 1.0;
        buttonPanel.setPreferredSize(new Dimension(500, 100)); // Tăng chiều rộng để hiển thị đầy đủ
        topPanel.add(buttonPanel, gbc);

        add(topPanel, BorderLayout.NORTH);

        // Phần giữa: Bảng dữ liệu
        add(dataTablePanel, BorderLayout.CENTER);

        // Khởi tạo controller sau khi các panel con đã được tạo
        controller = new PhanQuyenController(this);

        // Load dữ liệu ban đầu
        controller.loadData();
    }

    // Getter để controller truy cập các panel con
    public RoleDataTablePanel getDataTablePanel() {
        return dataTablePanel;
    }

    public RoleInfoPanel getInfoPanel() {
        return infoPanel;
    }

    public RoleButtonPanel getButtonPanel() {
        return buttonPanel;
    }
}