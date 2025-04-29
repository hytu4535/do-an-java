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
    
    // Lấy danh sách thương hiệu từ bảng vanphongpham
    public List<String> getAllThuongHieu() {
        List<String> thuongHieuList = new ArrayList<>();
        String sql = "SELECT DISTINCT thuongHieu FROM VanPhongPham WHERE thuongHieu IS NOT NULL";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                thuongHieuList.add(rs.getString("thuongHieu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return thuongHieuList;
    }

    // Lấy danh sách xuất xứ từ bảng vanphongpham
    public List<String> getAllXuatXu() {
        List<String> xuatXuList = new ArrayList<>();
        String sql = "SELECT DISTINCT xuatXu FROM VanPhongPham WHERE xuatXu IS NOT NULL";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                xuatXuList.add(rs.getString("xuatXu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return xuatXuList;
    }
    
    // Kiểm tra mã vật phẩm đã tồn tại hay chưa (bất kể trangThai)
    public boolean isMaVatPhamExists(String maVatPham) {
        String sql = "SELECT COUNT(*) FROM vanphongpham WHERE maVatPham = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maVatPham);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // phương thức cập nhật văn phòng phẩm
    public void update(VanPhongPham temp) {
        int ketqua = 0;
        
        try {
            Connection con = DBConnection.getConnection();
            
            String sql = "UPDATE vanphongpham SET "
                    + "maVatPham = ?, "
                    + "tenVatPham = ?, "
                    + "soLuong = ?, "
                    + "loaiVatPham = ?, "
                    + "gia = ?, "
                    + "thuongHieu = ?, "
                    + "chatLieu = ?, "
                    + "doDay = ?, "
                    + "moTa = ?, "
                    + "xuatXu = ?, "
                    + "trangThai = ? "
                    + "WHERE maVatPham = ?";
            
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
            
            String sql = "SELECT * FROM vanphongpham WHERE maVatPham = ? AND trangThai = 1";
            
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
        String sql = "SELECT * FROM VanPhongPham WHERE trangThai = 1";
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
    
    //###############< code quoc huy>#####################
    public List<VanPhongPham> getAllSanPhams() {
        List<VanPhongPham> sanPhams = new ArrayList<>();
        String sql = "SELECT * FROM VanPhongPham WHERE trangThai = 1";
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
                sanPhams.add(vpp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPhams;
    }

    public void addSanPham(VanPhongPham vpp) {
        String sql = "INSERT INTO VanPhongPham (maVatPham, tenVatPham, soLuong, loaiVatPham, gia, thuongHieu, chatLieu, doDay, moTa, xuatXu, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vpp.getMaVatPham());
            stmt.setString(2, vpp.getTenVatPham());
            stmt.setInt(3, vpp.getSoLuong());
            stmt.setString(4, vpp.getLoaiVatPham());
            stmt.setDouble(5, vpp.getGia());
            stmt.setString(6, vpp.getThuongHieu());
            stmt.setString(7, vpp.getChatLieu());
            stmt.setDouble(8, vpp.getDoDay());
            stmt.setString(9, vpp.getMoTa());
            stmt.setString(10, vpp.getXuatXu());
            stmt.setInt(11, vpp.getTrangThai());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi thêm sản phẩm: " + e.getMessage());
        }
    }

    public void updateSanPham(VanPhongPham vpp) {
        String sql = "UPDATE VanPhongPham SET tenVatPham = ?, soLuong = ?, loaiVatPham = ?, gia = ?, thuongHieu = ?, chatLieu = ?, doDay = ?, moTa = ?, xuatXu = ?, trangThai = ? WHERE maVatPham = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vpp.getTenVatPham());
            stmt.setInt(2, vpp.getSoLuong());
            stmt.setString(3, vpp.getLoaiVatPham());
            stmt.setDouble(4, vpp.getGia());
            stmt.setString(5, vpp.getThuongHieu());
            stmt.setString(6, vpp.getChatLieu());
            stmt.setDouble(7, vpp.getDoDay());
            stmt.setString(8, vpp.getMoTa());
            stmt.setString(9, vpp.getXuatXu());
            stmt.setInt(10, vpp.getTrangThai());
            stmt.setString(11, vpp.getMaVatPham());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
    }

    public void deleteSanPham(String maVatPham) {
        String sql = "UPDATE VanPhongPham SET trangThai = 0 WHERE maVatPham = ? AND trangThai = 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maVatPham);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Không tìm thấy sản phẩm với mã: " + maVatPham + " hoặc sản phẩm đã được xóa trước đó!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi xóa sản phẩm: " + e.getMessage());
        }
    }

    public List<VanPhongPham> searchSanPhams(String tuKhoa, String tieuChi) {
        List<VanPhongPham> sanPhams = new ArrayList<>();
        String sql;
        if (tieuChi.equals("Mã SP")) {
            sql = "SELECT * FROM VanPhongPham WHERE maVatPham LIKE ? AND trangThai = 1";
        } else if (tieuChi.equals("Tên SP")) {
            sql = "SELECT * FROM VanPhongPham WHERE tenVatPham LIKE ? AND trangThai = 1";
        } else {
            sql = "SELECT * FROM VanPhongPham WHERE (maVatPham LIKE ? OR tenVatPham LIKE ?) AND trangThai = 1";
        }
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String tuKhoaLike = "%" + tuKhoa + "%";
            stmt.setString(1, tuKhoaLike);
            if (tieuChi.equals("Tất cả")) {
                stmt.setString(2, tuKhoaLike);
            }
            try (ResultSet rs = stmt.executeQuery()) {
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
                    sanPhams.add(vpp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPhams;
    }
}