package com.example.loginultimodia;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginultimodia.databinding.ElementoAvisoBinding;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdaptadorAvisos extends
        FirestoreRecyclerAdapter<Aviso, AdaptadorAvisos.ViewHolder> {
    protected View.OnClickListener onClickListener;
    protected Context context;
    protected  static CollectionReference avisos;

    public AdaptadorAvisos(
            @NonNull FirestoreRecyclerOptions<Aviso> options, Context context){
        super(options);
        this.context = context;

    }
    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView motivo, fecha;
        private Task<DocumentSnapshot> aviso123;

        public ViewHolder(ElementoAvisoBinding itemView) {
            super(itemView.getRoot());
            motivo = itemView.motivo;
            fecha = itemView.fecha;
        }
        // Personalizamos un ViewHolder a partir de un lugar
        public void personaliza(Aviso aviso) {

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            avisos = db.collection("avisos");
            avisos.document().get();
            motivo.setText(aviso.getMotivo());
            Date date = aviso.getFechaHora();
            DateFormat df = new SimpleDateFormat("dd/MM/yy");
            fecha.setText(df.format(date));

        }
    }

    @Override
    public AdaptadorAvisos.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        ElementoAvisoBinding v = ElementoAvisoBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        v.getRoot().setOnClickListener(onClickListener);
        return new AdaptadorAvisos.ViewHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull AdaptadorAvisos
            .ViewHolder holder, int position, @NonNull Aviso aviso) {
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
