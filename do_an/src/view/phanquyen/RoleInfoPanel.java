package view.phanquyen;

import model.RoleGroup;
import javax.swing.*;
import java.awt.*;

public class RoleInfoPanel extends JPanel {
    private JTextField roleGroupIdField;
    private JTextField roleGroupNameField;
    private JTextField descriptionField;

    public RoleInfoPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Mảng chứa các nhãn tiêu đề
        String[] labels = {"Mã nhóm quyền:", "Tên nhóm quyền:", "Mô tả:"};

        // Đặt font cho nhãn và textField
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font valueFont = new Font("Arial", Font.PLAIN, 14);

        // GridBagConstraints để kiểm soát vị trí và kích thước
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 5, 2, 2);

        // Khởi tạo các text field
        roleGroupIdField = new JTextField();
        roleGroupNameField = new JTextField();
        descriptionField = new JTextField();

        JTextField[] textFields = {roleGroupIdField, roleGroupNameField, descriptionField};

        // Thêm các nhãn và text field vào panel
        for (int i = 0; i < labels.length; i++) {
            // Cột bên trái: JLabel
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

            // Cột bên phải: JTextField
            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.weightx = 0.7;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JTextField textField = textFields[i];
            textField.setFont(valueFont);
            textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            textField.setPreferredSize(new Dimension(200, 25));
            textField.setEditable(false); // Không cho phép chỉnh sửa trực tiếp
            add(textField, gbc);
        }
    }

    public void updateInfo(RoleGroup roleGroup) {
        if (roleGroup == null) {
            roleGroupIdField.setText("");
            roleGroupNameField.setText("");
            descriptionField.setText("");
        } else {
            roleGroupIdField.setText(roleGroup.getRoleGroupId());
            roleGroupNameField.setText(roleGroup.getRoleGroupName());
            descriptionField.setText(roleGroup.getDescription() != null ? roleGroup.getDescription() : "");
        }
    }

    // Getter để lấy dữ liệu từ các text field (dùng khi thêm/sửa)
    public String getRoleGroupId() {
        return roleGroupIdField.getText();
    }

    public String getRoleGroupName() {
        return roleGroupNameField.getText();
    }

    public String getDescription() {
        return descriptionField.getText();
    }
}