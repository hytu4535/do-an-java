package view.nguoidung;

import controller.NguoiDungController;
import javax.swing.*;
import java.awt.*;

public class NguoiDungPanel extends JPanel {
    private NguoiDungController controller;
    private AvatarPanel avatarPanel;
    private InfoPanel infoPanel;
    private DataTablePanel dataTablePanel;
    private ButtonPanel buttonPanel;

    public NguoiDungPanel() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Tạo các panel con trước
        avatarPanel = new AvatarPanel();
        infoPanel = new InfoPanel();
        dataTablePanel = new DataTablePanel();
        buttonPanel = new ButtonPanel(dataTablePanel.getTable()); // Truyền JTable vào ButtonPanel

        // Sắp xếp layout
        // Phần trên: Avatar, Info và Button
        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 5, 0, 5); // Khoảng cách giữa các panel

        // AvatarPanel (thu nhỏ chiều rộng)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0; // Không kéo giãn
        gbc.weighty = 1.0;
        avatarPanel.setPreferredSize(new Dimension(300, 200)); // Thu nhỏ chiều rộng thành 100px
        topPanel.add(avatarPanel, gbc);

        // InfoPanel (chiếm phần lớn không gian)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Kéo giãn để chiếm phần còn lại
        gbc.weighty = 1.0;
        topPanel.add(infoPanel, gbc);

        // ButtonPanel (vừa đủ cho các nút)
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0; // Không kéo giãn
        gbc.weighty = 1.0;
        buttonPanel.setPreferredSize(new Dimension(300, 200)); // Chiều rộng vừa đủ cho 3 nút
        topPanel.add(buttonPanel, gbc);

        add(topPanel, BorderLayout.NORTH);

        // Phần giữa: Bảng dữ liệu
        add(dataTablePanel, BorderLayout.CENTER);

        // Khởi tạo controller sau khi các panel con đã được tạo
        controller = new NguoiDungController(this);

        // Load dữ liệu ban đầu
        controller.loadData();
    }

    // Getter để controller truy cập các panel con
    public DataTablePanel getDataTablePanel() {
        return dataTablePanel;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public AvatarPanel getAvatarPanel() {
        return avatarPanel;
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }
}