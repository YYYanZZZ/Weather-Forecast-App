package com.example.forecast.CityManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.location.AMapLocationClient;
import com.example.forecast.R;

import java.util.ArrayList;
import java.util.List;

import com.example.forecast.database.DBManager;
import com.example.forecast.database.Databasebean;

public class CityManagerActivity extends AppCompatActivity  {
    ImageView addIv,backIv,deleteIv;
    ListView cityLv;
    List<Databasebean>mDatas; //显示列表数据源
    private CityManagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_manager);
        addIv=findViewById(R.id.city_iv_add);
        backIv=findViewById(R.id.city_iv_back);
        deleteIv=findViewById(R.id.city_iv_delete);
        cityLv=findViewById(R.id.city_lv);
        mDatas=new ArrayList<>();

        //添加监听事件
        addIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cityCount= DBManager.getCityCount();
                if(cityCount<7){
            Intent intent=new Intent(addIv.getContext(), SearchCityActivity.class);
            startActivity(intent);}
                else {
                    Toast.makeText(addIv.getContext(),"存储城市数量已经达到上限，请删除后添加",Toast.LENGTH_SHORT).show();
                }
            }
        });
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(backIv.getContext(), DeleteCityActivity.class);
                startActivity(intent);
            }
        });
        //设置适配器
        adapter = new CityManagerAdapter(this, mDatas);
        cityLv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Databasebean> list=DBManager.queryAllInfo();
        mDatas.clear();;
        mDatas.addAll(list);
        adapter.notifyDataSetChanged();
    }
}