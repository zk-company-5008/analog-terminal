package cn.piesat.analogterminal.util;

import jdk.nashorn.internal.codegen.DumpBytecode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zk
 * @date 2019/2/15 15:25
 */
public class CalculationTimeUtil {
    /**
     * 计算周计数
     * @return
     */
    public static long countOfWeek() {
        String baseStr = "2006-01-01 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date baseDate = null;
        try {
            baseDate = sdf.parse(baseStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date now = new Date();
        //周计数 = （当前时间毫秒数 - 基准时间毫秒数）/（1000*86400*7）取整数部分
        long countOfWeek = (now.getTime() - baseDate.getTime())/(1000*86400*7);
        return countOfWeek;
    }

    /**
     * 计算周内秒
     * @return
     */
    public static long secondOfWeek() {
        //基准时间
        String baseStr = "2006-01-01 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date baseDate = null;
        try {
            baseDate = sdf.parse(baseStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //当前时间
        Date now = new Date();
        //（当前时间毫秒数 - 基准时间毫秒数）/（1000*86400*7）取小数部分
        double realNum = ((double) now.getTime() - (double) baseDate.getTime()) / (1000*86400*7);
        double integerNum = (now.getTime() - baseDate.getTime())/(1000*86400*7);
        double smallNum = realNum - integerNum;
        //周内秒 = 小数部分 * 86400 * 7
        long secondOfWeek = (long) (smallNum * 86400 * 7);
        return secondOfWeek;
    }
    public static void main(String[] args) {
        System.out.println(secondOfWeek());
    }
}
