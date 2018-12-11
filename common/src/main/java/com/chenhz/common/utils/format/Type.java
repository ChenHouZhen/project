package com.chenhz.common.utils.format;

public enum Type {
    LOCALDATETIME("class java.time.LocalDateTime"),
    DATE("class java.util.Date");

    private String cla;

    private Type(String cla){ this.cla = cla;}

    public static Type getFormat(String cls){
        Type[] arr$ = values();
        int len = arr$.length;
        for (int i$ = 0 ; i$ <len ;i$++){
            Type value = arr$[i$];
            if (value.cla.equals(cls)){
                return value;
            }
        }
        return null;
    }
}
