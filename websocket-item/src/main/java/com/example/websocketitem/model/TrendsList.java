package com.example.websocketitem.model;

public class TrendsList {
    private Long userid;
    private Integer status;
    private Integer page;
    private Integer size;




    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "TrendsList{" +
                "userid=" + userid +
                ", status=" + status +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}
