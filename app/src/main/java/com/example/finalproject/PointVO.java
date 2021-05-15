package com.example.finalproject;

public class PointVO {

    private String point_id;
    private String point_money;
    private String point_date;
    private String point_content;
    private String point_total;

    public PointVO(String point_id, String point_money, String point_date, String point_content, String point_total) {
        this.point_id = point_id;
        this.point_money = point_money;
        this.point_date = point_date;
        this.point_content = point_content;
        this.point_total = point_total;
    }

    public String getPoint_id() {
        return point_id;
    }

    public void setPoint_id(String point_id) {
        this.point_id = point_id;
    }

    public String getPoint_money() {
        return point_money;
    }

    public void setPoint_money(String point_money) {
        this.point_money = point_money;
    }

    public String getPoint_date() {
        return point_date;
    }

    public void setPoint_date(String point_date) {
        this.point_date = point_date;
    }

    public String getPoint_content() {
        return point_content;
    }

    public void setPoint_content(String point_content) {
        this.point_content = point_content;
    }

    public String getPoint_total() {
        return point_total;
    }

    public void setPoint_total(String point_total) {
        this.point_total = point_total;
    }
}
