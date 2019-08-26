package gr.therightclick.adam.ontologyexplorer;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class DecodingResponse {

    MyAppDatabase myAppDatabase;
    PrefsHandler pHandler = new PrefsHandler();
    Integer objectId;
    GeneralFunctions gFunctions = new GeneralFunctions();
    CheckValues checkValues = new CheckValues();
    ArrayList<String> valuesForRequest = new ArrayList();

    public DecodingResponse(Context context, String response, String ontologySelection) {

        myAppDatabase = MyAppDatabase.getAppDatabaseFallBack(context);

        String[] data = {" "};
        try {
            JSONArray jArray = new JSONArray(response);

            data = new String[jArray.length()];


            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo = jArray.getJSONObject(i);

                String ontologyComponent = jo.getString("@id");
                /// First decoding
                if (ontologyComponent.contains("#")) {
                    ontologyComponent = gFunctions.separateResponse(ontologyComponent);
                }

                Log.d("helpp", "helpp " + gFunctions.separateResponse(jo.getString("@type")));

                /////////// type ///////////
                String typeJson = jo.getString("@type");
                String type = "";
/*                if (typeJson.contains(",")) {
                    Log.d("DecodinggTypee0", String.valueOf(typeJson));
                    String formattedTypeJson = typeJson.replace("\\", "");
                    Log.d("DecodinggTypee01", String.valueOf(formattedTypeJson));
                    String[] types = gFunctions.separateDifferentTypes(formattedTypeJson);
                    for (int j = 0; j < types.length; j++) {
                        type = gFunctions.separateType(types[j]);
                        data[i] = ontologyComponent + "   " + type;
                        Log.d("DecodinggTypee1", String.valueOf(type));
                        Log.d("DecodinggTypee2", String.valueOf(types[j]));
                        settingOTO(ontologyComponent, type,ontologySelection);
                        if(checkValues.hasObjectValueForRequest(jo)) {
                            checkValues.checkIfValuesExists(jo, context, objectId);
                        }else{
                            setOntologyObject(jo);
                        }
                    }
                } else {*/
                type = gFunctions.separateType(typeJson);
                data[i] = ontologyComponent + "   " + type;
                settingOTO(ontologyComponent, type, ontologySelection);
                if (checkValues.hasObjectValueForRequest(jo)) {
                    checkValues.checkIfValuesExists(jo, context, objectId);
                } else {
                    setOntologyObject(jo);
                }
//                }

                pHandler.putSelectedOntology(context, ontologySelection);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setOntologyObject(JSONObject jo) {
        Iterator<String> keys = jo.keys();
        while (keys.hasNext()) {
            String iterationId = String.valueOf(keys.next());
            String formattedIterationId = iterationId.replace("\\", "");
            String specificObject = "";
            if (formattedIterationId.contains("http")) {
                try {
                    specificObject = jo.getString(formattedIterationId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String specific = gFunctions.separateResponse(formattedIterationId);
                try {

                    JSONArray propertyArray = new JSONArray(specificObject);
                    for (int j = 0; j < propertyArray.length(); j++) {
                        JSONObject jo2 = propertyArray.getJSONObject(j);
                        String propertyHelp = jo2.getString("@id");

                        if (propertyHelp.contains("#")) {
                            propertyHelp = gFunctions.separateResponse(jo2.getString("@id"));
                        }

                        ObjectData objectData = new ObjectData();
                        objectData.setObjectId(objectId);
                        objectData.setModel(specific);
                        objectData.setValue(propertyHelp);
                        myAppDatabase.MyDao().addObjectData(objectData);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /// Setting and saving ObjectTypeOntology object ////////////////
    public void settingOTO(String object, String type, String ontology) {
        ObjectTypeOntology oto = new ObjectTypeOntology();
        oto.setObject(object);
        oto.setType(type);
        oto.setOntology(ontology);

        objectId = (int) (long) myAppDatabase.MyDao().addOTO(oto);

    }

}
