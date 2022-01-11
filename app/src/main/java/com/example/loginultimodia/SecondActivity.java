package com.example.loginultimodia;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.loginultimodia.Controlador.PagerControler;
import com.firebase.ui.auth.AuthUI;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class SecondActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    TabItem tab1, tab2, tab3;
    PagerControler pagerAdapter;
    //Button tvRegisterHere;
    //Button tvRegisterHere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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