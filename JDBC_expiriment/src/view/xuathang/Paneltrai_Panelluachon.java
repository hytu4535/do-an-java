package view.xuathang;



import javax.swing.*;
import java.awt.*;

import javax.swing.border.LineBorder;
import util.ButtonFactory;


public class Paneltrai_Panelluachon extends JPanel {
    private JLabel lblSoluong;
    private JTextField txtfHienthi;
    private JButton btnThem;
    private GridBagConstraints gbc;
    
    public Paneltrai_Panelluachon() {
        InitComponents();
    }
    
    public void InitComponents() {
        
        InitPanel();
        
        InitLuachon();
    } 
    
    public void InitPanel() {
        this.setOpaque(true);
        
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 100));
        
        this.setBackground(new Color(255,255,255));
        //set layout
        gbc = new GridBagConstraints();
        
        this.setLayout(new GridBagLayout());
    }
    
    public void InitLuachon() {
        lblSoluong = new JLabel("Số lượng");// so luong
        
        txtfHienthi = new JTextField();
        
        btnThem = new JButton("Thêm");
        
        ButtonFactory.applyImageToButton(
                btnThem, 
                ButtonFactory.buildIconPath("icon_plus48.png"), 
                16, 
                16);
        
        int height = 40;
        Font myFont = new Font("Arial", Font.PLAIN, 18);//dung de dieu chinh phong chu, kieu chu, kich co
        
        //dieu chinh lblSoluong
        lblSoluong.setBorder(null);
        lblSoluong.setFont(myFont);
        //lblSoluong.setPreferredSize(new Dimension(lblSoluong.getPreferredSize().width, height));
        
        //dieu chinh txtfHienthi
        txtfHienthi.setBorder(new LineBorder(Color.black, 1));// tao vien co mau den, 2px
        txtfHienthi.setPreferredSize(new Dimension(lblSoluong.getPreferredSize().width, height));
        txtfHienthi.setFont(myFont);
        
        
         //dieu chinh btnThem
        btnThem.setFocusable(false);
        btnThem.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        btnThem.setFont(myFont);
        btnThem.setBackground(new Color(240, 248, 255));
        btnThem.setPreferredSize(new Dimension(lblSoluong.getPreferredSize().width, height));
        
        //them cac components vao panel
       
        //lblSoluong
        gbc.insets = new Insets(5, 15, 5, 15);//top, left, bottom, right(cai nay giong margin)
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        this.add(lblSoluong, gbc);
        
        //lblHienthi
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        this.add(txtfHienthi, gbc);
        
        //btnThem
        gbc.gridx = 2;
        gbc.gridy = 0;
        
        this.add(btnThem, gbc);
    }
    
    
    // getter

    public JTextField getTxtfHienthi() {
        return txtfHienthi;
    }

    public JButton getBtnThem() {
        return btnThem;
    }
    
    // getter 
    
    
}
