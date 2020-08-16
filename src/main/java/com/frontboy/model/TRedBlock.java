package com.frontboy.model;

public class TRedBlock {
    private int id;
    private String type;
    private String time;
    private String person;
    private String reason;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
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

        TRedBlock redBlock = (TRedBlock) o;

        if (id != redBlock.id) return false;
        if (type != null ? !type.equals(redBlock.type) : redBlock.type != null) return false;
        if (time != null ? !time.equals(redBlock.time) : redBlock.time != null) return false;
        if (person != null ? !person.equals(redBlock.person) : redBlock.person != null) return false;
        if (reason != null ? !reason.equals(redBlock.reason) : redBlock.reason != null) return false;
        if (remark != null ? !remark.equals(redBlock.remark) : redBlock.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (person != null ? person.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
