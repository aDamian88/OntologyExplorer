package gr.therightclick.adam.ontologyexplorer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ObjectTypeOntology")
public class ObjectTypeOntology {

    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "oto_object")
    String object;
    @ColumnInfo(name = "oto_type")
    String type;
    @ColumnInfo(name = "oto_ontology")
    String ontology;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOntology() {
        return ontology;
    }

    public void setOntology(String ontology) {
        this.ontology = ontology;
    }
}
