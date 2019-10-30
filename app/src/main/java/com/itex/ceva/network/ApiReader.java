package com.itex.ceva.network;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.androidnetworking.error.ANError;
import com.itex.ceva.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Response;

/**
 * Package com.itex.ceva.network in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-10-30
 */
public class ApiReader {
    private static final String connect_error = "connectionError";
    private static final String parse_error = "parseError";
    private static final String request_error = "requestCancelledError";
    private ANError anError;

    private final int code;
    private String message = "";
    private static final String TAG = ApiReader.class.getSimpleName();

    public ApiReader(Response okHttpResponse){
        this.code = okHttpResponse.code();
        Log.d(TAG, "ApiReader: "+okHttpResponse.body());
    }

    public ApiReader(ANError anError){
        this.anError = anError;
        this.code = anError.getErrorCode();
    }

    public String getNetworkErrorMsg(Context context){
        switch (anError.getErrorDetail()) {
            case ApiReader.connect_error:
                return context.getString(R.string.conn_error);
            case ApiReader.parse_error:
                return context.getString(R.string.parse_error);
            case ApiReader.request_error:
                return context.getString(R.string.req_error);
            default:
                return anError.getErrorDetail();
        }
    }


    public boolean isSuccess(){
        int OK_RESPONSE = 200;
        int CREATED_RESPONSE = 201;
        int DUPLICATE_RESPONSE = 202;
        return code == OK_RESPONSE || code == CREATED_RESPONSE || code == DUPLICATE_RESPONSE;
    }

    public boolean isFailed(){
        int VALIDATION_ERROR = 400;
        return code == VALIDATION_ERROR;
    }

    public boolean isAuthorizationError(){
        int UNAUTHORIZED_ERROR = 401;
        int FORBIDDEN_ERROR = 403;
        return code == UNAUTHORIZED_ERROR || code == FORBIDDEN_ERROR;
    }

    public boolean isSystemError(){
        int NOT_FOUND_ERROR = 500;
        int SYSTEM_ERROR = 501;
        return code == SYSTEM_ERROR || code == NOT_FOUND_ERROR;
    }

    public boolean isBadRequest(){
        int BAD_REQUEST = 404;
        return code == BAD_REQUEST;
    }

    public String getErrorTitle(){
        return this.message;
    }

    @Override
    public String toString() {
        return "ApiReader{" +
                "anError=" + anError +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
