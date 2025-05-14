package com.example.forecast.CityManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.amap.api.location.AMapLocationClient;
import com.example.forecast.MainActivity;
import com.example.forecast.R;
import com.example.forecast.base.NetUtil;
import com.example.forecast.bean.CityBean;
import com.google.gson.Gson;

import java.util.concurrent.CountDownLatch;

public class SearchCityActivity extends AppCompatActivity {

    EditText searchET;
    ImageView submitIv;
    GridView searchGv;
    String city;
    String Cityurl1 = "https://geoapi.qweather.com/v2/city/lookup?location=";
    String keyurl = "&key=" + BuildConfig.WEATHER_API_KEY;
    String cityurl;
    String []hotcitys={"北京", "上海", "广州", "深圳", "成都", "重庆", "杭州", "武汉", "西安", "郑州", "青岛", "长沙", "天津", "苏州", "南京", "东莞", "沈阳", "合肥", "佛山", "昆明", "福州", "无锡", "厦门", "哈尔滨", "长春", "南昌", "济南", "宁波", "大连", "贵阳", "温州", "石家庄"};
/*    MyHandler handler=new MyHandler();*/
    CountDownLatch latch = new CountDownLatch(1);
    static int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_city);
        searchET=findViewById(R.id.search_et);
        submitIv=findViewById(R.id.search_iv_submit);
        searchGv=findViewById(R.id.search_gv);
        submitIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city=searchET.getText().toString();
                cityurl=Cityurl1+city+keyurl;
                CityThread cityThread=new CityThread();
                cityThread.start();

                if(!TextUtils.isEmpty(city)){
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                //判断能否找到这个城市
                        if(flag==0) {
                            Toast.makeText(submitIv.getContext(),"此城市不存在或未收入此城市天气信息",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent intent = new Intent(submitIv.getContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("city",city);
                            startActivity(intent);
                        }
                    }
                else {
                    Toast.makeText(submitIv.getContext(),"输入内容不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //设置适配器
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_hotcity, hotcitys);
        searchGv.setAdapter(adapter);
        setListener();
    }

    private void setListener() {
        searchGv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        city=hotcitys[position];
                        cityurl=Cityurl1+city+keyurl;
                        Intent intent = new Intent(searchGv.getContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("city",city);
                        startActivity(intent);
                    }
                }
        );
    }

    class CityThread extends Thread {
        @Override
        public void run() {
            super.run();
            String cityinfo;
            try {
                cityinfo = NetUtil.net(cityurl, null, "GET");
                CityBean cityBean = new Gson().fromJson(cityinfo, CityBean.class);
           /*     Message message1 = new Message();
                Bundle bundle1 = new Bundle();
                if (cityBean != null && cityBean.getLocation() != null && !cityBean.getLocation().isEmpty()) {
                    bundle1.putString("content", cityBean.getCode());
                }
                message1.what = 1;
                message1.setData(bundle1);
                handler.sendMessage(message1);*/
                switch(cityBean.getCode()) {
                    case "404":
                    {flag = 0;
                        break;}
                    default:{
                        flag=1;
                        break;}
                }
                latch.countDown();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
 /*   class MyHandler extends Handler{
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                switch (msg.getData().getString("content"))
                {
                    case "404": {flag=0; break;}
                    default: {flag=1; break;}
                }
                latch.countDown();
            }
        }
    }*/
}