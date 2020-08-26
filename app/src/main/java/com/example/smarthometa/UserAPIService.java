package com.example.smarthometa;

import com.example.smarthometa.Id;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UserAPIService {
    @GET("/api/v1/devices")
    Call<Id> getResultInfo();

    @GET("/api/v1/devices")
    Call<ResponseBody> getResultAsJSON();

    @GET("/api/v1/devices/5efaf8303691e4d53c695ac7")
    Call<Device> getStatus1();

    @GET("/api/v1/devices/5efaf8303691e4d53c695ac8")
    Call<Device> getStatus2();

    @GET("/api/v1/devices/5efaf8303691e4d53c695aca")
    Call<Device> getStatus3();

    @GET("/api/v1/devices/5efaf8303691e4d53c695ac9")
    Call<Device> getStatus4();

    @GET("/api/v1/messages")
    Call<List<History>> addMessage();
}

