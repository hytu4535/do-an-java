package dao;

import model.PhieuNhap;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAO {
    // phương thức trả về đối tượng của lớp
    public static PhieuNhapDAO getInstance() {
        return new PhieuNhapDAO();
    }
    
    // các phương thức truy vấn
    
    // thêm phiếu nhập
    public void insert(PhieuNhap temp) {
        // nhận giá trị khi thêm vào bảng dữ liệu phiếu nhập mới
        int ketqua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "INSERT INTO phieunhap "
                    + "(maPhieu, thoiGianTao, nguoiTao, maNhaCungCap, tongTien) "
                    + "VALUES (?, ?, ?, ?, ?)";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            // đặt giá trị cho các dấu '?'
            pst.setString(1, temp.getMaPhieu());
            
            pst.setTimestamp(2, temp.getThoiGianTao());
            
            pst.setString(3, temp.getNguoiTao());
            
            pst.setString(4, temp.getMaNhaCungCap());
            
            pst.setDouble(5, temp.getTongTien());
            
            // thực thi câu lệnh
            ketqua = pst.executeUpdate();
            
            System.out.println(ketqua);
            
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
    public void update(PhieuNhap temp) {
        int ketQua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "UPDATE phieunhap SET "
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
    public void delete(PhieuNhap t) {
        int ketQua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "DELETE FROM phieunhap WHERE maPhieu = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, t.getMaPhieu());
            
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
    
    public PhieuNhap getByID(String id) {
        PhieuNhap p = new PhieuNhap();
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "SELECT * FROM phieunhap WHERE maPhieu = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                p.setMaPhieu( (String) rs.getString("maPhieu") );
                
                p.setThoiGianTao( (Timestamp) rs.getTimestamp("thoiGianTao") );
                
                p.setNguoiTao( (String) rs.getString("nguoiTao") );
                
                p.setMaNhaCungCap( (String) rs.getString("maNhaCungCap") );
                
                p.setTongTien( (double) rs.getDouble("tongTien") );
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
    
    public ArrayList<PhieuNhap> getAll() {
        ArrayList<PhieuNhap> ketQua = new ArrayList<PhieuNhap>();
        
        try {
            
            Connection con = DBConnection.getConnection();
            
            String sql = "SELECT * FROM phieunhap ORDER BY thoiGianTao DESC";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                String maPhieu = rs.getString("maPhieu");
                
                Timestamp thoiGianTao = rs.getTimestamp("thoiGianTao");
                
                String nguoiTao = rs.getString("nguoiTao");
                
                String maNhaCungCap = rs.getString("maNhaCungCap");
                
                double tongTien = rs.getDouble("tongTien");
                
                // tạo đối tượng và thêm vào mảng
                PhieuNhap p = new PhieuNhap();
                
                // Lưu ý: nhớ ép kiểu dữ liệu cho các biến 
                p.setMaPhieu( (String) rs.getString("maPhieu") );
                
                p.setThoiGianTao( (Timestamp) rs.getTimestamp("thoiGianTao") );
                
                p.setNguoiTao( (String) rs.getString("nguoiTao") );
                
                p.setMaNhaCungCap( (String) rs.getString("maNhaCungCap") );
                
                p.setTongTien( (double) rs.getDouble("tongTien") );
                
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
    
    public List<PhieuNhap> getAllPhieuNhaps() {
        List<PhieuNhap> phieuNhaps = new ArrayList<>();
        String sql = "SELECT * FROM phieunhap";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap();
                pn.setMaPhieu(rs.getString("maPhieu"));
                pn.setThoiGianTao(rs.getTimestamp("thoiGianTao"));
                pn.setNguoiTao(rs.getString("nguoiTao"));
                pn.setMaNhaCungCap(rs.getString("maNhaCungCap"));
                pn.setTongTien(rs.getDouble("tongTien"));
                phieuNhaps.add(pn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phieuNhaps;
    }
    
    // xóa hết dữ liệu trong bảng và trả về true khi thành công 
    public boolean deleteAll() {
        String query = "DELETE FROM phieunhap";
        
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