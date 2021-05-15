package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final String DB_MEMBER = "member.db";
    static final int DB_VERSION = 1;
    SQLiteDatabase database;
    MemberSQLiteOpenHelper helper;

    EditText edit_id, edit_pw, edit_tel, edit_reg;
    Button btn_insert, btn_update, btn_delete, btn_select;
    ListView lv;
    ArrayList<String> data;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View 객체 초기화 메소드
        iniView();

        // 데이터 베이스 객체 생성
        helper = new MemberSQLiteOpenHelper(getApplicationContext(), DB_MEMBER, null, DB_VERSION);

        // 읽고 쓰게함
        database = helper.getWritableDatabase();
// 1. 회원가입 버튼
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edit_id.getText().toString();
                String pw = edit_pw.getText().toString();
                String tel = edit_tel.getText().toString();
                String reg = edit_reg.getText().toString();

                insert(id, pw, tel, reg);
            }
        });
// 2. 조회버튼
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { select(); }
        });
// 3. 업데이트 버튼
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edit_id.getText().toString();
                String pw = edit_pw.getText().toString();
                String tel = edit_tel.getText().toString();
                String reg = edit_reg.getText().toString();

                update(id, pw, tel, reg);
            }
        });
// 4. 회원 탈퇴 버튼
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edit_id.getText().toString();

                delete(id);
            }
        });
    }

    private void delete(String id) {

        // delete(테이블 명, where 조건, 조건에 대입할 값);
        int result = database.delete("member", "MEMBER_ID=?",
                new String[]{id});

        Toast.makeText(this, "탈퇴가 완료되었습니다.", Toast.LENGTH_SHORT).show();

        select();
    }

    private void update(String id, String pw, String tel, String reg) {

        // ContentValues는 insert와 update 구문에 대해서만 사용
        ContentValues values = new ContentValues();
        values.put("MEMBER_ID", pw);
        values.put("MEMBER_TEL", tel);
        values.put("MEMBER_REG", reg);

        int result = database.update("member", values,
                "MEMBER_ID=?",
                new String[]{id});

        Toast.makeText(this, "수정 완료 되었습니다.", Toast.LENGTH_SHORT).show();
        select();
    }

    private void insert(String id, String pw, String tel, String reg) {

        // database 객체의 insert()에 넘겨줄 객체
        // -key : 테이블의 컬럼명

        ContentValues values = new ContentValues();

        values.put("MEMBER_ID", id);
        values.put("MEMBER_PW", pw);
        values.put("MEMBER_TEL", tel);
        values.put("MEMBER_REG", reg);

        long result = database.insert("member", null, values);

        if(result>0){
            Toast.makeText(this, "회원가입 완료!!", Toast.LENGTH_SHORT).show();
        }
        select();
    }

    private void select() {
        data.clear();

        //Cursor c = database.rawQuery("select * from member", null);
        // columns : 검색할 컬럼을 문자배열로 정의 ex) new String[]{age, phone}
        // selection : where조건 ex) name = ?
        // selectionArgs : where 조건에 들어갈 값을 문자배열로 정의

        Cursor c =database.query("member", null, null,
                null, null, null, null);

        while(c.moveToNext()){
            // member 테이블 : NUM_ID, ID, PW, TEL, REG
            int num_id = c.getInt(0);
            String member_id = c.getString(1);
            String member_pw = c.getString(2);
            String member_tel = c.getString(3);
            String member_reg = c.getString(4);

            data.add("순서 : "+num_id+"아이디 : "+member_id+"비밀번호 : "+member_pw+
                    "전화번호 : "+member_tel+"지역구 : "+ member_reg);
        }

        // adapter는 select에서만 사용된다
        adapter.notifyDataSetChanged();
    }

    private void iniView() {
        edit_id = findViewById(R.id.edit_id);
        edit_pw = findViewById(R.id.edit_pw);
        edit_tel = findViewById(R.id.edit_tel);
        edit_reg = findViewById(R.id.edit_reg);

        btn_insert = findViewById(R.id.btn_insert);
        btn_delete = findViewById(R.id.btn_delete);
        btn_update = findViewById(R.id.btn_update);
        btn_select = findViewById(R.id.btn_select);

        lv = findViewById(R.id.lv);
        data = new ArrayList<>();
        adapter = new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);
    }
}