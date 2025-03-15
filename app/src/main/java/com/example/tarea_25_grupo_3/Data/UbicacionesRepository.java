package com.example.tarea_25_grupo_3.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class UbicacionesRepository {
    private final SQLiteConexion conexion;

    public UbicacionesRepository(Context context) {
        this.conexion = new SQLiteConexion(context.getApplicationContext());
    }

    public boolean insertarUbicacion(Ubicacion ubicacion) {
        SQLiteDatabase db = conexion.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UbicacionesContract.UbicacionesEntry.COLUMN_NOMBRE, ubicacion.getNombre());
        values.put(UbicacionesContract.UbicacionesEntry.COLUMN_ALTITUD, ubicacion.getAltitud());
        values.put(UbicacionesContract.UbicacionesEntry.COLUMN_LATITUD, ubicacion.getLatitud());
        values.put(UbicacionesContract.UbicacionesEntry.COLUMN_LONGITUD, ubicacion.getLongitud());

        // No es necesario agregar fecha_creacion porque tiene un valor por defecto
        try {
            long resultado = db.insert(UbicacionesContract.UbicacionesEntry.TABLE_NAME, null, values);
            db.close();
            return resultado != -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<Ubicacion> obtenerUbicaciones() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = null;
        ArrayList<Ubicacion> listaUbicaciones = new ArrayList<>();

        String query = "SELECT * FROM " + UbicacionesContract.UbicacionesEntry.TABLE_NAME;

        try {
            cursor = db.rawQuery(query, null);
            if (cursor.moveToNext()) {
                do {
                    Ubicacion ubicacion = new Ubicacion();
                    ubicacion.setId(cursor.getInt(0));
                    ubicacion.setNombre(cursor.getString(1));
                    ubicacion.setAltitud(cursor.getDouble(2));
                    ubicacion.setLatitud(cursor.getDouble(3));
                    ubicacion.setLongitud(cursor.getDouble(4));

                    listaUbicaciones.add(ubicacion);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return listaUbicaciones;
    }



}
