package com.jx.xztongcheng.ui.activity.print;

import android.graphics.Bitmap;
import android.util.Log;

import com.jx.xztongcheng.bean.response.OrderSheetInfo;
import com.qr.print.PrintPP_CPCL;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PrintLabel {
    public static void Lable(PrintPP_CPCL iPrinter, Bitmap mBitmap,OrderSheetInfo coreOrderList) {
        Log.w("coreOrderList","coreOrderList:"+coreOrderList.toString());
//        if(true){
//            return;
//        }

        iPrinter.pageSetup(585, 1050);

        iPrinter.drawLine(2, 0, 75, 580, 75, false);//横线1
        iPrinter.drawLine(2, 0, 110, 580, 110, false);//横线2
        iPrinter.drawLine(2, 0, 250, 580, 250, false);//横线3
        iPrinter.drawLine(2, 0, 450, 580, 450, false);//横线4
        iPrinter.drawLine(2, 0, 600, 580, 600, false);//横线5
        iPrinter.drawLine(2, 0, 750, 580, 750, false);//横线6
        iPrinter.drawLine(2, 0, 800, 580, 800, false);//横线7
        iPrinter.drawLine(2, 0, 900, 580, 900, false);//横线8
        iPrinter.drawLine(2, 0, 75, 0, 900, false);//竖线1
        iPrinter.drawLine(2, 45, 250, 45, 750, false);//竖线2
        iPrinter.drawLine(2, 400, 600, 400, 750, false);//竖线3
        iPrinter.drawLine(2, 580, 75, 580, 900, false);//竖线4


        iPrinter.drawGraphic(10,10,100,40,mBitmap);

        iPrinter.drawText(10, 55, getTimeToYMD(System.currentTimeMillis(),"yyyy-MM-dd HH:mm:ss"),1,0,0,false,false);

        iPrinter.drawText(6, 80, "热线电话：400-400-400", 2, 0, 0, false, false);

        iPrinter.drawBarCode(20,130, coreOrderList.getOrderNo(), 1, 0, 2, 70);
        iPrinter.drawText(50, 210, coreOrderList.getOrderNo(), 2, 0, 0, false, false);

        iPrinter.drawText(390, 150,170,140, coreOrderList.getWebsiteName(), 3, 0, 1, false, false);

        //收件人
        iPrinter.drawText(2 + 4, 250 + 50, 43, 200, "收件人", 3, 0, 1, false, false);

        //收件人姓名＋电话，最终实施时请用变量替换
        iPrinter.drawText(2 + 4 + 43 + 8, 250 + 10 , 480, 40, coreOrderList.getConsigneeName() + " " + coreOrderList.getConsigneeMobile(), 3, 0, 1, false, false);

        //收件地址 ，最终实施时请用变量替换
        iPrinter.drawText(2 + 4 + 43 + 8, 250 + 10  + 40 +10, 480, 160, coreOrderList.getConsigneeAddress(), 3, 0, 1, false, false);

        //寄件人
        iPrinter.drawText(2 + 8, 250 + 30 +200, 43, 200, "寄件人", 2, 0, 0, false, false);

        //寄件人姓名＋电话，
        iPrinter.drawText(2 + 4 + 43 + 8, 250 + 20+200, 480, 40, coreOrderList.getMailingName() + " " + coreOrderList.getMailingMobile(), 2, 0, 0, false, false);
        //寄件人地址
        iPrinter.drawText(2 + 4 + 43 + 8, 250 + 20+200+40, 480, 160, coreOrderList.getMailingAddress(), 2, 0, 0, false, false);

        //内容品名
        iPrinter.drawText(2 + 8, 630, 43, 200, "内容品名", 2, 0, 0, false, false);

        iPrinter.drawText(2 + 8, 760, "现付：", 2, 0, 0, false, false);

        iPrinter.drawText(2 + 8+200, 760, "代付：", 2, 0, 0, false, false);
        iPrinter.drawText(2 + 8+380, 760, "到付：", 2, 0, 0, false, false);


        //内容
        iPrinter.drawText(2 + 4 + 43 + 8, 610, 400, 200, coreOrderList.getExpressName(), 2, 0, 0, false, false);
        iPrinter.drawText(2 + 4 + 43+400-30, 610, 300, 160, "签收人:", 2, 0, 0, false, false);
        iPrinter.drawText(2 + 4 + 43+400-30, 700, 400, 40, "日期：", 2, 0, 0, false, false);

        iPrinter.print(0, 0);

    }
    public static String getTimeToYMD(long seconds, String layout) {
        Date d = new Date(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat(layout);
        return sdf.format(d).toString();
    }

}
