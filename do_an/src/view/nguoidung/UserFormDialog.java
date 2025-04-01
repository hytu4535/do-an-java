package view.nguoidung;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class UserFormDialog extends JDialog {
    private HashMap<String, JTextField> fields;
    private JComboBox<String> statusComboBox;
    private JComboBox<String> roleComboBox; // Thêm JComboBox cho Vai trò
    private boolean confirmed;

    public UserFormDialog(Frame parent, String title, HashMap<String, String> initialData, boolean isEditMode) {
        super(parent, title, true); // Modal dialog
        setLayout(new BorderLayout());
        fields = new HashMap<>();
        confirmed = false;

        // Panel chứa các trường nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(240, 248, 255)); // Màu nền xanh nhạt

        // Các trường nhập liệu
        String[] labels = {"Tên tài khoản", "Họ tên", "Vai trò", "Trạng thái", "Ngày sinh", "Địa chỉ", "Điện thoại", "Email"};
        for (int i = 0; i < labels.length; i++) {
            JLabel jLabel = new JLabel(labels[i] + ":");
            jLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            inputPanel.add(jLabel);

            if (labels[i].equals("Trạng thái")) {
                statusComboBox = new JComboBox<>(new String[]{"Hoạt động", "Ngưng hoạt động"});
                statusComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
                if (initialData != null && initialData.containsKey(labels[i])) {
                    statusComboBox.setSelectedItem(initialData.get(labels[i]));
                }
                inputPanel.add(statusComboBox);
            } else if (labels[i].equals("Vai trò")) { // Thay JTextField thành JComboBox cho Vai trò
                String[] roles = {"Admin", "User", "Nhân viên nhập", "Nhân viên xuất", "Nhân viên kho", "Quản lý kho"};
                roleComboBox = new JComboBox<>(roles);
                roleComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
                if (initialData != null && initialData.containsKey(labels[i])) {
                    roleComboBox.setSelectedItem(initialData.get(labels[i]));
                }
                inputPanel.add(roleComboBox);
            } else {
                JTextField textField = new JTextField(20);
                textField.setFont(new Font("Arial", Font.PLAIN, 14));
                if (labels[i].equals("Tên tài khoản") && isEditMode) {
                    textField.setEditable(false); // Không cho sửa Tên tài khoản khi chỉnh sửa
                }
                if (initialData != null && initialData.containsKey(labels[i])) {
                    textField.setText(initialData.get(labels[i]));
                }
                fields.put(labels[i], textField);
                inputPanel.add(textField);
            }
        }

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(240, 248, 255));
        JButton saveButton = new JButton("Lưu");
        JButton cancelButton = new JButton("Hủy");

        // Định dạng nút
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(new Color(200, 220, 255)); // Màu xanh nhạt đậm hơn
        cancelButton.setBackground(new Color(200, 220, 255));
        saveButton.setFocusPainted(false);
        cancelButton.setFocusPainted(false);

        // Sự kiện nút "Lưu"
        saveButton.addActionListener(e -> {
            if (validateFields()) {
                confirmed = true;
                dispose();
            }
        });

        // Sự kiện nút "Hủy"
        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Cài đặt dialog
        setSize(450, 350);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    // Kiểm tra dữ liệu đầu vào
    private boolean validateFields() {
        // Kiểm tra Tên tài khoản và Họ tên không được để trống
        if (fields.get("Tên tài khoản").getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (fields.get("Họ tên").getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ tên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra định dạng Ngày sinh
        String namSinhStr = fields.get("Ngày sinh").getText().trim();
        if (!namSinhStr.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                sdf.parse(namSinhStr);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Ngày sinh phải có định dạng dd/MM/yyyy!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        // Kiểm tra định dạng Email
        String email = fields.get("Email").getText().trim();
        if (!email.isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra định dạng Điện thoại
        String dienThoai = fields.get("Điện thoại").getText().trim();
        if (!dienThoai.isEmpty() && !dienThoai.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(this, "Điện thoại phải là số và có 10-11 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public HashMap<String, String> getData() {
        HashMap<String, String> data = new HashMap<>();
        for (String label : fields.keySet()) {
            data.put(label, fields.get(label).getText().trim());
        }
        data.put("Trạng thái", (String) statusComboBox.getSelectedItem());
        data.put("Vai trò", (String) roleComboBox.getSelectedItem()); // Thêm giá trị của Vai trò
        return data;
    }
}