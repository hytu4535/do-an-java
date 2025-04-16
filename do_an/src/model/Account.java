package model;

import java.sql.Date;

public class Account {
    private String userName;
    private String fullName;
    private String roleGroupId; // Đổi từ role thành roleGroupId
    private String roleGroupName; // Thêm để lưu tên nhóm quyền
    private int status;
    private Date namSinh; // Đổi từ Integer sang Date
    private String diaChi;
    private String dienThoai;
    private String email;

    // Constructor
    public Account() {
    }

    // Getters và Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleGroupId() {
        return roleGroupId;
    }

    public void setRoleGroupId(String roleGroupId) {
        this.roleGroupId = roleGroupId;
    }

    public String getRoleGroupName() {
        return roleGroupName;
    }

    public void setRoleGroupName(String roleGroupName) {
        this.roleGroupName = roleGroupName;
    }

    public String getRole() {
        return roleGroupName; // Trả về roleGroupName thay vì roleGroupId
    }

    public void setRole(String role) {
        this.roleGroupName = role; // Gán vào roleGroupName
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}