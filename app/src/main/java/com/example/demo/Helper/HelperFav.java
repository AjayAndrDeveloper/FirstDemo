package com.example.demo.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.demo.Adapter.FavAdapter;

import java.util.ArrayList;

public class HelperFav extends SQLiteOpenHelper {
    private static  final String dbName = "myDB";
    private static  final int version = 1;
//    String category="dfdfd";
//            String quotes = "gddd";
    Context context;
    public HelperFav(Context context){
        super(context,dbName,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="CREATE TABLE FAV (id INTEGER PRIMARY KEY AUTOINCREMENT,QUOTES TEXT)";
        db.execSQL(sql);

    }
    public   void  insertData(String quotes){
        SQLiteDatabase database = this.getWritableDatabase();
//        in this step  we get data from pagerActivity through intent
        ContentValues contentValues = new ContentValues();

        contentValues.put("quotes",quotes);
        database.insert("FAV",null,contentValues);
        database.close();
    }
    public ArrayList<String> getAllQuotes(){
        ArrayList<String> favList = new ArrayList<>();
        SQLiteDatabase allDB = this.getReadableDatabase();
        Cursor res =allDB.rawQuery("SELECT * FROM FAV", null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            favList.add(res.getString(res.getColumnIndex("QUOTES")));
            res.moveToNext();
        }


        return  favList;
    }
    public void deleteData(String quotes){
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete("FAV","quotes=?",new String[]{quotes});
        Log.d("deleteData", "deleteData: "+quotes);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
//   Cursor  readAllData() {
//        String Add = "SELECT * FROM FAV";
//        SQLiteDatabase database = this.getReadableDatabase();
//        Cursor cursor = null;
//        if (database!=null){
//            cursor = database.rawQuery(Add,null);
//
//        }
//        return cursor;
//    }
}
