package commitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GOODSDETAIL {

    @Expose
    private String GOODSDETAILSID;
    @SerializedName("SPEC_NUMBER")
    @Expose
    private String SPECNUMBER;

    /**
     * 
     * @return
     *     The GOODSDETAILSID
     */
    public String getGOODSDETAILSID() {
        return GOODSDETAILSID;
    }

    /**
     * 
     * @param GOODSDETAILSID
     *     The GOODSDETAILSID
     */
    public void setGOODSDETAILSID(String GOODSDETAILSID) {
        this.GOODSDETAILSID = GOODSDETAILSID;
    }

    /**
     * 
     * @return
     *     The SPECNUMBER
     */
    public String getSPECNUMBER() {
        return SPECNUMBER;
    }

    /**
     * 
     * @param SPECNUMBER
     *     The SPEC_NUMBER
     */
    public void setSPECNUMBER(String SPECNUMBER) {
        this.SPECNUMBER = SPECNUMBER;
    }

}
