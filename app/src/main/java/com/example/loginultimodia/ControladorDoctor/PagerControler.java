package com.example.loginultimodia.ControladorDoctor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerControler extends FragmentPagerAdapter {
    int numoftabs;

    public PagerControler(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numoftabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Avisos();
            case 1:
                return new Habitaciones();

            case 2:
                return new Dosis();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
