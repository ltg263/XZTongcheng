package com.jx.xztongcheng.net.service;

import com.jx.xztongcheng.bean.clazz.UserInfo;
import com.jx.xztongcheng.bean.event.AccountLists;
import com.jx.xztongcheng.bean.event.CourierListBaen;
import com.jx.xztongcheng.bean.response.BannerListResponse;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.bean.response.GrabStatusResponse;
import com.jx.xztongcheng.bean.response.LoginResponse;
import com.jx.xztongcheng.bean.response.UserInfoResponse;
import com.jx.xztongcheng.net.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Create by Sxl on 2020/10/15.
 */
public interface UserService {

    @POST("user/api/v1/user/verify/register")
    Observable<BaseResponse<EmptyResponse>> register(@Body RequestBody body);

    @POST("user/api/v1/user/verify/login")
    Observable<BaseResponse<LoginResponse>> login(@Body RequestBody body);

    @GET("user/api/v1/sms/send")
    Observable<BaseResponse<EmptyResponse>> getCode(@Query("type") int type, @Query("mobile") String mobile);

    //用户xinxi
    @GET("user/api/v1/user/current/info")
    Observable<BaseResponse<UserInfo>> getUserInfo();

    //用户余额
    @GET("user/api/v1/feign/user/account/amount")
    Observable<BaseResponse<EmptyResponse>> getAmount(@Query("type") int type, @Query("userId") int userId);

    @GET("user/api/v1/feign/user/account/current/amount")
    Observable<BaseResponse<EmptyResponse>> getCurrentAmount(@Query("type") int type);

    @GET("user/api/v1/user/verify/forget/password")
    Observable<BaseResponse<EmptyResponse>> forgetPassword(@Body RequestBody body);

    @GET("user/api/v1/user/change/mobile")
    Observable<BaseResponse<EmptyResponse>> changeMobile(@Query("mobile") String mobile, @Query("verifyCode") String verifyCode);

    //获取图片路径
    @Multipart
    @POST("appliance/api/v1/cloud/oos/upload")
    //courier_auth
    Observable<BaseResponse<EmptyResponse>> uploadImage(@Part MultipartBody.Part file);

    @Multipart
    @POST("appliance/api/v1/cloud/oos/upload")
        //courier_auth
    Observable<BaseResponse<String>> uploadImage(@PartMap Map<String, RequestBody> map, @Part MultipartBody.Part file);

    @POST("order/api/v1/user/courier/auth/save")
    Observable<BaseResponse<EmptyResponse>> saveAuth(@Body RequestBody body);

    @GET("appliance/api/v1/appliance/ad/list")
    Observable<BaseResponse<BannerListResponse>> getBanner(@Query("seat") int seat);

    @POST("user/api/v1/user/cash/out/account/save")
    Observable<BaseResponse<EmptyResponse>> addCard(@Body RequestBody body);

    @POST("user/api/v1/user/cashout/save")
    Observable<BaseResponse<EmptyResponse>> addCashoutSave(@Body RequestBody body);

    @GET("user/api/v1/user/cash/out/account/list")
    Observable<BaseResponse<AccountLists>> getBankCard();

    @FormUrlEncoded
    @POST("order/api/v1/user/courier/update/current/grab")
    Observable<BaseResponse<EmptyResponse>> updateGrab(@Field("grabStatus")int grabStatus);

    @GET("order/api/v1/user/courier/current/grab")
    Observable<BaseResponse<GrabStatusResponse>> getGrab();

    @GET("user/api/v1/user/cashout/current/list")
    Observable<BaseResponse<CourierListBaen>> getCourierList();

    @GET("user/api/v1/user/cash/out/account/get")
    Observable<BaseResponse<AccountLists.ListBean>> getAcconunt(@Query("id") String id);



    @FormUrlEncoded
    @POST("user/api/v1/user/cash/out/account/save")
    Observable<BaseResponse<EmptyResponse>> addCard(@Field("accountNo") String accountNo,@Field("bank") String bank,
                                                    @Field("mobile") String mobile,@Field("name") String name,
                                                    @Field("status") String status,@Field("cashOutType") String cashOutType);

    @POST("user/api/v1/user/cash/out/account/update")
    Observable<BaseResponse<EmptyResponse>> addCardUpdate(@Body RequestBody body);

    @POST("user/api/v1/user/cash/out/account/update/status")
    Observable<BaseResponse<EmptyResponse>> addCardUpdatestatus(@Body RequestBody body);


    @DELETE("user/api/v1/user/cash/out/account/remove")
    Observable<BaseResponse> deleteCard(@Query("id") int id);
}
