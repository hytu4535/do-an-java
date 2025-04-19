package view.phieunhap;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import model.PhieuXuat;


public class SuaPhieuXuatDialog extends JDialog {
    private ArrayList<JTextField> txtfNhap = new ArrayList<>();
    private ArrayList<JLabel> lbllHienthi = new ArrayList<>();
    private String[] Hienthi = {"Mã phiếu:", "Người tạo:", "Tổng tiền:", "Thời gian tạo:"};
    private JDateChooser dcNgay;
    
    private PhieuXuat phieu;// dùng để lấy mã phiếu xuất
    private PhieuXuat phieuXuat = new PhieuXuat();// dùng để lưu các giá trị nhập để cập nhật phiếu theo cái này
            
    private Font myFont = new Font("Arial", Font.PLAIN, 16);
    private Color bgColor = new Color(255, 255, 255);
    
    private GridBagConstraints gbc = new GridBagConstraints();
    
    
    public SuaPhieuXuatDialog(JFrame parent, PhieuXuat phieu) {
        super(parent, "Sửa Phiếu Nhập", true);
        this.phieu = phieu;
        
        this.setLayout(new GridBagLayout());
        
        for(int i = 0; i < this.Hienthi.length; ++i) {
            // label
            JLabel label = new JLabel(this.Hienthi[i]);
            label.setFont(myFont);
            label.setBackground(bgColor);
            label.setBorder(null);
            
             this.lbllHienthi.add(label);
             
             // textfield
             if(i < this.Hienthi.length - 1) {
                 JTextField txtfield = new JTextField();
                txtfield.setFont(myFont);
                txtfield.setBackground(bgColor);
                txtfield.setBorder(new LineBorder(Color.black, 1));
           
                this.txtfNhap.add(txtfield);
             }
        }
        
        // điều chỉnh cho ô mã phiếu không thay đổi được
        this.txtfNhap.get(0).setEditable(false);
        
        this.txtfNhap.get(0).setText(this.phieu.getMaPhieu());
        
         // điều chỉnh cho mã nhân viên không đổi được
        this.txtfNhap.get(1).setEditable(false);
        
        this.txtfNhap.get(1).setText(this.phieu.getNguoiTao());
        
        
        // dcNgay
        dcNgay = new JDateChooser();
        
        dcNgay.setDate(phieu.getThoiGianTao());
        
        dcNgay.setFont(myFont);
        
        dcNgay.setBackground(bgColor);
        
        dcNgay.setBorder(new LineBorder(Color.black, 1));
        

        // thêm vào dialog
        gbc.insets = new Insets(5, 5, 5, 5);//top, left, right, bottom
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // label
        for(int i = 0; i < this.Hienthi.length; ++i) {
            gbc.gridx = 0;
            gbc.gridy = i;
            
            gbc.weightx = 0.4;
            
            this.add(this.lbllHienthi.get(i), gbc);
        }
        
        // textfield
        // lí do < .... - 1 vì chỉ có 4 cái txtf 
        for(int i = 0; i < this.Hienthi.length - 1; ++i) {
            gbc.gridx = 1;
            gbc.gridy = i;
            
            gbc.weightx = 0.6;
            
            this.add(this.txtfNhap.get(i), gbc);
        }
        
        // dcNgay
        gbc.gridx = 1;
        gbc.gridy = this.Hienthi.length - 1;
        
        gbc.weightx = 0.6;
        
        this.add(dcNgay, gbc);
        
        
        // hai nút
        JButton btnSave = new JButton("Lưu");
        JButton btnCancel = new JButton("Hủy");
        
        // điều chỉnh
        btnSave.setFont(myFont);
        btnSave.setBackground(bgColor);
        btnSave.setFocusable(false);
        
        btnCancel.setFont(myFont);
        btnCancel.setBackground(bgColor);
        btnCancel.setFocusable(false);
        
        // thêm hai nút vào dialog
        // cancel
        gbc.gridx = 0;
        gbc.gridy = this.Hienthi.length;
            
        gbc.weightx = 0.5;
            
        this.add(btnCancel, gbc);
        
        // save
        gbc.gridx = 1;
        gbc.gridy = this.Hienthi.length;
            
        gbc.weightx = 0.5;
            
        this.add(btnSave, gbc);
        
        
        // thêm sự kiện cho nút
        // save
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cập nhật thông tin vào đối tượng PhieuNhap
                // kiểm tra nếu nhập đủ thông tin
                if( validateTextFields() ) {
                    phieuXuat.setMaPhieu(phieu.getMaPhieu());
                    
                    phieuXuat.setNguoiTao(txtfNhap.get(1).getText());
                    
                    phieuXuat.setTongTien( Double.parseDouble(txtfNhap.get(2).getText()) );
                    
                    phieuXuat.setThoiGianTao(new Timestamp(dcNgay.getDate().getTime()));
                    
                    // đóng
                    dispose();
                }
                else {
                    // Hiển thị cảnh báo nếu chưa nhập đủ
                    JOptionPane.showMessageDialog(
                        SuaPhieuXuatDialog.this,
                        "Vui lòng nhập đầy đủ và chính xác thông tin!",
                        "Thiếu thông tin",
                        JOptionPane.WARNING_MESSAGE
                        );
                }
            }
        });
        
        // Cancel
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đóng Dialog mà không làm gì cả
                dispose();
            }
        });
        
        // hiển thị dialog
        this.setSize(600, 300);
        
        this.setLocationRelativeTo(parent);
        
        this.setVisible(true);

    }
    
    
    // Hàm kiểm tra tính hợp lệ cho tất cả các JTextField
    // "Mã phiếu:", "Người tạo:", "Mã nhà cung cấp:", "Tổng tiền:", "Thời gian tạo:"
    private boolean validateTextFields() {
        // kiểm tra nếu có đầy đủ textfield
        for (var com : this.txtfNhap) {
            if ( com.getText().trim().isEmpty() ) {
                return false;
            }
        }
        
        // kiểm tra nếu tổng tiền là chuỗi số thì bỏ qua return false trong ô cacth
        try {
            double test = Double.parseDouble(this.txtfNhap.get(2).getText());
            
        }
        catch(NumberFormatException e) {
            e.printStackTrace();
            
            return false;
        }
        
        return true;
    }
    
    // phương thức trả về đối tượng chứa dữ liệu mới
    public PhieuXuat getPhieuXuat() {
        return this.phieuXuat;
    }
}
