package view.phieuxuat;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.DefaultTableModel;



/*###################
- Panel popup này sẽ hiển thị khi nhấn nút xem chi tiết 
###################*/

public class ChiTietPhieuXuat extends JPanel {
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
    
    private int height = 600;
    private int width = 1000;
    private Font myFont1 = new Font("Arial", Font.BOLD, 26);//dung de dieu chinh phong chu, kieu chu, kich co
    private Font myFont2 = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    
    private Color bgColor = new Color(255, 255, 255);
    
    public ChiTietPhieuXuat() {
        InitComponents();
    }
    
    public void InitComponents() {
        InitPanel();
        
        InitPaneltieude();
        
        InitPanelthongtinchitiet();
        
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
    
    public void InitPaneltieude() {
        pnlTieude = new JPanel();
        
        pnlTieude.setOpaque(true);
        
        pnlTieude.setPreferredSize(new Dimension(this.pnlTieude.getPreferredSize().width, 80));
       
        // đặt màu tạm thời
        pnlTieude.setBackground(Color.cyan);
        
        //căn giũa Label trong Panel bằng GridBagLayout 
        pnlTieude.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        //label
        JLabel lblTieude = new JLabel("Chi tiết phiếu xuất");
        
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
        this.add(pnlTieude, BorderLayout.NORTH);
    }
    
    public void InitPanelthongtinchitiet() {
        //các lablel thông tin
        lblThongtin = new ArrayList<JLabel>();
        
        lblHienthi = new String[] {"Mã phiếu", "Người tạo", "Thời gian tạo"};
        
        for(int i = 0; i < 3; ++i) {
            JLabel templbl = new JLabel(lblHienthi[i] + ": ");
            
            templbl.setOpaque(true);
            
            templbl.setFont(myFont2);
            
            templbl.setBorder(null);
            
            templbl.setBackground(bgColor);
            
            lblThongtin.add(templbl);
        }
        
        // tblThongtin
        
         tblThongtin = new JTable();
        
        tblTruongdulieu = new String[] {"STT", "Mã vật phẩm", "Tên vật phẩm", 
                                                                   "Số lượng", "Đơn giá", "Thành tiền"};
        
        //them du lieu vao tablemodel
        //tablemodel có các trường dữ liệu nhưng không có dòng dữ liệu nào
        tablemodel = new DefaultTableModel(null, tblTruongdulieu);
        
        // ngăn không cho thay đổi các hàng dữ liệu( cái này ngăn việc chọn hàng luôn)
        tblThongtin.setEnabled(false);
        
        //set du lieu cho table thong tin
        tblThongtin.setModel(tablemodel);
        
        tblThongtin.setFont(myFont2);
        
        tblThongtin.setBackground(bgColor);
        
        // Đặt màu nền cho tiêu đề cột (có thể thay đổi thành màu mong muốn)
        tblThongtin.getTableHeader().setBackground(bgColor);  
        
        // Đặt phông chữ cho tiêu đề(cột). header có in đậm nên phông riêng
        Font myTableFont = new Font("Arial", Font.BOLD, 16);
        
        tblThongtin.getTableHeader().setFont(myTableFont);
        
        //#############################
        // Tạo một Custom TableCellRenderer để thay đổi màu nền của ô dữ liệu
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Thay đổi màu nền của ô dữ liệu
                c.setBackground(bgColor); // Đặt màu nền cho ô dữ liệu
                return c;
            }
        };
        
        // Áp dụng renderer cho tất cả các ô trong bảng
        for (int i = 0; i < tblThongtin.getColumnCount(); i++) {
            tblThongtin.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        //#############################
        
        // điểu chỉnh độ rộng cho các cột của table
        tblThongtin.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblThongtin.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblThongtin.getColumnModel().getColumn(2).setPreferredWidth(400);
        tblThongtin.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblThongtin.getColumnModel().getColumn(4).setPreferredWidth(150);
        tblThongtin.getColumnModel().getColumn(5).setPreferredWidth(150);
        
        // thêm table vào nut cuộn
        spNutcuon = new JScrollPane(tblThongtin);
        
        // do nút cuộn chứa table nên chỉnh background của nút cuộn
        spNutcuon.getViewport().setBackground(bgColor);
        
        //Panel chứa các components
        pnlThongtin = new JPanel();
        
        pnlThongtin.setOpaque(true);
        
        pnlThongtin.setBackground(bgColor);
        
        pnlThongtin.setPreferredSize(new Dimension(this.width, 440));//600 - 80 - 80
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(7, 5, 7, 5);//top, left, bottom, right
        
        pnlThongtin.setLayout(new GridBagLayout());
        
        //thêm các components vào pnlThongtin
        // các label thông tin
        for(int i = 0; i < lblThongtin.size(); ++i) {
            if(i <= 1) {
                gbc.gridy = 0;
                gbc.gridx = i;
            }
            else {
                gbc.gridy = 1;
                gbc.gridx = i - 2;
            }
            
            gbc.weightx = 0.5;
            gbc.weighty = 0.1;
             
            gbc.fill = GridBagConstraints.VERTICAL;
            
            gbc.anchor = GridBagConstraints.WEST;// căn các components về bên trái
            
            this.pnlThongtin.add(lblThongtin.get(i), gbc);
        }
        
        // table Thông tin
        gbc.gridx = 0;
        gbc.gridy = 4;
        
        gbc.weightx = 1.0;
        gbc.weighty = 0.8;
        
        gbc.gridwidth = 2;
        
        gbc.fill = GridBagConstraints.BOTH;
        
        this.pnlThongtin.add(spNutcuon, gbc);
        
        //thêm vào Panel lớn
        
        this.add(pnlThongtin, BorderLayout.CENTER);
    }
    
    public void InitPanelTongtien() {
        pnlTongtien = new JPanel();
        
        lblTongtien = new JLabel("Tổng tiền");
        
        // pnlTongtien
        pnlTongtien.setOpaque(true);
        
        pnlTongtien.setBackground(bgColor);
        
        pnlTongtien.setPreferredSize(new Dimension(this.width, 80));
        
        pnlTongtien.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        // căn components về bên trái, hgap, vgap(giống padding)
        
        //lblTongtien
        
        lblTongtien.setOpaque(true);
        
        lblTongtien.setFont(myFont1);
        
        lblTongtien.setBackground(bgColor);
        
        lblTongtien.setBorder(null);
        
        //thêm components và pnlTongtien
        pnlTongtien.add(lblTongtien);
        
        //thêm vào Panel lớn
        this.add(pnlTongtien, BorderLayout.SOUTH);
    }
    
    
    // getter

    public ArrayList<JLabel> getLblThongtin() {
        return lblThongtin;
    }

    public String[] getLblHienthi() {
        return lblHienthi;
    }

    public JTable getTblThongtin() {
        return tblThongtin;
    }

    public DefaultTableModel getTablemodel() {
        return tablemodel;
    }
    

    public JLabel getLblTongtien() {
        return lblTongtien;
    }
    
}
