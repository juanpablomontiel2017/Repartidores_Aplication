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
    private static final String CREATE_TABLE_ZONA_REPARTO = "create table "+DbContract.TABLE_NAME_ZONA_REPARTO+"("+DbContract.DNI+" integer primary key,"+DbContract.ID+" integer,"+DbContract.APELLIDO+" text,"+DbContract.NOMBRE+" text,"+DbContract.DIRECCION+" text,"+DbContract.BARRIO+" text,"+DbContract.REFERENCIA+" text,"+DbContract.TELEFONO+" text,"+DbContract.CORREO+" text,"+DbContract.LUNES+" int,"+DbContract.MARTES+" int,"+DbContract.MIERCOLES+" int,"+DbContract.JUEVES+" int,"+DbContract.VIERNES+" int,"+DbContract.SABADO+" int, "+DbContract.FOTO+" int, "+DbContract.SYNC_STATUS+" integer);";
    private static final String CREATE_TABLE_ARTICULO = "create table "+DbContract.TABLE_NAME_ARTICULO+"("+DbContract.ID+" integer primary key,"+DbContract.ARTICULO+" text,"+DbContract.PRECIO+" integer);";
    private static final String CREATE_TABLE_VENTA = "create table "+DbContract.TABLE_NAME_VENTA+"("+DbContract.ID+" integer primary key autoincrement,"+DbContract.ID_CLIENTE+" integer,"+DbContract.DNI+" integer,"+DbContract.ENVASE_LLENO+" int,"+DbContract.ENVASE_VACIO+" int,"+DbContract.CANILLA+" int,"+DbContract.DISPENSER_ELECTRICO+" int,"+DbContract.DISPENSER_PLASTICO+" int,"+DbContract.ENVASE_VENTA+" int,"+DbContract.ENTREGA+" int,"+DbContract.FECHA+" text,"+DbContract.SYNC_STATUS+" integer);";



    private static final String DROP_TABLE = "drop table if exists "+DbContract.TABLE_NAME_USUARIO;
    private static final String DROP_TABLE_ZONA_REPARTO = "drop table if exists "+DbContract.TABLE_NAME_ZONA_REPARTO;
    private static final String DROP_TABLE_ARTICULO = "drop table if exists "+DbContract.TABLE_NAME_ARTICULO;
    private static final String DROP_TABLE_VENTA = "drop table if exists "+DbContract.TABLE_NAME_VENTA;


    public DbHelper (Context context){
        super(context, DbContract.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_ZONA_REPARTO);
        db.execSQL(CREATE_TABLE_ARTICULO);
        db.execSQL(CREATE_TABLE_VENTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DROP_TABLE);
        db.execSQL(DROP_TABLE_ZONA_REPARTO);
        db.execSQL(DROP_TABLE_ARTICULO);
        db.execSQL(DROP_TABLE_VENTA);
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


    public  void saveToLocalDatabaseZonaReparto(int dni, int id, String apellido, String nombre, String direccion, String barrio, String referencia, String telefono, String correo, int foto,int lunes,int martes,int miercoles,int jueves,int viernes,int sabado, int sync_status, SQLiteDatabase database){
    ContentValues contentValues = new ContentValues();
    contentValues.put(DbContract.DNI, dni);
    contentValues.put(DbContract.ID, id);
    contentValues.put(DbContract.APELLIDO, apellido);
    contentValues.put(DbContract.NOMBRE, nombre);
    contentValues.put(DbContract.DIRECCION, direccion);
    contentValues.put(DbContract.BARRIO, barrio);
    contentValues.put(DbContract.REFERENCIA, referencia);
    contentValues.put(DbContract.TELEFONO, telefono);
    contentValues.put(DbContract.CORREO, correo);

    contentValues.put(DbContract.LUNES, lunes);
    contentValues.put(DbContract.MARTES, martes);
    contentValues.put(DbContract.MIERCOLES, miercoles);
    contentValues.put(DbContract.JUEVES, jueves);
    contentValues.put(DbContract.VIERNES, viernes);
    contentValues.put(DbContract.SABADO, sabado);
    contentValues.put(DbContract.FOTO, foto);
    contentValues.put(DbContract.SYNC_STATUS, sync_status);
    database.insert(DbContract.TABLE_NAME_ZONA_REPARTO,null,contentValues);

}

    public Cursor readFromLocalDatabaseZonaReparto (SQLiteDatabase database){
        String[] projection = {DbContract.DNI, DbContract.ID, DbContract.APELLIDO, DbContract.NOMBRE, DbContract.DIRECCION, DbContract.BARRIO, DbContract.REFERENCIA, DbContract.TELEFONO, DbContract.CORREO, DbContract.FOTO, DbContract.LUNES, DbContract.MARTES, DbContract.MIERCOLES, DbContract.JUEVES, DbContract.VIERNES, DbContract.SABADO, DbContract.SYNC_STATUS};

        return (database.query(DbContract.TABLE_NAME_ZONA_REPARTO,projection, null,null, null,null,null));
    }

    public void updateLocalDatabaseZonaReparto(int dni, int id, String apellido, String nombre, String direccion, String barrio, String referencia, String telefono, String correo, int lunes, int martes, int miercoles, int jueves, int viernes, int sabado, int sync_status, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        if (apellido!=null){
            contentValues.put(DbContract.APELLIDO, apellido);
        }
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
        if (lunes!=DbContract.ENTERO_NULO){
            contentValues.put(DbContract.LUNES, lunes);
        }
        if (martes!=DbContract.ENTERO_NULO){
            contentValues.put(DbContract.MARTES, martes);
        }
        if (miercoles!=DbContract.ENTERO_NULO){
            contentValues.put(DbContract.MIERCOLES, miercoles);
        }
        if (jueves!=DbContract.ENTERO_NULO){
            contentValues.put(DbContract.JUEVES, jueves);
        }
        if (viernes!=DbContract.ENTERO_NULO){
            contentValues.put(DbContract.VIERNES, viernes);
        }
        if (sabado!=DbContract.ENTERO_NULO){
            contentValues.put(DbContract.SABADO, sabado);
        }



        contentValues.put(DbContract.SYNC_STATUS, sync_status);

        String selection = DbContract.DNI+" = ";
        String[] selection_args = {Integer.toString(dni)};
        database.update(DbContract.TABLE_NAME_ZONA_REPARTO,contentValues,selection,selection_args);

    }

/**
 *              MÉTODOS PARA TABLA ARTÍCULO
 */



    public  void saveToLocalDatabaseArticulo(int id, String articulo, int precio, SQLiteDatabase database){
    ContentValues contentValues = new ContentValues();

    contentValues.put(DbContract.ID, id);
    contentValues.put(DbContract.ARTICULO, articulo);
    contentValues.put(DbContract.PRECIO, precio);
    database.insert(DbContract.TABLE_NAME_ARTICULO,null,contentValues);

}

    public Cursor readFromLocalDatabaseArticulo (SQLiteDatabase database){
        String[] projection = {DbContract.ID, DbContract.ARTICULO, DbContract.PRECIO};

        return (database.query(DbContract.TABLE_NAME_ARTICULO,projection, null,null, null,null,null));
    }

    public void updateLocalDatabaseArticulo(int id, String articulo, int precio, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        if (articulo!=null){
            contentValues.put(DbContract.ARTICULO, articulo);
        }
        if (precio!=DbContract.ENTERO_NULO){
            contentValues.put(DbContract.PRECIO, precio);
        }



        String selection = DbContract.ID+" = ";
        String[] selection_args = {Integer.toString(id)};
        database.update(DbContract.TABLE_NAME_ARTICULO,contentValues,selection,selection_args);

    }




    /**
     *              MÉTODOS PARA TABLA VENTAS
     */


    public  void saveToLocalDatabaseVenta(int idCliente, int dni, int lleno, int vacio, int canilla, int electrico, int plastico, int envase, int entrega, String fecha, int sync_status, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DbContract.ID_CLIENTE, idCliente);
        contentValues.put(DbContract.DNI, dni);
        contentValues.put(DbContract.ENVASE_LLENO, lleno);
        contentValues.put(DbContract.ENVASE_VACIO, vacio);
        contentValues.put(DbContract.CANILLA, canilla);
        contentValues.put(DbContract.DISPENSER_ELECTRICO, electrico);
        contentValues.put(DbContract.DISPENSER_PLASTICO, plastico);
        contentValues.put(DbContract.ENVASE_VENTA, envase);

        contentValues.put(DbContract.ENTREGA, entrega);
        contentValues.put(DbContract.FECHA, fecha);

        contentValues.put(DbContract.SYNC_STATUS, sync_status);
        database.insert(DbContract.TABLE_NAME_VENTA,null,contentValues);

    }

    public Cursor readFromLocalDatabaseVenta (SQLiteDatabase database){
        String[] projection = {DbContract.ID, DbContract.ID_CLIENTE, DbContract.DNI, DbContract.ENVASE_LLENO, DbContract.ENVASE_VACIO, DbContract.CANILLA, DbContract.DISPENSER_ELECTRICO, DbContract.DISPENSER_PLASTICO, DbContract.ENVASE_VENTA, DbContract.ENTREGA, DbContract.FECHA, DbContract.SYNC_STATUS};

        return (database.query(DbContract.TABLE_NAME_ZONA_REPARTO,projection, null,null, null,null,null));
    }

    public void updateLocalDatabaseVenta(int dni, int lleno, int vacio, int canilla, int electrico, int plastico, int envase, int entrega, String fecha, int sync_status, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        if (lleno!=0){
            contentValues.put(DbContract.ENVASE_LLENO, lleno);
        }
        if (vacio!=0){
            contentValues.put(DbContract.ENVASE_VACIO, vacio);
        }
        if (canilla!=0){
            contentValues.put(DbContract.CANILLA, canilla);
        }
        if (electrico!=0){
            contentValues.put(DbContract.BARRIO, electrico);
        }
        if (plastico!=0){
            contentValues.put(DbContract.REFERENCIA, plastico);
        }
        if (envase!=0){
            contentValues.put(DbContract.TELEFONO, envase);
        }
        if (entrega!=0){
            contentValues.put(DbContract.ENTREGA, entrega);
        }
        if (fecha!=null){
            contentValues.put(DbContract.FECHA, fecha);
        }




        contentValues.put(DbContract.SYNC_STATUS, sync_status);

        String selection = DbContract.DNI+" = ";
        String[] selection_args = {Integer.toString(dni)};
        database.update(DbContract.TABLE_NAME_VENTA,contentValues,selection,selection_args);

    }



}
