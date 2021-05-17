package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button btn_login_login, btn_lgoin_join;
    EditText edit_login_id, edit_login_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_login_id = findViewById(R.id.edit_login_id);
        edit_login_pw = findViewById(R.id.edit_login_pw);
        btn_login_login = findViewById(R.id.btn_login_login);
        btn_lgoin_join = findViewById(R.id.btn_login_join);


        // 로그인 버튼 클릭 시 로그인 실패/성공 팝업창 띄우기
        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

        // 회원가입 버튼 클릭 시 회원가입 페이지로 전환
        btn_lgoin_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),JoinActivity.class);
                startActivity(intent);

            }
        });

        //체크박스 버튼 클릭 시 자동로그인


    }
}