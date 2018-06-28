package com.example.jumpi.repartidores_aplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CheckAppStatus {
    /**
     * COMPRUEBA SI YA TIENE LOS DATOS DEL REPARTIDOR PARA LOGUEARSE AUTOMÁTICAMENTE
     *
     *
     * */
    Context contexto;

    DbHelper dbHelper = new DbHelper(contexto);
    SQLiteDatabase database = dbHelper.getReadableDatabase();

    Cursor cursor = dbHelper.readFromLocalDatabase(database);

        if (dbHelper.checkForTableExists(database, "repartidor")){
        Log.d("BDrepartidor", "existen datos de repartidor");
        Intent myIntent = new Intent(CheckAppStatus.java,MainActivity.class);

        CheckAppStatus.this.startActivity(myIntent);
        /**
         showProgress(true);
         String usuario=null;
         String password=null;

         while (cursor.moveToNext())
         {
         usuario = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));
         password = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));


         }
         dbHelper.close();
         mAuthTask = new UserLoginTask(usuario, password);
         Log.d("DBrepartidor", "se encuentra el repartidor en la BD");
         mAuthTask.doInBackground();
         StringBuilder sb = new StringBuilder();
         sb.append(usuario);
         sb.append(password);
         //sb.append(dnibd);
         //sb.append(idbd);
         String resultado = sb.toString();

         Log.d("RepartidorGuardado", resultado);

         */

    }

}
