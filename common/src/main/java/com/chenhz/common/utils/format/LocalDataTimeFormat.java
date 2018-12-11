package com.chenhz.common.utils.format;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDataTimeFormat implements Format {
    @Override
    public String format(Object val) {
        if (null ==val){
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format((LocalDateTime)val);
    }
}
