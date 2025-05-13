package view.sanpham;

import controller.SanPhamController;
import dao.VanPhongPhamDAO;
import model.VanPhongPham;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SanPhamThemForm extends JDialog {
    private JTextField[] textFields;
    private JComboBox<String> cbLoaiSP;
    private JComboBox<String> cbTrangThai;
    private JComboBox<String> cbXuatXu;
    private JComboBox<String> cbThuongHieu;
    private SanPhamController controller;
    private VanPhongPhamDAO dao;

    public SanPhamThemForm(Frame parent, SanPhamController controller) {
        super(parent, "Thêm sản phẩm", true);
        this.controller = controller;
        this.dao = VanPhongPhamDAO.getInstance();
        setSize(600, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));

        // Tiêu đề
        JLabel lblTitle = new JLabel("THÊM SẢN PHẨM", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBackground(new Color(50, 168, 82));
        lblTitle.setOpaque(true);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        // Panel chứa thông tin
        JPanel infoPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        infoPanel.setBackground(new Color(245, 245, 245));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] labels = {"Mã SP", "Tên SP", "Số lượng", "Loại SP", "Giá", "Thương hiệu",
                           "Chất liệu", "Độ dày", "Mô tả", "Xuất xứ", "Trạng thái"};
        textFields = new JTextField[labels.length - 4]; // Bớt 4 trường cho Loại SP, Thương hiệu, Xuất xứ, Trạng thái (sẽ dùng JComboBox)

        // Lấy danh sách xuất xứ và thương hiệu từ cơ sở dữ liệu
        List<String> xuatXuList = dao.getAllXuatXu();
        List<String> thuongHieuList = dao.getAllThuongHieu();

        // Các trường dùng JTextField hoặc JComboBox
        int textFieldIndex = 0;
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i] + ":");
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            infoPanel.add(lbl);

            if (i == 3) { // Trường Loại SP
                cbLoaiSP = new JComboBox<>(new String[]{"Bút", "Bút chì", "Sổ tay", "Tẩy", "Thước", "Vở"});
                cbLoaiSP.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                cbLoaiSP.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
                infoPanel.add(cbLoaiSP);
            } else if (i == 5) { // Trường Thương hiệu
                cbThuongHieu = new JComboBox<>(thuongHieuList.toArray(new String[0]));
                cbThuongHieu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                cbThuongHieu.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
                infoPanel.add(cbThuongHieu);
            } else if (i == 9) { // Trường Xuất xứ
                cbXuatXu = new JComboBox<>(xuatXuList.toArray(new String[0]));
                cbXuatXu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                cbXuatXu.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
                infoPanel.add(cbXuatXu);
            } else if (i == 10) { // Trường Trạng thái
                cbTrangThai = new JComboBox<>(new String[]{"Bán", "Không được bán"});
                cbTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                cbTrangThai.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
                cbTrangThai.setSelectedItem("Bán"); // Mặc định chọn "Bán"
                infoPanel.add(cbTrangThai);
            } else {
                textFields[textFieldIndex] = new JTextField();
                textFields[textFieldIndex].setFont(new Font("Segoe UI", Font.PLAIN, 14));
                textFields[textFieldIndex].setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
                if (i == 0) {
                    textFields[textFieldIndex].setEditable(true); // Có thể thêm logic kiểm tra mã duy nhất
                }
                infoPanel.add(textFields[textFieldIndex]);
                textFieldIndex++;
            }
        }

        add(infoPanel, BorderLayout.CENTER);

        // Nút xác nhận và hủy
        JButton btnConfirm = new JButton("Xác nhận");
        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnConfirm.setBackground(new Color(50, 168, 82));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFocusPainted(false);
        btnConfirm.addActionListener(e -> {
            try {
                String maVatPham = textFields[0].getText().trim();
                String tenVatPham = textFields[1].getText().trim();
                String soLuongStr = textFields[2].getText().trim();
                String giaStr = textFields[3].getText().trim();
                String doDayStr = textFields[5].getText().trim();
                String loaiVatPham = (String) cbLoaiSP.getSelectedItem();
                String thuongHieu = (String) cbThuongHieu.getSelectedItem();
                String chatLieu = textFields[4].getText().trim();
                String moTa = textFields[6].getText().trim();
                String xuatXu = (String) cbXuatXu.getSelectedItem();
                String trangThaiStr = (String) cbTrangThai.getSelectedItem();
                int trangThai = trangThaiStr.equals("Bán") ? 1 : 0;

                // Kiểm tra từng trường
                StringBuilder errorMessage = new StringBuilder();
                int soLuong = 0;
                double gia = 0.0;
                double doDay = 0.0;

                if (soLuongStr.isEmpty()) {
                    errorMessage.append("Số lượng không được để trống! ");
                } else {
                    try {
                        soLuong = Integer.parseInt(soLuongStr);
                        if (soLuong < 0) errorMessage.append("Số lượng phải là số dương! ");
                    } catch (NumberFormatException ex) {
                        errorMessage.append("Số lượng phải là số hợp lệ! ");
                    }
                }

                if (giaStr.isEmpty()) {
                    errorMessage.append("Giá không được để trống! ");
                } else {
                    try {
                        gia = Double.parseDouble(giaStr);
                        if (gia < 0) errorMessage.append("Giá phải là số dương! ");
                    } catch (NumberFormatException ex) {
                        errorMessage.append("Giá phải là số hợp lệ! ");
                    }
                }

                if (doDayStr.isEmpty()) {
                    errorMessage.append("Độ dày không được để trống! ");
                } else {
                    try {
                        doDay = Double.parseDouble(doDayStr);
                        if (doDay < 0) errorMessage.append("Độ dày phải là số dương! ");
                    } catch (NumberFormatException ex) {
                        errorMessage.append("Độ dày phải là số hợp lệ! ");
                    }
                }

                if (maVatPham.isEmpty() || tenVatPham.isEmpty() || loaiVatPham == null || thuongHieu == null || xuatXu == null) {
                    errorMessage.append("Mã SP, Tên SP, Loại SP, Thương hiệu và Xuất xứ không được để trống!");
                }

                if (errorMessage.length() > 0) {
                    throw new IllegalArgumentException(errorMessage.toString().trim());
                }

                VanPhongPham sp = new VanPhongPham();
                sp.setMaVatPham(maVatPham);
                sp.setTenVatPham(tenVatPham);
                sp.setSoLuong(soLuong);
                sp.setLoaiVatPham(loaiVatPham);
                sp.setGia(gia);
                sp.setThuongHieu(thuongHieu);
                sp.setChatLieu(chatLieu);
                sp.setDoDay(doDay);
                sp.setMoTa(moTa);
                sp.setXuatXu(xuatXu);
                sp.setTrangThai(trangThai);

                controller.themSanPham(sp);
                dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnCancel = new JButton("Hủy");
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCancel.setBackground(new Color(100, 149, 237));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(btnConfirm);
        buttonPanel.add(btnCancel);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}