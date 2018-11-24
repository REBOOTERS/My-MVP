package huyifei.mymvp.datastorage.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * @version V1.0
 * @author: Rookie
 * @date: 2018-08-04 09:24
 */
@Entity(tableName = "user")
public class UserEntity {


    @PrimaryKey(autoGenerate = true)
    private int uid;

    private String name;
    private String address;

    public UserEntity(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
