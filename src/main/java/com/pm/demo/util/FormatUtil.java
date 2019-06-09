package com.pm.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

//格式转化类
public class FormatUtil {
    //字符串转时间
    public Date dateFormat(String vtime){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try {
            date=simpleDateFormat.parse(vtime);
        } catch(Exception px) {
            px.printStackTrace();
        }
        return date;
    }
}
