package view.nguoidung;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ButtonPanel extends JPanel {
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton importExcelButton;
    private JButton exportExcelButton;
    private JTable table;

    // Các thành phần cho chức năng lọc
    private JComboBox<String> filterFieldComboBox;
    private JPanel filterInputPanel; // Panel động để chứa trường nhập liệu
    private JTextField filterTextField; // Trường nhập liệu cho các trường dạng text
    private JComboBox<String> roleFilterComboBox; // Trường nhập liệu cho Vai trò
    private JButton filterButton;
    private JButton clearFilterButton;

    public ButtonPanel(JTable table) {
        this.table = table;

        // Sử dụng BoxLayout để sắp xếp các hàng theo chiều dọc
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(40, 5, 5, 5));

        // Khởi tạo các nút
        addButton = new JButton("Thêm");
        editButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        importExcelButton = new JButton("Nhập Excel");
        exportExcelButton = new JButton("Xuất Excel");

        // Định dạng các nút
        Font buttonFont = new Font("Arial", Font.BOLD, 13);
        Color buttonColor = new Color(220, 235, 255);
        Dimension buttonSize = new Dimension(90, 30);

        addButton.setFont(buttonFont);
        editButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        importExcelButton.setFont(buttonFont);
        exportExcelButton.setFont(buttonFont);

        addButton.setBackground(buttonColor);
        editButton.setBackground(buttonColor);
        deleteButton.setBackground(buttonColor);
        importExcelButton.setBackground(buttonColor);
        exportExcelButton.setBackground(buttonColor);

        addButton.setFocusPainted(false);
        editButton.setFocusPainted(false);
        deleteButton.setFocusPainted(false);
        importExcelButton.setFocusPainted(false);
        exportExcelButton.setFocusPainted(false);

        addButton.setPreferredSize(buttonSize);
        editButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        importExcelButton.setPreferredSize(buttonSize);
        exportExcelButton.setPreferredSize(buttonSize);

        addButton.setMaximumSize(buttonSize);
        editButton.setMaximumSize(buttonSize);
        deleteButton.setMaximumSize(buttonSize);
        importExcelButton.setMaximumSize(buttonSize);
        exportExcelButton.setMaximumSize(buttonSize);

        int padding = 5;
        Color borderColor = new Color(150, 200, 255);
        int borderThickness = 1;
        javax.swing.border.Border outerBorder = BorderFactory.createLineBorder(borderColor, borderThickness);
        javax.swing.border.Border innerPadding = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
        javax.swing.border.Border compoundBorder = BorderFactory.createCompoundBorder(outerBorder, innerPadding);

        addButton.setBorder(compoundBorder);
        editButton.setBorder(compoundBorder);
        deleteButton.setBorder(compoundBorder);
        importExcelButton.setBorder(compoundBorder);
        exportExcelButton.setBorder(compoundBorder);

        // Tạo hàng 1: Thêm, Sửa, Xóa
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        row1.setBackground(Color.WHITE);
        row1.add(addButton);
        row1.add(editButton);
        row1.add(deleteButton);

        // Tạo hàng 2: Nhập Excel, Xuất Excel
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        row2.setBackground(Color.WHITE);
        row2.add(importExcelButton);
        row2.add(exportExcelButton);

        // Tạo panel lọc
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBackground(Color.WHITE);
        filterPanel.setBorder(BorderFactory.createTitledBorder("Lọc dữ liệu"));

        // Hàng 1: JComboBox chọn trường lọc
        JPanel filterRow1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        filterRow1.setBackground(Color.WHITE);
        filterRow1.add(new JLabel("Lọc theo:"));
        String[] filterFields = {"Tên tài khoản", "Họ tên", "Vai trò", "Ngày sinh", "Địa chỉ", "Điện thoại"};
        filterFieldComboBox = new JComboBox<>(filterFields);
        filterRow1.add(filterFieldComboBox);

        // Hàng 2: Panel động để chứa trường nhập liệu
        filterInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        filterInputPanel.setBackground(Color.WHITE);
        filterTextField = new JTextField(15);
        String[] roles = {"Admin", "User", "Nhân viên nhập", "Nhân viên xuất", "Nhân viên kho", "Quản lý kho"};
        roleFilterComboBox = new JComboBox<>(roles);
        // Mặc định hiển thị JTextField
        filterInputPanel.add(new JLabel("Giá trị:"));
        filterInputPanel.add(filterTextField);

        // Hàng 3: Nút Lọc và Xóa bộ lọc
        JPanel filterRow2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        filterRow2.setBackground(Color.WHITE);
        filterButton = new JButton("Lọc");
        filterButton.setFont(buttonFont);
        filterButton.setBackground(buttonColor);
        filterButton.setFocusPainted(false);
        filterButton.setPreferredSize(new Dimension(90, 30));
        filterButton.setMaximumSize(new Dimension(90, 30));
        filterButton.setBorder(compoundBorder);
        clearFilterButton = new JButton("Xóa bộ lọc");
        clearFilterButton.setFont(buttonFont);
        clearFilterButton.setBackground(buttonColor);
        clearFilterButton.setFocusPainted(false);
        clearFilterButton.setPreferredSize(new Dimension(90, 30));
        clearFilterButton.setMaximumSize(new Dimension(90, 30));
        clearFilterButton.setBorder(compoundBorder);
        filterRow2.add(filterButton);
        filterRow2.add(clearFilterButton);

        // Thêm sự kiện cho JComboBox để thay đổi trường nhập liệu
        filterFieldComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    filterInputPanel.removeAll();
                    filterInputPanel.add(new JLabel("Giá trị:"));
                    String selectedField = (String) filterFieldComboBox.getSelectedItem();
                    if (selectedField.equals("Vai trò")) {
                        filterInputPanel.add(roleFilterComboBox);
                    } else {
                        filterInputPanel.add(filterTextField);
                    }
                    filterInputPanel.revalidate();
                    filterInputPanel.repaint();
                }
            }
        });

        // Thêm các hàng vào panel lọc
        filterPanel.add(filterRow1);
        filterPanel.add(filterInputPanel);
        filterPanel.add(filterRow2);

        // Thêm các hàng vào panel chính
        add(row1);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(row2);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(filterPanel);
    }

    // Getters
    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getImportExcelButton() {
        return importExcelButton;
    }

    public JButton getExportExcelButton() {
        return exportExcelButton;
    }

    public JComboBox<String> getFilterFieldComboBox() {
        return filterFieldComboBox;
    }

    public JTextField getFilterTextField() {
        return filterTextField;
    }

    public JComboBox<String> getRoleFilterComboBox() {
        return roleFilterComboBox;
    }

    public JButton getFilterButton() {
        return filterButton;
    }

    public JButton getClearFilterButton() {
        return clearFilterButton;
    }
}