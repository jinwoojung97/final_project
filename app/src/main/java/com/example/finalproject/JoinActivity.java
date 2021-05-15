package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class JoinActivity extends AppCompatActivity {

    Spinner spinner_region; //지역 선택 스피너
    ArrayList<String> regions;
    TextView tv_region;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        tv_region=findViewById(R.id.tv_region);
        spinner_region=findViewById(R.id.spinner_region);
        regions = new ArrayList<>();
        regions.add("지역을 선택하세요");
        regions.add("광산구");
        regions.add("남구");
        regions.add("동구");
        regions.add("북구");
        regions.add("서구");



        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,regions);

        spinner_region.setAdapter(arrayAdapter);

        spinner_region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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





    }
}