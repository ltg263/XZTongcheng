package com.jx.xztongcheng.net;


import com.blankj.utilcode.util.SPUtils;
import com.jx.xztongcheng.BuildConfig;
import com.jx.xztongcheng.app.App;
import com.jx.xztongcheng.utils.ConstValues;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by wjr on 2019/8/30
 * Des: Retrofit2.0
 */
public class RetrofitManager {

    private static final int DEFAULT_TIME_OUT = 30;//超时时间5s
    private static final int DEFAULT_READ_TIME_OUT = 30;//读取时间
    private static final int DEFAULT_WRITE_TIME_OUT = 30;//读取时间

    //有网刷新时不使用缓存
    public static final int CACHE_AGE_SHORT = 0;
    //长缓存有效期为1周
    public static final int CACHE_STALE_LONG = 7 * 60 * 60 * 24;

    public static RetrofitManager mRetrofitManager;

    public Retrofit mRetrofit;

    private OkHttpClient mOkHttpClient;


    //单例
    public static RetrofitManager build() {
        if (mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager();
                }
            }
        }
        return mRetrofitManager;
    }

    public RetrofitManager() {

        // 指定缓存路径,缓存大小10Mb
        Cache cache = new Cache(new File(App.getInstance().getCacheDir(), "HttpCache"),
                1024 * 1024 * 10);
        // 添加日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.SECONDS)
                // 添加通用的Header
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder
                                .addHeader("Content-Type", "application/json; charset=utf-8")
                                .addHeader("Authorization", SPUtils.getInstance().getString(ConstValues.TOKENID));

                        return chain.proceed(builder.build());
                    }
                })
                .addInterceptor(loggingInterceptor)
                //.addNetworkInterceptor(mCacheControlInterceptor)//缓存策略暂时用不上
                .cache(cache)
                .build();


        mRetrofit = new Retrofit.Builder()
                .baseUrl(ConstValues.BASE_URL)
                .client(mOkHttpClient)
                // 添加Retrofit到RxJava的转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    /**
     * 响应头拦截器，用来配置缓存策略
     * 我们如果哪里需要缓存数据的话，只要在请求里添加header（“Cache-Time”，“3600*24”）就可以把当前数据缓存一天啦
     */
    private Interceptor mCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            String cache = request.header("Cache-Time");
            if (!"".equals(cache)) {//缓存时间不为空
                Response response1 = response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        //cache for cache seconds
                        .header("Cache-Control", "max-age=" + cache)
                        .build();
                return response1;
            } else {
                return response;
            }
        }
    };


    public class NullOnEmptyConverterFactory extends Converter.Factory {

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
            return new Converter<ResponseBody, Object>() {
                @Override
                public Object convert(ResponseBody body) throws IOException {
                    if (body.contentLength() == 0) return null;
                    return delegate.convert(body);
                }
            };
        }
    }

    /**
     * 创建相应的服务接口
     */
    public <T> T create(Class<T> reqServer) {
        return mRetrofit.create(reqServer);
    }

}
