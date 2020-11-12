package com.example.jumpi.repartidores_aplication;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

import static com.example.jumpi.repartidores_aplication.ActivityMapaSupervisores.mensaje1;

public class Localizacion implements LocationListener {


    ActivityMapaSupervisores activityMapa_supervisores;



    public ActivityMapaSupervisores getMapaSupervisoresActivity() {

        return activityMapa_supervisores;
    }


    public void setMapaSupervisoresActivity(ActivityMapaSupervisores activityMapa_supervisores_activity) {

        this.activityMapa_supervisores = activityMapa_supervisores_activity;

    }


    @Override
    public void onLocationChanged(Location loc) {
        // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
        // debido a la deteccion de un cambio de ubicacion
        loc.getLatitude();
        loc.getLongitude();
        String Text = "Mi ubicacion actual es: " + "\n Lat = "
                + loc.getLatitude() + "\n Long = " + loc.getLongitude();
        mensaje1.setText(Text);
        this.activityMapa_supervisores.setLocation(loc);
    }







    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {


        switch (i) {
            case LocationProvider.AVAILABLE:
                Log.d("debug", "LocationProvider.AVAILABLE");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                break;
        }



    }

    @Override
    public void onProviderEnabled(String s) {


        // Este metodo se ejecuta cuando el GPS es activado
        mensaje1.setText("GPS Activado");

    }

    @Override
    public void onProviderDisabled(String s) {


        // Este metodo se ejecuta cuando el GPS es desactivado
        mensaje1.setText("GPS Desactivado");

    }





}/*************** FIN DE LA CLASE Localización ***************/
