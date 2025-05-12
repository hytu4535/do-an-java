package controller;

import dao.*;

import model.PhieuNhap;
import model.ChiTietPhieuNhap;
import model.VanPhongPham;

import view.phieunhap.*;

import view.phieunhap.PhieunhapPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import util.ExcelHandler;



public class PhieuNhapController {
    private PhieunhapPanel view;
    private ArrayList<PhieuNhap> danhsachPhieuNhap;
    
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
        
        //thêm sự kiện cho nút xem chi tiết
        
        Panel1_Panelchucnang panelchucnang = (Panel1_Panelchucnang) panel1.getPnlChucnang();
        
        panelchucnang.getBtnNut().get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataChiTietPhieuNhap();
            }
        });
        
        // thêm sự kiên cho nút sửa
        panelchucnang.getBtnNut().get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suaPhieuNhap();
            }
        });
        
        // thêm sự kiên cho nút xóa
        panelchucnang.getBtnNut().get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xoaPhieuNhap();
            }
        });
        
         // thêm sự kiên cho nút xuất excel
        panelchucnang.getBtnNut().get(3).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuatFileExcel();
            }
        });
        
         // thêm sự kiên cho nút nhập excel
        panelchucnang.getBtnNut().get(4).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nhapFileExcel();
            }
        });
        
        /*
        // thêm sự kiện cho combobox lọc
        paneltimkiem.getCbTimkiem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
        */
        
    }
    
    // lọc dữ liệu theo các lựa chon trong combobox(nếu các giá trị lọc không được thêm vào thì sẽ in ra danh
    // sách từ getAll() )
    private void loadData() {
        // lấy dữ liêu từ cdsl
        this.danhsachPhieuNhap = PhieuNhapDAO.getInstance().getAll();
        
        // dùng để lọc danh sách
        Iterator<PhieuNhap> it = this.danhsachPhieuNhap.iterator();
        
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
            it = this.danhsachPhieuNhap.iterator();
            
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
            it = this.danhsachPhieuNhap.iterator();
            
            // nếu cả hai bộ lọc được chọn
            if(tuGia.length() > 0 && denGia.length() > 0) {
                // kiểm tra nếu không phải là số
                if( !isNumeric(tuGia) || !isNumeric(denGia) ) {
                     // báo lỗi
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
        for(int i = 0; i < this.danhsachPhieuNhap.size(); ++i) {
            PhieuNhap phieu = this.danhsachPhieuNhap.get(i);
            
            tblmodel.addRow(new Object[] {
                String.valueOf(i + 1),
                phieu.getMaPhieu(),
                phieu.getMaNhaCungCap(),
                phieu.getNguoiTao(),
                phieu.getThoiGianTao(),
                DoubleToDong(phieu.getTongTien())
            });
        }
        
    }
    
    // tải dữ liệu của chi tiết phiếu nhập
    public void loadDataChiTietPhieuNhap() {
        // lấy các component
        Panel3 panel3 = (Panel3) this.view.getPnlPanel3();
        
        // lấy table của panel 3
        JTable bangphieu = panel3.getTblThongtin();
        
        // lấy hàng được chọn trong bang phieu
        // trả về -1 nếu không chọn
        int hangDuocChon = bangphieu.getSelectedRow();
        
        
        // tạo JDialog để hiển thị
        ChiTietPhieuNhapDialog CTphieu = new ChiTietPhieuNhapDialog(null, "Chi tiết phiếu nhập", true);
        
        // kiểm tra nếu có chọn một hàng
        if( hangDuocChon != -1 ) {
            // lấy maPhieu tại hàng thứ "hang", cột 1
            String maPhieu = bangphieu.getValueAt(hangDuocChon, 1).toString();
            
            //lấy danh sách chi tiết phiếu nhập
            ArrayList<ChiTietPhieuNhap> danhsachChiTietPhieu = 
                    ChiTietPhieuNhapDAO.getInstance().getAllById(maPhieu);
            
        
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
            lblThongtin.get(1).setText(lblHienthi[1] + ": " + bangphieu.getValueAt(hangDuocChon, 3).toString());
                
            // nhà cung cấp
            lblThongtin.get(2).setText(lblHienthi[2] + ": " + bangphieu.getValueAt(hangDuocChon, 2).toString());
                
            //thời gian
            lblThongtin.get(3).setText(lblHienthi[3] + ": " + bangphieu.getValueAt(hangDuocChon, 4).toString());
           
            
            
            
            // PHẦN BẢNG SẢN PHẨM
            // lấy table model của bảng
            DefaultTableModel tablemodel = CTphieu.getPnlContent().getTablemodel();
            
            // thêm dữ liệu vào bảng 
            for (int i = 0; i < danhsachChiTietPhieu.size(); ++i) {
                // các thông tin sản phẩm sẽ được lấy từ vpp nhưng số lượng, thành tiền thì là từ chiTiet
                ChiTietPhieuNhap chiTiet = danhsachChiTietPhieu.get(i);
                VanPhongPham vpp = VanPhongPhamDAO.getInstance().getByID(chiTiet.getMaVatPham());

                double gia = chiTiet.getDonGia();
                int soLuong = chiTiet.getSoLuong();
                double thanhTien = gia * soLuong;

                tablemodel.addRow(new Object[]{
                    String.valueOf(i + 1),
                    vpp.getMaVatPham(),
                    vpp.getTenVatPham(),
                    soLuong,
                    DoubleToDong(gia),
                    DoubleToDong(thanhTien)
                });
            }

            
            
            
            // PHẦN TỔNG TIỀN
            JLabel lblTongtien = CTphieu.getPnlContent().getLblTongtien();
            
            // điền thông tin
            lblTongtien.setText("TỔNG TIỀN:  " + bangphieu.getValueAt(hangDuocChon, 5).toString());
            
            
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
    
    // sửa phiêu nhập
    public void suaPhieuNhap() {
        // lấy các component
        Panel3 panel3 = (Panel3) this.view.getPnlPanel3();
        
        // lấy table của panel 3
        JTable bangphieu = panel3.getTblThongtin();
        
        // lấy hàng được chọn trong bang phieu
        // trả về -1 nếu không chọn
        int hangDuocChon = bangphieu.getSelectedRow();
        
        
        // kiểm tra nếu có chọn một hàng
        if( hangDuocChon != -1 ) {
            // lấy maPhieu tại hàng thứ "hang", cột 1
            String maPhieu = bangphieu.getValueAt(hangDuocChon, 1).toString();
            
            // lấy ra đối tượng phiếu nhập được chọn
            PhieuNhap phieuDuocChon = PhieuNhapDAO.getInstance().getByID(maPhieu);
            
            System.out.println(phieuDuocChon.getMaPhieu());
            
            // yêu cầu người dùng nhập các thông tin để sửa phiếu
            SuaPhieuNhapDialog popup = new SuaPhieuNhapDialog(null, phieuDuocChon);
            
            // trong popup đã có hàm để kiểm tra dữ liệu rồi
            // lấy ra đối tượng chứa dữ liệu thay đổi
            PhieuNhap phieuMoiSua = popup.getPhieuNhap();
   
            System.out.println(phieuMoiSua.getMaPhieu());
            
            // cập nhật phiếu nhập vào csdl
            PhieuNhapDAO.getInstance().update(phieuMoiSua);
            
        }
        else {
            // báo cho người dùng chọn hàng dữ liệu
            JOptionPane.showMessageDialog(
                    null                                                                     // parent: 
                    , "Hãy chọn một hàng dữ liệu"                              // nội dung của thông báo
                    , "THÔNG BÁO"                                                       // tiêu đề của thông báo
                    , JOptionPane.INFORMATION_MESSAGE        // icon của thông báo
            );    
        }
        
        // cập nhật lại bảng phiếu nhập
        loadData();
    }
    
    // xóa phiếu nhập
    public void xoaPhieuNhap() {
        // lấy các component
        Panel3 panel3 = (Panel3) this.view.getPnlPanel3();
        
        // lấy table của panel 3
        JTable bangphieu = panel3.getTblThongtin();
        
        // lấy hàng được chọn trong bang phieu
        // trả về -1 nếu không chọn
        int hangDuocChon = bangphieu.getSelectedRow();
        
        
        // kiểm tra nếu có chọn một hàng
        if( hangDuocChon != -1 ) {
            // lấy maPhieu tại hàng thứ "hang", cột 1
            String maPhieu = bangphieu.getValueAt(hangDuocChon, 1).toString();
            
            // lấy ra đối tượng phiếu nhập được chọn
            PhieuNhap phieuDuocChon = PhieuNhapDAO.getInstance().getByID(maPhieu);
            
            System.out.println(phieuDuocChon.getMaPhieu());
            
            // xóa các chi tiết phiếu nhập có liên quan đến phiếu nhập cần xóa trước rồi mới xóa phiếu nhập
            
            // lấy danh sách chi tiết phiếu nhập có liên quan tới phiếu nhập
            ArrayList<ChiTietPhieuNhap> danhsachCTPN = 
                    ChiTietPhieuNhapDAO.getInstance().getAllById(maPhieu);
            
            // xóa
            for(var phieu : danhsachCTPN) {
                ChiTietPhieuNhapDAO.getInstance().delete(phieu);
            }
            
            // xóa phiếu nhập
            PhieuNhapDAO.getInstance().delete(phieuDuocChon);
            
        }
        else {
            // báo cho người dùng chọn hàng dữ liệu
            JOptionPane.showMessageDialog(
                    null                                                                     // parent: 
                    , "Hãy chọn một hàng dữ liệu"                              // nội dung của thông báo
                    , "THÔNG BÁO"                                                       // tiêu đề của thông báo
                    , JOptionPane.INFORMATION_MESSAGE        // icon của thông báo
            );    
        }
        
        // cập nhật lại bảng phiếu nhập
        loadData();
    }
    
    // xuất file excel
    public void xuatFileExcel() {
        // tạo một gui để hiển thị thư mục cho người dùng chọn để lưu
        JFileChooser chooser = new JFileChooser();
        
        // đặt title cho gui
        chooser.setDialogTitle("Hãy chọn thư mục lưu file");
        
        // tạo tên file mặc định khi lưu
        chooser.setSelectedFile(new java.io.File("PhieuNhap.xlsx"));
        
        // kiểm tra nếu người dùng có nhấn nút save thì sẽ trả về 0
        if (chooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            // lấy đường dẫn file
            String filePath = chooser.getSelectedFile().getPath();
            // kiểm tra nếu đường dẫn không kết thúc với đuôi như dưới thì thêm vào
            if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx";
            }
            
            // xuất file
            ArrayList<PhieuNhap> danhSachPhieu = PhieuNhapDAO.getInstance().getAll();
            ArrayList<ChiTietPhieuNhap> danhSachCTPhieu = ChiTietPhieuNhapDAO.getInstance().getAll();
            
            ExcelHandler.getInstance().XuatFilePhieuNhapExcel(danhSachPhieu, danhSachCTPhieu, filePath);
        }
    }
    
    
    // nhập file excel
    public void nhapFileExcel() {
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setDialogTitle("Chọn file Excel");
        
        // chỉ cho nhập file excel
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx"));

        int result = fileChooser.showOpenDialog(null);
        
        // nếu người dùng có chọn
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            
            String filePath = selectedFile.getAbsolutePath();

            // Gọi hàm đọc file Excel
            ArrayList<PhieuNhap> danhSach = ExcelHandler.getInstance().nhapFileExcelPhieuNhap(filePath);

            ArrayList<ChiTietPhieuNhap> danhSachCT = 
                    ExcelHandler.getInstance().nhapFileExcelChiTietPhieuNhap(filePath);
            
            // bắt đầu nhập
            // B1: xóa dữ liệu ở bảng chi tiết phiếu rồi tới bảng phiếu
            if(ChiTietPhieuNhapDAO.getInstance().deleteAll()) {
                if(PhieuNhapDAO.getInstance().deleteAll()) {
                    // B2: thêm bảng phiếu nhập trước rồi tới bảng chi tiết phiếu
                    
                    // phiếu nhập
                    for(var phieu : danhSach) {
                        PhieuNhapDAO.getInstance().insert(phieu);
                    }
                    
                    // chi tiết phiếu nhập
                    for(var phieu : danhSachCT) {
                        ChiTietPhieuNhapDAO.getInstance().insert(phieu);
                    }
                    
                    // làm mới lại bảng hiển thị
                    loadData();
                    
                    // thông báo đã nhập xong
                    JOptionPane.showMessageDialog(
                        null,        // parent
                        "Nhập fil Excel hoàn tất!",// nội dung 
                        "Cảnh báo", 
                        JOptionPane.INFORMATION_MESSAGE
                    );  
                }
                else {
                  JOptionPane.showMessageDialog(
                        null,        // parent
                        "Có lỗi trong lúc ghi đè bảng chi tiết phiếu nhập",// nội dung 
                        "Cảnh báo", 
                        JOptionPane.ERROR_MESSAGE
                    );  
                }
            }
            else {
                JOptionPane.showMessageDialog(
                        null,        // parent
                        "Có lỗi trong lúc ghi đè bảng phiếu nhập",// nội dung 
                        "Cảnh báo", 
                        JOptionPane.ERROR_MESSAGE
                    );
            }
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


// mở output window = ctrl + 4