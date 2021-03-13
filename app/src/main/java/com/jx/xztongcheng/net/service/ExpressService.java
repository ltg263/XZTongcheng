package com.jx.xztongcheng.net.service;

import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.net.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Create by Sxl on 2020/11/20.
 */
public interface ExpressService {

    @POST("api/v1/user/courier/express/core/express/list")
    Observable<BaseResponse<EmptyResponse>> listExpress();

}
