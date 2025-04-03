package view.phieunhap;

import javax.swing.*;
import java.awt.*;

public class Panel1 extends JPanel {
    private JPanel pnlChucnang;
    private JPanel pnlTimkiem;
    
    private GridBagConstraints gbc;
    
    // Constructor của Panel1
    public Panel1() {
        InitComponents();
    }
    
    public void InitComponents() {
        InitPanel();
        
        InitPanelchucnang();
        
        InitPaneltimkiem();
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
    
    public void InitPanelchucnang() {
        this.pnlChucnang = new Panel1_Panelchucnang();
        
        this.pnlChucnang.setOpaque(true);
        
        this.pnlChucnang.setPreferredSize(new Dimension(this.pnlChucnang.getPreferredSize().width, 70));
        
        //thêm vào Panel1
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        gbc.weightx = 0.6; // 
        gbc.weighty = 1.0; // 
        gbc.fill = GridBagConstraints.BOTH; // Panel sẽ trải rộng theo hai chiều 
        
        this.add(this.pnlChucnang, gbc);
    }
    
    public void InitPaneltimkiem() {
        this.pnlTimkiem = new Panel1_Paneltimkiem();
        
        this.pnlTimkiem.setOpaque(true);
        
        this.pnlTimkiem.setPreferredSize(new Dimension(this.pnlTimkiem.getPreferredSize().width, 70));
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        gbc.weightx = 1.0; // Chiếm hết chiều rộng
        gbc.weighty = 1.0; // 
        gbc.fill = GridBagConstraints.BOTH; // Panel sẽ trải rộng theo hai chiều 
        
        this.add(this.pnlTimkiem, gbc);
    }
}
