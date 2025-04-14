package view.xuathang;



import javax.swing.*;
import java.awt.*;

import javax.swing.border.LineBorder;


public class Paneltrai_Panelluachon extends JPanel {
    private JLabel lblSoluong;
    private JLabel lblHienthi;
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
        
        lblHienthi = new JLabel();
        
        btnThem = new JButton("Thêm");
        
        int height = 40;
        Font myFont = new Font("Arial", Font.PLAIN, 18);//dung de dieu chinh phong chu, kieu chu, kich co
        
        //dieu chinh lblSoluong
        lblSoluong.setBorder(null);
        lblSoluong.setFont(myFont);
        //lblSoluong.setPreferredSize(new Dimension(lblSoluong.getPreferredSize().width, height));
        
        //dieu chinh lblHienthi
        lblHienthi.setBorder(new LineBorder(Color.black, 1));// tao vien co mau den, 2px
        lblHienthi.setPreferredSize(new Dimension(lblSoluong.getPreferredSize().width, height));
        
        //dieu chinh btnThem
        btnThem.setFocusable(false);
        btnThem.setBorder(null);
        btnThem.setFont(myFont);
        btnThem.setBackground(Color.green);
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
        
        this.add(lblHienthi, gbc);
        
        //btnThem
        gbc.gridx = 2;
        gbc.gridy = 0;
        
        this.add(btnThem, gbc);
    }
}
