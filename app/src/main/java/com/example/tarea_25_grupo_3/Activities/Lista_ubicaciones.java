package com.example.tarea_25_grupo_3.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarea_25_grupo_3.Adapter.UbicacionAdapter;
import com.example.tarea_25_grupo_3.Data.Ubicacion;
import com.example.tarea_25_grupo_3.Data.UbicacionesRepository;
import com.example.tarea_25_grupo_3.R;

import java.util.ArrayList;

public class Lista_ubicaciones extends AppCompatActivity {

    UbicacionesRepository ubicacionesRepository;
    RecyclerView recyclerView;
    ArrayList<Ubicacion> ubicacionArrayList;
    UbicacionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_ubicaciones);

        ubicacionesRepository = new UbicacionesRepository(this);
        recyclerView = findViewById(R.id.Lista);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ubicacionArrayList = ubicacionesRepository.obtenerUbicaciones();
        if (ubicacionArrayList.isEmpty()) {
            Toast.makeText(this, "No se encontraron ubicaciones", Toast.LENGTH_SHORT).show();  // Mostrar mensaje si la lista está vacía
        } else {
            adapter = new UbicacionAdapter(getApplicationContext(), ubicacionArrayList);
            recyclerView.setAdapter(adapter);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}