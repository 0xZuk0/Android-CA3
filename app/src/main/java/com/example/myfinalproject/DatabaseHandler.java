package com.example.myfinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.sql.Array;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AnimalApp3";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String TABLE_NAME2 = "myreports";
    private static final String TABLE_NAME3 = "myadoption";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "username";
    private static final String KEY_PET_NAME = "pet_name";
    private static final String KEY_PET_AGE = "pet_age";
    private static final String KEY_PET_DESCRIPTION = "pet_description";
    private static final String KEY_PET_LAST_SEEN = "last_seen";
    private static final String KEY_PET_MOBILE = "mobile_number";
    private static final String KEY_PET_FEATURE1 = "feature1";
    private static final String KEY_PET_FEATURE2 = "feature2";
    private static final String KEY_PET_FEATURE3 = "feature3";
    private static final String KEY_PET_FEATURE4 = "feature4";
    private static final String KEY_PET_EMAIL = "email";
    private static final String KEY_PET_ADDRESS = "address";
    private static final String KEY_PET_IMAGE = "pet_image";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, " + KEY_EMAIL + " TEXT, " + KEY_PASSWORD + " TEXT)";

        String createTable2Query = "CREATE TABLE " + TABLE_NAME2 + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_PET_NAME + " TEXT, " + KEY_PET_AGE + " TEXT, " + KEY_PET_DESCRIPTION +
                " TEXT, " + KEY_PET_LAST_SEEN + " TEXT, " + KEY_PET_MOBILE + " TEXT, " + KEY_PET_EMAIL + " TEXT, " + KEY_PET_ADDRESS + " TEXT, " + KEY_PET_IMAGE + " BLOB)";

        String createTable3Query = "CREATE TABLE " + TABLE_NAME3 + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_PET_NAME + " TEXT, " + KEY_PET_AGE + " TEXT, " + KEY_PET_DESCRIPTION +
                " TEXT, " + KEY_PET_FEATURE1 + " TEXT, " + KEY_PET_FEATURE2 + " TEXT, " + KEY_PET_FEATURE3 + " TEXT, " +  KEY_PET_FEATURE4 + " TEXT, " + KEY_PET_MOBILE + " TEXT, " + KEY_PET_EMAIL + " TEXT, " + KEY_PET_ADDRESS + " TEXT, " + KEY_PET_IMAGE + " BLOB)";
        sqLiteDatabase.execSQL(createTable2Query);
        sqLiteDatabase.execSQL(createTableQuery);
        sqLiteDatabase.execSQL(createTable3Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
            String dropTable2Query = "DROP TABLE IF EXISTS " + TABLE_NAME2;

        onCreate(sqLiteDatabase);
    }

    public boolean addUser(String username, String email, String password) {

        if(getUser(email) == null) {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_NAME, username);
            contentValues.put(KEY_EMAIL, email);
            contentValues.put(KEY_PASSWORD, password);
            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
            sqLiteDatabase.close();
            return true;
        }
        return false;
    }

    public Users getUser(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] tableColumns = {KEY_ID, KEY_NAME, KEY_EMAIL, KEY_PASSWORD};
        String[] whereArgs = {email};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, tableColumns, KEY_EMAIL + "=?", whereArgs, null, null, null, null);
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        Users user = new Users(cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return user;
    }

    public boolean saveReport(String name, int age, String description, String lastSeen, String mobile, String address, String email, byte[] image) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String[] tableColumns = {KEY_ID, KEY_PET_NAME, KEY_PET_AGE, KEY_PET_DESCRIPTION, KEY_PET_LAST_SEEN, KEY_PET_MOBILE, KEY_PET_EMAIL, KEY_PET_ADDRESS, KEY_PET_IMAGE};
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PET_NAME, name);
        contentValues.put(KEY_PET_AGE, age);
        contentValues.put(KEY_PET_DESCRIPTION, description);
        contentValues.put(KEY_PET_LAST_SEEN, lastSeen);
        contentValues.put(KEY_PET_MOBILE, mobile);
        contentValues.put(KEY_PET_EMAIL, email);
        contentValues.put(KEY_PET_ADDRESS, address);
        contentValues.put(KEY_PET_IMAGE, image);

        if( sqLiteDatabase.insert(TABLE_NAME2, null, contentValues) != -1) {
            return true;
        }
        return false;

    }

    public boolean saveAdoption(String name, int age, String description, String[] features, String mobile, String address, String email, byte[] image) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String[] tableColumns = {KEY_ID, KEY_PET_NAME, KEY_PET_AGE, KEY_PET_DESCRIPTION, KEY_PET_LAST_SEEN, KEY_PET_MOBILE, KEY_PET_EMAIL, KEY_PET_ADDRESS, KEY_PET_IMAGE};
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PET_NAME, name);
        contentValues.put(KEY_PET_AGE, age);
        contentValues.put(KEY_PET_DESCRIPTION, description);
        contentValues.put(KEY_PET_FEATURE1, features[0]);
        contentValues.put(KEY_PET_FEATURE2, features[1]);
        contentValues.put(KEY_PET_FEATURE3, features[2]);
        contentValues.put(KEY_PET_FEATURE4, features[3]);
        contentValues.put(KEY_PET_MOBILE, mobile);
        contentValues.put(KEY_PET_EMAIL, email);
        contentValues.put(KEY_PET_ADDRESS, address);
        contentValues.put(KEY_PET_IMAGE, image);

        if( sqLiteDatabase.insert(TABLE_NAME3, null, contentValues) != -1) {
            return true;
        }
        return false;

    }

    public void showUsersTable() {
        /*
            This Method is for Debug Purpose.
         */
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] tableColumns = {KEY_ID, KEY_NAME, KEY_EMAIL, KEY_PASSWORD};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        try {
            if(cursor.moveToFirst()) {
                do {
                    Log.d("USER TABLE", cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + "\n");
                }
                while(cursor.moveToNext());
            }
        } finally {
            try {
                cursor.close();
            }
            catch (Exception ignore) {}
        }
        sqLiteDatabase.close();
    }


    public ArrayList<MissingPet> getAllMissingReport() {
        ArrayList<MissingPet> missingPets = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] tableColumns = {KEY_ID, KEY_PET_NAME, KEY_PET_AGE, KEY_PET_DESCRIPTION, KEY_PET_LAST_SEEN, KEY_PET_MOBILE, KEY_PET_EMAIL, KEY_PET_ADDRESS, KEY_PET_IMAGE};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME2, null);
        try {
            if(cursor.moveToFirst()) {
                do {
                    byte[] image = cursor.getBlob(8);
                    MissingPet missingPet = new MissingPet(cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), image);
                    missingPets.add(missingPet);
                }
                while(cursor.moveToNext());
            }
        } finally {
            try {
                cursor.close();
            }
            catch (Exception ignore) {}
        }
        sqLiteDatabase.close();
        return missingPets;
    }

    public ArrayList<AdoptionPet> getAllAdoptionPets() {
        ArrayList<AdoptionPet> adoptionPets = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] tableColumns = {KEY_ID, KEY_PET_NAME, KEY_PET_AGE, KEY_PET_DESCRIPTION, KEY_PET_FEATURE1, KEY_PET_FEATURE2, KEY_PET_FEATURE3, KEY_PET_FEATURE4, KEY_PET_MOBILE, KEY_PET_EMAIL, KEY_PET_ADDRESS, KEY_PET_IMAGE};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME3, null);
        try {
            if(cursor.moveToFirst()) {
                do {
                    byte[] image = cursor.getBlob(11);
                    String[] features = new String[]{
                            cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7)
                    };
                    AdoptionPet adoptionPet = new AdoptionPet(cursor.getString(1), cursor.getInt(2), cursor.getString(3), features, cursor.getString(8), cursor.getString(9), cursor.getString(10), image);
                    adoptionPets.add(adoptionPet);
                }
                while(cursor.moveToNext());
            }
        } finally {
            try {
                cursor.close();
            }
            catch (Exception ignore) {}
        }
        sqLiteDatabase.close();
        return adoptionPets;
    }

    public void showReportTable() {
        /*
            This Method is for Debug Purpose.
         */
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] tableColumns = {KEY_ID, KEY_PET_NAME, KEY_PET_AGE, KEY_PET_DESCRIPTION, KEY_PET_LAST_SEEN, KEY_PET_MOBILE, KEY_PET_EMAIL, KEY_PET_ADDRESS, KEY_PET_IMAGE};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME2, null);
        try {
            if(cursor.moveToFirst()) {
                do {
                    byte[] image = cursor.getBlob(8);
                    Log.d("TABLE 1", cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5)
                    + cursor.getString(6) + " " + cursor.getString(7) + " " + image.length + "\n");
                }
                while(cursor.moveToNext());
            }
        } finally {
            try {
                cursor.close();
            }
            catch (Exception ignore) {}
        }
        sqLiteDatabase.close();
    }

    public void showAdoptionTable() {
        /*
            This Method is for Debug Purpose.
         */
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String[] tableColumns = {KEY_ID, KEY_PET_NAME, KEY_PET_AGE, KEY_PET_DESCRIPTION, KEY_PET_FEATURE1, KEY_PET_FEATURE2, KEY_PET_FEATURE3, KEY_PET_FEATURE4, KEY_PET_MOBILE, KEY_PET_EMAIL, KEY_PET_ADDRESS, KEY_PET_IMAGE};
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME3, null);
        try {
            if(cursor.moveToFirst()) {
                do {
                    byte[] image = cursor.getBlob(11);
                    Log.d("TABLE 3", cursor.getString(1) + " " + cursor.getString(2) + " " + cursor.getString(3) + " " + cursor.getString(4) + " " + cursor.getString(5)
                            + cursor.getString(6) + " " + cursor.getString(7) + " " + cursor.getString(8) + " " + cursor.getString(9) + " " + cursor.getString(10) + " " + image.length + "\n");
                }
                while(cursor.moveToNext());
            }
        } finally {
            try {
                cursor.close();
            }
            catch (Exception ignore) {}
        }
        sqLiteDatabase.close();
    }

    public void cleanTable1() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, null, null);
    }
    public void cleanTable2() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME2, null, null);
    }
    public void cleanTable3() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME3, null, null);
    }

}
