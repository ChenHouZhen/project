package com.chenhz.common.utils.format;

public class DefaultFormat implements Format{
    @Override
    public String format(Object val) {
        if (null == val){
            return "";
        }
        return val.toString();
    }
}
