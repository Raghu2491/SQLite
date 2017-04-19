package com.example.raghu_gowda.a_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Raghu_Gowda on 12/20/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String ID="ID";
    public static final String NAME="NAME";
    public static final String SURNAME="SURNAME";
    public static final String MARKS= "MARKS";

    public DatabaseHelper(Context context) {
        //--- database is created in this constructor
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db =this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " TEXT, " + SURNAME + " TEXT, "+ MARKS + " INTEGER)";
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
        contentValues.put(NAME,name);
        contentValues.put(SURNAME,surname);
        contentValues.put(MARKS,marks);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if(result==-1)
            return false;
        else
            return true;

    }

    // --------------

    public String getMarks(String name){
        SQLiteDatabase db=this.getReadableDatabase();
        String[] args={name};
        String[] columns={MARKS};

        Cursor cursor= db.query(TABLE_NAME,columns,MARKS + "= ?",args,null,null,null);

        StringBuffer stringBuffer=new StringBuffer();

        while (cursor.moveToNext()){
            int index=cursor.getColumnIndex(NAME);

            stringBuffer.append(cursor.getString(index)+"\n");
        }

        return stringBuffer.toString();
    }

    public int updateMarks(String name,String new_marks){
        String[] args={name};

        ContentValues contentValues=new ContentValues();
        contentValues.put(MARKS,new_marks);

        SQLiteDatabase db=this.getWritableDatabase();

        return db.update(TABLE_NAME,contentValues,MARKS + "= ?", args);

    }

}
