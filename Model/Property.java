package gr.therightclick.adam.ontologyexplorer.Model;

import android.arch.persistence.room.Ignore;
import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import gr.therightclick.adam.ontologyexplorer.GeneralFunctions;
import gr.therightclick.adam.ontologyexplorer.MyAppDatabase;
import gr.therightclick.adam.ontologyexplorer.ObjectData;

public class Property {

    Integer id;
    Integer objectId;
    String property;

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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }


    GeneralFunctions gFunctions = new GeneralFunctions();

    public void decodingProperty(Context context,final String propertyStr,Integer objectID){

        MyAppDatabase myAppDatabase = MyAppDatabase.getAppDatabase(context);

        try {
            JSONArray propertyArray = new JSONArray(propertyStr);
            for(int j=0; j<propertyArray.length();j++){
                JSONObject jo2 = propertyArray.getJSONObject(j);
                String propertyHelp = jo2.getString("@id");

                if(propertyHelp.contains("#")){
                    propertyHelp = gFunctions.separateResponse(jo2.getString("@id"));
                }

                ObjectData objectData = new ObjectData();
                objectData.setObjectId(objectID);
                objectData.setModel("Property");
                objectData.setValue(propertyHelp);
                myAppDatabase.MyDao().addObjectData(objectData);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
