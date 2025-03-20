package admin.ui.nhanvien;


import javax.swing.*;
import java.awt.*;

public class AvatarPanel extends JPanel {
    public AvatarPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        // Tạo avatar (giả sử dùng ảnh từ file)
        ImageIcon avatarIcon = new ImageIcon("D:/ảnh/github.jpg");
        JLabel avatarLabel = new JLabel(avatarIcon);
        avatarLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Nếu ảnh không tải được, hiển thị nhãn mặc định
        if (avatarIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            avatarLabel.setText("Avatar không tải được");
            avatarLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        } else {
            // Điều chỉnh kích thước ảnh nếu cần
            Image img = avatarIcon.getImage();
            Image scaledImg = img.getScaledInstance(160, 160, Image.SCALE_SMOOTH); // Kích thước ảnh 80x80
            avatarLabel.setIcon(new ImageIcon(scaledImg));
        }

        add(avatarLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding
    }
}