package dao;

import model.Account;
import dao.DBConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AccountDAO {
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT a.*, rg.roleGroupName FROM quanlivanphongpham.account a " +
                     "LEFT JOIN quanlivanphongpham.role_group rg ON a.roleGroupId = rg.roleGroupId " +
                     "WHERE a.status = 1";
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
                account.setPassword(rs.getString("password")); // Lấy mật khẩu
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy dữ liệu tài khoản: " + e.getMessage());
            e.printStackTrace();
        }
        return accounts;
    }

    public void addAccount(Account account) {
        String sql = "INSERT INTO quanlivanphongpham.account (userName, fullName, roleGroupId, status, namsinh, diachi, dienthoai, email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            String hashedPassword = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt());
            stmt.setString(9, hashedPassword); // Lưu mật khẩu mã hóa
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm tài khoản: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateAccount(Account account) {
        String sql = "UPDATE quanlivanphongpham.account SET fullName = ?, roleGroupId = ?, status = ?, namsinh = ?, diachi = ?, dienthoai = ?, email = ?, password = ? WHERE userName = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getFullName());
            stmt.setString(2, account.getRoleGroupId());
            stmt.setInt(3, account.getStatus());
            stmt.setDate(4, account.getNamSinh());
            stmt.setString(5, account.getDiaChi());
            stmt.setString(6, account.getDienThoai());
            stmt.setString(7, account.getEmail());
            if (account.getPassword() != null && !account.getPassword().isEmpty()) {
                String hashedPassword = BCrypt.hashpw(account.getPassword(), BCrypt.gensalt());
                stmt.setString(8, hashedPassword); // Cập nhật mật khẩu mới nếu có
            } else {
                stmt.setString(8, getCurrentPassword(account.getUserName())); // Giữ nguyên mật khẩu cũ
            }
            stmt.setString(9, account.getUserName());
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

    public void deactivateAccount(String userName) {
        String sql = "UPDATE quanlivanphongpham.account SET status = 0 WHERE userName = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi chuyển trạng thái tài khoản: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String getCurrentPassword(String userName) {
        String sql = "SELECT password FROM quanlivanphongpham.account WHERE userName = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy mật khẩu hiện tại: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static final String USERNAME_REGEX = "^[a-zA-Z0-9_-]{3,20}$";
    private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_REGEX);

    private boolean isValidUsername(String userName) {
        if (userName == null) {
            return false;
        }
        return USERNAME_PATTERN.matcher(userName).matches();
    }

    public boolean isUsernameExists(String userName) {
        String sql = "SELECT COUNT(*) FROM quanlivanphongpham.account WHERE userName = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi kiểm tra userName: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public String checkUsernameDuplicate(String userName) {
        if (!isValidUsername(userName)) {
            return "Tên tài khoản không hợp lệ. Tên tài khoản chỉ được chứa chữ cái, số, dấu gạch dưới hoặc gạch ngang, và có độ dài từ 3 đến 20 ký tự.";
        }
        if (isUsernameExists(userName)) {
            return "Tên tài khoản đã tồn tại. Vui lòng chọn tên tài khoản khác.";
        }
        return null;
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