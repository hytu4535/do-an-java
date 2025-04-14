package controller;

import dao.*;

import model.PhieuNhap;

import view.phieunhap.*;

import view.phieunhap.PhieunhapPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class PhieuNhapController {
    private PhieunhapPanel view;
    
    public PhieuNhapController(PhieunhapPanel view ) {
        this.view = view;
        
        Action();
    }
    
    // thực hiện các hành động logic, ....
    public void Action() {
        // mặc định sẽ tải dữ liệu lên
        loadData();
        
        // thêm sự kiên cho nút làm mới khi nhấn
        Panel1 panel1 = (Panel1) this.view.getPnlPanel1();
        
        Panel1_Paneltimkiem paneltimkiem = (Panel1_Paneltimkiem) panel1.getPnlTimkiem();
        
        paneltimkiem.getBtnLammoi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                loadData();
            }
        });
        
    }
    
    // lọc dữ liệu theo các lựa chon trong combobox(nếu các giá trị lọc không được thêm vào thì sẽ in ra danh
    // sách từ getAll() )
    private void loadData() {
        // lấy dữ liêu từ cdsl
        ArrayList<PhieuNhap> danhsachphieu = PhieuNhapDAO.getInstance().getAll();
        
        // dùng để lọc danh sách
        Iterator<PhieuNhap> it = danhsachphieu.iterator();
        
        // LỌC DỮ LIỆU THEO PHẦN PANEL1
        // lấy các component ở panel1_timkiem
        Panel1 panel1 = (Panel1) this.view.getPnlPanel1();
        
        Panel1_Paneltimkiem paneltimkiem = (Panel1_Paneltimkiem) panel1.getPnlTimkiem();
        
        // TextField lấy chữ thường để tìm hết
        String text = paneltimkiem.getTxtfTimkiem().getText().toLowerCase();
        
        // ComboBox
        int choice = paneltimkiem.getCbTimkiem().getSelectedIndex();
        
        // kiểm tra điều kiện lọc trên mảng danhsachphieu
        // nếu phần textfield không trống
        if(text.length() != 0) {
            // bắt đầu lọc danh sách
            
            //"Mã phiếu", "Nhà cung cấp", "Người tạo"
            switch(choice) {
                // maPhieu
                case 0 -> {
                    while( it.hasNext() ) {
                        if( !it.next().getMaPhieu().toLowerCase().contains(text) )  it.remove();
                    }
                }
                // nhaCungCap
                case 1 -> {
                    while( it.hasNext() ) {
                        if( !it.next().getMaNhaCungCap().toLowerCase().contains(text) )  it.remove();
                    }
                }
                // nguoiTao
                case 2 -> {
                    while( it.hasNext() ) {
                        if( !it.next().getNguoiTao().toLowerCase().contains(text) )  it.remove();
                    }
                }
            }
        }
        //################################
        
        // LỌC DỮ LIỆU THEO PHẦN PANEL2
        // LỌC THEO NGÀY TRÊN MẢNG danhsachphieu
        // lấy các components: ngày được lấy từ JDateChooser thuộc kiểu Date
        Panel2 panel2 = (Panel2) this.view.getPnlPanel2();
        
        Panel2_Panellocngay panellocngay = (Panel2_Panellocngay) panel2.getPnlLocngay();
        
        // lấy mốc ngày(* CÓ THỂ NULL*)
        Date tuNgay = panellocngay.getDcTu().getDate();
        
        Date denNgay = panellocngay.getDcDen().getDate();
        
        
        // nếu một trong hai điều kiện lọc không bị trống => có sử dụng bộ lọc ngày
        if(tuNgay != null || denNgay != null) {
            it = danhsachphieu.iterator();
            
            // nếu cả hai bộ lọc đều được chọn
            if( tuNgay != null && denNgay != null) {
                // kiểm tra nếu tuNgay lớn hơn deNgay
                if( tuNgay.getTime() > denNgay.getTime() ) {
                    // check lỗi
                }
                else{
                    // nếu ngày của phiếu không nằm trong tầm [tuNgay, denNgay]
                    while( it.hasNext() ) {
                    long phieuNgay = it.next().getThoiGianTao().getTime();

                    if( !(tuNgay.getTime() <= phieuNgay && phieuNgay <= denNgay.getTime()) ) it.remove();
                    }
                }
            }
            else {
                // thông báo người dùng phải chọn đủ
            }
        }
        //#############################
       
        // LỌC THEO GIÁ 
        // lấy các component
        Panel2_Panellocgia panellocgia = (Panel2_Panellocgia) panel2.getPnlLocgia();
        
        // lấy giá trị lọc 
        String tuGia = panellocgia.getTxtfTu().getText();
        
        String denGia = panellocgia.getTxtfDen().getText();
        
        // nếu có dùng bộ lọc và số
        if(tuGia.length() > 0 || denGia.length() > 0) {
            it = danhsachphieu.iterator();
            
            // nếu cả hai bộ lọc được chọn
            if(tuGia.length() > 0 && denGia.length() > 0) {
                // kiểm tra nếu không phải là số
                if( !isNumeric(tuGia) || !isNumeric(denGia) ) {
                    // báo lỗi cho người dùng
                }
                // nếu là số nhưng tu > den
                else if( Double.parseDouble(tuGia) > Double.parseDouble(denGia) ) {
                    //báo lỗi
                }
                // đủ điều kiện
                else {
                    while(it.hasNext()) {

                        double tu = Double.parseDouble(tuGia);
                        double den = Double.parseDouble(denGia);
                        double tongTien = it.next().getTongTien();
                                              
                        // nếu tongTien không nằm trong bộ lọc 
                        if( !( tu <= tongTien && tongTien <= den) )

                            it.remove();
                    }
                }
            }
            else {
                //thông báo phải nhập đủ thông tin để dùng
            }
        }
        //############################
            
        // tải dữ liệu lên bảng
        // lấy Panel chứa bảng hiển thị 
        // gọi đúng Panel chưa các component cần tìm và ép kiểu nó
        Panel3 panel = (Panel3) this.view.getPnlPanel3();
        
        DefaultTableModel tblmodel = panel.getTablemodel();
        
        // xóa các hàng trong bảng
        tblmodel.setRowCount(0);
        
        // thêm hàng vào bảng
        // tạo mảng object để chưa dữ liệu
        Object[][] dulieu = new Object[danhsachphieu.size()][6];// [số hàng dữ liệu][các cột dữ liệu]
        
        for(int i = 0; i < danhsachphieu.size(); ++i) {
            // lấy phiếu nhập tại vị trí i
            PhieuNhap phieu = danhsachphieu.get(i);
            
            // STT
            dulieu[i][0] = String.valueOf(i + 1);
            
            // maPhieu
            dulieu[i][1] = phieu.getMaPhieu();
            
            // nhaCungCap(tạm thời là mã)
            dulieu[i][2] = phieu.getMaNhaCungCap();
            
            // nguoiTao
            dulieu[i][3] = phieu.getNguoiTao();
            
            // thoiGianTao
            dulieu[i][4] = phieu.getThoiGianTao();
            
            // tongTien
            dulieu[i][5] = DoubleToDong(phieu.getTongTien());
        }
        
        // đưa dữ liệu vào bảng
        for(Object[] hangdulieu : dulieu) {
            tblmodel.addRow(hangdulieu);
        }
        
    }

    
    
  
    // hàm để kiểm tra xem chuỗi có  phải số không(kiểu dữ liệu double)
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        
        // thử chuyển chuỗi thành số, nếu không được thì parse... sẽ có lỗi
        try {
            double number = Double.parseDouble(strNum);
        } 
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    // hàm để chuyển double thành chuỗi 
    public String DoubleToDong(double number) {
    DecimalFormat Format = new DecimalFormat("###,###,###");
    
    // làm tròn xuống nếu có 
    //Format.setRoundingMode(RoundingMode.DOWN);
    
    //trả về chuỗi và cộng thêm "đ"
    return Format.format((long) number) + " đ";
}

}
