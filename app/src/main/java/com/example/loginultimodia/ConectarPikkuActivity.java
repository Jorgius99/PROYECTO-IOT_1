package com.example.loginultimodia;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.blautic.pikkuAcademyLib.PikkuAcademy;
import com.blautic.pikkuAcademyLib.ScanInfo;
import com.blautic.pikkuAcademyLib.ble.gatt.ConnectionState;
import com.blautic.pikkuAcademyLib.callback.AccelerometerCallback;
import com.blautic.pikkuAcademyLib.callback.ConnectionCallback;
import com.blautic.pikkuAcademyLib.callback.ScanCallback;
import com.example.loginultimodia.databinding.ActivityConectarPikkuBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

import timber.log.Timber;

public class ConectarPikkuActivity  extends  AppCompatActivity implements pikkuFuncion.MovementListener{

    private static Usuario usuarioConDatos;
    private ActivityConectarPikkuBinding binding;
    private PikkuAcademy pikku;
    private final int TIME_PRESS_BUTTON = 3000;
    private boolean call;

    private static final int SOLICITUD_PERMISO_WRITE_CALL_LOG = 0;
    private pikkuFuncion detectorCaidas;
    private NotificationManager notificationManager;
    static final String CANAL_ID = "mi_canal";
    static final int NOTIFICACION_ID = 1;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityConectarPikkuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pikku = PikkuAcademy.getInstance(this);
        pikku.enableLog();
        detectorCaidas = new pikkuFuncion(this);

        binding.switchLecturaPikku.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                pikku.readAccelerometer(new AccelerometerCallback() {
                    @Override
                    public void onReadSuccess(float x, float y, float z) {
                        detectorCaidas.setDataAccelerometer(x, y, z);
                    }

                    @Override
                    public void onReadAngles(float xy, float zy, float xz) {
                        //  Timber.d("xy: " + xy +"zy: " + zy +"xz: " + xz);
                        detectorCaidas.setDataAngles(xy, zy, xz);
                    }

                });
                pikku.readButtons((nButton, pressed, duration) -> {
                    if (isChecked && binding.switchLecturaPikku.isChecked() && pressed && duration > TIME_PRESS_BUTTON && !call) {
                        call = true;
                        llamarTelefono();
                    }

                });
            } else {
                pikku.enableReportSensors(false);
            }
        });
/*
        Button arrancar = findViewById(R.id.biniciarPikku);
        arrancar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //startService(new Intent(ConectarPikkuActivity.this, ServicioPikku.class));
                pikku.enableReportSensors(true);

                pikku.readAccelerometer(new AccelerometerCallback() {
                    @Override
                    public void onReadSuccess(float x, float y, float z) {
                        detectorCaidas.setDataAccelerometer(x, y, z);
                    }

                    @Override
                    public void onReadAngles(float xy, float zy, float xz) {
                        //  Timber.d("xy: " + xy +"zy: " + zy +"xz: " + xz);
                        detectorCaidas.setDataAngles(xy, zy, xz);
                    }

                });

                pikku.readButtons((nButton, pressed, duration) -> {
                        llamarTelefono();
                });

            }
        });*/
        Button detener = findViewById(R.id.pararPikku);
        detener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //pikku.enableReportSensors(false);

                stopService(new Intent(ConectarPikkuActivity.this,
                        ServicioPikku.class));
            }
        });
    }//onCreate()


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

    public void onClickScan(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission
                .ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            binding.textScan.setText("Pulsa el botón Pikku 1 para ser scaneado");

            pikku.scan(true, new ScanCallback() {
                @Override
                public void onScan(ScanInfo scanInfo) {
                    pikku.saveDevice(scanInfo);
                    // guardar dispositivo para futuras conexiones
                    Log.d("Pikku", scanInfo.toString());
                    binding.textScan.setText("Encontrado: " + pikku.getAddressDevice());
                    binding.buttonConnect.setEnabled(true);
                }
            });
        }else {
            solicitarPermiso(Manifest.permission.ACCESS_FINE_LOCATION, "Sin el permiso"+
                            " administrar llamadas no puedo borrar llamadas del registro.",
                    SOLICITUD_PERMISO_WRITE_CALL_LOG, this);
        }
    }//onClickScan()

    public void onClickConnect(View view) {
        if (binding.buttonConnect.getText().equals("Conectar") ) {
            binding.textConnect.setText("Conectando...");
            pikku.connect(new ConnectionCallback() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onConnect(ConnectionState state) {
                    if (state == ConnectionState.CONNECTED) {
                        binding.buttonConnect.setText("Desconectar");
                        binding.textConnect.setText("Conectado: " + pikku.getAddressDevice());
                        binding.buttonScan.setEnabled(false);

                    }
                }

            });
        }
        else if (binding.buttonConnect.getText().equals("Desconectar")){
           // pikku.disconnect();

            pikku.turnOffDevice();
            binding.buttonConnect.setText("Conectar");
            binding.textConnect.setText("No conectado");
            binding.textScan.setText("Desconectado");
            binding.buttonScan.setEnabled(true);

        }
    }//onClickConnect()
    public static Usuario rellenarUsuario(Usuario ussus)  {
        usuarioConDatos  = ussus;
        Log.d("FAFA", ""+usuarioConDatos);
        return usuarioConDatos;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCaida(int caida) {
        Timber.d("CAIDA DETECTADA");
        String hab = usuarioConDatos.getNumHabitacion();
        String motiv = "Caida detectada en habitación "+hab;
        Date date = new Date();
        String dni = "32456215H";
        startService(new Intent(ConectarPikkuActivity.this,
                ServicioPikku.class));
        Aviso avisoPikku = new Aviso(motiv, date, "0", dni, hab);
        db.collection("avisos").document().set(avisoPikku);
    }
    public void llamarTelefono() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission
                .CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:655564961"));
            startActivity(intent);
        }else {
            solicitarPermiso(Manifest.permission.CALL_PHONE, "Sin el permiso"+
                            " administrar llamadas no puedo borrar llamadas del registro.",
                    SOLICITUD_PERMISO_WRITE_CALL_LOG, this);
        }

    }
    @Override
    public void onAccelX(float accelX) {

    }

    @Override
    public void onAccelY(float accelY) {

    }

    @Override
    public void onAccelZ(float accelZ) {

    }

    @Override
    public void onRest() {

    }

    @Override public void onStop(){
        super.onStop();
        stopService(new Intent(ConectarPikkuActivity.this,
                ServicioPikku.class));
    }
    @Override public void onDestroy() {

        super.onDestroy();
        stopService(new Intent(ConectarPikkuActivity.this,
                ServicioPikku.class));
    }

}

