package com.chenhz.http.utils;


import com.chenhz.http.exception.ClientException;

import java.util.Map;

public interface Reader {
    Map<String, String> read(String var1, String var2) throws ClientException;

    Map<String, String> readForHideArrayItem(String var1, String var2) throws ClientException;
}
