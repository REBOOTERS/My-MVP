package huyifei.mymvp.datastorage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Publisher;

import java.util.List;

import huyifei.mymvp.R;
import huyifei.mymvp.datastorage.room.UserDataBase;
import huyifei.mymvp.datastorage.room.entity.UserEntity;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RoomActivity extends AppCompatActivity {

    private static final String TAG = "RoomActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        Context context = this;

        findViewById(R.id.add).setOnClickListener(v -> {

            Observable.just(new UserEntity("mike", "beijing"))
                    .subscribeOn(Schedulers.io())
                    .subscribe(userEntity -> UserDataBase.getINSTANCE(context).getUserDao().addUser(userEntity));
        });


        findViewById(R.id.getall).setOnClickListener(v -> {
            UserDataBase.getINSTANCE(context).getUserDao().getAllAsync()
                    .subscribe(userEntities -> {
                        Log.e(TAG, "accept: users ="+userEntities.size() );
                        for (UserEntity userEntity : userEntities) {
                            Log.e(TAG, "accept: name=="+userEntity.getName());
                            Log.e(TAG, "accept: address=="+userEntity.getAddress());
                            Log.e(TAG, "accept: uid=="+userEntity.getUid());
                        }
                    });
        });


    }
}
