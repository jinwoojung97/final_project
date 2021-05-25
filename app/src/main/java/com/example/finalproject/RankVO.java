package com.example.finalproject;

public class RankVO {
    private int img_medal;
    private String region; //지역
    private String totalPoint; //총포인트   //스트링으로바꿈
    private String countId; //참여자수      //여기도

    public int getImg_medal() {
        return img_medal;
    }

    public void setImg_medal(int img_medal) {
        this.img_medal = img_medal;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(String totalPoint) {
        this.totalPoint = totalPoint;
    }

    public String getCountId() {
        return countId;
    }

    public void setCountId(String countId) {
        this.countId = countId;
    }

    public RankVO(String region, String totalPoint, String countId) {
        this.region = region;
        this.totalPoint = totalPoint;
        this.countId = countId;
    }

    public RankVO(int img_medal, String region, String totalPoint, String countId) {
        this.img_medal = img_medal;
        this.region = region;
        this.totalPoint = totalPoint;
        this.countId = countId;
    }



}
