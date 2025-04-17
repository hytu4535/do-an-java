package view.nhaphang;


import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

import javax.swing.border.LineBorder;

public class Panelphai_Paneltimkiem extends JPanel {
    private ArrayList<JLabel> lblHienthi;// 3 cai label de hien thi cho 2 textfield va 1 combobox
    private ArrayList<JTextField> txtfHienthi;
    private String[] Hienthiname;
    private JComboBox<String> cbNhacungcap;//chi dinh ro kieu doi tuong ma combobox chua
    private GridBagConstraints gbc;
    
     private Font myFont = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    
    private Color bgColor = new Color(255, 255, 255);
    
    public Panelphai_Paneltimkiem() {
        InitComponents();
    }
    
    public void InitComponents() {
        InitPanel();
        
        InitTimkiem();
    }
    
    public void InitPanel() {
        this.setOpaque(true);
        
        this.setBackground(bgColor);
        
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 100));
        
        //set layout
        gbc = new GridBagConstraints();
        
        this.setLayout(new GridBagLayout());
        
    }
    
    public void InitTimkiem() {
        Hienthiname = new String[] {"Mã phiếu tạo", "Nhà cung cấp", "Người tạo phiếu"};
        
        lblHienthi = new ArrayList<JLabel>();
        
        txtfHienthi = new ArrayList<JTextField>();
        
        //dieu chinh va them Label vao arraylist
        
        for(int i = 0; i < 3; ++i) {
            JLabel temp = new JLabel(Hienthiname[i]);
            
            temp.setFont(myFont);
            
            temp.setBackground(bgColor);
            
            temp.setOpaque(true);
            
            lblHienthi.add(temp);
        }
        
        //tuong tu voi textfield
        for(int i = 0; i < 2; ++i) {
            JTextField temp = new JTextField();
            
            temp.setOpaque(true);
            //ngan khong cho nguoi dung chinh sua phan textfield
            temp.setEditable(false);
            
            temp.setBackground(bgColor);
            
            temp.setBorder(new LineBorder(Color.black, 1));
            
            temp.setPreferredSize(new Dimension(temp.getPreferredSize().width, 40));
            
            txtfHienthi.add(temp);
        }
        
        //dieu chinh cbNhacungcap
        String[] cbName = new String[] {"bla1", "bla2", "bla3"};
        cbNhacungcap = new JComboBox<>(cbName);
        
        cbNhacungcap.setPreferredSize(new Dimension(cbNhacungcap.getPreferredSize().width, 40));
        
        cbNhacungcap.setFont(myFont);
        
        cbNhacungcap.setBackground(bgColor);
        
        
        //them components vao panel
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 15, 10, 15);//t, l, b, r
        
        //lblHienthi
        for(int i = 0; i < lblHienthi.size(); ++i) {
            gbc.gridx = 0;
            gbc.gridy = i;
            
            gbc.weightx = 0.08;
            
            this.add(lblHienthi.get(i), gbc);
        }
        
        //txtfHienthi
        for(int i = 0; i < txtfHienthi.size(); ++i) {
            //textfield cua ma phieu nhap
            if(i == txtfHienthi.size() - 1) {
                gbc.gridx = 1;
                gbc.gridy = 2;
            }
            //textfield cua nguoi tao phieu
            else {
                gbc.gridx = 1;
                gbc.gridy = 0;
            }
            
            gbc.weightx = 1.0;
            
            this.add(txtfHienthi.get(i), gbc);
        }
        
        //cbNhacuncap
        gbc.gridx = 1;
        gbc.gridy = 1;
        
        gbc.weightx = 1.0;
        
        this.add(cbNhacungcap, gbc);
    }
    
    // getter

    public ArrayList<JTextField> getTxtfHienthi() {
        return txtfHienthi;
    }

    public JComboBox<String> getCbNhacungcap() {
        return cbNhacungcap;
    }
    
}
