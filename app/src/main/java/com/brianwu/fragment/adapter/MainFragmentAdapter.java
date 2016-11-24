package com.brianwu.fragment.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.brianwu.bean.Pixabay;
import com.brianwu.fragment.ItemFragment;
import com.brianwu.phototest.R;

/**
 * Created by Brian-SSD on 2016/11/24.
 */
public class MainFragmentAdapter extends FragmentStatePagerAdapter{


    private final Pixabay mPixabay;
    private Context context;
         public MainFragmentAdapter(FragmentManager fm, Context context , Pixabay pixabay) {
            super(fm);
            this.context = context;
            this.mPixabay = pixabay;

        }

        /**
         * @return the number of pages to display
         */
        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return context.getResources().getString(R.string.main_tab_grid);
                case 1:
                    return context.getResources().getString(R.string.main_tab_list);
            }
            return "";
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return ItemFragment.newInstance(mPixabay , ViewType.GIRD );
                case 1:
                    return ItemFragment.newInstance(mPixabay  , ViewType.LIST);

                default:
                    return null;
            }
        }

    public enum ViewType{
        GIRD,LIST
    }
}
