package gr.therightclick.adam.ontologyexplorer;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import gr.therightclick.adam.ontologyexplorer.Model.AllValuesFrom;
import gr.therightclick.adam.ontologyexplorer.Model.Cardinality;
import gr.therightclick.adam.ontologyexplorer.Model.DistrictMembers;
import gr.therightclick.adam.ontologyexplorer.Model.Domain;
import gr.therightclick.adam.ontologyexplorer.Model.EquivalentClass;
import gr.therightclick.adam.ontologyexplorer.Model.HasValue;
import gr.therightclick.adam.ontologyexplorer.Model.IntersectionOf;
import gr.therightclick.adam.ontologyexplorer.Model.InverseOf;
import gr.therightclick.adam.ontologyexplorer.Model.MaxCardinality;
import gr.therightclick.adam.ontologyexplorer.Model.MinCardinality;
import gr.therightclick.adam.ontologyexplorer.Model.OneOf;
import gr.therightclick.adam.ontologyexplorer.Model.Property;
import gr.therightclick.adam.ontologyexplorer.Model.Range;
import gr.therightclick.adam.ontologyexplorer.Model.SomeValuesFrom;
import gr.therightclick.adam.ontologyexplorer.Model.SubClassOf;
import gr.therightclick.adam.ontologyexplorer.Model.SubPropertyOf;
import gr.therightclick.adam.ontologyexplorer.Model.UnionOf;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by adam2 on 11/12/2018.
 */
@Dao
public interface MyDao {

    /////////// ObjectTypeOntology ///////////////
    @Insert(onConflict = IGNORE)
    public long addOTO(ObjectTypeOntology oto);

    @Query("select * from ObjectTypeOntology where oto_ontology =:ontology group by oto_type")
    public List<ObjectTypeOntology> getOntologyTypes(String ontology);

    @Query("select oto_object from ObjectTypeOntology where oto_type =:type and oto_ontology=:ontology")
    public List<String> getObjectsFromType(String type,String ontology);

    @Query("select oto_object from ObjectTypeOntology where oto_ontology=:ontology")
    public List<String> getObjectsFromOntology(String ontology);

    @Query("select oto_object from ObjectTypeOntology where oto_ontology=:ontology")
    public List<String> getOntologyObjects(String ontology);

    @Query("select id from ObjectTypeOntology where oto_object=:objectName and oto_type=:objectType and oto_ontology=:ontology")
    public Integer getObjectIdFromName(String objectName, String objectType, String ontology);

    @Query("select id from ObjectTypeOntology where oto_object=:objectName and oto_ontology=:ontology")
    public Integer getObjectIdFromName(String objectName, String ontology);

    @Query("select id from ObjectTypeOntology where oto_type=:objectType and oto_ontology=:ontology")
    public Integer getObjectIdFromType(String objectType, String ontology);

    @Query("select * from ObjectTypeOntology where oto_object=:objectName ")
    public List<ObjectTypeOntology> getOntologyTypesFromObject(String objectName);

    @Query("select oto_object from ObjectTypeOntology where id=:objectId")
    public String getObjectNameFromId(Integer objectId);

    ///////// Object Data ///////////////
    @Insert
    public void addObjectData(ObjectData objectData);

    @Query("select * from ObjectData where object_data_object_id=:objectId")
    public List<ObjectData> getObjectDataByObjectId(Integer objectId);

    @Query("select * from ObjectData where object_data_model=:model")
    public List<ObjectData> getObjectDataByModel(String model);

    @Query("select * from ObjectData where object_data_value=:value")
    public List<ObjectData> getObjectDataByValue(String value);

    @Query("select object_data_value from ObjectData,ObjectTypeOntology where ObjectTypeOntology.oto_ontology=:ontology and ObjectData.object_data_object_id = ObjectTypeOntology.id")
    public List<String> getOntologyObjectValues(String ontology);


    //////// Ontology /////////////
    @Insert
    public void addOntology(Ontology ontology);

    @Query("select count(*) from Ontology where ontology_name=:name")
    public Integer checkIfOntologyExists(String name);

    @Query("select ontology_name from Ontology")
    public List<String> getOntologiesNames();

}
