package com.example.jumpi.repartidores_aplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.api.matching.v5.MapboxMapMatching;
import com.mapbox.api.matching.v5.models.MapMatchingResponse;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.services.android.navigation.ui.v5.NavigationView;
import com.mapbox.services.android.navigation.ui.v5.NavigationViewOptions;
import com.mapbox.services.android.navigation.ui.v5.OnNavigationReadyCallback;
import com.mapbox.services.android.navigation.ui.v5.feedback.FeedbackItem;
import com.mapbox.services.android.navigation.ui.v5.listeners.FeedbackListener;
import com.mapbox.services.android.navigation.ui.v5.listeners.NavigationListener;
import com.mapbox.services.android.navigation.ui.v5.listeners.RouteListener;
import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigation;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.mapbox.services.android.navigation.v5.routeprogress.ProgressChangeListener;
import com.mapbox.services.android.navigation.v5.routeprogress.RouteProgress;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mapbox.geojson.Point.fromLngLat;

public class NavigationRepartidores extends AppCompatActivity implements OnNavigationReadyCallback,
        NavigationListener, RouteListener, ProgressChangeListener, FeedbackListener {




    private NavigationView navigationView;

    private boolean dropoffDialogShown;

    private Location lastKnownLocation;

    private DirectionsRoute currentRoute;

    private DirectionsRoute tramo;

    private JSONArray arrayLegs = new JSONArray();

    private List<Point> puntos_clientes = new ArrayList<>();

    private List<Clientes> clientesList;

    String ActivityProvenienteDe;

    final private int REALIZAR_VENTA = 1001;

    final private int MAPA_REPARTIDORES = 1002;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MapboxNavigation navigation = new MapboxNavigation(getApplicationContext(), getString(R.string.access_token_repartidor));

        setTheme(R.style.Theme_AppCompat_NoActionBar);

        /*Llamada a la función: */
        initNightMode();


        super.onCreate(savedInstanceState);






        ActivityProvenienteDe = getIntent().getStringExtra("Activity");

        /*Llamada a la función: */
        leerArchivoRutaJson();





        switch (ActivityProvenienteDe) {

            case "Mapa_Repartidores":

                Toast.makeText(NavigationRepartidores.this, "¡Bienvenido!", Toast.LENGTH_LONG).show();


                break;

            case "Realizar_Venta_Cliente_Repartidores":

                showDropoffDialog();


            default:

                Toast.makeText(NavigationRepartidores.this, "¡No se encontró la activity!", Toast.LENGTH_LONG).show();


        }//Fin del switch







        //Punto de origen: envasadora AquaVital
        puntos_clientes.add(fromLngLat(-60.446815, -26.784306));

        puntos_clientes.add(fromLngLat(-60.448123, -26.784327));
        puntos_clientes.add(fromLngLat(-60.448241, -26.790556));
        puntos_clientes.add(fromLngLat(-60.448427, -26.792986));
        puntos_clientes.add(fromLngLat(-60.446893, -26.792885));
        puntos_clientes.add(fromLngLat(-60.444785, -26.794505));
        puntos_clientes.add(fromLngLat(-60.442989, -26.792107));
        puntos_clientes.add(fromLngLat(-60.442023, -26.793726));
        puntos_clientes.add(fromLngLat(-60.442989, -26.792107));
        puntos_clientes.add(fromLngLat(-60.438246, -26.794419));


        //Regreso a la envasadora
        puntos_clientes.add(fromLngLat(-60.446815, -26.784306));






        setContentView(R.layout.activity_navigation_repartidores);


        navigationView = findViewById(R.id.navigationView);
        navigationView.onCreate(savedInstanceState);
        navigationView.initialize(this);





        /*Llamada a las siguientes funciones: */
        ListaDeClientesDelDia();



    }/************************** FIN DEL onCreate() **************************************/




    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/


    private void initNightMode() {
        int nightMode = retrieveNightModeFromPreferences();
        AppCompatDelegate.setDefaultNightMode(nightMode);
    }


    private int retrieveNightModeFromPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getInt("Modo Nocturno" ,AppCompatDelegate.MODE_NIGHT_AUTO);
    }



    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/


    private void obtenerTramoDeRuta() throws JSONException {

        List<Point> coordenadasDeSteps = new ArrayList<>();


        JSONObject objectLeg = arrayLegs.getJSONObject(0);
        JSONArray arraySteps = objectLeg.getJSONArray("steps");

        for ( int i=0 ; i < arraySteps.length(); i++ ){
            JSONObject objectSteps = arraySteps.getJSONObject(i);
            JSONObject objectManeuver = objectSteps.getJSONObject("maneuver");
            JSONArray arrayLocation = objectManeuver.getJSONArray("location");

            coordenadasDeSteps.add(fromLngLat((Double)arrayLocation.get(0),(Double)arrayLocation.get(1)));

        }


         Integer[]  arregloConIndicesDeOrigenDestino = {0,coordenadasDeSteps.size()-1};




        MapboxMapMatching.builder()
                .accessToken(Mapbox.getAccessToken())
                .coordinates(coordenadasDeSteps)
                .waypoints(arregloConIndicesDeOrigenDestino)
                .steps(true)
                .voiceInstructions(true)
                .voiceUnits(DirectionsCriteria.METRIC)
                .bannerInstructions(true)
                .profile(DirectionsCriteria.PROFILE_DRIVING)
                .build().enqueueCall(new Callback<MapMatchingResponse>() {

                @Override
                public void onResponse(Call<MapMatchingResponse> call, Response<MapMatchingResponse> response) {

                    if (response.body() == null) {
                        Log.e("TAG", "Map matching has failed.");
                        return;
                    }

                    if (response.isSuccessful()) {
                        tramo = response.body().matchings().get(0).toDirectionRoute();


                        fetchRoute(coordenadasDeSteps.get(0), coordenadasDeSteps.get(coordenadasDeSteps.size()-1));
                    }
                }

                @Override
                public void onFailure(Call<MapMatchingResponse> call, Throwable t) {

                    Log.d("TAG", "FAlló ");
                }
        });

        arrayLegs.remove(0);

    }/************************** FIN DE LA FUNCIÓN obtenerTramoDeRuta() **************************************/



    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/


    private void  leerArchivoRutaJson(){


        String archivoOsrmJson = loadGeoJsonFromAsset("rutaDeprueba90.json");



        try {

            JSONObject jsonObject = new JSONObject(archivoOsrmJson);
            JSONArray arrayRoute = jsonObject.getJSONArray("routes");
            JSONObject objectRuta = arrayRoute.getJSONObject(0);

            arrayLegs = objectRuta.getJSONArray("legs");



        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("TAG", e.toString());
        }

    }/************************** FIN DE LA FUNCIÓN leerArchivoRutaJson() **************************************/



    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/


    String loadGeoJsonFromAsset(String filename) {

        try {

            // Load GeoJSON file from local asset folder
            InputStream is = getApplicationContext().getAssets().open(filename);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            return new String(buffer, Charset.forName("UTF-8"));

        } catch (Exception exception) {

            throw new RuntimeException(exception);

        }

    }/************************** FIN DE LA FUNCIÓN loadGeoJsonFromAsset() **************************************/

    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/




    @Override
    public void onStart() {
        super.onStart();


        navigationView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        navigationView.onResume();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        navigationView.onLowMemory();
    }

    @Override
    public void onBackPressed() {
        // If the navigation view didn't need to do anything, call super
        if (!navigationView.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        navigationView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        navigationView.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        navigationView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        navigationView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        navigationView.onDestroy();
    }



    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/


    @Override
    public void onNavigationReady(boolean isRunning) {

        try {
            obtenerTramoDeRuta();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }/********************** FIN DE LA MÉTODO onNavigationReady() ******************************/


    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/

    @Override
    public void onCancelNavigation() {
    // Navigation canceled, finish the activity
        finish();
    }

    @Override
    public void onNavigationFinished() {
    // Intentionally empty
    }

    @Override
    public void onNavigationRunning() {
    // Intentionally empty
    }

    @Override
    public boolean allowRerouteFrom(Point offRoutePoint) {
        return true;
    }

    @Override
    public void onOffRoute(Point offRoutePoint) {

    }

    @Override
    public void onRerouteAlong(DirectionsRoute directionsRoute) {

    }

    @Override
    public void onFailedReroute(String errorMessage) {

    }



    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/


    @Override
    public void onArrival() {

        if (!dropoffDialogShown && !puntos_clientes.isEmpty()) {

            /* Llamada a la función: */
            MostrarMensajeDeVenta();

            dropoffDialogShown = true; // Accounts for multiple arrival events

            Toast.makeText(this, "Haz llegado a tu destino!", Toast.LENGTH_SHORT).show();

        }

    }/********************** FIN DE LA MÉTODO onArrival() ******************************/



    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/


    @Override
    public void onProgressChange(Location location, RouteProgress routeProgress) {
        lastKnownLocation = location;
    }


    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/


    private void startNavigation(DirectionsRoute directionsRoute) {
        NavigationViewOptions navigationViewOptions = setupOptions(directionsRoute);

        navigationView.startNavigation(navigationViewOptions);
    }



    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/


    private void showDropoffDialog() {


        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(getString(R.string.dropoff_dialog_text));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.dropoff_dialog_positive_text),
                (dialogInterface, in) -> {
                    try {
                        obtenerTramoDeRuta();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });

        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.dropoff_dialog_negative_text),
                (dialogInterface, in) -> {

                    Intent intent = new Intent(NavigationRepartidores.this, Mapa_Repartidores.class);

                    startActivityForResult(intent, MAPA_REPARTIDORES);

                    //startActivity(intent);


                });

        alertDialog.show();


    }/********************** FIN DE LA MÉTODO showDropoffDialog() ******************************/



    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/



    private void fetchRoute(Point origin, Point destination) {



        NavigationRoute.builder(this)
                .accessToken(Mapbox.getAccessToken())
                .origin(origin)
                .destination(destination)
                .alternatives(true)
                .voiceUnits(DirectionsCriteria.METRIC)
                .enableRefresh(true)
                .build();


             startNavigation(tramo);



    }/********************** FIN DE LA MÉTODO fetchRoute() ******************************/



    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/




    private NavigationViewOptions setupOptions(DirectionsRoute directionsRoute) {
        dropoffDialogShown = false;

        NavigationViewOptions.Builder options = NavigationViewOptions.builder();
        options.directionsRoute(directionsRoute)
                .navigationListener(this)
                .progressChangeListener(this)
                .routeListener(this)
                .feedbackListener(this)
                .shouldSimulateRoute(true);



        return options.build();


    }/********************** FIN DE LA MÉTODO setupOptions() ******************************/





    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/


    private Point getLastKnownLocation() {
        return Point.fromLngLat(lastKnownLocation.getLongitude(), lastKnownLocation.getLatitude());
    }




    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/




    public void MostrarMensajeDeVenta(){

        AlertDialog.Builder builder = new AlertDialog.Builder(NavigationRepartidores.this);
        builder.setTitle("¡Llego a su destino!");
        builder.setMessage("¿Desea realizar la venta?");


        builder.setPositiveButton("Realizar Venta", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Intent intentVentas = new Intent(NavigationRepartidores.this, RealizarVentasClientesRepartidor.class);

                intentVentas.putExtra("Foto",clientesList.get(0).getFoto());

                intentVentas.putExtra("DNI",clientesList.get(0).getDNI());

                intentVentas.putExtra("Apellido",clientesList.get(0).getApellido());

                intentVentas.putExtra("Nombre",clientesList.get(0).getNombre());

                intentVentas.putExtra("Codigo_Area",clientesList.get(0).getCodigo_Area());

                intentVentas.putExtra("Telefono",clientesList.get(0).getTelefono());

                intentVentas.putExtra("Direccion", clientesList.get(0).getDireccion());

                intentVentas.putExtra("Barrio", clientesList.get(0).getBarrio());

                intentVentas.putExtra("Referencia", clientesList.get(0).getReferencia());

                intentVentas.putExtra("Correo", clientesList.get(0).getCorreo());



                startActivityForResult(intentVentas, REALIZAR_VENTA);



            }
        });


        builder.setNegativeButton("Cancelar Venta", new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int id) {

                showDropoffDialog();

                dialog.dismiss();

            }
        });



        AlertDialog dialog = builder.create();
        dialog.show();

    }/************************ FIN DE LA FUNCIÓN MostrarMensajeDeVenta() ***********************/




    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/




    private void ListaDeClientesDelDia() {

        clientesList = new ArrayList<>();

        clientesList.add(new Clientes(38765245,1, R.mipmap.cliente_img1_hombrebarbudo_512px,"Kanje",
                "George", "Calle 6 entre 9 y 11","Belgrano",
                "Casi esquina 11, en mano izquierda con un portón de chapa. Tocar timbre 2","364","4445654",
                "No tiene correo"));

        clientesList.add(new Clientes(35543234,2,R.mipmap.cliente_img2_hombrerulos_512px, "Calamaro",
                "Andrés","Calle 29 entre 12 y 14", "Centro","Casa azul con frente floreado",
                "364","4665543","No tiene correo"));

        clientesList.add(new Clientes(38235123,3,R.mipmap.cliente_img3_profesor_512px, "Smoker",
                "Alejandro","Calle 29 entre 12 y 10","Centro","Casa de 2 pisos, al lado tiene un portón como garage","364","4654321","No tiene correo"));

        clientesList.add(new Clientes(32456953,4,R.mipmap.cliente_img4_policia_512px, "Gomez",
                "Alberto","Calle 1 entre 12 y 10", "Centro", "Comisaría N°92",
                "364","4911999","No tiene correo"));

        clientesList.add(new Clientes(35444999,5,R.mipmap.cliente_img5_sra_512px, "Ruiz",
                "Viviana", "Calle 8 entre 22 y 24", "La Madrid", "Casa blanca con portón de madera",
                "364","4965433", "vivana_ruiz@gmail.com"));

        clientesList.add(new Clientes(34445885,6,R.mipmap.cliente_img6_profesor_512px, "Iznardo",
                "Natanael","Calle 19 esquina 14","Centro", "Ferretería Norte",
                "364","4595959","natanaeliznardo@gmail.com"));

        clientesList.add(new Clientes(27456432,7,R.mipmap.cliente_img7_abuelo_512px, "Montiel",
                "Timoteo", "Calle 12 entre 21 y 23","Centro", "Al lado de la universidad Siglo 21", "364","4461211","No tiene correo"));

        clientesList.add(new Clientes(38456321,8,R.mipmap.cliente_img8_chica_512px, "Medina",
                "Belén","Calle 11 entre 3 y 5", "Loma Linda","Al lado del papa Francisco",
                "364","4456788","No tiene correo"));

        clientesList.add(new Clientes(34567965,9,R.mipmap.cliente_img9_dentista_512px, "Uribarri",
                "Celeste","Calle 12 entre 47 y 49","San Martín","Mansión de 2 pisos con letrero con el nombre de la cliente","364","4998822","No tiene correo"));

        clientesList.add(new Clientes(34567965,10,R.mipmap.cliente_img10_doctor_512px, "Guzmán",
                "Leonardo","Calle 9 entre 12 y 14 ","Belgrano","Al lado de estudio contable Sosa","364","4667788","No tiene correo"));



    }/****************** FIN DE LA FUNCIÓN ListaCompletaDeClientes() ***************/





    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/



    // Esta función es llamada cuando finalice la activity que fue llamada con startActivityForResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == REALIZAR_VENTA) {

            if (resultCode == RESULT_OK) { // Activity.RESULT_OK


                //En caso de que el resultado fuera correcto
                showDropoffDialog();


            } else{

                //En caso de que el resultado fuera INCORRECTO o no esperado
                showDropoffDialog();
            }

        } /************** FIN DEL if(requestCode == REALIZAR_VENTA) *****************/


        /*********************************************************************/
        /*********************************************************************/
        /*********************************************************************/
        /*********************************************************************/
        /*********************************************************************/

        // check that it is the SecondActivity with an OK result
        if (requestCode == MAPA_REPARTIDORES) {

            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                //En caso de que el resultado fuera correcto
                showDropoffDialog();


            } else{

                //En caso de que el resultado fuera INCORRECTO o no esperado
                showDropoffDialog();



            }

        } /************** FIN DEL if(requestCode == MAPA_REPARTIDORES) *****************/





    }/************************ FIN DEL onActivityResult() *********************/




    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/



    @Override
    public void onFeedbackOpened() {

    }

    @Override
    public void onFeedbackCancelled() {


    }

    @Override
    public void onFeedbackSent(FeedbackItem feedbackItem) {

    }




    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/
    /********************************************************************************************/





} /********************** FIN DE LA Activity NavigationRepartidores *********************/

