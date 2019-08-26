package gr.therightclick.adam.ontologyexplorer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Ontology")
public class Ontology {

    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "ontology_name")
    String ontologyName;
    @ColumnInfo(name = "ontology_url")
    String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOntologyName() {
        return ontologyName;
    }

    public void setOntologyName(String ontologyName) {
        this.ontologyName = ontologyName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
