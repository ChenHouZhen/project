package com.chenhz.http.enums;


public enum ErrorType {
    Client,
    Server,
    Throttling,
    Unknown;

    private ErrorType() {
    }
}
