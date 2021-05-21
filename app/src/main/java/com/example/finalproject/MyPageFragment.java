package com.example.finalproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MyPageFragment extends Fragment {
    private DatabaseReference reference;
    String member_id, member_pw, member_phone, member_reg;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference  databaseReference = firebaseDatabase.getReference();

    static ArrayList<String> arrayListID = new ArrayList<String>();
    static ArrayList<String> arrayListPW = new ArrayList<String>();

    static ArrayList<String> arrayIndex = new ArrayList<String>();

    TextView tv_mypage_id, tv_mypage_point;
    EditText edit_mypage_pw, edit_mypage_phone, edit_mypage_region;
    Button btn_mypage_point, btn_mypage_update;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_my_page, container, false);

        tv_mypage_id = fragment.findViewById(R.id.tv_mypage_id);
        tv_mypage_point = fragment.findViewById(R.id.tv_mypage_point);
        edit_mypage_pw = fragment.findViewById(R.id.edit_mypage_pw);
        edit_mypage_phone = fragment.findViewById(R.id.edit_mypage_phone);
        edit_mypage_region = fragment.findViewById(R.id.edit_mypage_region);
        btn_mypage_point = fragment.findViewById(R.id.btn_mypage_point);

        // 회원테이블에서 아이디  가져오기

        // 포인트테이블에서 가져오기

        // 포인트 조회버튼 클릭 시 포인트 조회페이지로 넘어감
        btn_mypage_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 회원테이블에서 비밀번호, 핸드폰번호, 지역 가져오기
        btn_mypage_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member_pw = edit_mypage_pw.getText().toString();
                member_phone = edit_mypage_phone.getText().toString();
                member_reg = edit_mypage_region.getText().toString();

                postFirebaseDatabase(true);
                getFirebaseDatabase();
                setInsertMode();
            }
        });
        
        return fragment;
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
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("getFirebaseDatabase", "key" + dataSnapshot.getChildrenCount());

                arrayIndex.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    String key = postSnapshot.getKey();
                    MembersVO get = postSnapshot.getValue(MembersVO.class);
                    String[] info = {get.member_id, get.member_pw, get.member_phone, get.member_reg};

                    arrayIndex.add(key);
                    Log.d("getFirebaseDatabase", "key" + key);
                    Log.d("getFirebaseDatabase", "info" + info[0] + info[1] + info[2] + info[3]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("getFirebaseDatabase", "loadPost:onCancelled", databaseError.toException());
            }
        };
    }
//    edit 텍스트 초기화
    private void setInsertMode() {
        edit_mypage_pw.setText(member_pw);
        edit_mypage_phone.setText(member_phone);
        edit_mypage_region.setText(member_reg);
    }


}