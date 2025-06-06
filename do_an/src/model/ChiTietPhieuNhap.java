package model;

public class ChiTietPhieuNhap {
    private String maPhieu;
    private String maVatPham;
    private int soLuong;
    private double donGia;

    // Constructor
    public ChiTietPhieuNhap() {}

    
    public ChiTietPhieuNhap(String maPhieu, String maVatPham, int soLuong, double donGia) {
        this.maPhieu = maPhieu;
        this.maVatPham = maVatPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    
    

    // Getters và Setters
    public String getMaPhieu() { return maPhieu; }
    public void setMaPhieu(String maPhieu) { this.maPhieu = maPhieu; }
    public String getMaVatPham() { return maVatPham; }
    public void setMaVatPham(String maVatPham) { this.maVatPham = maVatPham; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
}