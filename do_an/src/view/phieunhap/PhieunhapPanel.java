package view.phieunhap;

import java.awt.BorderLayout;

import java.awt.*;

import javax.swing.*;


public class PhieunhapPanel extends JPanel {
    private JPanel pnlPanel1, pnlPanel2, pnlPanel3;//mỗi panel tương ứng với 3 tầng của giao diện
    //Panel 1 gồm: chức năng, tìm kiếm
    //Panel2 gồm: bộ lọc
    //Panel3 gồm: table
    
    private GridBagConstraints gbc;
    
    public PhieunhapPanel() {
        
        InitComponents();
    }
    
    public void InitComponents() {
       
       InitPanel();
       
       InitPanel1();
        
       InitPanel2();
       
       InitPanel3();
        
    }
    
    public void InitPanel() {
         //dieuchinh thong so cho panel
        this.setOpaque(true);
        
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 600));
        
        this.setBackground(new Color(255, 255, 255));
        
        //set layout cho panel
        gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(5, 5, 5, 5);//top, left, bottom, right
 
        this.setLayout(new GridBagLayout());
    }
    
    public void InitPanel1() {
        this.pnlPanel1 = new Panel1();
        //this.pnlPanel1 = new JPanel();
        
        this.pnlPanel1.setOpaque(true);
        
        this.pnlPanel1.setPreferredSize(new Dimension(pnlPanel1.getPreferredSize().width, 70));
        
        this.pnlPanel1.setBackground(new Color(255,255,255));
        
        //thêm pnlPanel1 vào Panel lớn
        gbc.gridx = 0;
        gbc.gridy = 0;
    
        gbc.weightx = 1.0; // Chiếm hết chiều rộng
        gbc.weighty = 0.15; //
        gbc.fill = GridBagConstraints.BOTH; // Panel sẽ trải rộng theo hai chiều
        
        this.add(this.pnlPanel1, gbc);
    }
    
    public void InitPanel2() {
        this.pnlPanel2 = new Panel2();
        //this.pnlPanel1 = new JPanel();
        
        this.pnlPanel2.setOpaque(true);
        
        this.pnlPanel2.setPreferredSize(new Dimension(pnlPanel2.getPreferredSize().width, 70));
        
        this.pnlPanel2.setBackground(new Color(255,255,255));
        
        //thêm pnlPanel1 vào Panel lớn
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        gbc.weightx = 1.0; // Chiếm hết chiều rộng
        gbc.weighty = 0.15;
        gbc.fill = GridBagConstraints.BOTH; // Panel sẽ trải rộng theo hai chiều 
        
        this.add(this.pnlPanel2, gbc);
    }
    
    public void InitPanel3() {
        this.pnlPanel3 = new Panel3();
        //this.pnlPanel1 = new JPanel();
        
        this.pnlPanel3.setOpaque(true);
        
        this.pnlPanel3.setPreferredSize(new Dimension(pnlPanel3.getPreferredSize().width, 460));
        
        this.pnlPanel3.setBackground(new Color(255,255,255));
        
        //thêm pnlPanel1 vào Panel lớn
        gbc.gridx = 0;
        gbc.gridy = 2;
        
        gbc.weightx = 1.0; // Chiếm hết chiều rộng
        gbc.weighty = 0.7;
        gbc.fill = GridBagConstraints.BOTH; // Panel sẽ trải rộng theo hai chiều 
        
        this.add(this.pnlPanel3, gbc);
    }
}
