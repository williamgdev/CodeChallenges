package com.williamgdev.example.ebay_codechallenge;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.williamgdev.example.ebay_codechallenge.local.DatabaseContract;
import com.williamgdev.example.ebay_codechallenge.local.UsersDataHelper;
import com.williamgdev.example.ebay_codechallenge.network.RestApi;
import com.williamgdev.example.ebay_codechallenge.network.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "CodeChallenge =>";
    private SQLiteDatabase db;
    private UsersDataHelper dataHelper;
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView = findViewById(R.id.txt_view);

        dataHelper = new UsersDataHelper(this);
        db = dataHelper.getWritableDatabase();

        RestApi.getInstance().getUsers(new RestApi.ApiListener<List<User>>() {
            @Override
            public void onSuccess(List<User> result) {
                saveUsers(result);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveUsers(List<User> result) {
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (User user : result) {
                values.put(DatabaseContract.UsersTable.USER_ID, user.getUid());
                values.put(DatabaseContract.UsersTable.USER_NAME, user.getName() + " " + user.getLastName());
                db.insert(DatabaseContract.UsersTable.TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        updateUI();
    }

    private void updateUI() {
        printUsers();
    }

    private void printUsers() {
        Cursor cursor = db.query(
                DatabaseContract.UsersTable.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            Log.d(TAG, "onCreate: Cursor Empty");
            return;
        }
        do {
            String username = cursor.getString(cursor.getColumnIndex(DatabaseContract.UsersTable.USER_NAME));
            txtView.setText(txtView.getText() + "\n" + username);
        } while (cursor.moveToNext());
        cursor.close();
    }
}
