package com.example.jumpi.repartidores_aplication;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.api.directions.v5.models.RouteLeg;
import com.mapbox.api.directions.v5.models.RouteOptions;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Geometry;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.utils.PolylineUtils;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.BubbleLayout;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.OnCameraTrackingChangedListener;
import com.mapbox.mapboxsdk.location.OnLocationClickListener;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.LineLayer;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.utils.BitmapUtils;
import com.mapbox.mapboxsdk.utils.ColorUtils;
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import timber.log.Timber;

import static com.mapbox.mapboxsdk.style.expressions.Expression.eq;
import static com.mapbox.mapboxsdk.style.expressions.Expression.get;
import static com.mapbox.mapboxsdk.style.expressions.Expression.literal;
import static com.mapbox.mapboxsdk.style.layers.Property.ICON_ANCHOR_BOTTOM;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAnchor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.lineWidth;

// classes needed to initialize map
// classes needed to add the location component
// classes needed to add a marker
// classes to calculate a route
// classes needed to launch navigation UI



public class Mapa_Repartidores extends AppCompatActivity implements OnMapReadyCallback
        /*MapboxMap.OnMapClickListener*/, OnLocationClickListener, PermissionsListener, OnCameraTrackingChangedListener {

    /********************* DECLARACIÓN DE VARIABLES GLOBALES **********************/


    private View View_Separador;

    private ClientesRecyclerViewAdapter_ListaDeClientesDelDía adapter;

    private List<Clientes> clientesList;


    private List<Point> puntos_clientes = new ArrayList<>();


    private Integer[]  OD = {0,5};

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







    /************************** COMIENZO DEL onCreate() ***************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Mapbox.getInstance(getApplicationContext(), getString(R.string.access_token_repartidor));




        setContentView(R.layout.activity_mapa_repartidores);


        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Samuel Arévalo");
        setSupportActionBar(toolbar);


        mapView = findViewById(R.id.mapViewRepartidores);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);




    }/*********************************** FIN DEL onCreate() ****************************************/




    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/
    /************************************************************************/





    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {


        this.mapboxMap = mapboxMap;

        //this.mapboxMap.setMinZoomPreference(15);

        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {

            @Override
            public void onStyleLoaded(@NonNull Style style) {


                enableLocationComponent(style);


                BotonComenzarReparto = findViewById(R.id.btn_comenzar_reparto);

                BotonComenzarReparto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent (Mapa_Repartidores.this, NavigationRepartidores.class);

                        intent.putExtra("Activity","Mapa_Repartidores");

                        setResult(RESULT_OK, intent);

                        startActivity(intent);

                        //Debo finalizar la activity para que actúe el ActivityForResult
                        finish();

                    }
                });


            }


        });


    }/********************************* FIN DE LA FUNCIÓN onMapReady() *************************************/








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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mapa_repartidores, menu);
        return true;
    } /**************FIN DE LA FUNCIÓN onCreateOptionsMenu()********/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cliente_mapa_repartidores) {



            Intent intent = new Intent (Mapa_Repartidores.this, BuscarClienteParaRealizarVenta.class);

            startActivity(intent);


            return true;

        }




        return super.onOptionsItemSelected(item);


    } /*************************FIN DE LA FUNCIÓN onOptionsItemSelected()*******************/







}/************************************* FIN DE LA ACTIVITY  Mapa_Repartidores() **********************************/
