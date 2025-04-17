package view.nhaphang;



import javax.swing.*;
import java.awt.*;

public class Panelphai extends JPanel {
     /*################
    GHI CHU
    -Panelphai dung boderlayout va co 3 panel nho ben trong no:
    + Panel cho phan tim kiem: NORTH
    + Panel cho phan thong tin(Jtable): CENTER
    + Panel cho phan nut, label: SOUTH
    ################*/
    private Panelphai_Paneltimkiem pnlPaneltimkiem;
    private Panelphai_Panelthongtin pnlPanelthongtin;
    private Panelphai_Panelluachon pnlPanelluachon;
    
     private Font myFont = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    
    private Color bgColor = new Color(255, 255, 255);
    
    
    public Panelphai() {
        InitComponents();
    }
    
    public void InitComponents() {
        InitPanel();
        
        InitPaneltimkiem();
        
        InitPanelthongtin();
        
        InitPanelluachon();
        
    }
    
    public void InitPanel() {
        //dieuchinh thong so cho panel
        this.setOpaque(true);
        
        this.setBackground(bgColor);
        
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 600));
        
        //set layout cho panel
        
        this.setLayout(new BorderLayout());
    }
    
    public void InitPaneltimkiem() {
        this.pnlPaneltimkiem = new Panelphai_Paneltimkiem();
        
        pnlPaneltimkiem.setOpaque(true);
        
        pnlPaneltimkiem.setPreferredSize(new Dimension(pnlPaneltimkiem.getPreferredSize().width, 200));
        
        //dung de fix loi va kiem tra
        //pnlPaneltimkiem.setBackground(Color.red);
        
        //them vao panel lon
        this.add(pnlPaneltimkiem, BorderLayout.NORTH);
    }
    
    public void InitPanelthongtin() {
        this.pnlPanelthongtin = new Panelphai_Panelthongtin();
        
        pnlPanelthongtin.setOpaque(true);
        
        pnlPanelthongtin.setPreferredSize(new Dimension(pnlPanelthongtin.getPreferredSize().width, 200));
        
        //dung de fix loi va kiem tra
        pnlPanelthongtin.setBackground(Color.yellow);
        
        //them vao panel lon
        this.add(pnlPanelthongtin, BorderLayout.CENTER);
    }
    
    public void InitPanelluachon() {
        this.pnlPanelluachon= new Panelphai_Panelluachon();
        
        pnlPanelluachon.setOpaque(true);
        
        pnlPanelluachon.setPreferredSize(new Dimension(pnlPanelluachon.getPreferredSize().width, 200));
        
        //pnlPanelluachon.setBackground(Color.green);
        
        //them vao panel lon
        this.add(pnlPanelluachon, BorderLayout.SOUTH);
    }
    
    
    // getter

    public Panelphai_Paneltimkiem getPnlPaneltimkiem() {
        return pnlPaneltimkiem;
    }

    public Panelphai_Panelthongtin getPnlPanelthongtin() {
        return pnlPanelthongtin;
    }

    public Panelphai_Panelluachon getPnlPanelluachon() {
        return pnlPanelluachon;
    }
    
}
