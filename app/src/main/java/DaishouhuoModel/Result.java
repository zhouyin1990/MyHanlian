package DaishouhuoModel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("CM_CREATETIME")
    @Expose
    private String CMCREATETIME;
    @SerializedName("CM_ORDERID")
    @Expose
    private String CMORDERID;
    @SerializedName("CM_NUMBERSUN")
    @Expose
    private Integer CMNUMBERSUN;
    @SerializedName("CM_MONEYSUN")
    @Expose
    private Integer CMMONEYSUN;
    @SerializedName("CM_ORDERDETAILSIDS")
    @Expose
    private String CMORDERDETAILSIDS;
    @SerializedName("CM_STATE")
    @Expose
    private Integer CMSTATE;
    @SerializedName("CM_USERID")
    @Expose
    private String CMUSERID;
    @SerializedName("TB_ORDERDETAILS")
    @Expose
    private List<TBORDERDETAIL> TBORDERDETAILS = new ArrayList<TBORDERDETAIL>();

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
     *     The CMORDERDETAILSIDS
     */
    public String getCMORDERDETAILSIDS() {
        return CMORDERDETAILSIDS;
    }

    /**
     * 
     * @param CMORDERDETAILSIDS
     *     The CM_ORDERDETAILSIDS
     */
    public void setCMORDERDETAILSIDS(String CMORDERDETAILSIDS) {
        this.CMORDERDETAILSIDS = CMORDERDETAILSIDS;
    }

    /**
     * 
     * @return
     *     The CMSTATE
     */
    public Integer getCMSTATE() {
        return CMSTATE;
    }

    /**
     * 
     * @param CMSTATE
     *     The CM_STATE
     */
    public void setCMSTATE(Integer CMSTATE) {
        this.CMSTATE = CMSTATE;
    }

    /**
     * 
     * @return
     *     The CMUSERID
     */
    public String getCMUSERID() {
        return CMUSERID;
    }

    /**
     * 
     * @param CMUSERID
     *     The CM_USERID
     */
    public void setCMUSERID(String CMUSERID) {
        this.CMUSERID = CMUSERID;
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
