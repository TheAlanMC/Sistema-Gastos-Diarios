package models;

public class MovementModel {
    private int tipoMovimiento;
    private String fechaHoraMovimiento;
    private int razonMovimiento;
    private double montoMovimiento;
    private String descripcionMovimiento;

    public MovementModel(int tipoMovimiento, String fechaHoraMovimiento, int razonMovimiento, double montoMovimiento, String descripcionMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
        this.fechaHoraMovimiento = fechaHoraMovimiento;
        this.razonMovimiento = razonMovimiento;
        this.montoMovimiento = montoMovimiento;
        this.descripcionMovimiento = descripcionMovimiento;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getFechaHoraMovimiento() {
        return fechaHoraMovimiento;
    }

    public void setFechaHoraMovimiento(String fechaHoraMovimiento) {
        this.fechaHoraMovimiento = fechaHoraMovimiento;
    }

    public int getRazonMovimiento() {
        return razonMovimiento;
    }

    public void setRazonMovimiento(int razonMovimiento) {
        this.razonMovimiento = razonMovimiento;
    }

    public double getMontoMovimiento() {
        return montoMovimiento;
    }

    public void setMontoMovimiento(double montoMovimiento) {
        this.montoMovimiento = montoMovimiento;
    }

    public String getDescripcionMovimiento() {
        return descripcionMovimiento;
    }

    public void setDescripcionMovimiento(String descripcionMovimiento) {
        this.descripcionMovimiento = descripcionMovimiento;
    }
}
