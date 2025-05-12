package view.sanpham;

import controller.SanPhamController;

import javax.swing.*;
import java.awt.*;

public class SanPhamXoaForm extends JDialog {
    private JTextField txtMaSP;
    private SanPhamController controller;

    public SanPhamXoaForm(Frame parent, SanPhamController controller, String maSP) {
        super(parent, "Xóa sản phẩm", true);
        this.controller = controller;

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));

        JLabel lblTitle = new JLabel("XÓA SẢN PHẨM", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBackground(new Color(50, 168, 82));
        lblTitle.setOpaque(true);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        infoPanel.setBackground(new Color(245, 245, 245));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblMaSP = new JLabel("Mã SP:");
        lblMaSP.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        infoPanel.add(lblMaSP);

        txtMaSP = new JTextField(maSP);
        txtMaSP.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMaSP.setEditable(false);
        infoPanel.add(txtMaSP);

        add(infoPanel, BorderLayout.CENTER);

        JButton btnConfirm = new JButton("Xác nhận");
        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnConfirm.setBackground(new Color(50, 168, 82));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFocusPainted(false);
        btnConfirm.addActionListener(e -> {
            try {
                controller.xoaSanPham(txtMaSP.getText());
                JOptionPane.showMessageDialog(this, "Đã đánh dấu sản phẩm là không hoạt động!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                dispose();
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