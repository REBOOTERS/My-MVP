package huyifei.mymvp.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rookie on 2017/8/1.
 */

public class MyMessage implements Parcelable {


    protected MyMessage(Parcel in) {
    }

    public static final Creator<MyMessage> CREATOR = new Creator<MyMessage>() {
        @Override
        public MyMessage createFromParcel(Parcel in) {
            return new MyMessage(in);
        }

        @Override
        public MyMessage[] newArray(int size) {
            return new MyMessage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }


    private static class MyClass {

    }
}
