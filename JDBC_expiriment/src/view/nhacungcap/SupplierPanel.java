package view.nhacungcap;

import controller.SupplierController;
import model.Supplier;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SupplierPanel extends JPanel {
    private JTable supplierTable;
    private DefaultTableModel tableModel;
    private JTextField txtMaNhaCungCap, txtTenNhaCungCap, txtSdt, txtDiaChi;
    private JButton btnAdd, btnUpdate, btnDelete;
    private SupplierController controller;

    public SupplierPanel() {
        controller = new SupplierController(this);
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 245, 245)); // Màu nền nhạt

        // Panel chứa các trường hiển thị thông tin
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBackground(new Color(245, 245, 245));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblMaNhaCungCap = new JLabel("Mã Nhà Cung Cấp:");
        lblMaNhaCungCap.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMaNhaCungCap = createStyledTextField();
        txtMaNhaCungCap.setEditable(false);

        JLabel lblTenNhaCungCap = new JLabel("Tên Nhà Cung Cấp:");
        lblTenNhaCungCap.setFont(new Font("Arial", Font.PLAIN, 14));
        txtTenNhaCungCap = createStyledTextField();
        txtTenNhaCungCap.setEditable(false);

        JLabel lblSdt = new JLabel("Số Điện Thoại:");
        lblSdt.setFont(new Font("Arial", Font.PLAIN, 14));
        txtSdt = createStyledTextField();
        txtSdt.setEditable(false);

        JLabel lblDiaChi = new JLabel("Địa Chỉ:");
        lblDiaChi.setFont(new Font("Arial", Font.PLAIN, 14));
        txtDiaChi = createStyledTextField();
        txtDiaChi.setEditable(false);

        inputPanel.add(lblMaNhaCungCap);
        inputPanel.add(txtMaNhaCungCap);
        inputPanel.add(lblTenNhaCungCap);
        inputPanel.add(txtTenNhaCungCap);
        inputPanel.add(lblSdt);
        inputPanel.add(txtSdt);
        inputPanel.add(lblDiaChi);
        inputPanel.add(txtDiaChi);

        // Panel chứa các nút chức năng
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));
        btnAdd = createStyledButton("Thêm");
        btnUpdate = createStyledButton("Sửa");
        btnDelete = createStyledButton("Xóa");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        inputPanel.add(new JLabel("")); // Placeholder để căn chỉnh
        inputPanel.add(buttonPanel);

        // Bảng hiển thị danh sách nhà cung cấp
        String[] columns = {"Mã NCC", "Tên NCC", "SĐT", "Địa Chỉ"};
        tableModel = new DefaultTableModel(columns, 0);
        supplierTable = new JTable(tableModel);
        supplierTable.setRowHeight(30);
        supplierTable.setFont(new Font("Arial", Font.PLAIN, 14));
        supplierTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        supplierTable.getTableHeader().setBackground(new Color(100, 149, 237));
        supplierTable.getTableHeader().setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(supplierTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        // Thêm các thành phần vào panel
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Tải dữ liệu
        controller.loadSuppliers();

        // Xử lý sự kiện
        btnAdd.addActionListener(e -> showAddDialog());

        btnUpdate.addActionListener(e -> {
            int selectedRow = supplierTable.getSelectedRow();
            if (selectedRow >= 0) {
                String maNhaCungCap = tableModel.getValueAt(selectedRow, 0).toString();
                Supplier supplier = controller.getSupplierById(maNhaCungCap);
                if (supplier != null) {
                    showUpdateDialog(supplier);
                }
            } else {
                showMessage("Vui lòng chọn nhà cung cấp để sửa!");
            }
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = supplierTable.getSelectedRow();
            if (selectedRow >= 0) {
                String maNhaCungCap = tableModel.getValueAt(selectedRow, 0).toString();
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Bạn có chắc muốn xóa nhà cung cấp này?", 
                    "Xác nhận xóa", 
                    JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    controller.deleteSupplier(maNhaCungCap);
                    clearFields();
                }
            } else {
                showMessage("Vui lòng chọn nhà cung cấp để xóa!");
            }
        });

        supplierTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = supplierTable.getSelectedRow();
            if (selectedRow >= 0) {
                txtMaNhaCungCap.setText(tableModel.getValueAt(selectedRow, 0).toString());
                txtTenNhaCungCap.setText(tableModel.getValueAt(selectedRow, 1).toString());
                txtSdt.setText(tableModel.getValueAt(selectedRow, 2).toString());
                txtDiaChi.setText(tableModel.getValueAt(selectedRow, 3).toString());
            }
        });
    }

    // Tạo nút với giao diện đẹp
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }
        });
        return button;
    }

    // Tạo trường nhập liệu với giao diện đẹp
    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return textField;
    }

    // Hiển thị dialog thêm nhà cung cấp
    private void showAddDialog() {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Thêm Nhà Cung Cấp", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getContentPane().setBackground(new Color(255, 255, 255));

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(new Color(255, 255, 255));

        JTextField txtMaNhaCungCap = createStyledTextField();
        JTextField txtTenNhaCungCap = createStyledTextField();
        JTextField txtSdt = createStyledTextField();
        JTextField txtDiaChi = createStyledTextField();

        inputPanel.add(new JLabel("Mã Nhà Cung Cấp:"));
        inputPanel.add(txtMaNhaCungCap);
        inputPanel.add(new JLabel("Tên Nhà Cung Cấp:"));
        inputPanel.add(txtTenNhaCungCap);
        inputPanel.add(new JLabel("Số Điện Thoại:"));
        inputPanel.add(txtSdt);
        inputPanel.add(new JLabel("Địa Chỉ:"));
        inputPanel.add(txtDiaChi);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(255, 255, 255));
        JButton btnSave = createStyledButton("Lưu");
        JButton btnCancel = createStyledButton("Hủy");
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        btnSave.addActionListener(e -> {
            String maNhaCungCap = txtMaNhaCungCap.getText().trim();
            String tenNhaCungCap = txtTenNhaCungCap.getText().trim();
            String sdt = txtSdt.getText().trim();
            String diaChi = txtDiaChi.getText().trim();

            if (maNhaCungCap.isEmpty() || tenNhaCungCap.isEmpty()) {
                showMessage("Mã và tên nhà cung cấp không được để trống!");
                return;
            }

            Supplier supplier = new Supplier(maNhaCungCap, tenNhaCungCap, sdt, diaChi);
            controller.addSupplier(supplier);
            dialog.dispose();
            clearFields();
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    // Hiển thị dialog sửa nhà cung cấp
    private void showUpdateDialog(Supplier supplier) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Sửa Nhà Cung Cấp", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.getContentPane().setBackground(new Color(255, 255, 255));

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(new Color(255, 255, 255));

        JTextField txtMaNhaCungCap = createStyledTextField();
        txtMaNhaCungCap.setText(supplier.getMaNhaCungCap());
        txtMaNhaCungCap.setEditable(false);
        JTextField txtTenNhaCungCap = createStyledTextField();
        txtTenNhaCungCap.setText(supplier.getTenNhaCungCap());
        JTextField txtSdt = createStyledTextField();
        txtSdt.setText(supplier.getSdt());
        JTextField txtDiaChi = createStyledTextField();
        txtDiaChi.setText(supplier.getDiaChi());

        inputPanel.add(new JLabel("Mã Nhà Cung Cấp:"));
        inputPanel.add(txtMaNhaCungCap);
        inputPanel.add(new JLabel("Tên Nhà Cung Cấp:"));
        inputPanel.add(txtTenNhaCungCap);
        inputPanel.add(new JLabel("Số Điện Thoại:"));
        inputPanel.add(txtSdt);
        inputPanel.add(new JLabel("Địa Chỉ:"));
        inputPanel.add(txtDiaChi);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(255, 255, 255));
        JButton btnSave = createStyledButton("Lưu");
        JButton btnCancel = createStyledButton("Hủy");
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        btnSave.addActionListener(e -> {
            String tenNhaCungCap = txtTenNhaCungCap.getText().trim();
            String sdt = txtSdt.getText().trim();
            String diaChi = txtDiaChi.getText().trim();

            if (tenNhaCungCap.isEmpty()) {
                showMessage("Tên nhà cung cấp không được để trống!");
                return;
            }

            Supplier updatedSupplier = new Supplier(
                supplier.getMaNhaCungCap(),
                tenNhaCungCap,
                sdt,
                diaChi
            );
            controller.updateSupplier(updatedSupplier);
            dialog.dispose();
            clearFields();
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    // Hiển thị danh sách nhà cung cấp trong bảng
    public void displaySuppliers(List<Supplier> suppliers) {
        tableModel.setRowCount(0);
        for (Supplier supplier : suppliers) {
            tableModel.addRow(new Object[]{
                supplier.getMaNhaCungCap(),
                supplier.getTenNhaCungCap(),
                supplier.getSdt(),
                supplier.getDiaChi()
            });
        }
    }

    // Hiển thị thông báo
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    // Xóa trắng các trường hiển thị
    private void clearFields() {
        txtMaNhaCungCap.setText("");
        txtTenNhaCungCap.setText("");
        txtSdt.setText("");
        txtDiaChi.setText("");
    }
}