package controller;

import dao.SupplierDAO;
import model.Supplier;
import view.nhacungcap.SupplierPanel;
import java.util.List;

public class SupplierController {
    private SupplierPanel view;
    private SupplierDAO dao;

    public SupplierController(SupplierPanel view) {
        this.view = view;
        this.dao = new SupplierDAO();
    }

    // Tải danh sách nhà cung cấp
    public void loadSuppliers() {
        List<Supplier> suppliers = dao.getAllSuppliers();
        view.displaySuppliers(suppliers);
    }

    // Thêm nhà cung cấp
    public void addSupplier(Supplier supplier) {
        if (dao.addSupplier(supplier)) {
            view.showMessage("Thêm nhà cung cấp thành công!");
            loadSuppliers();
        } else {
            view.showMessage("Lỗi khi thêm nhà cung cấp!");
        }
    }

    // Cập nhật nhà cung cấp
    public void updateSupplier(Supplier supplier) {
        if (dao.updateSupplier(supplier)) {
            view.showMessage("Cập nhật nhà cung cấp thành công!");
            loadSuppliers();
        } else {
            view.showMessage("Lỗi khi cập nhật nhà cung cấp!");
        }
    }

    // Xóa nhà cung cấp
    public void deleteSupplier(String maNhaCungCap) {
        if (dao.deleteSupplier(maNhaCungCap)) {
            view.showMessage("Xóa nhà cung cấp thành công!");
            loadSuppliers();
        } else {
            view.showMessage("Lỗi khi xóa nhà cung cấp!");
        }
    }

    // Lấy nhà cung cấp theo mã
    public Supplier getSupplierById(String maNhaCungCap) {
        return dao.getSupplierById(maNhaCungCap);
    }
}