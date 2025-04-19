package dao;

import model.PhieuXuat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuXuatDAO {
    // phương thức trả về đối tượng của lớp
    public static PhieuXuatDAO getInstance() {
        return new PhieuXuatDAO();
    }
    
    // các phương thức truy vấn
    
    // thêm phiếu nhập
    public void insert(PhieuXuat temp) {
        // nhận giá trị khi thêm vào bảng dữ liệu phiếu nhập mới
        int ketqua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "INSERT INTO phieuxuat "
                    + "(maPhieu, thoiGianTao, nguoiTao, tongTien) "
                    + "VALUES (?, ?, ?, ?)";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            // đặt giá trị cho các dấu '?'
            pst.setString(1, temp.getMaPhieu());
            
            pst.setTimestamp(2, temp.getThoiGianTao());
            
            pst.setString(3, temp.getNguoiTao());
            
            pst.setDouble(4, temp.getTongTien());
            
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
    public void update(PhieuXuat temp) {
        int ketQua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "UPDATE phieuxuat SET "
                    + "thoiGianTao = ?, "
                    + "tongTien = ? "
                    + "WHERE maPhieu = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
           
            pst.setTimestamp(1, temp.getThoiGianTao());
            
            pst.setDouble(2, temp.getTongTien());
            
            pst.setString(3, temp.getMaPhieu());
            
            ketQua = pst.executeUpdate();
            
            //
            pst.close();
            
            DBConnection.closeConnection(con);
            
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    
    // xóa phiêu nhập
    public void delete(PhieuXuat temp) {
        int ketQua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "DELETE FROM phieuxuat WHERE maPhieu = ?";
            
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
    
    public PhieuXuat getByID(String id) {
        PhieuXuat p = new PhieuXuat();
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "SELECT * FROM phieuxuat WHERE maPhieu = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                p.setMaPhieu(rs.getString("maPhieu"));
                
                p.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
                
                p.setNguoiTao(rs.getString("nguoiTao"));
                
                p.setTongTien(rs.getDouble("tongTien"));
            }
            
            //
            pst.close();
            
            DBConnection.closeConnection(con);
            
        } 
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return p;
    }
    
    // lấy tất cả phiêu nhập
    
    public List<PhieuXuat> getAllPhieuXuats() {
        List<PhieuXuat> phieuXuats = new ArrayList<>();
        String sql = "SELECT * FROM phieuxuat";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PhieuXuat px = new PhieuXuat();
                px.setMaPhieu(rs.getString("maPhieu"));
                px.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
                px.setNguoiTao(rs.getString("nguoiTao"));
                px.setTongTien(rs.getDouble("tongTien"));
                phieuXuats.add(px);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phieuXuats;
    }
}