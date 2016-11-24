package com.brianwu.utils;

import android.os.Build;
import android.os.StrictMode;

/**
 * Created by Brian-SSD on 2016/11/23.
 */
public class NetworkUtil {

    public static void setNetWorkForICS() {
        try{
            double d = Build.VERSION.SDK_INT;
            if(d >= 11){
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

        }catch(Exception ex){
            ex.printStackTrace();

        }

    }
}
