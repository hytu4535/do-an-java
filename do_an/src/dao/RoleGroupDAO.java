package dao;

import model.RoleGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleGroupDAO {
    // Lấy tất cả nhóm quyền và danh sách chức năng + quyền tương ứng
    public List<RoleGroup> getAllRoleGroups() {
        List<RoleGroup> roleGroups = new ArrayList<>();
        String sqlRoleGroup = "SELECT * FROM quanlivanphongpham.role_group";
        String sqlFunctions = "SELECT f.functionName, rgf.action FROM quanlivanphongpham.role_group_function rgf " +
                             "JOIN quanlivanphongpham.function f ON rgf.functionId = f.functionId " +
                             "WHERE rgf.roleGroupId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("Không thể kết nối đến database. Vui lòng kiểm tra thông số kết nối.");
                return roleGroups;
            }

            // Lấy danh sách nhóm quyền
            try (PreparedStatement stmt = conn.prepareStatement(sqlRoleGroup);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RoleGroup roleGroup = new RoleGroup();
                    roleGroup.setRoleGroupId(rs.getString("roleGroupId"));
                    roleGroup.setRoleGroupName(rs.getString("roleGroupName"));
                    roleGroup.setDescription(rs.getString("description"));
                    roleGroups.add(roleGroup);
                }
            }

            // Lấy danh sách chức năng và quyền cho từng nhóm quyền
            try (PreparedStatement stmt = conn.prepareStatement(sqlFunctions)) {
                for (RoleGroup roleGroup : roleGroups) {
                    stmt.setString(1, roleGroup.getRoleGroupId());
                    List<String> functions = new ArrayList<>();
                    try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) {
                            String functionName = rs.getString("functionName");
                            String action = rs.getString("action");
                            functions.add(functionName + "_" + action); // Lưu dưới dạng "functionName_action"
                        }
                    }
                    roleGroup.setFunctions(functions);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy dữ liệu nhóm quyền: " + e.getMessage());
            e.printStackTrace();
        }
        return roleGroups;
    }

    // Thêm nhóm quyền và danh sách chức năng + quyền
    public void addRoleGroup(RoleGroup roleGroup) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                throw new SQLException("Không thể kết nối đến database.");
            }
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Thêm vào bảng role_group
            String sqlRoleGroup = "INSERT INTO quanlivanphongpham.role_group (roleGroupId, roleGroupName, description) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlRoleGroup)) {
                stmt.setString(1, roleGroup.getRoleGroupId());
                stmt.setString(2, roleGroup.getRoleGroupName());
                stmt.setString(3, roleGroup.getDescription());
                stmt.executeUpdate();
            }

            // Thêm danh sách chức năng và quyền vào bảng role_group_function
            if (roleGroup.getFunctions() != null && !roleGroup.getFunctions().isEmpty()) {
                String sqlFunction = "INSERT INTO quanlivanphongpham.role_group_function (roleGroupId, functionId, action) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sqlFunction)) {
                    for (String functionAction : roleGroup.getFunctions()) {
                        String[] parts = functionAction.split("_");
                        if (parts.length != 2) continue; // Bỏ qua nếu định dạng không đúng
                        String functionName = parts[0];
                        String action = parts[1];
                        String functionId = getFunctionIdByName(conn, functionName);
                        if (functionId != null) {
                            stmt.setString(1, roleGroup.getRoleGroupId());
                            stmt.setString(2, functionId);
                            stmt.setString(3, action);
                            stmt.executeUpdate();
                        }
                    }
                }
            }

            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Lỗi khi thêm nhóm quyền: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Cập nhật nhóm quyền và danh sách chức năng + quyền
    public void updateRoleGroup(RoleGroup roleGroup) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                throw new SQLException("Không thể kết nối đến database.");
            }
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Cập nhật bảng role_group
            String sqlRoleGroup = "UPDATE quanlivanphongpham.role_group SET roleGroupName = ?, description = ? WHERE roleGroupId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlRoleGroup)) {
                stmt.setString(1, roleGroup.getRoleGroupName());
                stmt.setString(2, roleGroup.getDescription());
                stmt.setString(3, roleGroup.getRoleGroupId());
                stmt.executeUpdate();
            }

            // Xóa các quyền cũ trong bảng role_group_function
            String sqlDeleteFunctions = "DELETE FROM quanlivanphongpham.role_group_function WHERE roleGroupId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlDeleteFunctions)) {
                stmt.setString(1, roleGroup.getRoleGroupId());
                stmt.executeUpdate();
            }

            // Thêm danh sách chức năng và quyền mới vào bảng role_group_function
            if (roleGroup.getFunctions() != null && !roleGroup.getFunctions().isEmpty()) {
                String sqlInsertFunction = "INSERT INTO quanlivanphongpham.role_group_function (roleGroupId, functionId, action) VALUES (?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sqlInsertFunction)) {
                    for (String functionAction : roleGroup.getFunctions()) {
                        String[] parts = functionAction.split("_");
                        if (parts.length != 2) continue; // Bỏ qua nếu định dạng không đúng
                        String functionName = parts[0];
                        String action = parts[1];
                        String functionId = getFunctionIdByName(conn, functionName);
                        if (functionId != null) {
                            stmt.setString(1, roleGroup.getRoleGroupId());
                            stmt.setString(2, functionId);
                            stmt.setString(3, action);
                            stmt.executeUpdate();
                        }
                    }
                }
            }

            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Lỗi khi cập nhật nhóm quyền: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Xóa nhóm quyền và các chức năng + quyền liên quan
    public void deleteRoleGroup(String roleGroupId) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn == null) {
                throw new SQLException("Không thể kết nối đến database.");
            }
            conn.setAutoCommit(false); // Bắt đầu transaction

            // Xóa các quyền liên quan trong bảng role_group_function
            String sqlDeleteFunctions = "DELETE FROM quanlivanphongpham.role_group_function WHERE roleGroupId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlDeleteFunctions)) {
                stmt.setString(1, roleGroupId);
                stmt.executeUpdate();
            }

            // Xóa nhóm quyền trong bảng role_group
            String sqlDeleteRoleGroup = "DELETE FROM quanlivanphongpham.role_group WHERE roleGroupId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sqlDeleteRoleGroup)) {
                stmt.setString(1, roleGroupId);
                stmt.executeUpdate();
            }

            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println("Lỗi khi xóa nhóm quyền: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Phương thức hỗ trợ: Lấy functionId từ functionName
    private String getFunctionIdByName(Connection conn, String functionName) throws SQLException {
        String sql = "SELECT functionId FROM quanlivanphongpham.function WHERE functionName = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, functionName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("functionId");
                }
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }
}