package gr.therightclick.adam.ontologyexplorer;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Call<JsonArray> call;
    Call<JsonArray> call2;
    AlertDialog dialog;
//    String query1 = "bins/t2ofw";
    String query1 = "bins/14u1dt";
    String query2 = "bins/ulhfy";
    String query3 = "bins/1ecqmm";
    String query4 = "bins/uafji";
    String[] queries = new String[4];
    Button execute;
    String choose = query1;
    ProgressDialog progressDialog;
    MyAppDatabase myAppDatabase;
    String ontologySelection;
    PrefsHandler pHandler = new PrefsHandler();
    DecodingResponse decodingResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAppDatabase = MyAppDatabase.getAppDatabaseFallBack(MainActivity.this);
//        myAppDatabase = MyAppDatabase.getAssetDatabase(MainActivity.this);
        progressDialog = new ProgressDialog(this);

        execute = (Button) findViewById(R.id.button);
        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callSparql(choose);
                Integer exists = myAppDatabase.MyDao().checkIfOntologyExists(ontologySelection);
                if(exists==0) {
                    progressDialog.setTitle("Please wait");
                    progressDialog.setMessage("Syncing ontology");
                    progressDialog.show();
                    Ontology ontology = new Ontology();
                    ontology.setOntologyName(ontologySelection);
                    myAppDatabase.MyDao().addOntology(ontology);
                    callOntology(choose);
                }else{
                    pHandler.putSelectedOntology(MainActivity.this,ontologySelection);
                    Intent myIntent = new Intent(MainActivity.this, OntologyData.class);
                    startActivity(myIntent);
                }
            }
        });

        queries[0] = "Wine";
        queries[1] = "People";
        queries[2] = "Travel";
        queries[3] = "Pizza";

        SearchableSpinner dropdownStr = findViewById(R.id.spnQueries);
        ArrayAdapter<String> adapterStr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, queries); //queries
        dropdownStr.setAdapter(adapterStr);
        dropdownStr.setTitle("Select an ontology");
        try {
            dropdownStr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                    String chosenQuery = parentView.getSelectedItem().toString();
                    if(chosenQuery.equals("Wine")){
                        choose = query1;
                        ontologySelection="Wine";
                    }else if(chosenQuery.equals("People")){
                        choose = query2;
                        ontologySelection="People";
                    }else if(chosenQuery.equals("Travel")){
                        choose = query3;
                        ontologySelection="Travel";
                    }else if(chosenQuery.equals("Pizza")){
                        choose = query4;
                        ontologySelection="Pizza";
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        } catch (Exception e) {
            Log.d("Error dropdownStr", e.getMessage());
        }

    }

    /// Making call to myjson.com
    public void callOntology(String query){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.myjson.com/").addConverterFactory(GsonConverterFactory.create()).build();
        final Api api = retrofit.create(Api.class);

        call = api.getOntology(query);
        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                decodingResponse = new DecodingResponse(MainActivity.this,String.valueOf(response.body()),ontologySelection);
                progressDialog.dismiss();
                Intent myIntent = new Intent(MainActivity.this, OntologyData.class);
                startActivity(myIntent);
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
                Log.d("TAG_ERROR", t.getMessage());
            }
        });
    }

}
