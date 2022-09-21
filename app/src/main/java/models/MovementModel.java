package models;

public class MovementModel {
    private int idMovimiento;
    private int tipoMovimiento;
    private String fechaHoraMovimiento;
    private int fuenteIdMovimiento;
    private int categoriaIdMovimiento;
    private double montoMovimiento;
    private String descripcionMovimiento;

    public MovementModel(int tipoMovimiento, int idMovimiento, String fechaHoraMovimiento, int fuenteIdMovimiento, int categoriaIdMovimiento, double montoMovimiento, String descripcionMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
        this.idMovimiento = idMovimiento;
        this.fechaHoraMovimiento = fechaHoraMovimiento;
        this.fuenteIdMovimiento = fuenteIdMovimiento;
        this.categoriaIdMovimiento = categoriaIdMovimiento;
        this.montoMovimiento = montoMovimiento;
        this.descripcionMovimiento = descripcionMovimiento;
    }

    public int getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(int tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getFechaHoraMovimiento() {
        return fechaHoraMovimiento;
    }

    public void setFechaHoraMovimiento(String fechaHoraMovimiento) {
        this.fechaHoraMovimiento = fechaHoraMovimiento;
    }

    public int getFuenteIdMovimiento() {
        return fuenteIdMovimiento;
    }

    public void setFuenteIdMovimiento(int fuenteIdMovimiento) {
        this.fuenteIdMovimiento = fuenteIdMovimiento;
    }

    public int getCategoriaIdMovimiento() {
        return categoriaIdMovimiento;
    }

    public void setCategoriaIdMovimiento(int categoriaIdMovimiento) {
        this.categoriaIdMovimiento = categoriaIdMovimiento;
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
