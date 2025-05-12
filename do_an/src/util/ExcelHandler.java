package util;

import dao.AccountDAO;
import dao.RoleGroupDAO;
import model.Account;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.PhieuNhap;
import model.PhieuXuat;

import java.sql.Timestamp;
import java.text.ParseException;

import model.ChiTietPhieuNhap;
import model.ChiTietPhieuXuat;

public class ExcelHandler {
    private final AccountDAO accountDAO;
    private final RoleGroupDAO roleGroupDAO;
    private final Map<String, String> roleMapping;

    public ExcelHandler() {
        this.accountDAO = new AccountDAO();
        this.roleGroupDAO = new RoleGroupDAO();
        this.roleMapping = new HashMap<>();
        loadRoleMapping();
    }
    
    // hàm lấy đói tượng để truy cập phương thức
    public static ExcelHandler getInstance() {
        return new ExcelHandler();
    }

    private void loadRoleMapping() {
        roleGroupDAO.getAllRoleGroups().forEach(roleGroup ->
            roleMapping.put(roleGroup.getRoleGroupName(), roleGroup.getRoleGroupId()));
    }

    public void exportAccountsToExcel(List<Account> accounts, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Accounts");

        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setAlignment(HorizontalAlignment.LEFT);

        Row headerRow = sheet.createRow(0);
        String[] columns = {"STT", "Tên tài khoản", "Họ tên", "Vai trò", "Trạng thái", "Ngày sinh", "Địa chỉ", "Điện thoại", "Email"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerStyle);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(i + 1);
            row.createCell(1).setCellValue(account.getUserName());
            row.createCell(2).setCellValue(account.getFullName());
            row.createCell(3).setCellValue(account.getRoleGroupName());
            row.createCell(4).setCellValue(account.getStatus() == 1 ? "Hoạt động" : "Ngưng hoạt động");
            row.createCell(5).setCellValue(account.getNamSinh() != null ? sdf.format(account.getNamSinh()) : "");
            row.createCell(6).setCellValue(account.getDiaChi() != null ? account.getDiaChi() : "");
            row.createCell(7).setCellValue(account.getDienThoai() != null ? account.getDienThoai() : "");
            row.createCell(8).setCellValue(account.getEmail() != null ? account.getEmail() : "");

            for (int j = 0; j < columns.length; j++) {
                row.getCell(j).setCellStyle(dataStyle);
            }
        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            JOptionPane.showMessageDialog(null, "Xuất file Excel thành công tại: " + filePath, "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất file Excel: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean importAccountsFromExcel(String filePath) {
        List<Account> accounts = accountDAO.getAllAccounts();
        Map<String, Account> existingAccounts = new HashMap<>();
        accounts.forEach(acc -> existingAccounts.put(acc.getUserName(), acc));

        try (FileInputStream fileIn = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileIn)) {
            Sheet sheet = workbook.getSheetAt(0);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            Row headerRow = sheet.getRow(0);
            String[] expectedHeaders = {"STT", "Tên tài khoản", "Họ tên", "Vai trò", "Trạng thái", "Ngày sinh", "Địa chỉ", "Điện thoại", "Email"};
            for (int i = 0; i < expectedHeaders.length; i++) {
                if (!headerRow.getCell(i).getStringCellValue().equals(expectedHeaders[i])) {
                    JOptionPane.showMessageDialog(null, "File Excel không đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String userName = row.getCell(1) != null ? row.getCell(1).getStringCellValue().trim() : "";
                String fullName = row.getCell(2) != null ? row.getCell(2).getStringCellValue().trim() : "";
                String roleGroupName = row.getCell(3) != null ? row.getCell(3).getStringCellValue().trim() : "";
                String statusStr = row.getCell(4) != null ? row.getCell(4).getStringCellValue().trim() : "";
                String namSinhStr = row.getCell(5) != null ? row.getCell(5).getStringCellValue().trim() : "";
                String diaChi = row.getCell(6) != null ? row.getCell(6).getStringCellValue().trim() : "";
                String dienThoai = row.getCell(7) != null ? row.getCell(7).getStringCellValue().trim() : "";
                String email = row.getCell(8) != null ? row.getCell(8).getStringCellValue().trim() : "";

                if (userName.isEmpty() || fullName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Dòng " + (i + 1) + ": Tên tài khoản và Họ tên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                if (!existingAccounts.containsKey(userName)) {
                    JOptionPane.showMessageDialog(null, "Dòng " + (i + 1) + ": Tên tài khoản '" + userName + "' không tồn tại trong cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                String roleGroupId = roleMapping.get(roleGroupName);
                if (roleGroupId == null) {
                    JOptionPane.showMessageDialog(null, "Dòng " + (i + 1) + ": Vai trò '" + roleGroupName + "' không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                int status = statusStr.equals("Hoạt động") ? 1 : 0;

                Date namSinh = null;
                if (!namSinhStr.isEmpty()) {
                    try {
                        namSinh = new Date(sdf.parse(namSinhStr).getTime());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Dòng " + (i + 1) + ": Ngày sinh phải có định dạng dd/MM/yyyy!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }

                if (!email.isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    JOptionPane.showMessageDialog(null, "Dòng " + (i + 1) + ": Email không đúng định dạng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                // Loại bỏ khoảng trắng trong số điện thoại trước khi kiểm tra
                if (!dienThoai.isEmpty()) {
                    dienThoai = dienThoai.replaceAll("\\s+", ""); // Loại bỏ tất cả khoảng trắng
                    if (!dienThoai.matches("\\d{10,11}")) {
                        JOptionPane.showMessageDialog(null, "Dòng " + (i + 1) + ": Điện thoại phải là số và có 10-11 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }

                Account account = new Account();
                account.setUserName(userName);
                account.setFullName(fullName);
                account.setRoleGroupId(roleGroupId);
                account.setRoleGroupName(roleGroupName);
                account.setStatus(status);
                account.setNamSinh(namSinh);
                account.setDiaChi(diaChi.isEmpty() ? null : diaChi);
                account.setDienThoai(dienThoai.isEmpty() ? null : dienThoai);
                account.setEmail(email.isEmpty() ? null : email);

                accountDAO.updateAccount(account);
            }

            JOptionPane.showMessageDialog(null, "Nhập file Excel thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập file Excel: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }
    
    //############< code >##############
    // các hàm dưới đây để tái sử dụng cho nhiều phần trong view
    
    // hàm để tạo các cột dữ liệu gồm một đối tượng sheet và mảng String[] tên các cột
    public void createHeaderRow(Workbook workbook, Sheet mySheet, int index, String[] cotDuLieu) {
        // tạo hàng dữ liệu
        Row headerRow = mySheet.createRow(index);
        
        // cấu hình màu sắc,...
        CellStyle headerStyle = workbook.createCellStyle();
        
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        // đưa các dữ liệu vào các cell
        for(int i = 0; i < cotDuLieu.length; ++i) {
            // tạo cell tại vị trí i
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(cotDuLieu[i]);
            headerCell.setCellStyle(headerStyle);
        }
    }
    
    // hàm để tạo các dòng dữ liệu cho phiếu nhập
    public void createPhieuNhapContentRow(Workbook workbook, Sheet mySheet, int index, PhieuNhap phieu) {
        Row contentRow = mySheet.createRow(index);
        
        // cái này để tạo format cho ngày
        DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // thêm dữ liệu vào 
        Cell contentCell = null;
                
        // mã phiếu
        contentCell = contentRow.createCell(0);
        contentCell.setCellValue(phieu.getMaPhieu());
        
        // nhà cung cấp
        contentCell = contentRow.createCell(1);
        contentCell.setCellValue(phieu.getMaNhaCungCap());
        
        // người tạo
        contentCell = contentRow.createCell(2);
        contentCell.setCellValue(phieu.getNguoiTao());
        
        // tổng tiền
        contentCell = contentRow.createCell(3);
        contentCell.setCellValue(phieu.getTongTien());
        
        // thời gian tạo
        // chuyển thời gian tạo thành LocalDate
        LocalDateTime dateTime = phieu.getThoiGianTao().toLocalDateTime();
        
        contentCell = contentRow.createCell(4);
        contentCell.setCellValue(dateTime.format(Formatter));
    }
    
     // hàm để tạo các dòng dữ liệu cho chi tiết phiếu nhập
    public void createChiTietPhieuNhapContentRow(Workbook workbook, Sheet mySheet, int index, ChiTietPhieuNhap ctphieu) {
        Row contentRow = mySheet.createRow(index);
        
        // cái này để tạo format cho ngày
        // DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // thêm dữ liệu vào 
        Cell contentCell = null;
                
        // mã phiếu
        contentCell = contentRow.createCell(0);
        contentCell.setCellValue(ctphieu.getMaPhieu());
        
        // mã văn phòng phẩm
        contentCell = contentRow.createCell(1);
        contentCell.setCellValue(ctphieu.getMaVatPham());
        
        // số lượng
        contentCell = contentRow.createCell(2);
        contentCell.setCellValue(ctphieu.getSoLuong());
        
        // đơn giá
        contentCell = contentRow.createCell(3);
        contentCell.setCellValue(ctphieu.getDonGia());
        
    }
    
    
    
    // hàm để tạo các dòng dữ liệu cho phiếu xuất
    public void createPhieuXuatContentRow(Workbook workbook, Sheet mySheet, int index, PhieuXuat phieu) {
        Row contentRow = mySheet.createRow(index);
        
        // cái này để tạo format cho ngày
        DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // thêm dữ liệu vào 
        Cell contentCell = null;
                
        // mã phiếu
        contentCell = contentRow.createCell(0);
        contentCell.setCellValue(phieu.getMaPhieu());
        
        // người tạo
        contentCell = contentRow.createCell(1);
        contentCell.setCellValue(phieu.getNguoiTao());
        
        // tổng tiền
        contentCell = contentRow.createCell(2);
        contentCell.setCellValue(phieu.getTongTien());
        
        // thời gian tạo
        // chuyển thời gian tạo thành LocalDate
        LocalDateTime dateTime = phieu.getThoiGianTao().toLocalDateTime();
        
        contentCell = contentRow.createCell(3);
        contentCell.setCellValue(dateTime.format(Formatter));
    }
    
    // hàm để tạo các dòng dữ liệu cho chi tiết phiếu xuất
    public void createChiTietPhieuXuatContentRow(Workbook workbook, Sheet mySheet, 
            int index, ChiTietPhieuXuat ctphieu) {
        Row contentRow = mySheet.createRow(index);
        
        // cái này để tạo format cho ngày
        // DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // thêm dữ liệu vào 
        Cell contentCell = null;
                
        // mã phiếu
        contentCell = contentRow.createCell(0);
        contentCell.setCellValue(ctphieu.getMaPhieu());
        
        // mã văn phòng phẩm
        contentCell = contentRow.createCell(1);
        contentCell.setCellValue(ctphieu.getMaVatPham());
        
        // số lượng
        contentCell = contentRow.createCell(2);
        contentCell.setCellValue(ctphieu.getSoLuong());
        
        // đơn giá
        contentCell = contentRow.createCell(3);
        contentCell.setCellValue(ctphieu.getDonGia());
        
    }
    
    
    // xuất file excel phiếu nhập
    public void XuatFilePhieuNhapExcel(ArrayList<PhieuNhap> danhSachPhieu, 
            ArrayList<ChiTietPhieuNhap> danhSachCTPhieu, String filePath) {
        
        Workbook workBook = new XSSFWorkbook();
        // trang tinh phieu nhap
        Sheet mySheet = workBook.createSheet("phieu nhap");
        // trang tinh chi tiet phieu
        Sheet mySheet2 = workBook.createSheet("chi tiet phieu");
        
        
        // tải dữ liệu cho mySheet
        
        // tạo hàng dữ liệu cho header
        String[] header1 = new String[] {"Mã phiếu", "Nhà cung cấp", 
                                                                   "Người tạo", "Tổng tiền", "Thời gian tạo"};
        
        createHeaderRow(workBook, mySheet, 0, header1);
        
         // chỉnh cho kích thước các cột rộng 15 kí tự
        for(int i = 0; i < header1.length; ++i) {
            mySheet.setColumnWidth(i, 15 * 256);
        }
        
        // tạo hàng dữ liệu
        for(int i = 0; i < danhSachPhieu.size(); ++i) {
            PhieuNhap phieu = danhSachPhieu.get(i);
            createPhieuNhapContentRow(workBook, mySheet, i + 1, phieu);
        }
        
        // tải dữ liệu cho mySheet2
        
        // tạo hàng dữ liệu cho header
        String[] header2 = new String[] {"Mã phiếu", "Mã vật phẩm", 
                                                                   "Số lượng", "Đơn giá"};
        
        createHeaderRow(workBook, mySheet2, 0, header2);
        
         // chỉnh cho kích thước các cột rộng 15 kí tự
        for(int i = 0; i < header2.length; ++i) {
            mySheet.setColumnWidth(i, 15 * 256);
        }
        
        // tạo hàng dữ liệu
        for(int i = 0; i < danhSachCTPhieu.size(); ++i) {
            ChiTietPhieuNhap ctphieu = danhSachCTPhieu.get(i);
            createChiTietPhieuNhapContentRow(workBook, mySheet2, i + 1, ctphieu);
        }
        
        // xuất file ra
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workBook.write(fileOut);
            JOptionPane.showMessageDialog(
                    null, 
                    "Xuất file Excel thành công tại: " + filePath, 
                    "Thành công", 
                    JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Lỗi khi xuất file Excel: " + e.getMessage(), 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            
            e.printStackTrace();
        } 
        finally {
            try {
                workBook.close();
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    // xuất file excel phiếu nhập
    public void XuatFilePhieuXuatExcel(ArrayList<PhieuXuat> danhSachPhieu, 
            ArrayList<ChiTietPhieuXuat> danhSachCTPhieu, String filePath) {
        Workbook workBook = new XSSFWorkbook();
        
        Sheet mySheet = workBook.createSheet("phieu xuat");
        
        Sheet mySheet2 = workBook.createSheet("chi tiet phieu");
        
        // tạo hàng dữ liệu cho header
        String[] header = new String[] {"Mã phiếu", "Người tạo", "Tổng tiền", "Thời gian tạo"};
        
        createHeaderRow(workBook, mySheet, 0, header);
        
         // chỉnh cho kích thước các cột rộng 15 kí tự
        for(int i = 0; i < header.length; ++i) {
            mySheet.setColumnWidth(i, 15 * 256);
        }
        
        // tạo hàng dữ liệu
        for(int i = 0; i < danhSachPhieu.size(); ++i) {
            PhieuXuat phieu = danhSachPhieu.get(i);
            createPhieuXuatContentRow(workBook, mySheet, i + 1, phieu);
        }
        
        // tải dữ liệu cho mySheet2
        
        // tạo hàng dữ liệu cho header
        String[] header2 = new String[] {"Mã phiếu", "Mã vật phẩm", 
                                                                   "Số lượng", "Đơn giá"};
        
        createHeaderRow(workBook, mySheet2, 0, header2);
        
         // chỉnh cho kích thước các cột rộng 15 kí tự
        for(int i = 0; i < header2.length; ++i) {
            mySheet.setColumnWidth(i, 15 * 256);
        }
        
        // tạo hàng dữ liệu
        for(int i = 0; i < danhSachCTPhieu.size(); ++i) {
            ChiTietPhieuXuat ctphieu = danhSachCTPhieu.get(i);
            createChiTietPhieuXuatContentRow(workBook, mySheet2, i + 1, ctphieu);
        }
        
        // xuất file ra
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workBook.write(fileOut);
            JOptionPane.showMessageDialog(
                    null, 
                    "Xuất file Excel thành công tại: " + filePath, 
                    "Thành công", 
                    JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Lỗi khi xuất file Excel: " + e.getMessage(), 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            
            e.printStackTrace();
        } 
        finally {
            try {
                workBook.close();
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
    //#####################
    //Nhập file excel sẽ có hai hàm trả về ArrayList<> của phieu và chi tiết phiếu đề controller làm việc
    //#####################
   
    //###################< PHIẾU NHẬP >#####################
    // nhập file excel phiếu nhập
    public ArrayList<PhieuNhap> nhapFileExcelPhieuNhap(String filePath) {
        ArrayList<PhieuNhap> danhsachPhieuNhapExcel = new ArrayList<>();
        
        SimpleDateFormat Sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        // đọc dữ liệu từ file ở đường dẫn filePath và xử lý theo định dạng excel
        try(
                FileInputStream fileInput = new FileInputStream(filePath);
                Workbook workBook = new XSSFWorkbook(fileInput);        
        ) {
            // lấy ra sheet đầu tiên trong file( chọn sheet cần đọc)
            Sheet mySheet = workBook.getSheetAt(0);
            
            // bắt đầu đọc
            
            // đọc từ dòng 1 vì bỏ qua dòng tiêu đề
            for(int i = 1; i <= mySheet.getLastRowNum(); ++i) {
                // lấy dòng dữ liệu
                Row row = mySheet.getRow(i);
                
                // nếu dòng không trống
                if(row != null) {
                    // mã phiếu
                    String maPhieu = row.getCell(0).getStringCellValue();
                    
                    // nhà cung cấp
                    String maNhaCungCap = row.getCell(1).getStringCellValue();
                    
                    // người tạo
                    String nguoiTao = row.getCell(2).getStringCellValue();
                    
                    // tổng tiền
                    double tongTien = (double) row.getCell(3).getNumericCellValue();
                    
                    // thời gian tạo
                    
                    // lấy ngày từ cell
                    Timestamp thoiGianTao = toTimestamp(row.getCell(4).getStringCellValue().trim());
                    
                    // tạo đối tượng phiếu nhập và đưa vào danh sách
                    PhieuNhap phieuMoi = new PhieuNhap(maPhieu, thoiGianTao, 
                            nguoiTao, maNhaCungCap, tongTien);
                    
                    danhsachPhieuNhapExcel.add(phieuMoi);
                }
            }
           
        } 
        // bắt lỗi liên quan tới file
        catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        } 
        // các lỗi khác
        catch (NullPointerException e) {
            System.out.println("Lỗi dữ liệu null không mong muốn!");
        }
  
        return danhsachPhieuNhapExcel;
    }
    
    // nhập file excel chi tiết phiếu nhập
    public ArrayList<ChiTietPhieuNhap> nhapFileExcelChiTietPhieuNhap(String filePath) {
        ArrayList<ChiTietPhieuNhap> danhsachCTPhieuNhapExcel = new ArrayList<>();
        
        // đọc dữ liệu từ file ở đường dẫn filePath và xử lý theo định dạng excel
        try(
                FileInputStream fileInput = new FileInputStream(filePath);
                Workbook workBook = new XSSFWorkbook(fileInput);        
        ) {
            // lấy ra sheet đầu tiên trong file( chọn sheet cần đọc)
            Sheet mySheet = workBook.getSheetAt(1);
            
            // bắt đầu đọc
            
            // đọc từ dòng 1 vì bỏ qua dòng tiêu đề
            for(int i = 1; i <= mySheet.getLastRowNum(); ++i) {
                // lấy dòng dữ liệu
                Row row = mySheet.getRow(i);
                
                // nếu dòng không trống
                if(row != null) {
                   // bắt lỗi khi đọc
                   try {
                        //  mã phiếu
                        String maPhieu = row.getCell(0).getStringCellValue();

                        // Các cột chi tiết
                        String maVatPham = row.getCell(1).getStringCellValue();
                        
                        int soLuong = (int) row.getCell(2).getNumericCellValue();
                        
                        double donGia = row.getCell(3).getNumericCellValue();

                    // Tạo chi tiết phiếu nhập và thêm vào danh sách
                    ChiTietPhieuNhap ctphieuMoi = new ChiTietPhieuNhap(maPhieu, maVatPham, soLuong, donGia);
                    
                    danhsachCTPhieuNhapExcel.add(ctphieuMoi);
                   }
                   catch(Exception e) {
                       System.out.println("Lỗi đọc dữ liệu ở dòng "+ i );
                   }
                }
            }  
        } 
        // bắt lỗi liên quan tới file
        catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        } 
        // các lỗi khác
        catch (NullPointerException e) {
            System.out.println("Lỗi dữ liệu null không mong muốn!");
        }
  
        return danhsachCTPhieuNhapExcel;
    }
 
    
    //###################< PHIẾU XUẤT >#####################
    // nhập file excel phiếu nhập
    public ArrayList<PhieuXuat> nhapFileExcelPhieuXuat(String filePath) {
        ArrayList<PhieuXuat> danhsachPhieuXuatExcel = new ArrayList<>();
        
        SimpleDateFormat Sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        // đọc dữ liệu từ file ở đường dẫn filePath và xử lý theo định dạng excel
        try(
                FileInputStream fileInput = new FileInputStream(filePath);
                Workbook workBook = new XSSFWorkbook(fileInput);        
        ) {
            // lấy ra sheet đầu tiên trong file( chọn sheet cần đọc)
            Sheet mySheet = workBook.getSheetAt(0);
            
            // bắt đầu đọc
            
            // đọc từ dòng 1 vì bỏ qua dòng tiêu đề
            for(int i = 1; i <= mySheet.getLastRowNum(); ++i) {
                // lấy dòng dữ liệu
                Row row = mySheet.getRow(i);
                
                // nếu dòng không trống
                if(row != null) {
                    // mã phiếu
                    String maPhieu = row.getCell(0).getStringCellValue();
  
                    // người tạo
                    String nguoiTao = row.getCell(1).getStringCellValue();
                    
                    // tổng tiền
                    double tongTien = (double) row.getCell(2).getNumericCellValue();
                    
                    // thời gian tạo
                    
                    // lấy ngày từ cell
                    Timestamp thoiGianTao = toTimestamp(row.getCell(3).getStringCellValue().trim());
                    
                    // tạo đối tượng phiếu nhập và đưa vào danh sách
                    PhieuXuat phieuMoi = new PhieuXuat(maPhieu, thoiGianTao, 
                            nguoiTao, tongTien);
                    
                    danhsachPhieuXuatExcel.add(phieuMoi);
                }
            }
           
        } 
        // bắt lỗi liên quan tới file
        catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        } 
        // các lỗi khác
        catch (NullPointerException e) {
            System.out.println("Lỗi dữ liệu null không mong muốn!");
        }
  
        return danhsachPhieuXuatExcel;
    }
    
    // nhập file excel chi tiết phiếu nhập
    public ArrayList<ChiTietPhieuXuat> nhapFileExcelChiTietPhieuXuat(String filePath) {
        ArrayList<ChiTietPhieuXuat> danhsachCTPhieuXuatExcel = new ArrayList<>();
        
        // đọc dữ liệu từ file ở đường dẫn filePath và xử lý theo định dạng excel
        try(
                FileInputStream fileInput = new FileInputStream(filePath);
                Workbook workBook = new XSSFWorkbook(fileInput);        
        ) {
            // lấy ra sheet đầu tiên trong file( chọn sheet cần đọc)
            Sheet mySheet = workBook.getSheetAt(1);
            
            // bắt đầu đọc
            
            // đọc từ dòng 1 vì bỏ qua dòng tiêu đề
            for(int i = 1; i <= mySheet.getLastRowNum(); ++i) {
                // lấy dòng dữ liệu
                Row row = mySheet.getRow(i);
                
                // nếu dòng không trống
                if(row != null) {
                   // bắt lỗi khi đọc
                   try {
                        //  mã phiếu
                        String maPhieu = row.getCell(0).getStringCellValue();

                        // Các cột chi tiết
                        String maVatPham = row.getCell(1).getStringCellValue();
                        
                        int soLuong = (int) row.getCell(2).getNumericCellValue();
                        
                        double donGia = row.getCell(3).getNumericCellValue();

                    // Tạo chi tiết phiếu nhập và thêm vào danh sách
                    ChiTietPhieuXuat ctphieuMoi = new ChiTietPhieuXuat(maPhieu, maVatPham, soLuong, donGia);
                    
                    danhsachCTPhieuXuatExcel.add(ctphieuMoi);
                   }
                   catch(Exception e) {
                       System.out.println("Lỗi đọc dữ liệu ở dòng "+ i );
                   }
                }
            }  
        } 
        // bắt lỗi liên quan tới file
        catch (IOException e) {
            System.out.println("Lỗi đọc file: " + e.getMessage());
        } 
        // các lỗi khác
        catch (NullPointerException e) {
            System.out.println("Lỗi dữ liệu null không mong muốn!");
        }
  
        return danhsachCTPhieuXuatExcel;
    }
    
    // hàm hỗ trợ
    
    // hàm trả về Timestamp 
    public Timestamp toTimestamp(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) return null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            // chuyển về java.util.Date chứ không phải java.sql.Date 
            java.util.Date date = sdf.parse(dateStr.trim());
            return new Timestamp(date.getTime());
        } catch (ParseException e) {
            System.err.println("Lỗi chuyển ngày: " + dateStr);
            return null;
        }
    }

            
}
