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
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

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

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;









    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /**
         * COMPRUEBA SI YA TIENE LOS DATOS DEL REPARTIDOR PARA LOGUEARSE AUTOMÁTICAMENTE
         *
         *
         * */
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = dbHelper.readFromLocalDatabase(database);

        if (dbHelper.checkForTableExists(database, "repartidor")){
            Log.d("BDrepartidor", "existen datos de repartidor");
            Intent myIntent = new Intent(LoginActivity.this,MainActivity.class);

            LoginActivity.this.startActivity(myIntent);
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



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
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
                attemptLogin();
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
        Log.d("attemptLogin", "ingresa a attemptLogin ");


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
            Log.d("Validación", "se verifica la validación y pasa a loguearse con el servidor");

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
            Log.d("userlogin", "se crea el objeto userlogin, email y password");

        }


        private void doInBackground() {
            // TODO: attempt authentication against a network service.
            Log.d("background", "ingresa a doInBackground ");



            Response.Listener<String> responseListener = new Response.Listener<String>() {
                // Se realiza una acción cuando se recibe el response


                @Override
                public void onResponse(String response) {
                    Log.d("RESPONSE", String.valueOf(response));
                    Log.d("onResponse", "ingresa a onResponse");
                    //final VolleyCallback callback = new LoginExitoso();
                    // UserLoginTask obj = new UserLoginTask();

                    showProgress(false);

                    try {
                        JSONObject jsonResponse = new JSONObject(String.valueOf(response));
                        boolean success = jsonResponse.getBoolean("exito");


                        Log.d("RESPONSE", String.valueOf(response));



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
                                Log.d("exito", "response true/exitoso. Ingresa a Main Repartidor");

                                DbHelper dbHelper = new DbHelper(getApplicationContext());
                                SQLiteDatabase database = dbHelper.getWritableDatabase();

                                dbHelper.saveToLocalDatabase(Integer.parseInt(dni), Integer.parseInt(id),mEmail,mPassword, DbContract.SYNC_STATUS_OK, database);
                                mAuthTask = null;

                                // prueba para saber si guarda los datos del repartidor en la BD


                                DbHelper dbHelperRead = new DbHelper(getApplicationContext());
                                SQLiteDatabase databaseRead = dbHelperRead.getReadableDatabase();
                                Cursor cursor = dbHelperRead.readFromLocalDatabase(databaseRead);



                                while (cursor.moveToNext())
                                {
                                     usuariobd = cursor.getString(cursor.getColumnIndex(DbContract.USUARIO));
                                     passwordbd = cursor.getString(cursor.getColumnIndex(DbContract.PASSWORD));
                                     dnibd = cursor.getString(cursor.getColumnIndex(DbContract.DNI));
                                     idbd = cursor.getString(cursor.getColumnIndex(DbContract.IDREPARTIDOR));



                                }

                                StringBuilder sb = new StringBuilder();
                                sb.append(usuariobd);
                                sb.append(passwordbd);
                                sb.append(dnibd);
                                sb.append(idbd);
                                String resultado = sb.toString();

                                Log.d("BdRepartidor", resultado);

                                Intent myIntent = new Intent(LoginActivity.this,MainActivity.class);
                                myIntent.putExtra("id", id);
                                myIntent.putExtra("dni", dni);
                                LoginActivity.this.startActivity(myIntent);
                            } else {
                                if (TextUtils.equals(msj, "supervisor")){
                                    Log.d("exito", "response true/exitoso. Ingresa a Main Supervisor");

                                    // Crear el intent y pasar a una activity supervisor



                                }
                            }



                            // callback.onSuccess();

                            // obj.registerEventListener(callback);

                        } else {
                            // callback.onError();
                            //obj.registerEventListener(callback);

                            Log.d("exito", "response FALSE");
                            // mPasswordView.setError(getString(R.string.error_incorrect_password));
                            mPasswordView.setError("La contraseña o el usuario no son válidos. Vuelva a intentar");
                            mPasswordView.requestFocus();
                            Log.d("incorrect", "incorrect password ");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("JSON", "Error en login/response. Excepción json");

                    }

                }
            };

            //*************************
            //En ésta parte del código se crea el reponse para enviarlo al servidor










            LoginRequest loginRequest = new LoginRequest(mEmail, mPassword, responseListener);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(loginRequest);
            Log.d("request", "Se crea la solicitud al servidor");



        }
    }

 }

