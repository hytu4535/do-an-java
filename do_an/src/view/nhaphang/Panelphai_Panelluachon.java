package view.nhaphang;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

import javax.swing.border.LineBorder;


public class Panelphai_Panelluachon extends JPanel {
    private JLabel lblTongtien;
    private JLabel lblHienthi;
    private ArrayList<JButton> btnNut;// 4 cai nut 
    private GridBagConstraints gbc;
    private String[] btnTennut;
    private String[] btnCommand;
    
    private Font myFont = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    
    private Color bgColor = new Color(255, 255, 255);
    
    
    public Panelphai_Panelluachon() {
        InitComponents();
    }
    
    public void InitComponents() {
        
        InitPanel();
        
        InitLuachon();
    } 
    
    public void InitPanel() {
        this.setOpaque(true);
        
        this.setBackground(bgColor);
        
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 100));
        
        //set layout
        gbc = new GridBagConstraints();
        
        this.setLayout(new GridBagLayout());
    }
    
    public void InitLuachon() {
        lblTongtien = new JLabel("Tổng tiền");// so luong
        
        lblHienthi = new JLabel();
        
        btnNut = new ArrayList<JButton>();
        
        btnTennut = new String[] {"Nhập Excel", "Sửa số lượng", "Xóa sản phẩm", "Nhập hàng"};
        
        btnCommand = new String[] {"Excel", "Sua", "Xoa", "Nhap"};
        
        int height = 40;
        
        //dieu chinh lblTongtien
        lblTongtien.setBorder(null);
        
        lblTongtien.setFont(myFont);
        
        lblTongtien.setPreferredSize(new Dimension(lblTongtien.getPreferredSize().width, height));
        
        //dieu chinh lblHienthi
        lblHienthi.setBorder(new LineBorder(Color.black, 1));// tao vien co mau den, 2px
        //lblHienthi.setPreferredSize(new Dimension(lblHienthi.getPreferredSize().width, height));
        
        //dieu chinh btnNut
        for(int i = 0; i < 4; ++i) {
            JButton tempbutt = new JButton(btnTennut[i]);
            
            tempbutt.setFocusable(false);
            
            tempbutt.setFont(myFont);
            
            //tempbutt.setBorder(null);
            
            tempbutt.setBackground(bgColor);
            
            tempbutt.setPreferredSize(new Dimension(tempbutt.getPreferredSize().width, height));
            
            tempbutt.setActionCommand(btnCommand[i]);
            
            btnNut.add(tempbutt);
            
            if(i == 3) {
                tempbutt.setBackground(new Color(50, 168, 82));
            }
        }
        
        //them cac components vao panel
       
        //lblSoluong
        gbc.insets = new Insets(20, 25, 20, 25);//top, left, bottom, right(cai nay giong margin)
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        this.add(lblTongtien, gbc);
        
        //lblHienthi
        gbc.gridx = 1;
        gbc.gridy = 1;
        
        this.add(lblHienthi, gbc);
        
        //btnThem
        for(int i = 0; i < btnNut.size(); ++i) {
            if(i == btnNut.size() - 1) {
                gbc.gridx = 2;
                gbc.gridy = 1;
            }
            else {
                gbc.gridx = i;
                gbc.gridy = 0;
            }
            
            this.add(btnNut.get(i), gbc);
        }
    }
    
    // getter

    public JLabel getLblHienthi() {
        return lblHienthi;
    }

    public ArrayList<JButton> getBtnNut() {
        return btnNut;
    }
    
}
