package com.brianwu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brianwu.bean.Pixabay;
import com.brianwu.fragment.adapter.MainFragmentAdapter;
import com.brianwu.phototest.R;
import com.brianwu.view.SlidingTabLayout;

/**
 * Created by Brian-SSD on 2016/11/24.
 */
public class MainFragment extends Fragment {

    private ViewPager mViewPager;
;
    private static String ARG_COLUMN_DATA = "arg_data";
    private Pixabay mColumnData;
    private SlidingTabLayout mSlidingTabLayout;

    public static MainFragment newInstance(Pixabay data) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_COLUMN_DATA , (Parcelable) data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnData= getArguments().getParcelable(ARG_COLUMN_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_main, container,false);
        mViewPager = (ViewPager) convertView.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MainFragmentAdapter(getChildFragmentManager(),getContext() , mColumnData));
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mSlidingTabLayout = (SlidingTabLayout) convertView
                .findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);
        return convertView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }




}
