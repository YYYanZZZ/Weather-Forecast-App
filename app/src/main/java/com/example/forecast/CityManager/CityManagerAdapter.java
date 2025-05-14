package com.example.forecast.CityManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import com.example.forecast.R;
import com.example.forecast.bean.TodayWeatherBean;
import com.example.forecast.bean.Weatherbean;
import com.example.forecast.database.Databasebean;
import com.google.gson.Gson;

public class CityManagerAdapter extends BaseAdapter {

    Context context;
    List<Databasebean>mDatas;

    public CityManagerAdapter(Context context, List<Databasebean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        for (Databasebean bean : mDatas) {
            System.out.println("City: " + bean.getCity());
            System.out.println("Content: " + bean.getContent());
            // 打印其他属性信息
            System.out.println("------------------");
        }
        ViewHolder holder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_vity_manager,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder=(ViewHolder) convertView.getTag();
        }
        Databasebean bean=mDatas.get(position);
        holder.cityTv.setText(bean.getCity());;
        TodayWeatherBean weatherbean = new Gson().fromJson(bean.getContent(), TodayWeatherBean.class);
        holder.conTV.setText("天气:"+weatherbean.getNow().getText());
        holder.currentTempTv.setText(weatherbean.getNow().getTemp()+"℃");
        holder.windTv.setText(weatherbean.getNow().getWindDir()+weatherbean.getNow().getWindScale()+"级");
        return  convertView;

    }
    class ViewHolder{
        TextView cityTv,conTV,currentTempTv,windTv;
        public ViewHolder(View itemView){
            cityTv=itemView.findViewById(R.id.item_city_tv_city);
            conTV=itemView.findViewById(R.id.item_city_tv_condition);
            currentTempTv=itemView.findViewById(R.id.item_city_tv_temp);
            windTv=itemView.findViewById(R.id.item_city_wind);
        }
    }
}
