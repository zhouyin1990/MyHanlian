package myinfoModel;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;

public class EntityKey {

    @Expose
    private String $id;
    @Expose
    private String EntitySetName;
    @Expose
    private String EntityContainerName;
    @Expose
    private List<EntityKeyValue> EntityKeyValues = new ArrayList<EntityKeyValue>();

    /**
     * 
     * @return
     *     The $id
     */
    public String get$id() {
        return $id;
    }

    /**
     * 
     * @param $id
     *     The $id
     */
    public void set$id(String $id) {
        this.$id = $id;
    }

    /**
     * 
     * @return
     *     The EntitySetName
     */
    public String getEntitySetName() {
        return EntitySetName;
    }

    /**
     * 
     * @param EntitySetName
     *     The EntitySetName
     */
    public void setEntitySetName(String EntitySetName) {
        this.EntitySetName = EntitySetName;
    }

    /**
     * 
     * @return
     *     The EntityContainerName
     */
    public String getEntityContainerName() {
        return EntityContainerName;
    }

    /**
     * 
     * @param EntityContainerName
     *     The EntityContainerName
     */
    public void setEntityContainerName(String EntityContainerName) {
        this.EntityContainerName = EntityContainerName;
    }

    /**
     * 
     * @return
     *     The EntityKeyValues
     */
    public List<EntityKeyValue> getEntityKeyValues() {
        return EntityKeyValues;
    }

    /**
     * 
     * @param EntityKeyValues
     *     The EntityKeyValues
     */
    public void setEntityKeyValues(List<EntityKeyValue> EntityKeyValues) {
        this.EntityKeyValues = EntityKeyValues;
    }

}
