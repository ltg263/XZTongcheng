package com.jx.xztongcheng.net.service;

import com.jx.xztongcheng.bean.event.CarListBean;
import com.jx.xztongcheng.bean.response.OrderSheetInfo;
import com.jx.xztongcheng.bean.response.EmptyResponse;
import com.jx.xztongcheng.bean.response.CoreOrderList;
import com.jx.xztongcheng.bean.response.OrderListBean;
import com.jx.xztongcheng.bean.response.StatisResponse;
import com.jx.xztongcheng.bean.response.WebSiteReponse;
import com.jx.xztongcheng.net.BaseResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Create by Sxl on 2020/11/20.
 */
public interface OrderService {

    @GET("order/api/v1/feign/express/order/list")
    Observable<BaseResponse<EmptyResponse>> getCurrentAmount(@Query("type") int type);

    @GET("order/api/v1/feign/express/order/get")
    Observable<BaseResponse<EmptyResponse>> orderDetail(@Query("id") int id);

    @GET("order/api/v1/user/general/order/core/list")
    Observable<BaseResponse<CoreOrderList>> coreList(@QueryMap Map<String, Object> map);

    @GET("order/api/v1/feign/express/order/get")
    Observable<BaseResponse<EmptyResponse>> orderDetail1(@Query("id") int id);

    @GET("order/api/v1/user/general/order/core/current/list")
    Observable<BaseResponse<CoreOrderList>> myOrderList(@QueryMap Map<String, Object> map);

    @GET("order/api/v1/user/courier/express/core/express/sheet/info")
    Observable<BaseResponse<OrderSheetInfo>> myOrderSheetInfo(@Query("expressOrderId") String expressOrderId);

    @GET("order/api/v1/user/courier/express/core/express/sheet/barcode")
    Observable<ResponseBody> myOrderBarcodeInfo(@Query("expressOrderId") String expressOrderId);

    @FormUrlEncoded
    @POST("order/api/v1/user/courier/express/core/receive/express")
    Observable<BaseResponse<EmptyResponse>> receiveExpress(@Field("expressGeneralOrderId") int expressGeneralOrderId);

    @GET("order/api/v1/user/general/order/core/get")
    Observable<BaseResponse<OrderListBean>> getOrderDetail(@Query("expressOrderId") int orderId);

    @FormUrlEncoded
    @POST("order/api/v1/user/courier/express/core/user/pickup")
    Observable<BaseResponse<EmptyResponse>> pickupOrder(@Field("expressOrderId") int expressOrderId, @Field("code") String code);

    @FormUrlEncoded
    @POST("order/api/v1/user/courier/express/core/storage")
    Observable<BaseResponse<Object>> coreStorage(@Field("expressOrderNo") String expressOrderNo, @Field("weight") String weight);

    @FormUrlEncoded
    @POST("order/api/v1/user/courier/express/core/out/warehouse")
    Observable<BaseResponse<Object>> outWarehouse(@Field("expressOrderNo") String expressOrderNo, @Field("websiteCarId") String websiteCarId);

    @FormUrlEncoded
    @POST("order/api/v1/user/courier/express/core/user/pickup")
    Observable<BaseResponse<EmptyResponse>> pickupOrder(@Field("expressOrderId") int expressOrderId, @Field("code") String code, @Field("url") String url);

    @FormUrlEncoded
    @POST("order/api/v1/user/courier/express/core/user/finish")
    Observable<BaseResponse<EmptyResponse>> finish(@Field("expressOrderId") int expressOrderId, @Field("code") String code);

    @FormUrlEncoded
    @POST("order/api/v1/user/courier/express/core/user/send")
    Observable<BaseResponse<EmptyResponse>> send(@Field("expressOrderId") int expressOrderId);

    @POST("order/api/v1/user/courier/express/core/user/setting/price")
    Observable<BaseResponse<Double>> settingPrice(@Body RequestBody body);

    @GET("order/api/v1/user/express/courier/qr")
    Observable<ResponseBody> getQrCode(@Query("type") int type, @Query("id") Integer expressOrderId);

    @GET("order/api/v1/order/website/car/list")
    Observable<BaseResponse<CarListBean>> getCarList(@QueryMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("order/api/v1/user/courier/update/current/website")
    Observable<BaseResponse<EmptyResponse>> addSite(@Field("websiteId") int websiteId);

    @FormUrlEncoded
    @POST("order/api/v1/user/courier/exit/current/website")
    Observable<BaseResponse<EmptyResponse>> exitWebsite(@Field("websiteId") int websiteId);

    @GET("order/api/v1/user/website/list")
    Observable<BaseResponse<WebSiteReponse>> getWebsite(@Query("lng") double lng, @Query("lat") double lat);

    @FormUrlEncoded
    @POST("order/api/v1/user/courier/express/core/transfer")
    Observable<BaseResponse<EmptyResponse>> transfer(@Field("expressOrderId") int expressOrderId);

    @GET("order/api/v1/user/courier/order/statistic")
    Observable<BaseResponse<StatisResponse>> orderStatistic(@Query("dataType") int dataType, @Query("startDate") String startDate, @Query("endDate") String endDate);

    @GET("order/api/v1/user/courier/income/statistic")
    Observable<BaseResponse<StatisResponse>> incomeStatistic(@Query("dataType") int dataType, @Query("startDate") String startDate, @Query("endDate") String endDate);

    @GET("order/api/v1/user/courier/customer/statistic")
    Observable<BaseResponse<StatisResponse>> customerStatistic(@Query("dataType") int dataType, @Query("startDate") String startDate, @Query("endDate") String endDate);


}
