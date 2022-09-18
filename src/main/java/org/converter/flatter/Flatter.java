package org.converter.flatter;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Flatter {

    public static List<Map<String,String>> jsonFlatter(String jsonString) {
        List<Map<String,String>> flatJson=null;
        try {
            flatJson = new ArrayList<Map<String, String>>();
            JSONObject jsonObject = new JSONObject(jsonString);
        }catch (JSONException exception){
          flatJson  =  handleAsJson(jsonString);
        }
        return flatJson;
    }


    private static List<Map<String,String>> handleAsJson(String jsonString) {

        Map<String, String> flatJson = null;
        List<Map<String, String>> flatJsons = null;
        try {
            flatJson = new LinkedHashMap<>();
            JSONArray jsonArray = new JSONArray(jsonString);
            
         flatJsons = parse(jsonArray);
        }catch (JSONException exception){
            System.out.println(exception.getMessage());
        }
        return flatJsons;
    }

    private static List<Map<String,String>> parse(JSONArray jsonArray) {
        List<Map<String,String>> flatJsons = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject =jsonArray.getJSONObject(i);
            Map<String, String> flatJson = parse(jsonObject);
            flatJsons.add(flatJson);
        }
        return flatJsons;
    }
    private static Map<String, String>  parse(JSONObject jsonObject) {
        Map<String, String> flatJson = new LinkedHashMap<>();
        flatJson = flatten(jsonObject,flatJson,"");
        return flatJson;
    }

    private static Map<String, String> flatten(JSONObject object,Map<String ,String> flatJson,String prefix) {
        String _prefix = prefix != "" ? prefix + "." : "";
        Iterator<String> iterator = object.keys();
        while (iterator.hasNext()){
            String key = iterator.next();
            if (object.get(key).getClass() .equals(JSONObject.class)){
                JSONObject jsonObj = (JSONObject) object.get(key);
                flatten(jsonObj,flatJson,_prefix + key);
            }else  if (object.get(key).getClass().equals(JSONArray.class)){
                JSONArray jsonArray = (JSONArray) object.get(key);
                if (jsonArray.length() < 1) {
                    continue;
                }
                flatten(jsonArray,flatJson,_prefix + key);
            }else {
                String value = object.get(key).toString();
                flatJson.put(_prefix+key,value);
            }
        }
        return flatJson;
    }

    private static void flatten(JSONArray jsonArray,Map<String ,String> flatJson,String prefix) {

        for (int j = 0; j < jsonArray.length(); j++) {

            if (jsonArray.get(j).getClass().equals(JSONObject.class)){
                JSONObject jsonObject = (JSONObject) jsonArray.get(j);

                // jsonArray is empty
                if (jsonArray.length() < 1) {
                    continue;
                }

                flatten(jsonObject,flatJson,prefix + "[" + j + "]");

            }else if (jsonArray.get(j).getClass().equals(JSONArray.class)){
                JSONArray jsonArr = (JSONArray) jsonArray.get(j);

                flatten(jsonArr,flatJson,prefix + "[" + j + "]");

            }else {
                String value = jsonArray.get(j).toString();
                flatJson.put(prefix + "[" + (j + 1) + "]", value);
            }
        }
    }


    public static List<Map<String, String>> parseJson(File file, String encode) {
        List<Map<String, String>> flatJsons = null;
        try {
            String jsonString = FileUtils.readFileToString(file, encode);
            flatJsons = jsonFlatter(jsonString);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return flatJsons;
    }
}
