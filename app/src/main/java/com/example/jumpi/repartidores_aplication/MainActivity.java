package com.example.jumpi.repartidores_aplication;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
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
        //scheduleJob();
        ConnectionStateMonitor connectionStateMonitor = new ConnectionStateMonitor(getApplicationContext());
        connectionStateMonitor.enable();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final Button buttonLunes = (Button) findViewById(R.id.buttonLunes);

        buttonLunes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               Intent buttonLunes = new Intent(MainActivity.this, Second_Activity.class);
               Bundle bundle = new Bundle();
               String dia = "lunes";
                bundle.putString("dia", dia);

                buttonLunes.putExtras(bundle);
                // Agregas el Bundle al Intent e inicias ActivityB
               startActivity(buttonLunes);

            }
        });

        final Button buttonMartes = (Button) findViewById(R.id.buttonMartes);

        buttonMartes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonMartes = new Intent(MainActivity.this, Second_Activity.class);
                Bundle bundle = new Bundle();
                String dia = "martes";
                bundle.putString("dia", dia);

                buttonMartes.putExtras(bundle);
                startActivity(buttonMartes);

            }
        });

        final Button buttonMiercoles = (Button) findViewById(R.id.buttonMiercoles);

        buttonMiercoles.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonMiercoles = new Intent(MainActivity.this, Second_Activity.class);
                Bundle bundle = new Bundle();
                String dia = "miercoles";
                bundle.putString("dia", dia);

                buttonMiercoles.putExtras(bundle);
                startActivity(buttonMiercoles);

            }
        });


        final Button buttonJueves = (Button) findViewById(R.id.buttonJueves);

        buttonJueves.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonJueves = new Intent(MainActivity.this, Second_Activity.class);
                Bundle bundle = new Bundle();
                String dia = "jueves";
                bundle.putString("dia", dia);

                buttonJueves.putExtras(bundle);

                startActivity(buttonJueves);

            }
        });


        final Button buttonViernes = (Button) findViewById(R.id.buttonViernes);

        buttonViernes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonViernes = new Intent(MainActivity.this, Second_Activity.class);

                Bundle bundle = new Bundle();
                String dia = "viernes";
                bundle.putString("dia", dia);

                buttonViernes.putExtras(bundle);

                startActivity(buttonViernes);

            }
        });


        final Button buttonSabado = (Button) findViewById(R.id.buttonSabado);

        buttonSabado.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent buttonSabado = new Intent(MainActivity.this, Second_Activity.class);

                Bundle bundle = new Bundle();
                String dia = "sabado";
                bundle.putString("dia", dia);
                buttonSabado.putExtras(bundle);

                startActivity(buttonSabado);

            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent fab = new Intent(MainActivity.this, AgregarCliente.class);


                startActivity(fab);


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

    /**

    private void scheduleJob() {
        JobInfo myJob = new JobInfo.Builder(0, new ComponentName(this, NetworkSchedulerService.class))
                .setRequiresCharging(true)
                .setMinimumLatency(1000)
                .setOverrideDeadline(2000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(myJob);
    }

    protected void onStop() {
        // A service can be "started" and/or "bound". In this case, it's "started" by this Activity
        // and "bound" to the JobScheduler (also called "Scheduled" by the JobScheduler). This call
        // to stopService() won't prevent scheduled jobs to be processed. However, failing
        // to call stopService() would keep it alive indefinitely.
        stopService(new Intent(this, NetworkSchedulerService.class));
        super.onStop();
    }

    protected void onStart() {
        super.onStart();
        // Start service and provide it a way to communicate with this class.
        Intent startServiceIntent = new Intent(this, NetworkSchedulerService.class);
        startService(startServiceIntent);
    }

     */

}
