package model;

import java.util.List;

public class RoleGroup {
    private String roleGroupId;
    private String roleGroupName;
    private String description;
    private List<String> functions; // Thêm thuộc tính để lưu danh sách chức năng

    // Constructor
    public RoleGroup() {
    }

    // Getters và Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getFunctions() {
        return functions;
    }

    public void setFunctions(List<String> functions) {
        this.functions = functions;
    }
}