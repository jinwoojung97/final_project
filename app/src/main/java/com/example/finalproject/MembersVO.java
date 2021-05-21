package com.example.finalproject;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class MembersVO {
    public String member_id;
    public String member_pw;
    public String member_phone;
    public String member_reg;

    public MembersVO() {

    }

    public MembersVO(String member_id, String member_pw, String member_phone, String member_reg) {
        this.member_id = member_id;
        this.member_pw = member_pw;
        this.member_phone = member_phone;
        this.member_reg = member_reg;
    }

@Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("member_id", member_id);
        result.put("member_pw", member_pw);
        result.put("member_phone", member_phone);
        result.put("member_reg", member_reg);

        return result;
    }

    public String getMember_id() {
        return member_id;
    }

    public String getMember_pw() {
        return member_pw;
    }

    public String getMember_phone() {
        return member_phone;
    }

    public String getMember_reg() {
        return member_reg;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public void setMember_pw(String member_pw) {
        this.member_pw = member_pw;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }

    public void setMember_reg(String member_reg) {
        this.member_reg = member_reg;
    }
}
