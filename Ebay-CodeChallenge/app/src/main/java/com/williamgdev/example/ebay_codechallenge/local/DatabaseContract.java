package com.williamgdev.example.ebay_codechallenge.local;

import android.provider.BaseColumns;

public final class DatabaseContract {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "users.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String COMMA_SEP          = ",";

    private DatabaseContract() {}

    public static abstract class UsersTable implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String USER_ID = "id";
        public static final String USER_NAME = "userName";


        public static final String SCRIPT_CREATE_TABLE_USERS = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                USER_ID + TEXT_TYPE + COMMA_SEP +
                USER_NAME + TEXT_TYPE + " )";
        public static final String SCRIPT_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}