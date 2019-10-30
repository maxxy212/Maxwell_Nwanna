package com.itex.ceva.network;

/**
 * Package com.itex.ceva.network in
 * <p>
 * Project Ceva
 * <p>
 * Created by Maxwell on 2019-10-30
 */
public abstract class ApiCallHandler {
    protected abstract void done();
    public void success(Object data){
        this.done();
    }
    public void failed(String title, String reason){
        this.done();
    }
}
