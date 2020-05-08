package com.example.jumpi.repartidores_aplication;


import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

// classes needed to initialize map

import com.android.volley.Response;
import com.github.mikephil.charting.components.MarkerView;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.utils.PolylineUtils;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.BubbleLayout;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.Polyline;
import com.mapbox.mapboxsdk.annotations.PolylineOptions;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.OnCameraTrackingChangedListener;
import com.mapbox.mapboxsdk.location.OnLocationClickListener;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;


// classes needed to add the location component
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.location.LocationComponent;

// classes needed to add a marker
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import static com.mapbox.geojson.Point.fromLngLat;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.layers.Property.ICON_ANCHOR_BOTTOM;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAnchor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineWidth;
import static java.nio.file.Paths.get;

// classes to calculate a route
import com.mapbox.mapboxsdk.utils.BitmapUtils;
import com.mapbox.mapboxsdk.utils.ColorUtils;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncher;
import com.mapbox.services.android.navigation.ui.v5.NavigationLauncherOptions;
import com.mapbox.services.android.navigation.ui.v5.NavigationView;
import com.mapbox.services.android.navigation.ui.v5.map.NavigationMapboxMap;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.services.android.navigation.v5.navigation.MapboxNavigation;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;


import retrofit2.Call;
import retrofit2.Callback;
import timber.log.Timber;

// classes needed to launch navigation UI
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.Navigation;


import static com.mapbox.mapboxsdk.style.expressions.Expression.eq;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.layers.Property.ICON_ANCHOR_BOTTOM;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAnchor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;



