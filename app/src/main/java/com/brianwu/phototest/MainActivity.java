package com.brianwu.phototest;

import android.os.AsyncTask;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.brianwu.fragment.MainFragment;
import com.brianwu.utils.CustomProgressDialog;
import com.brianwu.utils.KeyBoardUtils;
import com.brianwu.utils.NetworkUtil;
import com.brianwu.bean.Pixabay;
import com.brianwu.network.APIRequst;


public class MainActivity extends FragmentActivity {
    private EditText mEditquery;
    private RelativeLayout mSearchview;
    private CustomProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkUtil.setNetWorkForICS();
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mProgressDialog = new CustomProgressDialog();
        mProgressDialog.setUpMyDialog(MainActivity.this , getString(R.string.main_searching));
        mEditquery = (EditText)findViewById(R.id.edit_query) ;
        mSearchview  = (RelativeLayout)findViewById(R.id.rl_searchview);
        mEditquery.setOnKeyListener(new View.OnKeyListener() {
           public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER)) {
                    getPhotoData (mEditquery.getText().toString());
                    return true;
                }
                return false;
            }
        });
        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhotoData (mEditquery.getText().toString());
            }
        });
    }
    private void getPhotoData(String keyword) {
        KeyBoardUtils.hideKeyboardFrom(MainActivity.this ,mEditquery);
        mProgressDialog.showMyDialog();
        new getApiAsyncTack().execute(keyword);
    }
    public void replaceFragment(int container,Fragment fragment){
        mSearchview.setVisibility(View.GONE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private class getApiAsyncTack extends AsyncTask<String , Void , Pixabay>{
        @Override
        protected Pixabay doInBackground(String... params) {
            return APIRequst.getInstance().getPixbayData(params[0]);
        }
        @Override
        protected void onPostExecute(Pixabay pixabay) {
            super.onPostExecute(pixabay);
            mProgressDialog.dismissMyDialog();
            if(pixabay != null) {
                if (pixabay.getHits().size() != 0) {
                    replaceFragment(R.id.container, MainFragment.newInstance(pixabay));
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.main_search_no_data), Toast.LENGTH_LONG).show();
                }
            } else{
                Toast.makeText(MainActivity.this , getString(R.string.main_search_null) , Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            mSearchview.setVisibility(View.VISIBLE);
            getSupportFragmentManager().popBackStack();
        }else{
            finish();
        }
    }
}

