package view.thongke;

import controller.StatisticsController;
import javax.swing.*;
import java.awt.*;

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

        // Thêm các thành phần vào StatisticsPanel
        add(statsPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(tablePanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }

    // Getter để Controller truy cập
    public SearchPanel getSearchPanel() {
        return searchPanel;
    }

    public TablePanel getTablePanel() {
        return tablePanel;
    }
}