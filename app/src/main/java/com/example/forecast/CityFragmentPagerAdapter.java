package com.example.forecast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class CityFragmentPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList;

    public CityFragmentPagerAdapter(@NonNull FragmentManager fm,List<Fragment>fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    int childCount=0; //表示包含的页数
    @Override
    public void notifyDataSetChanged()
    {
        this.childCount=getCount();
        super.notifyDataSetChanged();
    }
    @Override
    public int getItemPosition(@NonNull Object object){
        if(childCount>0)
        {
            childCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
