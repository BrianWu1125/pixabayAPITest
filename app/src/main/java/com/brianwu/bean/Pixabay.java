package com.brianwu.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by user on 2016/11/23.
 */
public class Pixabay implements Parcelable {
    private int totalHits ;
    private int total ;
    private ArrayList<HitBean> hits;

    public ArrayList<HitBean> getHits() {
        return hits;
    }

    public void setHits(ArrayList<HitBean> hits) {
        this.hits = hits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
