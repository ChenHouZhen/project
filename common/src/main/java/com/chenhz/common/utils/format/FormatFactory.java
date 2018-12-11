package com.chenhz.common.utils.format;

public class FormatFactory {

    public static Format creatInstance(Type type){
        Format f ;
        if (Type.LOCALDATETIME == type){
            f = new LocalDataTimeFormat();
        }else if (Type.DATE == type){
            f = new DateFormat();
        }else {
            f = new DefaultFormat();
        }
        return f;
    }
}
