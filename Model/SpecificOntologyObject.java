package gr.therightclick.adam.ontologyexplorer.Model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gr.therightclick.adam.ontologyexplorer.GeneralFunctions;
import gr.therightclick.adam.ontologyexplorer.MyAppDatabase;
import gr.therightclick.adam.ontologyexplorer.ObjectData;

public class SpecificOntologyObject {
    
    Integer id;
    Integer objectId;
    String specificOntologyObject;

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

    public String getSpecificOntologyObject() {
        return specificOntologyObject;
    }

    public void setSpecificOntologyObject(String specificOntologyObject) {
        this.specificOntologyObject = specificOntologyObject;
    }

    GeneralFunctions gFunctions = new GeneralFunctions();

    public void decodingSpecificOntologyObject(Context context, final String specificObjectStr, Integer objectID){

        MyAppDatabase myAppDatabase = MyAppDatabase.getAppDatabase(context);

        try {
            JSONArray objectArray = new JSONArray(specificObjectStr);
            for(int i=0; i<objectArray.length();i++){
                JSONObject jo = objectArray.getJSONObject(i);
                String objectName = jo.getString("@id");

                if(objectName.contains("#")){
                    objectName = gFunctions.separateResponse(jo.getString("@id"));
                }

//////////////////////////////////////////////
                String type = "";
                String typeJson = "";

                try {
                    typeJson = jo.getString("@type");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (typeJson.contains(",")) {
                    String formattedTypeJson = typeJson.replace("\\", "");
                    String[] types = gFunctions.separateDifferentTypes(formattedTypeJson);
                    for (int j = 0; j < types.length; j++) {
                        type = gFunctions.separateType(types[j]);

                        ObjectData objectData = new ObjectData();
                        objectData.setObjectId(objectID);
                        objectData.setModel(type);
                        objectData.setValue(objectName);
                        myAppDatabase.MyDao().addObjectData(objectData);
                    }
                } else {
                    type = gFunctions.separateType(typeJson);

                    ObjectData objectData = new ObjectData();
                    objectData.setObjectId(objectID);
                    objectData.setModel(type);
                    objectData.setValue(objectName);
                    myAppDatabase.MyDao().addObjectData(objectData);

                }

            }
        } catch (JSONException e) {
            Log.d("error specificOntObj",e.getMessage());
        }
    }

}
