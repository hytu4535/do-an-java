package controller;

import dao.AccountDAO;
import dao.PhieuNhapDAO;
import dao.PhieuXuatDAO;
import dao.VanPhongPhamDAO;
import model.Account;
import model.PhieuNhap;
import model.PhieuXuat;
import model.VanPhongPham;
import view.thongke.StatisticsPanel;
import view.thongke.TablePanel;

import java.text.SimpleDateFormat;
import java.util.List;

public class StatisticsController {
    private StatisticsPanel view;
    private VanPhongPhamDAO vanPhongPhamDAO;
    private PhieuNhapDAO phieuNhapDAO;
    private PhieuXuatDAO phieuXuatDAO;
    private AccountDAO accountDAO;

    public StatisticsController(StatisticsPanel view) {
        this.view = view;
        this.vanPhongPhamDAO = new VanPhongPhamDAO();
        this.phieuNhapDAO = new PhieuNhapDAO();
        this.phieuXuatDAO = new PhieuXuatDAO();
        this.accountDAO = new AccountDAO();

        // Load dữ liệu mặc định (Sản phẩm)
        loadProductData();

        // Thêm sự kiện cho typeFilter
        view.getSearchPanel().getTypeFilter().addActionListener(e -> {
            String selectedType = (String) view.getSearchPanel().getTypeFilter().getSelectedItem();
            switch (selectedType) {
                case "Sản phẩm":
                    loadProductData();
                    break;
                case "Phiếu":
                    loadTicketData();
                    break;
                case "Tài khoản":
                    loadUserData();
                    break;
            }
        });
    }

    private void loadProductData() {
        List<VanPhongPham> vanPhongPhams = vanPhongPhamDAO.getAllVanPhongPhams();
        TablePanel tablePanel = view.getTablePanel();
        Object[][] data = new Object[vanPhongPhams.size()][5];
        for (int i = 0; i < vanPhongPhams.size(); i++) {
            VanPhongPham vpp = vanPhongPhams.get(i);
            data[i][0] = String.valueOf(i + 1); // STT
            data[i][1] = vpp.getMaVatPham();
            data[i][2] = vpp.getTenVatPham();
            data[i][3] = vanPhongPhamDAO.getImportQuantity(vpp.getMaVatPham());
            data[i][4] = vanPhongPhamDAO.getExportQuantity(vpp.getMaVatPham());
        }
        tablePanel.getTableModel().setDataVector(data, tablePanel.getProductColumns());
        tablePanel.adjustColumnWidths("Sản phẩm");
    }

    private void loadTicketData() {
        List<PhieuNhap> phieuNhaps = phieuNhapDAO.getAllPhieuNhaps();
        List<PhieuXuat> phieuXuats = phieuXuatDAO.getAllPhieuXuats();
        TablePanel tablePanel = view.getTablePanel();
        Object[][] data = new Object[phieuNhaps.size() + phieuXuats.size()][5];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Thêm phiếu nhập
        for (int i = 0; i < phieuNhaps.size(); i++) {
            PhieuNhap pn = phieuNhaps.get(i);
            data[i][0] = String.valueOf(i + 1); // STT
            data[i][1] = pn.getMaPhieu();
            data[i][2] = "Phiếu nhập";
            data[i][3] = sdf.format(pn.getThoiGianTao());
            data[i][4] = pn.getTongTien() + " VNĐ";
        }

        // Thêm phiếu xuất
        for (int i = 0; i < phieuXuats.size(); i++) {
            PhieuXuat px = phieuXuats.get(i);
            data[i + phieuNhaps.size()][0] = String.valueOf(i + phieuNhaps.size() + 1); // STT
            data[i + phieuNhaps.size()][1] = px.getMaPhieu();
            data[i + phieuNhaps.size()][2] = "Phiếu xuất";
            data[i + phieuNhaps.size()][3] = sdf.format(px.getThoiGianTao());
            data[i + phieuNhaps.size()][4] = px.getTongTien() + " VNĐ";
        }

        tablePanel.getTableModel().setDataVector(data, tablePanel.getTicketColumns());
        tablePanel.adjustColumnWidths("Phiếu");
    }

    private void loadUserData() {
        List<Account> accounts = accountDAO.getAllAccounts();
        TablePanel tablePanel = view.getTablePanel();
        Object[][] data = new Object[accounts.size()][5];
        for (int i = 0; i < accounts.size(); i++) {
            Account account = accounts.get(i);
            data[i][0] = String.valueOf(i + 1); // STT
            data[i][1] = account.getUserName();
            data[i][2] = account.getFullName();
            data[i][3] = account.getRole();
            data[i][4] = account.getStatus() == 1 ? "Hoạt động" : "Ngưng hoạt động";
        }
        tablePanel.getTableModel().setDataVector(data, tablePanel.getUserColumns());
        tablePanel.adjustColumnWidths("Tài khoản");
    }
}