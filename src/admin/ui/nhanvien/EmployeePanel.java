package admin.ui.nhanvien;

import javax.swing.*;
import java.awt.*;

public class EmployeePanel extends JPanel {
    public EmployeePanel() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Tạo các panel con
        AvatarPanel avatarPanel = new AvatarPanel();
        InfoPanel infoPanel = new InfoPanel();
        DataTablePanel dataTablePanel = new DataTablePanel();
        ButtonPanel buttonPanel = new ButtonPanel();

        // Sắp xếp layout
        // Phần trên: Avatar và Info
        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.add(avatarPanel);
        topPanel.add(infoPanel);
        add(topPanel, BorderLayout.NORTH);

        // Phần giữa: Bảng dữ liệu
        add(dataTablePanel, BorderLayout.CENTER);

        // Di chuyển ButtonPanel lên góc trên bên phải (góc 1 giờ)
        // Sử dụng một panel đệm với BorderLayout và FlowLayout
        JPanel eastPanel = new JPanel(new BorderLayout());
        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Căn phải
        buttonWrapper.add(buttonPanel);
        eastPanel.add(buttonWrapper, BorderLayout.NORTH); // Đặt lên trên cùng bên phải
        add(eastPanel, BorderLayout.EAST);

        // Đảm bảo kích thước hợp lý cho eastPanel
        eastPanel.setPreferredSize(new Dimension(150, getHeight())); // Điều chỉnh độ rộng
    }
}