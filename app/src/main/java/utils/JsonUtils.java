package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import commitModel.GOODSDETAIL;

import java.util.List;


/**
 * Created by soft on 2016/12/26 0026.
 */

public class JsonUtils {

    /**
     * JSON数组上传
     */
    public static String parseJsontoStr(List<GOODSDETAIL> goodsList){
        JSONArray array=new JSONArray();
        for (int i=0;i<goodsList.size();i++){
            JSONObject object=new JSONObject();
            try {
                object.put("GOODSDETAILSID",goodsList.get(i).getGOODSDETAILSID());
                object.put("SPEC_NUMBER",goodsList.get(i).getSPECNUMBER());
                array.put(i,object);
            } catch (JSONException e){
            	
                e.printStackTrace();
            }
        }
        return array.toString();
    }
}
