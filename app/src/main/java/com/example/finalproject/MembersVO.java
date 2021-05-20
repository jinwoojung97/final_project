package com.example.finalproject;

import java.util.HashMap;
import java.util.Map;
import com.google.firebase.d

@IgnoreExtraProperties
public class MembersVO {
    public String member_id;
    public String member_pw;
    public String member_tel;
    public String member_reg;

    public MembersVO(String member_id, String member_pw, String member_tel, String member_reg) {
        this.member_id = member_id;
        this.member_pw = member_pw;
        this.member_tel = member_tel;
        this.member_reg = member_reg;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("member_id", member_id);
        result.put("member_pw", member_pw);
        result.put("member_tel", member_tel);
        result.put("member_reg", member_reg);

        return result;
    }
}
