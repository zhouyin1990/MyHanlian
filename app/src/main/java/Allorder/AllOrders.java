package Allorder;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class AllOrders {

    @Expose
    private Integer ErrorCode;
    @Expose
    private Object ErrorMsg;
    @Expose
    private Object Token;
    @Expose
    private Integer PageNum;
    @Expose
    private List<Result> Result = new ArrayList<Result>();

    /**
     * 
     * @return
     *     The ErrorCode
     */
    public Integer getErrorCode() {
        return ErrorCode;
    }

    /**
     * 
     * @param ErrorCode
     *     The ErrorCode
     */
    public void setErrorCode(Integer ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    /**
     * 
     * @return
     *     The ErrorMsg
     */
    public Object getErrorMsg() {
        return ErrorMsg;
    }

    /**
     * 
     * @param ErrorMsg
     *     The ErrorMsg
     */
    public void setErrorMsg(Object ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    /**
     * 
     * @return
     *     The Token
     */
    public Object getToken() {
        return Token;
    }

    /**
     * 
     * @param Token
     *     The Token
     */
    public void setToken(Object Token) {
        this.Token = Token;
    }

    /**
     * 
     * @return
     *     The PageNum
     */
    public Integer getPageNum() {
        return PageNum;
    }

    /**
     * 
     * @param PageNum
     *     The PageNum
     */
    public void setPageNum(Integer PageNum) {
        this.PageNum = PageNum;
    }

    /**
     * 
     * @return
     *     The Result
     */
    public List<Result> getResult() {
        return Result;
    }

    /**
     * 
     * @param Result
     *     The Result
     */
    public void setResult(List<Result> Result) {
        this.Result = Result;
    }

}
