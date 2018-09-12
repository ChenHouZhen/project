package com.chenhz.http.entity;


import com.chenhz.http.exception.ClientException;
import com.chenhz.http.exception.ServerException;
import com.chenhz.http.utils.UnmarshallerContext;

public abstract class AcsResponse {
    public AcsResponse() {
    }

    public abstract AcsResponse getInstance(UnmarshallerContext var1) throws ClientException, ServerException;

    public boolean checkShowJsonItemName() {
        return true;
    }
}
