package dao;

import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT a.*, rg.roleGroupName FROM quanlivanphongpham.account a " +
                     "LEFT JOIN quanlivanphongpham.role_group rg ON a.roleGroupId = rg.roleGroupId";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (conn == null) {
                System.out.println("Không thể kết nối đến database. Vui lòng kiểm tra thông số kết nối.");
                return accounts;
            }
            while (rs.next()) {
                Account account = new Account();
                account.setUserName(rs.getString("userName"));
                account.setFullName(rs.getString("fullName"));
                account.setRoleGroupId(rs.getString("roleGroupId"));
                account.setRoleGroupName(rs.getString("roleGroupName"));
                account.setStatus(rs.getInt("status"));
                account.setNamSinh(rs.getDate("namsinh"));
                account.setDiaChi(rs.getString("diachi"));
                account.setDienThoai(rs.getString("dienthoai"));
                account.setEmail(rs.getString("email"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy dữ liệu tài khoản: " + e.getMessage());
            e.printStackTrace();
        }
        return accounts;
    }

    public void addAccount(Account account) {
        String sql = "INSERT INTO quanlivanphongpham.account (userName, fullName, roleGroupId, status, namsinh, diachi, dienthoai, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getUserName());
            stmt.setString(2, account.getFullName());
            stmt.setString(3, account.getRoleGroupId());
            stmt.setInt(4, account.getStatus());
            stmt.setDate(5, account.getNamSinh());
            stmt.setString(6, account.getDiaChi());
            stmt.setString(7, account.getDienThoai());
            stmt.setString(8, account.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm tài khoản: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {
        String sql = "UPDATE quanlivanphongpham.account SET fullName = ?, roleGroupId = ?, status = ?, namsinh = ?, diachi = ?, dienthoai = ?, email = ? WHERE userName = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getFullName());
            stmt.setString(2, account.getRoleGroupId());
            stmt.setInt(3, account.getStatus());
            stmt.setDate(4, account.getNamSinh());
            stmt.setString(5, account.getDiaChi());
            stmt.setString(6, account.getDienThoai());
            stmt.setString(7, account.getEmail());
            stmt.setString(8, account.getUserName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật tài khoản: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteAccount(String userName) {
        String sql = "DELETE FROM quanlivanphongpham.account WHERE userName = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa tài khoản: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    //Phuc vu phan login - Quoc HUY
    public Account getByUsername(String username) {
    Account account = null;
    String sql = "SELECT * FROM quanlivanphongpham.account WHERE userName = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            account = new Account();
            account.setUserName(rs.getString("userName"));
            account.setPassword(rs.getString("password")); 
            account.setFullName(rs.getString("fullName"));
            account.setRoleGroupId(rs.getString("roleGroupId"));
            account.setStatus(rs.getInt("status"));
            account.setNamSinh(rs.getDate("namsinh"));
            account.setDiaChi(rs.getString("diachi"));
            account.setDienThoai(rs.getString("dienthoai"));
            account.setEmail(rs.getString("email"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return account;
}

}