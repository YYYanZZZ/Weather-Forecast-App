package com.example.forecast.base;

import android.app.DownloadManager;

import androidx.fragment.app.Fragment;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class BaseFragment extends Fragment implements Callback.CommonCallback {
    public void loadData(String url)
    {

        RequestParams params=new RequestParams(url);
        x.http().get(params,this);

    }

    @Override
    public void onSuccess(Object result) {

    }

    @Override
    public void onError(Throwable ex, boolean isOnCallback) {

    }

    @Override
    public void onCancelled(CancelledException cex) {

    }

    @Override
    public void onFinished() {

    }
}
