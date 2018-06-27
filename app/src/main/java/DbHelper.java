import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jumpi.repartidores_aplication.DbContract;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE = "create table "+DbContract.TABLE_NAME+"("+DbContract.DNI+" integer primary key,"+DbContract.IDREPARTIDOR+" integer,"+DbContract.USUARIO+" text,"+DbContract.PASSWORD+" text);";
    private static final String DROP_TABLE = "drop table if exists "+DbContract.TABLE_NAME;
    
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
