package com.league2.app.Service;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class SummonerErrorHandler implements ErrorHandler {

    @Override
    public Throwable handleError(RetrofitError retrofitError) {
        return null;
    }
}
