package com.example.azram.aatdiscussionscc1;

import com.example.azram.aatdiscussionscc1.data.AnswerWrapper;
import com.example.azram.aatdiscussionscc1.data.Example;
import com.example.azram.aatdiscussionscc1.data.GetMePleaseWrapper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ExampleApiService {
    @Headers("Content-Type: application/json")
    @POST("jedan")
    Call<Void> postRoot(@Body Example example);

    @GET("get_me_please")
    Call<GetMePleaseWrapper> getMePlease();

    @Headers("Content-Type: application/json")
    @POST("answer")
    Call<Void> postAnswer(@Body AnswerWrapper answerWrapper);
}