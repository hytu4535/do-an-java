package view.thongke;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {
    private JComboBox<String> typeFilter;
    private JTextField searchField;
    private JTextField fromField;
    private JTextField toField;
    private JButton refreshButton;

    public SearchPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(new Color(245, 245, 245));

        // Nhóm Loại thống kê
        JLabel typeFilterLabel = new JLabel("Loại thống kê");
        typeFilter = new JComboBox<>(new String[]{"Sản phẩm", "Phiếu", "Tài khoản"});
        typeFilter.setPreferredSize(new Dimension(100, 25));

        // Nhóm Tìm kiếm
        JLabel searchLabel = new JLabel("Tìm kiếm");
        searchField = new JTextField(15);

        // Nhóm Lọc theo ngày
        JLabel fromLabel = new JLabel("Từ");
        fromField = new JTextField(10);
        JLabel toLabel = new JLabel("Đến");
        toField = new JTextField(10);

        // Nút Làm mới
        refreshButton = new JButton("Làm mới");
        refreshButton.setBackground(new Color(0, 153, 255));
        refreshButton.setForeground(Color.WHITE);

        // Thêm các thành phần vào SearchPanel
        add(typeFilterLabel);
        add(typeFilter);
        add(searchLabel);
        add(searchField);
        add(fromLabel);
        add(fromField);
        add(toLabel);
        add(toField);
        add(refreshButton);
    }

    // Getter để các class khác truy cập
    public JComboBox<String> getTypeFilter() {
        return typeFilter;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JTextField getFromField() {
        return fromField;
    }

    public JTextField getToField() {
        return toField;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }
}