package view.nhaphang;

import javax.swing.*;
import java.awt.*;


public class NhaphangPanel extends JPanel {
     /*################### 
    GHI CHU
    NhaphangPanel chia thanh 2 panel nho de hien thi thong tin gom paneltrai va panelphai
    ###################*/
    private JPanel pnlPaneltrai;
    private JPanel pnlPanelphai;
    private GridLayout gridlayout;
    
    private Font myFont = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    
    private Color bgColor = new Color(255, 255, 255);
    
    public NhaphangPanel() {
        
        InitComponents();
    }
    
    public void InitComponents() {
       
       InitPanel();
        
       InitPaneltrai();
       
       InitPanelphai();
        
    }
    
    public void InitPanel() {
        
        //dieuchinh thong so cho panel
        this.setOpaque(true);
        
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 600));
        
        this.setBackground(bgColor);
        
        this.setFont(myFont);
        
        //set layout cho panel
        gridlayout = new GridLayout(1, 2);// 1 hang, 2 cot
        
        //dung de margin ngang cho 2 panel trai va phai 
        gridlayout.setHgap(5);
        
        this.setLayout(gridlayout);
    }
    
    public void InitPaneltrai() {
        //pnlPaneltrai = new JPanel();
        pnlPaneltrai = new Paneltrai();
        
        //panel ben trai
        
        pnlPaneltrai.setOpaque(true);
        //getPerferredSize().width de lay chieu rong theo mainframe neu co thay doi nhu phong to, thu nho
        //chieu cao van giu nguyen giong chieu cao cua mainframe
        pnlPaneltrai.setPreferredSize(new Dimension(pnlPaneltrai.getPreferredSize().width, 600));
        //Cai nay dung de kiem tra neu co loi
        //pnlPaneltrai.setBackground(Color.red);
        
        //them vao panel lon
        this.add(pnlPaneltrai);
    }
    
    public void InitPanelphai() {
        //pnlPanelphai = new JPanel();
        pnlPanelphai = new Panelphai();
        
        //panel ben phai
        
        pnlPanelphai.setOpaque(true);
        //getPerferredSize().width de lay chieu rong theo mainframe neu co thay doi nhu phong to, thu nho
        //chieu cao van giu nguyen giong chieu cao cua mainframe
        pnlPanelphai.setPreferredSize(new Dimension(pnlPaneltrai.getPreferredSize().width, 600));
        
        //Cai nay dung de kiem tra neu co loi
        //pnlPanelphai.setBackground(bgColor);
        
        //them vao panel lon
        
        this.add(pnlPanelphai);
    }
    
    /*################### 
    GHI CHU
    NhaphangPanel chia thanh 2 panel nho de hien thi thong tin gom paneltrai va panelphai
    ###################*/
}
