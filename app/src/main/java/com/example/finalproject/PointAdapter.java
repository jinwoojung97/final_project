package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PointAdapter extends BaseAdapter {

    // 필드변수 정의
    private Context context;
    private int layout;
    private ArrayList<PointVO> data;
    private LayoutInflater inflater;
    private PointViewHoler holder;

    //생성자 초기화
    public PointAdapter(Context context, int layout, ArrayList<PointVO> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() { return data.size(); }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView = inflater.inflate(layout,null);
            holder = new PointViewHoler();
            holder.img_point = convertView.findViewById(R.id.img_point);
            holder.tv_point_day = convertView.findViewById(R.id.tv_point_day);
            holder.tv_point_content = convertView.findViewById(R.id.tv_point_content);
            holder.tv_point_money = convertView.findViewById(R.id.tv_point_money);
//            holder.tv_point_total = convertView.findViewById(R.id.tv_point_total);

            convertView.setTag(holder);

        }
        holder = (PointViewHoler)convertView.getTag();
        holder.img_point.setImageResource(data.get(position).getPoint_img());
        holder.tv_point_money.setText(data.get(position).getPoint_p());
        holder.tv_point_content.setText(data.get(position).getPoint_content());
        holder.tv_point_day.setText(data.get(position).getPoint_date());

        return convertView;
    }
}
