package dao;

import model.VanPhongPham;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VanPhongPhamDAO {
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