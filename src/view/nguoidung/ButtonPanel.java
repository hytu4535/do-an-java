package view.nguoidung;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    public ButtonPanel() {
        setLayout(new GridLayout(1, 3, 5, 5)); // 1 hàng, 3 cột, khoảng cách 5px
        setBackground(Color.LIGHT_GRAY);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tạo các nút
        addButton = new JButton("Thêm");
        editButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");

        // Định dạng nút
        Font buttonFont = new Font("Arial", Font.BOLD, 12); // Giảm cỡ chữ để nút nhỏ hơn
        addButton.setFont(buttonFont);
        editButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);

        // Đặt kích thước cố định cho các nút
        Dimension buttonSize = new Dimension(80, 30); // Kích thước nhỏ hơn
        addButton.setPreferredSize(buttonSize);
        editButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);

        // Thêm các nút vào panel
        add(addButton);
        add(editButton);
        add(deleteButton);

        // Thêm hiệu ứng khi chạm
        for (JButton button : new JButton[]{addButton, editButton, deleteButton}) {
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.LIGHT_GRAY);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.WHITE);
                }
            });
        }
    }

    // Getter để controller truy cập các nút
    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }
}