package commitModel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class ORDER {

    @Expose
    private List<GOODSLIST> GOODSLIST = new ArrayList<GOODSLIST>();

    /**
     * 
     * @return
     *     The GOODSLIST
     */
    public List<GOODSLIST> getGOODSLIST() {
        return GOODSLIST;
    }

    /**
     * 
     * @param GOODSLIST
     *     The GOODSLIST
     */
    public void setGOODSLIST(List<GOODSLIST> GOODSLIST) {
        this.GOODSLIST = GOODSLIST;
    }

}
