package controller;

import dao.AccountDAO;
import model.Account;
import view.nguoidung.NguoiDungPanel;
import view.nguoidung.DataTablePanel;
import view.nguoidung.InfoPanel;
import view.nguoidung.ButtonPanel;
import view.nguoidung.UserFormDialog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NguoiDungController {
    private NguoiDungPanel view;
    private AccountDAO accountDAO;
    private List<Account> accounts;

    public NguoiDungController(NguoiDungPanel view) {
        this.view = view;
        this.accountDAO = new AccountDAO();
        setupButtonActions();
        setupTableSelectionListener();
        loadData(); // Gọi loadData() sau khi setup để đảm bảo dữ liệu được tải ngay
    }

    public void loadData() {
        accounts = accountDAO.getAllAccounts();
        DataTablePanel tablePanel = view.getDataTablePanel();
        Object[][] data = new Object[accounts.size()][8];
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            data[i][0] = String.valueOf(i + 1); // STT
            data[i][1] = account.getUserName();
            data[i][2] = account.getFullName();
            data[i][3] = account.getRole();
            data[i][4] = account.getStatus() == 1 ? "Hoạt động" : "Ngưng hoạt động";
            data[i][5] = account.getNamSinh() != null ? sdf.format(account.getNamSinh()) : "";
            data[i][6] = account.getDiaChi() != null ? account.getDiaChi() : "";
            data[i][7] = account.getDienThoai() != null ? account.getDienThoai() : "";
        }
        tablePanel.getTableModel().setDataVector(data, new String[]{"STT", "Tên tài khoản", "Họ tên", "Vai trò", "Trạng thái", "Ngày sinh", "Địa chỉ", "Điện thoại"});
        tablePanel.adjustColumnWidths();
    }

    private void setupTableSelectionListener() {
        DataTablePanel tablePanel = view.getDataTablePanel();
        tablePanel.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tablePanel.getTable().getSelectedRow();
                    if (selectedRow >= 0 && selectedRow < accounts.size()) {
                        Account selectedAccount = accounts.get(selectedRow);
                        InfoPanel infoPanel = view.getInfoPanel();
                        infoPanel.updateInfo(selectedAccount);
                    }
                }
            }
        });
    }

    private void setupButtonActions() {
        ButtonPanel buttonPanel = view.getButtonPanel();

        // Nút Thêm
        buttonPanel.getAddButton().addActionListener(e -> {
            UserFormDialog dialog = new UserFormDialog(
                (Frame) SwingUtilities.getWindowAncestor(view),
                "Thêm người dùng",
                null,
                false
            );
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                try {
                    HashMap<String, String> data = dialog.getData();
                    Account account = new Account();
                    account.setUserName(data.get("Tên tài khoản"));
                    account.setFullName(data.get("Họ tên"));
                    account.setRole(data.get("Vai trò"));
                    account.setStatus(data.get("Trạng thái").equals("Hoạt động") ? 1 : 0);
                    String namSinhStr = data.get("Ngày sinh");
                    if (!namSinhStr.isEmpty()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = sdf.parse(namSinhStr);
                        account.setNamSinh(new java.sql.Date(date.getTime()));
                    } else {
                        account.setNamSinh(null);
                    }
                    account.setDiaChi(data.get("Địa chỉ").isEmpty() ? null : data.get("Địa chỉ"));
                    account.setDienThoai(data.get("Điện thoại").isEmpty() ? null : data.get("Điện thoại"));
                    account.setEmail(data.get("Email").isEmpty() ? null : data.get("Email"));

                    accountDAO.addAccount(account);
                    loadData();
                    JOptionPane.showMessageDialog(view, "Thêm người dùng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(view, "Ngày sinh phải có định dạng dd/MM/yyyy!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi thêm người dùng: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Nút Sửa
        buttonPanel.getEditButton().addActionListener(e -> {
            int selectedRow = view.getDataTablePanel().getTable().getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một người dùng để sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Account selectedAccount = accounts.get(selectedRow);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            HashMap<String, String> initialData = new HashMap<>();
            initialData.put("Tên tài khoản", selectedAccount.getUserName());
            initialData.put("Họ tên", selectedAccount.getFullName());
            initialData.put("Vai trò", selectedAccount.getRole());
            initialData.put("Trạng thái", selectedAccount.getStatus() == 1 ? "Hoạt động" : "Ngưng hoạt động");
            initialData.put("Ngày sinh", selectedAccount.getNamSinh() != null ? sdf.format(selectedAccount.getNamSinh()) : "");
            initialData.put("Địa chỉ", selectedAccount.getDiaChi() != null ? selectedAccount.getDiaChi() : "");
            initialData.put("Điện thoại", selectedAccount.getDienThoai() != null ? selectedAccount.getDienThoai() : "");
            initialData.put("Email", selectedAccount.getEmail() != null ? selectedAccount.getEmail() : "");

            UserFormDialog dialog = new UserFormDialog(
                (Frame) SwingUtilities.getWindowAncestor(view),
                "Sửa người dùng",
                initialData,
                true
            );
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                try {
                    HashMap<String, String> data = dialog.getData();
                    Account account = new Account();
                    account.setUserName(data.get("Tên tài khoản"));
                    account.setFullName(data.get("Họ tên"));
                    account.setRole(data.get("Vai trò"));
                    account.setStatus(data.get("Trạng thái").equals("Hoạt động") ? 1 : 0);
                    String namSinhStr = data.get("Ngày sinh");
                    if (!namSinhStr.isEmpty()) {
                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = sdf2.parse(namSinhStr);
                        account.setNamSinh(new java.sql.Date(date.getTime()));
                    } else {
                        account.setNamSinh(null);
                    }
                    account.setDiaChi(data.get("Địa chỉ").isEmpty() ? null : data.get("Địa chỉ"));
                    account.setDienThoai(data.get("Điện thoại").isEmpty() ? null : data.get("Điện thoại"));
                    account.setEmail(data.get("Email").isEmpty() ? null : data.get("Email"));

                    accountDAO.updateAccount(account);
                    loadData();
                    JOptionPane.showMessageDialog(view, "Sửa người dùng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(view, "Ngày sinh phải có định dạng dd/MM/yyyy!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi sửa người dùng: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Nút Xóa
        buttonPanel.getDeleteButton().addActionListener(e -> {
            int selectedRow = view.getDataTablePanel().getTable().getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một người dùng để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Account selectedAccount = accounts.get(selectedRow);
            String message = "Bạn có chắc chắn muốn xóa người dùng này?\n" +
                            "Tên tài khoản: " + selectedAccount.getUserName() + "\n" +
                            "Họ tên: " + selectedAccount.getFullName() + "\n" +
                            "Vai trò: " + selectedAccount.getRole();
            int confirm = JOptionPane.showConfirmDialog(view, message, "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                accountDAO.deleteAccount(selectedAccount.getUserName());
                loadData();
                view.getInfoPanel().updateInfo(null); // Xóa thông tin trong InfoPanel
                JOptionPane.showMessageDialog(view, "Xóa người dùng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}