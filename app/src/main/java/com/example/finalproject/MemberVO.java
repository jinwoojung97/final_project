package com.example.finalproject;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;


public class MemberVO {
    private String member_id;
    private int member_point;

    public MemberVO(String member_id, int member_point) {
        this.member_id = member_id;
        this.member_point = member_point;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public int getMember_point() {
        return member_point;
    }

    public void setMember_point(int member_point) {
        this.member_point = member_point;
    }
}
