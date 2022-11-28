package com.selio30.tfc.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.selio30.tfc.db.Database;

public class DatabaseViewModel extends ViewModel {
    private Database database;

    public Database getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, Database.class, "database.db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return database;
    }
}
