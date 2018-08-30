package com.morpheus.morpheus.WebService;

/**
 * Created by Morpheus on 30/08/2018.
 */

public interface OnResultElementListener<T>
{
    void onSuccess(T result);
    void onFailed(String message, int code);
}
