package com.chenhz.http.utils;


import com.chenhz.http.enums.FormatType;

public class ReaderFactory {
    public ReaderFactory() {
    }

    public static Reader createInstance(FormatType format) {
        if (FormatType.JSON == format) {
            return new JsonReader();
        } else {
            return FormatType.XML == format ? new XmlReader() : null;
        }
    }
}
