package models;

public class ExpenseModel {
    private int idGasto;
    private double montoGasto;
    private int categoriaIdGasto;
    private int fuenteIdGasto;
    private String descripcionGasto;
    private String fechaHoraGasto;

    public ExpenseModel(int idGasto, double montoGasto, int categoriaIdGasto, int fuenteIdGasto, String descripcionGasto, String fechaHoraGasto) {
        this.idGasto = idGasto;
        this.montoGasto = montoGasto;
        this.categoriaIdGasto = categoriaIdGasto;
        this.fuenteIdGasto = fuenteIdGasto;
        this.descripcionGasto = descripcionGasto;
        this.fechaHoraGasto = fechaHoraGasto;
    }

    public ExpenseModel(double montoGasto, int categoriaIdGasto, int fuenteIdGasto, String descripcionGasto, String fechaHoraGasto) {
        this.montoGasto = montoGasto;
        this.categoriaIdGasto = categoriaIdGasto;
        this.fuenteIdGasto = fuenteIdGasto;
        this.descripcionGasto = descripcionGasto;
        this.fechaHoraGasto = fechaHoraGasto;
    }

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public double getMontoGasto() {
        return montoGasto;
    }

    public void setMontoGasto(double montoGasto) {
        this.montoGasto = montoGasto;
    }

    public int getCategoriaIdGasto() {
        return categoriaIdGasto;
    }

    public void setCategoriaIdGasto(int categoriaIdGasto) {
        this.categoriaIdGasto = categoriaIdGasto;
    }

    public int getFuenteIdGasto() {
        return fuenteIdGasto;
    }

    public void setFuenteIdGasto(int fuenteIdGasto) {
        this.fuenteIdGasto = fuenteIdGasto;
    }

    public String getDescripcionGasto() {
        return descripcionGasto;
    }

    public void setDescripcionGasto(String descripcionGasto) {
        this.descripcionGasto = descripcionGasto;
    }

    public String getFechaHoraGasto() {
        return fechaHoraGasto;
    }

    public void setFechaHoraGasto(String fechaHoraGasto) {
        this.fechaHoraGasto = fechaHoraGasto;
    }
}
