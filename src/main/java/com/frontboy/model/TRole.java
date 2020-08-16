package com.frontboy.model;

import java.sql.Timestamp;

public class TRole {
    private long id;
    private String name;
    private String description;
    private Integer level;
    private Timestamp createTime;
    private String permission;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRole tRole = (TRole) o;

        if (id != tRole.id) return false;
        if (name != null ? !name.equals(tRole.name) : tRole.name != null) return false;
        if (description != null ? !description.equals(tRole.description) : tRole.description != null) return false;
        if (level != null ? !level.equals(tRole.level) : tRole.level != null) return false;
        if (createTime != null ? !createTime.equals(tRole.createTime) : tRole.createTime != null) return false;
        if (permission != null ? !permission.equals(tRole.permission) : tRole.permission != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        return result;
    }
}
