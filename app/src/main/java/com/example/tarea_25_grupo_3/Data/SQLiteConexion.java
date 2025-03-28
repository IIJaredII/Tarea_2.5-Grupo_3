package com.example.tarea_25_grupo_3.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteConexion extends SQLiteOpenHelper {

    public static final String NameDB="Ubicaciones";
    public static final int version=1;
    public static final SQLiteDatabase.CursorFactory factory = null;

    public SQLiteConexion(@Nullable Context context) {
        super(context, NameDB, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UbicacionesContract.CREATE_TABLE_UBICACIONES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UbicacionesContract.DROP_TABLE_UBICACIONES);
        onCreate(db);
    }
}
