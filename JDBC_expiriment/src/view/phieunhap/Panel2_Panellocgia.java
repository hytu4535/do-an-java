package view.phieunhap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;



public class Panel2_Panellocgia extends JPanel {
    private JLabel lblTu, lblDen;//hiển thị cho cái bên trên
    private JTextField txtfTu, txtfDen;
    
    private GridBagConstraints gbc;// Panel sẽ dùng gridbaglayout để tạo giao diện
    
    private int height = 100;
    private Font myFont = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    private Color bgColor = new Color(255, 255, 255);
    
    
    public Panel2_Panellocgia() {
        InitComponents();
    }
    
    public void InitComponents() {
       
       InitPanel();
       
       InitLoc();
       
    }
    
    public void InitPanel() {
         //dieuchinh thong so cho panel
        this.setOpaque(true);
        
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 100));
        
        this.setBackground(bgColor);
        
        //set title border
        TitledBorder titledBorder = new TitledBorder("Lọc theo giá");
        
        // Thay đổi font của tiêu đề
        titledBorder.setTitleFont(myFont); // Font, kiểu chữ, kích thước
        
        this.setBorder(titledBorder);
        
        //set layout cho panel
        gbc = new GridBagConstraints();
        
        this.setLayout(new GridBagLayout());
    }
    
    public void InitLoc() {
        txtfTu = new JTextField();
        
        txtfDen = new JTextField();
        
        lblTu = new JLabel("Từ");
        
        lblDen = new JLabel("Đến");
        
        //điều chỉnh cho các label
        // lblTu
        lblTu.setOpaque(true);
        
        lblTu.setBorder(null);
        
        lblTu.setBackground(bgColor);
        
        lblTu.setFont(myFont);
        
        //lblDen
        lblDen.setOpaque(true);
        
        lblDen.setBorder(null);
        
        lblDen.setBackground(bgColor);
        
        lblDen.setFont(myFont);
        
        // điều chỉnh các jdatechooser
        // txtfTu
        txtfTu.setFont(myFont);
        
        txtfTu.setBackground(bgColor);
        
        // txtfDen
        txtfDen.setFont(myFont);
        
        txtfDen.setBackground(bgColor);
        
        // thêm các components vào Panel lớn
        gbc.insets = new Insets(5, 10, 5, 10);//top, left, bottom, right
        
        //lblTu
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        gbc.weightx = 0.1;
        //gbc.weighty = 1.0;
        
        gbc.fill = GridBagConstraints.BOTH;
        
        this.add(lblTu, gbc);
        
        // txtfTu
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        gbc.weightx = 0.4;
        //gbc.weighty = 1.0;
        
        gbc.fill = GridBagConstraints.BOTH;
        
        this.add(txtfTu, gbc);
        
        // lblDen
        gbc.gridx = 2;
        gbc.gridy = 0;
        
        gbc.weightx = 0.1;
        //gbc.weighty = 1.0;
        
        gbc.fill = GridBagConstraints.BOTH;
        
        this.add(lblDen, gbc);
        
        // txtfDen
        gbc.gridx = 3;
        gbc.gridy = 0;
        
        gbc.weightx = 0.4;
        //gbc.weighty = 1.0;
        
        gbc.fill = GridBagConstraints.BOTH;
        
        this.add(txtfDen, gbc);
        
    }
    
    
     //getter

    public JTextField getTxtfTu() {
        return txtfTu;
    }

    public JTextField getTxtfDen() {
        return txtfDen;
    }
}
