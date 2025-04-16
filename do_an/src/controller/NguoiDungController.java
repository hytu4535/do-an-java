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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class NguoiDungController implements RoleGroupChangeListener {
    private NguoiDungPanel view;
    private AccountDAO accountDAO;
    private List<Account> accounts;
    private List<Account> originalAccounts;
    private boolean dataLoaded = false; // Cờ để kiểm tra xem dữ liệu đã được tải chưa

    public NguoiDungController(NguoiDungPanel view) {
        this.view = view;
        this.accountDAO = new AccountDAO();
        setupButtonActions();
        setupTableSelectionListener();
        loadData();
    }

    public void loadData() {
        // Chỉ truy vấn cơ sở dữ liệu nếu dữ liệu chưa được tải hoặc cần làm mới
        if (!dataLoaded) {
            accounts = accountDAO.getAllAccounts();
            originalAccounts = new ArrayList<>(accounts);
            dataLoaded = true;
        }
        updateTable(accounts);
    }

    // Phương thức để làm mới dữ liệu từ cơ sở dữ liệu
    public void refreshData() {
        SwingWorker<List<Account>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Account> doInBackground() {
                // Truy vấn cơ sở dữ liệu trong luồng riêng
                return accountDAO.getAllAccounts();
            }

            @Override
            protected void done() {
                try {
                    accounts = get(); // Lấy kết quả từ luồng
                    originalAccounts = new ArrayList<>(accounts);
                    updateTable(accounts);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi làm mới dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute(); // Chạy SwingWorker
    }

    private void updateTable(List<Account> accountList) {
        DataTablePanel tablePanel = view.getDataTablePanel();
        DefaultTableModel tableModel = tablePanel.getTableModel();

        // Lưu vị trí cuộn hiện tại
        JScrollPane scrollPane = (JScrollPane) tablePanel.getTable().getParent().getParent();
        int scrollPosition = scrollPane.getVerticalScrollBar().getValue();

        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);

        // Thêm dữ liệu mới
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < accountList.size(); i++) {
            Account account = accountList.get(i);
            Object[] rowData = new Object[8];
            rowData[0] = String.valueOf(i + 1); // STT
            rowData[1] = account.getUserName();
            rowData[2] = account.getFullName();
            rowData[3] = account.getRole(); // getRole() đã trả về roleGroupName
            rowData[4] = account.getStatus() == 1 ? "Hoạt động" : "Ngưng hoạt động";
            rowData[5] = account.getNamSinh() != null ? sdf.format(account.getNamSinh()) : "";
            rowData[6] = account.getDiaChi() != null ? account.getDiaChi() : "";
            rowData[7] = account.getDienThoai() != null ? account.getDienThoai() : "";
            tableModel.addRow(rowData);
        }

        // Khôi phục vị trí cuộn
        SwingUtilities.invokeLater(() -> {
            scrollPane.getVerticalScrollBar().setValue(scrollPosition);
        });

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

    @Override
    public void onRoleGroupChanged() {
        refreshData(); // Làm mới dữ liệu từ cơ sở dữ liệu khi danh sách nhóm quyền thay đổi
        UserFormDialog.refreshRoles(); // Làm mới danh sách vai trò trong UserFormDialog
    }

    private void setupButtonActions() {
        ButtonPanel buttonPanel = view.getButtonPanel();

        // Xóa các ActionListener cũ trước khi thêm mới
        buttonPanel.getAddButton().removeActionListener(buttonPanel.getAddButton().getActionListeners().length > 0 ? buttonPanel.getAddButton().getActionListeners()[0] : null);
        buttonPanel.getEditButton().removeActionListener(buttonPanel.getEditButton().getActionListeners().length > 0 ? buttonPanel.getEditButton().getActionListeners()[0] : null);
        buttonPanel.getDeleteButton().removeActionListener(buttonPanel.getDeleteButton().getActionListeners().length > 0 ? buttonPanel.getDeleteButton().getActionListeners()[0] : null);
        buttonPanel.getFilterButton().removeActionListener(buttonPanel.getFilterButton().getActionListeners().length > 0 ? buttonPanel.getFilterButton().getActionListeners()[0] : null);
        buttonPanel.getClearFilterButton().removeActionListener(buttonPanel.getClearFilterButton().getActionListeners().length > 0 ? buttonPanel.getClearFilterButton().getActionListeners()[0] : null);

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
                    account.setRoleGroupId(data.get("Vai trò")); // Lưu roleGroupId
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
                    refreshData();
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
            initialData.put("Vai trò", selectedAccount.getRoleGroupId()); // Truyền roleGroupId
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
                    account.setRoleGroupId(data.get("Vai trò")); // Lưu roleGroupId
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
                    refreshData();
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
                refreshData();
                view.getInfoPanel().updateInfo(null);
                JOptionPane.showMessageDialog(view, "Xóa người dùng thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Nút Lọc
        buttonPanel.getFilterButton().addActionListener(e -> {
            String selectedField = (String) buttonPanel.getFilterFieldComboBox().getSelectedItem();
            String filterValue;
            if (selectedField.equals("Vai trò")) {
                filterValue = (String) buttonPanel.getRoleFilterComboBox().getSelectedItem();
            } else {
                filterValue = buttonPanel.getFilterTextField().getText().trim();
            }

            if (filterValue.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập giá trị để lọc!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Kiểm tra định dạng ngày sinh nếu lọc theo Ngày sinh
            Date filterDate = null;
            if (selectedField.equals("Ngày sinh")) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    sdf.setLenient(false);
                    filterDate = sdf.parse(filterValue);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(view, "Ngày sinh phải có định dạng dd/MM/yyyy!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // Lọc danh sách
            Date finalFilterDate = filterDate;
            accounts = originalAccounts.stream().filter(account -> {
                boolean matches = false;

                switch (selectedField) {
                    case "Tên tài khoản":
                        matches = account.getUserName() != null && account.getUserName().toLowerCase().contains(filterValue.toLowerCase());
                        break;
                    case "Họ tên":
                        matches = account.getFullName() != null && account.getFullName().toLowerCase().contains(filterValue.toLowerCase());
                        break;
                    case "Vai trò":
                        matches = account.getRole() != null && account.getRole().equals(filterValue); // So sánh với roleGroupName
                        break;
                    case "Ngày sinh":
                        matches = account.getNamSinh() != null && account.getNamSinh().equals(new java.sql.Date(finalFilterDate.getTime()));
                        break;
                    case "Địa chỉ":
                        matches = account.getDiaChi() != null && account.getDiaChi().toLowerCase().contains(filterValue.toLowerCase());
                        break;
                    case "Điện thoại":
                        matches = account.getDienThoai() != null && account.getDienThoai().contains(filterValue);
                        break;
                }

                return matches;
            }).collect(Collectors.toList());

            // Cập nhật JTable
            updateTable(accounts);
            JOptionPane.showMessageDialog(view, "Đã lọc dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });

        // Nút Xóa bộ lọc
        buttonPanel.getClearFilterButton().addActionListener(e -> {
            buttonPanel.getFilterTextField().setText("");
            buttonPanel.getFilterFieldComboBox().setSelectedIndex(0);
            accounts = new ArrayList<>(originalAccounts);
            updateTable(accounts);
            JOptionPane.showMessageDialog(view, "Đã xóa bộ lọc!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}