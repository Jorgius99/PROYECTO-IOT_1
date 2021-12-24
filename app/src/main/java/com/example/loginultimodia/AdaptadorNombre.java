package com.example.loginultimodia;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginultimodia.databinding.ElementoAvisoBinding;
import com.example.loginultimodia.databinding.ElementoNombrePacienteBinding;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdaptadorNombre extends
        FirestoreRecyclerAdapter<Usuario, AdaptadorNombre.ViewHolder> {
    protected View.OnClickListener onClickListener;
    protected Context context;
    protected  static CollectionReference pacientes;

    public AdaptadorNombre(
            @NonNull FirestoreRecyclerOptions<Usuario> options, Context context){
        super(options);
        this.context = context;

    }
    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre;
        private Task<DocumentSnapshot> aviso123;

        public ViewHolder(ElementoNombrePacienteBinding itemView) {
            super(itemView.getRoot());
            nombre = itemView.nombreApellidos;
        }
        // Personalizamos un ViewHolder a partir de un lugar
        public void personaliza(Usuario aviso) {

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            pacientes = db.collection("avisos");
            pacientes.document().get();
            nombre.setText(aviso.getNombreApellido());


        }
    }

    @Override
    public AdaptadorNombre.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        ElementoNombrePacienteBinding v =  ElementoNombrePacienteBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        v.getRoot().setOnClickListener(onClickListener);
        return new AdaptadorNombre.ViewHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdaptadorNombre
            .ViewHolder holder, int position, @NonNull Usuario aviso) {
        holder.personaliza(aviso);
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
