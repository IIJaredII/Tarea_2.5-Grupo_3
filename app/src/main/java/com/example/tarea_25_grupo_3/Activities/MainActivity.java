package com.example.tarea_25_grupo_3.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tarea_25_grupo_3.Data.Ubicacion;
import com.example.tarea_25_grupo_3.Data.UbicacionesRepository;
import com.example.tarea_25_grupo_3.Functions.Utils;
import com.example.tarea_25_grupo_3.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    private static final int PERMISSION_REQUEST_CODE = 1001;
    private FusedLocationProviderClient fusedLocationClient;
    private GoogleMap mMap;
    private Utils utils;
    private UbicacionesRepository ubicacionesRepository;
    CardView btnGuardarUbicacion,btnLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        utils = new Utils();
        ubicacionesRepository = new UbicacionesRepository(this);

        inicializarVista();
        configurarEventos();
        verificarPermisos();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void inicializarVista(){
        btnGuardarUbicacion = findViewById(R.id.cardGuardarUbicacion);
        btnLista = findViewById(R.id.cardMostrarLista);
    }

    private void configurarEventos(){
        btnGuardarUbicacion.setOnClickListener(v -> {
            double lat = utils.getLatitud(MainActivity.this, MainActivity.this);
            double lon = utils.getLongitud(MainActivity.this, MainActivity.this);
            double alt = utils.getAltitud(MainActivity.this, MainActivity.this);
            Ubicacion nuevaUbicacion = new Ubicacion("Ubicación actual", alt, lat, lon);
            boolean guardado = ubicacionesRepository.insertarUbicacion(nuevaUbicacion);
            if (guardado) {
                Toast.makeText(MainActivity.this, "Ubicación guardada con éxito.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Error al guardar la ubicación.", Toast.LENGTH_SHORT).show();
            }
        });
        btnLista.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Lista_ubicaciones.class);
            startActivity(intent);
        });
    }

    private void verificarPermisos(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        } else {
            obtenerUbicacion();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obtenerUbicacion();
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void obtenerUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                double lat = location.getLatitude();
                                double lng = location.getLongitude();
                                Toast.makeText(MainActivity.this, "Ubicación: " + lat + ", " + lng, Toast.LENGTH_LONG).show();

                                LatLng miUbicacion = new LatLng(lat, lng);
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(miUbicacion, 15));
                            } else {
                                Toast.makeText(MainActivity.this, "No se pudo obtener la ubicación", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            obtenerUbicacion();
        }
    }
}