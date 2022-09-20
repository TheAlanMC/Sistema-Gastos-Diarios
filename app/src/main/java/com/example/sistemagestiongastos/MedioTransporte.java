package com.example.sistemagestiongastos;

public interface MedioTransporte {
    void ingresos(int fuenteid);

    void gastos(int fuenteid);

    void transfer(int fuenteid);

    void movimientos(int fuenteid);

    void home();
}
