package com.xgd.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    //日期转字符串
    public static String date2String(Date data,String patt){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(patt);
        String format = simpleDateFormat.format(data);
        return format;
    }

    //字符串转日期

    /**
     *
     * @param str   需要转换的
     * @param patt  格 式
     * @return
     */
    public static Date String2Date(String str,String patt ){
         SimpleDateFormat simpleDateFormat=new SimpleDateFormat(patt);
        Date da = null;
        try {
            da = simpleDateFormat.parse(str);
            return da;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
