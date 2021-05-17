package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class JoinActivity extends AppCompatActivity {

    EditText edit_join_id, edit_join_pw, edit_join_phone;  //입력메시지
    TextView tv_region, tv_join_id_err, tv_join_pw_err, tv_join_phone_err, tv_join_region_err; //에러메시지
    Spinner spinner_join_region; //지역 선택 스피너
    ArrayList<String> regions;
    Button btn_join_join;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // 생성자 초기화
        edit_join_id = findViewById(R.id.edit_join_id);
        edit_join_pw = findViewById(R.id.edit_join_pw);
        edit_join_phone=findViewById(R.id.edit_join_phone);
        tv_join_id_err = findViewById(R.id.tv_join_id_err);
        tv_join_pw_err=findViewById(R.id.tv_join_pw_err);
        tv_join_phone_err=findViewById(R.id.tv_join_phone_err);
        tv_join_region_err=findViewById(R.id.tv_join_region_err);
        btn_join_join=findViewById(R.id.btn_join_join);
        tv_region=findViewById(R.id.tv_region);
        spinner_join_region=findViewById(R.id.spinner_join_region);

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
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /* 1. 입력받은 ID/PW/PHONE/REGION DB에 저장
        *  2. 중복된 ID 체크
        *  3. 입력 시 에러메시지 출력
        * */

        // join버튼 클릭 시 회원가입 성공/실패 팝업창 띄우기
        btn_join_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });



    }
}