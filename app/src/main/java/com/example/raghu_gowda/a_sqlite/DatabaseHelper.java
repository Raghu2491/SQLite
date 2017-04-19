package com.example.raghu_gowda.a_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Raghu_Gowda on 12/20/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL1="ID";
    public static final String COL2="NAME";
    public static final String COL3="SURNAME";
    public static final String COL4= "MARKS";

    public DatabaseHelper(Context context) {
        //--- database is created in this constructor
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db =this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME + "(" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL2 + " TEXT, " + COL3 + " TEXT, "+ COL4 + " INTEGER)";

        System.out.println("----------> "+query );
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData (String name, String surname, String marks){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,surname);
        contentValues.put(COL4,marks);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;

    }
}
