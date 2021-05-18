package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class MyPageFragment extends Fragment {

    TextView tv_mypage_id, tv_mypage_point;
    TextView tv_mypage_pw, tv_mypage_phone, tv_mypage_region;
    Button btn_mypage_point;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_my_page, container, false);

        tv_mypage_id = fragment.findViewById(R.id.tv_mypage_id);
        tv_mypage_point = fragment.findViewById(R.id.tv_mypage_point);
        tv_mypage_pw = fragment.findViewById(R.id.tv_mypage_pw);
        tv_mypage_phone = fragment.findViewById(R.id.tv_mypage_phone);
        tv_mypage_region = fragment.findViewById(R.id.tv_mypage_region);
        btn_mypage_point = fragment.findViewById(R.id.btn_mypage_point);

        // 회원테이블에서 아이디  가져오기

        // 포인트테이블에서 가져오기

        // 포인트 조회버튼 클릭 시 포인트 조회페이지로 넘어감
        btn_mypage_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 회원테이블에서 비밀번호, 핸드폰번호, 지역 가져오기

        return fragment;
    }
}