package DaishouhuoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TBORDERDETAIL {

    @SerializedName("CM_GOODSID")
    @Expose
    private Object CMGOODSID;
    @SerializedName("CM_MAINFIGUREPATH")
    @Expose
    private Object CMMAINFIGUREPATH;
    @SerializedName("CM_TITLE")
    @Expose
    private Object CMTITLE;
    @SerializedName("CM_PRESENTPRICE")
    @Expose
    private Object CMPRESENTPRICE;
    @SerializedName("CM_MONEY")
    @Expose
    private Integer CMMONEY;
    @SerializedName("CM_NUMBER")
    @Expose
    private Integer CMNUMBER;
    @SerializedName("CM_SELLERID")
    @Expose
    private Object CMSELLERID;
    @SerializedName("CM_SELLERNAME")
    @Expose
    private Object CMSELLERNAME;

    /**
     * 
     * @return
     *     The CMGOODSID
     */
    public Object getCMGOODSID() {
        return CMGOODSID;
    }

    /**
     * 
     * @param CMGOODSID
     *     The CM_GOODSID
     */
    public void setCMGOODSID(Object CMGOODSID) {
        this.CMGOODSID = CMGOODSID;
    }

    /**
     * 
     * @return
     *     The CMMAINFIGUREPATH
     */
    public Object getCMMAINFIGUREPATH() {
        return CMMAINFIGUREPATH;
    }

    /**
     * 
     * @param CMMAINFIGUREPATH
     *     The CM_MAINFIGUREPATH
     */
    public void setCMMAINFIGUREPATH(Object CMMAINFIGUREPATH) {
        this.CMMAINFIGUREPATH = CMMAINFIGUREPATH;
    }

    /**
     * 
     * @return
     *     The CMTITLE
     */
    public Object getCMTITLE() {
        return CMTITLE;
    }

    /**
     * 
     * @param CMTITLE
     *     The CM_TITLE
     */
    public void setCMTITLE(Object CMTITLE) {
        this.CMTITLE = CMTITLE;
    }

    /**
     * 
     * @return
     *     The CMPRESENTPRICE
     */
    public Object getCMPRESENTPRICE() {
        return CMPRESENTPRICE;
    }

    /**
     * 
     * @param CMPRESENTPRICE
     *     The CM_PRESENTPRICE
     */
    public void setCMPRESENTPRICE(Object CMPRESENTPRICE) {
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
    public Object getCMSELLERID() {
        return CMSELLERID;
    }

    /**
     * 
     * @param CMSELLERID
     *     The CM_SELLERID
     */
    public void setCMSELLERID(Object CMSELLERID) {
        this.CMSELLERID = CMSELLERID;
    }

    /**
     * 
     * @return
     *     The CMSELLERNAME
     */
    public Object getCMSELLERNAME() {
        return CMSELLERNAME;
    }

    /**
     * 
     * @param CMSELLERNAME
     *     The CM_SELLERNAME
     */
    public void setCMSELLERNAME(Object CMSELLERNAME) {
        this.CMSELLERNAME = CMSELLERNAME;
    }

}
