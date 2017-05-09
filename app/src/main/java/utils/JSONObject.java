package utils;

import com.activeandroid.Model;
import com.activeandroid.annotation.Table;

/**
 * Created by Administrator on 2017/5/9.
 */

@Table(name = "GOODLIST1")
public class JSONObject extends Model implements Cloneable{



    @Override
    public JSONObject clone()
    {
        try
        {
            return (JSONObject) super.clone();
        } catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return null;
    }



}
