package view.nguoidung;


import javax.swing.*;
import java.awt.*;

public class AvatarPanel extends JPanel {
    public AvatarPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        //C:\Users\DELL\Documents\SGU_CNTT_K23\NAM2_HK2\Ngon_ngu_lap_trinh_JAVA\do-an-java\do-an-java\do_an\src\resources\images
        ImageIcon avatarIcon = new ImageIcon("C:\\Users\\DELL\\Documents\\SGU_CNTT_K23\\NAM2_HK2\\Ngon_ngu_lap_trinh_JAVA\\do-an-java\\do-an-java\\do_an\\src\\resources\\images\\R.png");
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