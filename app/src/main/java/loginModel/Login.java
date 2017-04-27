package loginModel;

import com.google.gson.annotations.Expose;

public class Login {

    @Expose
    private Integer ErrorCode;
    @Expose
    private Object ErrorMsg;
    @Expose
    private String Token;
    @Expose
    private Integer PageNum;
    @Expose
    private Result Result;

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
    public String getToken() {
        return Token;
    }

    /**
     * 
     * @param Token
     *     The Token
     */
    public void setToken(String Token) {
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
    public Result getResult() {
        return Result;
    }

    /**
     * 
     * @param Result
     *     The Result
     */
    public void setResult(Result Result) {
        this.Result = Result;
    }

}
