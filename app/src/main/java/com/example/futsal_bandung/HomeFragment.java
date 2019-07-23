package com.example.futsal_bandung;


import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


public class HomeFragment extends Fragment {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;
    private ImageView slideImageView;
    private TextView[] mDots;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        mSlideViewPager = (ViewPager) v.findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) v.findViewById(R.id.dotsLayout);
        sliderAdapter = new SliderAdapter(getContext());
        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        return v;

    }

    public void addDotsIndicator(int position) {

        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {

            mDots[i] = new TextView(getContext());
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.indicator));

            mDotLayout.addView(mDots[i]);

        }



        }



}
