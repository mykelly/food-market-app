package com.food.market.util.HttpUtils;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;


public interface HttpApiService {

    @POST("itsmEntry/createFaultEntry/v1")
    Observable<ResponseTemplate<Object>> commitItsmList(@Body HashMap<String, Object> params);


}
