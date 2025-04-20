package view.sanpham;

import controller.SanPhamController;
import javax.swing.*;
import java.awt.*;

public class SanPhamXoaForm extends JDialog {
    private String maSP;
    private SanPhamController controller;

    public SanPhamXoaForm(Frame parent, String maSP, SanPhamController controller) {
        super(parent, "Xóa sản phẩm", true);
        this.maSP = maSP;
        this.controller = controller;
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));

        JLabel lblConfirm = new JLabel("Bạn có chắc chắn muốn xóa sản phẩm " + maSP + "?", JLabel.CENTER);
        lblConfirm.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        add(lblConfirm, BorderLayout.CENTER);

        JButton btnConfirm = new JButton("Xác nhận");
        btnConfirm.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnConfirm.setBackground(new Color(50, 168, 82));
        btnConfirm.setForeground(Color.WHITE);
        btnConfirm.setFocusPainted(false);
        btnConfirm.addActionListener(e -> {
            controller.xoaSanPham(maSP);
            dispose();
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