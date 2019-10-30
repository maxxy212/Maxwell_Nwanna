package com.itex.ceva.network.background;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.itex.ceva.network.ApiCallHandler;
import com.itex.ceva.network.ServiceResultReceiver;
import com.itex.ceva.network.calls.CevaApiCall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Package com.itex.ceva.network.background in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-10-30
 */
public class GetCevaDataService extends JobIntentService {
    private static final String TAG = GetCevaDataService.class.getSimpleName();
    private static final int JOB_ID = 1;
    public static final String RECEIVER = "receiver";
    public static final String REQUEST_TYPE = "request_type";
    public static final int SHOW_RESULT = 100;
    public static final int ERROR = 1;
    public static final String KEY = "data";

    public static final int TYPE_1 = 9;
    public static final int TYPE_2 = 8;
    public static final int TYPE_3 = 7;

    /**
     * Result receiver object to send results
     */
    private ResultReceiver mResultReceiver;
    private static final String ACTION_ARRANGE = "action.GET_DATA";

    public static void startActionRefresh(Context context, int type, ServiceResultReceiver workerResultReceiver) {
        Intent intent = new Intent(context, GetCevaDataService.class);
        intent.putExtra(RECEIVER, workerResultReceiver);
        intent.putExtra(REQUEST_TYPE, type);
        intent.setAction(ACTION_ARRANGE);
        enqueueWork(context, GetCevaDataService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        if (intent.getAction() != null) {
            if (ACTION_ARRANGE.equals(intent.getAction())) {
                int type = intent.getIntExtra(REQUEST_TYPE, 0);
                switch (type){
                    case TYPE_1:
                        getTenthChar(intent);
                        break;
                    case TYPE_2:
                        getEveryTenCharacters(intent);
                        break;
                    case TYPE_3:
                        getAllWords(intent);
                        break;
                }
            }
        }
    }

    private void getTenthChar(Intent intent){
        CevaApiCall.getCevaCharacter(getApplicationContext(), new ApiCallHandler() {
            @Override
            protected void done() {

            }

            @Override
            public void success(Object data) {
                super.success(data);
                String text = (String) data;
                text = text.replaceAll("\\s", "");
                Log.d(TAG, "success: "+ text.charAt(9));
                mResultReceiver = intent.getParcelableExtra(RECEIVER);
                Bundle bundle = new Bundle();
                bundle.putString(KEY, String.valueOf(text.charAt(9)).toUpperCase());
                mResultReceiver.send(SHOW_RESULT, bundle);
            }

            @Override
            public void failed(String title, String reason) {
                super.failed(title, reason);
                mResultReceiver = intent.getParcelableExtra(RECEIVER);
                Bundle bundle = new Bundle();
                bundle.putString(KEY, reason);
                mResultReceiver.send(ERROR, bundle);
            }
        });
    }

    private void getEveryTenCharacters(Intent intent){
        CevaApiCall.getCevaCharacter(getApplicationContext(), new ApiCallHandler() {
            @Override
            protected void done() {

            }

            @Override
            public void success(Object data) {
                super.success(data);
                String text = (String) data;
                text = text.replaceAll("\\s", "");
                List<String> tenthChars = new ArrayList<>();
                // I stopped at 100 just so the loop doesn't take long
                for (int i = 0; i < 200; i = i + 10){
                    tenthChars.add(String.valueOf(text.charAt(i)).toUpperCase());
                }
                mResultReceiver = intent.getParcelableExtra(RECEIVER);
                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY, (Serializable) tenthChars);
                mResultReceiver.send(SHOW_RESULT, bundle);
            }

            @Override
            public void failed(String title, String reason) {
                super.failed(title, reason);
                mResultReceiver = intent.getParcelableExtra(RECEIVER);
                Bundle bundle = new Bundle();
                bundle.putString(KEY, reason);
                mResultReceiver.send(ERROR, bundle);
            }
        });
    }

    private void getAllWords(Intent intent){
        CevaApiCall.getCevaCharacter(getApplicationContext(), new ApiCallHandler() {
            @Override
            protected void done() {

            }

            @Override
            public void success(Object data) {
                super.success(data);
                String text = (String) data;
                int length = countUniquedWords(text);
                mResultReceiver = intent.getParcelableExtra(RECEIVER);
                Bundle bundle = new Bundle();
                bundle.putInt(KEY, length);
                mResultReceiver.send(SHOW_RESULT, bundle);
            }

            @Override
            public void failed(String title, String reason) {
                super.failed(title, reason);
                mResultReceiver = intent.getParcelableExtra(RECEIVER);
                Bundle bundle = new Bundle();
                bundle.putString(KEY, reason);
                mResultReceiver.send(ERROR, bundle);
            }
        });
    }

    private int countUniquedWords(String str) {
        // Extracting words from string
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(str);

        // Map to store count of a word
        HashMap<String, Integer> hm = new HashMap<>();

        // if a word found
        while (m.find()) {
            String word = m.group();
            // If this is first occurrence of word
            if (!hm.containsKey(word))
                hm.put(word, 1);
            else
                // increment counter of word
                hm.put(word, hm.get(word) + 1);
        }

        // Traverse map and print all words whose count
        // is  1
        Set<String> s = hm.keySet();
        return s.size();
    }
}
