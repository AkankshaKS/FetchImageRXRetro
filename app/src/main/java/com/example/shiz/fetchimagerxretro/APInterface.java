package com.example.shiz.fetchimagerxretro;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

public interface APInterface {

    @Streaming
    @GET
    Observable<ResponseBody> getFile(@Url String url);

}
