package com.example.azram.aatdiscussionscc1;

import com.example.azram.aatdiscussionscc1.data.Example;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ExampleApiService {
    @Headers("Content-Type: application/json")
    @POST("jedan")
    Call<Void> postRoot(@Body Example example);
}