package view.phieunhap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;

/*##########################
-Lớp này tận dụng lại các lớp ChiTietPhieuNhap để thêm thanh tiêu đề vào
##########################*/

public class FramePhieunhap extends JPanel {
    private JPanel pnlContent;
    
    private int height = 900;
    private int width = 1000;
    private Font myFont1 = new Font("Arial", Font.BOLD, 26);//dung de dieu chinh phong chu, kieu chu, kich co
    private Font myFont2 = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    
    private Color bgColor = new Color(255, 255, 255);
    
    public FramePhieunhap() {
        InitComponents();
    }
    
    public void InitComponents() {
        InitPanel();
        
        InitPanelTitlebar();
        
        InitContent();
    }
    
    public void InitPanel() {
        // Điều chỉnh thông số cho panel
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setBackground(bgColor);
        
        //set layout
        this.setLayout(new BorderLayout());
    }
    
    public void InitPanelTitlebar() {
        JPanel Titlebar = new JPanel();
        
        JButton Close = new JButton("x");
        
        // điều chỉnh titlebar
        
        Titlebar.setOpaque(true);
        
        //Titlebar.setPreferredSize(new Dimension(40, 40));
        
        Titlebar.setBackground(Color.lightGray);
        
        Titlebar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        // điều chỉnh button
        
        Close.setBackground(Color.lightGray);
        
        Close.setBorder(null);
        
        Close.setFocusable(false);
        
        Close.setPreferredSize(new Dimension(50, 40));
        
        Close.setFont(myFont2);
        
        // thêm nút vào titlebar
        Titlebar.add(Close);
        
        //thêm vào panel lớn
        
        this.add(Titlebar, BorderLayout.NORTH);
    }
    
    public void InitContent() {
        this.pnlContent = new ChiTietPhieuNhap();
        
        this.add(this.pnlContent, BorderLayout.CENTER);
    }
}
