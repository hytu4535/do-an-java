package view.phieunhap;

import com.toedter.calendar.JDateChooser;

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



public class Panel2_Panellocngay extends JPanel {
    //####< Lưu ý >####
    // -Kiểm tra Libraries xem có file < jcalender-1.4.jar > chưa
    // NẾU CHƯA CÓ
    // => Tải jcalender-1.4.jar trên mạng về và add file đó vào Libraries trước khi dùng
    //###############
    private JDateChooser dcTu, dcDen;// chọn thời gian từ .... đến .....
    private JLabel lblTu, lblDen;//hiển thị cho cái bên trên
    
    private GridBagConstraints gbc;// Panel sẽ dùng gridbaglayout để tạo giao diện
    
    private int height = 100;
    private Font myFont = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    private Color bgColor = new Color(255, 255, 255);
    
    
    public Panel2_Panellocngay() {
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
        
        this.setBackground(new Color(255,255,255));
        
        //set title border
        TitledBorder titledBorder = new TitledBorder("Lọc theo ngày");
        
        // Thay đổi font của tiêu đề
        titledBorder.setTitleFont(myFont); // Font, kiểu chữ, kích thước
        
        this.setBorder(titledBorder);
        
        //set layout cho panel
        gbc = new GridBagConstraints();
        
        this.setLayout(new GridBagLayout());
    }
    
    public void InitLoc() {
        dcTu = new JDateChooser();
        
        dcDen = new JDateChooser();
        
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
        // dcTu
        dcTu.setFont(myFont);
        
        JDateChooserConfig.customizeDateChooser(dcTu, bgColor);
        
        // dcDen
        dcDen.setFont(myFont);
        
        //dcDen.setBackground(bgColor);
        JDateChooserConfig.customizeDateChooser(dcDen, bgColor);
        
        // thêm các components vào Panel lớn
        gbc.insets = new Insets(5, 10, 5, 10);//top, left, bottom, right
        
        //lblTu
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        gbc.weightx = 0.1;
        //gbc.weighty = 1.0;
        
        gbc.fill = GridBagConstraints.BOTH;
        
        this.add(lblTu, gbc);
        
        // dcTu
        gbc.gridx = 1;
        gbc.gridy = 0;
        
        gbc.weightx = 0.4;
        //gbc.weighty = 1.0;
        
        gbc.fill = GridBagConstraints.BOTH;
        
        this.add(dcTu, gbc);
        
        // lblDen
        gbc.gridx = 2;
        gbc.gridy = 0;
        
        gbc.weightx = 0.1;
        //gbc.weighty = 1.0;
        
        gbc.fill = GridBagConstraints.BOTH;
        
        this.add(lblDen, gbc);
        
        // dcDen
        gbc.gridx = 3;
        gbc.gridy = 0;
        
        gbc.weightx = 0.4;
        //gbc.weighty = 1.0;
        
        gbc.fill = GridBagConstraints.BOTH;
        
        this.add(dcDen, gbc);
        
    }
}
