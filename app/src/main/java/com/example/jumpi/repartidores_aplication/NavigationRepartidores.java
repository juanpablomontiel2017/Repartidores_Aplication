package com.example.jumpi.repartidores_aplication;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;

import com.android.volley.Response;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.services.android.navigation.ui.v5.NavigationView;
import com.mapbox.services.android.navigation.ui.v5.NavigationViewOptions;
import com.mapbox.services.android.navigation.ui.v5.OnNavigationReadyCallback;
import com.mapbox.services.android.navigation.ui.v5.listeners.NavigationListener;
import com.mapbox.services.android.navigation.ui.v5.listeners.RouteListener;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.mapbox.services.android.navigation.v5.routeprogress.ProgressChangeListener;
import com.mapbox.services.android.navigation.v5.routeprogress.RouteProgress;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static com.mapbox.geojson.Point.fromLngLat;

public class NavigationRepartidores extends AppCompatActivity implements OnNavigationReadyCallback,
        NavigationListener, RouteListener, ProgressChangeListener {




    private NavigationView navigationView;
    private boolean dropoffDialogShown;
    private Location lastKnownLocation;

    private List<Point> puntos_clientes = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.Theme_AppCompat_NoActionBar);
        super.onCreate(savedInstanceState);


        initNightMode();

        //Punto de origen: envasadora AquaVital
        puntos_clientes.add(Point.fromLngLat(-60.446815, -26.784306));

        puntos_clientes.add(Point.fromLngLat(-60.448123,-26.784327));
        puntos_clientes.add(Point.fromLngLat(-60.448241,-26.790556));
        puntos_clientes.add(Point.fromLngLat(-60.448427,-26.792986));
        puntos_clientes.add(Point.fromLngLat(-60.446893,-26.792885));


        puntos_clientes.add(Point.fromLngLat(-60.444785,-26.794505));
        puntos_clientes.add(Point.fromLngLat(-60.442989,-26.792107));
        puntos_clientes.add(Point.fromLngLat(-60.442023,-26.793726));
        puntos_clientes.add(Point.fromLngLat(-60.442989,-26.792107));
        puntos_clientes.add(Point.fromLngLat(-60.438246,-26.794419));


        setContentView(R.layout.activity_navigation_repartidores);

        navigationView = findViewById(R.id.navigationView);
        navigationView.onCreate(savedInstanceState);
        navigationView.initialize(this);






    }/************************** FIN DEL onCreate() **************************************/




    private void initNightMode() {
        int nightMode = retrieveNightModeFromPreferences();
        AppCompatDelegate.setDefaultNightMode(nightMode);
    }


    private int retrieveNightModeFromPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getInt("Modo Nocturno" ,AppCompatDelegate.MODE_NIGHT_AUTO);
    }




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

    @Override
    public void onNavigationReady(boolean isRunning) {
        fetchRoute(puntos_clientes.remove(0), puntos_clientes.remove(0));
    }

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

    @Override
    public void onArrival() {
        if (!dropoffDialogShown && !puntos_clientes.isEmpty()) {
            showDropoffDialog();
            dropoffDialogShown = true; // Accounts for multiple arrival events
            Toast.makeText(this, "You have arrived!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProgressChange(Location location, RouteProgress routeProgress) {
        lastKnownLocation = location;
    }

    private void startNavigation(DirectionsRoute directionsRoute) {
        NavigationViewOptions navigationViewOptions = setupOptions(directionsRoute);
        navigationView.startNavigation(navigationViewOptions);
    }

    private void showDropoffDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage(getString(R.string.dropoff_dialog_text));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.dropoff_dialog_positive_text),
                (dialogInterface, in) -> fetchRoute(getLastKnownLocation(), puntos_clientes.remove(0)));
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.dropoff_dialog_negative_text),
                (dialogInterface, in) -> {
                    // Do nothing
                });

        alertDialog.show();
    }

    private void fetchRoute(Point origin, Point destination) {
        NavigationRoute.builder(this)
                .accessToken(Mapbox.getAccessToken())
                .origin(origin)
                .destination(destination)
                .alternatives(true)
                .build()
                .getRoute(new SimplifiedCallback() {
                    @Override
                    public void onResponse(Call<DirectionsResponse> call, retrofit2.Response<DirectionsResponse> response) {

                        startNavigation(response.body().routes().get(0));

                    }

                });
    }

    private NavigationViewOptions setupOptions(DirectionsRoute directionsRoute) {
        dropoffDialogShown = false;

        NavigationViewOptions.Builder options = NavigationViewOptions.builder();
        options.directionsRoute(directionsRoute)
                .navigationListener(this)
                .progressChangeListener(this)
                .routeListener(this)
                .shouldSimulateRoute(true);
        return options.build();
    }

    private Point getLastKnownLocation() {
        return Point.fromLngLat(lastKnownLocation.getLongitude(), lastKnownLocation.getLatitude());
    }












}
