package view.phieuxuat;

import view.phieunhap.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.DefaultTableModel;


/*##############
-Do Panel3 chỉ có một table nên không cần phân nhỏ như Panel1, 2
##############*/

public class Panel3 extends JPanel {
    private JTable tblThongtin;
    private DefaultTableModel tablemodel;// thiết lập dữ liệu cho table
    private JScrollPane spNutcuon;
    private String[] tblTruongdulieu;
    
    private GridBagConstraints gbc;
    
    private int height = 460;
    private Font myFont = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    private Color bgColor = new Color(255, 255, 255);
    
    // Constructor của Panel3
    public Panel3() {
        InitComponents();
    }
    
    public void InitComponents() {
        InitPanel();
        
        InitPanelThongtin();
        
        //cái này để test cái popup chi tiết phiếu
        //InitTestPopup();
    }
    
    public void InitPanel() {
        // Điều chỉnh thông số cho panel
        this.setOpaque(true);
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 70));
        this.setBackground(Color.pink);
        
        //set layout
        gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(0, 5, 0, 5);//top, left, bottom, right
        
        this.setLayout(new GridBagLayout());
    }
    
    public void InitPanelThongtin() {
        tblThongtin = new JTable();
        
        tblTruongdulieu = new String[] {"STT", "Mã phiếu", 
                                                                   "Người tạo", 
                                                                   "Thời gian tạo", 
                                                                   "Tổng tiền"};
        
        //them du lieu vao tablemodel
        //tablemodel có các trường dữ liệu nhưng không có dòng dữ liệu nào
        tablemodel = new DefaultTableModel(null, tblTruongdulieu);
        
        //set du lieu cho table thong tin
        tblThongtin.setModel(tablemodel);
        
        tblThongtin.setFont(myFont);
        
        tblThongtin.setBackground(bgColor);
        
        // Đặt màu nền cho tiêu đề cột (có thể thay đổi thành màu mong muốn)
        tblThongtin.getTableHeader().setBackground(bgColor);  
        
        // Đặt phông chữ cho tiêu đề(cột). header có font riêng
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
        tblThongtin.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblThongtin.getColumnModel().getColumn(1).setPreferredWidth(185);
        tblThongtin.getColumnModel().getColumn(2).setPreferredWidth(185);
        tblThongtin.getColumnModel().getColumn(3).setPreferredWidth(185);
        tblThongtin.getColumnModel().getColumn(4).setPreferredWidth(185);
        
        // thêm table vào nut cuộn
        spNutcuon = new JScrollPane(tblThongtin);
        
        // chỉnh màu nền của nút cuộn
        spNutcuon.getViewport().setBackground(bgColor);
        
        // thêm vào Panel lớn
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        gbc.fill = GridBagConstraints.BOTH;
        
        this.add(spNutcuon, gbc);
    }
    
    public void InitTestPopup() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        gbc.fill = GridBagConstraints.BOTH;
        
        this.add(new ChiTietPhieuNhap(), gbc);
    }
}
