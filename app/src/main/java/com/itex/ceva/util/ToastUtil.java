package com.itex.ceva.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Package com.itex.ceva.util in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-10-30
 */
public class ToastUtil {
    public static void makeToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public static void makeToastLong(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
