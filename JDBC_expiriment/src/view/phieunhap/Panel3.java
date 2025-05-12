package view.phieunhap;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.DefaultTableModel;
import view.phieunhap.ChiTietPhieuNhap;


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
        
        tblTruongdulieu = new String[] {"STT", "Mã phiếu", "Nhà cung cấp", 
                                                                   "Người tạo", "Thời gian tạo", "Tổng tiền"};
        
        //them du lieu vao tablemodel
        //tablemodel có các trường dữ liệu nhưng không có dòng dữ liệu nào và
        // các ô dữ liệu không thể bị thay đổi nhưng vẫn chọn được
        //########################
        tablemodel = new DefaultTableModel(null, tblTruongdulieu) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không ô nào được chỉnh sửa
            }
        };
        //########################

        
        // ngăn không cho thay đổi các hàng dữ liệu( cái này ngăn việc chọn hàng luôn)
        //tblThongtin.setEnabled(false);
        
        //set du lieu cho table thong tin
        tblThongtin.setModel(tablemodel);
        
        tblThongtin.setFont(myFont);
        
        tblThongtin.setBackground(bgColor);
        
        // cho phép chọn theo hàng
        tblThongtin.setRowSelectionAllowed(true);
        // cái này chỉ chọn 1 hàng duy nhất
        tblThongtin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
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
                
                // nếu hàng được chọn thì chuyển màu
                if (isSelected) {
                    // Màu khi được chọn
                    c.setBackground(new Color(255, 255, 153)); // Vàng nhạt 
                    c.setForeground(Color.BLACK);
                }
                else {
                    // Màu nền mặc định
                    c.setBackground(bgColor);
                    c.setForeground(Color.BLACK);
                }

                return c;
            }
        };

        // Áp dụng renderer cho tất cả các ô trong bảng
        for (int i = 0; i < tblThongtin.getColumnCount(); i++) {
            tblThongtin.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        //#############################
        
        // điểu chỉnh độ rộng cho các cột của table
        tblThongtin.getColumnModel().getColumn(0).setPreferredWidth(48);
        tblThongtin.getColumnModel().getColumn(1).setPreferredWidth(48);
        tblThongtin.getColumnModel().getColumn(2).setPreferredWidth(352);
        tblThongtin.getColumnModel().getColumn(3).setPreferredWidth(128);
        tblThongtin.getColumnModel().getColumn(4).setPreferredWidth(112);
        tblThongtin.getColumnModel().getColumn(5).setPreferredWidth(112);
        
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
    
    //########################
    public void InitTestPopup() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        
        gbc.fill = GridBagConstraints.BOTH;
        
        this.add(new ChiTietPhieuNhap(), gbc);
    }
   //########################

    
    //các getter để lấy component cho lớp bus(controller)
    
    public JTable getTblThongtin() {
        return tblThongtin;
    }

    public DefaultTableModel getTablemodel() {
        return tablemodel;
    }

}
