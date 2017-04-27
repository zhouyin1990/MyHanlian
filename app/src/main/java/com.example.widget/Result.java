package com.example.widget;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("CM_BRAND")
    @Expose
    private String CMBRAND;
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
    @SerializedName("CM_JOINTIME")
    @Expose
    private String CMJOINTIME;
    @SerializedName("CM_COLLECTIONID")
    @Expose
    private Integer CMCOLLECTIONID;

    /**
     * 
     * @return
     *     The CMBRAND
     */
    public String getCMBRAND() {
        return CMBRAND;
    }

    /**
     * 
     * @param CMBRAND
     *     The CM_BRAND
     */
    public void setCMBRAND(String CMBRAND) {
        this.CMBRAND = CMBRAND;
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
     *     The CMJOINTIME
     */
    public String getCMJOINTIME() {
        return CMJOINTIME;
    }

    /**
     * 
     * @param CMJOINTIME
     *     The CM_JOINTIME
     */
    public void setCMJOINTIME(String CMJOINTIME) {
        this.CMJOINTIME = CMJOINTIME;
    }

    /**
     * 
     * @return
     *     The CMCOLLECTIONID
     */
    public Integer getCMCOLLECTIONID() {
        return CMCOLLECTIONID;
    }

    /**
     * 
     * @param CMCOLLECTIONID
     *     The CM_COLLECTIONID
     */
    public void setCMCOLLECTIONID(Integer CMCOLLECTIONID) {
        this.CMCOLLECTIONID = CMCOLLECTIONID;
    }

}
