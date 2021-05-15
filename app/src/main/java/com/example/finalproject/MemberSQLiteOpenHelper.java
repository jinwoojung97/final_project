package com.example.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MemberSQLiteOpenHelper extends SQLiteOpenHelper {
    public MemberSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table member(NUM_ID INTEGER primary key autoincrement, "+
                "MEMBER_ID TEXT, MEMBER_PW TEXT, MEMBER_TEL TEXT, MEMBER_REG TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table member;");
        onCreate(db);
    }
}
