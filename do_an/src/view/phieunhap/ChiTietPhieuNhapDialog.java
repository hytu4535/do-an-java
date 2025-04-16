package view.phieunhap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*##########################
-Lớp này tận dụng lại các lớp ChiTietPhieuNhap để thêm thanh tiêu đề vào
để làm popup
##########################*/

public class ChiTietPhieuNhapDialog extends JDialog {
    private ChiTietPhieuNhap pnlContent;
    
    private int height = 900;
    private int width = 1000;
    private Font myFont1 = new Font("Arial", Font.BOLD, 26);//dung de dieu chinh phong chu, kieu chu, kich co
    private Font myFont2 = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    
    private Color bgColor = new Color(255, 255, 255);
    
    //                                                   parent của dialog, tiêu đề, ngăn việc tương tác với parent nếu vẫn còn dialog
    public ChiTietPhieuNhapDialog(JFrame owner, String title, boolean modal) {
        // constructor của JDialog
        super(owner, title, modal);
        InitComponents();

        // căn kích cỡ tự động?
        pack(); 
        // vị trí khi hiện lên
        setLocationRelativeTo(owner); 
    }
    
    public void InitComponents() {
        InitDialog();
        
        InitContent();
    }
    
    public void InitDialog() {
        // Điều chỉnh thông số cho dialog
        this.setLayout(new BorderLayout());
        
        this.getContentPane().setBackground(bgColor);
    }
    
   
    
    public void InitContent() {
        this.pnlContent = new ChiTietPhieuNhap();
        
        this.add(this.pnlContent, BorderLayout.CENTER);
    }
    
    // getter 

    public ChiTietPhieuNhap getPnlContent() {
        return pnlContent;
    }
    
}
