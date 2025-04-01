package view.nhaphang;


import javax.swing.*;
import java.awt.*;

import javax.swing.border.LineBorder;

public class Paneltrai_Paneltimkiem extends JPanel {
    private GridBagConstraints gbc;
    private JTextField txtfTimkiem;
    private JButton btnTimkiem;
    
    public Paneltrai_Paneltimkiem() {
        InitComponents();
    }
    
    public void InitComponents() {
        InitPanel();
        
        InitTimkiem();
    }
    
    public void InitPanel() {
        this.setOpaque(true);
        
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 100));
        
        //set layout
        gbc = new GridBagConstraints();
        
        this.setLayout(new GridBagLayout());
        
        //tao chu tim kiem nam tren cai border
        this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 1), "Tìm kiếm"));
    }
    
    public void InitTimkiem() {
        txtfTimkiem = new JTextField();
        
        btnTimkiem = new JButton("Làm mới");
        btnTimkiem.setBackground(new Color(255,255,255));
        
        //dieu chinh txtfTimkiem
        txtfTimkiem.setOpaque(true);
        txtfTimkiem.setBorder(new LineBorder(Color.black, 1));

        //dieu chinh btnTimkiem
        btnTimkiem.setFont(new Font("Arial", Font.PLAIN, 18));
        btnTimkiem.setFocusable(false);
        
        //them components vao panel
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 10);//top, left, bottom, right
        
        //lblTimkiem
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        gbc.weightx = 1.0;
        
        //gbc.gridwidth = 2;
        
        this.add(txtfTimkiem, gbc);
        
        //btnTimkiem
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        gbc.weightx = 0.08;
        
        this.add(btnTimkiem, gbc);
        
    }
}
