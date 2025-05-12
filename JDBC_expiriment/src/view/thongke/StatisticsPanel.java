package view.thongke;

import controller.StatisticsController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsPanel extends JPanel {
    private StatsPanel statsPanel;
    private SearchPanel searchPanel;
    private TablePanel tablePanel;
    private StatisticsController controller;

    public StatisticsPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 245));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Khởi tạo các thành phần
        statsPanel = new StatsPanel();
        searchPanel = new SearchPanel();
        tablePanel = new TablePanel();

        // Khởi tạo controller
        controller = new StatisticsController(this);

        // Thêm Timer để tự động làm mới dữ liệu mỗi 5 giây
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshData(); // Gọi hàm làm mới dữ liệu
            }
        });
        timer.start(); // Bắt đầu timer

        // Thêm các thành phần vào StatisticsPanel
        add(statsPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(tablePanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }

    // Phương thức làm mới dữ liệu
    private void refreshData() {
        controller.loadData(); // Gọi phương thức trong controller để làm mới dữ liệu
    }

    // Getter để Controller truy cập
    public StatsPanel getStatsPanel() {
        return statsPanel;
    }

    public SearchPanel getSearchPanel() {
        return searchPanel;
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }
}