public class Mapa_Repartidores extends AppCompatActivity implements OnMapReadyCallback
        /*MapboxMap.OnMapClickListener*/, OnLocationClickListener, PermissionsListener, OnCameraTrackingChangedListener {

    /********************* DECLARACIÓN DE VARIABLES GLOBALES **********************/

    private MapView mapView;
    private MapboxMap mapboxMap;


    // variables for adding location layer
    private LocationComponent locationComponent;
    private PermissionsManager permissionsManager;
    private boolean isInTrackingMode;

    private DirectionsRoute currentRoute;

    private Button BotonComenzarReparto;

    private NavigationMapRoute navigationMapRoute;

    private static final String TAG = "DirectionsActivity";




    private static final String GEOJSON_SOURCE_ID = "GEOJSON_SOURCE_ID";
    private static final String MARKER_IMAGE_ID = "MARKER_IMAGE_ID";
    private static final String MARKER_LAYER_ID = "MARKER_LAYER_ID";
    private static final String CALLOUT_LAYER_ID = "CALLOUT_LAYER_ID";
    private static final String PROPERTY_SELECTED = "selectedState";
    private static final String PROPERTY_NAME = "name";
    private static final String PROPERTY_CAPITAL = "referencia";

    private GeoJsonSource source;
    private FeatureCollection featureCollection;



    /****************** COMIENZO DEL onCreate() *******************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(getApplicationContext(), getString(R.string.access_token_repartidor));


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





    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {


        this.mapboxMap = mapboxMap;

        //this.mapboxMap.setMinZoomPreference(15);

        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {

            @Override
            public void onStyleLoaded(@NonNull Style style) {


                enableLocationComponent(style);


                new LoadGeoJson(Mapa_Repartidores.this).execute();
                new LoadGeoJsonDataTask(Mapa_Repartidores.this).execute();
                //mapboxMap.addOnMapClickListener(Mapa_Repartidores.this);



                //Point originPoint = fromLngLat(locationComponent.getLastKnownLocation().getLongitude(),
                //        locationComponent.getLastKnownLocation().getLatitude());

                //Point originPoint = fromLngLat(-60.446815, -26.784306) ;

                //Point destinationPoint = fromLngLat(-60.429515,-26.76976) ;

                //getRoute(originPoint,destinationPoint);


                BotonComenzarReparto = findViewById(R.id.btn_comenzar_reparto);


                BotonComenzarReparto.setEnabled(true);
                BotonComenzarReparto.setBackgroundResource(R.color.mapboxBlue);

                BotonComenzarReparto.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {

/*
                        initNightMode();

                        boolean simulateRoute = true;


                        NavigationLauncherOptions options = NavigationLauncherOptions.builder()
                                .directionsRoute(currentRoute)
                                .shouldSimulateRoute(simulateRoute)
                                .build();

                        // Call this method with Context from within an Activity
                        NavigationLauncher.startNavigation(Mapa_Repartidores.this, options);
*/
                        Intent intent = new Intent (Mapa_Repartidores.this, NavigationRepartidores.class);
                        startActivity(intent);


                    }


                });



            }

        });

    }/********************************* FIN DE LA FUNCIÓN onMapReady() *************************************/





    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/



    private void initNightMode() {
        int nightMode = retrieveNightModeFromPreferences();
        AppCompatDelegate.setDefaultNightMode(nightMode);
    }


    private int retrieveNightModeFromPreferences() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getInt("Modo Nocturno" ,AppCompatDelegate.MODE_NIGHT_AUTO);
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




    /*********************************************************************************/
    /*********************************************************************************/
    /*********************************************************************************/
    /**************Obtener la ubicación actual del REPARTIDOR y customización de íconos
     ********************************de localización**********************************/
    /*********************************************************************************/
    /*********************************************************************************/
    /*********************************************************************************/


    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            // Create and customize the LocationComponent's options
            LocationComponentOptions customLocationComponentOptions = LocationComponentOptions.builder(this)
                    .elevation(5)
                    .accuracyAlpha(.6f)
                    .accuracyColor(Color.RED)
                    .foregroundDrawable(R.mipmap.truck_repartidor_64px_opcion_uno)
                    .build();

            // Get an instance of the component
            locationComponent = mapboxMap.getLocationComponent();

            LocationComponentActivationOptions locationComponentActivationOptions =
                    LocationComponentActivationOptions.builder(this, loadedMapStyle)
                            .locationComponentOptions(customLocationComponentOptions)
                            .build();

            // Activate with options
            locationComponent.activateLocationComponent(locationComponentActivationOptions);

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);

            // Add the location icon click listener
            locationComponent.addOnLocationClickListener(this);

            // Add the camera tracking listener. Fires if the map camera is manually moved.
            locationComponent.addOnCameraTrackingChangedListener(this);

            findViewById(R.id.back_to_camera_tracking_mode).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isInTrackingMode) {
                        isInTrackingMode = true;
                        locationComponent.setCameraMode(CameraMode.TRACKING);
                        locationComponent.zoomWhileTracking(16f);
                        Toast.makeText(Mapa_Repartidores.this, getString(R.string.tracking_enabled),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Mapa_Repartidores.this, getString(R.string.tracking_already_enabled),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }

    }/************** FIN DE LA FUNCIÓN enableLocationComponent() **************************/





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
    public void onLocationComponentClick() {
        if (locationComponent.getLastKnownLocation() != null) {
            Toast.makeText(this, String.format(getString(R.string.current_location),
                    locationComponent.getLastKnownLocation().getLatitude(),
                    locationComponent.getLastKnownLocation().getLongitude()), Toast.LENGTH_LONG).show();
        }
    }/************** FIN DE LA FUNCIÓN onLocationComponentClick() **************************/




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
    public void onCameraTrackingDismissed() {

        isInTrackingMode = false;

    }/************** FIN DE LA FUNCIÓN onCameraTrackingDismissed() **************************/




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
    public void onCameraTrackingChanged(int currentMode) {

        // Empty on purpose

    }/************** FIN DE LA FUNCIÓN onCameraTrackingChanged() **************************/




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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }/************** FIN DE LA FUNCIÓN onRequestPermissionsResult() **************************/



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

    }/************** FIN DE LA FUNCIÓN onExplanationNeeded() **************************/




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
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {

                @Override
                public void onStyleLoaded(@NonNull Style style) {

                    enableLocationComponent(style);
                }

            });

        } else {

            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();

            finish();

        }

    }/************** FIN DE LA FUNCIÓN onPermissionResult() **************************/





    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/


