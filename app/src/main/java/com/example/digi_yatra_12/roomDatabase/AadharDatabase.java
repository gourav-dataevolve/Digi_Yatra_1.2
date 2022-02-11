package com.example.digi_yatra_12.roomDatabase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {AAdharData.class, ConnectionDB.class}, version = 1)
@TypeConverters(TypeConverter.class)
public abstract class AadharDatabase extends RoomDatabase {

    private static AadharDatabase instance;
    public abstract Dao Dao();

    public static synchronized AadharDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AadharDatabase.class, "aadhar_data")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                    //.allowMainThreadQueries()
                            .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(AadharDatabase instance) {
            Dao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            Log.d("task completed","yes");
            super.onPostExecute(unused);
        }
    }
}
