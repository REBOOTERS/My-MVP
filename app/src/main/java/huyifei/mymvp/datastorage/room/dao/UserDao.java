package huyifei.mymvp.datastorage.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import huyifei.mymvp.datastorage.room.entity.UserEntity;
import io.reactivex.Flowable;

/**
 * @version V1.0
 * @author: zhuyongging
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
