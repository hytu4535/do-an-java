package view.phieunhap;

import javax.swing.*;
import java.awt.*;

public class Panel2 extends JPanel {
    private JPanel pnlLocngay;
    private JPanel pnlLocgia;
    
    private GridBagConstraints gbc;
    
    // Constructor của Panel1
    public Panel2() {
        InitComponents();
    }
    
    public void InitComponents() {
        InitPanel();
        
        InitPanelLocngay();
        
        InitPanelLocgia();
    }
    
    public void InitPanel() {
        // Điều chỉnh thông số cho panel
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 70));
        this.setBackground(Color.pink);
        
        //set layout
        gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(0, 5, 0, 5);//top, left, bottom, right
        
        this.setLayout(new GridBagLayout());
    }
    
    public void InitPanelLocngay() {
        this.pnlLocngay = new Panel2_Panellocngay();
        
        this.pnlLocngay.setOpaque(true);
        
        this.pnlLocngay.setPreferredSize(new Dimension(this.pnlLocngay.getPreferredSize().width, 70));
        
        //thêm vào Panel1
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        gbc.weightx = 0.5; // chiêm một nữa chiều ngang của panel lớn chứa nó
        gbc.weighty = 1.0; // 
        gbc.fill = GridBagConstraints.BOTH; // Panel sẽ trải rộng theo hai chiều 
        
        this.add(this.pnlLocngay, gbc);
    }
    
    public void InitPanelLocgia() {
        this.pnlLocgia = new Panel2_Panellocgia();
        
        this.pnlLocgia.setOpaque(true);
        
        this.pnlLocgia.setPreferredSize(new Dimension(this.pnlLocgia.getPreferredSize().width, 70));
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        gbc.weightx = 0.5;
        gbc.weighty = 1.0; // 
        gbc.fill = GridBagConstraints.BOTH; // Panel sẽ trải rộng theo hai chiều 
        
        this.add(this.pnlLocgia, gbc);
    }
}
