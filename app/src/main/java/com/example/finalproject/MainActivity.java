package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static BottomNavigationView navi;
    HomeFragment homeFragment;
    PointFragment pointFragment;
    CameraFragment cameraFragment;
    RankFragment rankFragment;
    MyPageFragment myPageFragment;

    Button btn_camera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent(); // 넘어온 인텐트 get
        String loginId = intent.getStringExtra("loginId");//인텐트에서 데이터꺼내오기 타입주의!!
        String loginPw = intent.getStringExtra("loginPw");//인텐트에서 데이터꺼내오기 타입주의!!
        String loginPhone = intent.getStringExtra("loginPhone");//인텐트에서 데이터꺼내오기 타입주의!!
        String loginRegion = intent.getStringExtra("loginRegion");//인텐트에서 데이터꺼내오기 타입주의!!
        String loginPoint = intent.getStringExtra("loginPoint");//인텐트에서 데이터꺼내오기 타입주의!!

        Log.d("loginId",loginId);
        Log.d("loginPw",loginPw);
        Log.d("loginPhone",loginPhone);
        Log.d("loginRegion",loginRegion);
        Log.d("loginPoint",loginPoint);




        navi=findViewById(R.id.btm_nav);
        homeFragment = new HomeFragment();
        pointFragment = new PointFragment();
        cameraFragment = new CameraFragment();
        rankFragment = new RankFragment();
        myPageFragment = new MyPageFragment();


        Bundle bundle = new Bundle(5);
        bundle.putString("loginId",loginId);
        bundle.putString("loginPw",loginPw);
        bundle.putString("loginPhone",loginPhone);
        bundle.putString("loginRegion",loginRegion);
        bundle.putString("loginPoint",loginPoint);

        myPageFragment.setArguments(bundle);                           //로그인정보보내기

        pointFragment.setArguments(bundle);                           //로그인정보보내기

        btn_camera = findViewById(R.id.btn_camera);

        executeFragment(homeFragment);

        navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.item_home){
                    executeFragment(homeFragment);
                }else if(item.getItemId() == R.id.item_point){
                    executeFragment(pointFragment);
                }else if(item.getItemId() == R.id.item_camera){
                    executeFragment(cameraFragment);
                }else if(item.getItemId() == R.id.item_rank){
                    executeFragment(rankFragment);
                }else if(item.getItemId() == R.id.item_mypage){
                    executeFragment(myPageFragment);
                }

                //true >> 클릭한 메뉴아이템 포커싱.. 선택이 되어진걸로 보이게
                return true;
            }
        });




    }

    public static void executeMove(int id){
        navi.setSelectedItemId(id);
    }

    private void executeFragment(Fragment fragment) {
        //replace(FrameLayout id명, Fragment객체): FrameLayout에 Fragment화면을 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();
    }

}
