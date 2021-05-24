package com.example.finalproject;

public class PointVO {

    private String point_id;
    private int point_money;
    private String point_date;
    private String point_content;

    public PointVO(String point_id, int point_money, String point_date, String point_content) {
        this.point_id = point_id;
        this.point_money = point_money;
        this.point_date = point_date;
        this.point_content = point_content;
    }

    public String getPoint_id() {
        return point_id;
    }

    public void setPoint_id(String point_id) {
        this.point_id = point_id;
    }

    public int getPoint_money() {
        return point_money;
    }

    public void setPoint_money(int point_money) {
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
}
