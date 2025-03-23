package dao;

import model.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Account";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Account account = new Account();
                account.setFullName(rs.getString("fullName"));
                account.setUserName(rs.getString("userName"));
                account.setPassword(rs.getString("password"));
                account.setRole(rs.getString("role"));
                account.setStatus(rs.getInt("status"));
                account.setEmail(rs.getString("email"));
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}