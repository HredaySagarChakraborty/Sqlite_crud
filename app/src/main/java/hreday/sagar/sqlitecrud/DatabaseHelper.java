package hreday.sagar.sqlitecrud;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.zip.CheckedOutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static  final String TAG=DatabaseHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "Student.db";
    public static final int VERSION_NUMBER = 11;
    public static final String TABLE_NAME = "Students";
    public static final String NAME = "Name";
    public static final String ID = "_id";

    public static final String AGE = "Age";
    public static final String GENDER = "Gender";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," + NAME + " VARCHAR(255)," + AGE + " INTEGER," + GENDER + " VARCHAR(255)); ";


    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Toast.makeText(context, "Oncreate method is created", Toast.LENGTH_SHORT).show();
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public long InsertData(String name, String age, String gender) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(AGE, age);
        contentValues.put(GENDER, gender);

        long rowId = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return rowId;

    }


    public Cursor displayData() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
      //  Log.e(TAG,"Error");
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
       // SQLiteDatabase db = helper.getReadableDatabase();

/*
        String table = "TABLE_NAME";
        String[] columns = {"column1", "column3"};
        String selection = "column3 =?";
        String[] selectionArgs = {"NAME"};
        String groupBy = null;
        String having = null;
        String orderBy = "column3 DESC";
        String limit = "10";

        Cursor cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        Log.e(TAG,"Error");

      //  Cursor cursor=sqLiteDatabase.query("SELECT * FROM " + TABLE_NAME + " WHERE " + ID + "=?", new String[] { id + "" });


*/
        return cursor;

    }

    public boolean updateData(String name, String age, String gender, String id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(AGE, age);
        contentValues.put(GENDER, gender);
        contentValues.put(ID, id);
        sqLiteDatabase.update(TABLE_NAME, contentValues, ID + " = ?", new String[]{id});
        return true;

    }

    public int deleteData(String name,String age,String gender,String id){


        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,ID+" = ? ",new String[]{ id});


    }


}


