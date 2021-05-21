package com.example.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class PopupActivity extends Activity {

    TextView txtText;
    String result;
    String[] question_c_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        //UI 객체생성
        txtText = (TextView)findViewById(R.id.txtText);

        //데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("data"); //팝업창에 띄울 메신저
        String rdata = intent.getStringExtra("r.data");
        result = intent.getStringExtra("result"); //정답체크

        txtText.setText(data);
    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        if("1".equals(result)){
            setResult(RESULT_OK, intent);
            intent.putExtra("result", "성공 포인트 적금");
        }else if("2".equals(result)){
            setResult(RESULT_CANCELED, intent);
            intent.putExtra("result", "실패 포인트 적금 실패");
        }else if("3".equals(result)){
            setResult(RESULT_CANCELED, intent);
            intent.putExtra("result", "이미 적립되었습니다");
        }
        //액티비티(팝업) 닫기
        finish();
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        //바깥레이어 클릭시 안닫히게
//        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
//            return false;
//        }
//        return true;
//    }



}