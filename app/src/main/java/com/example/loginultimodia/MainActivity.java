package com.example.loginultimodia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.blautic.pikkuAcademyLib.PikkuAcademy;
import com.blautic.pikkuAcademyLib.ScanInfo;
import com.blautic.pikkuAcademyLib.ble.gatt.ConnectionState;
import com.blautic.pikkuAcademyLib.callback.ConnectionCallback;
import com.blautic.pikkuAcademyLib.callback.ScanCallback;
import com.firebase.ui.auth.AuthUI;
import com.example.loginultimodia.Controlador.Dosis;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;


public class MainActivity extends AppCompatActivity  {

    private PikkuAcademy pikku;
    private pikkuFuncion detectorCaidas;
    private NotificationManager notificationManager;
    static final String CANAL_ID = "mi_canal";
    static final int NOTIFICACION_ID = 1;
    private Button buttonConnect = findViewById(R.id.buttonConnect);
    private Button buttonScan = findViewById(R.id.buttonScan);
    private static final int SOLICITUD_PERMISO_WRITE_CALL_LOG = 0;

    /*  public static final int REQUEST_CODE_OPEN_GPS = 1002;
    public static final int REQUEST_CODE_ENABLE_BLUETOOTH = 1002;
    public static final int REQUEST_CODE_PERMISSION_LOCATION = 1003;
    public static final int REQUEST_CODE_CALL = 1005;*/

    private Button bAcercaDe;
    //TextView tvRegisterHere;//aquiiiiiiiiiiiiiiiiiiii

    //MenuItem btnLogOut;
    FirebaseAuth mAuth;


/*
    //7 y 8.-RecyclerView
    static public Map<Integer,Registro> mapa;

    // 5 a) Colecciones

    static public String  MEDICAMENTOS[]={"nolotil","enantyum","paracetamol"};
    ;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //tvRegisterHere = findViewById(R.id.textView16);//aquiiiiiiiiiiiiiiiiiiii

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        tvRegisterHere.setOnClickListener(view -> {//aquiiiiiiiiiiiiiiiiiiii
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });
        */

      //  detectorCaidas = new pikkuFuncion(this);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.btnLogout){
            AuthUI.getInstance().signOut(this);
            //lanzarLogOut(null);

            return true;
        }
        if (id == R.id.buttonMConnect){
            lanzarConectarPikku(null);
            return true;
        }
        if (id == R.id.acercadebien){
           // lanzarAcercaDeBien(null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void lanzarConectarPikku(View view){
        Intent i = new Intent(this, ConectarPikkuActivity.class);
        startActivity(i);
    }
    public void lanzarAcercaDe(View view){
        Intent i = new Intent(this, AcercaDeActivity.class);
        startActivity(i);
    }
    public void lanzarLogOut(View view){
        Intent i = new Intent(this, LogOutActivity.class);
        startActivity(i);
    }
    public void lanzarMedicamentos(View view){
        Intent i = new Intent(this, MedicamentosActivity.class);

    }
    public void lanzarAcercaDeBien(View view) {
        Intent i = new Intent(this, AcercaDeActivityREAL.class);
        startActivity(i);
    }

}