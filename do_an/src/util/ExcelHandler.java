package util;

import dao.AccountDAO;
import dao.RoleGroupDAO;
import model.Account;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}