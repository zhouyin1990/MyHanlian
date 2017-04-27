package UnpayModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TBORDERDETAIL {

    @SerializedName("CM_GOODSID")
    @Expose
    private String CMGOODSID;
    @SerializedName("CM_MAINFIGUREPATH")
    @Expose
    private String CMMAINFIGUREPATH;
    @SerializedName("CM_TITLE")
    @Expose
    private String CMTITLE;
    @SerializedName("CM_PRESENTPRICE")
    @Expose
    private Integer CMPRESENTPRICE;
    @SerializedName("CM_MONEY")
    @Expose
    private Integer CMMONEY;
    @SerializedName("CM_NUMBER")
    @Expose
    private Integer CMNUMBER;
    @SerializedName("CM_SELLERID")
    @Expose
    private String CMSELLERID;
    @SerializedName("CM_SELLERNAME")
    @Expose
    private String CMSELLERNAME;

    /**
     * 
     * @return
     *     The CMGOODSID
     */
    public String getCMGOODSID() {
        return CMGOODSID;
    }

    /**
     * 
     * @param CMGOODSID
     *     The CM_GOODSID
     */
    public void setCMGOODSID(String CMGOODSID) {
        this.CMGOODSID = CMGOODSID;
    }

    /**
     * 
     * @return
     *     The CMMAINFIGUREPATH
     */
    public String getCMMAINFIGUREPATH() {
        return CMMAINFIGUREPATH;
    }

    /**
     * 
     * @param CMMAINFIGUREPATH
     *     The CM_MAINFIGUREPATH
     */
    public void setCMMAINFIGUREPATH(String CMMAINFIGUREPATH) {
        this.CMMAINFIGUREPATH = CMMAINFIGUREPATH;
    }

    /**
     * 
     * @return
     *     The CMTITLE
     */
    public String getCMTITLE() {
        return CMTITLE;
    }

    /**
     * 
     * @param CMTITLE
     *     The CM_TITLE
     */
    public void setCMTITLE(String CMTITLE) {
        this.CMTITLE = CMTITLE;
    }

    /**
     * 
     * @return
     *     The CMPRESENTPRICE
     */
    public Integer getCMPRESENTPRICE() {
        return CMPRESENTPRICE;
    }

    /**
     * 
     * @param CMPRESENTPRICE
     *     The CM_PRESENTPRICE
     */
    public void setCMPRESENTPRICE(Integer CMPRESENTPRICE) {
        this.CMPRESENTPRICE = CMPRESENTPRICE;
    }

    /**
     * 
     * @return
     *     The CMMONEY
     */
    public Integer getCMMONEY() {
        return CMMONEY;
    }

    /**
     * 
     * @param CMMONEY
     *     The CM_MONEY
     */
    public void setCMMONEY(Integer CMMONEY) {
        this.CMMONEY = CMMONEY;
    }

    /**
     * 
     * @return
     *     The CMNUMBER
     */
    public Integer getCMNUMBER() {
        return CMNUMBER;
    }

    /**
     * 
     * @param CMNUMBER
     *     The CM_NUMBER
     */
    public void setCMNUMBER(Integer CMNUMBER) {
        this.CMNUMBER = CMNUMBER;
    }

    /**
     * 
     * @return
     *     The CMSELLERID
     */
    public String getCMSELLERID() {
        return CMSELLERID;
    }

    /**
     * 
     * @param CMSELLERID
     *     The CM_SELLERID
     */
    public void setCMSELLERID(String CMSELLERID) {
        this.CMSELLERID = CMSELLERID;
    }

    /**
     * 
     * @return
     *     The CMSELLERNAME
     */
    public String getCMSELLERNAME() {
        return CMSELLERNAME;
    }

    /**
     * 
     * @param CMSELLERNAME
     *     The CM_SELLERNAME
     */
    public void setCMSELLERNAME(String CMSELLERNAME) {
        this.CMSELLERNAME = CMSELLERNAME;
    }

}
