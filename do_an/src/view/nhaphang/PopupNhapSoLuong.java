package view.nhaphang;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class PopupNhapSoLuong extends JDialog {
    private int soLuongNhap = -1;// giá trị mặc định
    private int soLuongHienTai;// số lượng được truyền vào
    
    private boolean kiemtra;
    
    private JTextField txtfSoluong;
    
    private JButton btnOk, btnHuy;

    public PopupNhapSoLuong(JFrame owner, String title, boolean modal, int soLuongHienTai) {
        // constructor của JDialog
        super(owner, title, modal);
        
        //
        this.soLuongHienTai = soLuongHienTai;
        
        InitComponents();

        // căn kích cỡ tự động?
        this.pack(); 
        // vị trí khi hiện lên
        this.setLocationRelativeTo(owner); 
        
        this.setVisible(true);
    }
    
    public void InitComponents() {
        
        InitContents();
        
    }
    
    public void InitContents() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));

        panel.add(new JLabel("Số lượng hiện tại: " + soLuongHienTai));
        
        txtfSoluong = new JTextField();
        
        txtfSoluong.setText(String.valueOf(soLuongHienTai));
        
        panel.add(txtfSoluong);

        // Nút Xác nhận và Hủy
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        btnOk = new JButton("Xác nhận");
        
        btnHuy = new JButton("Hủy");

        
        buttonPanel.add(btnHuy);
        
        buttonPanel.add(btnOk);
        
        panel.add(buttonPanel);
        
        // thêm sự kiện nút
        btnOk.addActionListener(e -> {
            try {
                int value = Integer.parseInt(txtfSoluong.getText().trim());
                if (value < 0) {
                    JOptionPane.showMessageDialog(this, 
                            "Số lượng phải lớn hơn hoặc bằng 0", 
                            "Lỗi", 
                            JOptionPane.ERROR_MESSAGE
                    );
                } 
                else {
                    soLuongNhap = value;
                    kiemtra = true;
                    dispose();
                }
            } 
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Vui lòng nhập số hợp lệ", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        btnHuy.addActionListener(e -> dispose());
        
        
        // thêm vào dialog
        this.setContentPane(panel);
    }

    
    //getter
    
    public int getSoLuongNhap() {
        return soLuongNhap;
    }
    
    public void setSoLuongNhap(int num) {
        this.soLuongNhap = num;
    }

    public JTextField getTxtfSoluong() {
        return txtfSoluong;
    }

    public JButton getBtnOk() {
        return btnOk;
    }

    public JButton getBtnHuy() {
        return btnHuy;
    }

    public boolean getKiemtra() {
        return kiemtra;
    }
    
    public void setKiemTra(boolean set) {
        this.kiemtra = set;
    } 
    
}
