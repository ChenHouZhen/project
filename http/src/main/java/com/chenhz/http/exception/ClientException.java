package com.chenhz.http.exception;


import com.chenhz.http.enums.ErrorType;

public class ClientException extends Exception {
    private static final long serialVersionUID = 534996425110290578L;
    private String requestId;
    private String errCode;
    private String errMsg;
    private ErrorType errorType;

    public ClientException(String errCode, String errMsg, String requestId) {
        this(errCode, errMsg);
        this.requestId = requestId;
    }

    public ClientException(String errCode, String errMsg) {
        super(errCode + " : " + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.setErrorType(ErrorType.Client);
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getMessage() {
        return super.getMessage() + (null == this.getRequestId() ? "" : "\r\nRequestId : " + this.getRequestId());
    }
}
