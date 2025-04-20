package view.sanpham;

import controller.SanPhamController;
import model.VanPhongPham;
import javax.swing.*;
import java.awt.*;

public class SanPhamThemForm extends JDialog {
    private JTextField[] textFields;
    private SanPhamController controller;

    public SanPhamThemForm(Frame parent, SanPhamController controller) {
        super(parent, "Thêm sản phẩm", true);
        this.controller = controller;
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
        textFields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i] + ":");
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            infoPanel.add(lbl);

            textFields[i] = new JTextField();
            textFields[i].setFont(new Font("Segoe UI", Font.PLAIN, 14));
            textFields[i].setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
            infoPanel.add(textFields[i]);
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
                int soLuong = Integer.parseInt(textFields[2].getText().trim());
                String loaiVatPham = textFields[3].getText().trim();
                double gia = Double.parseDouble(textFields[4].getText().trim());
                String thuongHieu = textFields[5].getText().trim();
                String chatLieu = textFields[6].getText().trim();
                double doDay = Double.parseDouble(textFields[7].getText().trim());
                String moTa = textFields[8].getText().trim();
                String xuatXu = textFields[9].getText().trim();
                int trangThai = Integer.parseInt(textFields[10].getText().trim());

                if (maVatPham.isEmpty() || tenVatPham.isEmpty()) {
                    throw new IllegalArgumentException("Mã SP và Tên SP không được để trống!");
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
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số lượng, Giá, Độ dày và Trạng thái phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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