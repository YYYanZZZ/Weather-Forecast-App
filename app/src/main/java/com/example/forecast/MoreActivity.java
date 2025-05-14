package com.example.forecast;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocationClient;
import com.example.forecast.database.DBManager;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.forecast.CityManager.CityManagerActivity;

public class MoreActivity extends AppCompatActivity {
    TextView bgTv,cacheTv,versionTv,shareTv;
    RadioGroup exbgRg;
    ImageView backIv;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        bgTv = findViewById(R.id.more_tv_exchangebg);
        cacheTv = findViewById(R.id.more_tv_cache);
        versionTv = findViewById(R.id.more_tv_version);
        shareTv = findViewById(R.id.more_tv_share);
        backIv = findViewById(R.id.more_iv_back);
        exbgRg = findViewById(R.id.more_rg);
        pref=getSharedPreferences("bg_pref",MODE_PRIVATE);
        setRGListener();
        bgTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(exbgRg.getVisibility()==View.VISIBLE)
                {
                    exbgRg.setVisibility(View.GONE);
                }
                else {
                    exbgRg.setVisibility(View.VISIBLE);
                }
            }
        });
        cacheTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clearCache();
            }
        });
        shareTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareSoftWareMsg("本软件是一款超萌超可爱(由作者废了九牛二虎之力熬了n天n夜写)的天气预报（其实还可以做课程表和音乐播放器）软件！");
            }
        });
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });
        String versionName=gerVersionName();
        versionTv.setText("当前版本：  v"+versionName);
    }

    private void setRGListener() {
        /* 设置改变背景图片的单选按钮的监听*/
        exbgRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                获取目前的默认壁纸
                int bg = pref.getInt("bg", 0);
                SharedPreferences.Editor editor = pref.edit();
                Intent intent = new Intent(MoreActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                if(checkedId== R.id.more_rb_dayvhaitang){
                        if (bg==0) {
                            Toast.makeText(MoreActivity.this,"您选择的为当前背景，无需改变！",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        editor.putInt("bg",0);
                        editor.commit();}
                else if (checkedId== R.id.more_rb_wanyanshanhe) {
                    if (bg == 1) {
                        Toast.makeText(MoreActivity.this, "您选择的为当前背景，无需改变！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    editor.putInt("bg", 1);
                    editor.commit();
                } else if ( R.id.more_rb_yunkairiming==checkedId) {
                    if (bg==2) {
                        Toast.makeText(MoreActivity.this,"您选择的为当前背景，无需改变！",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    editor.putInt("bg",2);
                    editor.commit();
                }
                startActivity(intent);
            }
        });
    }

    private String gerVersionName() {
        PackageManager manager = getPackageManager();
        String versionName = null;
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;

    }

    private void shareSoftWareMsg(String s) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,s);
        startActivity(Intent.createChooser(intent,"Jay‘s天气预报"));
    }

    private  void   clearCache(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示信息").setMessage("确定要删除所有缓存么？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBManager.deleteAllInfo();
                Toast.makeText(MoreActivity.this,"已清除全部缓存！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MoreActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).setNegativeButton("取消",null);
        builder.create().show();
    }
}