package com.example.loginultimodia;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.loginultimodia.Controlador.Avisos;
import com.example.loginultimodia.Controlador.Dosis;
import com.example.loginultimodia.Controlador.PagerControler;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class SecondActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tab1, tab2, tab3;
    PagerControler pagerAdapter;
    private static String correoWaporro;
    //Button tvRegisterHere;
    //Button tvRegisterHere;

/*
    public static void sacaDatos(String email){

        final Usuario[] usuarioSacado = {new Usuario()};

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("pacientes").whereEqualTo("email",email).get().addOnCompleteListener(task ->  {
            if (task.isSuccessful()) {
                QuerySnapshot document = task.getResult();
                DocumentSnapshot docOC = document.getDocuments().get(0);
                if (document != null) {
                    usuarioSacado[0] = docOC.toObject(Usuario.class);// here
                    Log.d("USUARIOSACAD0",""+ usuarioSacado[0].getEmail());
                    Avisos.rellenarUsuario(usuarioSacado[0]);
                    Dosis.rellenarUsuario(usuarioSacado[0]);
                }
            }
        });
        // Log.d("NUEVOUSER", ""+nuevoUser);
    }*/

    public static void rellenaCorreoPUTA(String correo){
        correoWaporro=correo;
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final Usuario[] usuarioSacado = {new Usuario()};

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("pacientes").whereEqualTo("email",correoWaporro).get().addOnCompleteListener(task ->  {
            if (task.isSuccessful()) {
                QuerySnapshot document = task.getResult();
                DocumentSnapshot docOC = document.getDocuments().get(0);
                if (document != null) {
                    usuarioSacado[0] = docOC.toObject(Usuario.class);// here
                    Log.d("USUARIOSACAD0",""+ usuarioSacado[0].getEmail());
                    Avisos.rellenarUsuario(usuarioSacado[0]);
                    Dosis.rellenarUsuario(usuarioSacado[0]);
                    setContentView(R.layout.activity_tabmain);
                    tabLayout = findViewById(R.id.tablayout);
                    viewPager = findViewById(R.id.viewpager);
                    //tvRegisterHere = findViewById(R.id.tvRegisterHeress);//aquiiiiiiiiiiiiiiiiiiii

                    tab1 = findViewById(R.id.tabavisos);
                    tab2 = findViewById(R.id.tabhabitacion);
                    tab3 = findViewById(R.id.tabdosis);

                    pagerAdapter = new PagerControler(getSupportFragmentManager(), tabLayout.getTabCount());
                    viewPager.setAdapter(pagerAdapter);
                    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            viewPager.setCurrentItem(tab.getPosition());
                            if (tab.getPosition() == 0){
                                pagerAdapter.notifyDataSetChanged();
                            }
                            if (tab.getPosition() == 1){
                                pagerAdapter.notifyDataSetChanged();
                            }
                            if (tab.getPosition() == 2){
                                pagerAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

                    // tvRegisterHere.setOnClickListener(view -> {
                    //   startActivity(new Intent(SecondActivity.this, RegisterActivity.class));
                    //  });


                }
            }
        });
        // Log.d("NUEVOUSER", ""+nuevoUser);



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
            AuthUI.getInstance().signOut(this);
            return true;
        }
        if (id == R.id.listadoMedicamentos){
            lanzarMedicamentos(null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void lanzarMedicamentos(View view){
        Intent i = new Intent(this, AcercaDeActivity.class);
        startActivity(i);
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