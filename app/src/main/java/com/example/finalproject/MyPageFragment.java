package com.example.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyPageFragment extends Fragment {

    public static MyPageFragment newInstance(){
        return new MyPageFragment();
    }


    TextView tv_mypage_id, tv_mypage_point, tv_update_reg;
    EditText edit_mypage_pw, edit_mypage_phone, edit_mypage_region;
    Button btn_mypage_point, btn_mypage_update;
    String loginId, loginPoint, loginPw,loginPhone,loginRegion;
    ImageView img_mypage_logout;
    Intent point_intent;
    RequestQueue requestQueue;
    Context activity;

    String loginReg;
    String region = "";
    Spinner spinner_update_region;
    ArrayList<String> regions;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_my_page, container, false);

        activity = container.getContext();
        tv_mypage_id = fragment.findViewById(R.id.tv_mypage_id);
        tv_mypage_point = fragment.findViewById(R.id.tv_mypage_point);
        tv_update_reg = fragment.findViewById(R.id.tv_update_reg);
        edit_mypage_pw = fragment.findViewById(R.id.edit_mypage_pw);
        edit_mypage_phone = fragment.findViewById(R.id.edit_mypage_phone);

        spinner_update_region=fragment.findViewById(R.id.spinner_update_region);

        //지역 선택 스피너
        regions = new ArrayList<>();
        regions.add("지역을 선택하세요");
        regions.add("광산구");
        regions.add("남구");
        regions.add("동구");
        regions.add("북구");
        regions.add("서구");

        btn_mypage_point = fragment.findViewById(R.id.btn_mypage_point);
        btn_mypage_update = fragment.findViewById(R.id.btn_mypage_update);
        img_mypage_logout = fragment.findViewById(R.id.img_mypage_logout);

        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.start();

        Bundle extra = getArguments();
        if(extra != null){
            // 상단 아이디, 포인트
            loginId = extra.getString("loginId");
            login_reset();
            
            // 하단 비번, 전번, 지역
            String loginPw = extra.getString("loginPw");
            String loginPhone = extra.getString("loginPhone");
            loginReg = extra.getString("loginRegion");

            tv_mypage_id.setText(loginId);
            tv_mypage_point.setText(loginPoint);

            edit_mypage_pw.setText(loginPw);
            edit_mypage_phone.setText(loginPhone);

            switch (loginReg) {
                case "광산구":  spinner_update_region.setSelection(1);
                    break;
                case "남구":  spinner_update_region.setSelection(2);
                    break;
                case "동구":  spinner_update_region.setSelection(3);
                    break;
                case "북구":  spinner_update_region.setSelection(4);
                    break;
                case "서구":  spinner_update_region.setSelection(5);
                    break;
            }
        }


        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,regions);
        spinner_update_region.setAdapter(arrayAdapter);
        spinner_update_region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(parent.getItemAtPosition(i).equals("지역을 선택하세요")){
                    tv_update_reg.setText("현재 지역 : " + loginReg);
                }else{
                    tv_update_reg.setText("현재 지역 : " + loginReg);
                    region = parent.getItemAtPosition(i).toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_mypage_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UpdateId = loginId;
                String UpdatePw = edit_mypage_pw.getText().toString();
                String UpdateRegion = spinner_update_region.getSelectedItem().toString();
                String UpdatePhone = edit_mypage_phone.getText().toString();

                String server_url="http://222.102.43.79:8088/AndroidServer/UpdateController";

                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // 회원정보 수정 성공시 response 변수에 "1"값이 저장됨 실패시 "0"값이 저장됨

                                String success = "회원정보 수정 성공! 재로그인 해주세요.";
                                String fail = "회원정보 수정 실패";

                                if(response.equals("1")){
                                    Toast.makeText(activity, success , Toast.LENGTH_SHORT).show();
                                    Log.d("정보수정 여부","성공");
                                    Intent login_intent = new Intent(getActivity(),LoginActivity.class);
                                    startActivity(login_intent);
                                    getActivity().finish();

                                }else if(response.equals("0")){
                                    Toast.makeText(activity, fail , Toast.LENGTH_SHORT).show();
                                    Log.d("정보수정 여부","실패");
                                }
                            }

                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }

                ){
                    @Nullable
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("login_id", UpdateId);
                        params.put("login_pw",UpdatePw);
                        params.put("login_region",UpdateRegion);
                        params.put("login_phone",UpdatePhone);

                        return params; //★★★★★
                    }
                };
                requestQueue.add(request);
            }
        });


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




    public void login_reset(){

        point_intent = getActivity().getIntent();

        //로그인정보 가져오기

        loginId = point_intent.getStringExtra("loginId");  //로그인정보 가져오기
        loginPw = point_intent.getStringExtra("loginPw");
        Log.d("로그인정보 ",loginId);

        String server_url="http://222.102.43.79:8088/AndroidServer/LoginController";

        StringRequest request2 = new StringRequest(
                Request.Method.POST,
                server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String loginId=null;
                        String loginPw=null;
                        String loginPhone=null;
                        String loginRegion=null;
                        int loginPoint = 0;

                        try {
                            JSONArray loginInfos = new JSONArray(response);


                            JSONObject loginInfo = (JSONObject)loginInfos.get(0);
                            loginId = loginInfo.getString("member_id");
                            loginPw = loginInfo.getString("member_pw");
                            loginPhone = loginInfo.getString("member_phone");
                            loginRegion = loginInfo.getString("member_region");
                            loginPoint = loginInfo.getInt("member_point");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(response.equals("")){
                            Log.d("로그인여부",response);
                        } else{
                            Log.d("로그인여부",loginId);
                            Log.d("로그인여부", String.valueOf(loginPoint));
                            //Toast.makeText(PopupActivity.this,"환영합니다^^",Toast.LENGTH_SHORT).show();

                            tv_mypage_point.setText(loginPoint + "P");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(PopupActivity.this,"로그인 실패",Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("login_id", loginId);
                params.put("login_pw", loginPw);

                return params; //★★★★★
            }
        };
        requestQueue.add(request2);

    }
}
