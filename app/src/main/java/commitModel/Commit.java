package commitModel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class Commit {

    @Expose
    private Integer INTEGRAL;
    @Expose
    private List<ORDER> ORDERS = new ArrayList<ORDER>();

    /**
     * 
     * @return
     *     The INTEGRAL
     */
    public Integer getINTEGRAL() {
        return INTEGRAL;
    }

    /**
     * 
     * @param INTEGRAL
     *     The INTEGRAL
     */
    public void setINTEGRAL(Integer INTEGRAL) {
        this.INTEGRAL = INTEGRAL;
    }

    /**
     * 
     * @return
     *     The ORDERS
     */
    public List<ORDER> getORDERS() {
        return ORDERS;
    }

    /**
     * 
     * @param ORDERS
     *     The ORDERS
     */
    public void setORDERS(List<ORDER> ORDERS) {
        this.ORDERS = ORDERS;
    }

}
