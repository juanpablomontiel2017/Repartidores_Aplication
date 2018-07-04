package com.example.jumpi.repartidores_aplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {
/**
 *  /**
 * COMPRUEBA SI YA TIENE LOS DATOS DEL REPARTIDOR PARA LOGUEARSE AUTOMÁTICAMENTE
 *
 *
 *


    DbHelper dbHelper = new DbHelper(contexto);
    SQLiteDatabase database = dbHelper.getReadableDatabase();

    Cursor cursor = dbHelper.readFromLocalDatabase(database);

        if (dbHelper.checkForTableExists(database, "repartidor")){
        Log.d("TFSB", "existen datos de repartidor");




        showProgress(true);
        String usuario=null;
        String password=null;

        while (cursor.moveToNext())
        {
            usuario = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));
            password = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));


        }
        dbHelper.close();
        mAuthTask = new LoginActivity.UserLoginTask(usuario, password);
        Log.d("TFSB", "se encuentra el repartidor en la BD");
        mAuthTask.doInBackground();
        StringBuilder sb = new StringBuilder();
        sb.append(usuario);
        sb.append(password);
        //sb.append(dnibd);
        //sb.append(idbd);
        String resultado = sb.toString();

        Log.d("TFSB", resultado);



    }
 */







    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    ArrayList<Usuario> ListaUsuario = new ArrayList<>();

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    private UserLoginTask mAuthTask = null;
    private DbUserLogin mAuthDb = null;
    private boolean flag = false;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("TFSB", "***************************");
        Log.d("TFSB", "                           ");
        Log.d("TFSB", "    BIENVENIDO A LOGCAT    ");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);

        DbHelper dbHelper = new DbHelper(getApplicationContext());
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.readFromLocalDatabase(database);

        if (dbHelper.checkForTableExists(database, "usuario")){
            Log.d("TFSB", "existen datos de usuario");


            //showProgress(true);
            String usuario=null;
            String password=null;

            while (cursor.moveToNext())
            {
                usuario = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));
                password = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));

            }


            dbHelper.close();

            Log.d("TFSB", "se encuentra el usuario en la BD");


            mEmailView.setText(usuario);
            mPasswordView.setText(password);
            StringBuilder sb = new StringBuilder();
            sb.append(usuario);
            sb.append(password);
            //sb.append(dnibd);
            //sb.append(idbd);
            String resultado = sb.toString();

            Log.d("TFSB", "Existe un usuario: "+resultado+ " se autocompletaran los datos");



        }


        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);


        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TFSB", "***************************");
                Log.d("TFSB", "                           ");
                Log.d("TFSB", "onClick/ ingresa a onClick");


                DbHelper dbHelper = new DbHelper(getApplicationContext());
                SQLiteDatabase database = dbHelper.getReadableDatabase();
                Cursor cursor = dbHelper.readFromLocalDatabase(database);

                if (dbHelper.checkForTableExists(database, "usuario")){
                    Log.d("TFSB", "Se comprueba nuevamente que el usuario existe");


                    //showProgress(true);
                    String usuario=null;
                    String password=null;

                    while (cursor.moveToNext())
                    {
                        usuario = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));
                        password = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));

                    }


                    dbHelper.close();
                    showProgress(true);
                    Log.d("TFSB", "onClick/ obtiene los datos del usuario");

                    if (TextUtils.equals(usuario,mEmailView.getText().toString()) && TextUtils.equals(password,mPasswordView.getText().toString()) ){

                        Log.d("TFSB", "onClick/ el usuario y password a enviar se encuentra registrado");
                        mAuthDb = new DbUserLogin(usuario, password);
                        mAuthDb.execute();

                        StringBuilder sb = new StringBuilder();
                        sb.append(usuario);
                        sb.append(password);
                        //sb.append(dnibd);
                        //sb.append(idbd);
                        String resultado = sb.toString();


                    }else{
                        Log.d("TFSB", "onClick/ existe un usuario registrado pero se loguea con otra cuenta");



                        /**
                         * EN ESTA PARTE SE DEBE INVOCAR UN MÉTODO DIFERENTE DE ATTEMPTLOGIN
                         * NO SOLO DEBE LOGUEAR SI NO TAMBIÉN  BORRAR LAS TABLAS PERTENECIENTES A LA CUENTA VIEJA
                         */

                        mAuthDb = new DbUserLogin(mEmailView.getText().toString(), mPasswordView.getText().toString());
                        mAuthDb.newUserlogin();

                    }



                }else {
                    attemptLogin();
                }


            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        Log.d("TFSB", "ingresa a attemptLogin ");


        if (mAuthTask != null) {
            return;
        }











        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;
