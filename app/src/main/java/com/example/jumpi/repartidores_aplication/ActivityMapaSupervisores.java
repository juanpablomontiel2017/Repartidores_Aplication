package com.example.jumpi.repartidores_aplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ActivityMapaSupervisores extends AppCompatActivity {


    /************** DECLARACIÓN DE VARIABLES GLOBALES ****************/

    public static TextView mensaje1;

    TextView mensaje2;

    private MapView mapView;


    /********************* COMIENZO DEL onCreate() **************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(this, getString(R.string.access_tokens_supervisor));

        setContentView(R.layout.activity_mapa__supervisores);




        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {

                IconFactory iconFactory = IconFactory.getInstance(ActivityMapaSupervisores.this);
                //Icon icon_truck_yellow = iconFactory.fromResource(R.mipmap.camion_64px);
                Icon icon_truck_red = iconFactory.fromResource(R.mipmap.truck_rojo);




                LatLng Repartidor_Samuel = new LatLng(-26.7908,-60.4414);
                mapboxMap.addMarker(new MarkerOptions()

                        .position(new LatLng(Repartidor_Samuel))
                        .title("Samuel")
                        .snippet("")
                        .icon(icon_truck_red));




                final LatLng Repartidor_Marcelo = new LatLng(-26.795230,-60.441513);
                mapboxMap.addMarker(new MarkerOptions()

                        .position(new LatLng(Repartidor_Marcelo))
                        .title("Marcelo")
                        .snippet("")
                        .icon(icon_truck_red));


                LatLng Repartidor_Gastón = new LatLng(-26.778823,-60.435162);
                mapboxMap.addMarker(new MarkerOptions()

                        .position(new LatLng(Repartidor_Gastón))
                        .title("Gastón")
                        .snippet("")
                        .icon(icon_truck_red));







                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {

                        // Map is set up and the style has loaded. Now you can add data or make other map adjustments.




                    }


                });
            }
        });







        mensaje1 = (TextView) findViewById(R.id.mensaje_id);
        mensaje2 = (TextView) findViewById(R.id.mensaje_id2);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);

        }

        else {


            locationStart();


        }







    }/********************************** FIN DEL onCreate() *******************************************/


    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/


    private void locationStart() {


        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Localizacion Local = new Localizacion();

        Local.setMapaSupervisoresActivity(this);

        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!gpsEnabled) {

            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);

        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }


        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);

        mensaje1.setText("Localización agregada");
        mensaje2.setText("");



    }/******************* FIN DE LA FUNCIÓN locationStart() ****************/



    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == 1000) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                locationStart();

                return;

            }
        }


    }/******************** FIN DE LA FUNCIÓN onRequestPermissionsResult() ********************/



    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/



    public void setLocation(Location loc) {


        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {

            try {

                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);

                if (!list.isEmpty()) {

                    Address DirCalle = list.get(0);

                    mensaje2.setText("Mi direccion es: \n"
                            + DirCalle.getAddressLine(0));

                }

            }


            catch (IOException e) {
                e.printStackTrace();
            }

        }




    }/***************** FIN DE LA FUNCIÓN setLocation() ******************/



    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/









    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }




    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }





    @Override
    public void onPause() {

        super.onPause();
        mapView.onPause();

    }




    @Override
    public void onStop() {

        super.onStop();
        mapView.onStop();

    }





    @Override
    public void onLowMemory() {

        super.onLowMemory();
        mapView.onLowMemory();

    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }



    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/
    /******************************************************************************/



}/************************** FIN DE LA ACTIVITY ActivityMapaSupervisores ****************************/


