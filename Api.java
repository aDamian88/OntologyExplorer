package gr.therightclick.adam.ontologyexplorer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface Api {

    @GET
    Call<JsonObject> getResponse(@Url String url);

    @GET
    Call<JsonArray> getOntology(@Url String url);
}
