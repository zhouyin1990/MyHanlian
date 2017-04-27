package com.example.hanlian.Fristpager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Good {

    @SerializedName("CM_BRANDID")
    @Expose
    private Object CMBRANDID;
    @SerializedName("CM_FIGURESPATH")
    @Expose
    private String CMFIGURESPATH;
    @SerializedName("CM_GOODSID")
    @Expose
    private String CMGOODSID;
    @SerializedName("CM_MAINFIGUREPATH")
    @Expose
    private String CMMAINFIGUREPATH;
    @SerializedName("CM_PRESENTPRICE")
    @Expose
    private Integer CMPRESENTPRICE;
    @SerializedName("CM_ORIGINALPRICE")
    @Expose
    private Integer CMORIGINALPRICE;
    @SerializedName("CM_SALES")
    @Expose
    private Integer CMSALES;
    @SerializedName("CM_TITLE")
    @Expose
    private String CMTITLE;
    @SerializedName("CM_CREATETIME")
    @Expose
    private String CMCREATETIME;
    @SerializedName("CM_ISOFF")
    @Expose
    private Integer CMISOFF;
    @SerializedName("CM_SELLERID")
    @Expose
    private String CMSELLERID;

    /**
     * 
     * @return
     *     The CMBRANDID
     */
    public Object getCMBRANDID() {
        return CMBRANDID;
    }

    /**
     * 
     * @param CMBRANDID
     *     The CM_BRANDID
     */
    public void setCMBRANDID(Object CMBRANDID) {
        this.CMBRANDID = CMBRANDID;
    }

    /**
     * 
     * @return
     *     The CMFIGURESPATH
     */
    public String getCMFIGURESPATH() {
        return CMFIGURESPATH;
    }

    /**
     * 
     * @param CMFIGURESPATH
     *     The CM_FIGURESPATH
     */
    public void setCMFIGURESPATH(String CMFIGURESPATH) {
        this.CMFIGURESPATH = CMFIGURESPATH;
    }

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
     *     The CMORIGINALPRICE
     */
    public Integer getCMORIGINALPRICE() {
        return CMORIGINALPRICE;
    }

    /**
     * 
     * @param CMORIGINALPRICE
     *     The CM_ORIGINALPRICE
     */
    public void setCMORIGINALPRICE(Integer CMORIGINALPRICE) {
        this.CMORIGINALPRICE = CMORIGINALPRICE;
    }

    /**
     * 
     * @return
     *     The CMSALES
     */
    public Integer getCMSALES() {
        return CMSALES;
    }

    /**
     * 
     * @param CMSALES
     *     The CM_SALES
     */
    public void setCMSALES(Integer CMSALES) {
        this.CMSALES = CMSALES;
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
     *     The CMISOFF
     */
    public Integer getCMISOFF() {
        return CMISOFF;
    }

    /**
     * 
     * @param CMISOFF
     *     The CM_ISOFF
     */
    public void setCMISOFF(Integer CMISOFF) {
        this.CMISOFF = CMISOFF;
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

}