/*
    private ActivityConectarPikkuBinding binding;
    private PikkuAcademy pikku;

    private Button buttonConnect = findViewById(R.id.buttonConnect);
    private Button buttonScan = findViewById(R.id.buttonScan);
    private static final int SOLICITUD_PERMISO_WRITE_CALL_LOG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityConectarPikkuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }//onCreate()
    public void onClickScan(View view) {
        Log.d("Pikku", "ooooooooooooooooooooooooooooooooo");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission
                .BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getParent(), "Pulsa el botón Pikku 1 para ser scaneado",
                    Toast.LENGTH_SHORT).show();
            Log.d("Pikku", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

            pikku.scan(true, new ScanCallback() {
                @Override
                public void onScan(ScanInfo scanInfo) {
                    pikku.saveDevice(scanInfo);
                    // guardar dispositivo para futuras conexiones
                    Log.d("Pikku", scanInfo.toString());
                    Toast.makeText(getParent().getParent(), "Encontrado: "+pikku.getAddressDevice(),
                            Toast.LENGTH_SHORT).show();

                    buttonConnect.setEnabled(true);
                }
            });
        }else {
            solicitarPermiso(Manifest.permission.BLUETOOTH_SCAN, "Sin el permiso"+
                            " BLUETOOTH no puedo escanear la Pikku",
                    SOLICITUD_PERMISO_WRITE_CALL_LOG, this);
        }

    }//onClickScan()

    public void onClickConnect(View view) {
        if (buttonConnect.getText().equals("Conectar") ) {
            Toast.makeText(getParent(), "Conectando...",
                    Toast.LENGTH_SHORT).show();

            pikku.connect(new ConnectionCallback() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onConnect(ConnectionState state) {
                    if (state == ConnectionState.CONNECTED) {
                        buttonConnect.setText("Desconectar");
                        Toast.makeText(getParent(), "Conectado: " + pikku.getAddressDevice(),
                                Toast.LENGTH_SHORT).show();
                        buttonScan.setEnabled(false);

                    }
                }

            });
        }
        else if (buttonConnect.getText().equals("Desconectar")){
            pikku.disconnect();
            buttonConnect.setText("Conectar");
            Toast.makeText(getParent(), "Desconectado",
                    Toast.LENGTH_SHORT).show();
            buttonScan.setEnabled(true);
        }
    }//onClickConnect()
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

}
*/
