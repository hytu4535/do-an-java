/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.VanPhongPhamDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.VanPhongPham;
import view.sanpham.SanPhamPanel;

/**
 *
 * @author ACER
 */
public class SanPhamController {
    private SanPhamPanel sanPhamPanel;
    private DefaultTableModel tableModel;
    private VanPhongPhamDAO sanPhamDAO;

    public SanPhamController(SanPhamPanel sanPhamPanel) {
        this.sanPhamPanel = sanPhamPanel;
        this.tableModel = (DefaultTableModel) sanPhamPanel.getProductTable().getModel();
        this.sanPhamDAO = new VanPhongPhamDAO();
        loadDataFromDatabase();
    }
    
private void loadDataFromDatabase() {
    List<VanPhongPham> danhSachSanPham = sanPhamDAO.getAllSanPhams();
    updateTable(danhSachSanPham);
}
private void updateTable(List<VanPhongPham> danhSachSanPham) {
        tableModel.setRowCount(0); // xoa du lieu cu
        for (VanPhongPham sp : danhSachSanPham) {
            tableModel.addRow(new Object[]{
                    sp.getMaVatPham(), sp.getTenVatPham(), sp.getSoLuong(), sp.getLoaiVatPham(), sp.getGia(),
                    sp.getThuongHieu(), sp.getChatLieu(), sp.getDoDay(), sp.getMoTa(), sp.getXuatXu(), sp.getTrangThai()
            });
        }
    }
 
public void themSanPham(VanPhongPham sp) {
    sanPhamDAO.addSanPham(sp);
    loadDataFromDatabase();
}

public void suaSanPham(String maSP, VanPhongPham spMoi) {
    sanPhamDAO.updateSanPham(spMoi);
    loadDataFromDatabase();
}

public void xoaSanPham(String maSP) {
    sanPhamDAO.deleteSanPham(maSP);
    loadDataFromDatabase();
}

public void timKiemSanPham(String tuKhoa, String tieuChi) {
    List<VanPhongPham> ketQua = sanPhamDAO.searchSanPhams(tuKhoa, tieuChi);
    updateTable(ketQua);
}
    
}
