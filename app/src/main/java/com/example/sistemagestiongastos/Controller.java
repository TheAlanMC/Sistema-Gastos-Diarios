package com.example.sistemagestiongastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import models.ExpenseModel;
import models.IncomeModel;
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

}
