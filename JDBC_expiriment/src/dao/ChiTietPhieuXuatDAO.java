package dao;

import model.ChiTietPhieuXuat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ChiTietPhieuXuatDAO {
    // phương thức trả về đối tượng của lớp
    public static ChiTietPhieuXuatDAO getInstance() {
        return new ChiTietPhieuXuatDAO();
    }
    
    // các phương thức truy vấn
    
    // thêm phiếu nhập
    public void insert(ChiTietPhieuXuat temp) {
        // nhận giá trị khi thêm vào bảng dữ liệu phiếu nhập mới
        int ketqua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "INSERT INTO chitietphieuxuat "
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
            pst.close();
            
            DBConnection.closeConnection(con);
            
        } 
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    
    // chỉnh sửa phiếu nhập
    public void update(ChiTietPhieuXuat temp) {
        int ketQua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "UPDATE chitietphieuxuat SET "
                    + "maPhieu = ?, "
                    + "maVatPham = ?, "
                    + "soLuong = ?, "
                    + "donGia = ? "// NHỚ BỎ DẤU PHẨY TRƯỚC WHERE
                    + "WHERE maPhieu = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            // đặt giá trị cho các dấu '?'
            pst.setString(1, temp.getMaPhieu());
            
            pst.setString(2, temp.getMaVatPham());
            
            pst.setInt(3, temp.getSoLuong());
            
            pst.setDouble(4, temp.getDonGia());
            
            ketQua = pst.executeUpdate();
            
            // close
            pst.close();
            
            DBConnection.closeConnection(con);
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    
    // xóa phiếu xuất
    public void delete(ChiTietPhieuXuat temp) {
        int ketQua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "DELETE FROM chitietphieuxuat WHERE maPhieu = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, temp.getMaPhieu());
            
            ketQua = pst.executeUpdate();
            
            //
            pst.close();
            
            DBConnection.closeConnection(con);
            
        } 
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    
    // lấy tất cả chi tiết phiếu nhập
    
    public ArrayList<ChiTietPhieuXuat> getAll() {
        ArrayList<ChiTietPhieuXuat> ketQua = new ArrayList<>();
        
        try {
            
            Connection con = DBConnection.getConnection();
            
            String sql = "SELECT * FROM chitietphieuxuat";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                // tạo đối tượng và thêm vào mảng
                ChiTietPhieuXuat p = new ChiTietPhieuXuat();
                
                // Lưu ý: nhớ ép kiểu dữ liệu cho các biến 
                p.setMaPhieu( (String) rs.getString("maPhieu") );
                
                p.setMaVatPham( (String) rs.getString("maVatPham") );
                
                p.setDonGia( (double) rs.getDouble("donGia"));
                
                p.setSoLuong( (int) rs.getInt("soLuong") );
                
                ketQua.add(p);
            }
            
            //
            pst.close();
            
            DBConnection.closeConnection(con);
        } 
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        return ketQua;
    }
    
    // lấy tất cả chi tiết phiếu nhập theo maPhieu
    public ArrayList<ChiTietPhieuXuat> getAllById(String id) {
        ArrayList<ChiTietPhieuXuat> ketQua = new ArrayList<>();
        
        try {
            
            Connection con = DBConnection.getConnection();
            
            String sql = "SELECT * FROM chitietphieuxuat "
                    + "WHERE maPhieu = ? ";
                    
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                // tạo đối tượng và thêm vào mảng
                ChiTietPhieuXuat p = new ChiTietPhieuXuat();
                
                // Lưu ý: nhớ ép kiểu dữ liệu cho các biến 
                p.setMaPhieu( (String) rs.getString("maPhieu") );
                
                p.setMaVatPham( (String) rs.getString("maVatPham") );
                
                p.setDonGia( (double) rs.getDouble("donGia"));
                
                p.setSoLuong( (int) rs.getInt("soLuong") );
                
                ketQua.add(p);
                
            }
            
            //
            pst.close();
            
            DBConnection.closeConnection(con);
        } 
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        return ketQua;
    }
    
     // xóa hết dữ liệu trong bảng và trả về true khi thành công 
    public boolean deleteAll() {
        String query = "DELETE FROM chitietphieuxuat";
        
        try (Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(query);
                ) {
            int rowsAffected = pst.executeUpdate(query);
            
            return rowsAffected > 0;
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}