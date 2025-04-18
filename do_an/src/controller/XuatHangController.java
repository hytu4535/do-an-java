package controller;

import dao.PhieuXuatDAO;
import dao.VanPhongPhamDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.DecimalFormat;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.PhieuXuat;
import model.VanPhongPham;

import view.xuathang.PopupNhapSoLuong;
import view.xuathang.XuathangPanel;




public class XuatHangController {
    private XuathangPanel view;
    
    // hai mảng này để làm việc và hiển thị lên 2 table bên trái và bên phải
    private ArrayList<VanPhongPham> danhsachVPPTrai = 
            (ArrayList<VanPhongPham>) VanPhongPhamDAO.getInstance().getAllVanPhongPhams();
    
    private ArrayList<VanPhongPham> danhsachVPPPhai =
            new ArrayList<>();
    
    
    
    public XuatHangController(XuathangPanel view) {
        this.view = view;
        
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
        this.view.getPnlPanelphai().getPnlPanelluachon().getBtnNut().get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                chuyenPhaiVeTrai();
            }
        });
        
        // thêm sự kiện cho nút sửa số lượng bên phải
        this.view.getPnlPanelphai().getPnlPanelluachon().getBtnNut().get(1).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                suaSoLuong();
            }
        });
    }
    
    
    // tải dữ liệu cho bảng bên trái
    public void loadDataBenTrai() {
            // bảng này có bộ lọc tìm kiếm
            // lấy dữ liệu lọc
            String timkiem = this.view.getPnlPaneltrai().getPnlPaneltimkiem().getTxtfTimkiem().getText().toLowerCase();
            
            // danhsach để lọc ra dữ liệu nếu có
            ArrayList<VanPhongPham> danhsach = new ArrayList<>();
            
            // nếu có dùng bộ timkiem
           if( timkiem.length() > 0) {
                // lọc
                for(var item : this.danhsachVPPTrai) {
                // nếu hàng có điểm giống với điều kiện lọc
                String maVatPham = item.getMaVatPham().toLowerCase();
                
                String tenVatPham = item.getTenVatPham().toLowerCase();
                
                String soLuong = String.valueOf(item.getSoLuong()).toLowerCase();
                
                String donGia = String.valueOf(item.getGia()).toLowerCase();
                
                    if( maVatPham.contains(timkiem) || 
                            tenVatPham.contains(timkiem) || 
                            soLuong.contains(timkiem) ||
                            donGia.contains(timkiem) ) {
                        
                        danhsach.add(item);
                    }
                }
           }
           // nếu không dùng tìm kiếm
           else {
               danhsach =this.danhsachVPPTrai;
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
                        this.view.getPnlPaneltrai().getPnlPanelluachon().getTxtfHienthi().getText().toString();
                
                System.out.println(soLuongNhapTextField);
                
                if( isNumeric(soLuongNhapTextField) ) {
                    
                    // lấy bảng chứa hàng hóa
                    JTable table = this.view.getPnlPaneltrai().getPnlPanelthongtin().getTblThongtin();

                    // lấy số lượng nhập
                    int soLuongNhap = Integer.parseInt(soLuongNhapTextField);
                
                    // lấy số lượng hàng hiện tại
                    int soLuongHang = Integer.parseInt(table.getValueAt(hangDuocChon, 2).toString());
                
                    // kiểm tra nếu số lượng nhập không vượt quá số lượng hàng hiên tại 
                    // và phải lớn hơn 0
                    if( soLuongNhap <= soLuongHang && soLuongNhap > 0) {
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
                        
                        // cập nhật lại danhsachVPPTrai bên trái
                        temp.setSoLuong(soLuongHang - soLuongNhap);
                        
                        // làm mới lại hai table 
                        // table trái
                        loadData(this.danhsachVPPTrai, 1);
                        
                        // table phải
                        loadData(this.danhsachVPPPhai, 0);
                    }
                    // nếu vượt quá
                    else {
                    // báo cho người dùng chọn hàng dữ liệu
                        JOptionPane.showMessageDialog(
                            null,                                                                     // parent: 
                            "Số lượng nhập vào phải nhỏ hơn số lượng hàng và lớn hơn 0!", // nội dung của thông báo
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
            
            // B1: cập nhật danhsachVPPTrai trước
            for(var item : this.danhsachVPPTrai) {
                if( item.getMaVatPham().equals(maVatPhamPhai) ) {
                    // sửa số lượng
                    item.setSoLuong( item.getSoLuong() + soLuongPhai );
                }
            }
            
            // B2: xóa đối tượng được chọn trong danhsachVPPPhai
            for(int i = 0; i < this.danhsachVPPPhai.size(); ++i) {
                if( this.danhsachVPPPhai.get(i).getMaVatPham().equals(maVatPhamPhai) ) {
                    this.danhsachVPPPhai.remove(i);
                    break;
                }
            }
            
            // B3: cập nhật lại table của hai bên
            // table trái
            loadData(this.danhsachVPPTrai, 1);
            
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
            
            
            // B1: cập nhật danhsachVPPTrai trước
            for(var item : this.danhsachVPPTrai) {
                if( item.getMaVatPham().equals(maVatPhamPhai) ) {
                     // kiểm tra nếu số lượng sửa nhiều hơn số lượng bên danhsachVPPTrai
                     if( item.getSoLuong() < soLuongMoi ) {
                         // báo lỗi
                         JOptionPane.showMessageDialog(null,
                        "Số lượng được sửa phải bé hơn số lượng hàng trong danh sách!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE
                        );
                         // ngắt hành động
                        return;
                     }
                    // sửa số lượng
                     else {
                        item.setSoLuong( item.getSoLuong() + (soLuongHienTai - soLuongMoi) );
                     }
                }
            }
            
            // B2: cập nhật vào danhsachVPPPhai
            for(int i = 0; i < this.danhsachVPPPhai.size(); ++i) {
                // nếu số lượng nhập mới là 0 thì xóa luôn hàng đấy
                if( this.danhsachVPPPhai.get(i).getMaVatPham().equals(maVatPhamPhai)) {
                     // nếu số lượng nhập mới là 0 thì xóa luôn hàng đấy
                     if( (soLuongHienTai - soLuongMoi) == 0 ) {
                         this.danhsachVPPPhai.remove(i);
                     }
                     // nếu không phải
                     else {
                         this.danhsachVPPPhai.get(i).setSoLuong(soLuongMoi);
                     }
                     
                     break;
                }
            }
          
            // B3: cập nhật lại table của hai bên
            // table trái
            loadData(this.danhsachVPPTrai, 1);
            
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
        }
        
        // tải thêm thông tim của mã phiếu nhập mới
        this.view.getPnlPanelphai().getPnlPaneltimkiem().getTxtfHienthi().get(0).setText(
                maPhieuXuatMoi());
    }
    //##############################
    
    
    
    
    // hàm để tạo mã phiếu xuất
    public String maPhieuXuatMoi() {
        // tạo phần đầu mã phiếu xuất 
        String maPhieu = "PX";
        
        int maSo = 1;
        
        // đếm số lượng phiêu xuất trong csdl để lấy ra maSo
        ArrayList<PhieuXuat> danhsachPhieu = 
                (ArrayList<PhieuXuat>) PhieuXuatDAO.getInstance().getAllPhieuXuats();
        
        // lặp trong mảng để đếm maSo
        for(var item : danhsachPhieu) {
            maSo++;
        }
        
        return maPhieu + String.valueOf(maSo);
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
