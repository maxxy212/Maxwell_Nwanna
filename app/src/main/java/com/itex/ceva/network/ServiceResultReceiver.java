package com.itex.ceva.network;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Package com.itex.ceva.network in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-10-30
 */
public class ServiceResultReceiver extends ResultReceiver {

    private Receiver mReceiver;

    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler the handler object
     */

    public ServiceResultReceiver(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver mReceiver) {
        this.mReceiver = mReceiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (mReceiver != null){
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }
}
