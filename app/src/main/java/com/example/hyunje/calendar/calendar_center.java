package com.example.hyunje.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by Mureung on 2017-07-28.
 */


public class calendar_center extends Fragment
{

    private  ViewPagerAdapter mViewPagerAdapter;
    private int num=36;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.viewpager, container, false);    //viewpager 할당해주는 코드

        /*ArrayList<Fragment> mFragments = getFragments();*/
        ViewPager viewPager=(ViewPager)view.findViewById(R.id.view_pager);
        mViewPagerAdapter=new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.setCurrentItem(num);
        viewPager.setOffscreenPageLimit(6);

        return view;
    }

    public  class ViewPagerAdapter extends FragmentStatePagerAdapter  {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            }

        @Override
        public int getCount() {
            return num;}

        @Override
        public Fragment getItem(int position) {

            Fragment fragment=null;
            int x=position+1-num;       //counter 맞추기위한 연산

            fragment=MyFragment.newInstance(String.valueOf(x));
            Log.e("Fragment!!","no:"+x);

            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) { //instance 죽었는지 어떻게 확인???

            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();
            Log.e("no","destroying");
            super.destroyItem(container, position, object);
        }

    }

   /* private ArrayList<Fragment> getFragments(){

        ArrayList<Fragment> fList = new ArrayList<Fragment>();

        for(int i=-11;i<1;i++) {                                //1년치 fragment 생성
            fList.add(MyFragment.newInstance(""+i));
        }
        return fList;
    }*/

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
