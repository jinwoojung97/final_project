package com.example.finalproject;

public class PointVO {


    private String point_id;
    private int point_img;
    private String point_p;
    private String point_date;
    private String point_content;

    public PointVO(int point_img, String point_p, String point_date, String point_content) {
        this.point_img = point_img;
        this.point_p = point_p;
        this.point_date = point_date;
        this.point_content = point_content;
    }

    public PointVO(String point_id, int point_img, String point_p, String point_date, String point_content) {
        this.point_id = point_id;
        this.point_img = point_img;
        this.point_p = point_p;
        this.point_date = point_date;
        this.point_content = point_content;
    }

    public String getPoint_id() {
        return point_id;
    }

    public int getPoint_img() {
        return point_img;
    }

    public String getPoint_p() {
        return point_p;
    }

    public String getPoint_date() {
        return point_date;
    }

    public String getPoint_content() {
        return point_content;
    }
}
