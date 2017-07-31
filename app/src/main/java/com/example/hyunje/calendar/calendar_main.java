/*

package com.example.hyunje.calendar;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


*/
/**
 * Created by Mureung on 2017-07-24.
 * http://heum-story.tistory.com/6 - 원본 코드
 *//*



public class calendar_main extends Fragment {
    private TextView tvDate;
    private GridAdapter gridAdapter;
    private GridView gridView;
    private ArrayList<String> dayList;
    private Calendar mCal;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.view1, container, false);

        tvDate = (TextView)view.findViewById(R.id.tv_date);

        long now = System.currentTimeMillis(); //long을 int로 바꾸면 에러남
        final Date date = new Date(now);

        //연,월,일을 따로 저장- 파싱하는데 필요한 코드

        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜(월,일) 텍스트뷰에 뿌려줌

        tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));


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

        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) -1 , 1);   //mCal을 그년도, 그달, 1일로 설정
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);    //1일이 무슨요일인지 판단

        //1일 앞에 - 요일 매칭 시키기 위해 공백 add

        for (int i = 1; i < dayNum; i++) {
            dayList.add("");
        }

        setCalendarDate(mCal.get(Calendar.MONTH) + 1);  //+1: 1월달이 0 이기 때문

        gridView = (GridView)view.findViewById(R.id.gridview);
        gridAdapter = new GridAdapter(getActivity().getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);

        */
/*//*
/그리드뷰 터치리스터
        gridView.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(@SuppressWarnings("unused") View view, MotionEvent event)
            {
                // 터치 이벤트가 일어난 뷰가 ViewFlipper가 아니면 return

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    xAtDown = event.getX(); // 터치 시작지점 x좌표 저장
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    xAtUp = event.getX(); // 터치 끝난지점 x좌표 저장
                }

                if (xAtUp < xAtDown) {

                } else if (xAtUp > xAtDown) {
                    // 전 view 보여줌
                     }
                return true;
         }
        });*//*


        return view;
    }

    private void setCalendarDate(int month) {   //daylist에 날짜 추가하는 함수

        mCal.set(Calendar.MONTH, month - 1);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
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
            ViewHolder holder=null; //

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

            if (sToday.equals(getItem(position))) {                 //오늘날짜 =  getitem(position) 판단

                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
            //test

            return convertView; //변경된 convertView
        }
    }

    private class ViewHolder {      //뷰들을 보관하는 객체
        TextView tvItemGridView;
    }
}



*/
/*
/parsing 예제
            ArrayList<String> test;
            test = new ArrayList<String>();
            test.add("123456789");
            String parsing=test.get(0).substring(4,6);
            Log.e("parsing","46:"+parsing);*//*
*/
/*
*//*


*/