/*
    @Override
    public boolean onMapClick(@NonNull LatLng point) {

        Point destinationPoint = fromLngLat(point.getLongitude(), point.getLatitude());
        Point originPoint = fromLngLat(locationComponent.getLastKnownLocation().getLongitude(),
                locationComponent.getLastKnownLocation().getLatitude());


        getRoute(originPoint, destinationPoint);

        button.setEnabled(true);
        button.setBackgroundResource(R.color.mapboxBlue);

        return handleClickIcon(mapboxMap.getProjection().toScreenLocation(point));

    } */ /************** FIN DE LA FUNCIÓN onMapClick() **************************/

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
                    public void onResponse(Call<DirectionsResponse> call, retrofit2.Response<DirectionsResponse> response) {
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
                    }




                    @Override
                    public void onFailure(Call<DirectionsResponse> call, Throwable throwable) {
                        Log.e(TAG, "Error: " + throwable.getMessage());
                    }
                });



    }/************** FIN DE LA FUNCIÓN getRoute() **************************/




    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/






    /**
     * Sets up all of the sources and layers needed for this example
     *
     * @param collection the FeatureCollection to set equal to the globally-declared FeatureCollection
     */
    public void setUpData(final FeatureCollection collection) {

        featureCollection = collection;
        if (mapboxMap != null) {

            mapboxMap.getStyle(style -> {

                setupSource(style);
                setUpImage(style);
                setUpMarkerLayer(style);
                setUpInfoWindowLayer(style);

            });

        }

    }/************** FIN DE LA FUNCIÓN setUpData() **************************/



    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/




    /**
     * Adds the GeoJSON source to the map
     */
    private void setupSource(@NonNull Style loadedStyle) {

        source = new GeoJsonSource(GEOJSON_SOURCE_ID, featureCollection);

        loadedStyle.addSource(source);

    }/************** FIN DE LA FUNCIÓN setupSource() **************************/




    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/



    public Bitmap getBitmap() {

        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_marker_cliente_map_repartidores, null);

        Bitmap mBitmap = BitmapUtils.getBitmapFromDrawable(drawable);

        return mBitmap;

    }/************** FIN DE LA FUNCIÓN getBitmap() **************************/



    /**
     * Adds the marker image to the map for use as a SymbolLayer icon
     */
    private void setUpImage(@NonNull Style loadedStyle) {

        loadedStyle.addImage(MARKER_IMAGE_ID, getBitmap());

    }/************** FIN DE LA FUNCIÓN setUpImage() **************************/





    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/




    /**
     * Updates the display of data on the map after the FeatureCollection has been modified
     */
    private void refreshSource() {

        if (source != null && featureCollection != null) {

            source.setGeoJson(featureCollection);
        }

    }/************** FIN DE LA FUNCIÓN refreshSource() **************************/




    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/




    /**
     * Setup a layer with maki icons
     */
    private void setUpMarkerLayer(@NonNull Style loadedStyle) {

        loadedStyle.addLayer(new SymbolLayer(MARKER_LAYER_ID, GEOJSON_SOURCE_ID)
                .withProperties(
                        iconImage(MARKER_IMAGE_ID),
                        iconAllowOverlap(true),
                        iconOffset(new Float[] {0f, -8f})
                ));

    }/************** FIN DE LA FUNCIÓN setUpMarkerLayer() **************************/



    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/





    /**
     * Setup a layer with Android SDK call-outs
     * <p>
     * name of the feature is used as key for the iconImage
     * </p>
     */

    private void setUpInfoWindowLayer(@NonNull Style loadedStyle) {

        loadedStyle.addLayer(new SymbolLayer(CALLOUT_LAYER_ID, GEOJSON_SOURCE_ID)
                .withProperties(
                        /* show image with id title based on the value of the name feature property */
                        iconImage("{name}"),

                        /* set anchor of icon to bottom-left */
                        iconAnchor(ICON_ANCHOR_BOTTOM),

                        /* all info window and marker image to appear at the same time*/
                        iconAllowOverlap(true),

                        /* offset the info window to be above the marker */
                        iconOffset(new Float[] {-2f, -28f})
                )

                /* add a filter to show only when selected feature property is true */
                .withFilter(eq((get(PROPERTY_SELECTED)), literal(true))));

    }/************** FIN DE LA FUNCIÓN setUpInfoWindowLayer() **************************/




    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/





    /**
     * This method handles click events for SymbolLayer symbols.
     * <p>
     * When a SymbolLayer icon is clicked, we moved that feature to the selected state.
     * </p>
     *
     * @param screenPoint the point on screen clicked
     */

    private boolean handleClickIcon(PointF screenPoint) {

            List<Feature> features = mapboxMap.queryRenderedFeatures(screenPoint, MARKER_LAYER_ID);

            if (!features.isEmpty()) {

                String name = features.get(0).getStringProperty(PROPERTY_NAME);

                List<Feature> featureList = featureCollection.features();

                if (featureList != null) {

                    for (int i = 0; i < featureList.size(); i++) {

                        Geometry geometry = featureList.get(i).geometry();

                        if (!geometry.type().equals("LineString")) {

                            if (featureList.get(i).getStringProperty(PROPERTY_NAME).equals(name)) {

                                if (featureSelectStatus(i)) {

                                    setFeatureSelectState(featureList.get(i), false);

                                } else {

                                    setSelected(i);

                                }//Fin del else



                            }//Fin del if(featureList.get(i).getStringProperty(PROPERTY_NAME).equals(name))

                        }



                    }//Fin del for

                }//Fin del if(featureList != null)

                return true;

            } else {

                return false;

            }



    }/*********** FIN DE LA FUNCIÓN handleClickIcon() *****************/





    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/





    /**
     * Set a feature selected state.
     *  @param index the index of selected feature
     *
     */
    private void setSelected(int index) {

        if (featureCollection.features() != null) {

            Feature feature = featureCollection.features().get(index);

            setFeatureSelectState(feature, true);

            refreshSource();

        }

    }/************** FIN DE LA FUNCIÓN setSelected() **************************/






    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/


    /**
     * Selects the state of a feature
     *
     * @param feature the feature to be selected.
     */
    private void setFeatureSelectState(Feature feature, boolean selectedState) {

        if (feature.properties() != null) {

            feature.properties().addProperty(PROPERTY_SELECTED, selectedState);

            refreshSource();

        }

    }/************** FIN DE LA FUNCIÓN setFeatureSelectState() **************************/



    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/





    /**
     * Checks whether a Feature's boolean "selected" property is true or false
     *
     * @param index the specific Feature's index position in the FeatureCollection's list of Features.
     * @return true if "selected" is true. False if the boolean property is false.
     */
    private boolean featureSelectStatus(int index) {

        if (featureCollection == null) {

            return false;

        }

        boolean valor_retornado = featureCollection.features().get(index).getBooleanProperty(PROPERTY_SELECTED);

        return valor_retornado;

    }/************** FIN DE LA FUNCIÓN featureSelectStatus() **************************/





    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/




    /**
     * Invoked when the bitmaps have been generated from a view.
     */
    public void setImageGenResults(HashMap<String, Bitmap> imageMap) {

        if (mapboxMap != null) {

            mapboxMap.getStyle(style -> {

                // calling addImages is faster as separate addImage calls for each bitmap.
                //style.addImages(imageMap);




            });

        }

    }/************** FIN DE LA FUNCIÓN setImageGenResults() **************************/


    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/




    /**
     * AsyncTask to load data from the assets folder.
     */
    private static class LoadGeoJsonDataTask extends AsyncTask<Void, Void, FeatureCollection> {



        private final WeakReference<Mapa_Repartidores> activityRef;

        LoadGeoJsonDataTask(Mapa_Repartidores activity) {
            this.activityRef = new WeakReference<>(activity);
        }





        @Override
        protected FeatureCollection doInBackground(Void... params) {
            Mapa_Repartidores activity = activityRef.get();

            if (activity == null) {
                return null;
            }

            //String geoJson = loadGeoJsonFromAsset(activity, "waypoints_20_puntos.geojson");
            //return FeatureCollection.fromJson(geoJson);

            return null;

        }







        @Override
        protected void onPostExecute(FeatureCollection featureCollection) {
            super.onPostExecute(featureCollection);
            Mapa_Repartidores activity = activityRef.get();
            if (featureCollection == null || activity == null) {
                return;
            }

            // This example runs on the premise that each GeoJSON Feature has a "selected" property,
            // with a boolean value. If your data's Features don't have this boolean property,
            // add it to the FeatureCollection 's features with the following code:
            for (Feature singleFeature : featureCollection.features()) {
                singleFeature.addBooleanProperty(PROPERTY_SELECTED, true);
            }

            activity.setUpData(featureCollection);
            new GenerateViewIconTask(activity).execute(featureCollection);
        }






        static String loadGeoJsonFromAsset(Context context, String filename) {

            try {

                // Load GeoJSON file from local asset folder
                InputStream is = context.getAssets().open(filename);

                int size = is.available();

                byte[] buffer = new byte[size];

                is.read(buffer);

                is.close();

                return new String(buffer, Charset.forName("UTF-8"));

            } catch (Exception exception) {

                throw new RuntimeException(exception);

            }

        }


    }/************** FIN DE LA CLASE LoadGeoJsonDataTask() **************************/


    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/

    /**
     * AsyncTask to generate Bitmap from Views to be used as iconImage in a SymbolLayer.
     * <p>
     * Call be optionally be called to update the underlying data source after execution.
     * </p>
     * <p>
     * Generating Views on background thread since we are not going to be adding them to the view hierarchy.
     * </p>
     */

    private static class GenerateViewIconTask extends AsyncTask<FeatureCollection, Void, HashMap<String, Bitmap>> {

        private final HashMap<String, View> viewMap = new HashMap<>();
        private final WeakReference<Mapa_Repartidores> activityRef;
        private final boolean refreshSource;

        GenerateViewIconTask(Mapa_Repartidores activity, boolean refreshSource) {

            this.activityRef = new WeakReference<>(activity);
            this.refreshSource = refreshSource;

        }

        GenerateViewIconTask(Mapa_Repartidores activity) {
            this(activity, false);
        }
        @SuppressLint("WrongThread")
        @SuppressWarnings("WrongThread")
        @Override
        protected HashMap<String, Bitmap> doInBackground(FeatureCollection... params) {

            Mapa_Repartidores activity = activityRef.get();
            if (activity != null) {
                HashMap<String, Bitmap> imagesMap = new HashMap<>();
                LayoutInflater inflater = LayoutInflater.from(activity);

                FeatureCollection featureCollection = params[0];

                for (Feature feature : featureCollection.features()) {


                    BubbleLayout bubbleLayout = (BubbleLayout)
                            inflater.inflate(R.layout.symbol_layer_info_window_layout_callout, null);

                    String name = feature.getStringProperty(PROPERTY_NAME);

                    TextView titleTextView = bubbleLayout.findViewById(R.id.name_cliente);
                    titleTextView.setText(name);

                    String style = feature.getStringProperty(PROPERTY_CAPITAL);
                    TextView descriptionTextView = bubbleLayout.findViewById(R.id.description_cliente);
                    descriptionTextView.setText(style);

                    int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    bubbleLayout.measure(measureSpec, measureSpec);

                    float measuredWidth = bubbleLayout.getMeasuredWidth();

                    bubbleLayout.setArrowPosition(measuredWidth / 2 - 5);

                    Bitmap bitmap = SymbolGenerator.generate(bubbleLayout);
                    imagesMap.put(name, bitmap);
                    viewMap.put(name, bubbleLayout);


                }

                return imagesMap;

            } else {

                return null;

            }

        }




        @Override
        protected void onPostExecute(HashMap<String, Bitmap> bitmapHashMap) {

            super.onPostExecute(bitmapHashMap);

            Mapa_Repartidores activity = activityRef.get();

            if (activity != null && bitmapHashMap != null) {

                activity.setImageGenResults(bitmapHashMap);

                if (refreshSource) {

                    activity.refreshSource();

                }

            }

            Toast.makeText(activity, R.string.tap_on_marker_instruction, Toast.LENGTH_SHORT).show();
        }




    }/************** FIN DE LA CLASE GenerateViewIconTask() **************************/




    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/







    /**
     * Utility class to generate Bitmaps for Symbol.
     */
    private static class SymbolGenerator {

        /**
         * Generate a Bitmap from an Android SDK View.
         *
         * @param view the View to be drawn to a Bitmap
         * @return the generated bitmap
         */
        static Bitmap generate(@NonNull View view) {
            int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(measureSpec, measureSpec);

            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();

            view.layout(0, 0, measuredWidth, measuredHeight);
            Bitmap bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
            bitmap.eraseColor(Color.TRANSPARENT);
            Canvas canvas = new Canvas(bitmap);
            view.draw(canvas);
            return bitmap;
        }

    }/************** FIN DE LA CLASE SymbolGenerator() **************************/



    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/








    /*********************************************************************/
    /*********************************************************************/
    /*******************Comienzo para dibujar PolyLines*******************/
    /*********************************************************************/
    /*********************************************************************/



    private static class LoadGeoJson extends AsyncTask<Void, Void, FeatureCollection> {

        private WeakReference<Mapa_Repartidores> weakReference;



        LoadGeoJson(Mapa_Repartidores activity) {
            this.weakReference = new WeakReference<>(activity);
        }




        @Override
        protected FeatureCollection doInBackground(Void... voids) {
            try {
                Mapa_Repartidores activity = weakReference.get();
                if (activity != null) {
                    //InputStream inputStream = activity.getAssets().open("polyline_reparto.geojson");
                    //return FeatureCollection.fromJson(convertStreamToString(inputStream));
                }
            } catch (Exception exception) {
                Timber.e("Exception loading GeoJSON: %s", exception.toString());
            }
            return null;
        }



        static String convertStreamToString(InputStream is) {
            Scanner scanner = new Scanner(is).useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }





        @Override
        protected void onPostExecute(@Nullable FeatureCollection featureCollection) {
            super.onPostExecute(featureCollection);
            Mapa_Repartidores activity = weakReference.get();
            if (activity != null && featureCollection != null) {
                activity.drawLines(featureCollection);
            }
        }



    }/************** FIN DE LA CLASE LoadGeoJson() **************************/




    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/




    private void drawLines(@NonNull FeatureCollection featureCollection) {

        List<Feature> features = featureCollection.features();

        if (features != null && features.size() > 0) {
            Feature feature = features.get(0);
            drawBeforeSimplify(feature);
            drawSimplify(feature);
        }

    }/************** FIN DE LA FUNCIÓN drawLines() **************************/




    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/


    private void drawBeforeSimplify(@NonNull Feature lineStringFeature) {

        addLine("rawLine", lineStringFeature, "#8a8acb");

    }/************** FIN DE LA FUNCIÓN drawBeforeSimplify() **************************/



    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/


    private void drawSimplify(@NonNull Feature feature) {

        List<Point> points = ((LineString) Objects.requireNonNull(feature.geometry())).coordinates();

        List<Point> after = PolylineUtils.simplify(points, 0.001);

        addLine("simplifiedLine", Feature.fromGeometry(LineString.fromLngLats(after)), "#3bb2d0");

    }/************** FIN DE LA FUNCIÓN drawSimplify() **************************/




    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/
    /**************************************************************************/


    private void addLine(String layerId, Feature feature, String lineColorHex) {
        mapboxMap.getStyle(style -> {
            style.addSource(new GeoJsonSource(layerId, feature));
            style.addLayer(new LineLayer(layerId, layerId).withProperties(
                    lineColor(ColorUtils.colorToRgbaString(Color.parseColor(lineColorHex))),
                    lineWidth(4f)
            ));
        });

    }/************** FIN DE LA FUNCIÓN addLine() **************************/




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
        if (mapboxMap != null) {
            //mapboxMap.removeOnMapClickListener(this);
        }
        mapView.onDestroy();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
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





    }/**************************** FIN DE LA ACTIVITY **************************/
