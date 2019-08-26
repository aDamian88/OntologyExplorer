package gr.therightclick.adam.ontologyexplorer.Model;

import android.arch.persistence.room.Ignore;
import android.content.Context;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import gr.therightclick.adam.ontologyexplorer.GeneralFunctions;
import gr.therightclick.adam.ontologyexplorer.MyAppDatabase;
import gr.therightclick.adam.ontologyexplorer.ObjectData;

public class MinCardinality {

    Integer id;
    Integer objectId;
    String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Ignore
    GeneralFunctions gFunctions = new GeneralFunctions();

    public void decodingMinCardinality(Context context, final String helpStr, Integer objectID) {
        try {
            saveMinCardinality(context, helpStr,objectID);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("errorDecod", e.getMessage());
        }
    }

    public void saveMinCardinality(Context context, String saving,Integer objectID) {

        MyAppDatabase myAppDatabase = MyAppDatabase.getAppDatabase(context);

        try {
            JSONArray propertyArray = new JSONArray(saving);
            for (int j = 0; j < propertyArray.length(); j++) {
                JSONObject jo2 = propertyArray.getJSONObject(j);
                String help2Str = jo2.getString("@type");
                String help3Str = jo2.getString("@value");

                if (help2Str.contains("#")) {
                    help2Str = gFunctions.separateResponse(jo2.getString("@type"));
                }

                ObjectData objectData = new ObjectData();
                objectData.setObjectId(objectID);
                objectData.setModel("MinCardinality");
                objectData.setValue(help2Str+": "+help3Str);
                myAppDatabase.MyDao().addObjectData(objectData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
