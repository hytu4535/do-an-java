package view.nhaphang;

import javax.swing.*;
import java.awt.*;


public class Paneltrai extends JPanel {
    /*################
    GHI CHU
    -Paneltrai dung boderlayout va co 3 panel nho ben trong no:
    + Panel cho phan tim kiem: NORTH
    + Panel cho phan thong tin(Jtable): CENTER
    + Panel cho phan nut, label: SOUTH
    ################*/
    private JPanel pnlPaneltimkiem;
    private JPanel pnlPanelthongtin;
    private JPanel pnlPanelluachon;
    
    public Paneltrai() {
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
        
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 600));
        
        //set layout cho panel
        
        this.setLayout(new BorderLayout());
        
    }
    
    public void InitPaneltimkiem() {
        this.pnlPaneltimkiem = new Paneltrai_Paneltimkiem();
        
        pnlPaneltimkiem.setOpaque(true);
        
        pnlPaneltimkiem.setPreferredSize(new Dimension(pnlPaneltimkiem.getPreferredSize().width, 100));
        //dung de fix loi va kiem tra
        //pnlPaneltimkiem.setBackground(Color.red);
        
        //them vao panel lon
        this.add(pnlPaneltimkiem, BorderLayout.NORTH);
    }
    
    public void InitPanelthongtin() {
        this.pnlPanelthongtin = new Paneltrai_Panelthongtin();
        
        pnlPanelthongtin.setOpaque(true);
        
        pnlPanelthongtin.setPreferredSize(new Dimension(pnlPanelthongtin.getPreferredSize().width, 400));
        
        //dung de fix loi va kiem tra
        pnlPanelthongtin.setBackground(Color.yellow);
        
        //them vao panel lon
        this.add(pnlPanelthongtin, BorderLayout.CENTER);
    }
    
    public void InitPanelluachon() {
        this.pnlPanelluachon= new Paneltrai_Panelluachon();
                
        pnlPanelluachon.setOpaque(true);
        
        pnlPanelluachon.setPreferredSize(new Dimension(pnlPanelluachon.getPreferredSize().width, 100));
                
        //pnlPanelluachon.setBackground(Color.green);
        
        //them vao panel lon
        this.add(pnlPanelluachon, BorderLayout.SOUTH);
    }
}
