package admin.ui.nhanvien;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel{
	public ButtonPanel() {
        setLayout(new GridLayout(3, 1, 5, 5)); // 3 hàng, 1 cột, khoảng cách 5px
        setBackground(Color.LIGHT_GRAY); // Màu nền nhẹ cho dễ nhận diện
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding

        // Tạo các nút
        JButton addButton = new JButton("Thêm");
        JButton editButton = new JButton("Sửa");
        JButton deleteButton = new JButton("Xóa");

        // Định dạng nút
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        addButton.setFont(buttonFont);
        editButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);

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
}
