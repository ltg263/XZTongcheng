package com.jx.xztongcheng;

import android.os.Handler;
import android.util.Log;

import com.google.gson.JsonObject;
import com.jx.xztongcheng.app.App;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * Create by Sxl on 2020/12/23.
 */
public class JWebSocketClient extends WebSocketClient {

    public interface WebSocketConnectListener {
        //断开链接
        void onClose();

        //链接完成
        void onConnect();



        //鉴权完成
        void authFinish(int status);

        //刷新数据
        void refreshData(String resp, int type);
    }

    private static final String url = "wss://xztc.nbyjdz.com/ws/user/" + App.getInstance().getUserInfo().getUserId();
    private static final long HEART_BEAT_RATE = 3 * 1000;//每隔30秒进行一次对长连接的心跳检测
    private WebSocketConnectListener webSocketConnectListener;

    public JWebSocketClient() {
        super(URI.create(url));
    }

    private Handler heartHandler = new Handler();
    private Runnable hearRunable = new Runnable() {
        @Override
        public void run() {
            checkConnect();
//            doHeartPing();
            heartHandler.postDelayed(hearRunable, HEART_BEAT_RATE);
        }
    };

    public void setWebSocketConnectListener(WebSocketConnectListener webSocketConnectListener) {
        this.webSocketConnectListener = webSocketConnectListener;
    }

    public void doConnect() {
        connect();

        //开启心跳
        heartHandler.postDelayed(hearRunable, HEART_BEAT_RATE);
    }

    private void doHeartPing() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("message", "38");
        jsonObject.addProperty("type", "2");
        String jsonStr = jsonObject.toString();
        safeSend(jsonStr);
    }


    public void sendMessage(String message, String type) {
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("message", message);
        jsonObject2.addProperty("type", type);
        String jsonStr2 = jsonObject2.toString();
        safeSend(jsonStr2);
    }

    private void safeSend(String message) {
//        if (checkConnect()) {
        try {
            send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        }
    }

    /**
     * 开启重连
     */
    private boolean checkConnect() {
        if (isClosed()) {
            reconnect();
            return false;
        }
        return true;
    }


    @Override
    public void onOpen(ServerHandshake handshakedata) {
        Log.e("JWebSocketClient", "onOpen()");
        if (webSocketConnectListener != null) {
            webSocketConnectListener.onConnect();
        }
    }

    @Override
    public void onMessage(String message) {
        Log.e("JWebSocketClient", "onMessage()");
        if (webSocketConnectListener != null) {
            webSocketConnectListener.refreshData(message, 1);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.e("JWebSocketClient", "onClose()");
        close();
    }

    @Override
    public void onError(Exception ex) {
        Log.e("JWebSocketClient", "onError()");
    }
}
