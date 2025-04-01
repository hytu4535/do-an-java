package model;

public class VanPhongPham {
    private String maVatPham;
    private String tenVatPham;
    private int soLuong;
    private String loaiVatPham;
    private double gia;
    private String thuongHieu;
    private String chatLieu;
    private Double doDay;
    private String moTa;
    private String xuatXu;
    private Integer trangThai;

    // Constructor
    public VanPhongPham() {}

    // Getters and Setters
    public String getMaVatPham() { return maVatPham; }
    public void setMaVatPham(String maVatPham) { this.maVatPham = maVatPham; }
    public String getTenVatPham() { return tenVatPham; }
    public void setTenVatPham(String tenVatPham) { this.tenVatPham = tenVatPham; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public String getLoaiVatPham() { return loaiVatPham; }
    public void setLoaiVatPham(String loaiVatPham) { this.loaiVatPham = loaiVatPham; }
    public double getGia() { return gia; }
    public void setGia(double gia) { this.gia = gia; }
    public String getThuongHieu() { return thuongHieu; }
    public void setThuongHieu(String thuongHieu) { this.thuongHieu = thuongHieu; }
    public String getChatLieu() { return chatLieu; }
    public void setChatLieu(String chatLieu) { this.chatLieu = chatLieu; }
    public Double getDoDay() { return doDay; }
    public void setDoDay(Double doDay) { this.doDay = doDay; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public String getXuatXu() { return xuatXu; }
    public void setXuatXu(String xuatXu) { this.xuatXu = xuatXu; }
    public Integer getTrangThai() { return trangThai; }
    public void setTrangThai(Integer trangThai) { this.trangThai = trangThai; }
}