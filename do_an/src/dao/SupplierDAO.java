package dao;

import model.Supplier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    private Connection connection;

    public SupplierDAO() {
        /*
        try {
            String url = "jdbc:mysql://localhost:3306/quanlivanphongpham?useSSL=false";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
        try{
            this.connection = DBConnection.getConnection();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Add a new supplier
    public boolean addSupplier(Supplier supplier) {
        String query = "INSERT INTO nhacungcap (maNhaCungCap, tenNhaCungCap, Sdt, diaChi) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, supplier.getMaNhaCungCap());
            ps.setString(2, supplier.getTenNhaCungCap());
            ps.setString(3, supplier.getSdt());
            ps.setString(4, supplier.getDiaChi());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing supplier
    public boolean updateSupplier(Supplier supplier) {
        String query = "UPDATE nhacungcap SET tenNhaCungCap = ?, Sdt = ?, diaChi = ? WHERE maNhaCungCap = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, supplier.getTenNhaCungCap());
            ps.setString(2, supplier.getSdt());
            ps.setString(3, supplier.getDiaChi());
            ps.setString(4, supplier.getMaNhaCungCap());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a supplier
    public boolean deleteSupplier(String maNhaCungCap) {
        String query = "DELETE FROM nhacungcap WHERE maNhaCungCap = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, maNhaCungCap);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all suppliers
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM nhacungcap";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Supplier supplier = new Supplier(
                    rs.getString("maNhaCungCap"),
                    rs.getString("tenNhaCungCap"),
                    rs.getString("Sdt"),
                    rs.getString("diaChi")
                );
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    // Get supplier by ID
    public Supplier getSupplierById(String maNhaCungCap) {
        String query = "SELECT * FROM nhacungcap WHERE maNhaCungCap = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, maNhaCungCap);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Supplier(
                    rs.getString("maNhaCungCap"),
                    rs.getString("tenNhaCungCap"),
                    rs.getString("Sdt"),
                    rs.getString("diaChi")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}