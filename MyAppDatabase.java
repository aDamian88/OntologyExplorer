package gr.therightclick.adam.ontologyexplorer;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by adam2 on 11/5/2018.
 */
@Database(entities = {ObjectTypeOntology.class,ObjectData.class,Ontology.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {

    private static MyAppDatabase INSTANCE;

    public abstract MyDao MyDao();

    public static MyAppDatabase getAppDatabaseFallBack(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyAppDatabase.class, "userdb").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    public static MyAppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyAppDatabase.class, "userdb").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public static MyAppDatabase getAssetDatabase(Context context){

        INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyAppDatabase.class, "userdb")
                .build();

        return INSTANCE;
    }
}
