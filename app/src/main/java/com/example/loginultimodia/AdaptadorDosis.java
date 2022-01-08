package com.example.loginultimodia;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginultimodia.databinding.ElementoDosisBinding;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdaptadorDosis extends
        FirestoreRecyclerAdapter<ObjetoDosis, AdaptadorDosis.ViewHolder> {
    protected View.OnClickListener onClickListener;
    protected Context context;
    protected  static CollectionReference pacientes;

    public AdaptadorDosis(
            @NonNull FirestoreRecyclerOptions<ObjetoDosis> options, Context context){
        super(options);
        this.context = context;

    }
    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView pastilla, fecha;
        private Task<DocumentSnapshot> aviso123;

        public ViewHolder(ElementoDosisBinding itemView) {
            super(itemView.getRoot());
            pastilla = itemView.pastilla;
            fecha = itemView.fecha1;
        }
        // Personalizamos un ViewHolder a partir de un lugar
        public void personaliza(ObjetoDosis dosis) {

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            pacientes = db.collection("dosis");
            pacientes.document().get();
            pastilla.setText(dosis.getMedicamento());
            Date date = dosis.getHoraFecha();
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            fecha.setText(df.format(date));
        }
    }

    @Override
    public AdaptadorDosis.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        ElementoDosisBinding v = ElementoDosisBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        v.getRoot().setOnClickListener(onClickListener);
        return new AdaptadorDosis.ViewHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdaptadorDosis
            .ViewHolder holder, int position, @NonNull ObjetoDosis dosis) {
        holder.personaliza(dosis);
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
