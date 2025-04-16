package view.phieuxuat;

import view.phieunhap.*;
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


public class Panel1_Paneltimkiem extends JPanel {
    private JComboBox<String> cbTimkiem;
    private JTextField txtfTimkiem;
    private JButton btnLammoi;
    private String[] Boloc;//bộ lọc cho combobox
    
    private GridBagConstraints gbc;
    private int height = 70;
    private Font myFont = new Font("Arial", Font.PLAIN, 16);
    
    public Panel1_Paneltimkiem() {
        InitComponents();
    }
    
    public void InitComponents() {
       
       InitPanel();
       
       InitTimkiem();
       
    }
    
    public void InitPanel() {
         //dieuchinh thong so cho panel
        this.setOpaque(true);
        
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 100));
        
        this.setBackground(new Color(255,255,255));
        
        //set title border
        TitledBorder titledBorder = new TitledBorder("Tìm kiếm");
        
        // Thay đổi font của tiêu đề
        titledBorder.setTitleFont(myFont); // Font, kiểu chữ, kích thước
        
        this.setBorder(titledBorder);
        
        //set layout cho panel
        gbc = new GridBagConstraints();
        
        
        
        this.setLayout(new GridBagLayout());
    }
    
    public void InitTimkiem() {
        btnLammoi = new JButton("Làm mới");
        
        txtfTimkiem = new JTextField();
        
        Boloc = new String[] {"Mã phiếu ", "Người tạo"};
        
        cbTimkiem = new JComboBox<>(Boloc);
        
        // đieeuf chỉnh các components
        //btnLammoi
        btnLammoi.setOpaque(true);
        
        btnLammoi.setBackground(new Color(255, 255, 255));
        
        btnLammoi.setFont(myFont);
        
        btnLammoi.setFocusable(false);
        
        //btnLammoi.setPreferredSize(new Dimension(this.btnLammoi.getPreferredSize().width, this.height));
        
        btnLammoi.setActionCommand("lammoi");
        
        //txtfTimkiem
        
        txtfTimkiem.setOpaque(true);
        
        txtfTimkiem.setFont(myFont);
        
        txtfTimkiem.setBackground(new Color(255, 255, 255));
        
        txtfTimkiem.setBorder(new LineBorder(Color.black, 1));
        
        //txtfTimkiem.setPreferredSize(new Dimension(this.txtfTimkiem.getPreferredSize().width, this.height));
        
        //cbTimkiem
        cbTimkiem.setBackground(new Color(255, 255, 255));
        
        cbTimkiem.setFont(myFont);
        
        //cbTimkiem.setPreferredSize(new Dimension(this.cbTimkiem.getPreferredSize().width, this.height));
        
        // thêm vaof panel lớn
        gbc.insets = new Insets(5, 10, 5, 10);//top, left, right, bottom
        //cbTimkiem
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        gbc.weightx = 0.2;
        
        gbc.fill = GridBagConstraints.HORIZONTAL; // Panel sẽ trải rộng theo chiều ngang
        
        this.add(cbTimkiem, gbc);
        
        //txtfTimkiem
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        gbc.weightx = 0.7; 
        
        gbc.gridwidth = 2;//txtf sẽ chiếm 2 ô
        
        gbc.fill = GridBagConstraints.BOTH; // Panel sẽ trải rộng theo hai chiều
        
        this.add(txtfTimkiem, gbc);
        
        // btnLammoi
        gbc.gridx = 3;
        gbc.gridy = 0;
        
        gbc.weightx = 0.1;

        gbc.fill = GridBagConstraints.HORIZONTAL; // Panel sẽ trải rộng theo chiều ngang
        
        this.add(btnLammoi, gbc);
    }
    
    
    // getter

    public JTextField getTxtfTimkiem() {
        return txtfTimkiem;
    }

    public JButton getBtnLammoi() {
        return btnLammoi;
    }

    public JComboBox<String> getCbTimkiem() {
        return cbTimkiem;
    }
    
    
}
