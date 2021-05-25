package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RankAdapter  extends BaseAdapter {


    //필드변수 정의
    private Context context;
    private int layout;
    private ArrayList<RankVO> data;
    private LayoutInflater inflater;
    private RankViewHolder holder;

    public RankAdapter(Context context, int layout, ArrayList<RankVO> data) {
        this.context = context;
        this.layout = layout;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

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
            convertView = inflater.inflate(layout, null);

            holder = new RankViewHolder();
            holder.img_medal = convertView.findViewById(R.id.img_medal);
            holder.tv_rank_region = convertView.findViewById(R.id.tv_rank_region);
            holder.tv_rank_count = convertView.findViewById(R.id.tv_rank_count);
            holder.tv_rank_total = convertView.findViewById(R.id.tv_rank_total);

            convertView.setTag(holder);
        }

        holder = (RankViewHolder)convertView.getTag();
        holder.img_medal.setImageResource(data.get(position).getImg_medal());   //이거 추가
        holder.tv_rank_region.setText(data.get(position).getRegion());
        holder.tv_rank_count.setText(data.get(position).getCountId());
        holder.tv_rank_total.setText(data.get(position).getTotalPoint());



        return convertView;
    }
}
