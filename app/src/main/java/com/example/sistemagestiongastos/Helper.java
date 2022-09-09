package com.example.sistemagestiongastos;

import android.content.ContentValues;
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
        db.execSQL(
                "CREATE TABLE categoria_gastos (\n" +
                        "    id int NOT NULL CONSTRAINT categoria_gastos_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    categoria varchar(100) NOT NULL\n" +
                        ")");
        db.execSQL(
                "CREATE TABLE categoria_ingresos (\n" +
                        "    id int NOT NULL CONSTRAINT categoria_ingresos_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    categoria varchar(100) NOT NULL\n" +
                        ")");
        db.execSQL("CREATE TABLE fuente (\n" +
                "    id int NOT NULL CONSTRAINT fuente_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    categoria varchar(100) NOT NULL\n" +
                ")");
        db.execSQL("CREATE TABLE gastos (\n" +
                "    id int NOT NULL CONSTRAINT gastos_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    monto double NOT NULL,\n" +
                "    fecha datetime NOT NULL,\n" +
                "    detalle varchar(100) NOT NULL,\n" +
                "    categoria_gastos_id int NOT NULL,\n" +
                "    fuente_id int NOT NULL,\n" +
                "    CONSTRAINT gastos_fuente FOREIGN KEY (fuente_id)\n" +
                "    REFERENCES fuente (id),\n" +
                "    CONSTRAINT gastos_categoria_gastos FOREIGN KEY (categoria_gastos_id)\n" +
                "    REFERENCES categoria_gastos (id)\n" +
                ")");
        db.execSQL("CREATE TABLE ingresos (\n" +
                "    id int NOT NULL CONSTRAINT ingresos_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    monto double NOT NULL,\n" +
                "    fecha datetime NOT NULL,\n" +
                "    detalle varchar(100) NOT NULL,\n" +
                "    categoria_ingresos_id int NOT NULL,\n" +
                "    fuente_id int NOT NULL,\n" +
                "    CONSTRAINT ingresos_fuente FOREIGN KEY (fuente_id)\n" +
                "    REFERENCES fuente (id),\n" +
                "    CONSTRAINT ingresos_categoria_ingresos FOREIGN KEY (categoria_ingresos_id)\n" +
                "    REFERENCES categoria_ingresos (id)\n" +
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

        onCreate(db);
    }

    public void addSetting(SQLiteDatabase db) {
        db.execSQL("INSERT INTO fuente (categoria) VALUES ('Tarjeta'), ('Efectivo'),('Ahorros')");
        db.execSQL("INSERT INTO categoria_gastos (categoria) VALUES ('Prestamo'), ('Salario'),('Ventas')");
        db.execSQL("INSERT INTO fuente (categoria) VALUES ('Ropa'), ('Bebida'),('Educacion')," +
                "('Comida'), ('Combustible'),('Diversion'),('Viaje'), ('Hotel'),('Mercaderia')," +
                "('Personal'), ('Mascotas'),('Restaurante'),('Propina'), ('Transporte'),('Otros')");

    }

}