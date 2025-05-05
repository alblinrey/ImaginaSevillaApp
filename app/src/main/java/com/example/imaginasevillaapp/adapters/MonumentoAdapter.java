package com.example.imaginasevillaapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imaginasevillaapp.R;
import com.example.imaginasevillaapp.models.Monumentos;

import java.util.List;

public class MonumentoAdapter extends RecyclerView.Adapter<MonumentoAdapter.ViewHolder> {

    //Listado de monumentos que se mostrarán.
    private final List<Monumentos> lista;
    private final OnItemClickListener listener;

    //Interfaz que escuchará los clicks en los items.
    public interface OnItemClickListener {

        void onItemClick(Monumentos monumento);
    }

    //Constructor del adaptador, recibe la lista de monumentos y el listener del Itemclick.
    public MonumentoAdapter(List<Monumentos> lista, OnItemClickListener listener) {
        this.lista = lista;
        this.listener = listener;
    }

    //es lamado cuando se crea un nuevo ViewHolder (cuando un item es visible por 1ª vez)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Infla el layout para que cada item se vaya colocando conforme existan más items.
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_monumento, parent, false);
        return new ViewHolder(vista);
    }

    /*Este es para cuando un item ya ha sido visible y se tiene que actualizar. Obtiene los datos del monumento correspondiente
    a la posición del RecyclerView y pasa eso datos al ViewHolder para que los visualice.*/
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Monumentos monumento = lista.get(position);
        //Llama al bind() del ViewHolder para asignar datos al item.
        holder.bind(monumento, listener);
    }

    //Devuelve el nº total de items del listado.
    @Override
    public int getItemCount() {
        return lista.size();
    }

    //Clae ViewHolder que contiene la vista para cada uno de los items del RecyclerView.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textoNombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Inicia el TExtview con el id que están en item_monumento.xml.
            textoNombre = itemView.findViewById(R.id.nombreMonumento);
        }

        //Asigna los datos del monumento y establece el listener para el click.
        public void bind(final Monumentos monumento, final OnItemClickListener listener) {
            //Pone el nombre del monumeto en el TextView
            textoNombre.setText(monumento.getNombre());
            //Establece un listener para el click sobre el item.
            itemView.setOnClickListener(v -> listener.onItemClick(monumento));
        }
    }
}
