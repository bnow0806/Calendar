package com.example.hyunje.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mureung on 2017-07-28.
 */


public class calendar_center extends Fragment
{

    private  ViewPagerAdapter mViewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.viewpager, container, false);    //viewpager 할당해주는 코드

        ArrayList<Fragment> mFragments = getFragments();
        ViewPager viewPager=(ViewPager)view.findViewById(R.id.view_pager);
        mViewPagerAdapter=new ViewPagerAdapter(getFragmentManager(),mFragments);
        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.setCurrentItem(mFragments.size()/2);

        return view;
    }

    public  class ViewPagerAdapter extends FragmentPagerAdapter  {
        private List<Fragment> fragments;
        private FragmentManager fm;

        public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fm=fm;
            this.fragments = fragments;}

        @Override
        public int getCount() {
            return fragments.size();}

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);}
    }

    private ArrayList<Fragment> getFragments(){

        ArrayList<Fragment> fList = new ArrayList<Fragment>();

        for(int i=0;i<36;i++) {
            fList.add(MyFragment.newInstance("Fragment"+i));
        }
        return fList;
    }

}







  /*  @Override
    public Fragment getItem(int position) {         //viewpager 제어 해주는 코드
        Fragment fragmet=null;
        switch (position){
            case 0:
                fragmet=new calendar_pre();
                break;
            case 1:
                fragmet=new calendar_now();
                break;
            case 2:
                fragmet=new calendar_next();
                break;
        }
        return fragmet;
    }*/