package com.chenhz.common.utils.format;

import java.text.SimpleDateFormat;

public class DateFormat implements Format{
    @Override
    public String format(Object val) {
        if (null ==val){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(val);
    }
}
