package com.frontboy.model;

public class TComplaint {
    private int id;
    private String object;
    private String reason;
    private String status;
    private String time;
    private String operator;
    private String result;
    private String username;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

        TComplaint complaint = (TComplaint) o;

        if (id != complaint.id) return false;
        if (object != null ? !object.equals(complaint.object) : complaint.object != null) return false;
        if (reason != null ? !reason.equals(complaint.reason) : complaint.reason != null) return false;
        if (status != null ? !status.equals(complaint.status) : complaint.status != null) return false;
        if (time != null ? !time.equals(complaint.time) : complaint.time != null) return false;
        if (operator != null ? !operator.equals(complaint.operator) : complaint.operator != null) return false;
        if (result != null ? !result.equals(complaint.result) : complaint.result != null) return false;
        if (username != null ? !username.equals(complaint.username) : complaint.username != null) return false;
        if (remark != null ? !remark.equals(complaint.remark) : complaint.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = id;
        result1 = 31 * result1 + (object != null ? object.hashCode() : 0);
        result1 = 31 * result1 + (reason != null ? reason.hashCode() : 0);
        result1 = 31 * result1 + (status != null ? status.hashCode() : 0);
        result1 = 31 * result1 + (time != null ? time.hashCode() : 0);
        result1 = 31 * result1 + (operator != null ? operator.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (username != null ? username.hashCode() : 0);
        result1 = 31 * result1 + (remark != null ? remark.hashCode() : 0);
        return result1;
    }
}
