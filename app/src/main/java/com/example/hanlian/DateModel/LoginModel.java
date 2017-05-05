package com.example.hanlian.DateModel;

/**
 * Created by Administrator on 2017/4/27.
 */

public class LoginModel {

    /**
     * ErrorCode : 0
     * ErrorMsg : null
     * Token : 9FEE28405425AADE657F21D8A8685B52FB7112369062D5D739DA9998EA3D887E367651EEF14096F2ED9C9DA84AB5692A
     * PageNum : 0
     * Result : {"CM_USERID":"20170483184524161","CM_BALANCE":0,"CM_PHONE":15949569024,"CM_LEVEL":0,"CM_NAME":"张文贵","CM_INTEGRAL":0,"CM_SHOPEADDRESS":"敬源食品华中大厦","CM_SHOPNAME":"黄磊","CM_CODE":2332}
     */

    private int ErrorCode;
    private Object ErrorMsg;
    private String Token;
    private int PageNum;
    private ResultBean Result;

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

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public int getPageNum() {
        return PageNum;
    }

    public void setPageNum(int PageNum) {
        this.PageNum = PageNum;
    }

    public ResultBean getResult() {
        return Result;
    }

    public void setResult(ResultBean Result) {
        this.Result = Result;
    }

    public static class ResultBean {
        /**
         * CM_USERID : 20170483184524161
         * CM_BALANCE : 0
         * CM_PHONE : 15949569024
         * CM_LEVEL : 0
         * CM_NAME : 张文贵
         * CM_INTEGRAL : 0
         * CM_SHOPEADDRESS : 敬源食品华中大厦
         * CM_SHOPNAME : 黄磊
         * CM_CODE : 2332
         */

        private String CM_USERID;
        private int CM_BALANCE;
        private long CM_PHONE;
        private int CM_LEVEL;
        private String CM_NAME;
        private int CM_INTEGRAL;
        private String CM_SHOPEADDRESS;
        private String CM_SHOPNAME;
        private int CM_CODE;

        public String getCM_USERID() {
            return CM_USERID;
        }

        public void setCM_USERID(String CM_USERID) {
            this.CM_USERID = CM_USERID;
        }

        public int getCM_BALANCE() {
            return CM_BALANCE;
        }

        public void setCM_BALANCE(int CM_BALANCE) {
            this.CM_BALANCE = CM_BALANCE;
        }

        public long getCM_PHONE() {
            return CM_PHONE;
        }

        public void setCM_PHONE(long CM_PHONE) {
            this.CM_PHONE = CM_PHONE;
        }

        public int getCM_LEVEL() {
            return CM_LEVEL;
        }

        public void setCM_LEVEL(int CM_LEVEL) {
            this.CM_LEVEL = CM_LEVEL;
        }

        public String getCM_NAME() {
            return CM_NAME;
        }

        public void setCM_NAME(String CM_NAME) {
            this.CM_NAME = CM_NAME;
        }

        public int getCM_INTEGRAL() {
            return CM_INTEGRAL;
        }

        public void setCM_INTEGRAL(int CM_INTEGRAL) {
            this.CM_INTEGRAL = CM_INTEGRAL;
        }

        public String getCM_SHOPEADDRESS() {
            return CM_SHOPEADDRESS;
        }

        public void setCM_SHOPEADDRESS(String CM_SHOPEADDRESS) {
            this.CM_SHOPEADDRESS = CM_SHOPEADDRESS;
        }

        public String getCM_SHOPNAME() {
            return CM_SHOPNAME;
        }

        public void setCM_SHOPNAME(String CM_SHOPNAME) {
            this.CM_SHOPNAME = CM_SHOPNAME;
        }

        public int getCM_CODE() {
            return CM_CODE;
        }

        public void setCM_CODE(int CM_CODE) {
            this.CM_CODE = CM_CODE;
        }
    }
}
