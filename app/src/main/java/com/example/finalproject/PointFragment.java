package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class PointFragment extends Fragment {

    private ListView point_lv;
    private ArrayList<PointVO> data;
    private PointViewHoler adapter;
    RequestQueue requestQueue;

    TextView tv_point_id, tv_pointmoney;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_point, container, false);


        tv_point_id = fragment.findViewById(R.id.tv_point_id);
        tv_pointmoney = fragment.findViewById(R.id.tv_pointmoney);

        //String loginId;
       //String loginPoint;

        Bundle extra = getArguments();
        if(extra != null){
            String loginId = extra.getString("loginId");
            String loginPoint = extra.getString("loginPoint");

            tv_point_id.setText(loginId+"님의 포인트내역");
            tv_pointmoney.setText(loginPoint+"P");
        }






        return fragment;
    }
}