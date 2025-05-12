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
import util.ButtonFactory;


public class Panel1_Panelchucnang extends JPanel {
    //chiều cao của Panel1 là 100
    // chiều rộng là 800/3 ~ 266, chiếm 1 ô 
    private ArrayList<JButton> btnNut;//5 cái nut:
    private String[] btnTennut, btnCommand;//tên cua 5 nút và tên command
    /*
    +) Xóa
    +) Sửa
    +) Xem chi tiết
    +) Nhập excel
    +) Xuất excel
    */
    private GridBagConstraints gbc;// Panel sẽ dùng gridbaglayout để tạo giao diện
    
    private int height = 100;
    private Font myFont = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    
    
    public Panel1_Panelchucnang() {
        InitComponents();
    }
    
    public void InitComponents() {
       
       InitPanel();
       
       InitChucnang();
       
    }
    
    public void InitPanel() {
         //dieuchinh thong so cho panel
        this.setOpaque(true);
        
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 100));
        
        this.setBackground(new Color(255,255,255));
        
        //set title border
        TitledBorder titledBorder = new TitledBorder("Chức năng");
        
        // Thay đổi font của tiêu đề
        titledBorder.setTitleFont(myFont); // Font, kiểu chữ, kích thước
        
        this.setBorder(titledBorder);
        
        //set layout cho panel
        gbc = new GridBagConstraints();
        
        this.setLayout(new GridBagLayout());
    }
    
    public void InitChucnang() {
     
        //btnNut
        //###########
        btnNut = new ArrayList<JButton>();
        
        //tên các nút
        btnTennut = new String[] {"Xóa", "Sửa", "Chi tiết", "Xuất Excel", "Nhập Excel"};
        btnCommand = new String[] {"Xoa", "Sua", "CT", "XuatE", "NhapE"};
        
        String[] iconFile = new String[] {
           "icon_delete.png",
            "edit_icon.png",
            "view_icon.png",
            "export_icon.png",
            "import_icon.png"
        };
        
        
        
        // thêm nút vào btnNut
        for(int i = 0; i < 5; ++i) {
           JButton tempbutt = new JButton(btnTennut[i]);
            
            tempbutt.setFocusable(false);
            
            tempbutt.setFont(myFont);
            tempbutt.setBackground(new Color(255,255,255));
            
            tempbutt.setPreferredSize(new Dimension(tempbutt.getPreferredSize().width, height));
            tempbutt.setActionCommand(btnCommand[i]);
            
            ButtonFactory.applyImageAboveButton(
                tempbutt,
                ButtonFactory.buildIconPath(iconFile[i]), 
                18, 
                18);
            
            tempbutt.setBackground(new Color(240, 248, 255));
            
            btnNut.add(tempbutt);
       }
       
       //thêm btnNut vào Panel chucnang
       gbc.fill = GridBagConstraints.BOTH;
       gbc.insets = new Insets(5, 5, 5, 5);//top, left, bottom, right
       
       for(int i = 0; i < 5; ++i) {
           gbc.gridx = i;
           gbc.gridy = 0;
           
           this.add(btnNut.get(i), gbc);
       }
    }
    
    
    //getter
    public ArrayList<JButton> getBtnNut() {
        return btnNut;
    }
}
