package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextLanguage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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
    RequestQueue requestQueue;

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

        btn_mypage_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String update_pw = edit_mypage_pw.getText().toString();
                String update_phone = edit_mypage_phone.getText().toString();
                String update_reg = edit_mypage_region.getText().toString();

                if (update_pw.length() == 0||update_phone.length() == 0||update_reg.length() == 0){
                    Toast.makeText(getContext(), "필수 입력값을 작성해주세요!", Toast.LENGTH_SHORT).show();
                }

                String server_url = "http://222.102.43.79:8088/AndroidServer/JoinController";

                StringRequest request = new StringRequest(
                        Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 변경 성공시 response 변수에 1값이 저장, 실패시 0
                        if (response.equals("1")) {
                            Toast.makeText(getContext(), "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        } else if (response.equals("0")) {
                            Toast.makeText(getContext(), "수정 실패.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.v("hhd", error.getMessage());
                            }
                        }
                ){
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();

                        //키값 전송
                        params.put("join_id",loginId);
                        params.put("join_pw",update_pw);
                        params.put("join_phone",update_phone);
                        params.put("join_region",update_reg);

                        return params;
                    }
                };
                requestQueue.(request);

                Log.v("hhd", "Update Click");
            }
        });

        return fragment;

    }

    private void executeFragment(PointFragment pointFragment) {
    }

}
