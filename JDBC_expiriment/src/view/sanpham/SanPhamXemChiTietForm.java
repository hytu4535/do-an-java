package view.sanpham;

import javax.swing.*;
import java.awt.*;

public class SanPhamXemChiTietForm extends JDialog {
    public SanPhamXemChiTietForm(Frame parent, Object[] productData) {
        super(parent, "Xem chi tiết sản phẩm", true);
        setSize(600, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 245));

        // Tiêu đề
        JLabel lblTitle = new JLabel("THÔNG TIN SẢN PHẨM", JLabel.CENTER);
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

        // Các nhãn và giá trị
        String[] labels = {"Mã SP", "Tên SP", "Số lượng", "Loại SP", "Giá", "Thương hiệu",
                           "Chất liệu", "Độ dày", "Mô tả", "Xuất xứ", "Trạng thái"};
        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i] + ":");
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            infoPanel.add(lbl);

            JTextField txt = new JTextField();
            txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            txt.setEditable(false);
            txt.setBackground(new Color(230, 230, 230));
            txt.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
            if (i < productData.length) {
                if (i == labels.length - 1) { // Trường trạng thái
                    try {
                        int trangThai = (Integer) productData[i]; // Ép kiểu sang Integer
                        txt.setText(trangThai == 1 ? "Bán" : "Không được bán");
                    } catch (ClassCastException e) {
                        txt.setText("Không xác định"); // Xử lý trường hợp dữ liệu không phải số
                    }
                } else {
                    txt.setText(productData[i] != null ? productData[i].toString() : "");
                }
            }
            infoPanel.add(txt);
        }

        add(infoPanel, BorderLayout.CENTER);

        // Nút đóng
        JButton btnClose = new JButton("Đóng");
        btnClose.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnClose.setBackground(new Color(100, 149, 237));
        btnClose.setForeground(Color.WHITE);
        btnClose.setFocusPainted(false);
        btnClose.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(btnClose);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}