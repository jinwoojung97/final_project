package com.example.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


public class PointFragment extends Fragment {

    TextView tv_point_id, tv_pointmoney;
    private ListView point_lv;
    private ArrayList<PointVO> data;
    private PointAdapter adapter;
    private Context context;
    RequestQueue requestQueue;
    int img_point = R.drawable.coin;
    Intent point_intent;
    String loginId, loginPw;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View fragment = inflater.inflate(R.layout.fragment_point, container, false);
        data = new ArrayList<PointVO>();


//        img_point=fragment.la;
        tv_point_id = fragment.findViewById(R.id.tv_point_id);
        tv_pointmoney = fragment.findViewById(R.id.tv_pointmoney);
        point_lv = fragment.findViewById(R.id.point_lv);
        //String loginId;
        //String loginPoint;


        adapter=new PointAdapter(getActivity(),R.layout.point_list_item,data);
        point_lv.setAdapter(adapter);

        //요청을 보내주는 requestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.start();

        Bundle extra = getArguments();
        if(extra != null) {
            loginId = extra.getString("loginId");
            String loginPoint = extra.getString("loginPoint");
////            //int형으로 바꿔주기
//            int num = Integer.parseInt((loginPoint));


            tv_point_id.setText(loginId + "님의 포인트내역");

        }

        //여기까지가 진우씨가 하신거자나요 ??그런거같애요!
        point_lv = fragment.findViewById(R.id.point_lv);

        login_reset();

        //String member_id = extra.getString("loginId");;

//            String url = tv_url.getText().toString();

        String url = "http://222.102.43.79:8088/AndroidServer/PointController";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("네트워크통신",response);
                        //JSONObject jsonObject = null;
                        try {

                            JSONArray member_point = new JSONArray(response);

                            for (int i = 0; i < member_point.length(); i++) {
                                JSONObject point = (JSONObject) member_point.get(i);
                                //Log.d("volly",point.getString("region"));

                                PointVO vo = new PointVO(
                                        img_point,   //이미지 추가
                                        point.getInt("point_p")+"P",
                                        point.getString("point_date"),
                                        point.getString("point_content")
                                );

                                data.add(vo);
                            }

                            adapter.notifyDataSetChanged(); // 어댑터 갱신

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("여부","실패");
                    }
                }

        )        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params =  new HashMap<String,String>();

                //put(key값, value값)  --> url?key=value 로 변환되어 전송됨
                params.put("id",loginId);  // url?id=member_id 이런식으로 전달됨

                return params; //★★★★★
            }
        };
        requestQueue.add(request);
        return fragment;

    }

    private void setResult(int resultOk, Intent popup_intent) {
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
                        } else{
                            Log.d("로그인여부",loginId);
                            Log.d("로그인여부", String.valueOf(loginPoint));
                            //Toast.makeText(PopupActivity.this,"환영합니다^^",Toast.LENGTH_SHORT).show();

                            tv_pointmoney.setText(loginPoint + "P");
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
