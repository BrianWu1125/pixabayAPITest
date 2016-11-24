package com.brianwu.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Brian-SSD on 2016/11/24.
 */
public class KeyBoardUtils {
    public static void hideKeyboardFrom(Context context, View view) {
        if (view != null && context != null) {
            view.clearFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }
}
