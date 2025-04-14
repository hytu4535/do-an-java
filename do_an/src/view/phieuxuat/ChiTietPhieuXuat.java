package view.phieuxuat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;




public class ChiTietPhieuXuat extends JPanel {
    private JPanel pnlTitlebar;
    
    private JPanel pnlTieude;// tiêu đề của phiếu. vd: chi tiết phiếu nhập
    
    private ArrayList<JLabel> lblThongtin;
                                                    //lblMaphieu, lblNguoitao, lblNhacungcap, lblThoigiantao;
    private String[] lblHienthi;
    
    private JPanel pnlThongtin;
    private JTable tblThongtin;
    private DefaultTableModel tablemodel;// thiết lập dữ liệu cho table
    private JScrollPane spNutcuon;
    private String[] tblTruongdulieu;
    
    private JPanel pnlTongtien;
    private JLabel lblTongtien;
    
    private int height = 1000;
    private int width = 1200;
    private Font myFont1 = new Font("Arial", Font.BOLD, 26);//dung de dieu chinh phong chu, kieu chu, kich co
    private Font myFont2 = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    
    private Color bgColor = new Color(255, 255, 255);
    
    public ChiTietPhieuXuat() {
        InitComponents();
    }
    
    public void InitComponents() {
        InitPanel();
        
        InitPaneltitlebar();
        
        InitPanelTieude();
        
        InitPanelThongtinchitiet();
        
        InitPanelTongtien();
    }
    
    /**############
     -Panel lớn dùng BorderLyout chia làm 3 phần như trong InitComponents
    #############*/
    
    public void InitPanel() {
        // Điều chỉnh thông số cho panel
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setBackground(bgColor);
        
        //set layout
        this.setLayout(new BorderLayout());
    }
    
    public void InitPaneltitlebar() {
        this.pnlTitlebar = new JPanel();
        
        JButton btnClose = new JButton("X");
        
        // điều chỉnh nút 
        btnClose.setPreferredSize(new Dimension(50, 40));
        
        btnClose.setFocusable(false);
        
        btnClose.setBorder(null);
        
        btnClose.setForeground(Color.BLACK);
        
        btnClose.setFont(myFont2);
        
        btnClose.setBackground(Color.LIGHT_GRAY);
        
        // đặt tên để gọi khi xử lý sự kiện 
        btnClose.setActionCommand("Close");
        
        // điều chỉnh pnlTitlebar
        
        pnlTitlebar.setPreferredSize(new Dimension(this.width, 40));
        
        pnlTitlebar.setOpaque(true);
        
        pnlTitlebar.setBackground(Color.LIGHT_GRAY);
        
        // căn component về bên phải
        pnlTitlebar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        pnlTitlebar.add(btnClose);
        
        //thêm vào Panel lớn
        this.add(this.pnlTitlebar, BorderLayout.NORTH);
    }
    
    public void InitPanelTieude() {
        pnlTieude = new JPanel();
        
        pnlTieude.setOpaque(true);
        
        pnlTieude.setPreferredSize(new Dimension(this.pnlTieude.getPreferredSize().width, 80));
       
        // đặt màu tạm thời
        pnlTieude.setBackground(Color.cyan);
        
        //căn giũa Label trong Panel bằng GridBagLayout 
        pnlTieude.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //label
        JLabel lblTieude = new JLabel("Chi tiết phiếu nhập");
        
        lblTieude.setOpaque(true);
        
        lblTieude.setBorder(null);
        
        lblTieude.setFont(myFont1);
        
        lblTieude.setForeground(bgColor);
        
        // màu tạm thời
        lblTieude.setBackground(Color.cyan);
        
        //
        lblTieude.setAlignmentX(Component.CENTER_ALIGNMENT);  // Căn giữa theo chiều ngang
        lblTieude.setAlignmentY(Component.CENTER_ALIGNMENT);  // Căn giữa theo chiều dọc
        
        //thêm vào pnlTieude
        gbc.gridx = 0;  // Cột 0
        gbc.gridy = 0;  // Hàng 0
        gbc.anchor = GridBagConstraints.CENTER;  // Căn giữa cả theo chiều ngang và dọc
        
        pnlTieude.add(lblTieude, gbc);
        
        // thêm vào panel lớn
        this.add(pnlTieude, BorderLayout.CENTER);
    }
    
    public void InitPanelThongtinchitiet() {
        
    }
    
    public void InitPanelTongtien() {
        
    }
}
