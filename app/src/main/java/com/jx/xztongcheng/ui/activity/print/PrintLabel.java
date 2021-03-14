package com.jx.xztongcheng.ui.activity.print;

import android.graphics.Bitmap;

import com.jx.xztongcheng.bean.response.OrderSheetInfo;
import com.qr.print.PrintPP_CPCL;

public class PrintLabel {
    public static void Lable(PrintPP_CPCL iPrinter, OrderSheetInfo coreOrderList, Bitmap bitmap) {

        iPrinter.pageSetup(585, 1050);

        iPrinter.drawText(150, 40, "享指同城", 6, 0, 0, false, false);

        iPrinter.drawBarCode(20,140, coreOrderList.getOrderNo(), 1, 0, 2, 80);
//     参数:
//text_x: - 起始横坐标
//text_y: - 起始纵坐标
//width: - 文本框宽度
//height: - 文本框高度
//text: - 打印的文本内容
//fontSize: - 字体大小 1：16点阵； 2：24点阵； 3：32点阵； 4：24点阵放大一倍； 5：32点阵放大一倍 6：24点阵放大两倍； 7：32点阵放大两倍； 8：20点阵； 9：28点阵放大一倍； 其他：16点阵
//rotate: - 旋转角度 0：不旋转； 1：90度； 2：180°； 3:270°
//bold: - 是否粗体 0：否； 1：是
//reverse: - 是否反白 false：不反白；true：反白
//underline: - 是否有下划线 false：没有；true：有

        iPrinter.drawText(390, 150,170,140, coreOrderList.getWebsiteName(), 3, 0, 1, false, false);

        //lineWidth: - 线条宽度
        //start_x: - 矩形框左上角x坐标
        //start_y: - 矩形框左上角y坐标
        //end_x: - 矩形框右下角x坐标
        //end_y: - 矩形框右下角y坐标
        //fullline: - 实线:true 虚线:false

        //第一联
        iPrinter.drawLine(2, 0, 250, 580, 250, false);//第一联横线1
        iPrinter.drawLine(2, 0, 450, 580, 450, false);//第一联横线2
        iPrinter.drawLine(2, 0, 650, 580, 650, false);//第一联横线3
        iPrinter.drawLine(2, 0, 850, 580, 850, false);//第一联横线4
        iPrinter.drawLine(2, 0, 250, 0, 850, false);//第一联竖线3，从左到右
        iPrinter.drawLine(2, 580, 250, 580, 850, false);//第一联竖线3，从左到右

        iPrinter.drawLine(2, 45, 250, 45, 850, false);//第一联竖线3，从左到右

        iPrinter.drawLine(2, 400, 650, 400, 850, false);//第一联竖线3，从左到右

        iPrinter.drawLine(2, 580, 250, 580, 850, false);//第一联竖线3，从左到右

//text_x: - 起始横坐标
//text_y: - 起始纵坐标
//width: - 文本框宽度
//height: - 文本框高度
//text: - 打印的文本内容
//fontSize: - 字体大小 1：16点阵； 2：24点阵； 3：32点阵； 4：24点阵放大一倍； 5：32点阵放大一倍 6：24点阵放大两倍； 7：32点阵放大两倍； 8：20点阵； 9：28点阵放大一倍； 其他：16点阵
//rotate: - 旋转角度 0：不旋转； 1：90度； 2：180°； 3:270°
//bold: - 是否粗体 0：否； 1：是
//reverse: - 是否反白 false：不反白；true：反白
//underline: - 是否有下划线 false：没有；true：有

        //收件人
        iPrinter.drawText(2 + 4, 250 + 30, 43, 200, "收件人", 3, 0, 1, false, false);

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
        iPrinter.drawText(2 + 8, 250 + 30 +400, 43, 200, "内容品名", 2, 0, 0, false, false);

        //内容
        iPrinter.drawText(2 + 4 + 43 + 8, 250 + 20 + 400, 400, 200, coreOrderList.getExpressName(), 2, 0, 0, false, false);

        //内容
        iPrinter.drawText(2 + 4 + 43+400-30, 250 + 20 + 400, 400, 160, "签收人:", 2, 0, 0, false, false);

        iPrinter.drawText(2 + 4 + 43+400-30, 250 + 20 + 400+140, 400, 40, "日期：", 2, 0, 0, false, false);



////	//签收人
//        iPrinter.drawText(2 + 424 - 8, 552 + 8, "签收人：", 2, 0, 0, false, false);
////		//日期
//        iPrinter.drawText(2 + 424, 680 - 26 - 8, "日期：", 2, 0, 0, false, false);
////		//派件联
//        iPrinter.drawText(568 - 32 + 3 + 8, 384 , 32, 96, "派件联", 2, 0, 0, false, false);


//        iPrinter.drawLine(2, 0, 696 + 32, 568 + 16, 696 + 32, false);//第二联横线1，从左到右
//
//        iPrinter.drawLine(2, 0, 696 + 160, 568 - 32 + 16, 696 + 160, false);//第二联横线2，从左到右
//
//        iPrinter.drawLine(2, 40, 696 + 160 + 96, 568 - 32 + 8, 696 + 160 + 96, false);//第二联横线3，从左到右
//
//        iPrinter.drawLine(2, 40, 696 + 32, 40, 696 + 288, false);//第二联竖线1，从左到右
//
//        iPrinter.drawLine(2, 248 + 42, 696 + 160 + 96, 248 + 42, 680 + 16 + 288, false);//第二联竖线2，从左到右
//
//        iPrinter.drawLine(2, 568 - 32 + 8 - 96, 696 + 160, 568 - 32 + 8 - 96, 696 + 160 + 96, false);//第二联竖线3，从左到右
//
//        iPrinter.drawLine(2, 568 - 32 + 8, 696 + 32, 568 - 32 + 8, 680 + 16 + 288, false);//第二联竖线4，从左到右

//		//内容品名
//        iPrinter.drawText(2 + 4, 696 + 160 + 3, 32, 120, "内容品名", 2, 0, 0, false, false);
//        //内容品名具体
//        iPrinter.drawText(2 + 4 + 32 + 8 + 4 + 4, 696 + 160 + 8, 432 - 100, 136, "鞋子、衣服、鞋子、衣服、鞋子、衣服、鞋子、衣服、鞋子、衣服、鞋子、衣服、", 2, 0, 0, false, false);
//        //面单模式 A ：A网
//        iPrinter.drawText(568 - 32 + 8 - 96 + 24, 696 + 160 + 2, "A", 7, 0, 0, false, false);
//        //数量
//        iPrinter.drawText(2 + 4 + 32 + 8, 696 + 160 + 96 + 4, "数量：" + "1", 2, 0, 0, false, false);
//        //重量
//        iPrinter.drawText(248 + 42 + 4, 696 + 160 + 96 + 4, "重量：" + "1" + "kg", 2, 0, 0, false, false);
////		//收件联
//        iPrinter.drawText(568 - 32 + 3 + 8, 696 + 32 + 80, 32, 96, "收件联", 2, 0, 0, false, false);
//
        iPrinter.print(0, 0);

    }
}
