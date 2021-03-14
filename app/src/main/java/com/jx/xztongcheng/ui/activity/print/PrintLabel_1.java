package com.jx.xztongcheng.ui.activity.print;

import com.qr.print.PrintPP_CPCL;

public class PrintLabel_1 {
    public static int a = 0;
    public static int b = 128;
    public static int c = 100;
    public static void Lable(PrintPP_CPCL iPrinter) {

        iPrinter.pageSetup(586, 1436 - 8-b-c);
        //第一联

        iPrinter.drawLine(2, 0, 240-b, 568 + 16, 240-b, false);//第一联横线1

        iPrinter.drawLine(2, 0, 384-b, 568 + 16, 384-b, false);//第一联横线2

        iPrinter.drawLine(2, 0, 552-b, 568 - 32 + 8, 552-b, false);//第一联横线3

        iPrinter.drawLine(2, 40, 384-b, 40, 680-b, false);//第一联竖线1，从左到右

        iPrinter.drawLine(2, 408, 552-b, 408, 680-b, false);//第一联竖线2，从左到右

        iPrinter.drawLine(2, 568 - 32 + 8, 384-b, 568 - 32 + 8, 680-b, false);//第一联竖线3，从左到右
        //三段码
        iPrinter.drawText(2 + 20, a + 16 + 8, "210-123-000", 6, 0, 0, false, false);

//        text_x: - 起始横坐标
//        text_y: - 起始纵坐标
//        text: - 打印的文本内容
//        fontSize: - 字体大小 1：16点阵； 2：24点阵； 3：32点阵； 4：24点阵放大一倍； 5：32点阵放大一倍 6：24点阵放大两倍； 7：32点阵放大两倍； 8：20点阵； 9：28点阵放大一倍； 其他：16点阵
//        rotate: - 旋转角度 0：不旋转； 1：90度； 2：180°； 3:270°
//        bold: - 是否粗体 0：否； 1：是
//        reverse: - 是否反白 false：不反白；true：反白
//        underline: - 是否有下划线 false：没有；true：有

//        start_x: - 一维码起始横坐标
//        start_y: - 一维码起始纵坐标
//        text: - 条码内容
//        type: - 条码类型 0：CODE39； 1：CODE128； 2：CODE93； 3：CODEBAR； 4：EAN8； 5：EAN13； 6：UPCA; 7：UPC-E; 8：ITF;
//        rotate:旋转 - 0或2:不旋转 1或3:旋转90度
//        linewidth: - 条码线宽度
//        height: - 条码高度

        iPrinter.drawBarCode(2 + 20,a + 16 + 8+110, "Eade12345678", 1, 0, 2, 100);
        //收件人
        iPrinter.drawText(2 + 4, 384 + 28-b, 32, 120, "收件人", 3, 0, 1, false, false);
        //收件人姓名＋电话，最终实施时请用变量替换
        iPrinter.drawText(2 + 4 + 32 + 8, 264 + a, 480, 32, "张三" + " " + "18212345678", 3, 0, 1, false, false);
        //收件地址 ，最终实施时请用变量替换
        iPrinter.drawText(2 + 4 + 32 + 8, 372 + 40 + 22-b, 448, 120, "湖南省" + "湘潭市" + "雨湖区" + " " + "雨湖区政府1234号", 3, 0, 1, false, false);
        //寄件人
        iPrinter.drawText(2 + 8, 552 + 22-b, 32, 96, "寄件人", 2, 0, 0, false, false);
        //寄件人姓名＋电话，
        iPrinter.drawText(2 + 4 + 32 + 8, 552 + 8-b, 480, 24, "李四" + " " + "15188889999", 2, 0, 0, false, false);
        //寄件人地址
        iPrinter.drawText(2 + 4 + 32 + 8, 552 + 40-b, 344, 112, "上海" + "上海市" + "青浦区" + " " + "华徐公路3029弄28号", 2, 0, 0, false, false);
//		//签收人
        iPrinter.drawText(2 + 424 - 8, 552 + 8-b, "签收人：", 2, 0, 0, false, false);
//		//日期
        iPrinter.drawText(2 + 424, 680 - 26 - 8-b, "日期：", 2, 0, 0, false, false);
//		//派件联
        iPrinter.drawText(568 - 32 + 3 + 8, 384 + a, 32, 96, "派件联", 2, 0, 0, false, false);

        iPrinter.drawLine(2, 0, 696 + 32-b, 568 + 16, 696 + 32-b, false);//第二联横线1，从左到右

        iPrinter.drawLine(2, 0, 696 + 160-b, 568 - 32 + 16, 696 + 160-b, false);//第二联横线2，从左到右

        iPrinter.drawLine(2, 40, 696 + 160 + 96-b, 568 - 32 + 8, 696 + 160 + 96-b, false);//第二联横线3，从左到右

        iPrinter.drawLine(2, 40, 696 + 32-b, 40, 696 + 288-b, false);//第二联竖线1，从左到右

        iPrinter.drawLine(2, 248 + 42, 696 + 160 + 96-b, 248 + 42, 680 + 16 + 288-b, false);//第二联竖线2，从左到右

        iPrinter.drawLine(2, 568 - 32 + 8 - 96, 696 + 160-b, 568 - 32 + 8 - 96, 696 + 160 + 96-b, false);//第二联竖线3，从左到右

        iPrinter.drawLine(2, 568 - 32 + 8, 696 + 32-b, 568 - 32 + 8, 680 + 16 + 288-b, false);//第二联竖线4，从左到右

        //运单号+运单号
        iPrinter.drawText(8, 696 + 3-b, "运单号：" + "1234567890" + " □" + "订单号：" + "LP123456789", 2, 0, 0, false, false);
        //收件人
        iPrinter.drawText(2 + 4, 696 + 32 + 16-b, 32, 96, "收件人", 2, 0, 0, false, false);
        //收件人姓名＋电话，最终实施时请用变量替换
        iPrinter.drawText(2 + 8 + 32 + 8, 608 + a, 480, 24, "张三" + " " + "18212345678", 2, 0, 0, false, false);
        //收件地址 ，最终实施时请用变量替换
        iPrinter.drawText(2 + 8 + 32 + 8, 696 + 32 + 40 + 2-b, 424, 80, "湖南省" + "湘潭市" + "雨湖区" + " " + "雨湖区政府1234号", 2, 0, 0, false, false);
//		//内容品名
        iPrinter.drawText(2 + 4, 696 + 160 + 3-b, 32, 120, "内容品名", 2, 0, 0, false, false);
        //内容品名具体
        iPrinter.drawText(2 + 4 + 32 + 8 + 4 + 4, 696 + 160 + 8-b, 432 - 100, 136, "鞋子、衣服、鞋子、衣服、鞋子、衣服、鞋子、衣服、鞋子、衣服、鞋子、衣服、", 2, 0, 0, false, false);
        //面单模式 A ：A网
        iPrinter.drawText(568 - 32 + 8 - 96 + 24, 696 + 160 + 2-b, "A", 7, 0, 0, false, false);
        //数量
        iPrinter.drawText(2 + 4 + 32 + 8, 696 + 160 + 96 + 4-b, "数量：" + "1", 2, 0, 0, false, false);
        //重量
        iPrinter.drawText(248 + 42 + 4, 696 + 160 + 96 + 4-b, "重量：" + "1" + "kg", 2, 0, 0, false, false);
//		//收件联
        iPrinter.drawText(568 - 32 + 3 + 8, 696 + 32 + 80-b, 32, 96, "收件联", 2, 0, 0, false, false);
        //虚线

        iPrinter.drawLine(2, 0, 1096-b-c, 568 + 16, 1096-b-c, false);//第三联横线1，从左到右

        iPrinter.drawLine(2, 0, 1096 + 104 - 8 + 4-b-c, 568 - 32 + 8, 1096 + 104 - 8 + 4-b-c, false);//第三联横线2，从左到右

        iPrinter.drawLine(2, 0, 1096 + 104 + 104 - 8-b-c, 568 - 32 + 8, 1096 + 104 + 104 - 8-b-c, false);//第三联横线3，从左到右

        iPrinter.drawLine(2, 40, 1096 + 104 + 104 + 96 + 4 - 4 - 2 - 8 - 4-b-c, 568 - 32 + 8, 1096 + 104 + 104 + 96 + 4 - 4 - 2 - 8 - 4-b-c, false);//第三联横线4，从左到右


        iPrinter.drawLine(2, 40, 1096-b-c, 40, 1432 - 4 - 16 + 4-b-c, false);//第三联竖线1，从左到右

        iPrinter.drawLine(2, 248 + 42, 1096 + 104 + 104 + 96 - 8 + 4-b-c, 248 + 42, 1432 - 4 - 16 + 4-b-c, false);//第三联竖线2，从左到右

        iPrinter.drawLine(2, 568 - 32 + 8, 1096-b-c, 568 - 32 + 8, 1432 - 4 - 16 + 4-b-c, false);//第三联竖线3，从左到右

        //收件人
        iPrinter.drawText(2 + 4, 1096 + 5-b-c, 32, 96, "收件人", 2, 0, 0, false, false);
        //收件人姓名＋电话，最终实施时请用变量替换
        iPrinter.drawText(2 + 8 + 32 + 8 + 4 + 4, 1096 + 8-b-c, 480, 24, "张三" + " " + "18212345678", 2, 0, 0, false, false);
        //收件地址 ，最终实施时请用变量替换
        iPrinter.drawText(2 + 8 + 32 + 8 + 4 + 4, 1096 + 8 + 24 + 8-b-c, 456, 64, "湖南省" + "湘潭市" + "雨湖区" + " " + "雨湖区政府1234号", 2, 0, 0, false, false);
        //寄件人
        iPrinter.drawText(2 + 4, 1096 + 104 + 5-b-c, 32, 96, "寄件人", 2, 0, 0, false, false);
        //寄件人姓名＋电话，
        iPrinter.drawText(2 + 4 + 32 + 8, 1096 + 104 + 8-b-c, 480, 24, "李四" + " " + "15188889999", 2, 0, 0, false, false);
        //寄件人地址
        iPrinter.drawText(2 + 4 + 32 + 8, 1096 + 104 + 8 + 24 + 8-b-c, 456, 72, "上海" + "上海市" + "青浦区" + " " + "华徐公路3029弄28号", 2, 0, 0, false, false);
        //内容品名
        iPrinter.drawText(2 + 4, 1096 + 104 + 104 + 1-b-c, 32, 120, "内容品名", 2, 0, 0, false, false);
        //内容品名具体
        iPrinter.drawText(2 + 4 + 32 + 8 + 4 + 4, 1096 + 104 + 104 + 8-b-c, 432, 156, "衣服", 2, 0, 0, false, false);
        //数量
        iPrinter.drawText(2 + 4 + 32 + 8, 1432 - 32 + 4 - 4 - 8 - 4-b-c, "数量：" + "1", 2, 0, 0, false, false);
        //重量
        iPrinter.drawText(248 + 42 + 4, 1432 - 32 + 4 - 4 - 8 - 4-b-c, "重量：" + "1" + "kg", 2, 0, 0, false, false);
        //寄件联
        iPrinter.drawText(568 - 32 + 3 + 8, 1096 + 104 + 16-b-c, 32, 96, "寄件联", 2, 0, 0, false, false);

        iPrinter.print(0, 0);

    }
}
