package com.itex.ceva.util;

import android.app.Activity;

import com.itex.ceva.R;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Package com.itex.ceva.util in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-10-30
 */
public class DialogUtil {

    private final Activity activity;
    private final String title;

    public DialogUtil(Activity activity, String title) {
        this.activity = activity;
        this.title = title;
    }

    public KProgressHUD showNonCloseableProgress(){
        KProgressHUD hud = null;
        if (!activity.isFinishing())
            if (title == null || title.isEmpty()){
                hud =  KProgressHUD.create(activity)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel(activity.getString(R.string.load))
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
            }else {
                hud =  KProgressHUD.create(activity)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setDetailsLabel(title)
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
            }

        return hud;

    }
}
