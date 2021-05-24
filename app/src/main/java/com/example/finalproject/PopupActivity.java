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
    int point_p = 100;
    String loginId;
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
        String rdata = popup_intent.getStringExtra("r.data");
        result = popup_intent.getStringExtra("result"); //정답체크
        txtText.setText(data);

        //로그인정보 가져오기

        loginId = popup_intent.getStringExtra("loginId");  //로그인정보 가져오기
        Log.d("로그인정보 ",loginId);



    }

    //확인 버튼 클릭

    public void mOnClose(View v) {

         popup_intent = getIntent();


            if ("1".equals(result)) {
                setResult(RESULT_OK, popup_intent);
                 popup_intent.putExtra("result", "성공 100포인트 적금");

                String server_url="http://222.102.43.79:8088/AndroidServer/QuizController";

                StringRequest request=new StringRequest(
                        Request.Method.POST,
                        server_url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                if(response.equals("1")){
                                    Log.d("포인트 적립 ","실패");
                                    //Toast.makeText(PopupActivity.this,"포인트 적립 실패",Toast.LENGTH_SHORT).show();
                                } else{
                                    Log.d("포인트 적립","성공");
//                                    popup_intent.putExtra("result", "성공 100포인트 적금");

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
}





