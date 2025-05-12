package controller;

import dao.VanPhongPhamDAO;
import java.io.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.VanPhongPham;
import view.sanpham.SanPhamPanel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
        tableModel.setRowCount(0); // xóa dữ liệu cũ
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

    public void xuatExcel(DefaultTableModel model) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".xlsx");
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("SanPham");

                // Tạo header
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < model.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(model.getColumnName(i));
                }

                // Ghi dữ liệu
                for (int i = 0; i < model.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object value = model.getValueAt(i, j);
                        row.createCell(j).setCellValue(value != null ? value.toString() : "");
                    }
                }

                try (FileOutputStream fileOut = new FileOutputStream(fileToSave)) {
                    workbook.write(fileOut);
                }

                JOptionPane.showMessageDialog(null, "Xuất Excel thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi xuất Excel!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void nhapExcel(DefaultTableModel model) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn file Excel để nhập");
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try (FileInputStream fis = new FileInputStream(selectedFile);
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheetAt(0);
                int count = 0;
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
    Row row = sheet.getRow(i);
    if (row != null) {
        VanPhongPham sp = new VanPhongPham();

       
        sp.setMaVatPham(getStringValue(row.getCell(0)));
        sp.setTenVatPham(getStringValue(row.getCell(1)));
        sp.setSoLuong((int) getNumericValue(row.getCell(2)));
        sp.setLoaiVatPham(getStringValue(row.getCell(3)));
        sp.setGia(getNumericValue(row.getCell(4)));
        sp.setThuongHieu(getStringValue(row.getCell(5)));
        sp.setChatLieu(getStringValue(row.getCell(6)));
        sp.setDoDay(getNumericValue(row.getCell(7)));
        sp.setMoTa(getStringValue(row.getCell(8)));
        sp.setXuatXu(getStringValue(row.getCell(9)));
        sp.setTrangThai((int) getNumericValue(row.getCell(10)));
        sanPhamDAO.addSanPham(sp);
        count++;
    }
}


                JOptionPane.showMessageDialog(null, "Nhập Excel thành công! Đã thêm " + count + " sản phẩm.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadDataFromDatabase();
            } catch (IOException | NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi nhập Excel!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private String getStringValue(Cell cell) { //phuc vu phan nhap 
    if (cell == null) return "";
    if (cell.getCellType() == CellType.STRING) {
        return cell.getStringCellValue().trim();
    } else if (cell.getCellType() == CellType.NUMERIC) {
        return String.valueOf((int) cell.getNumericCellValue());
    } else if (cell.getCellType() == CellType.BOOLEAN) {
        return String.valueOf(cell.getBooleanCellValue());
    } else {
        return "";
    }
}

private double getNumericValue(Cell cell) { //phuc vu phan nhap excel
    if (cell == null) return 0.0;
    if (cell.getCellType() == CellType.NUMERIC) {
        return cell.getNumericCellValue();
    } else if (cell.getCellType() == CellType.STRING) {
        try {
            return Double.parseDouble(cell.getStringCellValue().trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    } else {
        return 0.0;
    }
}

}
