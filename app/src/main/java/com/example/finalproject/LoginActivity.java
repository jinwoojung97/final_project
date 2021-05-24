package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{

    Button btn_login_login, btn_login_join;
    EditText edit_login_id, edit_login_pw;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_login_id = findViewById(R.id.edit_login_id);
        edit_login_pw = findViewById(R.id.edit_login_pw);
        btn_login_login = findViewById(R.id.btn_login_login);
        btn_login_join = findViewById(R.id.btn_login_join);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 회원가입 버튼 클릭 시 회원가입 페이지로 변환
        btn_login_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent join_intent = new Intent(getApplicationContext(),JoinActivity.class);
                startActivity(join_intent);
                finish();
            }
        });

        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login_id = edit_login_id.getText().toString();
                String login_pw = edit_login_pw.getText().toString();

                if(login_id.length()==0 || login_pw.length()==0){
                    //아이디와 비밀번호는 필수 입력사항
                    Toast toast = Toast.makeText(LoginActivity.this, " 아이디와 비밀번호는 필수 입력사항 입니다.", Toast.LENGTH_SHORT);
                   toast.show();
                    return;
                }

                String server_url="http://222.102.43.79:8088/AndroidServer/LoginController";

                StringRequest request=new StringRequest(
                        Request.Method.POST,
                        server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                String loginId=null;
                                int loginPoint = 0;

                                try {
                                    JSONArray loginInfos = new JSONArray(response);


                                    JSONObject loginInfo = (JSONObject)loginInfos.get(0);
                                    loginId = loginInfo.getString("member_id");
                                    loginPoint = loginInfo.getInt("member_point");







                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if(response.equals("")){
                                    Log.d("로그인여부",response);
                                    Toast.makeText(LoginActivity.this,"로그인 실패",Toast.LENGTH_SHORT).show();
                                } else{
                                    Log.d("로그인여부",loginId);
                                    Log.d("로그인여부", String.valueOf(loginPoint));
                                    Toast.makeText(LoginActivity.this,response,Toast.LENGTH_SHORT).show();
                                    Intent login_intent = new Intent(getApplicationContext(), MainActivity.class);

                                    login_intent.putExtra("loginId",loginId);
                                    login_intent.putExtra("loginPoint",String.valueOf(loginPoint));
                                    startActivity(login_intent);
                                    finish();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LoginActivity.this,"로그인 실패",Toast.LENGTH_SHORT).show();
                            }
                        }
                ){
                    @Nullable
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("login_id", login_id);
                        params.put("login_pw",login_pw);

                        return params; //★★★★★
                    }
                };
                requestQueue.add(request);
            }
        });
    } }
