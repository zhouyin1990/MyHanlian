package com.example.hanlian.DateModel;

import java.util.List;

/**
 * 作者 null   on 2017/6/2.
 * 邮箱 zhouying19900502@163.com
 */

public class KeShouHouBean {


    /**
     * ErrorCode : 0
     * ErrorMsg : null
     * Token : 9FEE28405425AADE73C3A3727763EB207F8526534F696623521C55B4B853C967D4EB0D37473C9FB5B4699F4D05F42FC9
     * PageNum : 2
     * Result : [{"CM_ORDER_STATE":4,"CM_CREATETIME":"2017-05-23T14:28:08","CM_MONEYSUN":270,"CM_NUMBERSUN":10,"CM_ORDERID":"201705231428081406","TB_ORDERDETAILS":[{"CM_ORDERDETAILSID":"201705231428086755","CM_MONEY":270,"CM_NUMBER":10,"CM_SERVICE_STATE":0,"CM_GOODSID":"201703281207463226","CM_TITLE":"儿童春秋全棉印花卫衣杨幂同款","CM_MAINFIGUREPATH":"C://GoodsFiles/201703281207463226/0.jpeg","CM_PRESENTPRICE":27,"CM_SELLERID":"20170325155935961","CM_SELLERNAME":"小明爸爸的厂"}]},{"CM_ORDER_STATE":4,"CM_CREATETIME":"2017-05-23T14:27:54","CM_MONEYSUN":108,"CM_NUMBERSUN":4,"CM_ORDERID":"201705231427548400","TB_ORDERDETAILS":[{"CM_ORDERDETAILSID":"201705231427541470","CM_MONEY":108,"CM_NUMBER":4,"CM_SERVICE_STATE":0,"CM_GOODSID":"201703281156475044","CM_TITLE":"女童春季花朵印花全棉卫衣杨幂同款","CM_MAINFIGUREPATH":"C://GoodsFiles/201703281156475044/0.jpeg","CM_PRESENTPRICE":27,"CM_SELLERID":"20170325163547809","CM_SELLERNAME":null}]},{"CM_ORDER_STATE":4,"CM_CREATETIME":"2017-05-23T14:27:40","CM_MONEYSUN":800,"CM_NUMBERSUN":10,"CM_ORDERID":"201705231427404797","TB_ORDERDETAILS":[{"CM_ORDERDETAILSID":"201705231427409838","CM_MONEY":800,"CM_NUMBER":10,"CM_SERVICE_STATE":0,"CM_GOODSID":"201703281117258842","CM_TITLE":"儿童春秋两件套全棉长袖风衣外套","CM_MAINFIGUREPATH":"C://GoodsFiles/201703281117258842/0.jpeg","CM_PRESENTPRICE":80,"CM_SELLERID":"20170325155935961","CM_SELLERNAME":"小明爸爸的厂"}]}]
     */

    private int ErrorCode;
    private Object ErrorMsg;
    private String Token;
    private int PageNum;
    private List<ResultBean> Result;

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

    public List<ResultBean> getResult() {
        return Result;
    }

    public void setResult(List<ResultBean> Result) {
        this.Result = Result;
    }

    public static class ResultBean {
        /**
         * CM_ORDER_STATE : 4
         * CM_CREATETIME : 2017-05-23T14:28:08
         * CM_MONEYSUN : 270
         * CM_NUMBERSUN : 10
         * CM_ORDERID : 201705231428081406
         * TB_ORDERDETAILS : [{"CM_ORDERDETAILSID":"201705231428086755","CM_MONEY":270,"CM_NUMBER":10,"CM_SERVICE_STATE":0,"CM_GOODSID":"201703281207463226","CM_TITLE":"儿童春秋全棉印花卫衣杨幂同款","CM_MAINFIGUREPATH":"C://GoodsFiles/201703281207463226/0.jpeg","CM_PRESENTPRICE":27,"CM_SELLERID":"20170325155935961","CM_SELLERNAME":"小明爸爸的厂"}]
         */

        private int CM_ORDER_STATE;
        private String CM_CREATETIME;
        private int CM_MONEYSUN;
        private int CM_NUMBERSUN;
        private String CM_ORDERID;
        private List<TBORDERDETAILSBean> TB_ORDERDETAILS;

        public int getCM_ORDER_STATE() {
            return CM_ORDER_STATE;
        }

        public void setCM_ORDER_STATE(int CM_ORDER_STATE) {
            this.CM_ORDER_STATE = CM_ORDER_STATE;
        }

