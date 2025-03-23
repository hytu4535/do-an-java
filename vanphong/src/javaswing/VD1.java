package javaswing;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import java.awt.Color;




public class VD1 extends JFrame {

	private JPanel contentPane;
	private JTextField txtSoA; //bien toan cuc kieu JTextField de su dung duoc o nhieu phuong thuc khac
	private JTextField txtSoB;


	/**
	 * Create the frame.
	 */
	public VD1() {
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 450, 300);

	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        contentPane.setLayout(null);

	        setContentPane(contentPane);

	        //dong chu "chuong trinh co ban"
	        JLabel lblNewLabel = new JLabel("Chương trình cơ bản");
	        lblNewLabel.setBackground(new Color(0, 255, 0));
	        lblNewLabel.setOpaque(true); // Bật chế độ hiển thị nền

	        //canh giữa dòng chữ
	        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
	        lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);   // Căn giữa theo chiều dọc

	        
	        lblNewLabel.setForeground(new Color(255, 0, 0));
	        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
	        
	        
	     // Đặt ban đầu theo chiều rộng của JFrame
	        lblNewLabel.setBounds(0, 0, getWidth(), 46);
	        contentPane.add(lblNewLabel); //hien thi JLabel nay tren giao dien Frame
	        
	      //so A
	        JLabel lblsoA = new JLabel("so A: ");
	        lblsoA.setBackground(new Color(0, 255, 0));
	        lblsoA.setOpaque(true); // Bật chế độ hiển thị nền
	        lblsoA.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
	        lblsoA.setVerticalAlignment(SwingConstants.CENTER);  // Căn giữa theo chiều dọc
	        lblsoA.setFont(new Font("Times New Roman", Font.PLAIN,20)); //Font.PLAIN la khong in dam, in nghieng
	        lblsoA.setBounds(40,60,80,25); //x,y,width,height
	        contentPane.add(lblsoA);
	        
	       //nhap so A
	        txtSoA = new JTextField();
	        txtSoA.setBounds(150,60,200,25);
	        contentPane.add(txtSoA);
	        txtSoA.setFont(new Font("Times New Roman",Font.PLAIN,20));
	        txtSoA.setColumns(10);
	        
	        
	      //so B
	        JLabel lblsoB = new JLabel("so B: ");
	        lblsoB.setBackground(new Color(0, 255, 0));
	        lblsoB.setOpaque(true); // Bật chế độ hiển thị nền
	        lblsoB.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
	        lblsoB.setVerticalAlignment(SwingConstants.CENTER);  // Căn giữa theo chiều dọc
	        lblsoB.setFont(new Font("Times New Roman",Font.PLAIN,20)); //Font.PLAIN la khong in dam, in nghieng
	        lblsoB.setBounds(40,100,80,25); //x,y,width,height
	        contentPane.add(lblsoB);
	        
	       //nhap so B
	        txtSoB = new JTextField();
	        txtSoB.setBounds(150,100,200,25);
	        contentPane.add(txtSoB);
	        txtSoB.setFont(new Font("Times New Roman",Font.PLAIN,20));
	        txtSoB.setColumns(10);
	        
	        
	        int halfWidth = getWidth() / 2; //lay mot nua chieu rong Frame
	        //ket qua
	        JLabel lblketQua = new JLabel("Ket qua: ");
	        lblketQua.setBackground(new Color(0, 255, 0));
	        lblketQua.setOpaque(true); // Bật chế độ hiển thị nền
	        lblketQua.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa theo chiều ngang
	        lblketQua.setVerticalAlignment(SwingConstants.CENTER);  // Căn giữa theo chiều dọc
	        lblketQua.setFont(new Font("Times New Roman",Font.PLAIN,20)); //Font.PLAIN la khong in dam, in nghieng
	        lblketQua.setBounds(40,140,80,25); //x,y,width,height
	        contentPane.add(lblketQua);
	        
	        //truong hien thi ket qua
	        JTextField txtketQua = new JTextField();
	        txtketQua.setBounds(150,140,200,25);
	        txtketQua.setEditable(false);
	        txtketQua.setFont(new Font("Times New Roman",Font.PLAIN,20));
	        contentPane.add(txtketQua);
	        
	        //menu
	        JLabel menuBox = new JLabel();
	        menuBox.setBackground(new Color(0,255,0));
	        menuBox.setOpaque(true);
	        menuBox.setFont(new Font("Times New Roman",Font.PLAIN,20));
	        menuBox.setBounds(150,180,300,200);
	        contentPane.add(menuBox);
	        
	        
	        //4 cai JButton
	        JButton btn1 = new JButton("Cong");
	        JButton btn2 = new JButton("tru");
	        JButton btn3 = new JButton("nhan");
	        JButton btn4 = new JButton("chia");
	        btn1.setBackground(new Color(0,0,255));
	        btn1.setOpaque(true);
	        btn1.setFont(new Font("Times New Roman",Font.BOLD,15));
	        btn1.setBounds(40,10,200,50);
	        btn1.setForeground(Color.ORANGE);
	        
	        
	        btn2.setBackground(new Color(0,0,255));
	        btn2.setOpaque(true);
	        btn2.setFont(new Font("Times New Roman",Font.BOLD,15));
	        btn2.setBounds(40,180,200,50);
	        btn2.setForeground(Color.ORANGE);

	        
	        btn3.setBackground(new Color(0,0,255));
	        btn3.setOpaque(true);
	        btn3.setFont(new Font("Times New Roman",Font.BOLD,15));
	        btn3.setBounds(530,10,200,50);
	        btn3.setForeground(Color.ORANGE);
	        
	        
	        btn4.setBackground(new Color(0,0,255));
	        btn4.setOpaque(true);
	        btn4.setFont(new Font("Times New Roman",Font.BOLD,15));
	        btn4.setBounds(530,180,200,50);
	        btn4.setForeground(Color.ORANGE);

	        
	        //them 4 JButton vao menuBox
	        menuBox.setLayout(null); //su dung Layout tuy chinh
	        menuBox.add(btn1);
	        menuBox.add(btn2);
	        menuBox.add(btn3);
	        menuBox.add(btn4);
	        
	        // Lắng nghe sự kiện thay đổi kích thước của JFrame
	        addComponentListener(new ComponentAdapter() {
	            @Override
	            public void componentResized(ComponentEvent e) {
	            	int frameWidth = getWidth();
	            	int frameHeight = getHeight();
	            	int padding = frameHeight / 30;
	                int lblWidth = frameWidth / 4; // Chiều rộng nhãn (~25% cửa sổ)
	                int txtWidth = frameWidth - lblWidth - (padding * 3); // Phần còn lại cho ô nhập
	            	int txtX = lblWidth + (padding * 2);


	                int numFields = 3; // A, B, Kết quả (trước menu)
	                int totalSpacing = frameHeight - 50 - (padding * 2);
	            
	            	// Tính tổng chiều cao có thể dùng (trừ phần menu)
	                int topSectionHeight = frameHeight * 2 / 3;
	                int fieldHeight = Math.max(30, topSectionHeight / 8);
	                int spacing = fieldHeight + padding;
	            	
	            	//kich thuoc lblNewLabel
	                lblNewLabel.setBounds(0,0,frameWidth, 50);
	                
	                //kich thuoc lbl
	                lblsoA.setBounds(padding, 60, lblWidth, fieldHeight);
	                lblsoB.setBounds(padding, 60 + spacing, lblWidth, fieldHeight);
	                lblketQua.setBounds(padding, 60 + spacing * 2, lblWidth, fieldHeight);
	                
	                //kich thuoc txt
	                txtSoA.setBounds(txtX, 60, txtWidth, fieldHeight);
	                txtSoB.setBounds(txtX, 60 + spacing, txtWidth, fieldHeight);
	                txtketQua.setBounds(txtX, 60 + spacing * 2, txtWidth, fieldHeight);
	                
	                //menu 
	                int menuWidth = frameWidth / 2;
	                int menuHeight = frameHeight / 4;
	                int menuX = (frameWidth - menuWidth) / 2;
	                int menuY = 60 + spacing * 3 + padding;
	                menuBox.setBounds(menuX,menuY,menuWidth,menuHeight + 40);
	             
	                //button
	             // Cập nhật vị trí của 4 JButton trong menuBox
	                int btnWidth = menuWidth / 3;
	                int btnHeight = menuHeight / 4;
	                
	                int btnX1 = menuWidth / 8;         // Vị trí nút 1 và 2
	                int btnX2 = menuWidth - btnWidth - btnX1; // Vị trí nút 3 và 4
	                
	                int btnY1 = menuHeight / 6;        // Vị trí nút 1 và 3
	                int btnY2 = menuHeight - btnHeight - btnY1; // Vị trí nút 2 và 4

	                btn1.setBounds(btnX1, btnY1, btnWidth, btnHeight);
	                btn2.setBounds(btnX1, btnY2, btnWidth, btnHeight);
	                btn3.setBounds(btnX2, btnY1, btnWidth, btnHeight);
	                btn4.setBounds(btnX2, btnY2, btnWidth, btnHeight);

	            }
	        });
	        
	        
	        
	        
