package gr.therightclick.adam.ontologyexplorer;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gr.therightclick.adam.ontologyexplorer.Model.AllValuesFrom;
import gr.therightclick.adam.ontologyexplorer.Model.Cardinality;
import gr.therightclick.adam.ontologyexplorer.Model.DistrictMembers;
import gr.therightclick.adam.ontologyexplorer.Model.Domain;
import gr.therightclick.adam.ontologyexplorer.Model.EquivalentClass;
import gr.therightclick.adam.ontologyexplorer.Model.HasValue;
import gr.therightclick.adam.ontologyexplorer.Model.IntersectionOf;
import gr.therightclick.adam.ontologyexplorer.Model.InverseOf;
import gr.therightclick.adam.ontologyexplorer.Model.MaxCardinality;
import gr.therightclick.adam.ontologyexplorer.Model.MinCardinality;
import gr.therightclick.adam.ontologyexplorer.Model.OneOf;
import gr.therightclick.adam.ontologyexplorer.Model.Property;
import gr.therightclick.adam.ontologyexplorer.Model.Range;
import gr.therightclick.adam.ontologyexplorer.Model.SomeValuesFrom;
import gr.therightclick.adam.ontologyexplorer.Model.SpecificOntologyObject;
import gr.therightclick.adam.ontologyexplorer.Model.SubClassOf;
import gr.therightclick.adam.ontologyexplorer.Model.SubPropertyOf;
import gr.therightclick.adam.ontologyexplorer.Model.UnionOf;


public class CheckValues {

    GeneralFunctions gFunctions = new GeneralFunctions();
    PrefsHandler prefsHandler = new PrefsHandler();
    Integer objectId;

