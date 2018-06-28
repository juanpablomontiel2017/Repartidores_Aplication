package com.example.jumpi.repartidores_aplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jumpi.repartidores_aplication.DbContract;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE = "create table "+DbContract.TABLE_NAME_USUARIO+"("+DbContract.DNI+" integer primary key,"+DbContract.ID+" integer,"+DbContract.USUARIO+" text,"+DbContract.PASSWORD+" text, "+DbContract.SYNC_STATUS+" integer);";
    private static final String DROP_TABLE = "drop table if exists "+DbContract.TABLE_NAME_USUARIO;

    public DbHelper (Context context){
        super(context, DbContract.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public  void saveToLocalDatabase(int dni, int idrepartidor, String usuario, String password, int sync_status, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.DNI, dni);
        contentValues.put(DbContract.ID, idrepartidor);
        contentValues.put(DbContract.USUARIO, usuario);
        contentValues.put(DbContract.PASSWORD, password);
        contentValues.put(DbContract.SYNC_STATUS, sync_status);
        database.insert(DbContract.TABLE_NAME_USUARIO,null,contentValues);

    }

    public Cursor readFromLocalDatabase (SQLiteDatabase database){
        String[] projection = {DbContract.DNI, DbContract.ID, DbContract.USUARIO, DbContract.PASSWORD, DbContract.SYNC_STATUS};

        return (database.query(DbContract.TABLE_NAME_USUARIO,projection, null,null, null,null,null));
    }

    public void updateLocalDatabase(int dni, int id, String usuario, String password, int sync_status, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.USUARIO, usuario);
        contentValues.put(DbContract.PASSWORD, password);
        contentValues.put(DbContract.SYNC_STATUS, sync_status);

        String selection = DbContract.DNI+" = ";
        String[] selection_args = {Integer.toString(dni)};
        database.update(DbContract.TABLE_NAME_USUARIO,contentValues,selection,selection_args);

    }


    public boolean checkForTableExists(SQLiteDatabase db, String table){
        String sql = "SELECT * FROM ? ";
        Cursor mCursor = db.rawQuery(sql, table);
        if (mCursor.getCount() > 0) {
            return true;
        }
        mCursor.close();
        return false;
    }


}
