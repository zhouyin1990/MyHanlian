package DetailsModle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result implements Serializable{

    @SerializedName("CM_GOODSID")
    @Expose
    private String CMGOODSID;
    @SerializedName("CM_TITLE")
    @Expose
    private String CMTITLE;
    @SerializedName("CM_SALES")
    @Expose
    private Integer CMSALES;
    @SerializedName("CM_HTML")
    @Expose
    private String CMHTML;
    @SerializedName("CM_BRANDID")
    @Expose
    private Object CMBRANDID;
    @SerializedName("CM_MAINFIGUREPATH")
    @Expose
    private String CMMAINFIGUREPATH;
    @SerializedName("CM_FIGURESPATH")
    @Expose
    private String CMFIGURESPATH;
    @SerializedName("CM_CREATETIME")
    @Expose
    private String CMCREATETIME;
    @SerializedName("CM_ORIGINALPRICE")
    @Expose
    private Integer CMORIGINALPRICE;
    @SerializedName("CM_PRESENTPRICE")
    @Expose
    private Integer CMPRESENTPRICE;
    @SerializedName("CM_ISOFF")
    @Expose
    private Integer CMISOFF;
    @SerializedName("CM_SELLERID")
    @Expose
    private String CMSELLERID;
    @SerializedName("CM_SELLERNAME")
    @Expose
    private String CMSELLERNAME;
    @SerializedName("CM_ISCOLLECTION")
    @Expose
    private Integer CMISCOLLECTION;
    @SerializedName("CM_OTHER")
    @Expose
    private List<CMOTHER> CMOTHER = new ArrayList<CMOTHER>();

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
     *     The CMHTML
     */
    public String getCMHTML() {
        return CMHTML;
    }

    /**
     * 
     * @param CMHTML
     *     The CM_HTML
     */
    public void setCMHTML(String CMHTML) {
        this.CMHTML = CMHTML;
    }

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

    /**
     * 
     * @return
     *     The CMISCOLLECTION
     */
    public Integer getCMISCOLLECTION() {
        return CMISCOLLECTION;
    }

    /**
     * 
     * @param CMISCOLLECTION
     *     The CM_ISCOLLECTION
     */
    public void setCMISCOLLECTION(Integer CMISCOLLECTION) {
        this.CMISCOLLECTION = CMISCOLLECTION;
    }

    /**
     * 
     * @return
     *     The CMOTHER
     */
    public List<CMOTHER> getCMOTHER() {
        return CMOTHER;
    }

    /**
     * 
     * @param CMOTHER
     *     The CM_OTHER
     */
    public void setCMOTHER(List<CMOTHER> CMOTHER) {
        this.CMOTHER = CMOTHER;
    }

	@Override
	public String toString() {
		return "Result [CMGOODSID=" + CMGOODSID + ", CMTITLE=" + CMTITLE + ", CMSALES=" + CMSALES + ", CMHTML=" + CMHTML
				+ ", CMBRANDID=" + CMBRANDID + ", CMMAINFIGUREPATH=" + CMMAINFIGUREPATH + ", CMFIGURESPATH="
				+ CMFIGURESPATH + ", CMCREATETIME=" + CMCREATETIME + ", CMORIGINALPRICE=" + CMORIGINALPRICE
				+ ", CMPRESENTPRICE=" + CMPRESENTPRICE + ", CMISOFF=" + CMISOFF + ", CMSELLERID=" + CMSELLERID
				+ ", CMSELLERNAME=" + CMSELLERNAME + ", CMISCOLLECTION=" + CMISCOLLECTION + ", CMOTHER=" + CMOTHER
				+ "]";
	}
    
    
}
