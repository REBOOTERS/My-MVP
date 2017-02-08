package huyifei.mymvp.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import huyifei.mymvp.datastorage.DataBean;


/**
 * Created by rookie on 2017/2/7.
 */

public class MySqliteHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "mvp.db";
    private static final String TABLE_NAME = "only";
    private static final String CREATE_DATABASE =
            "CREATE TABLE " + TABLE_NAME + "(content text,times text);";

    private Context mContext;

    public MySqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DATABASE);
    }

    /**
     * 增加
     *
     * @param contentValues
     */
    public void insertData(ContentValues contentValues) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long tag = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (tag == -1) {
            Toast.makeText(mContext, "insert fail ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "insert success ", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 查找全部
     *
     * @return
     */
    public List<DataBean> queryAll() {
        List<DataBean> lists = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataBean bean = new DataBean();
            bean.setContent(cursor.getString(cursor.getColumnIndex("content")));
            bean.setTimes(cursor.getString(cursor.getColumnIndex("times")));
            lists.add(bean);
            cursor.moveToNext();
        }
        cursor.close();
        return lists;
    }

    /**
     * 查找特定值
     * @param selection
     * @param selectionArgs
     * @return
     */
    public DataBean query(String selection,String[] selectionArgs) {
        DataBean bean=new DataBean();
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            bean.setTimes(cursor.getString(cursor.getColumnIndex("times")));
            bean.setContent(cursor.getString(cursor.getColumnIndex("content")));
            cursor.moveToNext();
        }
        return bean;
    }

    /**
     * 删除全部
     * @return
     */
    public boolean delAll() {
        boolean result = false;
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int flag = sqLiteDatabase.delete(TABLE_NAME, null, null);
        result = flag == 0 ? false : true;
        return result;
    }

    public boolean del(String whereClause,String[] whereArgs){
        boolean result=false;
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        int flag = sqLiteDatabase.delete(TABLE_NAME, whereClause, whereArgs);
        result =flag==0 ? false: true;
        return  result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
