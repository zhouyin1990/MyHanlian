package DetailsModle;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CMOTHER implements Serializable{

    @SerializedName("CM_GOODSDETAILSID")
    @Expose
    private Integer CMGOODSDETAILSID;
    @SerializedName("CM_COLOR")
    @Expose
    private String CMCOLOR;
    @SerializedName("CM_IMAGEPATH")
    @Expose
    private String CMIMAGEPATH;
    @SerializedName("CM_SPEC_STOCK")
    @Expose
    private String CMSPECSTOCK;
    /**
     * 
     * @return
     *     The CMGOODSDETAILSID
     */
    public Integer getCMGOODSDETAILSID() {
        return CMGOODSDETAILSID;
    }

    /**
     * 
     * @param CMGOODSDETAILSID
     *     The CM_GOODSDETAILSID
     */
    public void setCMGOODSDETAILSID(Integer CMGOODSDETAILSID) {
        this.CMGOODSDETAILSID = CMGOODSDETAILSID;
    }

    /**
     * 
     * @return
     *     The CMCOLOR
     */
    public String getCMCOLOR() {
        return CMCOLOR;
    }

    /**
     * 
     * @param CMCOLOR
     *     The CM_COLOR
     */
    public void setCMCOLOR(String CMCOLOR) {
        this.CMCOLOR = CMCOLOR;
    }

    /**
     * 
     * @return
     *     The CMIMAGEPATH
     */
    public String getCMIMAGEPATH() {
        return CMIMAGEPATH;
    }

    /**
     * 
     * @param CMIMAGEPATH
     *     The CM_IMAGEPATH
     */
    public void setCMIMAGEPATH(String CMIMAGEPATH) {
        this.CMIMAGEPATH = CMIMAGEPATH;
    }

    /**
     * 
     * @return
     *     The CMSPECSTOCK
     */
    public String getCMSPECSTOCK() {
        return CMSPECSTOCK;
    }

    /**
     * 
     * @param CMSPECSTOCK
     *     The CM_SPEC_STOCK
     */
    public void setCMSPECSTOCK(String CMSPECSTOCK) {
        this.CMSPECSTOCK = CMSPECSTOCK;
    }

}
