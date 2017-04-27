package myinfoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @Expose
    private String $id;
    @SerializedName("CM_USERID")
    @Expose
    private String CMUSERID;
    @SerializedName("CM_NICKNAME")
    @Expose
    private String CMNICKNAME;
    @SerializedName("CM_PHONE")
    @Expose
    private String CMPHONE;
    @SerializedName("CM_ACCOUNT")
    @Expose
    private String CMACCOUNT;
    @SerializedName("CM_PASSWORD")
    @Expose
    private String CMPASSWORD;
    @SerializedName("CM_INTEGRAL")
    @Expose
    private Integer CMINTEGRAL;
    @SerializedName("CM_BALANCE")
    @Expose
    private Integer CMBALANCE;
    @SerializedName("CM_CARDFACEPATH")
    @Expose
    private String CMCARDFACEPATH;
    @SerializedName("CM_CARDBACKPATH")
    @Expose
    private String CMCARDBACKPATH;
    @SerializedName("CM_PERSONALPATH")
    @Expose
    private String CMPERSONALPATH;
    @SerializedName("CM_SHOPEADDRESS")
    @Expose
    private String CMSHOPEADDRESS;
    @SerializedName("CM_SHOPLON")
    @Expose
    private Double CMSHOPLON;
    @SerializedName("CM_SHOPLAT")
    @Expose
    private Double CMSHOPLAT;
    @SerializedName("CM_ISEXAMINE")
    @Expose
    private Integer CMISEXAMINE;
    @SerializedName("CM_LEVEL")
    @Expose
    private Integer CMLEVEL;
    @Expose
    private EntityKey EntityKey;

    /**
     * 
     * @return
     *     The $id
     */
    public String get$id() {
        return $id;
    }

    /**
     * 
     * @param $id
     *     The $id
     */
    public void set$id(String $id) {
        this.$id = $id;
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
     *     The CMACCOUNT
     */
    public String getCMACCOUNT() {
        return CMACCOUNT;
    }

    /**
     * 
     * @param CMACCOUNT
     *     The CM_ACCOUNT
     */
    public void setCMACCOUNT(String CMACCOUNT) {
        this.CMACCOUNT = CMACCOUNT;
    }

    /**
     * 
     * @return
     *     The CMPASSWORD
     */
    public String getCMPASSWORD() {
        return CMPASSWORD;
    }

    /**
     * 
     * @param CMPASSWORD
     *     The CM_PASSWORD
     */
    public void setCMPASSWORD(String CMPASSWORD) {
        this.CMPASSWORD = CMPASSWORD;
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
     *     The CMCARDFACEPATH
     */
    public String getCMCARDFACEPATH() {
        return CMCARDFACEPATH;
    }

    /**
     * 
     * @param CMCARDFACEPATH
     *     The CM_CARDFACEPATH
     */
    public void setCMCARDFACEPATH(String CMCARDFACEPATH) {
        this.CMCARDFACEPATH = CMCARDFACEPATH;
    }

    /**
     * 
     * @return
     *     The CMCARDBACKPATH
     */
    public String getCMCARDBACKPATH() {
        return CMCARDBACKPATH;
    }

    /**
     * 
     * @param CMCARDBACKPATH
     *     The CM_CARDBACKPATH
     */
    public void setCMCARDBACKPATH(String CMCARDBACKPATH) {
        this.CMCARDBACKPATH = CMCARDBACKPATH;
    }

    /**
     * 
     * @return
     *     The CMPERSONALPATH
     */
    public String getCMPERSONALPATH() {
        return CMPERSONALPATH;
    }

    /**
     * 
     * @param CMPERSONALPATH
     *     The CM_PERSONALPATH
     */
    public void setCMPERSONALPATH(String CMPERSONALPATH) {
        this.CMPERSONALPATH = CMPERSONALPATH;
    }

    /**
     * 
     * @return
     *     The CMSHOPEADDRESS
     */
    public String getCMSHOPEADDRESS() {
        return CMSHOPEADDRESS;
    }

    /**
     * 
     * @param CMSHOPEADDRESS
     *     The CM_SHOPEADDRESS
     */
    public void setCMSHOPEADDRESS(String CMSHOPEADDRESS) {
        this.CMSHOPEADDRESS = CMSHOPEADDRESS;
    }

    /**
     * 
     * @return
     *     The CMSHOPLON
     */
    public Double getCMSHOPLON() {
        return CMSHOPLON;
    }

    /**
     * 
     * @param CMSHOPLON
     *     The CM_SHOPLON
     */
    public void setCMSHOPLON(Double CMSHOPLON) {
        this.CMSHOPLON = CMSHOPLON;
    }

    /**
     * 
     * @return
     *     The CMSHOPLAT
     */
    public Double getCMSHOPLAT() {
        return CMSHOPLAT;
    }

    /**
     * 
     * @param CMSHOPLAT
     *     The CM_SHOPLAT
     */
    public void setCMSHOPLAT(Double CMSHOPLAT) {
        this.CMSHOPLAT = CMSHOPLAT;
    }

    /**
     * 
     * @return
     *     The CMISEXAMINE
     */
    public Integer getCMISEXAMINE() {
        return CMISEXAMINE;
    }

    /**
     * 
     * @param CMISEXAMINE
     *     The CM_ISEXAMINE
     */
    public void setCMISEXAMINE(Integer CMISEXAMINE) {
        this.CMISEXAMINE = CMISEXAMINE;
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
     *     The EntityKey
     */
    public EntityKey getEntityKey() {
        return EntityKey;
    }

    /**
     * 
     * @param EntityKey
     *     The EntityKey
     */
    public void setEntityKey(EntityKey EntityKey) {
        this.EntityKey = EntityKey;
    }

}
