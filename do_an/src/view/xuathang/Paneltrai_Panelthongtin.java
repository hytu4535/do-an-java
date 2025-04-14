package view.xuathang;


import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class Paneltrai_Panelthongtin extends JPanel {
    private JTable tblThongtin;
    private DefaultTableModel tablemodel;
    private String[] tblTruongdulieu;// cac truong du lieu cua table
    private JScrollPane nutcuon;//them nut cuon trong truong hop co qua nhieu du lieu
    
    private Font myFont = new Font("Arial", Font.PLAIN, 16);//dung de dieu chinh phong chu, kieu chu, kich co
    
    private Color bgColor = new Color(255, 255, 255);
    
    
    
    public Paneltrai_Panelthongtin() {
        InitComponents();
    }
    
    public void InitComponents() {
        InitPanel();
        
        InitThongtin();
    }
    
    public void InitPanel() {
        this.setOpaque(true);
        
        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 400));
                
        
        //set layout
        this.setLayout(new BorderLayout());
    }
    
    public void InitThongtin() {
        
        tblThongtin = new JTable();
        
        tblTruongdulieu = new String[] {"Mã vật phẩm", "Tên vật phẩm", "Số luọng", "Đơn gia"};
        //                                                          Ma vat pham     Ten vat pham    So luong        Don gia
        
        //du lieu rac de them vao table thong tin
        Object[][] dumpdata = {
            {"bla", "bla", "bla", "bla"}
        };
        
        //them du lieu vao tablemodel
        tablemodel = new DefaultTableModel(dumpdata, tblTruongdulieu);
        
        //set du lieu cho table thong tin
        tblThongtin.setModel(tablemodel);
        
        //set font cho table
        tblThongtin.setFont(myFont);
        
        //chỉnh màu cho table
        tblThongtin.setBackground(bgColor);
        
        // Đặt màu nền cho tiêu đề cột (có thể thay đổi thành màu mong muốn)
        tblThongtin.getTableHeader().setBackground(bgColor);  
        
        // Đặt phông chữ cho tiêu đề(cột). header của tbl có in đậm
        Font myTableFont = new Font("Arial", Font.BOLD, 16);
        
        tblThongtin.getTableHeader().setFont(myTableFont);
        
        //dieu chinh kich thuoc table thong tin
        tblThongtin.setPreferredSize(new Dimension(tblThongtin.getPreferredSize().width, 400));
        
        //set do rong cua cac cot trong bang
        tblThongtin.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblThongtin.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblThongtin.getColumnModel().getColumn(2).setPreferredWidth(5);
        
        //them table vao panel
        
        nutcuon = new JScrollPane(tblThongtin);// co the khai bao () sau do dung phuong thuc
        //                                                                      setViewportView(tab
        
        nutcuon.getViewport().setBackground(bgColor);// chỉnh màu cho cả table

        //do JScrollPane da la mot container nen chi can them JScrollPane vao panel
        this.add(nutcuon);// nutcuon, khong phai tblThongtin
    }
}
