package com.example.finalproject;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

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

import java.util.HashMap;
import java.util.Map;

public class PopupActivity extends Activity {

    TextView txtText;
    String result;
    RequestQueue requestQueue;
    String loginId,loginPw;
    Intent popup_intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();


        //UI 객체생성
        txtText = (TextView) findViewById(R.id.txtText);

        //데이터 가져오기
        popup_intent = getIntent();
        String data = popup_intent.getStringExtra("data"); //팝업창에 띄울 메신저
        result = popup_intent.getStringExtra("result"); //정답체크
        txtText.setText(data);
//        String rdata = popup_intent.getStringExtra("r.data");

        //로그인정보 가져오기

        loginId = popup_intent.getStringExtra("loginId");  //로그인정보 가져오기
        loginPw = popup_intent.getStringExtra("loginPw");
        Log.d("로그인정보 ",loginId);



    }

    //확인 버튼 클릭

    public void mOnClose(View v) {

         popup_intent = getIntent();


            if ("1".equals(result)) {
                setResult(RESULT_OK, popup_intent);
                 popup_intent.putExtra("result", "정답 500포인트 적금");

                String server_url="http://222.102.43.79:8088/AndroidServer/QuizController";

                StringRequest request=new StringRequest(
                        Request.Method.POST,
                        server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if(response.equals("0")){
                                    Log.d("포인트 적립 ","실패");
                                    //Toast.makeText(PopupActivity.this,"포인트 적립 실패",Toast.LENGTH_SHORT).show();
                                } else{
                                    Log.d("포인트 적립","성공");
//                                    popup_intent.putExtra("result", "성공 100포인트 적금");
                                    login_reset();
                                    //Toast.makeText(PopupActivity.this,response,Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //Toast.makeText(LoginActivity.this,"로그인 실패",Toast.LENGTH_SHORT).show();
                                Log.d("포인트 적립 ","실패");
                            }
                        }
                ){
                    @Nullable
                    @Override
                    protected Map<String,String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("login_id", loginId);


                        return params; //★★★★★
                    }
                };
                requestQueue.add(request);


            }


            else if ("2".equals(result)) {
                setResult(RESULT_CANCELED, popup_intent);
                popup_intent.putExtra("result", "실패 포인트 적금 실패");
            } else if ("3".equals(result)) {
                setResult(RESULT_CANCELED, popup_intent);
                popup_intent.putExtra("result", "이미 적립되었습니다");
            }
            //액티비티(팝업) 닫기

            finish();


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }





    public void login_reset(){
//        String login_id = edit_login_id.getText().toString();
//        String login_pw = edit_login_pw.getText().toString();
//
//        if(login_id.length()==0 || login_pw.length()==0){
//            //아이디와 비밀번호는 필수 입력사항
//            Toast toast = Toast.makeText(PopupActivity.this, " 아이디와 비밀번호는 필수 입력사항 입니다.", Toast.LENGTH_SHORT);
//            toast.show();
//            return;
//        }

        String server_url="http://222.102.43.79:8088/AndroidServer/LoginController";

        StringRequest request=new StringRequest(
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
                            Toast.makeText(PopupActivity.this,"로그인 실패",Toast.LENGTH_SHORT).show();
                        } else{
                            Log.d("로그인여부",loginId);
                            Log.d("로그인여부", String.valueOf(loginPoint));
                            //Toast.makeText(PopupActivity.this,"환영합니다^^",Toast.LENGTH_SHORT).show();
                            Intent login_intent = new Intent(getApplicationContext(), MainActivity.class);

                            login_intent.putExtra("loginId",loginId);
                            login_intent.putExtra("loginPw",loginPw);
                            login_intent.putExtra("loginPhone",loginPhone);
                            login_intent.putExtra("loginRegion",loginRegion);
                            login_intent.putExtra("loginPoint",String.valueOf(loginPoint));
                            startActivity(login_intent);
                            finish();
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
        requestQueue.add(request);
    }

}





