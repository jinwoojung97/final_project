package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class HomeFragment extends Fragment {

    TextView tv_quiz, tv_region, tv_region_point, tv_rank;
    Button btn_camera, btn_yes, btn_no;
    ImageView img_rank;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_home, container, false);

        tv_quiz = fragment.findViewById(R.id.tv_quiz);
        tv_region = fragment.findViewById(R.id.tv_region);
        tv_region_point = fragment.findViewById(R.id.tv_region_point);
        tv_rank = fragment.findViewById(R.id.tv_rank);
        btn_camera = fragment.findViewById(R.id.btn_camera);
        btn_yes = fragment.findViewById(R.id.btn_yes);
        btn_no = fragment.findViewById(R.id.btn_no);
        img_rank = fragment.findViewById(R.id.img_rank);

        // 카메라 버튼 클릭 시 카메라 페이지로 전환
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // 오늘의 퀴즈 문제 랜덤으로 보여주기


        // 정답 맞출 시 팝업창 생성
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 정답 실패 시 팝업창 생성
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //우리동네 랭킹 이미지 뷰 클릭 시 랭킹페이지로 전환
        img_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return fragment;
    };
}