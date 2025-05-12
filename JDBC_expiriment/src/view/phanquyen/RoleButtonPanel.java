package view.phanquyen;

import javax.swing.*;
import java.awt.*;

public class RoleButtonPanel extends JPanel {
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton detailButton;
    private JComboBox<String> filterFieldComboBox;
    private JTextField filterTextField;
    private JButton filterButton;
    private JButton clearFilterButton;
    private JTable table;

    public RoleButtonPanel(JTable table) {
        this.table = table;
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Tạo panel chính chứa các nút và thanh tìm kiếm
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.WHITE);
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        // Thêm khoảng cách phía trên (margin-top) cho các nút
        controlPanel.add(Box.createVerticalStrut(10));

        // Tạo panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        buttonPanel.setBackground(Color.WHITE);

        // Tạo các nút
        addButton = new JButton("Thêm");
        editButton = new JButton("Sửa");
        deleteButton = new JButton("Xóa");
        detailButton = new JButton("Chi tiết");

        // Định dạng nút
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        editButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        detailButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(200, 220, 255));
        editButton.setBackground(new Color(200, 220, 255));
        deleteButton.setBackground(new Color(200, 220, 255));
        detailButton.setBackground(new Color(200, 220, 255));
        addButton.setFocusPainted(false);
        editButton.setFocusPainted(false);
        deleteButton.setFocusPainted(false);
        detailButton.setFocusPainted(false);

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(detailButton);

        // Tạo panel tìm kiếm
        JPanel filterPanel = new JPanel();
        filterPanel.setBackground(Color.WHITE);
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));

        // Panel cho phần "Lọc theo ..." (label, combobox, text field)
        JPanel filterInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        filterInputPanel.setBackground(Color.WHITE);
        JLabel filterLabel = new JLabel("Lọc theo:");
        filterLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        filterFieldComboBox = new JComboBox<>(new String[]{"Mã nhóm quyền", "Tên nhóm quyền", "Mô tả"});
        filterFieldComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        filterFieldComboBox.setPreferredSize(new Dimension(150, 25));
        filterTextField = new JTextField(10);
        filterTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        filterTextField.setPreferredSize(new Dimension(100, 25)); 

        filterInputPanel.add(filterLabel);
        filterInputPanel.add(filterFieldComboBox);
        filterInputPanel.add(filterTextField);

        // Panel cho các nút "Lọc" và "Xóa bộ lọc"
        JPanel filterButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        filterButtonPanel.setBackground(Color.WHITE);
        filterButtonPanel.add(Box.createHorizontalStrut(108)); // Thêm margin-left 10px cho các nút
        filterButton = new JButton("Lọc");
        filterButton.setFont(new Font("Arial", Font.BOLD, 14));
        filterButton.setBackground(new Color(200, 220, 255));
        filterButton.setFocusPainted(false);
        filterButton.setPreferredSize(new Dimension(60, 25));

        clearFilterButton = new JButton("Xóa bộ lọc");
        clearFilterButton.setFont(new Font("Arial", Font.BOLD, 14));
        clearFilterButton.setBackground(new Color(200, 220, 255));
        clearFilterButton.setFocusPainted(false);
        clearFilterButton.setPreferredSize(new Dimension(110, 25));

        filterButtonPanel.add(filterButton);
        filterButtonPanel.add(clearFilterButton);

        // Thêm các panel con vào filterPanel
        filterPanel.add(filterInputPanel);
        filterPanel.add(Box.createVerticalStrut(5));
        filterPanel.add(filterButtonPanel);

        // Thêm các panel vào controlPanel với khoảng cách nhỏ
        controlPanel.add(buttonPanel);
        controlPanel.add(Box.createVerticalStrut(5));
        controlPanel.add(filterPanel);

        // Thêm controlPanel vào vị trí NORTH của RoleButtonPanel
        add(controlPanel, BorderLayout.NORTH);
    }

    // Getter để controller truy cập
    public JButton getAddButton() {
        return addButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getDetailButton() {
        return detailButton;
    }

    public JComboBox<String> getFilterFieldComboBox() {
        return filterFieldComboBox;
    }

    public JTextField getFilterTextField() {
        return filterTextField;
    }

    public JButton getFilterButton() {
        return filterButton;
    }

    public JButton getClearFilterButton() {
        return clearFilterButton;
    }

    public JTable getTable() {
        return table;
    }
}