package model;

import java.sql.Timestamp;

public class PhieuNhap {
    private String maPhieu;
    private Timestamp thoiGianTao;
    private String nguoiTao;
    private String maNhaCungCap;
    private double tongTien;

    // Constructor
    //######################
    public PhieuNhap() {}

    public PhieuNhap(String maPhieu, Timestamp thoiGianTao, String nguoiTao, String maNhaCungCap, double tongTien) {
        this.maPhieu = maPhieu;
        this.thoiGianTao = thoiGianTao;
        this.nguoiTao = nguoiTao;
        this.maNhaCungCap = maNhaCungCap;
        this.tongTien = tongTien;
    }
    //######################
    


    // Getters và Setters
    public String getMaPhieu() { return maPhieu; }
    public void setMaPhieu(String maPhieu) { this.maPhieu = maPhieu; }
    public Timestamp getThoiGianTao() { return thoiGianTao; }
    public void setThoiGianTao(Timestamp thoiGianTao) { this.thoiGianTao = thoiGianTao; }
    public String getNguoiTao() { return nguoiTao; }
    public void setNguoiTao(String nguoiTao) { this.nguoiTao = nguoiTao; }
    public String getMaNhaCungCap() { return maNhaCungCap; }
    public void setMaNhaCungCap(String maNhaCungCap) { this.maNhaCungCap = maNhaCungCap; }
    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
}