package com.example.tarea_25_grupo_3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea_25_grupo_3.Data.Ubicacion;
import com.example.tarea_25_grupo_3.Data.UbicacionesRepository;
import com.example.tarea_25_grupo_3.R;

import java.util.ArrayList;

public class UbicacionAdapter extends RecyclerView.Adapter<UbicacionAdapter.ViewHolder> {

    private Context context;
    private UbicacionesRepository ubicaciones;
    private ArrayList<Ubicacion> listaUbicaciones;

    public UbicacionAdapter(Context context, ArrayList<Ubicacion> listaUbicaciones){
        this.context = context;
        this.listaUbicaciones = listaUbicaciones;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ubicacion ubicacion = listaUbicaciones.get(position);
        holder.nombre.setText(ubicacion.getNombre());
        holder.lon.setText(String.valueOf(ubicacion.getLongitud()));
        holder.alt.setText(String.valueOf(ubicacion.getAltitud()));
        holder.lat.setText(String.valueOf(ubicacion.getLatitud()));

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Elemento " + ubicacion.getNombre() + " clickeado", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return listaUbicaciones.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView lat,alt,lon,nombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lat = itemView.findViewById(R.id.itemListLatitud);
            alt = itemView.findViewById(R.id.itemListAltitud);
            lon = itemView.findViewById(R.id.itemListLongitud);
            nombre=itemView.findViewById(R.id.itemListTitulo);
        }
    }
}
