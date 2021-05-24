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
import android.widget.ImageView;
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
    ImageView img_mypage_logout;

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
        img_mypage_logout = fragment.findViewById(R.id.img_mypage_logout);

        Bundle extra = getArguments();
        if(extra != null){
            // 상단 아이디, 포인트
            loginId = extra.getString("loginId");
            loginPoint = extra.getString("loginPoint")+"p";
            
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


        // 포인트 조회버튼 클릭 시 포인트 조회페이지로 넘어감
        btn_mypage_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Bundle bundle = new Bundle(2);
//                bundle.putString("loginId", loginId);
//                bundle.putString("loginPoint", loginPoint);
//
//                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                PointFragment pointFragment = new PointFragment();
//                fragmentTransaction.replace(R.id.frame, pointFragment);
//                fragmentTransaction.commit();
               MainActivity.executeMove(R.id.item_point);
                PointFragment pointFragment = new PointFragment();
                executeFragment(pointFragment);
            }
        });

        //로그아웃
        img_mypage_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"로그아웃 하였습니다..",Toast.LENGTH_SHORT).show();
                Intent login_intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(login_intent);
            }
        });




        return fragment;

    }

    private void executeFragment(PointFragment pointFragment) {
    }

}
