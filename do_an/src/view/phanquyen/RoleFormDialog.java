package view.phanquyen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoleFormDialog extends JDialog {
    private HashMap<String, JTextField> fields;
    private boolean confirmed;
    private List<List<JCheckBox>> functionCheckBoxes; // Danh sách checkbox cho từng chức năng và quyền (Xem, Tạo mới, Cập nhật, Xóa)
    private List<String> functionNames; // Danh sách tên các chức năng

    public RoleFormDialog(Frame parent, String title, HashMap<String, String> initialData, boolean isEditMode) {
        super(parent, title, true); // Modal dialog
        setLayout(new BorderLayout());
        fields = new HashMap<>();
        confirmed = false;
        functionCheckBoxes = new ArrayList<>();
        functionNames = new ArrayList<>();

        // Panel chính chứa các trường nhập liệu và danh sách chức năng
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(240, 248, 255)); // Màu nền xanh nhạt

        // Panel chứa các trường nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBackground(new Color(240, 248, 255));

        // Các trường nhập liệu
        String[] labels = {"Mã nhóm quyền", "Tên nhóm quyền", "Mô tả"};
        for (int i = 0; i < labels.length; i++) {
            JLabel jLabel = new JLabel(labels[i] + ":");
            jLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            inputPanel.add(jLabel);

            JTextField textField = new JTextField(20);
            textField.setFont(new Font("Arial", Font.PLAIN, 14));
            if (labels[i].equals("Mã nhóm quyền") && isEditMode) {
                textField.setEditable(false); // Không cho sửa Mã nhóm quyền khi chỉnh sửa
            }
            if (initialData != null && initialData.containsKey(labels[i])) {
                textField.setText(initialData.get(labels[i]));
            }
            fields.put(labels[i], textField);
            inputPanel.add(textField);
        }

        // Panel chứa danh sách chức năng với checkbox
        JPanel functionPanel = new JPanel(new BorderLayout());
        functionPanel.setBackground(new Color(240, 248, 255));
        functionPanel.setBorder(BorderFactory.createTitledBorder("Danh mục chức năng"));

        // Tạo bảng với 5 cột: Tên chức năng, Xem, Tạo mới, Cập nhật, Xóa
        String[] columnNames = {"Danh mục chức năng", "Xem", "Tạo mới", "Cập nhật", "Xóa"};
        functionNames.add("Quản lý khách hàng");
        functionNames.add("Quản lý khu vực kho");
        functionNames.add("Quản lý nhà cung cấp");
        functionNames.add("Quản lý nhân viên");
        functionNames.add("Quản lý nhập hàng");
        functionNames.add("Quản lý nhóm quyền");
        functionNames.add("Quản lý sản phẩm");
        functionNames.add("Quản lý tài khoản");
        functionNames.add("Quản lý thống kê");
        functionNames.add("Quản lý thuộc tính");
        functionNames.add("Quản lý xuất hàng");

        Object[][] data = new Object[functionNames.size()][5];
        for (int i = 0; i < functionNames.size(); i++) {
            data[i][0] = functionNames.get(i); // Cột đầu tiên là tên chức năng
            List<JCheckBox> checkBoxes = new ArrayList<>();
            for (int j = 1; j <= 4; j++) {
                JCheckBox checkBox = new JCheckBox();
                checkBox.setBackground(new Color(240, 248, 255));
                checkBoxes.add(checkBox);
                // Nếu có dữ liệu ban đầu, hiển thị trạng thái đã chọn
                if (initialData != null) {
                    String key = functionNames.get(i) + "_" + columnNames[j].toLowerCase();
                    if (initialData.containsKey(key)) {
                        checkBox.setSelected(Boolean.parseBoolean(initialData.get(key)));
                    }
                }
            }
            functionCheckBoxes.add(checkBoxes);
        }

        // Tạo JTable để hiển thị bảng
        JTable functionTable = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 0; // Chỉ cho phép chỉnh sửa các cột checkbox
            }

            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 0) return String.class;
                return Boolean.class;
            }

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                if (column > 0) {
                    functionCheckBoxes.get(row).get(column - 1).setSelected((Boolean) aValue);
                }
            }

            @Override
            public Object getValueAt(int row, int column) {
                if (column == 0) return functionNames.get(row);
                return functionCheckBoxes.get(row).get(column - 1).isSelected();
            }
        };

        functionTable.setRowHeight(25);
        functionTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        for (int i = 1; i < 5; i++) {
            functionTable.getColumnModel().getColumn(i).setPreferredWidth(50);
        }

        // Đặt panel chức năng vào một JScrollPane
        JScrollPane functionScrollPane = new JScrollPane(functionTable);
        functionScrollPane.setPreferredSize(new Dimension(400, 300));
        functionPanel.add(functionScrollPane, BorderLayout.CENTER);

        // Thêm các panel vào mainPanel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(functionPanel, BorderLayout.CENTER);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(240, 248, 255));
        JButton saveButton = new JButton("Cập nhật nhóm...");
        JButton cancelButton = new JButton("Hủy bỏ");

        // Định dạng nút
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(new Color(66, 139, 202)); // Màu xanh giống hình
        cancelButton.setBackground(new Color(217, 83, 79)); // Màu đỏ giống hình
        saveButton.setForeground(Color.WHITE);
        cancelButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        cancelButton.setFocusPainted(false);

        // Sự kiện nút "Cập nhật nhóm..."
        saveButton.addActionListener(e -> {
            if (validateFields()) {
                // Lưu dữ liệu từ các trường nhập liệu
                for (String label : fields.keySet()) {
                    fields.get(label).setText(fields.get(label).getText().trim());
                }
                // Lưu trạng thái của các checkbox
                for (int i = 0; i < functionNames.size(); i++) {
                    for (int j = 0; j < 4; j++) {
                        String key = functionNames.get(i) + "_" + columnNames[j + 1].toLowerCase();
                        fields.put(key, new JTextField(String.valueOf(functionCheckBoxes.get(i).get(j).isSelected())));
                    }
                }
                confirmed = true;
                dispose();
            }
        });

        // Sự kiện nút "Hủy bỏ"
        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Hỗ trợ chế độ "Chi tiết"
        if (title.equals("Chi tiết nhóm quyền")) {
            for (JTextField field : fields.values()) {
                field.setEditable(false);
            }
            for (List<JCheckBox> checkBoxes : functionCheckBoxes) {
                for (JCheckBox checkBox : checkBoxes) {
                    checkBox.setEnabled(false);
                }
            }
            saveButton.setVisible(false); // Ẩn nút "Cập nhật nhóm..." trong chế độ chi tiết
        }

        // Cài đặt dialog
        setSize(500, 500); // Tăng kích thước để chứa bảng chức năng
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    // Kiểm tra dữ liệu đầu vào
    private boolean validateFields() {
        // Kiểm tra Mã nhóm quyền và Tên nhóm quyền không được để trống
        if (fields.get("Mã nhóm quyền").getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã nhóm quyền không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (fields.get("Tên nhóm quyền").getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên nhóm quyền không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
            data.put(label, fields.get(label).getText());
        }
        return data;
    }
}