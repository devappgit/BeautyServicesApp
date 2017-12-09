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
        // direct access to Response object to get headers and other information
    }

    protected abstract void onSuccess(T response);

    protected abstract void onError(Error error);

    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.body() == null) {
            Timber.w("Empty Body");
            this.handleError(new Error(new IllegalStateException("No body returned from the server"), response));
        } else if(!response.isSuccessful()) {
            this.handleHttpError(response);
        } else {
            this.onSuccess(response);
            this.onSuccess(response.body());
        }
    }

    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        this.handleError(new Error(t, null));
    }

    private void handleError(Error error) {
        this.onError(error);
    }

    private void handleHttpError(Response response) {
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

        Error(Throwable throwable, @Nullable Response response) {
            this.throwable = throwable;
            this.error = parseErrorMessage(throwable, response);
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
    }
}
