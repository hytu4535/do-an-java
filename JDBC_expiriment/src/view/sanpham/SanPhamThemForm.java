package view.sanpham;

import controller.SanPhamController;
import dao.VanPhongPhamDAO;
import model.VanPhongPham;
import javax.swing.*;
import java.awt.*;

public class SanPhamThemForm extends JDialog {
    private JTextField[] textFields;
    private JComboBox<String> cbThuongHieu, cbXuatXu;
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

        JLabel lblTitle = new JLabel("THÊM SẢN PHẨM", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBackground(new Color(50, 168, 82));
        lblTitle.setOpaque(true);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        infoPanel.setBackground(new Color(245, 245, 245));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] labels = {"Mã SP", "Tên SP", "Số lượng", "Loại SP", "Giá", "Thương hiệu",
                           "Chất liệu", "Độ dày", "Mô tả", "Xuất xứ", "Trạng thái"};
        textFields = new JTextField[labels.length - 2]; // Bớt 2 trường (Thương hiệu, Xuất xứ)

        // Các trường dùng JTextField hoặc JComboBox
        int textFieldIndex = 0;
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i] + ":");
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            infoPanel.add(lbl);

            if (i == 5) { // Thương hiệu
                cbThuongHieu = new JComboBox<>(dao.getAllThuongHieu().toArray(new String[0]));
                cbThuongHieu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                infoPanel.add(cbThuongHieu);
            } else if (i == 9) { // Xuất xứ
                cbXuatXu = new JComboBox<>(dao.getAllXuatXu().toArray(new String[0]));
                cbXuatXu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                infoPanel.add(cbXuatXu);
            } else {
                textFields[textFieldIndex] = new JTextField();
                textFields[textFieldIndex].setFont(new Font("Segoe UI", Font.PLAIN, 14));
                textFields[textFieldIndex].setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
                infoPanel.add(textFields[textFieldIndex]);
                textFieldIndex++;
            }
        }

        add(infoPanel, BorderLayout.CENTER);

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
                String loaiVatPham = textFields[3].getText().trim();
                String giaStr = textFields[4].getText().trim();
                String thuongHieu = (String) cbThuongHieu.getSelectedItem();
                String chatLieu = textFields[5].getText().trim();
                String doDayStr = textFields[6].getText().trim();
                String moTa = textFields[7].getText().trim();
                String xuatXu = (String) cbXuatXu.getSelectedItem();
                String trangThaiStr = textFields[8].getText().trim();

                if (maVatPham.isEmpty() || tenVatPham.isEmpty()) {
                    throw new IllegalArgumentException("Mã SP và Tên SP không được để trống!");
                }
                if (soLuongStr.isEmpty()) {
                    throw new IllegalArgumentException("Số lượng không được để trống!");
                }
                if (giaStr.isEmpty()) {
                    throw new IllegalArgumentException("Giá không được để trống!");
                }
                if (dao.isMaVatPhamExists(maVatPham)) {
                    throw new IllegalArgumentException("Mã sản phẩm bị trùng, xin mời nhập lại mã sản phẩm!");
                }
                if (thuongHieu == null || thuongHieu.isEmpty()) {
                    throw new IllegalArgumentException("Thương hiệu không được để trống!");
                }
                if (xuatXu == null || xuatXu.isEmpty()) {
                    throw new IllegalArgumentException("Xuất xứ không được để trống!");
                }

                int soLuong = Integer.parseInt(soLuongStr);
                double gia = Double.parseDouble(giaStr);
                double doDay = doDayStr.isEmpty() ? 0 : Double.parseDouble(doDayStr);
                int trangThai = Integer.parseInt(trangThaiStr);

                if (trangThai != 0 && trangThai != 1) {
                    throw new IllegalArgumentException("Trạng thái chỉ được nhập 0 hoặc 1!");
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
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số lượng, Giá, Độ dày và Trạng thái phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException ex) {
                String errorMessage = ex.getMessage();
                if (errorMessage.contains("Duplicate entry")) {
                    JOptionPane.showMessageDialog(this, "Mã sản phẩm bị trùng, xin mời nhập lại mã sản phẩm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm sản phẩm: " + errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
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