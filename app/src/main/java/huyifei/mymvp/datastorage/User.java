package huyifei.mymvp.datastorage;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by rookie on 2017/2/20.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private long Id;
    private String name;
    private String sex;
    private int age;
    @Generated(hash = 1215615857)
    public User(long Id, String name, String sex, int age) {
        this.Id = Id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public long getId() {
        return this.Id;
    }
    public void setId(long Id) {
        this.Id = Id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
