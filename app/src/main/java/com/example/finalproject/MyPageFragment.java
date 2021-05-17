package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class MyPageFragment extends Fragment {

    Spinner spinner_region_mypage; //지역 선택 스피너
    ArrayList<String> regions;
    TextView tv_region_mypage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_my_page, container, false);

        tv_region_mypage=fragment.findViewById(R.id.tv_region_mypage);

        spinner_region_mypage=fragment.findViewById(R.id.spinner_region_mypage);
        regions = new ArrayList<>();
        regions.add("지역을 선택하세요");
        regions.add("광산구");
        regions.add("남구");
        regions.add("동구");
        regions.add("북구");
        regions.add("서구");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,regions);


        spinner_region_mypage.setAdapter(arrayAdapter);

        spinner_region_mypage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                if(parent.getItemAtPosition(i).equals("지역을 선택하세요")){
                    tv_region_mypage.setText("지역");
                }else{
                    tv_region_mypage.setText("지역 : "+parent.getItemAtPosition(i));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return fragment;
    }
}