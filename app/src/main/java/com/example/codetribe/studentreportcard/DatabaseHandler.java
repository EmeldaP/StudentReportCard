package com.example.codetribe.studentreportcard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by codetribe on 2017/07/27.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Card.db";
    public static final String TABLE_NAME = "code";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FULLNAME = "fullName";
    public static final String COLUMN_MARKS = "marks";
    public static final String COLUMN_SUBJECT = "subject";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    //create column into table
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CODE_TABLE = "CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FULLNAME + " TEXT,"
                + COLUMN_SUBJECT + " TEXT,"
                + COLUMN_MARKS + " INTEGER" + ")";
        //pass SQL () objrct to create table with with column
        db.execSQL(CREATE_CODE_TABLE);


    }

    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addLearner(Student student) {
        //create content object and add value keys into into student object
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULLNAME, student.getFullName());
        values.put(COLUMN_SUBJECT, student.getSubject());
        values.put(COLUMN_ID, student.getId());
        values.put(COLUMN_MARKS, student.getMarks());
        SQLiteDatabase db = this.getWritableDatabase();
        //insert() used to insert data into the table
        db.insert(TABLE_NAME, null, values);

        //close db after adding value keys
        db.close();

    }


    public Student searchLearner(String fullName) {
        //select method is used to find all matching record in the table
        String query = " Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_FULLNAME +
                " = \"" + fullName + "\"";




        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();

        //moteToFirst() to move cursor into next row/column
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setId(Integer.parseInt(cursor.getString(0)));
            student.setFullName(cursor.getString(1));
            student.setMarks(cursor.getInt(2));
            student.setSubject(cursor.getString(3));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        return student;
    }


    public boolean deleteLearner(String fullName) {

        boolean result = false;
        String query = " Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_FULLNAME +
                " = \"" + fullName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();

        if (cursor.moveToFirst()) {
            student.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME, COLUMN_FULLNAME + " = ?",
                    new String[]
                            {String.valueOf(student.getId())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public void updateLearner(Student student) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_FULLNAME, student.getFullName());
        values.put(COLUMN_SUBJECT, student.getSubject());
        values.put(COLUMN_ID, student.getId());
        values.put(COLUMN_MARKS, student.getMarks());

        //reference SQLiteDAtabase to writable db
        SQLiteDatabase db = this.getWritableDatabase();

        //referece update
        db.update(TABLE_NAME, values, COLUMN_FULLNAME + "=?", new String[]{
                String.valueOf(student.getFullName())});
        db.close();

    }
}