/**
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
*/

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            //mAuthTask.execute((Void) null);
            mAuthTask.doInBackground();


        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask  {

        private final String mEmail;
        private final String mPassword;
        private  String id = null;
        private  String dni = null;
        private  String msj = null;
      //  private  String usuariobd = null;


        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
            Log.d("TFSB", "se crea el objeto userlogin, email y password");

        }


        private void doInBackground() {
            // TODO: attempt authentication against a network service.
            Log.d("TFSB", "ingresa a doInBackground ");



            Response.Listener<String> responseListener = new Response.Listener<String>() {
                // Se realiza una acción cuando se recibe el response


                @Override
                public void onResponse(String response) {
                    Log.d("TFSB", String.valueOf(response));
                    Log.d("TFSB", "ingresa a onResponse");
                    //final VolleyCallback callback = new LoginExitoso();
                    // UserLoginTask obj = new UserLoginTask();
                    mAuthTask = null;
                    showProgress(false);

                    try {
                        JSONObject jsonResponse = new JSONObject(String.valueOf(response));
                        boolean success = jsonResponse.getBoolean("exito");


                        Log.d("TFSB", String.valueOf(response));



                        if (success) {

                            JSONObject jsonData = jsonResponse.getJSONObject("data");
                            id = jsonData.getString("id");
                            dni = jsonData.getString("dni");
                            msj = jsonData.getString("msj");

                            /**
                             * OBTENGO LOS CLIENTES DEL REPARTIDOR
                             */

                            JSONArray jsonClientes = jsonData.getJSONArray("clientes");
                            JSONObject jsonClientesDatos = null;
                            for (int i = 0; i < jsonClientes.length(); i++)
                            {
                                try {
                                    jsonClientesDatos = jsonClientes.getJSONObject(i);
                                    String idDB = jsonClientesDatos.getString("ClientesDirectos_Persona_IdCliente");
                                    String dniDB = jsonClientesDatos.getString("ClientesDirectos_Persona_DNICliente");
                                    String apellidoDB = jsonClientesDatos.getString("Apellido");
                                    String nombreDB = jsonClientesDatos.getString("Nombre");
                                    String telefonoDB = jsonClientesDatos.getString("Telefono");
                                    String emailDB = jsonClientesDatos.getString("Email");
                                    String direccionDB = jsonClientesDatos.getString("Direccion");
                                    String referenciaDB = jsonClientesDatos.getString("Referencia");
                                    String barrioDB = jsonClientesDatos.getString("Barrio");

                                    JSONArray jsonDia = jsonClientesDatos.getJSONArray("Dia");
                                    JSONObject jsonDiaDatos = null;
                                    int lunes =DbContract.DIA_FAIL;
                                    int martes =DbContract.DIA_FAIL;
                                    int miercoles =DbContract.DIA_FAIL;
                                    int jueves =DbContract.DIA_FAIL;
                                    int viernes =DbContract.DIA_FAIL;
                                    int sabado =DbContract.DIA_FAIL;


                                    for (int j = 0; j < jsonDia.length(); j++)
                                    {

                                        try{
                                            jsonDiaDatos = jsonDia.getJSONObject(j);
                                            String Dia = jsonDiaDatos.getString("Dia");

                                            if (TextUtils.equals(Dia, "LUNES")){
                                                 lunes = DbContract.DIA_OK;
                                            }
                                            if (TextUtils.equals(Dia, "MARTES")){
                                                lunes = DbContract.DIA_OK;
                                            }
                                            if (TextUtils.equals(Dia, "MIERCOLES")){
                                                lunes = DbContract.DIA_OK;
                                            }
                                            if (TextUtils.equals(Dia, "JUEVES")){
                                                lunes = DbContract.DIA_OK;
                                            }
                                            if (TextUtils.equals(Dia, "VIERNES")){
                                                lunes = DbContract.DIA_OK;
                                            }
                                            if (TextUtils.equals(Dia, "SABADO")){
                                                lunes = DbContract.DIA_OK;
                                            }

                                        }catch (JSONException a){
                                            Log.e("TSFB", "Parser JSON "+ a.toString());

                                        }
                                    }

                                    //String diaDB = jsonClientesDatos.getString("ClientesDirectos_Persona_DNICliente");

                                    DbHelper dbHelper = new DbHelper(getApplicationContext());
                                    SQLiteDatabase database = dbHelper.getWritableDatabase();

                                    dbHelper.saveToLocalDatabaseZonaReparto(Integer.parseInt(dniDB), Integer.parseInt(idDB), apellidoDB, nombreDB, direccionDB, barrioDB,referenciaDB, telefonoDB, emailDB, R.drawable.leomessi, lunes, martes, miercoles, jueves, viernes, sabado, DbContract.SYNC_STATUS_OK, database);
                                    dbHelper.close();

                                } catch (JSONException e) {
                                    Log.e("TSFB", "Parser JSON "+ e.toString());
                                }
                            }

                            // ingresas a otra activity de la app. Logueo exitoso

                            finish();
                            String usuariobd = null;
                            String passwordbd = null;
                            String dnibd = null;
                            String idbd = null;

                            if (TextUtils.equals(msj, "repartidor")){
                                Log.d("TFSB", "response true. Ingresa a Main Repartidor");

                                DbHelper dbHelper = new DbHelper(getApplicationContext());
                                SQLiteDatabase database = dbHelper.getWritableDatabase();

                                dbHelper.saveToLocalDatabase(Integer.parseInt(dni), Integer.parseInt(id),mEmail,mPassword, DbContract.SYNC_STATUS_OK, database);


                                // prueba para saber si guarda los datos del repartidor en la BD
/*

                                DbHelper dbHelperRead = new DbHelper(getApplicationContext());
                                SQLiteDatabase databaseRead = dbHelperRead.getReadableDatabase();
                                Cursor cursor = dbHelperRead.readFromLocalDatabase(databaseRead);


                                ListaUsuario = null;
                                while (cursor.moveToNext())
                                {
                                     usuariobd = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));
                                     passwordbd = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));
                                     dnibd = cursor.getString(cursor.getColumnIndex(DbContract.DNI));
                                     idbd = cursor.getString(cursor.getColumnIndex(DbContract.ID));
                                     ListaUsuario.add(new Usuario(Integer.parseInt(idbd), Integer.parseInt(dnibd), usuariobd, passwordbd));



                                }

                                StringBuilder sb = new StringBuilder();
                                sb.append(usuariobd);
                                sb.append(passwordbd);
                                sb.append(dnibd);
                                sb.append(idbd);
                                String resultado = sb.toString();

                                Log.d("TFSB", "Datos del usuario logeado "+resultado);
*/

/*
                                usuariobd = null;
                                passwordbd = null;
                                dnibd = null;
                                idbd = null;

                                Log.d("TFSB", "Datos del usuario logeado en Lista ");

                                for( int i = 0 ; i < ListaUsuario.size() ; i++ ){

                                    usuariobd = ListaUsuario.get(i).getUsuario();
                                    passwordbd = ListaUsuario.get(i).getPassword();
                                    dnibd = Integer.parseInt(ListaUsuario.get(i).get);
                                    idbd = null;

                                    Log.d("TFSB", "Datos del usuario logeado en Lista ");

                                }
*/
                                Intent myIntent = new Intent(LoginActivity.this,MainActivity.class);
                                myIntent.putExtra("id", id);
                                myIntent.putExtra("dni", dni);
                                LoginActivity.this.startActivity(myIntent);
                            } else {
                                if (TextUtils.equals(msj, "supervisor")){
                                    Log.d("TFSB", "response true. Ingresa a Main Supervisor");

                                    // Crear el intent y pasar a una activity supervisor



                                }
                            }



                            // callback.onSuccess();

                            // obj.registerEventListener(callback);

                        } else {
                            // callback.onError();
                            //obj.registerEventListener(callback);

                            Log.d("TFSB", "response FALSE");
                            // mPasswordView.setError(getString(R.string.error_incorrect_password));
                            mPasswordView.setError("La contraseña o el usuario no son válidos. Vuelva a intentar");
                            mPasswordView.requestFocus();
                            Log.d("TFSB", "incorrect password ");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("TFSB", "Error en login/response. Excepción json " + e.toString());

                    }

                }
            };

            //*************************
            //En ésta parte del código se crea el reponse para enviarlo al servidor










            LoginRequest loginRequest = new LoginRequest(mEmail, mPassword, responseListener);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(loginRequest);
            Log.d("TFSB", "Se crea la solicitud al servidor. Usuario: "+mEmail+" Pass: "+mPassword);



        }
    }

    public class DbUserLogin  {

        private final String mEmail;
        private final String mPassword;
        private  String id = null;
        private  String dni = null;
        private  String msj = null;
        //  private  String usuariobd = null;


        DbUserLogin(String usuario, String password) {
            mEmail = usuario;
            mPassword = password;
            Log.d("TFSB", "se crea el objeto userlogin, email y password");

        }


        private void execute() {
            // TODO: attempt authentication against a network service.
            Log.d("TFSB", "Execute/ ingresa a execute ");



            Response.Listener<String> responseListener = new Response.Listener<String>() {
                // Se realiza una acción cuando se recibe el response


                @Override
                public void onResponse(String response) {
                    Log.d("TFSB", "Execute/ "+String.valueOf(response));
                    Log.d("TFSB", "Execute/ ingresa a onResponse");
                    //final VolleyCallback callback = new LoginExitoso();
                    // UserLoginTask obj = new UserLoginTask();
                    mAuthDb = null;
                    showProgress(false);

                    try {
                        JSONObject jsonResponse = new JSONObject(String.valueOf(response));
                        boolean success = jsonResponse.getBoolean("exito");






                        if (success) {
                            id = jsonResponse.getString("id");
                            dni = jsonResponse.getString("dni");
                            msj = jsonResponse.getString("msj");






                            // ingresas a otra activity de la app. Logueo exitoso

                            finish();
                            String usuariobd = null;
                            String passwordbd = null;
                            String dnibd = null;
                            String idbd = null;

                            if (TextUtils.equals(msj, "repartidor")){
                                Log.d("TFSB", "Execute/ response true. Ingresa a Main Repartidor");

                                //DbHelper dbHelper = new DbHelper(getApplicationContext());
                                //SQLiteDatabase database = dbHelper.getWritableDatabase();

                              //  dbHelper.saveToLocalDatabase(Integer.parseInt(dni), Integer.parseInt(id),mEmail,mPassword, DbContract.SYNC_STATUS_OK, database);


                                // prueba para saber si guarda los datos del repartidor en la BD


                                DbHelper dbHelperRead = new DbHelper(getApplicationContext());
                                SQLiteDatabase databaseRead = dbHelperRead.getReadableDatabase();
                                Cursor cursor = dbHelperRead.readFromLocalDatabase(databaseRead);



                                while (cursor.moveToNext())
                                {
                                    usuariobd = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));
                                    passwordbd = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));
                                    dnibd = cursor.getString(cursor.getColumnIndex(DbContract.DNI));
                                    idbd = cursor.getString(cursor.getColumnIndex(DbContract.ID));



                                }

                                StringBuilder sb = new StringBuilder();
                                sb.append(usuariobd);
                                sb.append(passwordbd);
                                sb.append(dnibd);
                                sb.append(idbd);
                                String resultado = sb.toString();

                                Log.d("TFSB","Execute/ resultado en la BD " + resultado);

                                Intent myIntent = new Intent(LoginActivity.this,MainActivity.class);
                                myIntent.putExtra("id", id);
                                myIntent.putExtra("dni", dni);
                                LoginActivity.this.startActivity(myIntent);
                            } else {
                                if (TextUtils.equals(msj, "supervisor")){
                                    Log.d("TFSB", "Execute/ response true/exitoso. Ingresa a Main Supervisor");

                                    // Crear el intent y pasar a una activity supervisor



                                }
                            }



                            // callback.onSuccess();

                            // obj.registerEventListener(callback);

                        } else {
                            // callback.onError();
                            //obj.registerEventListener(callback);

                            Log.d("TFSB", "Execute/ response FALSE");
                            // mPasswordView.setError(getString(R.string.error_incorrect_password));
                            mPasswordView.setError("La contraseña o el usuario no son válidos. Vuelva a intentar");
                            mPasswordView.requestFocus();
                            Log.d("TFSB", "Execute/ incorrect password ");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("TFSB", "Execute/ Error en login/response. Excepción json");

                    }

                }
            };

            //*************************
            //En ésta parte del código se crea el reponse para enviarlo al servidor

            LoginRequest loginRequest = new LoginRequest(mEmail, mPassword, responseListener);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(loginRequest);
            Log.d("TFSB", "Se crea la solicitud al servidor. Usuario: "+mEmail+" Pass: "+mPassword);



        }

        private void newUserlogin(){

            // TODO: attempt authentication against a network service.
            Log.d("TFSB", "DbUserLogin/ ingresa a newUserlogin. Se loguea un nuevo usuario ");



            Response.Listener<String> responseListener = new Response.Listener<String>() {
                // Se realiza una acción cuando se recibe el response


                @Override
                public void onResponse(String response) {
                    Log.d("TFSB", String.valueOf(response));
                    Log.d("TFSB", "DbUserLogin/onResponse ingresa a onResponse");

                    mAuthDb = null;
                    showProgress(false);

                    try {
                        JSONObject jsonResponse = new JSONObject(String.valueOf(response));
                        boolean success = jsonResponse.getBoolean("exito");


                        if (success) {
                            id = jsonResponse.getString("id");
                            dni = jsonResponse.getString("dni");
                            msj = jsonResponse.getString("msj");

                            DbHelper dbHelper = new DbHelper(getApplicationContext());
                            SQLiteDatabase databaseRead = dbHelper.getReadableDatabase();

                            dbHelper.onUpgrade(databaseRead,1,1);

                            Cursor cursor = dbHelper.readFromLocalDatabase(databaseRead);
                            String usuariobd = null;
                            String passwordbd = null;
                            String dnibd = null;
                            String idbd = null;


                            while (cursor.moveToNext())
                            {
                                usuariobd = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));
                                passwordbd = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));
                                dnibd = cursor.getString(cursor.getColumnIndex(DbContract.DNI));
                                idbd = cursor.getString(cursor.getColumnIndex(DbContract.ID));



                            }
                           // cursor.close();


                            StringBuilder sb = new StringBuilder();
                            sb.append(usuariobd);
                            sb.append(passwordbd);
                            sb.append(dnibd);
                            sb.append(idbd);
                            String ResultadoViejoUsuario = sb.toString();

                            Log.d("TFSB", "DbUserLogin/onResponse Datos del viejo usuario: "+ResultadoViejoUsuario);



                            // ingresas a otra activity de la app. Logueo exitoso

                            finish();


                            if (TextUtils.equals(msj, "repartidor")){
                                Log.d("TFSB", "DbUserLogin/onResponse/ response true. Ingresa a Main Repartidor");

                                //DbHelper dbHelper = new DbHelper(getApplicationContext());
                                //SQLiteDatabase database = dbHelper.getWritableDatabase();

                                //  dbHelper.saveToLocalDatabase(Integer.parseInt(dni), Integer.parseInt(id),mEmail,mPassword, DbContract.SYNC_STATUS_OK, database);


                                // prueba para saber si guarda los datos del repartidor en la BD

/**
                                DbHelper dbHelperRead = new DbHelper(getApplicationContext());
                                SQLiteDatabase databaseRead = dbHelperRead.getReadableDatabase();
                                Cursor cursor = dbHelperRead.readFromLocalDatabase(databaseRead);



                                while (cursor.moveToNext())
                                {
                                    usuariobd = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));
                                    passwordbd = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));
                                    dnibd = cursor.getString(cursor.getColumnIndex(DbContract.DNI));
                                    idbd = cursor.getString(cursor.getColumnIndex(DbContract.ID));



                                }
                                dbHelperRead.close();
                                StringBuilder sb = new StringBuilder();
                                sb.append(usuariobd);
                                sb.append(passwordbd);
                                sb.append(dnibd);
                                sb.append(idbd);
                                String resultado = sb.toString();

                                Log.d("TFSB", resultado);
*/

                                SQLiteDatabase database = dbHelper.getWritableDatabase();

                                dbHelper.saveToLocalDatabase(Integer.parseInt(dni), Integer.parseInt(id),mEmail,mPassword, DbContract.SYNC_STATUS_OK, database);


                                // prueba para saber si guarda los datos del repartidor en la BD


                              //  DbHelper dbHelperRead = new DbHelper(getApplicationContext());
                                //SQLiteDatabase databaseRead = dbHelperRead.getReadableDatabase();
                                //Cursor cursor = dbHelper.readFromLocalDatabase(databaseRead);


                              //k  Cursor cursor = dbHelper.readFromLocalDatabase(databaseRead);
                                while (cursor.moveToFirst())
                                {
                                    usuariobd = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));
                                    passwordbd = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));
                                    dnibd = cursor.getString(cursor.getColumnIndex(DbContract.DNI));
                                    idbd = cursor.getString(cursor.getColumnIndex(DbContract.ID));



                                }

                                StringBuilder concat = new StringBuilder();
                                concat.append(usuariobd);
                                concat.append(passwordbd);
                                concat.append(dnibd);
                                concat.append(idbd);
                                String ResultadoNuevoUsuario = concat.toString();

                                Log.d("TFSB", "DbUserLogin/onResponse/ Datos del nuevo usuario :"+ResultadoNuevoUsuario);
                                dbHelper.close();

                                Intent myIntent = new Intent(LoginActivity.this,MainActivity.class);
                                myIntent.putExtra("id", id);
                                myIntent.putExtra("dni", dni);
                                LoginActivity.this.startActivity(myIntent);
                            } else {
                                if (TextUtils.equals(msj, "supervisor")){
                                    Log.d("TFSB", "DbUserLogin/onResponse/ response true. Ingresa a Main Supervisor");

                                    // Crear el intent y pasar a una activity supervisor



                                }
                            }



                            // callback.onSuccess();

                            // obj.registerEventListener(callback);

                        } else {
                            // callback.onError();
                            //obj.registerEventListener(callback);

                            Log.d("TFSB", "DbUserLogin/onResponse/ response FALSE");
                            // mPasswordView.setError(getString(R.string.error_incorrect_password));
                            mPasswordView.setError("La contraseña o el usuario no son válidos. Vuelva a intentar");
                            mPasswordView.requestFocus();
                            Log.d("TFSB", "DbUserLogin/onResponse/ incorrect password ");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("TFSB", "DbUserLogin/onResponse/ Error en login/response. Excepción json");

                    }

                }
            };

            //*************************
            //En ésta parte del código se crea el reponse para enviarlo al servidor

            LoginRequest loginRequest = new LoginRequest(mEmail, mPassword, responseListener);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(loginRequest);
            Log.d("TFSB", "DbUserLogin/ Se crea la solicitud al servidor. Usuario: "+mEmail+" pass: "+mPassword);

        }


    }



}

