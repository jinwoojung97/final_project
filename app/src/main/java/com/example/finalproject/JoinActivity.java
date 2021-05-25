package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JoinActivity extends AppCompatActivity {

    EditText edit_join_id, edit_join_pw, edit_join_phone;
    TextView tv_region;
    Spinner spinner_join_region;
    ArrayList<String> regions;
    Button btn_join_join;
    RequestQueue requestQueue;

    String region = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // 생성자 초기화
        edit_join_id = findViewById(R.id.edit_join_id);
        edit_join_pw = findViewById(R.id.edit_join_pw);
        edit_join_phone=findViewById(R.id.edit_join_phone);
        btn_join_join=findViewById(R.id.btn_join_join);
        tv_region=findViewById(R.id.tv_region);
        spinner_join_region=findViewById(R.id.spinner_join_region);

         requestQueue = Volley.newRequestQueue(getApplicationContext());
         requestQueue.start();

        //지역 선택 스피너
        regions = new ArrayList<>();
        regions.add("지역을 선택하세요");
        regions.add("광산구");
        regions.add("남구");
        regions.add("동구");
        regions.add("북구");
        regions.add("서구");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,regions);
        spinner_join_region.setAdapter(arrayAdapter);
        spinner_join_region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(parent.getItemAtPosition(i).equals("지역을 선택하세요")){
                    tv_region.setText("지역");
                }else{
                    tv_region.setText("지역 : "+parent.getItemAtPosition(i));
                    region = parent.getItemAtPosition(i).toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//         회원가입 버튼 클릭 시 서버로 데이터 전송
        btn_join_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //키값설정
                String join_id = edit_join_id.getText().toString();
                String join_pw = edit_join_pw.getText().toString();
                String join_phone = edit_join_phone.getText().toString();
                String join_region = tv_region.getText().toString();

                if (join_id.length() == 0 || join_pw.length() == 0 || join_phone.length() == 0 || join_region.length() == 0) {
                    Toast.makeText(JoinActivity.this, "필수 입력값을 작성해주세요!", Toast.LENGTH_SHORT).show();
                }else{
                    //서버주소
                    String server_url ="http://222.102.43.79:8088/AndroidServer/JoinController";

                    StringRequest request = new StringRequest(
                            Request.Method.POST,
                            server_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // 가입 성공 시 response 변수에 "1"값이 저장됨 실패시 "0"값이 저장됨
                                    if(response.equals("1")){
                                        Toast.makeText(JoinActivity.this, "가입이 완료되었습니다", Toast.LENGTH_SHORT).show();
                                        Intent login_intent = new Intent(getApplicationContext(),LoginActivity.class);
                                        startActivity(login_intent);
                                        finish();
                                    }else if(response.equals("0")){
                                        Toast.makeText(JoinActivity.this,"이미 존재하는 아이디 입니다.",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.v("hhd",error.getMessage());
                                }
                            }
                    ){
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();

                            //키값 전송
                            params.put("join_id",join_id);
                            params.put("join_pw",join_pw);
                            params.put("join_phone",join_phone);
                            params.put("join_region",region);

                            return params;
                        }
                    };
                    requestQueue.add(request);

                    Log.v("hhd","Join Click");
                }


            }
        });


    }
}