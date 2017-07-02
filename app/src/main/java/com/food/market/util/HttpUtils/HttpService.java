package com.food.market.util.HttpUtils;

import com.food.market.util.fastjson.ConverterFastJson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

public class HttpService {
    private static HttpApiService service = null;

    //    private static String url = "http://" + Constant.getHost() + ":" + Constant.getPort() + "/" + Constant.getPrj() + "/";
    private static String url = "http://112.91.224.90:8080/crbrWeb/";
    /**
     * 添加头部信息
     * @return
     */
    public static HttpApiService getHttpService() {
        if (service == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.readTimeout(240, TimeUnit.SECONDS);
            builder.connectTimeout(240, TimeUnit.SECONDS);
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {//请求头
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder();
//                        requestBuilder.addHeader(entry.getKey(), entry.getValue());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
            //打印debug日志
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
            final OkHttpClient myHttpClient = builder.build();
            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(new ConverterFastJson())
                    .client(myHttpClient)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            service = restAdapter.create(HttpApiService.class);
        }

        return service;

    }
}
