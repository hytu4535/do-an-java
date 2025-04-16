package controller;

import dao.RoleGroupDAO;
import model.RoleGroup;
import view.phanquyen.PhanQuyenPanel;
import view.phanquyen.RoleDataTablePanel;
import view.phanquyen.RoleInfoPanel;
import view.phanquyen.RoleButtonPanel;
import view.phanquyen.RoleFormDialog;
import view.nguoidung.UserFormDialog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PhanQuyenController {
    private PhanQuyenPanel view;
    private RoleGroupDAO roleGroupDAO;
    private List<RoleGroup> roleGroups;
    private List<RoleGroup> originalRoleGroups;
    private final List<String> FUNCTION_NAMES = List.of(
        "Quản lý khách hàng",
        "Quản lý khu vực kho",
        "Quản lý nhà cung cấp",
        "Quản lý nhân viên",
        "Quản lý nhập hàng",
        "Quản lý nhóm quyền",
        "Quản lý sản phẩm",
        "Quản lý tài khoản",
        "Quản lý thống kê",
        "Quản lý thuộc tính",
        "Quản lý xuất hàng"
    );
    private final List<String> ACTIONS = List.of("xem", "tạo mới", "cập nhật", "xóa");
    private List<RoleGroupChangeListener> listeners = new ArrayList<>(); // Danh sách listener
    private boolean dataLoaded = false; // Cờ để kiểm tra xem dữ liệu đã được tải chưa

    public PhanQuyenController(PhanQuyenPanel view) {
        this.view = view;
        this.roleGroupDAO = new RoleGroupDAO();
        setupButtonActions();
        setupTableSelectionListener();
        loadData();
    }

    // Phương thức để đăng ký listener
    public void addRoleGroupChangeListener(RoleGroupChangeListener listener) {
        listeners.add(listener);
    }

    // Phương thức để thông báo cho các listener
    private void notifyRoleGroupChanged() {
        for (RoleGroupChangeListener listener : listeners) {
            listener.onRoleGroupChanged();
        }
    }

    public void loadData() {
        // Chỉ truy vấn cơ sở dữ liệu nếu dữ liệu chưa được tải hoặc cần làm mới
        if (!dataLoaded) {
            roleGroups = roleGroupDAO.getAllRoleGroups();
            originalRoleGroups = new ArrayList<>(roleGroups);
            dataLoaded = true;
        }
        updateTable(roleGroups);
    }

    // Phương thức để làm mới dữ liệu từ cơ sở dữ liệu
    public void refreshData() {
        SwingWorker<List<RoleGroup>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<RoleGroup> doInBackground() {
                // Truy vấn cơ sở dữ liệu trong luồng riêng
                return roleGroupDAO.getAllRoleGroups();
            }

            @Override
            protected void done() {
                try {
                    roleGroups = get(); // Lấy kết quả từ luồng
                    originalRoleGroups = new ArrayList<>(roleGroups);
                    updateTable(roleGroups);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi làm mới dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute(); // Chạy SwingWorker
    }

    private void updateTable(List<RoleGroup> roleGroupList) {
        RoleDataTablePanel tablePanel = view.getDataTablePanel();
        DefaultTableModel tableModel = tablePanel.getTableModel();

        // Lưu vị trí cuộn hiện tại
        JScrollPane scrollPane = (JScrollPane) tablePanel.getTable().getParent().getParent();
        int scrollPosition = scrollPane.getVerticalScrollBar().getValue();

        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);

        // Thêm dữ liệu mới
        for (int i = 0; i < roleGroupList.size(); i++) {
            RoleGroup roleGroup = roleGroupList.get(i);
            Object[] rowData = new Object[4];
            rowData[0] = String.valueOf(i + 1); // STT
            rowData[1] = roleGroup.getRoleGroupId();
            rowData[2] = roleGroup.getRoleGroupName();
            rowData[3] = roleGroup.getDescription() != null ? roleGroup.getDescription() : "";
            tableModel.addRow(rowData);
        }

        // Khôi phục vị trí cuộn
        SwingUtilities.invokeLater(() -> {
            scrollPane.getVerticalScrollBar().setValue(scrollPosition);
        });

        tablePanel.adjustColumnWidths();
    }

    private void setupTableSelectionListener() {
        RoleDataTablePanel tablePanel = view.getDataTablePanel();
        tablePanel.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tablePanel.getTable().getSelectedRow();
                    if (selectedRow >= 0 && selectedRow < roleGroups.size()) {
                        RoleGroup selectedRoleGroup = roleGroups.get(selectedRow);
                        RoleInfoPanel infoPanel = view.getInfoPanel();
                        infoPanel.updateInfo(selectedRoleGroup);
                    }
                }
            }
        });
    }

    private void setupButtonActions() {
        RoleButtonPanel buttonPanel = view.getButtonPanel();

        // Xóa các ActionListener cũ trước khi thêm mới
        buttonPanel.getAddButton().removeActionListener(buttonPanel.getAddButton().getActionListeners().length > 0 ? buttonPanel.getAddButton().getActionListeners()[0] : null);
        buttonPanel.getEditButton().removeActionListener(buttonPanel.getEditButton().getActionListeners().length > 0 ? buttonPanel.getEditButton().getActionListeners()[0] : null);
        buttonPanel.getDeleteButton().removeActionListener(buttonPanel.getDeleteButton().getActionListeners().length > 0 ? buttonPanel.getDeleteButton().getActionListeners()[0] : null);
        buttonPanel.getDetailButton().removeActionListener(buttonPanel.getDetailButton().getActionListeners().length > 0 ? buttonPanel.getDetailButton().getActionListeners()[0] : null);
        buttonPanel.getFilterButton().removeActionListener(buttonPanel.getFilterButton().getActionListeners().length > 0 ? buttonPanel.getFilterButton().getActionListeners()[0] : null);
        buttonPanel.getClearFilterButton().removeActionListener(buttonPanel.getClearFilterButton().getActionListeners().length > 0 ? buttonPanel.getClearFilterButton().getActionListeners()[0] : null);

        // Nút Thêm
        buttonPanel.getAddButton().addActionListener(e -> {
            RoleFormDialog dialog = new RoleFormDialog(
                (Frame) SwingUtilities.getWindowAncestor(view),
                "Thêm nhóm quyền",
                null,
                false
            );
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                try {
                    HashMap<String, String> data = dialog.getData();
                    RoleGroup roleGroup = new RoleGroup();
                    roleGroup.setRoleGroupId(data.get("Mã nhóm quyền"));
                    roleGroup.setRoleGroupName(data.get("Tên nhóm quyền"));
                    roleGroup.setDescription(data.get("Mô tả").isEmpty() ? null : data.get("Mô tả"));

                    // Lấy danh sách các chức năng và quyền đã chọn
                    List<String> selectedFunctions = new ArrayList<>();
                    for (String function : FUNCTION_NAMES) {
                        for (String action : ACTIONS) {
                            String key = function + "_" + action;
                            if (Boolean.parseBoolean(data.get(key))) {
                                selectedFunctions.add(function + "_" + action);
                            }
                        }
                    }
                    roleGroup.setFunctions(selectedFunctions);

                    roleGroupDAO.addRoleGroup(roleGroup);
                    refreshData();
                    UserFormDialog.refreshRoles(); // Làm mới danh sách vai trò trong UserFormDialog
                    notifyRoleGroupChanged(); // Thông báo cho các listener
                    JOptionPane.showMessageDialog(view, "Thêm nhóm quyền thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi thêm nhóm quyền: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Nút Sửa
        buttonPanel.getEditButton().addActionListener(e -> {
            int selectedRow = view.getDataTablePanel().getTable().getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một nhóm quyền để sửa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            RoleGroup selectedRoleGroup = roleGroups.get(selectedRow);
            HashMap<String, String> initialData = new HashMap<>();
            initialData.put("Mã nhóm quyền", selectedRoleGroup.getRoleGroupId());
            initialData.put("Tên nhóm quyền", selectedRoleGroup.getRoleGroupName());
            initialData.put("Mô tả", selectedRoleGroup.getDescription() != null ? selectedRoleGroup.getDescription() : "");

            // Thêm trạng thái của các chức năng và quyền vào initialData
            List<String> functions = selectedRoleGroup.getFunctions();
            for (String function : FUNCTION_NAMES) {
                for (String action : ACTIONS) {
                    String key = function + "_" + action;
                    initialData.put(key, String.valueOf(functions != null && functions.contains(key)));
                }
            }

            RoleFormDialog dialog = new RoleFormDialog(
                (Frame) SwingUtilities.getWindowAncestor(view),
                "Sửa nhóm quyền",
                initialData,
                true
            );
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                try {
                    HashMap<String, String> data = dialog.getData();
                    RoleGroup roleGroup = new RoleGroup();
                    roleGroup.setRoleGroupId(data.get("Mã nhóm quyền"));
                    roleGroup.setRoleGroupName(data.get("Tên nhóm quyền"));
                    roleGroup.setDescription(data.get("Mô tả").isEmpty() ? null : data.get("Mô tả"));

                    // Lấy danh sách các chức năng và quyền đã chọn
                    List<String> selectedFunctions = new ArrayList<>();
                    for (String function : FUNCTION_NAMES) {
                        for (String action : ACTIONS) {
                            String key = function + "_" + action;
                            if (Boolean.parseBoolean(data.get(key))) {
                                selectedFunctions.add(key);
                            }
                        }
                    }
                    roleGroup.setFunctions(selectedFunctions);

                    roleGroupDAO.updateRoleGroup(roleGroup);
                    refreshData();
                    UserFormDialog.refreshRoles(); // Làm mới danh sách vai trò trong UserFormDialog
                    notifyRoleGroupChanged(); // Thông báo cho các listener
                    JOptionPane.showMessageDialog(view, "Sửa nhóm quyền thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi sửa nhóm quyền: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Nút Xóa
        buttonPanel.getDeleteButton().addActionListener(e -> {
            int selectedRow = view.getDataTablePanel().getTable().getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một nhóm quyền để xóa!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            RoleGroup selectedRoleGroup = roleGroups.get(selectedRow);
            String message = "Bạn có chắc chắn muốn xóa nhóm quyền này?\n" +
                            "Mã nhóm quyền: " + selectedRoleGroup.getRoleGroupId() + "\n" +
                            "Tên nhóm quyền: " + selectedRoleGroup.getRoleGroupName();
            int confirm = JOptionPane.showConfirmDialog(view, message, "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                roleGroupDAO.deleteRoleGroup(selectedRoleGroup.getRoleGroupId());
                refreshData();
                view.getInfoPanel().updateInfo(null);
                UserFormDialog.refreshRoles(); // Làm mới danh sách vai trò trong UserFormDialog
                notifyRoleGroupChanged(); // Thông báo cho các listener
                JOptionPane.showMessageDialog(view, "Xóa nhóm quyền thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Nút Chi tiết
        buttonPanel.getDetailButton().addActionListener(e -> {
            int selectedRow = view.getDataTablePanel().getTable().getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một nhóm quyền để xem chi tiết!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            RoleGroup selectedRoleGroup = roleGroups.get(selectedRow);
            HashMap<String, String> initialData = new HashMap<>();
            initialData.put("Mã nhóm quyền", selectedRoleGroup.getRoleGroupId());
            initialData.put("Tên nhóm quyền", selectedRoleGroup.getRoleGroupName());
            initialData.put("Mô tả", selectedRoleGroup.getDescription() != null ? selectedRoleGroup.getDescription() : "");

            // Thêm trạng thái của các chức năng và quyền vào initialData
            List<String> functions = selectedRoleGroup.getFunctions();
            for (String function : FUNCTION_NAMES) {
                for (String action : ACTIONS) {
                    String key = function + "_" + action;
                    initialData.put(key, String.valueOf(functions != null && functions.contains(key)));
                }
            }

            RoleFormDialog dialog = new RoleFormDialog(
                (Frame) SwingUtilities.getWindowAncestor(view),
                "Chi tiết nhóm quyền",
                initialData,
                false
            );
            dialog.setVisible(true);
        });

        // Nút Lọc
        buttonPanel.getFilterButton().addActionListener(e -> {
            String selectedField = (String) buttonPanel.getFilterFieldComboBox().getSelectedItem();
            String filterValue = buttonPanel.getFilterTextField().getText().trim();

            if (filterValue.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập giá trị để lọc!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Lọc danh sách
            roleGroups = originalRoleGroups.stream().filter(roleGroup -> {
                boolean matches = false;

                switch (selectedField) {
                    case "Mã nhóm quyền":
                        matches = roleGroup.getRoleGroupId() != null && roleGroup.getRoleGroupId().toLowerCase().contains(filterValue.toLowerCase());
                        break;
                    case "Tên nhóm quyền":
                        matches = roleGroup.getRoleGroupName() != null && roleGroup.getRoleGroupName().toLowerCase().contains(filterValue.toLowerCase());
                        break;
                    case "Mô tả":
                        matches = roleGroup.getDescription() != null && roleGroup.getDescription().toLowerCase().contains(filterValue.toLowerCase());
                        break;
                }

                return matches;
            }).collect(Collectors.toList());

            // Cập nhật JTable
            updateTable(roleGroups);
            JOptionPane.showMessageDialog(view, "Đã lọc dữ liệu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });

        // Nút Xóa bộ lọc
        buttonPanel.getClearFilterButton().addActionListener(e -> {
            buttonPanel.getFilterTextField().setText("");
            buttonPanel.getFilterFieldComboBox().setSelectedIndex(0);
            roleGroups = new ArrayList<>(originalRoleGroups);
            updateTable(roleGroups);
            JOptionPane.showMessageDialog(view, "Đã xóa bộ lọc!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}