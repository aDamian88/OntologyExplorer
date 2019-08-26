package gr.therightclick.adam.ontologyexplorer;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;

public class OntologyData extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAppDatabase myAppDatabase;
    TextView title;
    List<CardItem> cardItems;
    RecyclerView.Adapter adapter;
    PrefsHandler pHandler = new PrefsHandler();
    String selectedOntology;
    CardView cardBack,cardHome,cardSearch;
    GeneralFunctions gFunctions = new GeneralFunctions();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ontology_data);

        /////////////////////////////////////////////change to recycler list
        recyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.isClickable();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cardItems = new ArrayList<>();

        myAppDatabase = MyAppDatabase.getAppDatabase(OntologyData.this);
        selectedOntology = pHandler.getSelectedOntologyFromPrefs(this);


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
                Intent myIntent = new Intent(OntologyData.this, MainActivity.class);
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

                new SimpleSearchDialogCompat(OntologyData.this, selectedOntology+" ontology search", "Please type your value.", null, gFunctions.createSampleData(helpList), new SearchResultListener<SampleSearchModel>() {
                    @Override
                    public void onSelected(BaseSearchDialogCompat dialog, SampleSearchModel item, int position) {

                        String strName = item.getTitle();
                        Integer objectId = myAppDatabase.MyDao().getObjectIdFromName(strName,selectedOntology);
                        Log.d("objectIdAdapter", String.valueOf(objectId));

                        Intent myIntent = new Intent(OntologyData.this, ObjectDepiction.class);
                        myIntent.putExtra("objectId", String.valueOf(objectId));
                        startActivity(myIntent);

                    }
                }).show();
            }
        });



        title = (TextView) findViewById(R.id.tv_title);
        title.setText(selectedOntology);

        initOntologyList();

    }

    public void initOntologyList(){

        final List<ObjectTypeOntology> typeList = myAppDatabase.MyDao().getOntologyTypes(selectedOntology);
        String[] types = new String[typeList.size()];

        int i = 0;
        int count = 0;

        for (ObjectTypeOntology oto : typeList) {
            String type = oto.getType();
            if(type.equals("Class") || type.equals("DatatypeProperty") || type.equals("ObjectProperty") || type.equals("NamedIndividual")) {
                types[i] = type;
                i++;
                cardItems.add(new CardItem(type));
            }
        }

//        for (int j = i; j >= 0; j--){ //typeList.size() - 1
//            cardItems.add(new CardItem(types[j]));
//        }

        //creating the adapter
        adapter = new RecyclerTypeListAdapter(cardItems,this);
        recyclerView.setAdapter(adapter);

    }
}
