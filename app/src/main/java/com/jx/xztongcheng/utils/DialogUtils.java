package com.jx.xztongcheng.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class DialogUtils {

    public static AlertDialog cancelDialog(Context context, String title, String content, DialogInterface.OnClickListener listener) {

        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("确定",listener)
                .setNegativeButton("取消",null)
                .create();

    }
    public static AlertDialog donelDialog(Context context, String title, String content) {

        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton("确定",null)
                .create();

    }
}
