package com.example.tarea_25_grupo_3.Data;

public class UbicacionesContract {

    public static class UbicacionesEntry {
        public static final String TABLE_NAME = "ubicacion";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NOMBRE = "nombre";
        public static final String COLUMN_ALTITUD = "altitud";
        public static final String COLUMN_LATITUD = "latitud";
        public static final String COLUMN_LONGITUD = "longitud";
        public static final String COLUMN_FECHA_CREACION = "fecha_creacion";
    }

    public static final String CREATE_TABLE_UBICACIONES = "CREATE TABLE " +
            UbicacionesEntry.TABLE_NAME + " (" +
            UbicacionesEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            UbicacionesEntry.COLUMN_NOMBRE + " TEXT NOT NULL, " +
            UbicacionesEntry.COLUMN_ALTITUD + " REAL NOT NULL, " +
            UbicacionesEntry.COLUMN_LATITUD + " REAL NOT NULL, " +
            UbicacionesEntry.COLUMN_LONGITUD + " REAL NOT NULL, " +
            UbicacionesEntry.COLUMN_FECHA_CREACION + " TEXT DEFAULT CURRENT_TIMESTAMP)";

    public static final String DROP_TABLE_UBICACIONES = "DROP TABLE IF EXISTS " + UbicacionesEntry.TABLE_NAME;
}

