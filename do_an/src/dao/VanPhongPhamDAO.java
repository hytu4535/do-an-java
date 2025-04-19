package dao;

import model.VanPhongPham;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VanPhongPhamDAO {
    // phương thức trả về đối tượng của lớp
    public static VanPhongPhamDAO getInstance() {
        return new VanPhongPhamDAO();
    }
    
    
    // phương thức cập nhật văn phòng phẩm
    public void update(VanPhongPham temp) {
        int ketqua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "UPDATE vanphongpham SET "
                    + "maVatPham = ?, "//1
                    + "tenVatPham = ?, "//2
                    + "soLuong = ?, "//3
                    + "loaiVatPham = ?, "//4
                    + "gia = ?, "//5
                    + "thuongHieu = ?, "//6
                    + "chatLieu = ?, "//7
                    + "doDay = ?, "//8
                    + "moTa = ?, "//9
                    + "xuatXu = ?, "//10
                    + "trangThai = ? "//11 (NHỚ BỎ DẤU PHẨY TRƯỚC KHI ĐỨNG TRƯỚC WHERE)
                    + "WHERE maVatPham = ?";//12
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, temp.getMaVatPham());
            
            pst.setString(2, temp.getTenVatPham());
            
            pst.setInt(3, temp.getSoLuong());
            
            pst.setString(4, temp.getLoaiVatPham());
            
            pst.setDouble(5, temp.getGia());
            
            pst.setString(6, temp.getThuongHieu());
            
            pst.setString(7, temp.getChatLieu());
            
            pst.setDouble(8, temp.getDoDay());
           
            pst.setString(9, temp.getMoTa());
            
            pst.setString(10, temp.getXuatXu());
            
            pst.setInt(11, temp.getTrangThai());
            
            pst.setString(12, temp.getMaVatPham());
            
            // bắt đầu update
            ketqua = pst.executeUpdate();
            
            //
            pst.close();
            
            DBConnection.closeConnection(con);
            
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    } 
    
    
    // phương thức tìm kiếm theo maVatPham và trả về đối tượng
    public VanPhongPham getByID(String id) {
        VanPhongPham ketqua = null;
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "SELECT * FROM vanphongpham WHERE maVatPham = ?";
            
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, id);
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                VanPhongPham kq = new VanPhongPham();
                
                kq.setMaVatPham(rs.getString("maVatPham"));
                
                kq.setTenVatPham(rs.getString("tenVatPham"));
                
                kq.setSoLuong(rs.getInt("soLuong"));
                
                kq.setLoaiVatPham(rs.getString("loaiVatPham"));
                
                kq.setGia(rs.getDouble("gia"));
                
                kq.setThuongHieu(rs.getString("thuongHieu"));
                
                kq.setChatLieu(rs.getString("chatLieu"));
                
                kq.setDoDay((double) rs.getDouble("doDay"));
                
                kq.setMoTa(rs.getString("moTa"));
                
                kq.setXuatXu(rs.getString("xuatXu"));
                
                kq.setTrangThai(rs.getInt("trangThai"));
                
                ketqua = kq;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        
        return ketqua;
    }
    
    public List<VanPhongPham> getAllVanPhongPhams() {
        List<VanPhongPham> vanPhongPhams = new ArrayList<>();
        String sql = "SELECT * FROM VanPhongPham";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                VanPhongPham vpp = new VanPhongPham();
                vpp.setMaVatPham(rs.getString("maVatPham"));
                vpp.setTenVatPham(rs.getString("tenVatPham"));
                vpp.setSoLuong(rs.getInt("soLuong"));
                vpp.setLoaiVatPham(rs.getString("loaiVatPham"));
                vpp.setGia(rs.getDouble("gia"));
                vpp.setThuongHieu(rs.getString("thuongHieu"));
                vpp.setChatLieu(rs.getString("chatLieu"));
                vpp.setDoDay(rs.getDouble("doDay"));
                vpp.setMoTa(rs.getString("moTa"));
                vpp.setXuatXu(rs.getString("xuatXu"));
                vpp.setTrangThai(rs.getInt("trangThai"));
                vanPhongPhams.add(vpp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vanPhongPhams;
    }

    public int getImportQuantity(String maVatPham) {
        int importQuantity = 0;
        String sql = "SELECT SUM(soLuong) as total FROM ChiTietPhieuNhap WHERE maVatPham = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maVatPham);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    importQuantity = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return importQuantity;
    }

    public int getExportQuantity(String maVatPham) {
        int exportQuantity = 0;
        String sql = "SELECT SUM(soLuong) as total FROM ChiTietPhieuXuat WHERE maVatPham = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maVatPham);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    exportQuantity = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exportQuantity;
    }
}