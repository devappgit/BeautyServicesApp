package com.servpal.android.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public abstract class NetworkCallback<T> implements Callback<T> {

    protected void onSuccess(Response<T> response) {

    }

    protected abstract void onSuccess(T response);

    protected abstract void onError(Error error);

    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if(response == null) {
            Timber.w("Null response");
            this.handleError(new Error(new IllegalStateException("No body returned from the server"), null));
        } else if(!response.isSuccessful()) {
            //this.handleError(new Error(new Exception("Status code outside the 200-300 range"), response));
            this.handleHttpError(response);
        } else if(response.body() == null) {    // could be removed?
            Timber.w("Empty Body");
            this.handleError(new Error(new IllegalStateException("No body returned from the server"), response));
        } else {
            this.onSuccess(response);
            this.onSuccess(response.body());
        }
    }

    public void onFailure(Call<T> call, Throwable t) {
        this.handleError(new Error(t, null));
    }

    private void handleError(Error error) {
        this.onError(error);
    }

    private void handleHttpError(Response response) {
        // TODO: One problem, statusCode is lost through moshi parsing
        JsonAdapter<Error> adapter = new Moshi.Builder().build().adapter(Error.class);
        try {
            this.handleError(adapter.fromJson(response.errorBody().string()));
        } catch (IOException e) {
            this.handleError(new Error(e, response));
        }
    }

    public static class Error {
        transient Throwable throwable;

        String error;
        String state;

        int statusCode;

        Error(Throwable throwable, @Nullable Response response) {
            this.throwable = throwable;
            this.error = parseErrorMessage(throwable, response);
            this.statusCode = parseErrorCode(response);
        }

        public Throwable getThrowable() {
            return this.throwable;
        }

        public String getMessage() {
            return this.error;
        }

        public String getState() {
            return this.state;
        }

        public int getCode() {
            return this.statusCode;
        }

        private String parseErrorMessage(Throwable throwable, @Nullable Response response) {
            if (response != null && response.errorBody() != null) {
                try {
                    return response.errorBody().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return throwable.getMessage();
        }

        private int parseErrorCode(Response response) {
            if (response != null) {
                return response.code();
            }
            return -1;
        }
    }
}
