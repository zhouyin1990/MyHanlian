package com.example.hanlian.DateModel;

/**
 * Created by Administrator on 2017/5/10.
 */

public class GetcodeBean {


    /**
     * ErrorCode : 0
     * ErrorMsg : null
     * Token : null
     * PageNum : 0
     * Result : null
     */

    private int ErrorCode;
    private Object ErrorMsg;
    private Object Token;
    private int PageNum;
    private Object Result;

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public Object getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(Object ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    public Object getToken() {
        return Token;
    }

    public void setToken(Object Token) {
        this.Token = Token;
    }

    public int getPageNum() {
        return PageNum;
    }

    public void setPageNum(int PageNum) {
        this.PageNum = PageNum;
    }

    public Object getResult() {
        return Result;
    }

    public void setResult(Object Result) {
        this.Result = Result;
    }
}
