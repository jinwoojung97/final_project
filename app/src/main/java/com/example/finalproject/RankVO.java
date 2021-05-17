package com.example.finalproject;

public class RankVO {

    private int img_medal; //메달 색깔
    private String rank_region; //지역
    private String rank_count; // 사람 수
    private String rank_total; //포인트

    public RankVO(int img_medal, String rank_region, String rank_count, String rank_total) {
        this.img_medal = img_medal;
        this.rank_region = rank_region;
        this.rank_count = rank_count;
        this.rank_total = rank_total;
    }

    public int getImg_medal() {
        return img_medal;
    }

    public void setImg_medal(int img_medal) {
        this.img_medal = img_medal;
    }

    public String getRank_region() {
        return rank_region;
    }

    public void setRank_region(String rank_region) {
        this.rank_region = rank_region;
    }

    public String getRank_count() {
        return rank_count;
    }

    public void setRank_count(String rank_count) {
        this.rank_count = rank_count;
    }

    public String getRank_total() {
        return rank_total;
    }

    public void setRank_total(String rank_total) {
        this.rank_total = rank_total;
    }
}
