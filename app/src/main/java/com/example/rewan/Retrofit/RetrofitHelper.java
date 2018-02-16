package com.example.rewan.Retrofit;

import android.app.Application;

import com.example.rewan.Network.NetworkHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper extends Application {

    Retrofit retrofit;

    public Retrofit getRetrofitInstance() {
        if (retrofit != null) {
            return retrofit;
        }
        String baseUrl = "http://services.groupkt.com";
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(this.getCacheDir(), cacheSize);
        OkHttpClient okHttpClient = NetworkHelper.getBuilder(this, cache).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
    @Override
    public void onCreate() {
        retrofit = null;
        super.onCreate();
    }
}