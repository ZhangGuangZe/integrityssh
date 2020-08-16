package com.frontboy.model;

public class TBlock {
    private int id;
    private String name;
    private String time;
    private String reason;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TBlock tBlock = (TBlock) o;

        if (id != tBlock.id) return false;
        if (name != null ? !name.equals(tBlock.name) : tBlock.name != null) return false;
        if (time != null ? !time.equals(tBlock.time) : tBlock.time != null) return false;
        if (reason != null ? !reason.equals(tBlock.reason) : tBlock.reason != null) return false;
        if (remark != null ? !remark.equals(tBlock.remark) : tBlock.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
