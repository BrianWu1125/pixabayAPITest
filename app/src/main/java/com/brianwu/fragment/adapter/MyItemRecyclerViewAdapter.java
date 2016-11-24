package com.brianwu.fragment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.brianwu.bean.HitBean;
import com.brianwu.fragment.ItemFragment.OnListFragmentInteractionListener;
import com.brianwu.fragment.dummy.DummyContent.DummyItem;
import com.brianwu.phototest.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<HitBean> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final ImageLoader imageLoader;
    private final DisplayImageOptions options;
//    private final DisplayImageOptions options;


    public MyItemRecyclerViewAdapter(Context context , List<HitBean> items, OnListFragmentInteractionListener listener ) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .diskCache(new UnlimitedDiskCache(new File(Environment.getExternalStorageDirectory() +"/test"))) // default
                .diskCacheSize(200 * 1024 * 1024)
                .diskCacheFileCount(200)
                .build();

        ImageLoader.getInstance().init(config);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.transparent)

                .showImageForEmptyUri(null)
                .showImageOnFail(null)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        mValues = items;
        mListener = listener;
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        imageLoader.displayImage(mValues.get(position).getPreviewURL(), holder.mPhotoView , options  ,new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String s, View view) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private final ImageView mPhotoView;

        public HitBean mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mPhotoView = (ImageView) view.findViewById(R.id.img_photo);

        }
    }
}
