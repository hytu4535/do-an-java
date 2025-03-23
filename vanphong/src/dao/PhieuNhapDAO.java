package dao;

import model.PhieuNhap;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAO {
    public List<PhieuNhap> getAllPhieuNhaps() {
        List<PhieuNhap> phieuNhaps = new ArrayList<>();
        String sql = "SELECT * FROM PhieuNhap";
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
}