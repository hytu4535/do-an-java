package view.nguoidung;

import dao.RoleGroupDAO;
import model.RoleGroup;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class UserFormDialog extends JDialog {
    private HashMap<String, JTextField> fields;
    private JComboBox<String> statusComboBox;
    private JComboBox<String> roleComboBox;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private boolean confirmed;
    private static HashMap<String, String> roleMapping;
    private static boolean rolesLoaded = false;
    private RoleGroupDAO roleGroupDAO;
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d!@#$%^&*]{6,20}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final String PASSWORD_PLACEHOLDER = "********"; // Giá trị giả cho mật khẩu cũ

    public UserFormDialog(Frame parent, String title, HashMap<String, String> initialData, boolean isEditMode) {
        super(parent, title, true);
        setLayout(new BorderLayout());
        fields = new HashMap<>();
        confirmed = false;
        roleGroupDAO = new RoleGroupDAO();

        // Sửa lỗi: Kiểm tra roleMapping thay vì "role bella"
        if (roleMapping == null) {
            roleMapping = new HashMap<>();
        }

        if (!rolesLoaded) {
            loadRolesFromDatabase();
            rolesLoaded = true;
        }

        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(new Color(240, 248, 255));

        String[] labels = {"Tên tài khoản", "Họ tên", "Vai trò", "Trạng thái", "Ngày sinh", "Địa chỉ", "Điện thoại", "Email", "Mật khẩu", "Nhập lại mật khẩu"};
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
            } else if (labels[i].equals("Vai trò")) {
                List<String> roleNames = new ArrayList<>(roleMapping.keySet());
                roleComboBox = new JComboBox<>(roleNames.toArray(new String[0]));
                roleComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
                if (initialData != null && initialData.containsKey(labels[i])) {
                    String roleGroupId = initialData.get(labels[i]);
                    String roleGroupName = getRoleNameById(roleGroupId);
                    if (roleGroupName != null) {
                        roleComboBox.setSelectedItem(roleGroupName);
                    }
                }
                inputPanel.add(roleComboBox);
            } else if (labels[i].equals("Mật khẩu")) {
                passwordField = new JPasswordField(20);
                passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
                if (isEditMode) {
                    passwordField.setText(PASSWORD_PLACEHOLDER); // Điền sẵn placeholder cho chế độ sửa
                } else if (initialData != null && initialData.containsKey(labels[i])) {
                    passwordField.setText(initialData.get(labels[i]));
                }
                inputPanel.add(passwordField);
            } else if (labels[i].equals("Nhập lại mật khẩu")) {
                confirmPasswordField = new JPasswordField(20);
                confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
                if (isEditMode) {
                    confirmPasswordField.setText(PASSWORD_PLACEHOLDER); // Điền sẵn placeholder cho chế độ sửa
                } else if (initialData != null && initialData.containsKey(labels[i])) {
                    confirmPasswordField.setText(initialData.get(labels[i]));
                }
                inputPanel.add(confirmPasswordField);
            } else {
                JTextField textField = new JTextField(20);
                textField.setFont(new Font("Arial", Font.PLAIN, 14));
                if (labels[i].equals("Tên tài khoản") && isEditMode) {
                    textField.setEditable(false);
                }
                if (initialData != null && initialData.containsKey(labels[i])) {
                    textField.setText(initialData.get(labels[i]));
                }
                fields.put(labels[i], textField);
                inputPanel.add(textField);
            }
        }

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(240, 248, 255));
        JButton saveButton = new JButton("Lưu");
        JButton cancelButton = new JButton("Hủy");

        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(new Color(200, 220, 255));
        cancelButton.setBackground(new Color(200, 220, 255));
        saveButton.setFocusPainted(false);
        cancelButton.setFocusPainted(false);

        saveButton.addActionListener(e -> {
            if (validateFields(isEditMode)) {
                confirmed = true;
                dispose();
            }
        });

        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(450, 400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void loadRolesFromDatabase() {
        List<RoleGroup> roleGroups = roleGroupDAO.getAllRoleGroups();
        for (RoleGroup roleGroup : roleGroups) {
            roleMapping.put(roleGroup.getRoleGroupName(), roleGroup.getRoleGroupId());
        }
    }

    private String getRoleNameById(String roleGroupId) {
        for (String roleName : roleMapping.keySet()) {
            if (roleMapping.get(roleName).equals(roleGroupId)) {
                return roleName;
            }
        }
        return null;
    }

    private boolean validateFields(boolean isEditMode) {
        // Kiểm tra tên tài khoản
        if (fields.get("Tên tài khoản").getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Kiểm tra họ tên
        if (fields.get("Họ tên").getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ tên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra mật khẩu
        String password = new String(passwordField.getPassword()).trim();
        String confirmPassword = new String(confirmPasswordField.getPassword()).trim();

        if (!isEditMode) {
            // Khi thêm tài khoản mới, mật khẩu và nhập lại mật khẩu là bắt buộc
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống khi thêm tài khoản mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nhập lại mật khẩu không được để trống khi thêm tài khoản mới!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            // Khi sửathis, nếu cả hai trường mật khẩu đều là placeholder, bỏ qua kiểm tra
            if (password.equals(PASSWORD_PLACEHOLDER) && confirmPassword.equals(PASSWORD_PLACEHOLDER)) {
                return true; // Giữ nguyên mật khẩu cũ
            }
        }

        // Kiểm tra mật khẩu và nhập lại mật khẩu có khớp nhau không
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu và nhập lại mật khẩu không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra regex cho mật khẩu (nếu có nhập mật khẩu và không phải placeholder)
        if (!password.isEmpty() && !password.equals(PASSWORD_PLACEHOLDER)) {
            if (!PASSWORD_PATTERN.matcher(password).matches()) {
                JOptionPane.showMessageDialog(this, "Mật khẩu phải có độ dài 6-20 ký tự, chứa ít nhất một chữ hoa, một chữ thường, và một số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        // Kiểm tra ngày sinh
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

        // Kiểm tra email
        String email = fields.get("Email").getText().trim();
        if (!email.isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra điện thoại
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
        String selectedRoleName = (String) roleComboBox.getSelectedItem();
        data.put("Vai trò", roleMapping.get(selectedRoleName));
        String password = new String(passwordField.getPassword()).trim();
        // Nếu mật khẩu là placeholder, trả về chuỗi rỗng để giữ nguyên mật khẩu cũ
        data.put("Mật khẩu", password.equals(PASSWORD_PLACEHOLDER) ? "" : password);
        return data;
    }

    public static void refreshRoles() {
        rolesLoaded = false;
    }
}