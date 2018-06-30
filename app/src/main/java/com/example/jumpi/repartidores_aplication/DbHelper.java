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
    private static final String CREATE_TABLE_ZONA_REPARTO = "create table "+DbContract.TABLE_NAME_ZONA_REPARTO+"("+DbContract.DNI+" integer primary key,"+DbContract.ID+" integer,"+DbContract.NOMBRE+" text,"+DbContract.DIRECCION+" text,"+DbContract.BARRIO+" text,"+DbContract.REFERENCIA+" text,"+DbContract.TELEFONO+" text,"+DbContract.CORREO+" text, "+DbContract.FOTO+" int, "+DbContract.SYNC_STATUS+" integer);";


    private static final String DROP_TABLE = "drop table if exists "+DbContract.TABLE_NAME_USUARIO;
    private static final String DROP_TABLE_ZONA_REPARTO = "drop table if exists "+DbContract.TABLE_NAME_ZONA_REPARTO;

    public DbHelper (Context context){
        super(context, DbContract.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_ZONA_REPARTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        db.execSQL(DROP_TABLE_ZONA_REPARTO);
        onCreate(db);
    }

    public  void saveToLocalDatabase(int dni, int id, String usuario, String password, int sync_status, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbContract.DNI, dni);
        contentValues.put(DbContract.ID, id);
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
        String sql = "SELECT * FROM "+table;
        Cursor mCursor = db.rawQuery(sql, null);
        if (mCursor.getCount() > 0) {
            return true;
        }
        mCursor.close();
        return false;
    }


/**
 *              MÉTODOS PARA TABLA ZONAREPARTO
 */


    public  void saveToLocalDatabaseZonaReparto(int dni, int id, String nombre, String direccion, String barrio, String referencia, String telefono, String correo, int foto, int sync_status, SQLiteDatabase database){
    ContentValues contentValues = new ContentValues();
    contentValues.put(DbContract.DNI, dni);
    contentValues.put(DbContract.ID, id);
    contentValues.put(DbContract.NOMBRE, nombre);
    contentValues.put(DbContract.DIRECCION, direccion);
    contentValues.put(DbContract.BARRIO, barrio);
    contentValues.put(DbContract.REFERENCIA, referencia);
    contentValues.put(DbContract.TELEFONO, telefono);
    contentValues.put(DbContract.CORREO, correo);
    contentValues.put(DbContract.FOTO, foto);
    contentValues.put(DbContract.SYNC_STATUS, sync_status);
    database.insert(DbContract.TABLE_NAME_ZONA_REPARTO,null,contentValues);

}

    public Cursor readFromLocalDatabaseZonaReparto (SQLiteDatabase database){
        String[] projection = {DbContract.DNI, DbContract.ID, DbContract.NOMBRE, DbContract.DIRECCION, DbContract.BARRIO, DbContract.REFERENCIA, DbContract.TELEFONO, DbContract.CORREO, DbContract.FOTO, DbContract.SYNC_STATUS};

        return (database.query(DbContract.TABLE_NAME_ZONA_REPARTO,projection, null,null, null,null,null));
    }

    public void updateLocalDatabaseZonaReparto(int dni, int id, String nombre, String direccion, String barrio, String referencia, String telefono, String correo, int sync_status, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        if (nombre!=null){
            contentValues.put(DbContract.NOMBRE, nombre);
        }
        if (direccion!=null){
            contentValues.put(DbContract.DIRECCION, direccion);
        }
        if (barrio!=null){
            contentValues.put(DbContract.BARRIO, barrio);
        }
        if (direccion!=null){
            contentValues.put(DbContract.REFERENCIA, referencia);
        }
        if (telefono!=null){
            contentValues.put(DbContract.TELEFONO, telefono);
        }
        if (correo!=null){
            contentValues.put(DbContract.CORREO, correo);
        }



        contentValues.put(DbContract.SYNC_STATUS, sync_status);

        String selection = DbContract.DNI+" = ";
        String[] selection_args = {Integer.toString(dni)};
        database.update(DbContract.TABLE_NAME_ZONA_REPARTO,contentValues,selection,selection_args);

    }



}
