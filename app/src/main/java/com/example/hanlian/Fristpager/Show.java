package com.example.hanlian.Fristpager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Show {

    @SerializedName("CM_GOODSID")
    @Expose
    private String CMGOODSID;
    @SerializedName("CM_HORIZONTALPATH")
    @Expose
    private String CMHORIZONTALPATH;
    @SerializedName("CM_CREATETIME")
    @Expose
    private String CMCREATETIME;

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
     *     The CMHORIZONTALPATH
     */
    public String getCMHORIZONTALPATH() {
        return CMHORIZONTALPATH;
    }

    /**
     * 
     * @param CMHORIZONTALPATH
     *     The CM_HORIZONTALPATH
     */
    public void setCMHORIZONTALPATH(String CMHORIZONTALPATH) {
        this.CMHORIZONTALPATH = CMHORIZONTALPATH;
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

	@Override
	public String toString() {
		return "Show [CMGOODSID=" + CMGOODSID + ", CMHORIZONTALPATH=" + CMHORIZONTALPATH + ", CMCREATETIME="
				+ CMCREATETIME + "]";
	}
    
    

}
