package com.selio30.tfc.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.selio30.tfc.entity.Tipo;

import java.util.List;

@Dao
public interface TipoDAO {
    @Insert
    public void insert(Tipo tipo);

    @Insert
    public void insertAll(List<Tipo> tipoList);

    @Query("SELECT * FROM tipo")
    List<Tipo> getAll();

    @Query("SELECT * FROM tipo WHERE id_Tipo = :id limit 1")
    Tipo getById(String id);
}
