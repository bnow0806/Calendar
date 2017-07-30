package com.example.hyunje.calendar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Mureung on 2017-07-28.
 */

public class MyFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

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

        View v = inflater.inflate(R.layout.view1, container, false);
        TextView messageTextView = (TextView)v.findViewById(R.id.text);
        messageTextView.setText(message);       //인자를 텍스트로 표시함

        return v;

    }
}


