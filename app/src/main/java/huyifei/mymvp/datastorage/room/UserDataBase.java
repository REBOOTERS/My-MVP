package huyifei.mymvp.datastorage.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import huyifei.mymvp.datastorage.room.dao.UserDao;
import huyifei.mymvp.datastorage.room.entity.UserEntity;

/**
 * @version V1.0
 * @author: Rookie
 * @date: 2018-08-04 09:31
 */
@Database(entities = {UserEntity.class},version = 1,exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {

    private static UserDataBase INSTANCE;

    public static UserDataBase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),UserDataBase.class,"user.db").build();
        }
        return INSTANCE;
    }

    public static void onDestory(){
        INSTANCE = null;
    }

    public abstract UserDao getUserDao();
}
