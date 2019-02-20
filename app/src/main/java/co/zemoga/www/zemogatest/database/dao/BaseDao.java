package co.zemoga.www.zemogatest.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BaseDao<T> {

    @Insert
    void insert(T... entityToInsert);

    @Insert
    void insertAll(List<T> entitiesToInsert);

    @Insert
    void insertAll(T[] entitiesToInsert);

    @Update
    void update(T... entityToInsert);

    @Update
    void updateAll(List<T> entitiesToInsert);

    @Update
    void updateAll(T[] entitiesToInsert);

    @Delete
    void delete(T entityToDelete);

    @Delete
    void delete(List<T> entitiesToDelete);

    @Delete
    void delete(T[] entitiesToDelete);
}
