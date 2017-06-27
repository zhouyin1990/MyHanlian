package com.example.hanlian.DateModel;

/**
 * Created by Administrator on 2017/5/20.
 */

public class WuliuBean {


    /**
     * ErrorCode : 0
     * ErrorMsg : null
     * Token : null
     * PageNum : 0
     * Result : {"showapi_res_code":0,"showapi_res_error":"","showapi_res_body":{"mailNo":"435997057797","update":1495272661327,"updateStr":"2017-05-20 17:31:01","ret_code":0,"flag":true,"dataSize":9,"status":4,"tel":"021-39777777","expSpellName":"zhongtong","data":[{"time":"2017-04-28 13:59:51","context":"[椒江康平路分部] [台州市] [椒江康平路分部]的派件已签收 感谢使用中通快递,期待再次为您服务!"},{"time":"2017-04-28 13:54:26","context":"[椒江康平路分部] [台州市] 快件已到达[椒江康平路分部],业务员汪凯正在第1次派件 电话:15857615435 请保持电话畅通、耐心等待"},{"time":"2017-04-28 11:53:12","context":"[椒江] [台州市] 快件离开 [椒江]已发往[椒江康平路分部]"},{"time":"2017-04-28 11:51:10","context":"[椒江] [台州市] 快件到达 [椒江]"},{"time":"2017-04-28 07:40:01","context":"[台州中转部] [台州市] 快件离开 [台州中转部]已发往[椒江]"},{"time":"2017-04-27 23:17:10","context":"[杭州中转部] [嘉兴市] 快件离开 [杭州中转部]已发往[台州中转部]"},{"time":"2017-04-27 23:15:43","context":"[杭州中转部] [嘉兴市] 快件到达 [杭州中转部]"},{"time":"2017-04-27 20:28:08","context":"[杭州城南区] [杭州市] 快件离开 [杭州城南区]已发往[台州中转部]"},{"time":"2017-04-27 20:23:48","context":"[杭州城南区] [杭州市] [杭州城南区]的方海文已收件 电话:15381009209"}],"expTextName":"中通快递"}}
     */

    private int ErrorCode;
    private Object ErrorMsg;
    private Object Token;
    private int PageNum;
    private String Result;

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

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }
}
