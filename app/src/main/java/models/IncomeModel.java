package models;

public class IncomeModel {
    private int idIngreso;
    private double montoIngreso;
    private int categoriaIdIngreso;
    private int fuenteIdIngreso;
    private String descripcionIngreso;
    private String fechaHoraIngreso;

    public IncomeModel(int idIngreso, double montoIngreso, int categoriaIdIngreso, int fuenteIdIngreso, String descripcionIngreso, String fechaHoraIngreso) {
        this.idIngreso = idIngreso;
        this.montoIngreso = montoIngreso;
        this.fuenteIdIngreso = fuenteIdIngreso;
        this.categoriaIdIngreso = categoriaIdIngreso;
        this.descripcionIngreso = descripcionIngreso;
        this.fechaHoraIngreso = fechaHoraIngreso;
    }

    public IncomeModel(double montoIngreso, int categoriaIdIngreso, int fuenteIdIngreso, String descripcionIngreso, String fechaHoraIngreso) {
        this.montoIngreso = montoIngreso;
        this.fuenteIdIngreso = fuenteIdIngreso;
        this.categoriaIdIngreso = categoriaIdIngreso;
        this.descripcionIngreso = descripcionIngreso;
        this.fechaHoraIngreso = fechaHoraIngreso;
    }

    public int getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
    }

    public double getMontoIngreso() {
        return montoIngreso;
    }

    public void setMontoIngreso(double montoIngreso) {
        this.montoIngreso = montoIngreso;
    }

    public int getFuenteIdIngreso() {
        return fuenteIdIngreso;
    }

    public void setFuenteIdIngreso(int fuenteIdIngreso) {
        this.fuenteIdIngreso = fuenteIdIngreso;
    }

    public int getCategoriaIdIngreso() {
        return categoriaIdIngreso;
    }

    public void setCategoriaIdIngreso(int categoriaIdIngreso) {
        this.categoriaIdIngreso = categoriaIdIngreso;
    }

    public String getDescripcionIngreso() {
        return descripcionIngreso;
    }

    public void setDescripcionIngreso(String descripcionIngreso) {
        this.descripcionIngreso = descripcionIngreso;
    }

    public String getFechaHoraIngreso() {
        return fechaHoraIngreso;
    }

    public void setFechaHoraIngreso(String fechaHoraIngreso) {
        this.fechaHoraIngreso = fechaHoraIngreso;
    }
}
