package com.zlw.lifequan.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zlw on 2017/1/23.
 */
public class TestFragment extends Fragment {


    private String text = "";


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView textView = new TextView(getActivity());

        textView.setText("TEST");
        textView.setTextSize(23);
        return textView;
    }

}
