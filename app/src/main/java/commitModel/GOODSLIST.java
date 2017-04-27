package commitModel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GOODSLIST {

    @SerializedName("CM_SELLERID")
    @Expose
    private String CMSELLERID;
    @Expose
    private String GOODSID;
    @Expose
    private List<GOODSDETAIL> GOODSDETAILS = new ArrayList<GOODSDETAIL>();

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
     *     The GOODSID
     */
    public String getGOODSID() {
        return GOODSID;
    }

    /**
     * 
     * @param GOODSID
     *     The GOODSID
     */
    public void setGOODSID(String GOODSID) {
        this.GOODSID = GOODSID;
    }

    /**
     * 
     * @return
     *     The GOODSDETAILS
     */
    public List<GOODSDETAIL> getGOODSDETAILS() {
        return GOODSDETAILS;
    }

    /**
     * 
     * @param GOODSDETAILS
     *     The GOODSDETAILS
     */
    public void setGOODSDETAILS(List<GOODSDETAIL> GOODSDETAILS) {
        this.GOODSDETAILS = GOODSDETAILS;
    }

}
