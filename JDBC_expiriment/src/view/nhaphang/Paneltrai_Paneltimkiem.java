package view.nhaphang;


import javax.swing.*;
import java.awt.*;

import javax.swing.border.LineBorder;
import util.ButtonFactory;

import util.ButtonFactory;



public class Paneltrai_Paneltimkiem extends JPanel {
    private GridBagConstraints gbc;
    private JTextField txtfTimkiem;
    private JLabel lblNhacungcap;
    private JComboBox<String> cbNhacungcap;
    private JButton btnTimkiem;
    
    private Font myFont = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    
    private Color bgColor = new Color(255, 255, 255);
    
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
        
        this.setBackground(bgColor);
        
        //set layout
        gbc = new GridBagConstraints();
        
        this.setLayout(new GridBagLayout());
        
        //tao chu tim kiem nam tren cai border
        this.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black, 1), "Tìm kiếm"));
    }
    
    public void InitTimkiem() {
        txtfTimkiem = new JTextField();
        
        btnTimkiem = new JButton("Tìm kiếm");
        
        ButtonFactory.applyImageToButton(
                btnTimkiem,
                ButtonFactory.buildIconPath("icons8-magnifying-glass-tilted-left-32.png"), 
                16, 
                16);
        
        btnTimkiem.setBackground(this.bgColor);
        
        //dieu chinh txtfTimkiem
        txtfTimkiem.setOpaque(true);
        txtfTimkiem.setFont(myFont);
        txtfTimkiem.setBorder(new LineBorder(Color.black, 1));

        //dieu chinh btnTimkiem
        btnTimkiem.setFont(myFont);
        btnTimkiem.setFocusable(false);
        
        // combobox nha cung cap
        cbNhacungcap = new JComboBox();
        cbNhacungcap.setFont(myFont);
        cbNhacungcap.setBackground(bgColor);
        
        // lblNhacuncap
        lblNhacungcap = new JLabel("Nhà cung cấp: ");
        lblNhacungcap.setFont(myFont);
        lblNhacungcap.setBackground(this.bgColor);
        
        //them components vao panel
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);//top, left, bottom, right
        
        //txtfTimkiem
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
        
        // lblNhacungcap
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        gbc.weightx = 0.5;
        
        this.add(lblNhacungcap, gbc);
        
        //cbNhacungcap
        gbc.gridx = 1;
        gbc.gridy = 1;
        
        gbc.weightx = 0.5;
        
        this.add(cbNhacungcap, gbc);
        
    }
    
    
    
    // getter

    public JTextField getTxtfTimkiem() {
        return txtfTimkiem;
    }

    public JButton getBtnTimkiem() {
        return btnTimkiem;
    }

    public JComboBox<String> getCbNhacungcap() {
        return cbNhacungcap;
    }
    
}
