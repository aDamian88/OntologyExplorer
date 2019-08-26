package gr.therightclick.adam.ontologyexplorer;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GeneralFunctions {

    public String separateResponse(String response){
        String help;
        String[] seperated ={"init","init"};
        if(response.contains("#")) {
            seperated = response.split("#");
        }

        if(seperated[1].equals("init")){
            help =seperated[0];
        }else{
            help = seperated[1];
        }
        return help;
    }

    public String separateType(String typeJson){
        String[] helpType = typeJson.split("#");
        String[] helpType2 = helpType[1].split("\\\"");
        return helpType2[0];
    }

    public String[] separateDifferentTypes(String types){
        String[] helpType = types.split(",");
        return helpType;
    }

    public ArrayList<SampleSearchModel> createSampleData(List<String> helpList) {
        ArrayList<SampleSearchModel> items = new ArrayList<>();
        for (String help : helpList) {
            items.add(new SampleSearchModel(help));

        }
        return items;
    }


}
