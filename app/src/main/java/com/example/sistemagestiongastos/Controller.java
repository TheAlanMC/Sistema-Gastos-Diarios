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


    public long altaIngreso(IncomeModel obTran) {
        SQLiteDatabase sql = helper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("monto", obTran.getMontoIngreso());
        valoresParaInsertar.put("fuente_id", obTran.getFuenteIdIngreso());
        valoresParaInsertar.put("categoria_ingresos_id", obTran.getCategoriaIdIngreso());
        valoresParaInsertar.put("descripcion", obTran.getDescripcionIngreso());
        valoresParaInsertar.put("fecha_hora", obTran.getFechaHoraIngreso());
        return sql.insert("ingresos", null, valoresParaInsertar);
    }

    public long altaGasto(ExpenseModel obTran) {
        SQLiteDatabase sql = helper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("monto", obTran.getMontoGasto());
        valoresParaInsertar.put("fuente_id", obTran.getFuenteIdGasto());
        valoresParaInsertar.put("categoria_gastos_id", obTran.getCategoriaIdGasto());
        valoresParaInsertar.put("descripcion", obTran.getDescripcionGasto());
        valoresParaInsertar.put("fecha_hora", obTran.getFechaHoraGasto());
        return sql.insert("gastos", null, valoresParaInsertar);
    }

    public long altaTransferencia(TransferModel obTran) {
        SQLiteDatabase sql = helper.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("monto", obTran.getMontoTransferencia());
        valoresParaInsertar.put("fuente_id_origen", obTran.getCuentaOrigenTransferencia());
        valoresParaInsertar.put("fuente_id_destino", obTran.getCuentaDestinoTransferencia());
        valoresParaInsertar.put("descripcion", obTran.getDescripcionTransferencia());
        valoresParaInsertar.put("fecha_hora", obTran.getFechaHoraTransferencia());
        return sql.insert("transferencias", null, valoresParaInsertar);
    }

    public ArrayList<MovementModel> obtenerMovimientos(int fuenteId, int mes, int anio) {
        ArrayList<MovementModel> listaMovimientos = new ArrayList<>();
        SQLiteDatabase baseDeDatos = helper.getReadableDatabase();
        String fecha = anio + "-" + mes + "%";
        String q = "SELECT * FROM (" +
                " SELECT * , 1 as tipo FROM ingresos WHERE fuente_id=" + fuenteId +
                " UNION ALL SELECT * , 2 as tipo FROM gastos WHERE fuente_id=" + fuenteId +
                " UNION ALL SELECT * , 3 as tipo FROM transferencias WHERE fuente_id_origen=" + fuenteId +
                " UNION ALL SELECT * , 4 as tipo FROM transferencias WHERE fuente_id_destino=" + fuenteId +
                " ) WHERE fecha_hora LIKE " + "'" + fecha + "'" +
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

    public ArrayList<MovementModel> obtenerMovimientosAnual(int fuenteId, int anio) {
        ArrayList<MovementModel> listaMovimientos = new ArrayList<>();
        SQLiteDatabase baseDeDatos = helper.getReadableDatabase();
        String fecha = anio + "-%";
        String q = "SELECT * FROM (" +
                " SELECT * , 1 as tipo FROM ingresos WHERE fuente_id=" + fuenteId +
                " UNION ALL SELECT * , 2 as tipo FROM gastos WHERE fuente_id=" + fuenteId +
                " UNION ALL SELECT * , 3 as tipo FROM transferencias WHERE fuente_id_origen=" + fuenteId +
                " UNION ALL SELECT * , 4 as tipo FROM transferencias WHERE fuente_id_destino=" + fuenteId +
                " ) WHERE fecha_hora LIKE " + "'" + fecha + "'" +
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

    public double ObtenerIngresos(int fuenteId, int mes, int anio) {
        SQLiteDatabase baseDeDatos = helper.getReadableDatabase();
        String fecha = anio + "-" + mes + "%";
        String q = "SELECT SUM(monto) FROM (SELECT monto FROM ingresos WHERE fuente_id=" + fuenteId + " AND fecha_hora LIKE " + "'" + fecha + "'"
                + " UNION ALL SELECT monto FROM transferencias WHERE fuente_id_destino=" + fuenteId + " AND fecha_hora LIKE " + "'" + fecha + "'" + ")";
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

    public double ObtenerGastos(int fuenteId, int mes, int anio) {
        SQLiteDatabase baseDeDatos = helper.getReadableDatabase();
        String fecha = anio + "-" + mes + "%";
        String q = "SELECT SUM(monto) FROM (SELECT monto FROM gastos WHERE fuente_id=" + fuenteId + " AND fecha_hora LIKE " + "'" + fecha + "'"
                + " UNION ALL SELECT monto FROM transferencias WHERE fuente_id_origen=" + fuenteId + " AND fecha_hora LIKE " + "'" + fecha + "'" + ")";

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

    public int EliminarIngreso(int id) {
        SQLiteDatabase baseDeDatos = helper.getWritableDatabase();
        return baseDeDatos.delete("ingresos", "id = ?", new String[]{String.valueOf(id)});
    }

    public int EliminarGasto(int id) {
        SQLiteDatabase baseDeDatos = helper.getWritableDatabase();
        return baseDeDatos.delete("gastos", "id = ?", new String[]{String.valueOf(id)});
    }

    public int EliminarTransferencia(int id) {
        SQLiteDatabase baseDeDatos = helper.getWritableDatabase();
        return baseDeDatos.delete("transferencias", "id = ?", new String[]{String.valueOf(id)});
    }

    public int ModificarIngreso(IncomeModel obTran) {
        SQLiteDatabase baseDeDatos = helper.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("monto", obTran.getMontoIngreso());
        valoresParaActualizar.put("fuente_id", obTran.getFuenteIdIngreso());
        valoresParaActualizar.put("categoria_ingresos_id", obTran.getCategoriaIdIngreso());
        valoresParaActualizar.put("descripcion", obTran.getDescripcionIngreso());
        valoresParaActualizar.put("fecha_hora", obTran.getFechaHoraIngreso());
        return baseDeDatos.update("ingresos", valoresParaActualizar, "id = ?", new String[]{String.valueOf(obTran.getIdIngreso())});
    }

    public int ModificarGasto(ExpenseModel obTran) {
        SQLiteDatabase baseDeDatos = helper.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("monto", obTran.getMontoGasto());
        valoresParaActualizar.put("fuente_id", obTran.getFuenteIdGasto());
        valoresParaActualizar.put("categoria_gastos_id", obTran.getCategoriaIdGasto());
        valoresParaActualizar.put("descripcion", obTran.getDescripcionGasto());
        valoresParaActualizar.put("fecha_hora", obTran.getFechaHoraGasto());
        return baseDeDatos.update("gastos", valoresParaActualizar, "id = ?", new String[]{String.valueOf(obTran.getIdGasto())});
    }

    public int ModificarTransferencia(TransferModel obTran) {
        SQLiteDatabase baseDeDatos = helper.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("monto", obTran.getMontoTransferencia());
        valoresParaActualizar.put("fuente_id_origen", obTran.getCuentaOrigenTransferencia());
        valoresParaActualizar.put("fuente_id_destino", obTran.getCuentaDestinoTransferencia());
        valoresParaActualizar.put("descripcion", obTran.getDescripcionTransferencia());
        valoresParaActualizar.put("fecha_hora", obTran.getFechaHoraTransferencia());
        return baseDeDatos.update("transferencias", valoresParaActualizar, "id = ?", new String[]{String.valueOf(obTran.getIdTransferencia())});
    }

    public String ObtenerBalance(int fuenteId, int mes, int anio) {
        double ingresos = ObtenerIngresos(fuenteId, mes, anio);
        double gastos = ObtenerGastos(fuenteId, mes, anio);
        double balance = ingresos - gastos;
        if (balance == 0) {
            return "0.00";
        } else {
            return String.format("%.2f", balance);
        }
    }

    public double ObtenerIngresoAcumulado(int fuenteId, int mes, int anio) {
        SQLiteDatabase baseDeDatos = helper.getReadableDatabase();
        String fecha = anio + "-" + (mes + 1) + "%";
        String q = "SELECT SUM(monto) FROM (SELECT monto FROM ingresos WHERE fuente_id=" + fuenteId + " AND fecha_hora NOT LIKE " + "'" + fecha + "'"
                + " UNION ALL SELECT monto FROM transferencias WHERE fuente_id_destino=" + fuenteId + " AND fecha_hora NOT LIKE " + "'" + fecha + "'" + ")";

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

    public double ObtenerGastoAcumulado(int fuenteId, int mes, int anio) {
        SQLiteDatabase baseDeDatos = helper.getReadableDatabase();
        String fecha = anio + "-" + (mes + 1) + "%";
        String q = "SELECT SUM(monto) FROM (SELECT monto FROM gastos WHERE fuente_id=" + fuenteId + " AND fecha_hora NOT LIKE " + "'" + fecha + "'"
                + " UNION ALL SELECT monto FROM transferencias WHERE fuente_id_origen=" + fuenteId + " AND fecha_hora NOT LIKE " + "'" + fecha + "'" + ")";

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

    public String ObtenerBalanceAcumulado(int fuenteId, int mes, int anio) {
        double ingresos = ObtenerIngresoAcumulado(fuenteId, mes, anio);
        double gastos = ObtenerGastoAcumulado(fuenteId, mes, anio);
        double balance = ingresos - gastos;
        if (balance == 0) {
            return "0.00";
        } else {
            return String.format("%.2f", balance);
        }
    }

    public double ObtenerIngresoAnual(int fuenteId, int anio) {
        SQLiteDatabase baseDeDatos = helper.getReadableDatabase();
        String fecha = anio + "%";
        String q = "SELECT SUM(monto) FROM (SELECT monto FROM ingresos WHERE fuente_id=" + fuenteId + " AND fecha_hora LIKE " + "'" + fecha + "'"
                + " UNION ALL SELECT monto FROM transferencias WHERE fuente_id_destino=" + fuenteId + " AND fecha_hora LIKE " + "'" + fecha + "'" + ")";

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

    public double ObtenerGastoAnual(int fuenteId, int anio) {
        SQLiteDatabase baseDeDatos = helper.getReadableDatabase();
        String fecha = anio + "%";
        String q = "SELECT SUM(monto) FROM (SELECT monto FROM gastos WHERE fuente_id=" + fuenteId + " AND fecha_hora LIKE " + "'" + fecha + "'"
                + " UNION ALL SELECT monto FROM transferencias WHERE fuente_id_origen=" + fuenteId + " AND fecha_hora LIKE " + "'" + fecha + "'" + ")";

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

    public String ObtenerBalanceAnual(int fuenteId, int anio) {
        double ingresos = ObtenerIngresoAnual(fuenteId, anio);
        double gastos = ObtenerGastoAnual(fuenteId, anio);
        double balance = ingresos - gastos;
        if (balance == 0) {
            return "0.00";
        } else {
            return String.format("%.2f", balance);
        }
    }

    public String ObtenerIngresosString(int fuenteId, int mes, int anio) {
        double ingresos = ObtenerIngresos(fuenteId, mes, anio);
        if (ingresos == 0) {
            return "0.00";
        } else {
            return String.format("%.2f", ingresos);
        }
    }

    public String ObtenerGastosString(int fuenteId, int mes, int anio) {
        double gastos = ObtenerGastos(fuenteId, mes, anio);
        if (gastos == 0) {
            return "0.00";
        } else {
            return String.format("%.2f", gastos);
        }
    }

}
