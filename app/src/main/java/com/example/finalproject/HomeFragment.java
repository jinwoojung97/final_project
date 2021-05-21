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

import android.util.Log;
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
    int question_count; // 자동으로 0 잡힘
    ImageView mainimg;


    // 퀴즈 문제
    int[] question_list = {R.drawable.quiz1, R.drawable.quiz2, R.drawable.quiz3, R.drawable.quiz4};
    int img = 0;

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
        mainimg = fragment.findViewById(R.id.mainimg);


        // 퀴즈 정답
        String[] question_c_list = {"입구가 조금이라도 깨지면 재사용이 \n " +
                "불가능하기 때문에 \n" +
                "입구 보호를 위해 뚜껑을 \n" +
                "끼운 채로 배출하면 좋다", //1번quiz
                "HDPE,LDPE,PP,PVC는 재활용이 가능\n" +
                        "OTHER 표시가 있는 플라스틱 포장재는 복합 재질이므로 일반쓰레기!!", // 2번quiz
                "-철사에 플라스틱을 감싸서 만든\n 철제 옷걸이(세탁소 옷걸이)는 \n" +
                        "그대로 고철(캔류-철)옷걸이\n 분리 수거로 분리 배출해요.-\n" +
                        "\n" +
                        "-고리 부분을 포함해서 \n모두 플라스틱으로만 제작된 옷걸이는 \n" +
                        "플라스틱 분리 수거로 분리 배출해요.-\n" +
                        "\n" +
                        "-분리가 안되는 경우 모두 일반쓰레기\n(정량제봉투)로 버려주세요-", //3번 quiz
                "유리병은 색깔별로 분리배출을 해야하고 \n" +
                        "도자기 세라믹 종류는 유리병과 분리해서 폐기물 봉투에 배출해야한다\n" +
                        "유리병은 색깔별로 분리배출해줘야\n 올바른 재활용이 가능합니다",
        };



        // 카메라 버튼 클릭 시 카메라 페이지로 전환
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainActivity.executeMove(R.id.item_camera);
//                CameraFragment cameraFragment = new CameraFragment();
//                executeFragment(cameraFragment);

                Intent intent = new Intent(getContext(), CameraActivity.class);
                startActivity(intent);
            }
        });
        // 오늘의 퀴즈 문제 랜덤으로 보여주기


        // 팝업창 생성
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PopupActivity.class);

                if (question_count == 0) {
                    intent.putExtra("data", "정답입니다\n" + question_c_list[question_count++]);
                    intent.putExtra("result", "1");
                    startActivityForResult(intent, POINTDATA);
                    Log.v("test", question_count + "");

                    if (img == 3) {
                        img = 0;
                    } else {
                        img++;
                    }
                    mainimg.setImageResource(question_list[img]);
                } else if (question_count == 1) {
                    intent.putExtra("data", "정답입니다\n" + question_c_list[question_count++]);
                    intent.putExtra("result", "3");
                    startActivityForResult(intent, POINTDATA);
                    Log.v("test", question_count + "");

                    if (img == 3) {
                        img = 0;
                    } else {
                        img++;
                    }
                    mainimg.setImageResource(question_list[img]);
                } else if (question_count == 2) {
                    intent.putExtra("data", "오답입니다\n" + question_c_list[question_count++]);
                    intent.putExtra("result", "2");
                    startActivityForResult(intent, POINTDATA);
                    Log.v("test", question_count + "");

                    if (img == 3) {
                        img = 0;
                    } else {
                        img++;
                    }
                    mainimg.setImageResource(question_list[img]);
                } else if (question_count == 3) {
                    intent.putExtra("data", "오답입니다\n" + question_c_list[question_count++]);
                    intent.putExtra("result", "2");
                    startActivityForResult(intent, POINTDATA);
                    Log.v("test", question_count + "");

                    if (img == 3) {
                        img = 0;
                    } else {
                        img++;
                    }
                    mainimg.setImageResource(question_list[img]);
                } else {
                    Toast.makeText(getContext(), "오늘의 알쓸쓰잡 끝!", Toast.LENGTH_LONG).show();
                }
            }
        });

        // 팝업창 생성
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PopupActivity.class);
                if (question_count == 0) {
                    intent.putExtra("data", "오답입니다\n" + question_c_list[question_count++]);
                    intent.putExtra("result", "2");
                    startActivityForResult(intent, POINTDATA);
                    if (img == 3) {
                        img = 0;
                    } else {
                        img++;
                    }
                    mainimg.setImageResource(question_list[img]);
                }
                else if (question_count == 1) {
                    intent.putExtra("data", "오답입니다\n" + question_c_list[question_count++]);
                    intent.putExtra("result", "2");
                    startActivityForResult(intent, POINTDATA);
                    if (img == 3) {
                        img = 0;
                    } else {
                        img++;
                    }
                    mainimg.setImageResource(question_list[img]);
                }
                else if (question_count == 2) {
                    intent.putExtra("data", "정답입니다\n" + question_c_list[question_count++]);
                    intent.putExtra("result", "3");
                    startActivityForResult(intent, POINTDATA);
                    if (img == 3) {
                        img = 0;
                    } else {
                        img++;
                    }
                    mainimg.setImageResource(question_list[img]);
                }
                else if (question_count == 3) {
                    intent.putExtra("data", "정답입니다\n" + question_c_list[question_count++]);
                    intent.putExtra("result", "3");
                    startActivityForResult(intent, POINTDATA);
                    if (img == 3) {
                        img = 0;
                    } else {
                        img++;
                    }
                    mainimg.setImageResource(question_list[img]);
                }


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
    }

    ;

    private void executeFragment(Fragment fragment) {
        //replace(FrameLayout id명, Fragment객체): FrameLayout에 Fragment화면을 설정
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).commit();
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


}