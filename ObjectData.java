package gr.therightclick.adam.ontologyexplorer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "ObjectData")
public class ObjectData {

    @PrimaryKey(autoGenerate = true)
    Integer id;
    @ColumnInfo(name = "object_data_object_id")
    Integer objectId;
    @ColumnInfo(name = "object_data_model")
    String model;
    @ColumnInfo(name = "object_data_value")
    String value;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
