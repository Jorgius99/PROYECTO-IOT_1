package com.example.loginultimodia;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

//import com.example.loginultimodia.ControladorDoctor.PagerControler;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

//holaaaaaaaaaaaaaaaaaaaaaaaaa
public class SuperAdminSecondActivity extends AppCompatActivity {
    //Button tvRegisterHere;//aquiiiiiiiiiiiiiiiiiiii
    //TextView tvRegisterHere;//aquiiiiiiiiiiiiiiiiiiii
    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tab1, tab2, tab3;
    //Button  nuevooo;

    Button BotonAviso;
    Button BotonPaciente;
    Button BotonEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_administrador);

        BotonAviso = findViewById(R.id.btAvisos);
        BotonAviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuperAdminSecondActivity.this, AnyadirAvisoActivity.class);
                startActivity(intent);
            }
        });

        BotonPaciente = findViewById(R.id.btPacientes);
        BotonPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuperAdminSecondActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        BotonEliminar=findViewById(R.id.btPacientesEliminar);
        BotonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuperAdminSecondActivity.this, AbrirEliminarActivity.class);
                startActivity(intent);
                finish();
            }
        });
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

        if (id == R.id.acercaDe){
            lanzarAcercaDe(null);
            return true;
        }
        if (id == R.id.btnLogout){
            lanzarLogOut(null);
            return true;

        }


        return super.onOptionsItemSelected(item);
    }
    public void lanzarAcercaDe(View view){
        Intent i = new Intent(this, AcercaDeActivity.class);
        startActivity(i);
    }
    public void lanzarLogOut(View view) {
        Intent i = new Intent(this, LogOutActivity.class);
        startActivity(i);
    }
}