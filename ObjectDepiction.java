package gr.therightclick.adam.ontologyexplorer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;

public class ObjectDepiction extends AppCompatActivity {

    MyAppDatabase myAppDatabase;
    ListView objectList;
    PrefsHandler pHandler = new PrefsHandler();
    String[] data;
    String selectedOntology;
    TextView tvTitle, tvOntology;
    CardView cardBack, cardHome, cardOntology, cardSearch;
    GeneralFunctions gFunctions = new GeneralFunctions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_depiction);

        tvTitle = (TextView) findViewById(R.id.tv_title);

        objectList = (ListView) findViewById(R.id.objectList);
        selectedOntology = pHandler.getSelectedOntologyFromPrefs(this);

        Intent intent = getIntent();
        String help = (String) intent.getSerializableExtra("objectId");
        Log.d("objectString", String.valueOf(help));

        final Integer objectId = Integer.valueOf(help);

        Log.d("objectInt", String.valueOf(objectId));

        myAppDatabase = MyAppDatabase.getAppDatabase(this);

        data = initList(objectId, null);

        String objectName = null;
        try {
            objectName = myAppDatabase.MyDao().getObjectNameFromId(objectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (objectName != null) {
            tvTitle.setText(objectName);
        } else {
            tvTitle.setText(" ");
        }

        // Add data to the ListView subscription plans on dialog
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data);
        objectList.setAdapter(adapter);

        cardBack = (CardView) findViewById(R.id.cardBack);
        cardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        cardHome = (CardView) findViewById(R.id.cardHome);
        cardHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ObjectDepiction.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
        tvOntology = (TextView) findViewById(R.id.tv_ontology);
        if (selectedOntology != null) {
            tvOntology.setText(selectedOntology);
        }
        cardOntology = (CardView) findViewById(R.id.cardOntology);
        cardOntology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ObjectDepiction.this, OntologyData.class);
                startActivity(myIntent);
            }
        });
        cardSearch = (CardView) findViewById(R.id.cardSearch);
        cardSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> helpL = myAppDatabase.MyDao().getObjectsFromOntology( selectedOntology);

                // Unique values
                ArrayList<String> helpList = new ArrayList<>();
                for(String str: helpL){
                    if(!helpList.contains(str)) helpList.add(str);
                }

                new SimpleSearchDialogCompat(ObjectDepiction.this, selectedOntology+" ontology search", "Please type your value.", null, gFunctions.createSampleData(helpList), new SearchResultListener<SampleSearchModel>() {
                    @Override
                    public void onSelected(BaseSearchDialogCompat dialog, SampleSearchModel item, int position) {

                        String strName = item.getTitle();
                        Integer objectId = myAppDatabase.MyDao().getObjectIdFromName(strName,selectedOntology);
                        Log.d("objectIdAdapter", String.valueOf(objectId));

                        Intent myIntent = new Intent(ObjectDepiction.this, ObjectDepiction.class);
                        myIntent.putExtra("objectId", String.valueOf(objectId));
                        startActivity(myIntent);

                    }
                }).show();
            }
        });

        try {
            objectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String strHelp = adapter.getItem(position);
                    String[] help = strHelp.split(" ");

                    String strName = help[1];
                    String objectType = help[0];

                    Integer objId = myAppDatabase.MyDao().getObjectIdFromName(strName, selectedOntology);

                    if(objId!=null) {
                        Intent myIntent = new Intent(ObjectDepiction.this, ObjectDepiction.class);
                        myIntent.putExtra("objectId", String.valueOf(objId));///////////////////////////////////////////////////////////////////////
                        startActivity(myIntent);
                    }

                }
            });
        } catch (Exception e) {
            Log.d("error objectList", e.getMessage());
        }
    }

    public String[] initList(Integer objectId, String model) {
        List<ObjectData> objectDataList = null;
        Log.d("initListt", "id " + String.valueOf(objectId) + "." + "model" + String.valueOf(model));
        if (model != null) {
            Log.d("model", String.valueOf(model.trim()) + ".");
        }
        if (model == null) {
            objectDataList = myAppDatabase.MyDao().getObjectDataByObjectId(objectId);
            Log.d("objectList1", String.valueOf(objectDataList));
        } else {
            tvTitle.setText(model);
            Integer objId = myAppDatabase.MyDao().getObjectIdFromName(model, selectedOntology);
            objectDataList = myAppDatabase.MyDao().getObjectDataByObjectId(objId);
//            objectDataList = myAppDatabase.MyDao().getObjectIdFromName(model.trim(),selectedOntology);
            Log.d("objectList2", String.valueOf(objectDataList));
        }

        if (objectDataList != null) {

            data = new String[objectDataList.size()];
            int j = 0;
            for (ObjectData object : objectDataList) {
                data[j] = object.getModel() + ": " + object.getValue();
                Log.d("ooobject", String.valueOf(j) + " " + object.getModel() + " " + object.getValue());
                j++;
            }
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, data);
        objectList.setAdapter(adapter);

        return data;
    }

}
