package controller;

import dao.ChiTietPhieuNhapDAO;
import dao.NhaCungCapDAO;
import dao.VanPhongPhamDAO;
import dao.PhieuNhapDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.NhaCungCap;
import model.PhieuNhap;
import model.VanPhongPham;
import model.ChiTietPhieuNhap;

import view.nhaphang.NhaphangPanel;
import view.nhaphang.PopupNhapSoLuong;




public class NhapHangController {
    private NhaphangPanel view;
    
    // hai mảng này để làm việc và hiển thị lên 2 table bên trái và bên phải
    private ArrayList<VanPhongPham> danhsachVPPTrai = 
            (ArrayList<VanPhongPham>) VanPhongPhamDAO.getInstance().getAllVanPhongPhams();
    
    private ArrayList<VanPhongPham> danhsachVPPPhai =
            new ArrayList<>();
    
    
    
    public NhapHangController(NhaphangPanel view) {
        this.view = view;
        
        themLuaChon();
        
        Action();
    }
    
    
    public void Action() {
        // tự động đua thông tin lên bảng bên trái
        loadData(this.danhsachVPPTrai, 1);

        // thêm sự kiện cho nút tìm kiếm
        this.view.getPnlPaneltrai().getPnlPaneltimkiem().getBtnTimkiem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                loadDataBenTrai();
            }
        });
        
        // thêm sự kiện cho nút thêm bên trái
        this.view.getPnlPaneltrai().getPnlPanelluachon().getBtnThem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                chuyenTraiSangPhai();
            }
        });
        
        // thêm sự kiện cho nút xóa sản phẩm bên phải
        this.view.getPnlPanelphai().getPnlPanelluachon().getBtnNut().get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                chuyenPhaiVeTrai();
            }
        });
        
        // thêm sự kiện cho nút sửa số lượng bên phải
        this.view.getPnlPanelphai().getPnlPanelluachon().getBtnNut().get(0).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                suaSoLuong();
            }
        });
        
        // thêm sự kiện cho nút thêm phiếu nhập bên phải
        this.view.getPnlPanelphai().getPnlPanelluachon().getBtnNut().get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                themPhieuNhap();
            }
        });
        
        // thêm sự kiện cho combobox nhà cung cấp bên trái
        this.view.getPnlPaneltrai().getPnlPaneltimkiem().getCbNhacungcap().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent I) {
                ArrayList<VanPhongPham> danhSach = locTheoBoLoc();
                
                loadData(danhSach, 1);
            }
        });
    }
    
    
    // tải dữ liệu cho bảng bên trái
    public void loadDataBenTrai() {
            // bảng này có bộ lọc tìm kiếm
            // thêm các nhà cung cấp cho combobox ở phần tìm kiếm bên phải
            
            // lấy bộ lọc
            JComboBox boloc = this.view.getPnlPaneltrai().getPnlPaneltimkiem().getCbNhacungcap();
            
            // lấy giá trị được chọn 
            String nhaCungCap = boloc.getSelectedItem().toString();
            
            
            // lấy dữ liệu lọc
            String timkiem = this.view.getPnlPaneltrai().getPnlPaneltimkiem().getTxtfTimkiem().getText().toLowerCase();
            
            // danhsach để lọc ra dữ liệu nếu có
            ArrayList<VanPhongPham> danhsach = new ArrayList<>();
            
            // nếu có dùng bộ timkiem
           if( timkiem.length() > 0) {
                // lọc
                System.out.println(this.danhsachVPPTrai.size());
                for(var item : this.danhsachVPPTrai) {
                // nếu hàng có điểm giống với điều kiện lọc( phần tìm kiếm và phần combobox )
                String maVatPham = item.getMaVatPham().toLowerCase();
                
                String tenVatPham = item.getTenVatPham().toLowerCase();
                
                String soLuong = String.valueOf(item.getSoLuong()).toLowerCase();
                
                String donGia = String.valueOf(item.getGia()).toLowerCase();
                
                String thuongHieu = item.getThuongHieu();
                
                    if( (maVatPham.contains(timkiem) || tenVatPham.contains(timkiem) || 
                            soLuong.contains(timkiem) || donGia.contains(timkiem) )  &&
                            thuongHieu.equals(boloc.getSelectedItem().toString()) ) {
                        
                        danhsach.add(item);
                    }
                }
           }
           // nếu không dùng tìm kiếm thì sẽ lọc mặc định theo combobox
           else {
               for(var item : this.danhsachVPPTrai) {
                   if( item.getThuongHieu().equals(boloc.getSelectedItem().toString()) ) {
                       danhsach.add(item);
                   }
               }
           }
           
           //in danhsach ra bảng
           loadData(danhsach, 1);
    }
    //#########################
    
    // phương thức chuyển dữ liệu từ bảng bên trái sang bên phải
    public void chuyenTraiSangPhai() {
        // kiểm tra xem người dùng có chọn sản phẩm chưa
        int hangDuocChon = 
                this.view.getPnlPaneltrai().getPnlPanelthongtin().getTblThongtin().getSelectedRow();
        

        // nếu có chọn 
        if( hangDuocChon != -1 ) {
            
            // kiểm tra nếu có nhập số lượng để chuyển
            if( this.view.getPnlPaneltrai().getPnlPanelluachon().getTxtfHienthi().toString().length() > 0 ) {
                
                // kiểm tra nếu chuỗi nhập vào là số
                String soLuongNhapTextField = 
                        this.view.getPnlPaneltrai().getPnlPanelluachon().getTxtfHienthi().getText();
                
                System.out.println(soLuongNhapTextField);
                
                if( isNumeric(soLuongNhapTextField) ) {
                    
                    // lấy bảng chứa hàng hóa
                    JTable table = this.view.getPnlPaneltrai().getPnlPanelthongtin().getTblThongtin();

                    // lấy số lượng nhập
                    int soLuongNhap = Integer.parseInt(soLuongNhapTextField);
                
                    // kiểm tra nếu số lượng nhập phải lớn hơn 0
                    if( soLuongNhap > 0) {
                        // chuyển vật phẩm sang cho table bên phải và cập nhật lại danhsachVPPPhai
                        
                        // lấy ra văn phòng phẩm trong table trái
                        VanPhongPham temp = null;
                        for(var item : this.danhsachVPPTrai) {
                            if( item.getMaVatPham().equals(table.getValueAt(hangDuocChon, 0)) ) {
                                temp = item;
                                break;
                            }
                        }
                        
                        VanPhongPham kiemtra = null;
                        
                        // kiểm tra nếu table phải đã có cùng loại sản phẩm bên table trái thì cho vào cùng hàng
                        for(var item : this.danhsachVPPPhai) {
                            if( item.getMaVatPham().equals(temp.getMaVatPham()) ) {
                                kiemtra = item;
                                break;
                            }
                        }
                        
                        // nếu có tồn tại
                        if( kiemtra != null){
                            kiemtra.setSoLuong( (kiemtra.getSoLuong() + soLuongNhap) );
                        }
                        // nếu chưa có thì
                        else {
                            // chuyển đối tượng temp sang cho ArrayList bên phải
                            this.danhsachVPPPhai.add(new VanPhongPham(
                                temp.getMaVatPham(),
                                temp.getTenVatPham(),
                                
                                soLuongNhap,
                                
                                temp.getLoaiVatPham(),
                                temp.getGia(),
                                temp.getThuongHieu(),
                                temp.getChatLieu(),
                                temp.getDoDay(),
                                temp.getMoTa(),
                                temp.getXuatXu(),
                                temp.getTrangThai()
                            ));
                        }
                        
                        
                        // làm mới lại hai table phải
                        loadData(this.danhsachVPPPhai, 0);
                    }
                    // nếu vượt quá
                    else {
                    // báo cho người dùng chọn hàng dữ liệu
                        JOptionPane.showMessageDialog(
                            null,                                                                     // parent: 
                            "Số lượng nhập vào phải  lớn hơn 0!", // nội dung của thông báo
                            "THÔNG BÁO",                                                 // tiêu đề của thông báo
                            JOptionPane.INFORMATION_MESSAGE        // icon của thông báo
                        );    
                    }
                    //####################
                }
                // nếu không phải là số
                else {
                    // báo lỗi
                    JOptionPane.showMessageDialog(
                        null,        // parent
                        "Giá trị nhập vào  phải là số!",// nội dung 
                        "Cảnh báo", 
                        JOptionPane.INFORMATION_MESSAGE
                    );  
                }
                //##############
            }
            // nếu chưa nhập thì báo người dùng
            else {
                // báo cho người dùng chọn hàng dữ liệu
                JOptionPane.showMessageDialog(
                    null,                                                                     // parent: 
                    "Hãy nhập số lượng hàng!",                           // nội dung của thông báo
                    "THÔNG BÁO",                                                 // tiêu đề của thông báo
                    JOptionPane.INFORMATION_MESSAGE        // icon của thông báo
                );    
            }
            //#############
        }
        // nếu chưa chọn
        else {
            // báo cho người dùng chọn hàng dữ liệu
            JOptionPane.showMessageDialog(
                    null,                                                                     // parent: 
                    "Hãy chọn một hàng dữ liệu",                           // nội dung của thông báo
                    "THÔNG BÁO",                                                 // tiêu đề của thông báo
                    JOptionPane.INFORMATION_MESSAGE        // icon của thông báo
            );    
        }
    }
    
    
    // phương thức chuyển dữ liệu( xóa sản phẩm) từ bảng bên trái về bên phải
    public void chuyenPhaiVeTrai() {
        // kiểm tra xem người dùng có chọn sản phẩm chưa
        JTable tablePhai = 
                this.view.getPnlPanelphai().getPnlPanelthongtin().getTblThongtin();
        
        JTable tableTrai = 
                this.view.getPnlPaneltrai().getPnlPanelthongtin().getTblThongtin();
        
        // hàng được chọn bên phải
        int hangDuocChon = tablePhai.getSelectedRow();
        
        // nếu có chọn 
        if( hangDuocChon != -1 ) {
            // lấy maVatPham của hàng được chon ở bên phải
            String maVatPhamPhai = tablePhai.getValueAt(hangDuocChon, 1).toString();
            
            // biến để lưu số lượng hàng đưuọc chọn bên phải
            int soLuongPhai = Integer.parseInt(tablePhai.getValueAt(hangDuocChon, 3).toString());
            
            // B2: xóa đối tượng được chọn trong danhsachVPPPhai
            for(int i = 0; i < this.danhsachVPPPhai.size(); ++i) {
                if( this.danhsachVPPPhai.get(i).getMaVatPham().equals(maVatPhamPhai) ) {
                    this.danhsachVPPPhai.remove(i);
                    break;
                }
            }
            
            // B3: cập nhật lại table phải
            // table phải
            loadData(this.danhsachVPPPhai, 0);
        }
        // nếu chưa chọn
        else {
            // báo cho người dùng chọn hàng dữ liệu
            JOptionPane.showMessageDialog(
                    null,                                                                     // parent: 
                    "Hãy chọn một hàng dữ liệu",                           // nội dung của thông báo
                    "THÔNG BÁO",                                                 // tiêu đề của thông báo
                    JOptionPane.INFORMATION_MESSAGE        // icon của thông báo
            );    
        }
    }
    //###########################
    
    
    // phương thức sửa số lượng cho vật phẩm ở table bên phải
    public void suaSoLuong() {
        // kiểm tra xem người dùng có chọn sản phẩm chưa
        JTable tablePhai = 
                this.view.getPnlPanelphai().getPnlPanelthongtin().getTblThongtin();
        
        JTable tableTrai = 
                this.view.getPnlPaneltrai().getPnlPanelthongtin().getTblThongtin();
        
        // hàng được chọn bên phải
        int hangDuocChon = tablePhai.getSelectedRow();
        
        // nếu có chọn 
        if( hangDuocChon != -1 ) {
            // lấy maVatPham của hàng được chon ở bên phải
            String maVatPhamPhai = tablePhai.getValueAt(hangDuocChon, 1).toString();
            
            // biến để lưu số lượng hàng ban đầu được chọn bên phải
            int soLuongHienTai = Integer.parseInt(tablePhai.getValueAt(hangDuocChon, 3).toString());
            
            // lấy số lượng nhập từ người dùng
            PopupNhapSoLuong pop = new PopupNhapSoLuong(null, "", true, soLuongHienTai);
            
            int soLuongMoi = pop.getSoLuongNhap();
            
            // kiểm tra nếu nhấn nút hủy => giá trị của soLuongMoi = -1
            if( soLuongMoi == - 1 ) {
                // ngắt hành động
                return;
            }
            
            // B1: cập nhật vào danhsachVPPPhai
            for(int i = 0; i < this.danhsachVPPPhai.size(); ++i) {
                if( this.danhsachVPPPhai.get(i).getMaVatPham().equals(maVatPhamPhai)) {
                     // nếu số lượng nhập mới là 0 thì xóa luôn hàng đấy
                     if( soLuongMoi == 0 ) {
                         this.danhsachVPPPhai.remove(i);
                     }
                     // nếu không phải
                     else {
                         this.danhsachVPPPhai.get(i).setSoLuong(soLuongMoi);
                     }
                     
                     break;
                }
            }
          
            // B2: cập nhật lại table phải
            loadData(this.danhsachVPPPhai, 0);
        }
        // nếu chưa chọn
        else {
            // báo cho người dùng chọn hàng dữ liệu
            JOptionPane.showMessageDialog(
                    null,                                                                     // parent: 
                    "Hãy chọn một hàng dữ liệu",                           // nội dung của thông báo
                    "THÔNG BÁO",                                                 // tiêu đề của thông báo
                    JOptionPane.INFORMATION_MESSAGE        // icon của thông báo
            );    
        }
    }
    
    
    // phương thức thêm phiếu nhập mới
    public void themPhieuNhap() {
        // kiểm tra xem nếu có tồ tại sản phầm ở mảng VPPPhai không
        if(this.danhsachVPPPhai.size() == 0) {
            // báo cho người dùng phải thêm dữ liệu
            JOptionPane.showMessageDialog(
                    null,                                                                     // parent: 
                    "Không có sản phẩm nào được chọn để thêm",                           // nội dung của thông báo
                    "THÔNG BÁO",                                                 // tiêu đề của thông báo
                    JOptionPane.INFORMATION_MESSAGE        // icon của thông báo
            );    
            
            return;
        }
        
        // tạo một phiếu nhập trước
        PhieuNhap phieuMoi = new PhieuNhap();
        
        // thêm các thông tin của phiếu
        // maPhieuNhap
        String maPhieuNhap = maPhieuNhapMoi() ;
        
        phieuMoi.setMaPhieu(maPhieuNhap);
        
        // thoiGianTao
        Timestamp thoiGian = Timestamp.valueOf(LocalDateTime.now());
        
        phieuMoi.setThoiGianTao(thoiGian);
        
        // lấy người tạo từ phần login 
        phieuMoi.setNguoiTao(NguoiDungController.getCurrentUsername());
        
        // maNhaCungCap
        phieuMoi.setMaNhaCungCap(
                this.view.getPnlPaneltrai().getPnlPaneltimkiem().getCbNhacungcap().getSelectedItem().toString());
        
        // tongTien. Sử dụng mảng VPPPhai để tính
        double tongTien = 0;
        
        for(var item : this.danhsachVPPPhai) {
            tongTien += ( item.getGia() * item.getSoLuong() );
        }
        
        phieuMoi.setTongTien(tongTien);
        
        // đưa vào vào csdl
        PhieuNhapDAO.getInstance().insert(phieuMoi);
        
        // Sau khi thêm phiếu nhập, ta thêm chi tiết phiếu nhập
        for(var item : this.danhsachVPPPhai) {
            ChiTietPhieuNhap CTPhieuMoi = new ChiTietPhieuNhap();
            
            // thêm thông tin cho phiếu
            // maPhieu
            CTPhieuMoi.setMaPhieu(maPhieuNhap);
            
            // maVatPham
            CTPhieuMoi.setMaVatPham( item.getMaVatPham() );
            
            // soLuong
            CTPhieuMoi.setSoLuong( item.getSoLuong() );
            
            // đơn giá (gia)
            CTPhieuMoi.setDonGia( item.getGia() );
            
            // thêm vào csdl
            ChiTietPhieuNhapDAO.getInstance().insert(CTPhieuMoi);
        }
        
        // Cập nhật thông tin cua các sản phẩm bị ảnh hưởng
        // B1: lấy danh sách văn phòng phẩm hiện tại
        ArrayList<VanPhongPham> danhsachVPPMoi = 
                (ArrayList<VanPhongPham>) VanPhongPhamDAO.getInstance().getAllVanPhongPhams();
        
        // B2: cập nhật danh sách mới vào csdl
        for(var item : danhsachVPPMoi) {
            // kiểm tra xem nếu có VanPhongPham nào trùng maVatPham bên danhsachVPPPhai thì cập nhật
            // thay đổi lên danhsachVPPMoi và cập nhật lên csdl
            
            // * dùng danhsachVPPPhai vì danh sách đó đã lưu các thay đổi rồi *
            for(var sanPham : this.danhsachVPPPhai) {
                if( item.getMaVatPham().equals(sanPham.getMaVatPham()) ) {
                    // cập nhật số lượng sau khi thêm phiếu nhập và chi tiết phiếu nhập
                    item.setSoLuong( (item.getSoLuong() + sanPham.getSoLuong()) );
                    
                     VanPhongPhamDAO.getInstance().update(item);
                     
                    break;
                }
            }
        }
    
        
        // làm mới panel
        // table trái
        // làm mới dữ liệu danhsachVPPTrai
        this.danhsachVPPTrai = locTheoBoLoc();
        
        loadData(this.danhsachVPPTrai, 1);
        
        // table phải
        // xóa hết dữ liệu danhsachVPPPhai
        this.danhsachVPPPhai.clear();
        
        loadData(this.danhsachVPPPhai, 0);
    }
    
    // thêm lữa chọn cho combobox
    public void themLuaChon() {
         // thêm các lựa chọn là mã nhà cung cấp cho combobox
        ArrayList<NhaCungCap> danhsachNCC = 
                (ArrayList<NhaCungCap>) NhaCungCapDAO.getInstance().getAllNhaCungCaps();
        
         this.view.getPnlPaneltrai().getPnlPaneltimkiem().getCbNhacungcap().addItem("--Tất cả--");
        
        for(var item : danhsachNCC) {
            this.view.getPnlPaneltrai().getPnlPaneltimkiem().getCbNhacungcap().addItem(
                item.getMaNhaCungCap());
        }
        
    }
    
    // trả về mảng theo bộ lọc
    public ArrayList<VanPhongPham> locTheoBoLoc() {
        // lấy combobox nhà cung cấp
        JComboBox boloc = this.view.getPnlPaneltrai().getPnlPaneltimkiem().getCbNhacungcap();
        
        String ketQuaLoc = boloc.getSelectedItem().toString();
        
        ArrayList<VanPhongPham> ketqua = null;
        
        // kiểm tra ket qua lọc
        // nếu lấy tất cả 
        if(boloc.getSelectedIndex() == 0) {
            ketqua = (ArrayList<VanPhongPham>) VanPhongPhamDAO.getInstance().getAllSanPhams();
        }
        // nếu có chọn 
        else {
            ketqua = VanPhongPhamDAO.getInstance().getAllVanPhongPhamByNhaCungCap(ketQuaLoc);
        }
        
        return ketqua;
    }
    
    
    
    
    // hàm để hỗ trợ việc hiển thị dữ liêu bảng: bảng trái = 1, bảng phải = 0;
    public void loadData(ArrayList<VanPhongPham> dulieu, int luachon) {
        
        // nếu là bảng bên trái
        if( luachon == 1) {
            // lấy table model bên trái
            DefaultTableModel tableModelTrai = 
                   this.view.getPnlPaneltrai().getPnlPanelthongtin().getTablemodel();
            
            // làm mới table
            tableModelTrai.setRowCount(0);
            
           //thêm vào bảng bên trái
           for(var item : dulieu) {
               tableModelTrai.addRow(new Object[]{
                    item.getMaVatPham(),
                    item.getTenVatPham(),
                    item.getSoLuong(),
                    DoubleToDong(item.getGia())
               });
            }
           
        }
        // bảng bên phải
        else {
            // lấy table model bên phải
            DefaultTableModel tableModelPhai = 
                   this.view.getPnlPanelphai().getPnlPanelthongtin().getTablemodel();
            
            // làm mới table
            tableModelPhai.setRowCount(0);
            
           // thêm vào bảng bên phải
           for(int i = 0;i < dulieu.size(); ++i) {
               VanPhongPham temp = dulieu.get(i);
               
               tableModelPhai.addRow(new Object[]{
                    String.valueOf( i + 1),
                    temp.getMaVatPham(),
                    temp.getTenVatPham(),
                    temp.getSoLuong(),
                    DoubleToDong(temp.getGia())
               });
            }
           
             // làm mới phần tổng tiền
        
            // lấy label hiển thị tổng tiền
            double tong = tongTien(dulieu);
            
            this.view.getPnlPanelphai().getPnlPanelluachon().getLblHienthi().setText(DoubleToDong(tong));
        }
        
        // tải thêm thông tim của mã phiếu nhập mới và người tạo
        this.view.getPnlPanelphai().getPnlPaneltimkiem().getTxtfHienthi().get(0).setText(
                maPhieuNhapMoi());
        
        this.view.getPnlPanelphai().getPnlPaneltimkiem().getTxtfHienthi().get(1).setText(
                NguoiDungController.getCurrentUsername());
        
    }
    //##############################
    
    
    
    
    // hàm để tạo mã phiếu nhập
    public String maPhieuNhapMoi() {
        // tạo phần đầu mã phiếu nhập 
        String maPhieu = "PN";
        
        int maSo = 0;
        
        // đếm số lượng phiêu nhập trong csdl để lấy ra maSo
        ArrayList<PhieuNhap> danhsachPhieu = PhieuNhapDAO.getInstance().getAll();
        
        // lý do làm như này vì có thể các maPhieu không liên tục. vd: PX1, PX3, PX4, .....
        for(var item : danhsachPhieu) {
            String ma = item.getMaPhieu().replace(maPhieu, "");
            
            try {
                int so = Integer.parseInt(ma);
                
                if( maSo < so ) {
                    maSo = so;
                }
                
            }
            catch(NumberFormatException e) {
                e.printStackTrace();
            }
        }
        
        return maPhieu + String.valueOf(maSo + 1);
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
    
    // hàm để tính tổng tiền bên danhsachVPPPhai
    public double tongTien(ArrayList<VanPhongPham> dulieu) {
        double tongTien = 0;
        
        for(var vpp : dulieu) {
            tongTien += ( vpp.getGia() * vpp.getSoLuong() );
        }
        
        return tongTien;
    }
    
}
