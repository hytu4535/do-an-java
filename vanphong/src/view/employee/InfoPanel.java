package view.employee;





import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class InfoPanel extends JPanel {
    public InfoPanel() {
        setLayout(new GridBagLayout()); // này là GridBagLayout (cho phép điều chỉnh kích cỡ), còn GridLayout thì chỉnh 7 hàng, 1 cột, khoảng cách ngang/dọc là 2px
        setBackground(Color.WHITE); // Màu nền trắng
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding

        //Mảng chứa các nhãn tiêu đề
        String[] labels = {"Mã NV:", "Họ tên:", "Năm Sinh:", "Địa chỉ:", "Điện thoại:", "Hình ảnh", "Email:"};


        // Đặt font cho nhãn và textField
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font valueFont = new Font("Arial", Font.PLAIN, 14);

        //GridBagConstraints gbc để kiểm soát vị trí và kích thước
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,5,2,2); //padding top, left, bottom, right
        
        //Thêm các nhãn và text field vào panel
        for (int i = 0; i < labels.length; i++) {
            // Cột bên trái: JLabel
            gbc.gridx = 0; // Cột 0
            gbc.gridy = i; // Hàng i
            gbc.weightx = 0.08; // Cột trái chiếm 30% chiều rộng
            gbc.anchor = GridBagConstraints.WEST; // Căn trái
            gbc.fill = GridBagConstraints.HORIZONTAL; // Kéo giãn theo chiều ngang
            JLabel label = new JLabel(labels[i]);
            label.setFont(labelFont);
            label.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Thêm viền
            label.setBackground(Color.GREEN);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setOpaque(true);
            label.setPreferredSize(new Dimension(label.getPreferredSize().width, 25)); // Chiều cao tăng lên 30px
            add(label, gbc);

            // Cột bên phải: JTextField
            gbc.gridx = 1; // Cột 1
            gbc.gridy = i; // Hàng i
            gbc.weightx = 0.7; // Cột phải chiếm 70% chiều rộng
            gbc.fill = GridBagConstraints.HORIZONTAL; // Kéo giãn theo chiều ngang
            JTextField textField = new JTextField();
            textField.setFont(valueFont);
            textField.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Thêm viền cho text field
            textField.setPreferredSize(new Dimension(200, 25)); // Đặt kích thước cố định cho text field
            add(textField, gbc);
        }
    }
}