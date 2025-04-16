package dao;

import model.ChiTietPhieuNhap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ChiTietPhieuNhapDAO {
    // phương thức trả về đối tượng của lớp
    public static ChiTietPhieuNhapDAO getInstance() {
        return new ChiTietPhieuNhapDAO();
    }
    
    // các phương thức truy vấn
    
    // thêm phiếu nhập
    public void insert(ChiTietPhieuNhap temp) {
        // nhận giá trị khi thêm vào bảng dữ liệu phiếu nhập mới
        int ketqua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "INSERT INTO chitietphieunhap "
                    + "(maPhieu, maVatPham, soLuong, donGia) "
                    + "VALUES (?, ?, ?, ?)";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            // đặt giá trị cho các dấu '?'
            pst.setString(1, temp.getMaPhieu());
            
            pst.setString(2, temp.getMaVatPham());
            
            pst.setInt(3, temp.getSoLuong());
            
            pst.setDouble(4, temp.getDonGia());
            
            // thực thi câu lệnh
            ketqua = pst.executeUpdate();
            
            // đóng kết nối tới csdl
            DBConnection.closeConnection(con);
            
            
        } 
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    
    // chỉnh sửa phiếu nhập
    public void update(ChiTietPhieuNhap temp) {
        int ketQua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "UPDATE chitietphieunhap SET "
                    + "maPhieu = ?, "
                    + "maVatPham = ?, "
                    + "soLuong = ?, "
                    + "donGia = ?, "
                    + "WHERE maPhieu = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            // đặt giá trị cho các dấu '?'
            pst.setString(1, temp.getMaPhieu());
            
            pst.setString(2, temp.getMaVatPham());
            
            pst.setInt(3, temp.getSoLuong());
            
            pst.setDouble(4, temp.getDonGia());
            
            ketQua = pst.executeUpdate();
            
            DBConnection.closeConnection(con);
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    
    // xóa phiêu nhập
    public void delete(ChiTietPhieuNhap temp) {
        int ketQua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "DELETE FROM chitietphieunhap WHERE maPhieu = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, temp.getMaPhieu());
            
            ketQua = pst.executeUpdate();
            
            DBConnection.closeConnection(con);
            
        } 
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    
    // lấy tất cả chi tiết phiếu nhập
    
    public ArrayList<ChiTietPhieuNhap> getAll() {
        ArrayList<ChiTietPhieuNhap> ketQua = new ArrayList<ChiTietPhieuNhap>();
        
        try {
            
            Connection con = DBConnection.getConnection();
            
            String sql = "SELECT * FROM chitietphieunhap ORDER BY thoiGianTao DESC";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                // tạo đối tượng và thêm vào mảng
                ChiTietPhieuNhap p = new ChiTietPhieuNhap();
                
                // Lưu ý: nhớ ép kiểu dữ liệu cho các biến 
                p.setMaPhieu( (String) rs.getString("maPhieu") );
                
                p.setMaVatPham( (String) rs.getString("maVatPham") );
                
                p.setDonGia( (double) rs.getDouble("donGia"));
                
                p.setSoLuong( (int) rs.getInt("soLuong") );
                
                ketQua.add(p);
            }
        } 
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        return ketQua;
    }
    
    // lấy tất cả chi tiết phiếu nhập theo maPhieu
    public ArrayList<ChiTietPhieuNhap> getAllById(String id) {
        ArrayList<ChiTietPhieuNhap> ketQua = new ArrayList<ChiTietPhieuNhap>();
        
        try {
            
            Connection con = DBConnection.getConnection();
            
            String sql = "SELECT * FROM chitietphieunhap "
                    + "WHERE maPhieu = ? ";
                    
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                // tạo đối tượng và thêm vào mảng
                ChiTietPhieuNhap p = new ChiTietPhieuNhap();
                
                // Lưu ý: nhớ ép kiểu dữ liệu cho các biến 
                p.setMaPhieu( (String) rs.getString("maPhieu") );
                
                p.setMaVatPham( (String) rs.getString("maVatPham") );
                
                p.setDonGia( (double) rs.getDouble("donGia"));
                
                p.setSoLuong( (int) rs.getInt("soLuong") );
                
                ketQua.add(p);
            }
        } 
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        return ketQua;
    }
    
}