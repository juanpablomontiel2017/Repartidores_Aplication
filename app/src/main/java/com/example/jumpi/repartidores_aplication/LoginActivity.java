package com.example.jumpi.repartidores_aplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
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

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;
import static java.lang.Integer.parseInt;


public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {


    private static final int REQUEST_READ_CONTACTS = 0;

    ArrayList<Usuario> ListaUsuario = new ArrayList<>();


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    private UserLoginTask mAuthTask = null;
    private DbUserLogin mAuthDb = null;
    private boolean flag = false;


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    /******************COMIENZO DEL onCreate()**************************/


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

        if (dbHelper.checkForTableExists(database, "usuario")) {


            Log.d("TFSB", "existen datos de usuario");

            String usuario = null;

            String password = null;

            while (cursor.moveToNext()) {
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


            String resultado = sb.toString();

            Log.d("TFSB", "Existe un usuario: " + resultado + " se autocompletaran los datos");


        }


        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {


                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {

                    attemptLogin();

                    return true;

                }//Fin del if

                return false;


            }/***************FIN DEL EVENTO onEditorAction()****************/


        });/***************FIN DEL EVENTO setOnEditorActionListener()****************/


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

                if (dbHelper.checkForTableExists(database, "usuario")) {


                    Log.d("TFSB", "Se comprueba nuevamente que el usuario existe");


                    String usuario = null;

                    String password = null;

                    while (cursor.moveToNext()) {

                        usuario = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));

                        password = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));

                    }//Fin del while


                    dbHelper.close();

                    showProgress(true);

                    Log.d("TFSB", "onClick/ obtiene los datos del usuario");


                    if (TextUtils.equals(usuario, mEmailView.getText().toString()) && TextUtils.equals(password, mPasswordView.getText().toString())) {

                        Log.d("TFSB", "onClick/ el usuario y password a enviar se encuentra registrado");
                        mAuthDb = new DbUserLogin(usuario, password);
                        mAuthDb.execute();

                        StringBuilder sb = new StringBuilder();

                        sb.append(usuario);

                        sb.append(password);

                        String resultado = sb.toString();


                    } else {

                        Log.d("TFSB", "onClick/ existe un usuario registrado pero se loguea con otra cuenta");


                        /**
                         * EN ESTA PARTE SE DEBE INVOCAR UN MÉTODO DIFERENTE DE ATTEMPTLOGIN
                         * NO SOLO DEBE LOGUEAR SI NO TAMBIÉN  BORRAR LAS TABLAS PERTENECIENTES A LA CUENTA VIEJA
                         */

                        mAuthDb = new DbUserLogin(mEmailView.getText().toString(), mPasswordView.getText().toString());

                        mAuthDb.newUserlogin();

                    }//Fin del else


                } //Fin del primer if

                else {

                    attemptLogin();

                }


            }/***************FIN DEL EVENTO OnClick()****************/


        });/***************FIN DEL EVENTO setOnClickListener()****************/


        mLoginFormView = findViewById(R.id.login_form);

        mProgressView = findViewById(R.id.login_progress);


    }/************************FIN DEL onCreate()*****************************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    private void populateAutoComplete() {


        if (!mayRequestContacts()) {


            return;


        }

        getLoaderManager().initLoader(0, null, this);


    }/************************FIN DE LA FUNCIÓN populateAutoComplete()*****************************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


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


    }/***********************FIN DE LA FUNCIÓN mayRequestContacts()*************************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {


        if (requestCode == REQUEST_READ_CONTACTS) {


            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                populateAutoComplete();

            }//Fin del if


        }//Fin del primer if


    }/**************************FIN DE LA FUNCIÓN onRequestPermissionsResult()*********************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


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


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.


            focusView.requestFocus();


        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            showProgress(true);

            mAuthTask = new UserLoginTask(email, password);

            mAuthTask.doInBackground();


        }//Fin del else


    }/************************FIN DE LA FUNCIÓN attemptLogin()*************************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    private boolean isEmailValid(String email) {


        //TODO: Replace this with your own logic

        return email.contains("@");


    }/************************FIN DE LA FUNCIÓN isEmailValid()*************************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    private boolean isPasswordValid(String password) {

        //TODO: Replace this with your own logic


        return password.length() > 4;


    }/************************FIN DE LA FUNCIÓN isPasswordValid()*************************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


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


                }/******************FIN DEL EVENTO onAnimationEnd()****************/


            });/******************FIN DEL EVENTO setListener()****************/


            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);

            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {


                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);


                }/******************FIN DEL EVENTO onAnimationEnd()****************/


            });/******************FIN DEL EVENTO setListener()****************/


        }//Fin del if


        else {


            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);

        }//Fin del else


    }/************************FIN DE LA FUNCIÓN showProgress()*************************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


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


    }/************************FIN DE LA FUNCIÓN onCreateLoader()*************************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {

        List<String> emails = new ArrayList<>();

        cursor.moveToFirst();


        while (!cursor.isAfterLast()) {


            emails.add(cursor.getString(ProfileQuery.ADDRESS));

            cursor.moveToNext();


        }//Fin del while


        addEmailsToAutoComplete(emails);


    }/************************FIN DE LA FUNCIÓN onLoadFinished()*************************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {


        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);


    }/************************FIN DE LA FUNCIÓN addEmailsToAutoComplete()*************************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    private interface ProfileQuery {

        String[] PROJECTION = {

                ContactsContract.CommonDataKinds.Email.ADDRESS,

                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };


        int ADDRESS = 0;

        int IS_PRIMARY = 1;


    }/************************FIN DE LA FUNCIÓN ProfileQuery()*************************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */


    public class UserLoginTask {


        private final String mEmail;

        private final String mPassword;

        private String id = null;

        private String dni = null;

        private String msj = null;

        private String dniRepartidor = null;

        private String idRepartidor = null;

        private String nombreRepartidor = null;

        private String apellidoRepartidor = null;

        int lunes = DbContract.DIA_FAIL;

        int martes = DbContract.DIA_FAIL;

        int miercoles = DbContract.DIA_FAIL;

        int jueves = DbContract.DIA_FAIL;

        int viernes = DbContract.DIA_FAIL;

        int sabado = DbContract.DIA_FAIL;


        UserLoginTask(String email, String password) {


            mEmail = email;

            mPassword = password;


            Log.d("TFSB", "se crea el objeto userlogin, email y password");


        }

        /************************FIN DEL CONSTRUCTOR UserLoginTask()*************************/


        private void doInBackground() {
            // TODO: attempt authentication against a network service.
            Log.d("TFSB", "ingresa a doInBackground ");


            Response.Listener<String> responseListener = new Response.Listener<String>() {
                // Se realiza una acción cuando se recibe el response


                @Override
                public void onResponse(String response) {


                    Log.d("TFSB", String.valueOf(response));

                    Log.d("TFSB", "ingresa a onResponse");

                    mAuthTask = null;

                    showProgress(false);

                    try {


                        JSONObject jsonResponse = new JSONObject(String.valueOf(response));

                        boolean success = jsonResponse.getBoolean("exito");


                        Log.d("TFSB", String.valueOf(response));


                        /** SI SUCCESS ES = TRUE ENTONCES USUARIO Y CONTRASEÑA VÁLIDA**/

                        if (success) {

                            JSONObject jsonData = jsonResponse.getJSONObject("data");

                            id = jsonData.getString("id");

                            dni = jsonData.getString("dni");

                            msj = jsonData.getString("msj");


                            Usuario usuario = new Usuario(parseInt(id), dni, mEmail, mPassword, msj);

                            usuario.GuardarUsuarioEnUnSharedPreferences(LoginActivity.this);


                            if (TextUtils.equals(msj, "repartidor")) {



                                /*Llamada a la función: */
                                LeerClientesDelResponse(jsonData, lunes, martes, miercoles, jueves
                                        , viernes, sabado, id, dni, msj, mEmail, mPassword);


                                // Ingresas a otra activity de la app. Logueo exitoso


                                Log.d("TFSB", "response true. Ingresa a Main Repartidor");


                                /**
                                 * PRUEBA PARA SABER SI GUARDA LOS DATOS DEL USUARIO EN LA BD LOCAL
                                 */


                                DbHelper dbHelperRead = new DbHelper(getApplicationContext());

                                SQLiteDatabase databaseRead = dbHelperRead.getReadableDatabase();

                                Cursor cursor = dbHelperRead.readFromLocalDatabase(databaseRead);


                                String usuariobd = null;

                                String passwordbd = null;

                                String dnibd = null;

                                String idbd = null;


                                while (cursor.moveToNext()) {
                                    usuariobd = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));

                                    passwordbd = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));

                                    dnibd = cursor.getString(cursor.getColumnIndex(DbContract.DNI));

                                    idbd = cursor.getString(cursor.getColumnIndex(DbContract.ID));


                                }//Fin del while

                                dbHelperRead.close();

                                cursor.close();


                                Log.d("TFSB", "PRUEBA PARA SABER SI GUARDA LOS DATOS DEL USUARIO -> DNI: " + dnibd + " ID: " + idbd + " USUARIO: " + usuariobd + " PASSWORD: " + passwordbd);


                                /**
                                 * PRUEBA PARA SABER SI GUARDA LOS DATOS DE LOS CLIENTES
                                 */

                                dbHelperRead = new DbHelper(getApplicationContext());

                                databaseRead = dbHelperRead.getReadableDatabase();

                                cursor = dbHelperRead.readFromLocalDatabaseZonaReparto(databaseRead);

                                Log.d("TFSB", "                               ");
                                Log.d("TFSB", "  PRUEBA PARA SABER SI GUARDA LOS DATOS DE LOS CLIENTES");
                                Log.d("TFSB", "                               ");

                                dnibd = null;

                                idbd = null;


                                while (cursor.moveToNext()) {

                                    dnibd = cursor.getString(cursor.getColumnIndex(DbContract.DNI));

                                    idbd = cursor.getString(cursor.getColumnIndex(DbContract.ID));


                                    String apellidoDB = cursor.getString(cursor.getColumnIndex(DbContract.APELLIDO));

                                    String nombreDB = cursor.getString(cursor.getColumnIndex(DbContract.NOMBRE));

                                    String telefonoDB = cursor.getString(cursor.getColumnIndex(DbContract.TELEFONO));

                                    String emailDB = cursor.getString(cursor.getColumnIndex(DbContract.CORREO));

                                    String direccionDB = cursor.getString(cursor.getColumnIndex(DbContract.DIRECCION));

                                    String barrioDB = cursor.getString(cursor.getColumnIndex(DbContract.BARRIO));

                                    String foto = cursor.getString(cursor.getColumnIndex(DbContract.FOTO));

                                    String lunes = cursor.getString(cursor.getColumnIndex(DbContract.LUNES));

                                    String martes = cursor.getString(cursor.getColumnIndex(DbContract.MARTES));

                                    String miercoles = cursor.getString(cursor.getColumnIndex(DbContract.MIERCOLES));

                                    String jueves = cursor.getString(cursor.getColumnIndex(DbContract.JUEVES));

                                    String viernes = cursor.getString(cursor.getColumnIndex(DbContract.VIERNES));

                                    String sabado = cursor.getString(cursor.getColumnIndex(DbContract.SABADO));


                                    Log.d("TFSB", "DNI: " + dnibd + " ID: " + idbd + " APELLIDO: " + apellidoDB + " NOMBRE: " + nombreDB + " LUNES:" + lunes + " MARTES:" + martes + " MIERCOLES:" + miercoles + " JUEVES:" + jueves + " VIERNES:" + viernes + " SABADO:" + sabado);


                                }//Fin del while


                                dbHelperRead.close();

                                cursor.close();


                                Intent myIntent = new Intent(LoginActivity.this, Repartidores_Main_Activity.class);

                                Bundle bundle = new Bundle();


                                String dia = UtilidadFecha.getNombreDelDia();

                                bundle.putString("dia", dia);


                                myIntent.putExtras(bundle);

                                myIntent.putExtra("id", id);

                                myIntent.putExtra("dni", dni);


                                LoginActivity.this.startActivity(myIntent);


                            }//Fin del if (TextUtils.equals(msj,"repartidor")


                            else {


                                if (TextUtils.equals(msj, "supervisor")) {


                                    Log.d("TFSB", "response true. Ingresa a Main Supervisor");



                                    guardarDatosDeRepartidores(jsonData);
                                    guardarDatosDeArticulos(jsonData);



                                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);


                                    Bundle bundle = new Bundle();


                                    String dia = UtilidadFecha.getNombreDelDia();


                                    bundle.putString("dia", dia);


                                    myIntent.putExtras(bundle);


                                    myIntent.putExtra("id", id);


                                    myIntent.putExtra("dni", dni);


                                    LoginActivity.this.startActivity(myIntent);


                                }//Fin del if


                            }//Fin del else


                        }//Fin del if(success)


                        else {


                            Log.d("TFSB", "response FALSE");

                            mPasswordView.setError("La contraseña o el usuario no son válidos. Vuelva a intentar");

                            mPasswordView.requestFocus();


                            Log.d("TFSB", "incorrect password ");


                        }//Fin del else

                    }//Fin del primer try


                    catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("TFSB", "Error en login/response. Excepción json " + e.toString());

                    }


                }/*************************FIN DEL EVENTO onResponse()******************************/


            };/*************************FIN DEL EVENTO Response.Listener()******************************/


            utilsRequest request = utilsRequest.loginRequest(mEmail, mPassword, "true", responseListener);
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);


            Log.d("TFSB", "Se crea la solicitud al servidor. Usuario: " + mEmail + " Pass: " + mPassword);


        }/***********************FIN DE LA FUNCIÓN doInBackground()************************/


    }/**************************************** FIN DE LA CLASE UserLoginTask() ********************************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    public class DbUserLogin {


        private final String mEmail;

        private final String mPassword;

        private String id = null;

        private String dni = null;

        private String msj = null;


        int lunes = DbContract.DIA_FAIL;

        int martes = DbContract.DIA_FAIL;

        int miercoles = DbContract.DIA_FAIL;

        int jueves = DbContract.DIA_FAIL;

        int viernes = DbContract.DIA_FAIL;

        int sabado = DbContract.DIA_FAIL;


        DbUserLogin(String usuario, String password) {


            mEmail = usuario;

            mPassword = password;


            Log.d("TFSB", "se crea el objeto userlogin, email y password");


        }

        /***FIN DEL CONSTRUCTOR DbUserLogin() ****/


        private void execute() {


            // TODO: attempt authentication against a network service.


            Log.d("TFSB", "Execute/ ingresa a execute ");


            Response.Listener<String> responseListener = new Response.Listener<String>() {
                // Se realiza una acción cuando se recibe el response


                @Override
                public void onResponse(String response) {

                    Log.d("TFSB", "Execute/ " + String.valueOf(response));

                    Log.d("TFSB", "Execute/ ingresa a onResponse");


                    mAuthDb = null;


                    showProgress(false);

                    try {


                        JSONObject jsonResponse = new JSONObject(String.valueOf(response));

                        boolean success = jsonResponse.getBoolean("exito");


                        if (success) {


                            JSONObject jsonData = jsonResponse.getJSONObject("data");


                            id = jsonData.getString("id");

                            dni = jsonData.getString("dni");

                            msj = jsonData.getString("msj");


                            //Ingresa a otra activity de la app. Logueo exitoso

                            finish();


                            String usuariobd = null;

                            String passwordbd = null;

                            String dnibd = null;

                            String idbd = null;


                            Usuario usuario = new Usuario(parseInt(id), dni, mEmail, mPassword, msj);

                            usuario.GuardarUsuarioEnUnSharedPreferences(LoginActivity.this);


                            if (TextUtils.equals(msj, "repartidor")) {


                                Log.d("TFSB", "Execute/ response true. Ingresa a Main Repartidor");


                                // prueba para saber si guarda los datos del repartidor en la BD

                                DbHelper dbHelperRead = new DbHelper(getApplicationContext());
                                SQLiteDatabase databaseRead = dbHelperRead.getReadableDatabase();
                                Cursor cursor = dbHelperRead.readFromLocalDatabase(databaseRead);


                                while (cursor.moveToNext()) {

                                    usuariobd = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));

                                    passwordbd = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));

                                    dnibd = cursor.getString(cursor.getColumnIndex(DbContract.DNI));


                                    idbd = cursor.getString(cursor.getColumnIndex(DbContract.ID));


                                }//Fin del while


                                cursor.close();

                                dbHelperRead.close();


                                StringBuilder sb = new StringBuilder();


                                sb.append(usuariobd);

                                sb.append(passwordbd);

                                sb.append(dnibd);

                                sb.append(idbd);


                                String resultado = sb.toString();

                                Log.d("TFSB", "Execute/ resultado en la BD " + resultado);


                                /**
                                 * PRUEBA PARA SABER SI GUARDA LOS DATOS DE LOS CLIENTES
                                 */


                                dbHelperRead = new DbHelper(getApplicationContext());

                                databaseRead = dbHelperRead.getReadableDatabase();

                                cursor = dbHelperRead.readFromLocalDatabaseZonaReparto(databaseRead);


                                Log.d("TFSB", "                               ");
                                Log.d("TFSB", "  PRUEBA PARA SABER SI GUARDA LOS DATOS DE LOS CLIENTES");
                                Log.d("TFSB", "                               ");


                                dnibd = null;

                                idbd = null;


                                while (cursor.moveToNext()) {


                                    dnibd = cursor.getString(cursor.getColumnIndex(DbContract.DNI));

                                    idbd = cursor.getString(cursor.getColumnIndex(DbContract.ID));


                                    String apellidoDB = cursor.getString(cursor.getColumnIndex(DbContract.APELLIDO));

                                    String nombreDB = cursor.getString(cursor.getColumnIndex(DbContract.NOMBRE));

                                    String telefonoDB = cursor.getString(cursor.getColumnIndex(DbContract.TELEFONO));

                                    String emailDB = cursor.getString(cursor.getColumnIndex(DbContract.CORREO));

                                    String direccionDB = cursor.getString(cursor.getColumnIndex(DbContract.DIRECCION));

                                    String barrioDB = cursor.getString(cursor.getColumnIndex(DbContract.BARRIO));

                                    String foto = cursor.getString(cursor.getColumnIndex(DbContract.FOTO));


                                    String lunes = cursor.getString(cursor.getColumnIndex(DbContract.LUNES));

                                    String martes = cursor.getString(cursor.getColumnIndex(DbContract.MARTES));

                                    String miercoles = cursor.getString(cursor.getColumnIndex(DbContract.MIERCOLES));

                                    String jueves = cursor.getString(cursor.getColumnIndex(DbContract.JUEVES));

                                    String viernes = cursor.getString(cursor.getColumnIndex(DbContract.VIERNES));

                                    String sabado = cursor.getString(cursor.getColumnIndex(DbContract.SABADO));


                                    Log.d("TFSB", "DNI: " + dnibd + " ID: " + idbd + " APELLIDO: " + apellidoDB + " NOMBRE: " + nombreDB + " LUNES:" + lunes + " MARTES:" + martes + " MIERCOLES:" + miercoles + " JUEVES:" + jueves + " VIERNES:" + viernes + " SABADO:" + sabado);


                                }//Fin del while(cursor.moveToNext)


                                dbHelperRead.close();

                                cursor.close();


                                Intent myIntent = new Intent(LoginActivity.this, Repartidores_Main_Activity.class);


                                Bundle bundle = new Bundle();


                                String dia = UtilidadFecha.getNombreDelDia();


                                bundle.putString("dia", dia);


                                myIntent.putExtras(bundle);


                                myIntent.putExtra("id", id);


                                myIntent.putExtra("dni", dni);


                                LoginActivity.this.startActivity(myIntent);


                            }//Fin del if(TextUtils.equals(msj,"repartidor"))


                            else {


                                if (TextUtils.equals(msj, "supervisor")) {


                                    Log.d("TFSB", "Execute/ response true/exitoso. Ingresa a Main Supervisor");
                                    guardarDatosDeRepartidores(jsonData);
                                    guardarDatosDeArticulos(jsonData);

                                    // Crear el intent y pasar a una activity supervisor

                                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);


                                    Bundle bundle = new Bundle();


                                    String dia = UtilidadFecha.getNombreDelDia();


                                    bundle.putString("dia", dia);


                                    myIntent.putExtras(bundle);


                                    myIntent.putExtra("id", id);


                                    myIntent.putExtra("dni", dni);


                                    LoginActivity.this.startActivity(myIntent);


                                }//Fin del if


                            }//Fin el else


                        } else {


                            Log.d("TFSB", "Execute/ response FALSE");

                            mPasswordView.setError("La contraseña o el usuario no son válidos. Vuelva a intentar");

                            mPasswordView.requestFocus();


                            Log.d("TFSB", "Execute/ incorrect password ");
                        }//Fin del else


                    }//Fin del try


                    catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("TFSB", "Execute/ Error en login/response. Excepción json");

                    }


                }/**********************FIN DEL EVENTO onResponse()*****************/


            };/**********************FIN DEL EVENTO Response.Listener()*****************/


            //En ésta parte del código se crea el reponse para enviarlo al servidor

            utilsRequest request = utilsRequest.loginRequest(mEmail, mPassword, "false", responseListener);
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);


            Log.d("TFSB", "Se crea la solicitud al servidor. Usuario: " + mEmail + " Pass: " + mPassword);


        }

        /***************** FIN DE LA FUNCIÓN execute() ********************/


        private void newUserlogin() {

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


                        Log.d("TFSB", String.valueOf(response));


                        /** SI SUCCESS ES = TRUE ENTONCES USUARIO Y CONTRASEÑA VÁLIDA***/


                        if (success) {

                            DbHelper db = new DbHelper(getApplicationContext());
                            SQLiteDatabase sql = db.getReadableDatabase();

                            db.onUpgrade(sql, 1, 1);

                            db.close();


                            JSONObject jsonData = jsonResponse.getJSONObject("data");


                            id = jsonData.getString("id");

                            dni = jsonData.getString("dni");

                            msj = jsonData.getString("msj");


                            Usuario usuario = new Usuario(parseInt(id), dni, mEmail, mPassword, msj);

                            usuario.GuardarUsuarioEnUnSharedPreferences(LoginActivity.this);


                            if (TextUtils.equals(msj, "repartidor")) {


                                DbHelper dbHelper = new DbHelper(getApplicationContext());

                                SQLiteDatabase databaseRead = dbHelper.getReadableDatabase();

                                dbHelper.onUpgrade(databaseRead, 1, 1);


                                /*** OBTENGO LOS DATOS RECIBIDOS DE LA KEY "DATA" ***/

                                if (jsonData.has("clientes")) {


                                    JSONArray jsonClientes = jsonData.getJSONArray("clientes");

                                    JSONObject jsonClientesDatos;


                                    for (int i = 0; i < jsonClientes.length(); i++) {


                                        try {


                                            jsonClientesDatos = jsonClientes.getJSONObject(i);


                                            String idDB = jsonClientesDatos.getString("ClientesDirectos_Persona_IdCliente");

                                            String dniDB = jsonClientesDatos.getString("ClientesDirectos_Persona_DNICliente");

                                            String apellidoDB = jsonClientesDatos.optString("Apellido");

                                            String nombreDB = jsonClientesDatos.optString("Nombre");

                                            String telefonoDB = jsonClientesDatos.optString("Telefono");

                                            String emailDB = jsonClientesDatos.optString("Email");

                                            String direccionDB = jsonClientesDatos.optString("Direccion");

                                            String referenciaDB = jsonClientesDatos.optString("Referencia");

                                            String barrioDB = jsonClientesDatos.optString("Barrio");


                                            lunes = DbContract.DIA_FAIL;

                                            martes = DbContract.DIA_FAIL;

                                            miercoles = DbContract.DIA_FAIL;

                                            jueves = DbContract.DIA_FAIL;

                                            viernes = DbContract.DIA_FAIL;

                                            sabado = DbContract.DIA_FAIL;


                                            if (jsonClientesDatos.has("Dia")) {


                                                JSONArray jsonDia = jsonClientesDatos.getJSONArray("Dia");

                                                JSONObject jsonDiaDatos;


                                                for (int j = 0; j < jsonDia.length(); j++) {


                                                    try {


                                                        jsonDiaDatos = jsonDia.getJSONObject(j);

                                                        if (jsonDiaDatos.has("Dia")) {


                                                            String Dia = jsonDiaDatos.optString("Dia");


                                                            if (TextUtils.equals(Dia, "LUNES")) {

                                                                lunes = DbContract.DIA_OK;


                                                            } //Fin del  if


                                                            if (TextUtils.equals(Dia, "MARTES")) {

                                                                martes = DbContract.DIA_OK;

                                                            } //Fin del if


                                                            if (TextUtils.equals(Dia, "MIERCOLES")) {

                                                                miercoles = DbContract.DIA_OK;

                                                            } //Fin del if


                                                            if (TextUtils.equals(Dia, "JUEVES")) {

                                                                jueves = DbContract.DIA_OK;

                                                            } //Fin del if


                                                            if (TextUtils.equals(Dia, "VIERNES")) {

                                                                viernes = DbContract.DIA_OK;

                                                            } //Fin del if


                                                            if (TextUtils.equals(Dia, "SABADO")) {

                                                                sabado = DbContract.DIA_OK;

                                                            } //Fin del if


                                                        }//Fin del if (jsonDiaDatos.has("Dia"))


                                                    }//Fin del try


                                                    catch (JSONException a) {
                                                        Log.e("TSFB", "Parser JSON DIA DATOS  " + a.toString());

                                                    }


                                                }//Fin del for (hasta jsonDia.length)


                                            }// FIN DEL if (jsonClientesDatos.has("Dia"))


                                            dbHelper = new DbHelper(getApplicationContext());

                                            SQLiteDatabase database = dbHelper.getWritableDatabase();



                                    /*
                                        SE INSERTAN LOS DATOS DEL CLIENTE EN LA TABLA ZONAREPARTO
                                     */


                                            dbHelper.saveToLocalDatabaseZonaReparto(parseInt(dniDB), parseInt(idDB), apellidoDB, nombreDB, direccionDB, barrioDB, referenciaDB, telefonoDB, emailDB, R.drawable.leomessi, lunes, martes, miercoles, jueves, viernes, sabado, DbContract.SYNC_STATUS_OK, database);

                                            dbHelper.close();

                                        }//Fin del segundo try


                                        catch (JSONException e) {


                                            Log.e("TSFB", "Parser JSON " + e.toString());
                                        }


                                    }//Fin del for (hasta jsonClientes.length)


                                }//Fin del if (jsonData.has("clientes"))


                                finish();


                                dbHelper = new DbHelper(getApplicationContext());

                                SQLiteDatabase database = dbHelper.getWritableDatabase();


                                // INSERTA LOS DATOS DEL USUARIO EN LA TABLA USUARIO

                                dbHelper.saveToLocalDatabase(parseInt(dni), parseInt(id), mEmail, mPassword, DbContract.SYNC_STATUS_OK, database);

                                dbHelper.close();

                                // ingresas a otra activity de la app. Logueo exitoso

                                Log.d("TFSB", "response true. Ingresa a Main Repartidor");


                                /**
                                 * PRUEBA PARA SABER SI GUARDA LOS DATOS DEL USUARIO EN LA BD LOCAL
                                 */


                                DbHelper dbHelperRead = new DbHelper(getApplicationContext());

                                databaseRead = dbHelperRead.getReadableDatabase();

                                Cursor cursor = dbHelperRead.readFromLocalDatabase(databaseRead);


                                String usuariobd = null;

                                String passwordbd = null;

                                String dnibd = null;

                                String idbd = null;


                                while (cursor.moveToNext()) {

                                    usuariobd = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));

                                    passwordbd = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));

                                    dnibd = cursor.getString(cursor.getColumnIndex(DbContract.DNI));

                                    idbd = cursor.getString(cursor.getColumnIndex(DbContract.ID));


                                }//Fin del while


                                dbHelperRead.close();

                                cursor.close();


                                Log.d("TFSB", "PRUEBA PARA SABER SI GUARDA LOS DATOS DEL USUARIO -> DNI: " + dnibd + " ID: " + idbd + " USUARIO: " + usuariobd + " PASSWORD: " + passwordbd);


                                /**
                                 * PRUEBA PARA SABER SI GUARDA LOS DATOS DE LOS CLIENTES
                                 */

                                dbHelperRead = new DbHelper(getApplicationContext());

                                databaseRead = dbHelperRead.getReadableDatabase();

                                cursor = dbHelperRead.readFromLocalDatabaseZonaReparto(databaseRead);


                                Log.d("TFSB", "                               ");

                                Log.d("TFSB", "  PRUEBA PARA SABER SI GUARDA LOS DATOS DE LOS CLIENTES");

                                Log.d("TFSB", "                               ");

                                dnibd = null;

                                idbd = null;

                                while (cursor.moveToNext()) {


                                    dnibd = cursor.getString(cursor.getColumnIndex(DbContract.DNI));

                                    idbd = cursor.getString(cursor.getColumnIndex(DbContract.ID));


                                    String apellidoDB = cursor.getString(cursor.getColumnIndex(DbContract.APELLIDO));

                                    String nombreDB = cursor.getString(cursor.getColumnIndex(DbContract.NOMBRE));

                                    String telefonoDB = cursor.getString(cursor.getColumnIndex(DbContract.TELEFONO));

                                    String emailDB = cursor.getString(cursor.getColumnIndex(DbContract.CORREO));

                                    String direccionDB = cursor.getString(cursor.getColumnIndex(DbContract.DIRECCION));

                                    String barrioDB = cursor.getString(cursor.getColumnIndex(DbContract.BARRIO));

                                    String foto = cursor.getString(cursor.getColumnIndex(DbContract.FOTO));


                                    String lunes = cursor.getString(cursor.getColumnIndex(DbContract.LUNES));

                                    String martes = cursor.getString(cursor.getColumnIndex(DbContract.MARTES));

                                    String miercoles = cursor.getString(cursor.getColumnIndex(DbContract.MIERCOLES));

                                    String jueves = cursor.getString(cursor.getColumnIndex(DbContract.JUEVES));

                                    String viernes = cursor.getString(cursor.getColumnIndex(DbContract.VIERNES));

                                    String sabado = cursor.getString(cursor.getColumnIndex(DbContract.SABADO));


                                    Log.d("TFSB", "DNI: " + dnibd + " ID: " + idbd + " APELLIDO: " + apellidoDB + " NOMBRE: " + nombreDB);


                                }//Fin del while (cursor.moveToNext)


                                dbHelperRead.close();

                                cursor.close();


                                Intent myIntent = new Intent(LoginActivity.this, Repartidores_Main_Activity.class);


                                Bundle bundle = new Bundle();


                                String dia = UtilidadFecha.getNombreDelDia();

                                bundle.putString("dia", dia);


                                myIntent.putExtras(bundle);

                                myIntent.putExtra("id", id);

                                myIntent.putExtra("dni", dni);

                                LoginActivity.this.startActivity(myIntent);


                            }//Fin del if (TextUtils.equals(msj,"repartidor"))


                            else {


                                if (TextUtils.equals(msj, "supervisor")) {


                                    Log.d("TFSB", "response true. Ingresa a Main Supervisor");

                                    // Crea el intent y pasar a una activity supervisor

                                    guardarDatosDeRepartidores(jsonData);
                                    guardarDatosDeArticulos(jsonData);
                                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);


                                    Bundle bundle = new Bundle();


                                    String dia = UtilidadFecha.getNombreDelDia();


                                    bundle.putString("dia", dia);


                                    myIntent.putExtras(bundle);


                                    myIntent.putExtra("id", id);


                                    myIntent.putExtra("dni", dni);


                                    DbHelper dbHelper = new DbHelper(getApplicationContext());

                                    SQLiteDatabase database = dbHelper.getWritableDatabase();


                                    // INSERTA LOS DATOS DEL USUARIO EN LA TABLA USUARIO

                                    dbHelper.saveToLocalDatabase(parseInt(dni), parseInt(id), mEmail, mPassword, DbContract.SYNC_STATUS_OK, database);

                                    dbHelper.close();


                                    LoginActivity.this.startActivity(myIntent);


                                }//Fin del if


                            }//Fin del else


                        }//Fin del if (success)


                        else {

                            Log.d("TFSB", "response FALSE");
                            // mPasswordView.setError(getString(R.string.error_incorrect_password));
                            mPasswordView.setError("La contraseña o el usuario no son válidos. Vuelva a intentar");
                            mPasswordView.requestFocus();
                            Log.d("TFSB", "incorrect password ");
                        }


                    }//Fin del primer try


                    catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("TFSB", "Error en login/response se murio en madrid. Excepción json " + e.toString());

                    }


                }
            }; /************** FIN DEL EVENTO Response.Listener() *******/


            //En ésta parte del código se crea el reponse para enviarlo al servidor

            utilsRequest request = utilsRequest.loginRequest(mEmail, mPassword, "true", responseListener);
            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);


            Log.d("TFSB", "DbUserLogin/ Se crea la solicitud al servidor. Usuario: " + mEmail + " pass: " + mPassword);


        }/********************* FIN DE LA FUNCIÓN NewUserLogin() *********************/


    }/******************************************* FIN DE LA CLASE DbUserLogin() ************************************/


    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/


    public void LeerClientesDelResponse(JSONObject jsonData, int lunes, int martes, int miercoles, int jueves
            , int viernes, int sabado, String id, String dni, String msj
            , String mEmail, String mPassword) throws JSONException {


        /**
         * OBTENGO LOS DATOS RECIBIDOS DE LA KEY "DATA"
         */


        JSONArray jsonClientes = jsonData.getJSONArray("clientes");

        JSONObject jsonClientesDatos;


        for (int i = 0; i < jsonClientes.length(); i++) {


            try {


                jsonClientesDatos = jsonClientes.getJSONObject(i);

                String idDB = jsonClientesDatos.getString("ClientesDirectos_Persona_IdCliente");

                String dniDB = jsonClientesDatos.getString("ClientesDirectos_Persona_DNICliente");

                String apellidoDB = jsonClientesDatos.optString("Apellido");

                String nombreDB = jsonClientesDatos.optString("Nombre");

                String telefonoDB = jsonClientesDatos.optString("Telefono");

                String emailDB = jsonClientesDatos.optString("Email");

                String direccionDB = jsonClientesDatos.optString("Direccion");

                String referenciaDB = jsonClientesDatos.optString("Referencia");

                String barrioDB = jsonClientesDatos.optString("Barrio");


                lunes = DbContract.DIA_FAIL;

                martes = DbContract.DIA_FAIL;

                miercoles = DbContract.DIA_FAIL;

                jueves = DbContract.DIA_FAIL;

                viernes = DbContract.DIA_FAIL;

                sabado = DbContract.DIA_FAIL;


                if (jsonClientesDatos.has("Dia")) {


                    JSONArray jsonDia = jsonClientesDatos.getJSONArray("Dia");


                    JSONObject jsonDiaDatos;


                    for (int j = 0; j < jsonDia.length(); j++) {

                        try {


                            jsonDiaDatos = jsonDia.getJSONObject(j);


                            if (jsonDiaDatos.has("Dia")) {
                                String Dia = jsonDiaDatos.optString("Dia");


                                if (TextUtils.equals(Dia, "LUNES")) {
                                    lunes = DbContract.DIA_OK;
                                }


                                if (TextUtils.equals(Dia, "MARTES")) {
                                    martes = DbContract.DIA_OK;
                                }


                                if (TextUtils.equals(Dia, "MIERCOLES")) {
                                    miercoles = DbContract.DIA_OK;
                                }


                                if (TextUtils.equals(Dia, "JUEVES")) {
                                    jueves = DbContract.DIA_OK;
                                }


                                if (TextUtils.equals(Dia, "VIERNES")) {
                                    viernes = DbContract.DIA_OK;
                                }


                                if (TextUtils.equals(Dia, "SABADO")) {
                                    sabado = DbContract.DIA_OK;
                                }


                            }//Fin del if(jsonDiaDatos.has("Dia"))


                        }//Fin del try


                        catch (JSONException a) {
                            Log.e("TSFB", "Parser JSON DIA DATOS  " + a.toString());

                        }//Fin del catch


                    }//Fin del for (hasta jsonDia)


                }//Fin del if(jsonClientesDatos.has("Dia"))


                DbHelper dbHelper = new DbHelper(getApplicationContext());
                SQLiteDatabase database = dbHelper.getWritableDatabase();

                                    /*
                                        SE INSERTAN LOS DATOS DEL CLIENTE EN LA TABLA ZONAREPARTO
                                     */


                dbHelper.saveToLocalDatabaseZonaReparto(parseInt(dniDB), parseInt(idDB), apellidoDB, nombreDB, direccionDB, barrioDB, referenciaDB, telefonoDB, emailDB, R.drawable.leomessi, lunes, martes, miercoles, jueves, viernes, sabado, DbContract.SYNC_STATUS_OK, database);

                dbHelper.close();

            }//Fin del segundo try


            catch (JSONException e) {


                Log.e("TSFB", "Parser JSON " + e.toString());


            }//Fin del catch


        }//Fin del for(hasta jsonClientes.length)


        finish();


        DbHelper dbHelper = new DbHelper(getApplicationContext());

        SQLiteDatabase database = dbHelper.getWritableDatabase();


        // INSERTA LOS DATOS DEL USUARIO EN LA TABLA USUARIO

        dbHelper.saveToLocalDatabase(parseInt(dni), parseInt(id), mEmail, mPassword, DbContract.SYNC_STATUS_OK, database);

        dbHelper.close();


    }

    /************************ FIN DE LA FUNCIÓN LeerClientesDelResponse() *************************/


    public void guardarDatosDeRepartidores(JSONObject jsonData) throws JSONException {

        /**
         * OBTENGO LOS DATOS RECIBIDOS DE LA KEY "DATA"
         *
         * TODO: refactorizar. Probar con enviar jsonArrayRepartidores y no con cada elemento
         *
         */

        if (jsonData.has("repartidores")) {


            JSONArray jsonArrayRepartidores = jsonData.getJSONArray("repartidores");

            JSONObject jsonRepartidor;


            for (int i = 0; i < jsonArrayRepartidores.length(); i++) {


                try {


                    jsonRepartidor = jsonArrayRepartidores.getJSONObject(i);

                    String idRepartidor = jsonRepartidor.getString("Persona_IdRepartidor");

                    String dniRepartidor = jsonRepartidor.getString("Persona_DNIRepartidor");

                    String apellidoRepartidor = jsonRepartidor.getString("Apellido");

                    String nombreRepartidor = jsonRepartidor.getString("Nombre");



                    Repartidores repartidores = new Repartidores(nombreRepartidor + ' ' + apellidoRepartidor, R.mipmap.messi, parseInt(idRepartidor), parseInt(dniRepartidor));

                    repartidores.GuardarRepartidoresEnUnSharedPreferences(LoginActivity.this, i);



                }//Fin del segundo try


                catch (JSONException e) {


                    Log.d("TSFB", "Parser JSON " + e.toString());


                }//Fin del catch


            }//Fin del for(hasta jsonClientes.length)

            SharedPreferences sharedPreferences = this.getSharedPreferences("Datos_Repartidores", MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();


            editor.putString("dimensionRepartidores",String.valueOf(jsonArrayRepartidores.length()));
            editor.commit();

        }
        else{
            Log.e("TSFB", "No existe array repartidores en jsonObject data ");
        }



    }/************************ FIN DE LA FUNCIÓN LeerClientesDelResponse() *************************/


    public void guardarDatosDeArticulos(JSONObject jsonData) throws JSONException {

        /**
         * OBTENGO LOS DATOS RECIBIDOS DE LA KEY "DATA"
         *          * TODO: refactorizar. Probar con enviar jsonArrayArticulos y no con cada elemento
         */

        if (jsonData.has("articulos")) {


            JSONArray jsonArrayArticulos= jsonData.getJSONArray("articulos");

            JSONObject jsonArticulo;


            for (int i = 0; i < jsonArrayArticulos.length(); i++) {


                try {


                    jsonArticulo = jsonArrayArticulos.getJSONObject(i);

                    String idArticulo = jsonArticulo.getString("IdArticulo");

                    String nombreArticulo = jsonArticulo.getString("Nombre");

                    String precioArticulo = jsonArticulo.getString("Precio");





                    articulo articulo = new articulo(parseInt(idArticulo), nombreArticulo, precioArticulo);

                    articulo.guardarArticulosEnUnSharedPreferences(LoginActivity.this, i);





                }//Fin del segundo try


                catch (JSONException e) {


                    Log.d("TFSB", "Parser JSON " + e.toString());


                }//Fin del catch


            }//Fin del for(hasta jsonClientes.length)

            SharedPreferences sharedPreferences = this.getSharedPreferences("Datos_Articulos", MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();


            editor.putString("dimensionArticulos",String.valueOf(jsonArrayArticulos.length()));
            editor.commit();


        }
        else{
            Log.e("TSFB", "No existe array repartidores en jsonObject data ");
        }



    }/************************ FIN DE LA FUNCIÓN LeerClientesDelResponse() *************************/




}/*****************************************FIN DE LA Activity LoginActivity ***************************************************/
