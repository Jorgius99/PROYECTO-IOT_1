package com.example.loginultimodia;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginultimodia.databinding.ElementoAvisoBinding;
import com.example.loginultimodia.databinding.ElementoHabsTemphumBinding;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdaptadorTempHum extends
        FirestoreRecyclerAdapter<Habitacion, AdaptadorTempHum.ViewHolder> {
    protected View.OnClickListener onClickListener;
    protected Context context;
    protected  static CollectionReference habitaciones;

    public AdaptadorTempHum(
            @NonNull FirestoreRecyclerOptions<Habitacion> options, Context context){
        super(options);
        this.context = context;

    }
    @Override
    protected void onBindViewHolder(@NonNull AdaptadorTempHum
            .ViewHolder holder, int position, @NonNull Habitacion habs) {
        holder.personaliza(habs);
    }
    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView temperatura, humedad;
        private Task<DocumentSnapshot> aviso123;

        public ViewHolder(ElementoHabsTemphumBinding itemView) {
            super(itemView.getRoot());
            temperatura = itemView.grados2;
            humedad = itemView.hum;
        }
        // Personalizamos un ViewHolder a partir de un lugar
        public void personaliza(Habitacion hab) {

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            habitaciones = db.collection("habitaciones");
            habitaciones.document().get();
            humedad.setText(hab.getHumedad());
            temperatura.setText(hab.getTemperatura());

        }
    }

    @Override
    public AdaptadorTempHum.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        ElementoHabsTemphumBinding v = ElementoHabsTemphumBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        v.getRoot().setOnClickListener(onClickListener);
        return new AdaptadorTempHum.ViewHolder(v);
    }



    public void setOnItemClickListener(View.OnClickListener onClick) {
        onClickListener = onClick;
    }

    public String getKey(int pos) {
        return super.getSnapshots().getSnapshot(pos).getId();
    }
    /*
    public int getPos(String id) {
        int pos = 0;
        while (pos < getItemCount()) {
            if (getKey(pos).equals(id)) return pos;
            pos++;
        }
        return -1;
    }*/

}