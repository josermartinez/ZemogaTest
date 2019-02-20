package co.zemoga.www.zemogatest.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import co.zemoga.www.zemogatest.database.entities.Post;

@Dao
public interface PostDao extends BaseDao<Post> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @Override
    void insertAll(List<Post> entitiesToInsert);

    @Query("SELECT * FROM Post")
    LiveData<List<Post>> getPostList();

    @Query("SELECT * FROM Post "
            + "WHERE Post.favorite = 1")
    LiveData<List<Post>> getFavoritePostList();

    @Query("Update Post SET favorite = :favorite "
            + "WHERE id = :postId")
    void updateFavoriteState(boolean favorite, int postId);

    @Query("Update Post SET read = 1 "
            + "WHERE id = :postId")
    void updateReadState(int postId);

    @Query("DELETE FROM Post "
            + "WHERE id = :postId")
    void deleteItem(int postId);

    @Query("DELETE FROM Post")
    void deleteAll();

    @Query("UPDATE POST Set read = 0 "
            + "WHERE id <= :i ")
    void changeReadState(int i);
}
