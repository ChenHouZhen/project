package com.chenhz.http.enums;


import java.util.Arrays;

public enum FormatType {
    XML(new String[]{"application/xml", "text/xml"}),
    JSON(new String[]{"application/Json", "text/Json"}),
    RAW(new String[]{"application/octet-stream"}),
    FORM(new String[]{"application/x-www-form-urlencoded"});

    private String[] formats;

    private FormatType(String[] formats) {
        this.formats = formats;
    }

    public static String mapFormatToAccept(FormatType format) {
        return format.formats[0];
    }

    public static FormatType mapAcceptToFormat(String accept) {
        FormatType[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            FormatType value = arr$[i$];
            if (Arrays.asList(value.formats).contains(accept)) {
                return value;
            }
        }

        return RAW;
    }
}
