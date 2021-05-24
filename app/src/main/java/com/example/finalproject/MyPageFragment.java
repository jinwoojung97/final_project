package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MyPageFragment extends Fragment {

    public static MyPageFragment newInstance(){
        return new MyPageFragment();
    }

    TextView tv_mypage_id, tv_mypage_point;
    EditText edit_mypage_pw, edit_mypage_phone, edit_mypage_region;
    Button btn_mypage_point, btn_mypage_update;
    String loginId, loginPoint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_my_page, container, false);


        tv_mypage_id = fragment.findViewById(R.id.tv_mypage_id);
        tv_mypage_point = fragment.findViewById(R.id.tv_mypage_point);
        edit_mypage_pw = fragment.findViewById(R.id.edit_mypage_pw);
        edit_mypage_phone = fragment.findViewById(R.id.edit_mypage_phone);
        edit_mypage_region = fragment.findViewById(R.id.edit_mypage_region);
        btn_mypage_point = fragment.findViewById(R.id.btn_mypage_point);
        btn_mypage_update = fragment.findViewById(R.id.btn_mypage_update);

        Bundle extra = getArguments();
        if(extra != null){
            // 상단 아이디, 포인트
            loginId = extra.getString("loginId");
            loginPoint = extra.getString("loginPoint");
            
            // 하단 비번, 전번, 지역
            String loginPw = extra.getString("loginPw");
            String loginPhone = extra.getString("loginPhone");
            String loginReg = extra.getString("loginRegion");

            tv_mypage_id.setText(loginId);
            tv_mypage_point.setText(loginPoint);

            edit_mypage_pw.setText(loginPw);
            edit_mypage_phone.setText(loginPhone);
            edit_mypage_region.setText(loginReg);

        }

        // 회원테이블에서 아이디  가져오기
        // 포인트테이블에서 가져오기

        // 포인트 조회버튼 클릭 시 포인트 조회페이지로 넘어감
        btn_mypage_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Bundle bundle = new Bundle(2);
//                bundle.putString("loginId", loginId);
//                bundle.putString("loginPoint", loginPoint);
//
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                PointFragment pointFragment = new PointFragment();
                fragmentTransaction.replace(R.id.frame, pointFragment);

                fragmentTransaction.commit();
            }
        });

        // 회원테이블에서 비밀번호, 핸드폰번호, 지역 가져오기
        btn_mypage_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return fragment;

    }

}
