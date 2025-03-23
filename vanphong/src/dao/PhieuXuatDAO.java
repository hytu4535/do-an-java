package dao;

import model.PhieuXuat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuXuatDAO {
    public List<PhieuXuat> getAllPhieuXuats() {
        List<PhieuXuat> phieuXuats = new ArrayList<>();
        String sql = "SELECT * FROM PhieuXuat";
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