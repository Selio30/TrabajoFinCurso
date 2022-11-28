package com.selio30.tfc.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.selio30.tfc.entity.Formato;

import java.util.List;

@Dao
public interface FormatoDAO {
    @Insert
    public void insert(Formato formato);

    @Insert
    public void insertAll(List<Formato> formatoList);

    @Query("SELECT * FROM formato")
    List<Formato> getAll();

    @Query("SELECT * FROM formato WHERE id_Formato = :id limit 1")
    Formato getById(String id);
}
