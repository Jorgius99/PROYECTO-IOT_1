package com.example.loginultimodia;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.loginultimodia.databinding.ElementoMedicamentosBinding;

import java.util.Map;

//7 y 8.-RecyclerView
public class Adaptador extends
        RecyclerView.Adapter<Adaptador.ViewHolder> {

    protected Map<Integer,Registro> registros;    // Lista de lugares a mostrar

    public Adaptador(Map<Integer,Registro> registros) {
        this.registros = registros;
    }

    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView medicamento;
        public ImageView foto;

        public ViewHolder(ElementoMedicamentosBinding itemView) {
            super(itemView.getRoot());
            medicamento = itemView.medicamento;
            foto = itemView.foto;
        }

        // Personalizamos un ViewHolder a partir de un lugar
        public void personaliza(Registro registro) {
            medicamento.setText(registro.getMedicamento());

            if (registro.getMedicamento().equals("nolotil")){
                foto.setImageResource(R.drawable.nolotil);
            } else if(registro.getMedicamento().equals("paracetamol")){
                foto.setImageResource(R.drawable.paracetamol);
            }else{
                foto.setImageResource(R.drawable.enentyum);
            }
            foto.setScaleType(ImageView.ScaleType.FIT_END);
        }
    }

    // Creamos el ViewHolder con la vista de un elemento sin personalizar
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos la vista desde el xml
        ElementoMedicamentosBinding v = ElementoMedicamentosBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false);
        return new ViewHolder(v);
    }

    // Usando como base el ViewHolder y lo personalizamos
    @Override
    public void onBindViewHolder(ViewHolder holder, int posicion) {
        Registro registro = registros.get(posicion+1);
        holder.personaliza(registro);
    }

    // Indicamos el número de elementos de la lista
    @Override public int getItemCount() {
        return registros.size();
    }
}