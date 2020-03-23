package huyifei.mymvp.datastorage.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import huyifei.mymvp.datastorage.room.entity.UserEntity;
import io.reactivex.Flowable;

/**
 * @version V1.0
 * @author: Rookie
 * @date: 2018-08-04 09:26
 */

@Dao
public interface UserDao {


    @Query("select * from user")
    List<UserEntity> getAll();

    @Query("select * from user")
    Flowable<List<UserEntity>> getAllAsync();


    @Query("select * from user where name = :name")
    UserEntity getUserByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(UserEntity userEntity);

    @Delete
    void delete(UserEntity userEntity);
}
