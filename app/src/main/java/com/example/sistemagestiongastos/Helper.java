package com.example.sistemagestiongastos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {


    static final String nameDB = "DB1";
    static final int verDB = 1;

    public Helper(@Nullable Context context) {
        super(context, nameDB, null, verDB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE categoria_gastos (\n" +
                "    id integer NOT NULL CONSTRAINT categoria_gastos_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    categoria varchar(100) NOT NULL\n" +
                ")");
        db.execSQL(
                "CREATE TABLE categoria_ingresos (\n" +
                        "    id integer NOT NULL CONSTRAINT categoria_ingresos_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    categoria varchar(100) NOT NULL\n" +
                        ")");
        db.execSQL("CREATE TABLE fuente (\n" +
                "    id integer NOT NULL CONSTRAINT fuente_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    categoria varchar(100) NOT NULL\n" +
                ")");
        db.execSQL("CREATE TABLE gastos (\n" +
                "    id integer NOT NULL CONSTRAINT gastos_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    monto decimal(8,2) NOT NULL,\n" +
                "    fuente_id integer NOT NULL,\n" +
                "    categoria_gastos_id integer NOT NULL,\n" +
                "    descripcion varchar(255) NOT NULL,\n" +
                "    fecha_hora datetime NOT NULL,\n" +
                "    CONSTRAINT gastos_categoria_gastos FOREIGN KEY (categoria_gastos_id)\n" +
                "    REFERENCES categoria_gastos (id),\n" +
                "    CONSTRAINT gastos_fuente FOREIGN KEY (fuente_id)\n" +
                "    REFERENCES fuente (id)\n" +
                ")");
        db.execSQL("CREATE TABLE ingresos (\n" +
                "    id integer NOT NULL CONSTRAINT ingresos_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    monto decimal(8,2) NOT NULL,\n" +
                "    fuente_id integer NOT NULL,\n" +
                "    categoria_ingresos_id integer NOT NULL,\n" +
                "    descripcion varchar(255) NOT NULL,\n" +
                "    fecha_hora datetime NOT NULL,\n" +
                "    CONSTRAINT ingresos_categoria_ingresos FOREIGN KEY (categoria_ingresos_id)\n" +
                "    REFERENCES categoria_ingresos (id),\n" +
                "    CONSTRAINT ingresos_fuente FOREIGN KEY (fuente_id)\n" +
                "    REFERENCES fuente (id)\n" +
                ")");
        db.execSQL("CREATE TABLE transferencias (\n" +
                "    id integer NOT NULL CONSTRAINT transferencias_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    monto decimal(8,2) NOT NULL,\n" +
                "    fuente_id_origen integer NOT NULL,\n" +
                "    fuente_id_destino integer NOT NULL,\n" +
                "    descripcion varchar(255) NOT NULL,\n" +
                "    fecha_hora datetime NOT NULL,\n" +
                "    CONSTRAINT transferencias_fuente_origen FOREIGN KEY (fuente_id_origen)\n" +
                "    REFERENCES fuente (id),\n" +
                "    CONSTRAINT transferencias_fuente_destino FOREIGN KEY (fuente_id_destino)\n" +
                "    REFERENCES fuente (id)\n" +
                ")");
        this.addSetting(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS categoria_gastos");
        db.execSQL("DROP TABLE IF EXISTS categoria_ingresos");
        db.execSQL("DROP TABLE IF EXISTS fuente");
        db.execSQL("DROP TABLE IF EXISTS gastos");
        db.execSQL("DROP TABLE IF EXISTS ingresos");
        db.execSQL("DROP TABLE IF EXISTS transferencias");
        onCreate(db);
    }

    public void addSetting(SQLiteDatabase db) {
        db.execSQL("INSERT INTO fuente (categoria) VALUES ('Tarjeta'), ('Efectivo'),('Ahorros')");
        db.execSQL("INSERT INTO categoria_gastos (categoria) VALUES ('Prestamo'), ('Salario'),('Ventas')");
        db.execSQL("INSERT INTO fuente (categoria) VALUES ('Otros'),('Ropa'), ('Bebida'),('Educacion')," +
                "('Comida'), ('Combustible'),('Diversion'),('Viaje'), ('Hotel'),('Mercaderia')," +
                "('Personal'), ('Mascotas'),('Restaurante'),('Propina'), ('Transporte')");
    }

}