package com.example.jumpi.repartidores_aplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final Button buttonLunes = (Button) findViewById(R.id.buttonLunes);

        buttonLunes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               Intent buttonLunes = new Intent(MainActivity.this, Second_Activity.class);
               startActivity(buttonLunes);

            }
        });

        final Button buttonMartes = (Button) findViewById(R.id.buttonMartes);

        buttonMartes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonMartes = new Intent(MainActivity.this, Second_Activity.class);
                startActivity(buttonMartes);

            }
        });

        final Button buttonMiercoles = (Button) findViewById(R.id.buttonMiercoles);

        buttonMiercoles.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonMiercoles = new Intent(MainActivity.this, Second_Activity.class);
                startActivity(buttonMiercoles);

            }
        });


        final Button buttonJueves = (Button) findViewById(R.id.buttonJueves);

        buttonJueves.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonJueves = new Intent(MainActivity.this, Second_Activity.class);
                startActivity(buttonJueves);

            }
        });


        final Button buttonViernes = (Button) findViewById(R.id.buttonViernes);

        buttonViernes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonViernes = new Intent(MainActivity.this, Second_Activity.class);
                startActivity(buttonViernes);

            }
        });


        final Button buttonSabado = (Button) findViewById(R.id.buttonSabado);

        buttonSabado.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonSabado = new Intent(MainActivity.this, Second_Activity.class);
                startActivity(buttonSabado);

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Toast.makeText(this, "Ha pulsado en menu -> Configuraciones <-",Toast.LENGTH_SHORT).show();

            return true;
        }

        if (id == R.id.id_cerrar_sesión) {

            //Toast.makeText(this, "Ha pulsado en menu -> Cerrar sesión <-",Toast.LENGTH_SHORT).show();

            DbHelper dbHelper = new DbHelper(getApplicationContext());
            SQLiteDatabase database = dbHelper.getReadableDatabase();

            dbHelper.onUpgrade(database,1,1);
            dbHelper.close();

            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            finish();
            startActivity(i);



            return true;
        }


        return super.onOptionsItemSelected(item);
    }


}
