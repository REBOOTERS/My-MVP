package huyifei.mymvp.datastorage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import huyifei.mymvp.R;
import huyifei.mymvp.util.MySqliteHelper;

public class DataStorageActivity extends AppCompatActivity {
    private Context mContext;
    //View
    private CheckBox mCheckBox;
    private EditText mEditText;
    private Button write, read;
    private TextView content;
    private Button insert, getall, delall, get, del, greendao;
    private TextView resultTv;
    private Button getContacts;
    private ListView mListView;
    //
    private SharedPreferences sp;
    private final String SP_NAME = "only";
    //
    private String FILENAME = "ONLY.txt";
    //Sqlite
    private MySqliteHelper sqHelper;
    //ContentResolver
    private ContentResolver mContentResolver;
    private List<String> datas = new ArrayList<>();

    private final String CONTACTS = android.Manifest.permission.READ_CONTACTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_data_storage);
        initView();
        initSetup();

        //
        sqHelper = new MySqliteHelper(mContext);

        FILENAME.toCharArray();
    }

    private void initSetup() {
        sp = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        if (sp != null) {
            boolean isChecked = sp.getBoolean("checked", false);
            mCheckBox.setChecked(isChecked);
            if (isChecked) {
                Toast.makeText(mContext, "Last time checked", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "Last time not checked", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void initView() {
        mCheckBox = findViewById(R.id.checkBox);

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sp.edit().putBoolean("checked", isChecked).apply();
            }
        });

        ///
        mEditText=findViewById(R.id.et);
        write = findViewById(R.id.write);
        read = findViewById(R.id.read);
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEditText.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    try {
                        FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                        fos.write(content.getBytes());
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        content = findViewById(R.id.content);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.setText("");
                String str = "";
                try {
                    FileInputStream fis = openFileInput(FILENAME);
                    int hasRead = 0;
                    byte[] bytes = new byte[1024];
                    while ((hasRead = fis.read(bytes)) != -1) {
                        str = new String(bytes, 0, hasRead);
                    }
                    content.setText("");
                    content.setText(str);
                    fis.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //

        resultTv = findViewById(R.id.resultTv);
        insert = findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(mEditText.getText().toString())) {
                    ContentValues values = new ContentValues();
                    values.put("content", mEditText.getText().toString());
                    String date =
                            Calendar.getInstance(Locale.CHINA).get(Calendar.MONTH) + "-" +
                                    Calendar.getInstance(Locale.CHINA).get(Calendar.DAY_OF_MONTH) + " " +
                                    Calendar.getInstance(Locale.CHINA).get(Calendar.HOUR_OF_DAY) + ":" +
                                    Calendar.getInstance(Locale.CHINA).get(Calendar.MINUTE);
                    values.put("times", date);
                    sqHelper.insertData(values);
                } else {
                    Toast.makeText(mContext, "can not null", Toast.LENGTH_SHORT).show();
                }


            }
        });

        getall = findViewById(R.id.getall);

        getall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<DataBean> dataBeanList = sqHelper.queryAll();
                if (dataBeanList != null) {
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < dataBeanList.size(); i++) {
                        sb.append(dataBeanList.get(i).getContent() + " " + dataBeanList.get(i).getTimes() + "\n");
                    }
                    resultTv.setText(sb.toString());
                }
            }
        });

        delall = findViewById(R.id.delall);
        delall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sqHelper.delAll()) {
                    Toast.makeText(mContext, "del success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "del fail", Toast.LENGTH_SHORT).show();

                }
            }
        });

        get = findViewById(R.id.get);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selections = "content= ?";
                String temp = mEditText.getText().toString();
                String[] selectionsArgs = new String[]{temp};
                DataBean bean = sqHelper.query(selections, selectionsArgs);
                if (bean != null) {
                    resultTv.setText(bean.getContent() + " " + bean.getTimes());
                }
            }
        });

        del = findViewById(R.id.del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String whereClause = "content=?";
                String temp = mEditText.getText().toString();
                if (!TextUtils.isEmpty(temp)) {
                    String[] whereArgs = new String[]{temp};
                    if (sqHelper.del(whereClause, whereArgs)) {
                        Toast.makeText(mContext, "del success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "del fail", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        mListView = findViewById(R.id.listView);
        getContacts = findViewById(R.id.getContacts);

        getContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PermissionChecker.checkSelfPermission(mContext, CONTACTS) == PermissionChecker.PERMISSION_GRANTED) {
                    readContacts();
                } else {
                    ActivityCompat.requestPermissions(DataStorageActivity.this, new String[]{CONTACTS}, 100);
                }
            }
        });

        findViewById(R.id.room).setOnClickListener(v -> startActivity(new Intent(mContext,RoomActivity.class)));


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                readContacts();
            } else {
                Toast.makeText(mContext, "Permission denied !", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void readContacts() {
        mContentResolver = getContentResolver();
        //联系人URI
        Uri contactsUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = mContentResolver.query(contactsUri, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER));
            datas.add(name + "----" + phone);
        }
        cursor.close();
        ArrayAdapter arrayAdapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, datas);
        mListView.setAdapter(arrayAdapter);
    }
}
