package com.example.forecast;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.forecast.CityManager.CityManagerActivity;
import com.example.forecast.base.UniteApp;
import com.example.forecast.database.DBManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageView addCity, more;
    ViewPager mainvp;
    LinearLayout pointLayout;
    RelativeLayout outLayout;
    List<Fragment> fragmentList;
    List<String> citylist;
    List<ImageView> imgList;
    String Gcity;
    CityFragmentPagerAdapter adapter;
    private SharedPreferences pref;
    private int bgNum;
    private boolean isDialogShow = false;
    private AlertDialog.Builder builder;

    public MainActivity() throws Exception {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addCity = findViewById(R.id.add);
        more = findViewById(R.id.more);
        mainvp = findViewById(R.id.weather_viewpage);
        pointLayout = findViewById(R.id.pointLayout);
        outLayout = findViewById(R.id.main_out_layout);
        exchangeBg();
        Intent intent = new Intent();
        //点击事件
        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(addCity.getContext(), CityManagerActivity.class);
                startActivity(intent);
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(addCity.getContext(), MoreActivity.class);
                startActivity(intent);
            }
        });
        fragmentList = new ArrayList<>();
        citylist = DBManager.queryAllcityName();//获取数据库的城市列表
        imgList = new ArrayList<>();

        if (citylist.size() == 0) {
            AMapLocationClient.updatePrivacyAgree(this, true);
            AMapLocationClient.updatePrivacyShow(this, true, true);
           citylist.add("沈阳");
        }
        /*获取搜索界面传来的界面*/
        Intent intent1 = getIntent();
        String city = intent1.getStringExtra("city");
        if (!citylist.contains(city) && !TextUtils.isEmpty(city)) citylist.add(city);
        /*测试定位*/



        /*初始化pager*/
        initPager();
        adapter = new CityFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        mainvp.setAdapter(adapter);
        //创建小圆点
        initPoint();
        mainvp.setCurrentItem(0);
        //设置监听
        setPagerLIstenner();
    }

    public void exchangeBg() {
        pref = getSharedPreferences("bg_pref", MODE_PRIVATE);
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

    private void setPagerLIstenner() {
        mainvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < imgList.size(); i++) {
                    imgList.get(i).setImageResource(R.mipmap.love);
                }
                imgList.get(position).setImageResource(R.mipmap.reallove);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initPoint() {
        for (int i = 0; i < fragmentList.size(); i++) {
            ImageView pIv = new ImageView(this);
            pIv.setImageResource(R.mipmap.love);
            pIv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) pIv.getLayoutParams();
            lp.setMargins(0, 20, 20, 0);
            imgList.add(pIv);
            pointLayout.addView(pIv);
        }
        imgList.get(0).setImageResource(R.mipmap.reallove);
    }

    private void initPager() {
        for (int i = 0; i < citylist.size(); i++) {
            CityWeatherFragment cwFrag = new CityWeatherFragment();
            Bundle bundle = new Bundle();
            bundle.putString("city", citylist.get(i));
            cwFrag.setArguments(bundle);
            fragmentList.add(cwFrag);
        }
    }

    /*页面加载调用的函数*/
    @Override
    protected void onRestart() {
        super.onRestart();
        List<String> list = DBManager.queryAllcityName();
        if (list.size() == 0) {
            AMapLocationClient.updatePrivacyAgree(this, true);
            AMapLocationClient.updatePrivacyShow(this, true, true);
            citylist.add("沈阳");
        }
        citylist.clear();
        citylist.addAll(list);
        fragmentList.clear();
        initPager();
        adapter.notifyDataSetChanged();
        imgList.clear();
        pointLayout.removeAllViews();
        initPoint();
        mainvp.setCurrentItem(fragmentList.size() - 1);
    }



}