package com.frontboy.model;

public class VUserRole {
    private String username;
    private Long roleid;
    private String password;
    private String realname;
    private String gender;
    private String phone;
    private String level;
    private String avatar;
    private String language;
    private Long enabled;
    private String token;
    private String name;
    private String description;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Long getEnabled() {
        return enabled;
    }

    public void setEnabled(Long enabled) {
        this.enabled = enabled;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VUserRole vUserRole = (VUserRole) o;

        if (username != null ? !username.equals(vUserRole.username) : vUserRole.username != null) return false;
        if (roleid != null ? !roleid.equals(vUserRole.roleid) : vUserRole.roleid != null) return false;
        if (password != null ? !password.equals(vUserRole.password) : vUserRole.password != null) return false;
        if (realname != null ? !realname.equals(vUserRole.realname) : vUserRole.realname != null) return false;
        if (gender != null ? !gender.equals(vUserRole.gender) : vUserRole.gender != null) return false;
        if (phone != null ? !phone.equals(vUserRole.phone) : vUserRole.phone != null) return false;
        if (level != null ? !level.equals(vUserRole.level) : vUserRole.level != null) return false;
        if (avatar != null ? !avatar.equals(vUserRole.avatar) : vUserRole.avatar != null) return false;
        if (language != null ? !language.equals(vUserRole.language) : vUserRole.language != null) return false;
        if (enabled != null ? !enabled.equals(vUserRole.enabled) : vUserRole.enabled != null) return false;
        if (token != null ? !token.equals(vUserRole.token) : vUserRole.token != null) return false;
        if (name != null ? !name.equals(vUserRole.name) : vUserRole.name != null) return false;
        if (description != null ? !description.equals(vUserRole.description) : vUserRole.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (roleid != null ? roleid.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (realname != null ? realname.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
