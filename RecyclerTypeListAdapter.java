package gr.therightclick.adam.ontologyexplorer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;
import ir.mirrajabi.searchdialog.core.Searchable;


public class RecyclerTypeListAdapter extends RecyclerView.Adapter<RecyclerTypeListAdapter.ViewHolder> {

    private List<CardItem> cardList;
    private Context context;
    AlertDialog dialog;
    MyAppDatabase myAppDatabase = MyAppDatabase.getAppDatabase(context);
    PrefsHandler pHandler = new PrefsHandler();
    GeneralFunctions gFunctions = new GeneralFunctions();

    public RecyclerTypeListAdapter(List<CardItem> cardList, Context context) {
        this.cardList = cardList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.material_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CardItem cardListItem = cardList.get(position);

        holder.type.setText(cardListItem.getObjectType());

        Log.d("adapterr", "adapterr position " + String.valueOf(position));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("adapterr", "adapterr position2 " + String.valueOf(position));

                final CardItem cardListItem = cardList.get(position);
                final String objectType = cardListItem.getObjectType();
                Toast.makeText(context, " Clicked " + String.valueOf(objectType), Toast.LENGTH_SHORT).show();

                ListView listView = new ListView(context);

                final String selectedOntology = pHandler.getSelectedOntologyFromPrefs(context);

                List<String> helpList = myAppDatabase.MyDao().getObjectsFromType(String.valueOf(cardListItem.getObjectType()), selectedOntology);

                new SimpleSearchDialogCompat(context, selectedOntology+ " ontology "+ objectType, "Please type your value.", null, gFunctions.createSampleData(helpList), new SearchResultListener<SampleSearchModel>() {
                    @Override
                    public void onSelected(BaseSearchDialogCompat dialog, SampleSearchModel item, int position) {

                        String strName = item.getTitle();
                        Integer objectId = myAppDatabase.MyDao().getObjectIdFromName(strName, objectType, selectedOntology);
                        Log.d("objectIdAdapter", String.valueOf(objectId));

                        Intent myIntent = new Intent(context, ObjectDepiction.class);
                        myIntent.putExtra("objectId", String.valueOf(objectId));
                        context.startActivity(myIntent);

                    }
                }).show();
            }

        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView type;
        public LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            type = (TextView) itemView.findViewById(R.id.tv_type);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.types_linear_layout);

        }
    }
}
