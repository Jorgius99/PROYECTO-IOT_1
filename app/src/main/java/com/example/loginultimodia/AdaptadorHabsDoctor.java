package com.example.loginultimodia;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.loginultimodia.databinding.ElementoHabsDoctorBinding;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdaptadorHabsDoctor extends
        FirestoreRecyclerAdapter<Habitacion, AdaptadorHabsDoctor.ViewHolder> {
    protected View.OnClickListener onClickListener;
    protected Context context;
    protected  static CollectionReference habitaciones;

    public AdaptadorHabsDoctor(
            @NonNull FirestoreRecyclerOptions<Habitacion> options, Context context){
        super(options);
        this.context = context;

    }
    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView numHab;
        private Task<DocumentSnapshot> aviso123;

        public ViewHolder(ElementoHabsDoctorBinding itemView) {
            super(itemView.getRoot());
            numHab = itemView.numHab;

        }
        // Personalizamos un ViewHolder a partir de un lugar
        public void personaliza(Habitacion habitacion) {

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            habitaciones = db.collection("habitaciones");
            habitaciones.document().get();
            numHab.setText(habitacion.getNumHab());


        }
    }

    @Override
    public AdaptadorHabsDoctor.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        ElementoHabsDoctorBinding v = ElementoHabsDoctorBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        v.getRoot().setOnClickListener(onClickListener);
        return new AdaptadorHabsDoctor.ViewHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdaptadorHabsDoctor
            .ViewHolder holder, int position, @NonNull Habitacion hab) {
        holder.personaliza(hab);
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
