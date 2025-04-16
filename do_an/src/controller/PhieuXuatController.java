package controller;

import dao.*;

import model.PhieuXuat;
import model.ChiTietPhieuXuat;
import model.VanPhongPham;

import view.phieuxuat.*;

import view.phieuxuat.PhieuxuatPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class PhieuXuatController {
    private PhieuxuatPanel view;
    
    public PhieuXuatController(PhieuxuatPanel view ) {
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
        
        
        //thêm sự kiện cho nút xem chi tiết
        
        Panel1_Panelchucnang panelchucnang = (Panel1_Panelchucnang) panel1.getPnlChucnang();
        
        panelchucnang.getBtnNut().get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataChiTietPhieuXuat();
            }
        });
        
    }
    
    // lọc dữ liệu theo các lựa chon trong combobox(nếu các giá trị lọc không được thêm vào thì sẽ in ra danh
    // sách từ getAll() )
    private void loadData() {
        // lấy dữ liêu từ cdsl
        ArrayList<PhieuXuat> danhsachphieu = 
                (ArrayList<PhieuXuat>) PhieuXuatDAO.getInstance().getAllPhieuXuats();
        
        // dùng để lọc danh sách
        Iterator<PhieuXuat> it = danhsachphieu.iterator();
        
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
            
            //"Mã phiếu", "Người tạo"
            switch(choice) {
                // maPhieu
                case 0 -> {
                    while( it.hasNext() ) {
                        if( !it.next().getMaPhieu().toLowerCase().contains(text) )  it.remove();
                    }
                }
                // nguoiTao
                case 1 -> {
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
        
        // lấy mốc ngày(* CÓ THỂ NULL *)
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
                    JOptionPane.showMessageDialog(
                        null,        // parent
                        "Ngày từ phải bé hơn ngày đến!",// nội dung 
                        "Cảnh báo", 
                        JOptionPane.ERROR_MESSAGE
                    );
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
                JOptionPane.showMessageDialog(
                        null,        // parent
                        "Hãy chọn đủ thông tin ngày để lọc",// nội dung 
                        "Cảnh báo", 
                        JOptionPane.INFORMATION_MESSAGE
                 );
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
                    JOptionPane.showMessageDialog(
                        null,        // parent
                        "các giá trị từ và đến phải là số!",// nội dung 
                        "Cảnh báo", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
                // nếu là số nhưng tu > den
                else if( Double.parseDouble(tuGia) > Double.parseDouble(denGia) ) {
                    //báo lỗi cho người dùng
                    JOptionPane.showMessageDialog(
                        null,        // parent
                        "Giá từ phải bé hơn giá đến!!",// nội dung 
                        "Cảnh báo", // tiêu đề 
                        JOptionPane.ERROR_MESSAGE// icon
                    );
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
                JOptionPane.showMessageDialog(
                        null,        // parent
                        "Hãy chọn đủ thông tin giá để lọc",// nội dung 
                        "Cảnh báo", 
                        JOptionPane.INFORMATION_MESSAGE
                );
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
        for(int i = 0; i < danhsachphieu.size(); ++i) {
            PhieuXuat phieu = danhsachphieu.get(i);
            
            tblmodel.addRow(new Object[] {
                String.valueOf(i + 1),
                phieu.getMaPhieu(),
                phieu.getNguoiTao(),
                phieu.getThoiGianTao(),
                DoubleToDong(phieu.getTongTien())
            });
        }
        
    }

    
     // tải dữ liệu của chi tiết phiếu xuất
    public void loadDataChiTietPhieuXuat() {
        // lấy các component
        Panel3 panel3 = (Panel3) this.view.getPnlPanel3();
        
        // lấy table của panel 3
        JTable bangphieu = panel3.getTblThongtin();
        
        // lấy hàng được chọn trong bang phieu
        // trả về -1 nếu không chọn
        int hangDuocChon = bangphieu.getSelectedRow();
        
        
        // tạo JDialog để hiển thị
        ChiTietPhieuXuatDialog CTphieu = new ChiTietPhieuXuatDialog(null, "Chi tiết phiếu nhập", true);
        
        // kiểm tra nếu có chọn một hàng
        if( hangDuocChon != -1 ) {
            // lấy maPhieu tại hàng thứ "hang", cột 1
            String maPhieu = bangphieu.getValueAt(hangDuocChon, 1).toString();
            
            //lấy danh sách chi tiết phiếu nhập
            ArrayList<ChiTietPhieuXuat> danhsachChiTietPhieu = 
                    ChiTietPhieuXuatDAO.getInstance().getAllById(maPhieu);
            
            // tạo danh sách các sản phẩm nằm trong danhsachChiTietPhieu để đưa vào bảng sản phẩm 
            ArrayList<VanPhongPham> danhsachVPP = new ArrayList<>();
            
            for(var item : danhsachChiTietPhieu) {
                // truy xuất vật phẩm từ csdl trùng với maVatPham 
                VanPhongPham vpp = VanPhongPhamDAO.getInstance().getByID(item.getMaVatPham());
                
                danhsachVPP.add(vpp);
            }
            
            // hiển thị lên chi tiết phiếu nhập
            
            // PHẦN THÔNG TIN
            // -phần thông tin trước gồm: mã phiếu, người tạo, nhà cung cấp, thời gian
            // -phần thông tin sẽ lấy dữ liệu từ hàng được chọn
            ArrayList<JLabel> lblThongtin = CTphieu.getPnlContent().getLblThongtin();
            
            String[] lblHienthi =  CTphieu.getPnlContent().getLblHienthi();
            
            // bắt đầu điền thông tin
            // mã phiếu
            lblThongtin.get(0).setText(lblHienthi[0] + ": " + bangphieu.getValueAt(hangDuocChon, 1).toString());
                
            // người tạo
            lblThongtin.get(1).setText(lblHienthi[1] + ": " + bangphieu.getValueAt(hangDuocChon, 2).toString());
                
            // thời gian tạo
            lblThongtin.get(2).setText(lblHienthi[2] + ": " + bangphieu.getValueAt(hangDuocChon, 3).toString());
                
            //thời gian
            //lblThongtin.get(3).setText(lblHienthi[3] + ": " + bangphieu.getValueAt(hangDuocChon, 4).toString());
           
            
            
            
            // PHẦN BẢNG SẢN PHẨM
            // lấy table model của bảng
            DefaultTableModel tablemodel = CTphieu.getPnlContent().getTablemodel();
            
            // thêm dữ liệu vào bảng 
            for(int i = 0; i < danhsachVPP.size(); ++i) {
                VanPhongPham temp = danhsachVPP.get(i);
                
                double gia = temp.getGia();
                double soLuong = temp.getSoLuong();
                
                double thanhTien = gia * soLuong;
                
                tablemodel.addRow(new Object[] {
                    String.valueOf(i + 1),                  // STT
                    temp.getMaVatPham(),             // mã vật phẩm
                    temp.getTenVatPham(),           // tên vật phẩm
                    temp.getSoLuong(),                  // số lượng
                    DoubleToDong(gia),                  // đơn giá
                    DoubleToDong(thanhTien)      // thành tiền            
                });
            }
            
            
            
            // PHẦN TỔNG TIỀN
            JLabel lblTongtien = CTphieu.getPnlContent().getLblTongtien();
            
            // điền thông tin
            lblTongtien.setText("TỔNG TIỀN:  " + bangphieu.getValueAt(hangDuocChon, 4).toString());
            
            
            // hiển thị Dialog
            CTphieu.setVisible(true);
        }
        else {
            // báo cho người dùng chọn hàng dữ liệu
            JOptionPane.showMessageDialog(
                    CTphieu                                                                     // parent: 
                    , "Hãy chọn một hàng dữ liệu"                              // nội dung của thông báo
                    , "THÔNG BÁO"                                                       // tiêu đề của thông báo
                    , JOptionPane.INFORMATION_MESSAGE        // icon của thông báo
            );    
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
