package com.example.azram.aatdiscussionscc1.api;

import com.example.azram.aatdiscussionscc1.api.data.AnswerWrapper;
import com.example.azram.aatdiscussionscc1.api.data.PostRootWrapper;
import com.example.azram.aatdiscussionscc1.api.data.GetMePleaseWrapper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ExampleApiService {

    String BASE_URL = "http://58489054.ngrok.io/";

    @Headers("Content-Type: application/json")
    @POST("jedan")
    Call<Void> postRoot(@Body PostRootWrapper postRootWrapper);

    @GET("get_me_please")
    Call<GetMePleaseWrapper> getMePlease();

    @Headers("Content-Type: application/json")
    @POST("answer")
    Call<Void> postAnswer(@Body AnswerWrapper answerWrapper);
}