//--------------------------------------------------------------------------------------------------
	        //lang nghe su kien o button cong
	        btn1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	//kiem tra truong do co trong khong
	            	if(txtSoA.getText().trim().isEmpty() && txtSoB.getText().trim().isEmpty())
	            	{
	                    JOptionPane.showMessageDialog(null, "Vui lòng nhập cả số A và số B!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    return;
	            	}
	            	else if(txtSoA.getText().trim().isEmpty())
	            	{
	            		JOptionPane.showMessageDialog(null, "Vui lòng nhập số A!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            		return;
	            	}
	            	else if(txtSoB.getText().trim().isEmpty())
	            	{
	            		JOptionPane.showMessageDialog(null, "Vui lòng nhập số B", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            		return;
	            	}
	            	
	            	
	            	
	            	
	            	//chay ham tinh toan
	            	try {	
	            	//lay gia tri roi chuyen tu dang string sang double
	            	double soA = Double.parseDouble(txtSoA.getText().trim());
	            	double soB = Double.parseDouble(txtSoB.getText().trim());
	            	
	            	double kq = soA + soB;
	            	
	            	//hien thi ket qua vao o txtKetQua
	            	txtketQua.setText(String.valueOf(kq));
	            }catch (NumberFormatException ex) {
	            	JOptionPane.showMessageDialog(null,"Vui long nhap lai so hop le!","Loi",JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        });
	        
	        
	        
	        //lang nghe su kien o button tru
	        btn2.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	//kiem tra truong do co trong khong
	            	if(txtSoA.getText().trim().isEmpty() && txtSoB.getText().trim().isEmpty())
	            	{
	                    JOptionPane.showMessageDialog(null, "Vui lòng nhập cả số A và số B!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    return;
	            	}
	            	else if(txtSoA.getText().trim().isEmpty())
	            	{
	            		JOptionPane.showMessageDialog(null, "Vui lòng nhập số A!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            		return;
	            	}
	            	else if(txtSoB.getText().trim().isEmpty())
	            	{
	            		JOptionPane.showMessageDialog(null, "Vui lòng nhập số B", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            		return;
	            	}
	            	
	            	
	            	//chay ham tinh toan
	            	try {
	            	//lay gia tri roi chuyen tu dang string sang double
	            	double soA = Double.parseDouble(txtSoA.getText().trim());
	            	double soB = Double.parseDouble(txtSoB.getText().trim());
	            	
	            	double kq = soA - soB;
	            	
	            	
	            	//hien thi ket qua vao o txtKetQua
	            	txtketQua.setText(String.valueOf(kq));
	            }catch (NumberFormatException ex) {
	            	JOptionPane.showMessageDialog(null,"Vui long nhap lai so hop le!","Loi",JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        });

	        
	        //lang nghe su kien o button nhan
	        btn3.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	
	            	//kiem tra truong do co trong khong
	            	if(txtSoA.getText().trim().isEmpty() && txtSoB.getText().trim().isEmpty())
	            	{
	                    JOptionPane.showMessageDialog(null, "Vui lòng nhập cả số A và số B!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    return;
	            	}
	            	else if(txtSoA.getText().trim().isEmpty())
	            	{
	            		JOptionPane.showMessageDialog(null, "Vui lòng nhập số A!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            		return;
	            	}
	            	else if(txtSoB.getText().trim().isEmpty())
	            	{
	            		JOptionPane.showMessageDialog(null, "Vui lòng nhập số B", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            		return;
	            	}
	            	
	            	//chay ham tinh toan
	            	try {
	            	//lay gia tri roi chuyen tu dang string sang double
	            	double soA = Double.parseDouble(txtSoA.getText().trim());
	            	double soB = Double.parseDouble(txtSoB.getText().trim());
	            	
	            	double kq = soA * soB;
	            	
	            	//hien thi ket qua vao o txtKetQua
	            	txtketQua.setText(String.valueOf(kq));
	            }catch (NumberFormatException ex) {
	            	JOptionPane.showMessageDialog(null,"Vui long nhap lai so hop le!","Loi",JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        });
	        
	        
	        //lang nghe su kien o button chia
	        btn4.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	
	            	//kiem tra truong do co trong khong
	            	if(txtSoA.getText().trim().isEmpty() && txtSoB.getText().trim().isEmpty())
	            	{
	                    JOptionPane.showMessageDialog(null, "Vui lòng nhập cả số A và số B!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    return;
	            	}
	            	else if(txtSoA.getText().trim().isEmpty())
	            	{
	            		JOptionPane.showMessageDialog(null, "Vui lòng nhập số A!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            		return;
	            	}
	            	else if(txtSoB.getText().trim().isEmpty())
	            	{
	            		JOptionPane.showMessageDialog(null, "Vui lòng nhập số B", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            		return;
	            	}
	            	
	            	
	            	
	            	//chay ham tinh toan
	            	try {
	            	//lay gia tri roi chuyen tu dang string sang double
	            	double soA = Double.parseDouble(txtSoA.getText().trim());
	            	double soB = Double.parseDouble(txtSoB.getText().trim());
	            	
	            	//ep kieu de chia lay ca nguyen va du
	            	double kq = (double) soA / soB;
	            	
	            	//hien thi ket qua vao o txtKetQua
	            	txtketQua.setText(String.valueOf(kq));
	            }catch (NumberFormatException ex) {
	            	JOptionPane.showMessageDialog(null,"Vui long nhap lai so hop le!","Loi",JOptionPane.ERROR_MESSAGE);
	            }
	        }
	        });
	        
	        txtSoA.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                txtSoA.setBorder(new LineBorder(Color.GRAY, 2)); // Viền đen khi di chuột vào
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                txtSoA.setBorder(null); // Xóa viền khi di chuột ra
	            }
	        });

	        txtSoB.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                txtSoB.setBorder(new LineBorder(Color.GRAY, 2)); // Viền đen khi di chuột vào
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                txtSoB.setBorder(null); // Xóa viền khi di chuột ra
	            }
	        });
	}



	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            VD1 frame = new VD1();
	            frame.setVisible(true);
	        });
	    }

}
