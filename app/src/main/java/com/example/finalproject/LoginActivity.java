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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity{

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference  databaseReference = firebaseDatabase.getReference();

    static ArrayList<String> arrayListID = new ArrayList<String>();
    static ArrayList<String> arrayListPW = new ArrayList<String>();

    Button btn_login_login, btn_lgoin_join;
    EditText edit_login_id, edit_login_pw;

    String member_id, member_pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edit_login_id = findViewById(R.id.edit_login_id);
        edit_login_pw = findViewById(R.id.edit_login_pw);

        btn_login_login = findViewById(R.id.btn_login_login);
        btn_lgoin_join = findViewById(R.id.btn_login_join);

        databaseReference.child("MembersVO").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MembersVO membersVO = snapshot.getValue(MembersVO.class);
                arrayListID.add(membersVO.getMember_id());
                arrayListPW.add(membersVO.getMember_pw());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        // 로그인 버튼 클릭 시 로그인 실패/성공 팝업창 띄우기


        // 회원가입 버튼 클릭 시 회원가입 페이지로 전환

        btn_login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member_id = edit_login_id.getText().toString();
                member_pw = edit_login_pw.getText().toString();

                for (int i=0; i<arrayListID.size(); i++){
                    if (arrayListID.get(i).contains(member_id) && arrayListPW.get(i).contains(member_pw)){
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                }


            }
        });

        btn_lgoin_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),JoinActivity.class);
                startActivity(intent);
            }
        });
    }
}