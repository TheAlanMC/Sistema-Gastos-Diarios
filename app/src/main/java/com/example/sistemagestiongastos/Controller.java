package com.example.sistemagestiongastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import models.ExpenseModel;
import models.IncomeModel;
import models.MovementModel;
import models.TransferModel;

public class Controller {
    Helper helper;

    public Controller(Context context) {
        helper = new Helper(context);
    }


    public long altaIngreso(IncomeModel objProd) {
        SQLiteDatabase sql = helper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("monto", objProd.getMontoIngreso());
        valoresParaInsertar.put("fuente_id", objProd.getFuenteIdIngreso());
        valoresParaInsertar.put("categoria_ingresos_id", objProd.getCategoriaIdIngreso());
        valoresParaInsertar.put("descripcion", objProd.getDescripcionIngreso());
        valoresParaInsertar.put("fecha_hora", objProd.getFechaHoraIngreso());
        return sql.insert("ingresos", null, valoresParaInsertar);
    }

    public long altaGasto(ExpenseModel objProd) {
        SQLiteDatabase sql = helper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("monto", objProd.getMontoGasto());
        valoresParaInsertar.put("fuente_id", objProd.getFuenteIdGasto());
        valoresParaInsertar.put("categoria_gastos_id", objProd.getCategoriaIdGasto());
        valoresParaInsertar.put("descripcion", objProd.getDescripcionGasto());
        valoresParaInsertar.put("fecha_hora", objProd.getFechaHoraGasto());
        return sql.insert("gastos", null, valoresParaInsertar);
    }

    public long altaTransferencia(TransferModel objProd) {
        SQLiteDatabase sql = helper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("monto", objProd.getMontoTransferencia());
        valoresParaInsertar.put("fuente_id_origen", objProd.getCuentaOrigenTransferencia());
        valoresParaInsertar.put("fuente_id_destino", objProd.getCuentaDestinoTransferencia());
        valoresParaInsertar.put("descripcion", objProd.getDescripcionTransferencia());
        valoresParaInsertar.put("fecha_hora", objProd.getFechaHoraTransferencia());
        return sql.insert("transferencias", null, valoresParaInsertar);
    }

    public ArrayList<MovementModel> obtenerMovimientos(int fuenteId, int mes,int anio) {
        ArrayList<MovementModel> listaMovimientos = new ArrayList<>();
        SQLiteDatabase baseDeDatos = helper.getReadableDatabase();
        String  fechaInicio = anio+"-"+mes+"-01";
        String  fechaFin = anio+"-"+mes+"-31";
        String q = "SELECT * , 1 as tipo FROM ingresos WHERE fuente_id=" + fuenteId + " and fecha_hora between '"+fechaInicio+"' and '"+fechaFin+"' " +
                "UNION SELECT * , 2 as tipo FROM gastos WHERE fuente_id=" + fuenteId + " and fecha_hora between '"+fechaInicio+"' and '"+fechaFin+"' " +
                "UNION SELECT * , 3 as tipo FROM transferencias WHERE fuente_id_origen=" + fuenteId + " and fecha_hora between '"+fechaInicio+"' and '"+fechaFin+"' " +
                "UNION SELECT * , 4 as tipo FROM transferencias WHERE fuente_id_destino=" + fuenteId + " and fecha_hora between '"+fechaInicio+"' and '"+fechaFin+"'" +
                " ORDER BY fecha_hora DESC";

        Cursor cursor = baseDeDatos.rawQuery(q, null);
        if (cursor == null) {
            return listaMovimientos;
        }
        if (!cursor.moveToFirst())
            return listaMovimientos;
        do {
            int idMovimiento = cursor.getInt(0);
            double montoMovimiento = cursor.getDouble(1);
            int fuenteIdMovimiento = cursor.getInt(2);
            int categoriaId = cursor.getInt(3);
            String descripcionMovimiento = cursor.getString(4);
            String fechaHoraMovimiento = cursor.getString(5);
            int tipoMovimiento = cursor.getInt(6);

            MovementModel objMov = new MovementModel(tipoMovimiento, idMovimiento, fechaHoraMovimiento, fuenteIdMovimiento, categoriaId, montoMovimiento, descripcionMovimiento);
            listaMovimientos.add(objMov);

        } while (cursor.moveToNext());
        cursor.close();
        return listaMovimientos;
    }

    public double ObtenerIngresos(int fuenteId, int mes,int anio){
        SQLiteDatabase baseDeDatos = helper.getReadableDatabase();
        String  fechaInicio = anio+"-"+mes+"-01";
        String  fechaFin = anio+"-"+mes+"-31";
        String q = "SELECT SUM(monto) FROM (SELECT monto FROM ingresos WHERE fuente_id=" + fuenteId + " and fecha_hora between '"+fechaInicio+"' and '"+fechaFin+"' " +
                "UNION SELECT monto FROM transferencias WHERE fuente_id_destino=" + fuenteId + " and fecha_hora between '"+fechaInicio+"' and '"+fechaFin+"')";
        Cursor cursor = baseDeDatos.rawQuery(q, null);
        if (cursor == null) {
            return 0;
        }
        if (!cursor.moveToFirst())
            return 0;
        double ingresos = cursor.getDouble(0);
        cursor.close();
        return ingresos;
    }

    public double ObtenerGastos(int fuenteId, int mes,int anio){
        SQLiteDatabase baseDeDatos = helper.getReadableDatabase();
        String  fechaInicio = anio+"-"+mes+"-01";
        String  fechaFin = anio+"-"+mes+"-31";
        String q = "SELECT SUM(monto) FROM (SELECT monto FROM gastos WHERE fuente_id=" + fuenteId + " and fecha_hora between '"+fechaInicio+"' and '"+fechaFin+"' " +
                "UNION SELECT monto FROM transferencias WHERE fuente_id_origen=" + fuenteId + " and fecha_hora between '"+fechaInicio+"' and '"+fechaFin+"')";
        Cursor cursor = baseDeDatos.rawQuery(q, null);
        if (cursor == null) {
            return 0;
        }
        if (!cursor.moveToFirst())
            return 0;
        double gastos = cursor.getDouble(0);
        cursor.close();
        return gastos;
    }

    public String ObtenerBalance(int fuenteId, int mes,int anio){
        double ingresos = ObtenerIngresos(fuenteId,mes,anio);
        double gastos = ObtenerGastos(fuenteId,mes,anio);
        double balance = ingresos - gastos;
        if (balance ==0){
            return "0.00";
        }
        else {
            return String.format("%.2f", balance);
        }
    }

    public String ObtenerIngresosString(int fuenteId, int mes,int anio){
        double ingresos = ObtenerIngresos(fuenteId,mes,anio);
        if (ingresos ==0){
            return "0.00";
        }
        else {
            return String.format("%.2f", ingresos);
        }
    }

    public String ObtenerGastosString(int fuenteId, int mes,int anio){
        double gastos = ObtenerGastos(fuenteId,mes,anio);
        if (gastos ==0){
            return "0.00";
        }
        else {
            return String.format("%.2f", gastos);
        }
    }

}
