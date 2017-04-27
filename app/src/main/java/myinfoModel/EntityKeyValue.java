package myinfoModel;

import com.google.gson.annotations.Expose;

public class EntityKeyValue {

    @Expose
    private String Key;
    @Expose
    private String Type;
    @Expose
    private String Value;

    /**
     * 
     * @return
     *     The Key
     */
    public String getKey() {
        return Key;
    }

    /**
     * 
     * @param Key
     *     The Key
     */
    public void setKey(String Key) {
        this.Key = Key;
    }

    /**
     * 
     * @return
     *     The Type
     */
    public String getType() {
        return Type;
    }

    /**
     * 
     * @param Type
     *     The Type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * 
     * @return
     *     The Value
     */
    public String getValue() {
        return Value;
    }

    /**
     * 
     * @param Value
     *     The Value
     */
    public void setValue(String Value) {
        this.Value = Value;
    }

}
