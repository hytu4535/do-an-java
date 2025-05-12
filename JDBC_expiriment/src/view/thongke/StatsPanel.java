package view.thongke;

import dao.AccountDAO;
import dao.NhaCungCapDAO;
import dao.VanPhongPhamDAO;
import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {
    private JLabel productCountLabel; // Nhãn hiển thị số "Sản phẩm trong kho"
    private JLabel supplierCountLabel; // Nhãn hiển thị số "Nhà cung cấp"
    private JLabel userCountLabel; // Nhãn hiển thị số "Tài khoản người dùng"

    public StatsPanel() {
        setLayout(new GridLayout(1, 3, 10, 10));
        setBackground(new Color(245, 245, 245));
        setPreferredSize(new Dimension(0, 120));

        // Lấy số liệu từ database
        VanPhongPhamDAO vanPhongPhamDAO = new VanPhongPhamDAO();
        NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
        AccountDAO accountDAO = new AccountDAO();

        int productCount = vanPhongPhamDAO.getAllVanPhongPhams().size();
        int supplierCount = nhaCungCapDAO.getAllNhaCungCaps().size();
        int userCount = accountDAO.getAllAccounts().size();

        // Ô 1: Sản phẩm trong kho
        JPanel productStats = new JPanel();
        productStats.setBackground(new Color(255, 204, 0)); // Màu vàng
        productStats.setLayout(new BorderLayout());
        productStats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        productStats.setPreferredSize(new Dimension(0, 100));
        java.net.URL productIconUrl = getClass().getResource("../../resources/images/product-icon.png");
        JLabel productIcon;
        if (productIconUrl == null) {
            System.out.println("Không tìm thấy file: ../../resources/images/product-icon.png");
            productIcon = new JLabel("No Icon");
        } else {
            ImageIcon originalIcon = new ImageIcon(productIconUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            productIcon = new JLabel(new ImageIcon(scaledImage));
        }
        productIcon.setHorizontalAlignment(SwingConstants.CENTER);
        productIcon.setBorder(BorderFactory.createEmptyBorder(-10, 80, 0, 0));
        productCountLabel = new JLabel(String.valueOf(productCount), SwingConstants.CENTER);
        productCountLabel.setFont(new Font("Arial", Font.BOLD, 38));
        productCountLabel.setForeground(Color.WHITE);
        JLabel productLabel = new JLabel("Sản phẩm trong kho", SwingConstants.CENTER);
        productLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        productLabel.setForeground(Color.WHITE);
        productStats.add(productIcon, BorderLayout.WEST);
        JPanel productTextPanel = new JPanel(new GridLayout(2, 1));
        productTextPanel.setBackground(new Color(255, 204, 0));
        productTextPanel.add(productCountLabel);
        productTextPanel.add(productLabel);
        productStats.add(productTextPanel, BorderLayout.CENTER);

        // Ô 2: Nhà cung cấp
        JPanel supplierStats = new JPanel();
        supplierStats.setBackground(new Color(255, 102, 0)); // Màu cam
        supplierStats.setLayout(new BorderLayout());
        supplierStats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        supplierStats.setPreferredSize(new Dimension(0, 100));
        java.net.URL supplierIconUrl = getClass().getResource("../../resources/images/supplier-icon.png");
        JLabel supplierIcon;
        if (supplierIconUrl == null) {
            System.out.println("Không tìm thấy file: ../../resources/images/supplier-icon.png");
            supplierIcon = new JLabel("No Icon");
        } else {
            ImageIcon originalIcon = new ImageIcon(supplierIconUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            supplierIcon = new JLabel(new ImageIcon(scaledImage));
        }
        supplierIcon.setHorizontalAlignment(SwingConstants.CENTER);
        supplierIcon.setBorder(BorderFactory.createEmptyBorder(-10, 80, 0, 0));
        supplierCountLabel = new JLabel(String.valueOf(supplierCount), SwingConstants.CENTER);
        supplierCountLabel.setFont(new Font("Arial", Font.BOLD, 38));
        supplierCountLabel.setForeground(Color.WHITE);
        JLabel supplierLabel = new JLabel("Nhà cung cấp", SwingConstants.CENTER);
        supplierLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        supplierLabel.setForeground(Color.WHITE);
        supplierStats.add(supplierIcon, BorderLayout.WEST);
        JPanel supplierTextPanel = new JPanel(new GridLayout(2, 1));
        supplierTextPanel.setBackground(new Color(255, 102, 0));
        supplierTextPanel.add(supplierCountLabel);
        supplierTextPanel.add(supplierLabel);
        supplierStats.add(supplierTextPanel, BorderLayout.CENTER);

        // Ô 3: Tài khoản người dùng
        JPanel userStats = new JPanel();
        userStats.setBackground(new Color(0, 204, 204)); // Màu xanh lam
        userStats.setLayout(new BorderLayout());
        userStats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        userStats.setPreferredSize(new Dimension(0, 100));
        java.net.URL userIconUrl = getClass().getResource("../../resources/images/user-icon.png");
        JLabel userIcon;
        if (userIconUrl == null) {
            System.out.println("Không tìm thấy file: ../../resources/images/user-icon.png");
            userIcon = new JLabel("No Icon");
        } else {
            ImageIcon originalIcon = new ImageIcon(userIconUrl);
            Image scaledImage = originalIcon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
            userIcon = new JLabel(new ImageIcon(scaledImage));
        }
        userIcon.setHorizontalAlignment(SwingConstants.CENTER);
        userIcon.setBorder(BorderFactory.createEmptyBorder(-10, 80, 0, 0));
        userCountLabel = new JLabel(String.valueOf(userCount), SwingConstants.CENTER);
        userCountLabel.setFont(new Font("Arial", Font.BOLD, 38));
        userCountLabel.setForeground(Color.WHITE);
        JLabel userLabel = new JLabel("Tài khoản người dùng", SwingConstants.CENTER);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        userLabel.setForeground(Color.WHITE);
        userStats.add(userIcon, BorderLayout.WEST);
        JPanel userTextPanel = new JPanel(new GridLayout(2, 1));
        userTextPanel.setBackground(new Color(0, 204, 204));
        userTextPanel.add(userCountLabel);
        userTextPanel.add(userLabel);
        userStats.add(userTextPanel, BorderLayout.CENTER);

        // Thêm 3 ô vào StatsPanel
        add(productStats);
        add(supplierStats);
        add(userStats);
    }

    // Setter để cập nhật số liệu
    public void setProductCount(int count) {
        productCountLabel.setText(String.valueOf(count));
    }

    public void setSupplierCount(int count) {
        supplierCountLabel.setText(String.valueOf(count));
    }

    public void setAccountCount(int count) {
        userCountLabel.setText(String.valueOf(count));
    }
}