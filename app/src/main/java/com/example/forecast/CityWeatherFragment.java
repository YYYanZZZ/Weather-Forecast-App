package com.example.forecast;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.amap.api.location.AMapLocationClient;
import com.example.forecast.CityManager.CityManagerActivity;
import com.example.forecast.base.UniteApp;
import com.example.forecast.database.DBManager;

import java.util.ArrayList;
import java.util.List;
import com.example.forecast.base.BaseFragment;
import com.example.forecast.base.NetUtil;
import com.example.forecast.bean.CityBean;
import com.example.forecast.bean.IndexBean;
import com.example.forecast.bean.TodayWeatherBean;
import com.example.forecast.bean.Weatherbean;
import com.example.forecast.database.DBManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class CityWeatherFragment extends BaseFragment {
    TextView tempTv, cityTv, conditionTv, windTv, tempRangeTv, dateTv, clothesIndexTv, carIndexTv, coldIndexTv, sportIndexTv, raysIndexTv;
    ImageView dayIv;
    LinearLayout futureLayout;
    ScrollView outLayout;
    String Todayurl1 = "https://devapi.qweather.com/v7/weather/now?location=";
    String Weatherurl1 = "https://devapi.qweather.com/v7/weather/7d?location=";
    String Indexurl1 = "https://devapi.qweather.com/v7/indices/1d?type=0&location=";
    String Cityurl1 = "https://geoapi.qweather.com/v2/city/lookup?location=";
    String keyurl = "&key=b73d5de4ed484686a2944c777a751480";
    String citynum;
    String Cityurl;
    String Todayurl;
    String city;
    String weatherurl;
    String Indexurl;
    Myhandler handler = new Myhandler();
    CountDownLatch latch = new CountDownLatch(1);
    private SharedPreferences pref;
    private int bgNum;
    public void exchangeBg()
    {
        pref= getActivity().getSharedPreferences("bg_pref", Context.MODE_PRIVATE);
        bgNum = pref.getInt("bg", 0);
        switch (bgNum) {
            case 0:
                outLayout.setBackgroundResource(R.mipmap.img);
                break;
            case 1:
                outLayout.setBackgroundResource(R.mipmap.shanhe);
                break;
            case 2:
                outLayout.setBackgroundResource(R.mipmap.yunkairiming);
                break;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_city_weather, container, false);
        initView(view);
        exchangeBg();
        Bundle bundle = getArguments();
        city = bundle.getString("city");
        Cityurl = Cityurl1 + city + keyurl;
        CityThread cityThread = new CityThread();
        cityThread.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WeatherThread weatherThread = new WeatherThread();
        Todayurl = Todayurl1 + citynum + keyurl;
        weatherurl = Weatherurl1 + citynum + keyurl;
        Indexurl = Indexurl1 + citynum + keyurl;
        weatherThread.start();
        TodayWeatherThread todayWeatherThread = new TodayWeatherThread();
        todayWeatherThread.start();
        IndexThread indexThread=new IndexThread();
        indexThread.start();
        /* Click();*/
        return view;
    }

    class Myhandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                cityTv.setText(msg.getData().getString("content"));
            } else if (msg.what == 2) {
                tempRangeTv.setText(msg.getData().getString("tempRang"));
                windTv.setText(msg.getData().getString("wind"));
                dateTv.setText(msg.getData().getString("date"));
            } else if (msg.what == 3) {
                conditionTv.setText(msg.getData().getString("cond"));
                tempTv.setText(msg.getData().getString("temp"));
                String s=msg.getData().getString("cond");
                int i= DBManager.updateInfoByCity(city,msg.getData().getString("todayweatherbean"));
                if(i<=0)
                {
                    System.out.println("传入数据"+city);
                    System.out.println("传入数据"+msg.getData().getString("todayweatherbean"));
                    //增加记录
                    DBManager.addCityInfo(city,msg.getData().getString("todayweatherbean"));
                }
                if(s.contains("晴"))  dayIv.setImageResource(R.mipmap.sunny);
                else if(s.contains("雨")&&!s.contains("雪")) dayIv.setImageResource(R.mipmap.rain);
                else if (s.contains("雷")) dayIv.setImageResource(R.mipmap.thunder);
                else if(s.contains("雹")) dayIv.setImageResource(R.mipmap.ice);
                else if(s.contains("雪")) dayIv.setImageResource(R.mipmap.snow);
                else if(s.contains("多云")||s.contains("阴")) dayIv.setImageResource(R.mipmap.cloudy);
                else if(s.contains("雨")&&s.contains("雪")) dayIv.setImageResource(R.mipmap.rainwithsnow);
            } else if (msg.what == 0) {
                Weatherbean weatherbean = new Gson().fromJson(msg.getData().getString("bean"), Weatherbean.class);
                List<Weatherbean.DailyBean> futureweather = weatherbean.getDaily();
                futureweather.remove(0);
                for (int i = 0; i < futureweather.size(); i++) {
                    View itemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_main_center, null);
                    itemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    futureLayout.addView(itemView);
                    TextView idateTv = itemView.findViewById(R.id.itme_center_tv_date);
                    TextView iconTv = itemView.findViewById(R.id.itme_center_tv_con);
                    TextView itemprangTV = itemView.findViewById(R.id.itme_center_tv_temp);
                    ImageView itv = itemView.findViewById(R.id.itme_center_iv);
                    Weatherbean.DailyBean databean = futureweather.get(i);
                    idateTv.setText(databean.getFxDate());
                    iconTv.setText(databean.getTextDay());
                    String range = databean.getTempMax() + "℃~" + databean.getTempMin() + "℃";
                    itemprangTV.setText(range);
                    if (databean.getTextDay().contains("晴")) itv.setImageResource(R.mipmap.sunny);
                    else if (databean.getTextDay().contains("雨"))
                        itv.setImageResource(R.mipmap.rain);
                    else if (databean.getTextDay().contains("雷"))
                        itv.setImageResource(R.mipmap.thunder);
                    else if (databean.getTextDay().contains("阴天"))
                        itv.setImageResource(R.mipmap.cloudy);
                    else if (databean.getTextDay().contains("雹"))
                        itv.setImageResource(R.mipmap.ice);
                    else if (databean.getTextDay().contains("雪"))
                        itv.setImageResource(R.mipmap.snow);
                    else if (databean.getTextDay().contains("多云"))
                        itv.setImageResource(R.mipmap.cloudy);
                    else if (databean.getTextDay().contains("雨") && databean.getTextDay().contains("雪"))
                        itv.setImageResource(R.mipmap.rainwithsnow);
                }
            }
            else if (msg.what==4){
                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                IndexBean indexBean = new Gson().fromJson(msg.getData().getString("Indexbean"), IndexBean.class);
                IndexBean.DailyBean sportIndex = indexBean.getDaily().get(0);
                IndexBean.DailyBean coldIndex = indexBean.getDaily().get(8);
                IndexBean.DailyBean washIndex = indexBean.getDaily().get(1);
                IndexBean.DailyBean clothesIndex = indexBean.getDaily().get(2);
                IndexBean.DailyBean rayIndex = indexBean.getDaily().get(4);
                List<IndexBean.DailyBean> Index = new ArrayList<>();
                Index.add(sportIndex);
                Index.add(coldIndex);
                Index.add(washIndex);
                Index.add(clothesIndex);
                Index.add(rayIndex);
                clothesIndexTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            String msg ="穿衣指数为"+Index.get(3).getLevel()+"，"+Index.get(3).getText();
                            builder.setTitle("穿衣指数");
                            builder.setMessage(msg);
                            builder.setPositiveButton("确定",null);
                            builder.create().show();
                    }
                });
                carIndexTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            builder.setTitle("洗车指数");
                            String msg = "洗车指数为" + Index.get(2).getLevel()+"，" + Index.get(2).getText();
                            builder.setMessage(msg);
                            builder.setPositiveButton("确定", null);
                            builder.create().show();
                    }
                });
                coldIndexTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            builder.setTitle("感冒指数");
                            String msg = "感冒指数为" + Index.get(1).getLevel() +"，"+ Index.get(1).getText();
                            builder.setMessage(msg);
                            builder.setPositiveButton("确定", null);
                            builder.create().show();
                    }
                });
                sportIndexTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            builder.setTitle("运动指数");
                            String msg = "运动指数为" + Index.get(0).getLevel() +"，"+ Index.get(0).getText();
                            builder.setMessage(msg);
                            builder.setPositiveButton("确定", null);
                            builder.create().show();
                        }
                });
                raysIndexTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            builder.setTitle("紫外线指数");
                            String msg = "紫外线指数为" + Index.get(4).getLevel() +"，"+ Index.get(4).getText();
                            builder.setMessage(msg);
                            builder.setPositiveButton("确定", null);
                             builder.create().show();
                    }
                });
            }
        }
    }

    class CityThread extends Thread {
        @Override
        public void run() {
            super.run();
            String cityinfo;
            try {
                cityinfo = NetUtil.net(Cityurl, null, "GET");
                System.out.println(cityinfo);
                CityBean cityBean = new Gson().fromJson(cityinfo, CityBean.class);
                Message message1 = new Message();
                Bundle bundle1 = new Bundle();
                if (cityBean != null && cityBean.getLocation() != null && !cityBean.getLocation().isEmpty()) {
                    bundle1.putString("content", cityBean.getLocation().get(0).getName());
                    String s = cityBean.getLocation().get(0).getId();
                    citynum = s;
                    System.out.println(s);
                }
                message1.what = 1;
                message1.setData(bundle1);
                handler.sendMessage(message1);
                latch.countDown();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    class WeatherThread extends Thread {
        @Override
        public void run() {
            super.run();
            String str;
            try {
                latch.await();
                System.out.println(weatherurl);
                str = NetUtil.net(weatherurl, null, "GET");
                Message infomessage = new Message();
                Bundle infobundle = new Bundle();
                infobundle.putString("bean", str);
                infomessage.setData(infobundle);
                infomessage.what = 0;
                handler.sendMessage(infomessage);
                Weatherbean weatherbean = new Gson().fromJson(str, Weatherbean.class);
                Weatherbean.DailyBean dailyBean = weatherbean.getDaily().get(0);
                Message message = new Message();
                Bundle bundle = new Bundle();
                if (dailyBean != null) {
                    String temp = dailyBean.getTempMax() + "℃~" + dailyBean.getTempMin() + "℃";
                    String wind = dailyBean.getWindDirDay() + dailyBean.getWindScaleDay() + "级";
                    bundle.putString("tempRang", temp);
                    bundle.putString("wind", wind);
                    bundle.putString("date", dailyBean.getFxDate());
                }
                message.what = 2;
                message.setData(bundle);
                handler.sendMessage(message);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    class TodayWeatherThread extends Thread {
        @Override
        public void run() {
            super.run();
            String str;
            try {
                latch.await();
                str = NetUtil.net(Todayurl, null, "GET");
                TodayWeatherBean todayWeatherBean = new Gson().fromJson(str, TodayWeatherBean.class);
                Message message = new Message();
                Bundle bundle = new Bundle();
                if (todayWeatherBean != null) {
                    String temp = todayWeatherBean.getNow().getTemp() + "℃";
                    String cond = todayWeatherBean.getNow().getText();
                    bundle.putString("temp", temp);
                    bundle.putString("cond", cond);
                    bundle.putString("todayweatherbean",str);
                }
                message.what = 3;
                message.setData(bundle);
                handler.sendMessage(message);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    class IndexThread extends  Thread{
        @Override
        public void run() {
            super.run();
            String str;
            try {
                latch.await();
                str = NetUtil.net(Indexurl, null, "GET");
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("Indexbean", str);
                message.what=4;
                message.setData(bundle);
                handler.sendMessage(message);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void initView(View view)
    {
        tempTv=view.findViewById(R.id.frag_tv_currenttemp);
        cityTv=view.findViewById(R.id.frag_tv_city);
        conditionTv=view.findViewById(R.id.frag_cv_condition);
        windTv=view.findViewById(R.id.frag_tv_wind);
        tempRangeTv=view.findViewById(R.id.frag_cv_temp);
        dateTv=view.findViewById(R.id.frag_cv_time);
        clothesIndexTv=view.findViewById(R.id.frag_index_clothes);
        carIndexTv=view.findViewById(R.id.frag_index_wash);
        coldIndexTv=view.findViewById(R.id.frag_cv_cold);
        sportIndexTv=view.findViewById(R.id.frag_cv_sport);
        raysIndexTv=view.findViewById(R.id.frag_index_rays);
        dayIv=view.findViewById(R.id.frag_tv_today);
        futureLayout=view.findViewById(R.id.frag_center_layout);
        outLayout=view.findViewById(R.id.imageofday);
    }

}