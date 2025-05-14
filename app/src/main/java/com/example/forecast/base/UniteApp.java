package com.example.forecast.base;

import android.app.Application;

import com.example.forecast.database.DBManager;

public class UniteApp extends Application {
   public void onCreate()
   {
       super.onCreate();
       DBManager.iniDB(this);
   }
}
