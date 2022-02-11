package com.example.digi_yatra_12.roomDatabase;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAadharData(AAdharData aAdharData);
    @Query("SELECT * FROM aadhar_data")
    List<AAdharData> getAadharData();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveConnections(ConnectionDB connectionDB);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateConnection(ConnectionDB connectionDB);
    @Query("SELECT * FROM connection_db")
    ConnectionDB getConnectionData();
}
