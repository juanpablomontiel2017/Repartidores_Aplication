package com.example.jumpi.repartidores_aplication;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.widget.LinearLayout;
import android.widget.Toast;

// classes needed to initialize map
import com.mapbox.api.directions.v5.DirectionsCriteria;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;


// classes needed to add the location component
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.modes.CameraMode;

// classes needed to add a marker
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;

// classes to calculate a route
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;

// classes needed to launch navigation UI
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

public class Mapa_Repartidores extends AppCompatActivity implements OnMapReadyCallback,
             MapboxMap.OnMapClickListener, PermissionsListener{

    // variables for adding location layer
    private MapView mapView;

    private MapboxMap mapboxMap;

    private LinearLayout linearLayout;

    private LocationComponent locationComponent;

    private PermissionsManager permissionsManager;

    private DirectionsRoute currentRoute;

    private Button button;

    private NavigationMapRoute navigationMapRoute;

    private static final String TAG = "DirectionsActivity";

    private List<Point> points = new ArrayList<>();


    Point originPoint ,destinationPoint;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(getApplicationContext(),getString(R.string.access_token_repartidor));


        setContentView(R.layout.activity_mapa_repartidores);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        mapView = findViewById(R.id.mapViewRepartidores);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);






    }/************** FIN DEL onCreate() **********/






    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/



    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {


        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();

    }



    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/



    @Override
    public void onPermissionResult(boolean granted) {


        if(granted){


            enableLocationComponent(mapboxMap.getStyle());


        }//Fin del if


        else {

            Toast.makeText(getApplicationContext(), "Permiso denegado", Toast.LENGTH_LONG).show();
            finish();

        }//Fin del else




    } /**************** FIN DEL MÉTODO onPermissionResult() *****************/




    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/




    @SuppressWarnings( {"MissingPermission"})
    @Override
    public boolean onMapClick(@NonNull LatLng point) {

        Point destinationPoint = Point.fromLngLat(point.getLongitude(), point.getLatitude());
        Point originPoint = Point.fromLngLat(locationComponent.getLastKnownLocation().getLongitude(),
                locationComponent.getLastKnownLocation().getLatitude());

        GeoJsonSource source = mapboxMap.getStyle().getSourceAs("destination-source-id");

        if (source != null) {

            source.setGeoJson(Feature.fromGeometry(destinationPoint));

        }

        getRoute(originPoint, destinationPoint);

        button.setEnabled(true);
        button.setBackgroundResource(R.color.mapboxBlue);

        return true;

    }/************** FIN DE LA FUNCIÓN onMapClick() **************************/



    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/



    private void getRoute(Point origin, Point destination) {



        NavigationRoute.builder(this)
                .accessToken(Mapbox.getAccessToken())
                .origin(origin)
                .destination(destination)
                .build()


                .getRoute(new Callback<DirectionsResponse>() {



                    @Override
                    public void onResponse(Call<DirectionsResponse> call, Response<DirectionsResponse> response) {



                        // You can get the generic HTTP info about the response
                        Log.d(TAG, "Response code: " + response.code());

                        if (response.body() == null) {

                            Log.e(TAG, "No routes found, make sure you set the right user and access token.");

                            return;

                        } else if (response.body().routes().size() < 1) {

                            Log.e(TAG, "No routes found");

                            return;

                        }

                        currentRoute = response.body().routes().get(0);

                        // Draw the route on the map
                        if (navigationMapRoute != null) {

                            navigationMapRoute.removeRoute();

                        } else {

                            navigationMapRoute = new NavigationMapRoute(null, mapView, mapboxMap, R.style.NavigationMapRoute);
                        }


                        navigationMapRoute.addRoute(currentRoute);




                        /*
                        if (response.body() != null && response.body().routes().size() > 1 ) {


                            currentRoute = response.body().routes().get(0);

                            if(navigationMapRoute != null){


                                navigationMapRoute.removeRoute();


                            }

                            else {


                                navigationMapRoute = new NavigationMapRoute(null,mapView,mapboxMap,R.style.NavigationMapRoute);


                            }

                            navigationMapRoute.addRoute(currentRoute);


                        }*/

                    }



                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable throwable) {

                        Log.e(TAG, "Error: " + throwable.getMessage());

                    }
                });




    }/******************* FIN DE LA FUNCIÓN getRoute() *********************/



    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/





    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {


        this.mapboxMap = mapboxMap;

        this.mapboxMap.setMinZoomPreference(15);


        mapboxMap.setStyle(getString(R.string.navigation_guidance_day), new Style.OnStyleLoaded() {

            @Override
            public void onStyleLoaded(@NonNull Style style) {

                enableLocationComponent(style);

                addDestinationIconSymbolLayer(style);

                mapboxMap.addOnMapClickListener(Mapa_Repartidores.this);

                button = findViewById(R.id.btn_comenzar_reparto);
                button.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {

                        boolean simulateRoute = true;

                        NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                                .directionsRoute(currentRoute)
                                .shouldSimulateRoute(simulateRoute)
                                .build();

                        // Call this method with Context from within an Activity
                        NavigationLauncher.startNavigation(Mapa_Repartidores.this, options);

                    }

                });



            }


        });


    }/************** FIN DE LA FUNCIÓN onMapReady() **************************/




    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/





    private void addDestinationIconSymbolLayer(@NonNull Style loadedMapStyle) {

        loadedMapStyle.addImage("destination-icon-id",
                BitmapFactory.decodeResource(this.getResources(), R.drawable.mapbox_marker_icon_default));

        GeoJsonSource geoJsonSource = new GeoJsonSource("destination-source-id");
        loadedMapStyle.addSource(geoJsonSource);

        SymbolLayer destinationSymbolLayer = new SymbolLayer("destination-symbol-layer-id", "destination-source-id");
        destinationSymbolLayer.withProperties(
                iconImage("destination-icon-id"),
                iconAllowOverlap(true),
                iconIgnorePlacement(true)
        );

        loadedMapStyle.addLayer(destinationSymbolLayer);

    } /************** FIN DE LA FUNCIÓN addDestinationIconSymbolLayer() **************************/






    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/






    private void enableLocationComponent(@NonNull Style loadedMapStyle){


        if(PermissionsManager.areLocationPermissionsGranted(this)){

            locationComponent = mapboxMap.getLocationComponent();

            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                return;

            }//Fin del if


            locationComponent.activateLocationComponent(this,loadedMapStyle);

            locationComponent.setLocationComponentEnabled(true);

            locationComponent.setCameraMode(CameraMode.TRACKING);


        }//Fin del primer if

        else {

            permissionsManager = new PermissionsManager(Mapa_Repartidores.this);

            permissionsManager.requestLocationPermissions(this);


        }



    }/************** FIN DE LA FUNCIÓN enableLocationComponent() **************************/




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        permissionsManager.onRequestPermissionsResult(requestCode,permissions,grantResults);


//      super.onRequestPermissionsResult(requestCode, permissions, grantResults);



    }





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







}
