package view.nguoidung;

import model.Account;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class InfoPanel extends JPanel {
    private JTextField userNameField;
    private JTextField fullNameField;
    private JTextField roleField;
    private JTextField statusField;
    private JTextField namSinhField;
    private JTextField diaChiField;
    private JTextField dienThoaiField;
    private JTextField emailField;
    private JPasswordField passwordField; // Thêm trường mật khẩu

    public InfoPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] labels = {"Tên tài khoản:", "Họ tên:", "Vai trò:", "Trạng thái:", "Ngày sinh:", "Địa chỉ:", "Điện thoại:", "Email:", "Mật khẩu:"};

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font valueFont = new Font("Arial", Font.PLAIN, 14);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 5, 2, 2);

        userNameField = new JTextField();
        fullNameField = new JTextField();
        roleField = new JTextField();
        statusField = new JTextField();
        namSinhField = new JTextField();
        diaChiField = new JTextField();
        dienThoaiField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField(); // Khởi tạo JPasswordField

        JTextField[] textFields = {userNameField, fullNameField, roleField, statusField, namSinhField, diaChiField, dienThoaiField, emailField, passwordField};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.08;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JLabel label = new JLabel(labels[i]);
            label.setFont(labelFont);
            label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            label.setBackground(Color.GREEN);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setOpaque(true);
            label.setPreferredSize(new Dimension(label.getPreferredSize().width, 25));
            add(label, gbc);

            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.weightx = 0.7;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JTextField textField = textFields[i];
            textField.setFont(valueFont);
            textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            textField.setPreferredSize(new Dimension(200, 25));
            textField.setEditable(false);
            add(textField, gbc);
        }
    }

    public void updateInfo(Account account) {
        if (account == null) {
            userNameField.setText("");
            fullNameField.setText("");
            roleField.setText("");
            statusField.setText("");
            namSinhField.setText("");
            diaChiField.setText("");
            dienThoaiField.setText("");
            emailField.setText("");
            passwordField.setText(""); // Xóa mật khẩu
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            userNameField.setText(account.getUserName());
            fullNameField.setText(account.getFullName());
            roleField.setText(account.getRole());
            statusField.setText(account.getStatus() == 1 ? "Hoạt động" : "Ngưng hoạt động");
            namSinhField.setText(account.getNamSinh() != null ? sdf.format(account.getNamSinh()) : "");
            diaChiField.setText(account.getDiaChi() != null ? account.getDiaChi() : "");
            dienThoaiField.setText(account.getDienThoai() != null ? account.getDienThoai() : "");
            emailField.setText(account.getEmail() != null ? account.getEmail() : "");
            passwordField.setText(account.getPassword() != null ? "********" : ""); // Không hiển thị mật khẩu thực
        }
    }

    public String getUserName() {
        return userNameField.getText();
    }

    public String getFullName() {
        return fullNameField.getText();
    }

    public String getRole() {
        return roleField.getText();
    }

    public String getStatus() {
        return statusField.getText();
    }

    public String getNamSinh() {
        return namSinhField.getText();
    }

    public String getDiaChi() {
        return diaChiField.getText();
    }

    public String getDienThoai() {
        return dienThoaiField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}