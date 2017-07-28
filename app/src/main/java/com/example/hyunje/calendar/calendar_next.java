package com.example.hyunje.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by HyunJe on 2017-07-27.
 */


public class calendar_next extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view =inflater.inflate(R.layout.view1, container, false);
        TextView text=(TextView) view.findViewById(R.id.text);
        text.setText("next");
        return view;
    }
}
