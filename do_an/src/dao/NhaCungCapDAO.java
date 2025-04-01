package dao;

import model.NhaCungCap;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO {
    public List<NhaCungCap> getAllNhaCungCaps() {
        List<NhaCungCap> nhaCungCaps = new ArrayList<>();
        String sql = "SELECT * FROM NhaCungCap";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNhaCungCap(rs.getString("maNhaCungCap"));
                ncc.setTenNhaCungCap(rs.getString("tenNhaCungCap"));
                ncc.setSdt(rs.getString("Sdt"));
                ncc.setDiaChi(rs.getString("diaChi"));
                nhaCungCaps.add(ncc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhaCungCaps;
    }
}