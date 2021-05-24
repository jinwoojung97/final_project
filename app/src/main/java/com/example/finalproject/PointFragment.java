package com.example.finalproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PointFragment extends Fragment {

    TextView tv_point_id, tv_pointmoney;
    private ListView point_lv;
    private ArrayList<PointVO> data;
    private PointAdapter adapter;
    private Context context;
    RequestQueue requestQueue;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_point, container, false);


        tv_point_id = fragment.findViewById(R.id.tv_point_id);
        tv_pointmoney = fragment.findViewById(R.id.tv_pointmoney);
        point_lv = fragment.findViewById(R.id.point_lv);
        //String loginId;
        //String loginPoint;

        Bundle extra = getArguments();
        if(extra != null){
            String loginId = extra.getString("loginId");
            String loginPoint = extra.getString("loginPoint");

            tv_point_id.setText(loginId+"님의 포인트내역");
            tv_pointmoney.setText(loginPoint+"P");
        }
        point_lv = fragment.findViewById(R.id.point_lv);
        String member_id = extra.getString("loginId");;
//            String url = tv_url.getText().toString();
        String url2 = "http://222.102.43.79:8088/AndroidServer/PointController";

        StringRequest request1 = new StringRequest(
                Request.Method.POST,
                url2,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("여부","성공");

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
                params.put("id",member_id);  // url?id=member_id 이런식으로 전달됨



                return params; //★★★★★

            }

        };

        requestQueue.add(request1);

        Log.d("아이디",member_id);
        //요청객체 생성
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url2,

//                    String data = edit_data.getText().toString();

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONArray point_p = new JSONArray(response);

                            for (int i = 0 ; i <point_p.length(); i++){
                                JSONObject point = (JSONObject) point_p.get(i); //>>jsonObject 가 출력됨

                                PointVO vo = new PointVO(
                                        point.getString("point_id"),
                                        point.getInt("point_money"),
                                        point.getString("point_date"),
                                        point.getString("point_content")
                                );

                                data.add(vo);

                            }

                            adapter.notifyDataSetChanged(); //어댑터 갱신
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {  //실패
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("오류","삐용삐용!!!!!!!!!!!!삐용삐용!!!!!!!!!!!!삐용삐용!!!!!!!!!!!!삐용삐용!!!!!!!!!!!!");
                    }
                }
        );

        //서버에 요청하기
        //요청객체를 생성한 후 반드시 requestQueue에 넘겨줘야 요청이 됨!★★★★★★★
        requestQueue.add(request);



        return fragment;

    }






}
