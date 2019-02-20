package co.zemoga.www.zemogatest.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import co.zemoga.www.zemogatest.database.dao.PostDao;
import co.zemoga.www.zemogatest.database.entities.Post;

@Database(entities = {Post.class}, version = 1, exportSchema = false)
public abstract class ZemogaDatabase extends RoomDatabase {

    public final static String DATABASE_NAME = "zemogaDb.db";

    public abstract PostDao postDao();
}
