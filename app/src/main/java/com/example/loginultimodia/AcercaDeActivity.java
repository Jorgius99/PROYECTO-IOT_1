package com.example.loginultimodia;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import io.grpc.internal.JsonParser;

public class AcercaDeActivity extends FragmentActivity implements
        OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnInfoWindowClickListener {
    private static final String filePath = "ocity.json";
    private GoogleMap mapa;
    private final LatLng UPV = new LatLng(39.159911, -0.418471);
    private final LatLng GANDIA = new LatLng(38.9666700, -0.1833300);
    private static final int SOLICITUD_PERMISO_WRITE_CALL_LOG = 0;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acercade);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);
        solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION, "Sin este permiso no podra disfrutar de todas las funcionalidades."
                        ,
                SOLICITUD_PERMISO_WRITE_CALL_LOG, this);
      
    }



    @Override public void onMapReady(GoogleMap googleMap) {
        String json = "";
        String id = "0";
        String name = null;
        String latitud;
        mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(false);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(GANDIA, 13));
        try {
            InputStream is = getAssets().open("ocity.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    json += line.trim();
                }
            } finally {
                reader.close();
            }
            //Log.v("test " , json);
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(json);

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray dataArray = (JSONArray) jsonObject.get("data");

            for (int i = 0; i < dataArray.size(); i++) {
                JSONObject item = (JSONObject) dataArray.get(i);

                id = item.get("id").toString();
                name = item.get("manifestation_name").toString();
                latitud= (String) item.get("latitude_longitude");
                String[] latlong =  latitud.split(",");
                double latitude = Double.parseDouble(latlong[0]);
                double longitude = Double.parseDouble(latlong[1]);
                LatLng location = new LatLng(latitude, longitude);
                //Log.v("test" , "" + location);
                mapa.addMarker(new MarkerOptions()
                        .position(location)
                        .title(name)
                        .snippet("Pulse para más información.")
                );

            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
/*
        Log.v("test" , "" + id);
        Log.v("test " , "" + name);

 */
        /*mapa.addMarker(new MarkerOptions()
                .position(UPV)
                .title("Hospital1")
                .snippet("Hospital Universitario de la Ribera")
                .icon(BitmapDescriptorFactory
                        .fromResource(android.R.drawable.ic_menu_compass))
                .anchor(0.5f, 0.5f));*/
        mapa.setOnMapClickListener(this);
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            mapa.setMyLocationEnabled(true);
            mapa.getUiSettings().setCompassEnabled(true);
        }
        mapa.setOnInfoWindowClickListener(this);
    }


    @Override public void onMapClick(LatLng puntoPulsado) {
        mapa.addMarker(new MarkerOptions().position(puntoPulsado)
                .icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));


    }
    public static void solicitarPermiso(final String permiso, String
            justificacion, final int requestCode, final Activity actividad) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(actividad,
                permiso)){
            new AlertDialog.Builder(actividad)
                    .setTitle("Solicitud de permiso")
                    .setMessage(justificacion)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ActivityCompat.requestPermissions(actividad,
                                    new String[]{permiso}, requestCode);
                        }}).show();
        } else {
            ActivityCompat.requestPermissions(actividad,
                    new String[]{permiso}, requestCode);
        }
    }


    @Override public void onInfoWindowClick(Marker marker) {
        String json = "";
        String id = "0";
        String name = null;
        String latitud;
        try {
            InputStream is = getAssets().open("ocity.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    json += line.trim();
                }
            } finally {
                reader.close();
            }
            //Log.v("test " , json);
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(json);

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray dataArray = (JSONArray) jsonObject.get("data");

            for (int i = 0; i < dataArray.size(); i++) {
                JSONObject item = (JSONObject) dataArray.get(i);

                id = item.get("id").toString();
                name = item.get("manifestation_name").toString();

                //Log.v("test" , "" + location);
                if (name.equals(marker.getTitle())){
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ocityplatform.webs.upv.es/ocityws/public/index.php/auxCity/"+id));
                    startActivity(intent);
                    break;
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}