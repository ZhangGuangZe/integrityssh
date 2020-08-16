package com.frontboy.model;

import java.sql.Timestamp;

public class TMenu {
    private long id;
    private String name;
    private String component;
    private long pid;
    private String icon;
    private String path;
    private String componentName;
    private Timestamp createTime;

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

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TMenu tMenu = (TMenu) o;

        if (id != tMenu.id) return false;
        if (pid != tMenu.pid) return false;
        if (name != null ? !name.equals(tMenu.name) : tMenu.name != null) return false;
        if (component != null ? !component.equals(tMenu.component) : tMenu.component != null) return false;
        if (icon != null ? !icon.equals(tMenu.icon) : tMenu.icon != null) return false;
        if (path != null ? !path.equals(tMenu.path) : tMenu.path != null) return false;
        if (componentName != null ? !componentName.equals(tMenu.componentName) : tMenu.componentName != null)
            return false;
        if (createTime != null ? !createTime.equals(tMenu.createTime) : tMenu.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (component != null ? component.hashCode() : 0);
        result = 31 * result + (int) (pid ^ (pid >>> 32));
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (componentName != null ? componentName.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
