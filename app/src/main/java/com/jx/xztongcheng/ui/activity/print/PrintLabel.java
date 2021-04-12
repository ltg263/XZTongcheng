package com.jx.xztongcheng.ui.activity.print;

import android.graphics.Bitmap;
import android.util.Log;

import com.jx.xztongcheng.bean.response.OrderSheetInfo;
import com.qr.print.PrintPP_CPCL;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintLabel {
    public static int y = 30;
    public static void Lable(PrintPP_CPCL iPrinter, Bitmap mBitmap,Bitmap mBitmapGg,OrderSheetInfo coreOrderList) {
        Log.w("coreOrderList","coreOrderList:"+coreOrderList.toString());
//        if(true){
//            return;
//        }

        iPrinter.pageSetup(585, 1050);

        iPrinter.drawLine(2, 0, 75+y, 580, 75+y, false);//横线1
        iPrinter.drawLine(2, 0, 110+y, 580, 110+y, false);//横线2
        iPrinter.drawLine(2, 0, 250+y, 580, 250+y, false);//横线3
        iPrinter.drawLine(2, 0, 450+y, 580, 450+y, false);//横线4
        iPrinter.drawLine(2, 0, 600+y, 580, 600+y, false);//横线5
        iPrinter.drawLine(2, 0, 750+y, 580, 750+y, false);//横线6
        iPrinter.drawLine(2, 0, 800+y, 580, 800+y, false);//横线7
        iPrinter.drawLine(2, 0, 900+y, 580, 900+y, false);//横线8
        iPrinter.drawLine(2, 0, 75+y, 0, 900+y, false);//竖线1
        iPrinter.drawLine(2, 45, 250+y, 45, 750+y, false);//竖线2
        iPrinter.drawLine(2, 400, 600+y, 400, 750+y, false);//竖线3
        iPrinter.drawLine(2, 580, 75+y, 580, 900+y, false);//竖线4


        iPrinter.drawGraphic(10,10,540,40+y,mBitmap);
        if(mBitmapGg!=null){
            iPrinter.drawGraphic(20,820+y,540,80,mBitmapGg);
        }

        iPrinter.drawText(10, 55+y, getTimeToYMD(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"),1,0,0,false,false);

        iPrinter.drawText(6, 80+y, "热线电话：400-6898-588", 2, 0, 0, false, false);

        iPrinter.drawBarCode(20,130+y, coreOrderList.getWaybillNumber(), 1, 0, 2, 70);
        iPrinter.drawText(50, 210+y, coreOrderList.getWaybillNumber(), 2, 0, 0, false, false);

        iPrinter.drawText(390, 150+y,170,140, coreOrderList.getWebsiteName(), 3, 0, 1, false, false);

        //收件人
        iPrinter.drawText(2 + 4, 250 + 50+y, 43, 200, "收件人", 3, 0, 1, false, false);

        //收件人姓名＋电话，最终实施时请用变量替换
        iPrinter.drawText(2 + 4 + 43 + 8, 250 + 10+y , 480, 40, coreOrderList.getConsigneeName() + " " + coreOrderList.getConsigneeMobile(), 3, 0, 1, false, false);

        //收件地址 ，最终实施时请用变量替换
        iPrinter.drawText(2 + 4 + 43 + 8, 250 + 10  + 40 +10+y, 480, 160, coreOrderList.getConsigneeAddress(), 3, 0, 1, false, false);

        //寄件人
        iPrinter.drawText(2 + 8, 250 + 30 +200+y, 43, 200, "寄件人", 2, 0, 0, false, false);

        //寄件人姓名＋电话，
        iPrinter.drawText(2 + 4 + 43 + 8, 250 + 20+200+y, 480, 40, coreOrderList.getMailingName() + " " + coreOrderList.getMailingMobile(), 2, 0, 0, false, false);
        //寄件人地址
        iPrinter.drawText(2 + 4 + 43 + 8, 250 + 20+200+40+y, 480, 160, coreOrderList.getMailingAddress(), 2, 0, 0, false, false);

        //内容品名
        iPrinter.drawText(2 + 8, 630+y, 43, 200, "内容品名", 2, 0, 0, false, false);


        switch (coreOrderList.getType()){//1线上(不用操作)2现金支付3到付4待收货跨
            case "1":
                iPrinter.drawText(2 + 8, 760+y, "现付：", 2, 0, 0, false, false);
                iPrinter.drawText(2 + 8+200, 760+y, "到付：", 2, 0, 0, false, false);
                iPrinter.drawText(2 + 8+380, 760+y, "代收：", 2, 0, 0, false, false);
                break;
            case "2":
                iPrinter.drawText(2 + 8, 760+y, "现付："+coreOrderList.getAmount()+"元", 2, 0, 0, false, false);
                iPrinter.drawText(2 + 8+200, 760+y, "到付：", 2, 0, 0, false, false);
                iPrinter.drawText(2 + 8+380, 760+y, "代收：", 2, 0, 0, false, false);
                break;
            case "3":
                iPrinter.drawText(2 + 8, 760+y, "现付：", 2, 0, 0, false, false);
                iPrinter.drawText(2 + 8+200, 760+y, "到付："+coreOrderList.getAmount()+"元", 2, 0, 0, false, false);
                iPrinter.drawText(2 + 8+380, 760+y, "代收：", 2, 0, 0, false, false);
                break;
            case "4":
                iPrinter.drawText(2 + 8, 760+y, "现付：", 2, 0, 0, false, false);
                iPrinter.drawText(2 + 8+200, 760+y, "到付：", 2, 0, 0, false, false);
                iPrinter.drawText(2 + 8+380, 760+y, "代收："+coreOrderList.getAmount()+"元", 2, 0, 0, false, false);
                break;
        }


        //内容
        iPrinter.drawText(2 + 4 + 43 + 8, 610+y, 400, 200, coreOrderList.getExpressName(), 2, 0, 0, false, false);
        iPrinter.drawText(2 + 4 + 43+400-30, 610+y, 300, 160, "签收人:", 2, 0, 0, false, false);
        iPrinter.drawText(2 + 4 + 43+400-30, 700+y, 400, 40, "日期：", 2, 0, 0, false, false);

        iPrinter.print(0, 0);

    }
    public static String getTimeToYMD(long seconds, String layout) {
        Date d = new Date(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat(layout);
        return sdf.format(d).toString();
    }

}
