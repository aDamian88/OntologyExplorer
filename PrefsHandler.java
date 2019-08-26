package gr.therightclick.adam.ontologyexplorer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefsHandler {

    public void putSelectedOntology(Context context, String classStr){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ontology", classStr);
        editor.commit();
    }

    public String getSelectedOntologyFromPrefs(Context context){
        String restoredActivity = "";
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        restoredActivity = prefs.getString("ontology",null);
        return restoredActivity;
    }
}
