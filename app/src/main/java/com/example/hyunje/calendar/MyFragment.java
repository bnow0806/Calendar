package com.example.hyunje.calendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mureung on 2017-07-28.
 */

public class MyFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private TextView tvDate;
    private GridAdapter gridAdapter;
    private GridView gridView;
    private ArrayList<String> dayList;
    private Calendar mCal;
    private int counter;

    public static final MyFragment newInstance(String message) {    //newInstance 함수
        MyFragment f = new MyFragment();
        Bundle bdl = new Bundle(1);
        bdl.putString(EXTRA_MESSAGE, message);
        f.setArguments(bdl);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        String message = getArguments().getString(EXTRA_MESSAGE);   //인자를 전달받음
        counter=Integer.parseInt(message);

        View v = inflater.inflate(R.layout.view1, container, false);
        tvDate = (TextView)v.findViewById(R.id.tv_date);

        long now = System.currentTimeMillis(); //long을 int로 바꾸면 에러남
        final Date date = new Date(now);
        //달력의 년,월을 만들기위한 데이터 저장

        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);

        //gridview 요일 표시

        dayList = new ArrayList<String>();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");

        mCal = Calendar.getInstance();  //calendar instance 생성

        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) -1+counter , 1);   //mCal을 그년도, 그달, 1일로 설정

        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);    //1일이 무슨요일인지 판단
        //1일 앞에 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {      // 그월에 1일 맞추는 함수
            dayList.add("");
        }

//현재 날짜(월,일) 텍스트뷰에 뿌려줌

        tvDate.setText(mCal.get(Calendar.YEAR) + "/" + (mCal.get(Calendar.MONTH)+1));

        setCalendarDate(mCal.get(Calendar.MONTH) + 1);  //+1: 1월달이 0 이기 때문

        gridView = (GridView)v.findViewById(R.id.gridview);
        gridAdapter = new GridAdapter(getActivity().getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);

        gridView.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View v, MotionEvent me)  //1.뷰페이저 스크롤시 터치문제!!! 2.new calendar 생성문제
            {
                int action = me.getActionMasked(); // MotionEvent types such as ACTION_UP,ACTION_DOWN
                float currentXPosition = me.getX();
                float currentYPosition = me.getY();
                int position = gridView.pointToPosition((int) currentXPosition, (int) currentYPosition); // Access text in the cell, or the object itself
                Calendar mCaltouch = Calendar.getInstance();    //새로생성
                mCaltouch.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) -1+counter , 1);
                String text=mCaltouch.get(Calendar.YEAR) + "/" + (mCaltouch.get(Calendar.MONTH)+1+"/"+gridView.getItemAtPosition(position));
                Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return v;
    }
    private void setCalendarDate(int month) {   //daylist에 날짜 추가하는 함수

        mCal.set(Calendar.MONTH, month - 1);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {    //그달의 날짜만큼 30 or 31
            dayList.add("" + (i + 1));
        }
    }

    private class GridAdapter extends BaseAdapter { //GridAdapter 안에 getView가 있음

        private final LayoutInflater inflater;      //class내 변수
        private final List<String> list;

        public GridAdapter(Context context, List<String> list) {        //생성자

            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //setContentView(R.layout.main);와 같은 기능
            this.list = list;       //  list = daylist가 됨
        }

        @Override
        public int getCount() {

            return list.size();
        }

        @Override
        public String getItem(int position) {

            return list.get(position);           //getItem(20) ==dayList.get(20) 일치
        }

        @Override
        public long getItemId(int position) {
            return position;}

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.e("getView","alive");
            ViewHolder holder=null;

            if (convertView == null) {      //초기에 convertView=null, convertView는 재활용과 관련된 파라메터

                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);

                holder = new ViewHolder();
                holder.tvItemGridView = (TextView)convertView.findViewById(R.id.tv_item_gridview);
                convertView.setTag(holder);                  //convertView 에 textView 붙이기
            } else {
                holder = (ViewHolder)convertView.getTag();      //convetView 가 null 이 아닌 경우에는 convertView 에 붙여놓았던 textView 를 그냥 가져다 쓰면 된다.
            }

            holder.tvItemGridView.setText(" "+getItem(position));   //itemgridview에 입력할 텍스트값 대입(여백포함)
            //해당 날짜 텍스트 컬러,배경 변경

            mCal = Calendar.getInstance();

            //오늘 day 가져옴
            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);
            Log.e("sToday",""+sToday);

            if ((counter==0)&(sToday.equals(getItem(position)))) {                 //오늘날짜 =  getitem(position) 판단

                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.colorPrimary));
            }

            return convertView; //변경된 convertView
        }
    }

    private class ViewHolder {      //뷰들을 보관하는 객체
        TextView tvItemGridView;
    }
}