    public void checkIfValuesExists(JSONObject jo, Context context, Integer objectID) {


        /////// onProperty //////////////////////// creation of method at Property model. ok @id
        if (jo.has("http://www.w3.org/2002/07/owl#onProperty")) {
            String onProperty = null;
            try {
                onProperty = jo.getString("http://www.w3.org/2002/07/owl#onProperty");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Property property = new Property();
            property.decodingProperty(context, onProperty, objectID);
        }


        /////// intersectionOf //////////////////////// ok @list
        if (jo.has("http://www.w3.org/2002/07/owl#intersectionOf")) {
            String intersectionOf = null;
            try {
                intersectionOf = jo.getString("http://www.w3.org/2002/07/owl#intersectionOf");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("intersectionOf", String.valueOf(intersectionOf));
            IntersectionOf intersection = new IntersectionOf();
            intersection.decodingIntersectionOf(context, intersectionOf, objectID);
        }


        /////// hasValue //////////////////////// ok @id ?
        if (jo.has("http://www.w3.org/2002/07/owl#hasValue")) { ///////////// ?????????????????????
            String hasValue = null;
            try {
                hasValue = jo.getString("http://www.w3.org/2002/07/owl#hasValue");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            HasValue hasValueObj = new HasValue();
            hasValueObj.decodingHasValue(context, hasValue, objectID);
        }

        /////// allValuesFrom //////////////////////// ok @id
        if (jo.has("http://www.w3.org/2002/07/owl#allValuesFrom")) {
            String allValuesFrom = null;
            try {
                allValuesFrom = jo.getString("http://www.w3.org/2002/07/owl#allValuesFrom");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AllValuesFrom allValues = new AllValuesFrom();
            allValues.decodingAllValuesFrom(context, allValuesFrom, objectID);
        }

        /////// oneOf //////////////////////// ok @list ?
        if (jo.has("http://www.w3.org/2002/07/owl#oneOf")) { ///////////////////////// ?????????????????????
            String oneOf = null;
            try {
                oneOf = jo.getString("http://www.w3.org/2002/07/owl#oneOf");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OneOf oneOfObj = new OneOf();
            oneOfObj.decodingOneOf(context, oneOf, objectID);
        }

        /////// maxCardinality //////////////////////// @type,@value
        if (jo.has("http://www.w3.org/2002/07/owl#maxCardinality")) {
            String maxCardinality = null;
            try {
                maxCardinality = jo.getString("http://www.w3.org/2002/07/owl#maxCardinality");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MaxCardinality maxCardinalityObj = new MaxCardinality();
            maxCardinalityObj.decodingMaxCardinality(context, maxCardinality, objectID);
        }

        /////// minCardinality //////////////////////// @type, @value
        if (jo.has("http://www.w3.org/2002/07/owl#minCardinality")) {
            String minCardinality = null;
            try {
                minCardinality = jo.getString("http://www.w3.org/2002/07/owl#minCardinality");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MinCardinality minCardinalityObj = new MinCardinality();
            minCardinalityObj.decodingMinCardinality(context, minCardinality, objectID);
        }

        /////// cardinality ////////////////////////  @type, @value
        if (jo.has("http://www.w3.org/2002/07/owl#cardinality")) {////////////////////// ?????????????????????????
            String cardinality = null;
            try {
                cardinality = jo.getString("http://www.w3.org/2002/07/owl#cardinality");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Cardinality cardinalityObj = new Cardinality();
            cardinalityObj.decodingCardinality(context, cardinality, objectID);
        }

        /////// someValuesFrom //////////////////////// ok @id
        if (jo.has("http://www.w3.org/2002/07/owl#someValuesFrom")) {
            String someValuesFrom = null;
            try {
                someValuesFrom = jo.getString("http://www.w3.org/2002/07/owl#someValuesFrom");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SomeValuesFrom someValuesFromObj = new SomeValuesFrom();
            someValuesFromObj.decodingSomeValuesFrom(context, someValuesFrom, objectID);
        }


        /////// unionOf //////////////////////// ok @list
        if (jo.has("http://www.w3.org/2002/07/owl#unionOf")) {
            String unionOf = null;
            try {
                unionOf = jo.getString("http://www.w3.org/2002/07/owl#unionOf");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            UnionOf unionOfObj = new UnionOf();
            unionOfObj.decodingUnionOf(context, unionOf, objectID);
        }

        /////// districtMembers //////////////////////// ok @list
        if (jo.has("http://www.w3.org/2002/07/owl#distinctMembers")) {
            String districtMembers = null;
            try {
                districtMembers = jo.getString("http://www.w3.org/2002/07/owl#distinctMembers");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            DistrictMembers districtMembersObj = new DistrictMembers();
            districtMembersObj.decodingDistrictMembers(context, districtMembers, objectID);
        }

        /////// comment //////////////////////// @value,@value
        if (jo.has("http://www.w3.org/2000/01/rdf-schema#comment")) {
            String comment = null;
            try {
                comment = jo.getString("http://www.w3.org/2000/01/rdf-schema#comment");
            } catch (JSONException e) {
                e.printStackTrace();
            }
//            Property property = new Property();
//            property.decodingProperty(context,intersectionOf);
        }

        /////// equivalent class //////////////////////// ok @id
        if (jo.has("http://www.w3.org/2002/07/owl#equivalentClass")) {
            String equivalent = null;
            try {
                equivalent = jo.getString("http://www.w3.org/2002/07/owl#equivalentClass");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            EquivalentClass equivalentClass = new EquivalentClass();
            equivalentClass.decodingEquivalentClass(context, equivalent, objectID);

        }

        /////// sub class of //////////////////////// ok @id
        if (jo.has("http://www.w3.org/2000/01/rdf-schema#subClassOf")) {
            String subClassOf = null;
            try {
                subClassOf = jo.getString("http://www.w3.org/2000/01/rdf-schema#subClassOf");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SubClassOf subClassOfObj = new SubClassOf();
            subClassOfObj.decodingSubClassOf(context, subClassOf, objectID);
        }

        /////// domain //////////////////////// ok @id
        if (jo.has("http://www.w3.org/2000/01/rdf-schema#domain")) {
            String domain = null;
            try {
                domain = jo.getString("http://www.w3.org/2000/01/rdf-schema#domain");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Domain domainObj = new Domain();
            domainObj.decodingDomain(context, domain, objectID);
        }

        /////// range //////////////////////// ok @id
        if (jo.has("http://www.w3.org/2000/01/rdf-schema#range")) {
            String range = null;
            try {
                range = jo.getString("http://www.w3.org/2000/01/rdf-schema#range");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Range rangeObj = new Range();
            rangeObj.decodingRange(context, range, objectID);
        }

        /////// sub property of //////////////////////// ok @id
        if (jo.has("http://www.w3.org/2000/01/rdf-schema#subPropertyOf")) {
            String subPropertyOf = null;
            try {
                subPropertyOf = jo.getString("http://www.w3.org/2000/01/rdf-schema#subPropertyOf");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SubPropertyOf subPropertyOfObj = new SubPropertyOf();
            subPropertyOfObj.decodingSubPropertyOf(context, subPropertyOf, objectID);
        }

        /////// inverse of //////////////////////// ok @id
        if (jo.has("http://www.w3.org/2002/07/owl#inverseOf")) {
            String inverseOf = null;
            try {
                inverseOf = jo.getString("http://www.w3.org/2002/07/owl#inverseOf");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            InverseOf inverseOfObj = new InverseOf();
            inverseOfObj.decodingInverseOf(context, inverseOf, objectID);
        }
    }

    public boolean hasValueForRequest(String jo) {
        boolean hasValue = false;

        if (jo.contains("http://www.w3.org/2002/07/owl#onProperty")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2002/07/owl#intersectionOf")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2002/07/owl#hasValue")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2002/07/owl#allValuesFrom")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2002/07/owl#oneOf")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2002/07/owl#maxCardinality")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2002/07/owl#minCardinality")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2002/07/owl#cardinality")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2002/07/owl#someValuesFrom")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2002/07/owl#unionOf")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2002/07/owl#distinctMembers")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2000/01/rdf-schema#comment")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2002/07/owl#equivalentClass")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2000/01/rdf-schema#subClassOf")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2000/01/rdf-schema#domain")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2000/01/rdf-schema#range")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2000/01/rdf-schema#subPropertyOf")) {
            hasValue = true;
        }

        if (jo.contains("http://www.w3.org/2002/07/owl#inverseOf")) {
            hasValue = true;
        }

        if (jo.contains("@id")) {
            hasValue = true;
        }

        if (jo.contains("@type")) {
            hasValue = true;
        }


        return hasValue;
    }

    public boolean hasObjectValueForRequest(JSONObject jo) {
        boolean hasValue = false;

        if (jo.has("http://www.w3.org/2002/07/owl#onProperty")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2002/07/owl#intersectionOf")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2002/07/owl#hasValue")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2002/07/owl#allValuesFrom")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2002/07/owl#oneOf")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2002/07/owl#maxCardinality")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2002/07/owl#minCardinality")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2002/07/owl#cardinality")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2002/07/owl#someValuesFrom")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2002/07/owl#unionOf")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2002/07/owl#distinctMembers")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2000/01/rdf-schema#comment")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2002/07/owl#equivalentClass")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2000/01/rdf-schema#subClassOf")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2000/01/rdf-schema#domain")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2000/01/rdf-schema#range")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2000/01/rdf-schema#subPropertyOf")) {
            hasValue = true;
        }

        if (jo.has("http://www.w3.org/2002/07/owl#inverseOf")) {
            hasValue = true;
        }

        return hasValue;
    }

    public void checkSpecificOntologyObjects(String objectUrl, JSONObject objectAllData, Context context, Integer objectID) {

        Log.d("speOntObjCheckkk", objectUrl);
        Log.d("speOntObjCheckkk", String.valueOf(objectAllData));
        Log.d("speOntObjCheckkk", String.valueOf(objectID));

        String specificOntologyObjectString = null;
        try {
            specificOntologyObjectString = objectAllData.getString(objectUrl);
        } catch (JSONException e) {
            Log.d("error speOntObjCheck", e.getMessage());
        }


        MyAppDatabase myAppDatabase = MyAppDatabase.getAppDatabase(context);

        try {
                String objectName = objectAllData.getString("@id");

                if(objectName.contains("#")){
                    objectName = gFunctions.separateResponse(objectName);
                }

//////////////////////////////////////////////
                String type = "";
                String typeJson = "";

                try {
                    typeJson = objectAllData.getString("@type");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (typeJson.contains(",")) {
                    String formattedTypeJson = typeJson.replace("\\", "");
                    String[] types = gFunctions.separateDifferentTypes(formattedTypeJson);
                    for (int j = 0; j < types.length; j++) {
                        type = gFunctions.separateType(types[j]);

//                        ObjectData objectData = new ObjectData();
//                        objectData.setObjectId(objectID);
//                        objectData.setModel(type);
//                        objectData.setValue(objectName);
//                        myAppDatabase.MyDao().addObjectData(objectData);
                    }
                } else {
                    type = gFunctions.separateType(typeJson);

//                    ObjectData objectData = new ObjectData();
//                    objectData.setObjectId(objectID);
//                    objectData.setModel(type);
//                    objectData.setValue(objectName);
//                    myAppDatabase.MyDao().addObjectData(objectData);
                }

                String selectedOntology = prefsHandler.getSelectedOntologyFromPrefs(context);
            settingOTO(context,objectName, type, selectedOntology);
            checkIfValuesExists(objectAllData,context,objectId);



////////////////////////////////////////////////////

        } catch (JSONException e) {
            Log.d("error specificOntObj",e.getMessage());
        }

        /*if (specificOntologyObjectString != null) {
            SpecificOntologyObject specificOntologyObject = new SpecificOntologyObject();
            specificOntologyObject.decodingSpecificOntologyObject(context, specificOntologyObjectString, objectID);
        }*/
        ///////////////////////////////////

    }

    public void settingOTO(Context context,String object, String type, String ontology) {
        ObjectTypeOntology oto = new ObjectTypeOntology();
        oto.setObject(object);
        oto.setType(type);
        oto.setOntology(ontology);
        MyAppDatabase myAppDatabase = MyAppDatabase.getAppDatabase(context);

        objectId = (int) (long) myAppDatabase.MyDao().addOTO(oto);

    }

}
