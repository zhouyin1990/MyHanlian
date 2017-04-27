package shouhoushenqingModel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("CM_ORDER_STATE")
    @Expose
    private Integer CMORDERSTATE;
    @SerializedName("CM_CREATETIME")
    @Expose
    private String CMCREATETIME;
    @SerializedName("CM_MONEYSUN")
    @Expose
    private Integer CMMONEYSUN;
    @SerializedName("CM_NUMBERSUN")
    @Expose
    private Integer CMNUMBERSUN;
    @SerializedName("CM_ORDERID")
    @Expose
    private String CMORDERID;
    @SerializedName("TB_ORDERDETAILS")
    @Expose
    private List<TBORDERDETAIL> TBORDERDETAILS = new ArrayList<TBORDERDETAIL>();

    /**
     * 
     * @return
     *     The CMORDERSTATE
     */
    public Integer getCMORDERSTATE() {
        return CMORDERSTATE;
    }

    /**
     * 
     * @param CMORDERSTATE
     *     The CM_ORDER_STATE
     */
    public void setCMORDERSTATE(Integer CMORDERSTATE) {
        this.CMORDERSTATE = CMORDERSTATE;
    }

    /**
     * 
     * @return
     *     The CMCREATETIME
     */
    public String getCMCREATETIME() {
        return CMCREATETIME;
    }

    /**
     * 
     * @param CMCREATETIME
     *     The CM_CREATETIME
     */
    public void setCMCREATETIME(String CMCREATETIME) {
        this.CMCREATETIME = CMCREATETIME;
    }

    /**
     * 
     * @return
     *     The CMMONEYSUN
     */
    public Integer getCMMONEYSUN() {
        return CMMONEYSUN;
    }

    /**
     * 
     * @param CMMONEYSUN
     *     The CM_MONEYSUN
     */
    public void setCMMONEYSUN(Integer CMMONEYSUN) {
        this.CMMONEYSUN = CMMONEYSUN;
    }

    /**
     * 
     * @return
     *     The CMNUMBERSUN
     */
    public Integer getCMNUMBERSUN() {
        return CMNUMBERSUN;
    }

    /**
     * 
     * @param CMNUMBERSUN
     *     The CM_NUMBERSUN
     */
    public void setCMNUMBERSUN(Integer CMNUMBERSUN) {
        this.CMNUMBERSUN = CMNUMBERSUN;
    }

    /**
     * 
     * @return
     *     The CMORDERID
     */
    public String getCMORDERID() {
        return CMORDERID;
    }

    /**
     * 
     * @param CMORDERID
     *     The CM_ORDERID
     */
    public void setCMORDERID(String CMORDERID) {
        this.CMORDERID = CMORDERID;
    }

    /**
     * 
     * @return
     *     The TBORDERDETAILS
     */
    public List<TBORDERDETAIL> getTBORDERDETAILS() {
        return TBORDERDETAILS;
    }

    /**
     * 
     * @param TBORDERDETAILS
     *     The TB_ORDERDETAILS
     */
    public void setTBORDERDETAILS(List<TBORDERDETAIL> TBORDERDETAILS) {
        this.TBORDERDETAILS = TBORDERDETAILS;
    }

}
