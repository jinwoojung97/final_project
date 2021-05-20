package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener{

    private DatabaseReference reference;

    static ArrayList<String> arrayIndex = new ArrayList<String>();
    static ArrayList<String> arrayData = new ArrayList<String>();

    String member_id, member_pw, member_phone, member_reg;
    String sort = "MEMBER_ID";

    EditText edit_join_id, edit_join_pw, edit_join_phone;  //입력메시지
    TextView tv_region, tv_join_id_err, tv_join_pw_err, tv_join_phone_err, tv_join_region_err; //에러메시지
    Spinner spinner_join_region; //지역 선택 스피너
    ArrayList<String> regions;
    Button btn_join_join;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // 생성자 초기화
        edit_join_id = findViewById(R.id.edit_join_id);
        edit_join_pw = findViewById(R.id.edit_join_pw);
        edit_join_phone=findViewById(R.id.edit_join_phone);

        tv_join_id_err = findViewById(R.id.tv_join_id_err);
        tv_join_pw_err=findViewById(R.id.tv_join_pw_err);
        tv_join_phone_err=findViewById(R.id.tv_join_phone_err);
        tv_join_region_err=findViewById(R.id.tv_join_region_err);

        btn_join_join=findViewById(R.id.btn_join_join);
        btn_join_join.setOnClickListener(this);

        tv_region=findViewById(R.id.tv_region);

        spinner_join_region=findViewById(R.id.spinner_join_region);

        regions = new ArrayList<>();
        regions.add("지역을 선택하세요");
        regions.add("광산구");
        regions.add("남구");
        regions.add("동구");
        regions.add("북구");
        regions.add("서구");

        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,regions);
        spinner_join_region.setAdapter(arrayAdapter);
        spinner_join_region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        getFirebaseDatabase();

        /* 1. 입력받은 ID/PW/PHONE/REGION DB에 저장
         *  2. 중복된 ID 체크
         *  3. 입력 시 에러메시지 출력
         * */
        btn_join_join.setEnabled(true);


    }

    private boolean IsExistID() {
        boolean IsExist = arrayIndex.contains(member_id);
        return IsExist;
    }

    // 회원가입 성공시 초기화
    private void setInsertMode() {
        edit_join_id.setText("");
        edit_join_pw.setText("");
        edit_join_phone.setText("");
        spinner_join_region.setSelection(0);
    }

    public void postFirebaseDatabase(boolean add){
        reference = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String,Object> postValues = null;

        if (add){
            MembersVO post = new MembersVO(member_id, member_pw, member_phone, member_reg);
            postValues = post.toMap();
        }
        childUpdates.put("/MembersVO/" + member_id, postValues);
        reference.updateChildren(childUpdates);
    }

    private void getFirebaseDatabase() {
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e("getFirebaseDatabase", "key" + snapshot.getChildrenCount());
                arrayData.clear();
                arrayIndex.clear();

                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    String key = postSnapshot.getKey();
                    MembersVO get = postSnapshot.getValue(MembersVO.class);
                    String[] info = {get.member_id, get.member_pw, get.member_phone};
                    String Result = setTextLength(info[0], 10) + setTextLength(info[1], 10) +
                            setTextLength(info[2], 10) +setTextLength(info[3], 10);
                    arrayData.add(Result);
                    arrayIndex.add(key);
                    Log.d("getFirebaseDatabase", "key" + key);
                    Log.d("getFirebaseDatabase", "info" + info[0] + info[1] + info[2] + info[3]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("getFirebaseDatabase", "loadPost:onCancelled", error.toException());
            }
        };
        Query sortbyAge = FirebaseDatabase.getInstance().getReference().child("MembersVO").orderByChild(sort);
        sortbyAge.addListenerForSingleValueEvent(postListener);
    }
    public  String setTextLength(String text, int length){
        if (text.length()<length){
            int gap = length - text.length();
            for (int i=0; i<gap; i++){
                text = text + " ";
            }
        }
        return text;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_join_join:
                member_id = edit_join_id.getText().toString();
                member_pw = edit_join_pw.getText().toString();
                member_phone = edit_join_phone.getText().toString();
                member_reg = spinner_join_region.getSelectedItem().toString();

                // 아이디 존재 여부
                if(!IsExistID()){
                    postFirebaseDatabase(true);
                    getFirebaseDatabase();
                    /*텍스트 초기화
                    setInsertMode();*/
                    // join버튼 클릭 시 회원가입 성공/실패 팝업창 띄우기
                    Toast.makeText(JoinActivity.this, "회원가입이 완료 되었습니다!!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);

                    startActivity(intent);
                }else{
                    Toast.makeText(JoinActivity.this, "가입창을 다시 확인 하십시오.", Toast.LENGTH_SHORT).show();
                }
                edit_join_id.requestFocus();
                edit_join_id.setCursorVisible(true);
                break;

            /* 업데이트 버튼 ----------------------------------------------------------
            case R.id.btn_update:
                member_id = edit_join_id.getText().toString();
                member_pw = edit_join_pw.getText().toString();
                member_phone = edit_join_phone.getText().toString();
                member_reg = spinner_join_region.getSelectedItem().toString();

                postFirebaseDatabase(true);
                getFirebaseDatabase();
                setInsertMode();
                Toast.makeText(MainActivity.this, "정보가 수정되었습니다.", Toast.LENGTH_LONG).show();

                edit_join_id.setEnabled(true);
                edit_join_id.requestFocus();
                edit_join_id.setCursorVisible(true);
                break;*/

        }

    }




}