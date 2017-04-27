package loginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("CM_USERID")
    @Expose
    private String CMUSERID;
    @SerializedName("CM_BALANCE")
    @Expose
    private Integer CMBALANCE;
    @SerializedName("CM_PHONE")
    @Expose
    private String CMPHONE;
    @SerializedName("CM_LEVEL")
    @Expose
    private Integer CMLEVEL;
    @SerializedName("CM_NICKNAME")
    @Expose
    private String CMNICKNAME;
    @SerializedName("CM_INTEGRAL")
    @Expose
    private Integer CMINTEGRAL;
    @SerializedName("CM_CODE")
    @Expose
    private String CMCODE;

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
     *     The CMBALANCE
     */
    public Integer getCMBALANCE() {
        return CMBALANCE;
    }

    /**
     * 
     * @param CMBALANCE
     *     The CM_BALANCE
     */
    public void setCMBALANCE(Integer CMBALANCE) {
        this.CMBALANCE = CMBALANCE;
    }

    /**
     * 
     * @return
     *     The CMPHONE
     */
    public String getCMPHONE() {
        return CMPHONE;
    }

    /**
     * 
     * @param CMPHONE
     *     The CM_PHONE
     */
    public void setCMPHONE(String CMPHONE) {
        this.CMPHONE = CMPHONE;
    }

    /**
     * 
     * @return
     *     The CMLEVEL
     */
    public Integer getCMLEVEL() {
        return CMLEVEL;
    }

    /**
     * 
     * @param CMLEVEL
     *     The CM_LEVEL
     */
    public void setCMLEVEL(Integer CMLEVEL) {
        this.CMLEVEL = CMLEVEL;
    }

    /**
     * 
     * @return
     *     The CMNICKNAME
     */
    public String getCMNICKNAME() {
        return CMNICKNAME;
    }

    /**
     * 
     * @param CMNICKNAME
     *     The CM_NICKNAME
     */
    public void setCMNICKNAME(String CMNICKNAME) {
        this.CMNICKNAME = CMNICKNAME;
    }

    /**
     * 
     * @return
     *     The CMINTEGRAL
     */
    public Integer getCMINTEGRAL() {
        return CMINTEGRAL;
    }

    /**
     * 
     * @param CMINTEGRAL
     *     The CM_INTEGRAL
     */
    public void setCMINTEGRAL(Integer CMINTEGRAL) {
        this.CMINTEGRAL = CMINTEGRAL;
    }

    /**
     * 
     * @return
     *     The CMCODE
     */
    public String getCMCODE() {
        return CMCODE;
    }
  
    /**
     * 
     * @param CMCODE
     *     The CM_CODE
     */
    public void setCMCODE(String CMCODE) {
        this.CMCODE = CMCODE;
    }

}