        public String getCM_CREATETIME() {
            return CM_CREATETIME;
        }

        public void setCM_CREATETIME(String CM_CREATETIME) {
            this.CM_CREATETIME = CM_CREATETIME;
        }

        public int getCM_MONEYSUN() {
            return CM_MONEYSUN;
        }

        public void setCM_MONEYSUN(int CM_MONEYSUN) {
            this.CM_MONEYSUN = CM_MONEYSUN;
        }

        public int getCM_NUMBERSUN() {
            return CM_NUMBERSUN;
        }

        public void setCM_NUMBERSUN(int CM_NUMBERSUN) {
            this.CM_NUMBERSUN = CM_NUMBERSUN;
        }

        public String getCM_ORDERID() {
            return CM_ORDERID;
        }

        public void setCM_ORDERID(String CM_ORDERID) {
            this.CM_ORDERID = CM_ORDERID;
        }

        public List<TBORDERDETAILSBean> getTB_ORDERDETAILS() {
            return TB_ORDERDETAILS;
        }

        public void setTB_ORDERDETAILS(List<TBORDERDETAILSBean> TB_ORDERDETAILS) {
            this.TB_ORDERDETAILS = TB_ORDERDETAILS;
        }

        public static class TBORDERDETAILSBean {
            /**
             * CM_ORDERDETAILSID : 201705231428086755
             * CM_MONEY : 270
             * CM_NUMBER : 10
             * CM_SERVICE_STATE : 0
             * CM_GOODSID : 201703281207463226
             * CM_TITLE : 儿童春秋全棉印花卫衣杨幂同款
             * CM_MAINFIGUREPATH : C://GoodsFiles/201703281207463226/0.jpeg
             * CM_PRESENTPRICE : 27
             * CM_SELLERID : 20170325155935961
             * CM_SELLERNAME : 小明爸爸的厂
             */

            private String CM_ORDERDETAILSID;
            private int CM_MONEY;
            private int CM_NUMBER;
            private int CM_SERVICE_STATE;
            private String CM_GOODSID;
            private String CM_TITLE;
            private String CM_MAINFIGUREPATH;
            private int CM_PRESENTPRICE;
            private String CM_SELLERID;
            private String CM_SELLERNAME;

            public String getCM_ORDERDETAILSID() {
                return CM_ORDERDETAILSID;
            }

            public void setCM_ORDERDETAILSID(String CM_ORDERDETAILSID) {
                this.CM_ORDERDETAILSID = CM_ORDERDETAILSID;
            }

            public int getCM_MONEY() {
                return CM_MONEY;
            }

            public void setCM_MONEY(int CM_MONEY) {
                this.CM_MONEY = CM_MONEY;
            }

            public int getCM_NUMBER() {
                return CM_NUMBER;
            }

            public void setCM_NUMBER(int CM_NUMBER) {
                this.CM_NUMBER = CM_NUMBER;
            }

            public int getCM_SERVICE_STATE() {
                return CM_SERVICE_STATE;
            }

            public void setCM_SERVICE_STATE(int CM_SERVICE_STATE) {
                this.CM_SERVICE_STATE = CM_SERVICE_STATE;
            }

            public String getCM_GOODSID() {
                return CM_GOODSID;
            }

            public void setCM_GOODSID(String CM_GOODSID) {
                this.CM_GOODSID = CM_GOODSID;
            }

            public String getCM_TITLE() {
                return CM_TITLE;
            }

            public void setCM_TITLE(String CM_TITLE) {
                this.CM_TITLE = CM_TITLE;
            }

            public String getCM_MAINFIGUREPATH() {
                return CM_MAINFIGUREPATH;
            }

            public void setCM_MAINFIGUREPATH(String CM_MAINFIGUREPATH) {
                this.CM_MAINFIGUREPATH = CM_MAINFIGUREPATH;
            }

            public int getCM_PRESENTPRICE() {
                return CM_PRESENTPRICE;
            }

            public void setCM_PRESENTPRICE(int CM_PRESENTPRICE) {
                this.CM_PRESENTPRICE = CM_PRESENTPRICE;
            }

            public String getCM_SELLERID() {
                return CM_SELLERID;
            }

            public void setCM_SELLERID(String CM_SELLERID) {
                this.CM_SELLERID = CM_SELLERID;
            }

            public String getCM_SELLERNAME() {
                return CM_SELLERNAME;
            }

            public void setCM_SELLERNAME(String CM_SELLERNAME) {
                this.CM_SELLERNAME = CM_SELLERNAME;
            }
        }
    }
}
