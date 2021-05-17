package com.example.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class HomeFragment extends Fragment {

    private final int POINTDATA = 1001;

    TextView tv_quiz, tv_region, tv_region_point, tv_rank;
    Button btn_camera, btn_yes, btn_no;
    ImageView img_rank;
    Context activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        / Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_home, container, false);

        activity = container.getContext();

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
                MainActivity.executeMove(R.id.item_camera);
                CameraFragment cameraFragment = new CameraFragment();
                executeFragment(cameraFragment);

            }
        });
        // 오늘의 퀴즈 문제 랜덤으로 보여주기


        // 정답 맞출 시 팝업창 생성
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PopupActivity.class);
                intent.putExtra("data","정답입니다");
                intent.putExtra("result","1");
                startActivityForResult(intent, POINTDATA);
            }
        });

        // 정답 실패 시 팝업창 생성
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PopupActivity.class);
                intent.putExtra("data","오답입니다");
                intent.putExtra("result","2");
                startActivityForResult(intent, POINTDATA);
            }
        });

        //우리동네 랭킹 이미지 뷰 클릭 시 랭킹페이지로 전환
        img_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.executeMove(R.id.item_rank);
                RankFragment rankFragment = new RankFragment();
                executeFragment(rankFragment);
            }
        });


        return fragment;
    };

    private void executeFragment(Fragment fragment) {
        //replace(FrameLayout id명, Fragment객체): FrameLayout에 Fragment화면을 설정
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == POINTDATA) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("result");
                //디비 정답성공
                Toast.makeText(activity, result, Toast.LENGTH_SHORT).show();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                String result = data.getStringExtra("result");
                //디비 정답실패
                Toast.makeText(activity, result, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void msPopup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle("버튼 추가 예제").setMessage("선택하세요.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(activity, "OK Click", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(activity, "Cancel Click", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(activity, "Neutral Click", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}