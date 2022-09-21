package models;

public class ExpenseModel {
    private int idGasto;
    private double montoGasto;
    private int fuenteIdGasto;
    private int categoriaIdGasto;
    private String descripcionGasto;
    private String fechaHoraGasto;

    public ExpenseModel(int idGasto, double montoGasto, int fuenteIdGasto, int categoriaIdGasto, String descripcionGasto, String fechaHoraGasto) {
        this.idGasto = idGasto;
        this.montoGasto = montoGasto;
        this.fuenteIdGasto = fuenteIdGasto;
        this.categoriaIdGasto = categoriaIdGasto;
        this.descripcionGasto = descripcionGasto;
        this.fechaHoraGasto = fechaHoraGasto;
    }

    public ExpenseModel(double montoGasto, int fuenteIdGasto, int categoriaIdGasto, String descripcionGasto, String fechaHoraGasto) {
        this.montoGasto = montoGasto;
        this.fuenteIdGasto = fuenteIdGasto;
        this.categoriaIdGasto = categoriaIdGasto;
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

    public int getFuenteIdGasto() {
        return fuenteIdGasto;
    }

    public void setFuenteIdGasto(int fuenteIdGasto) {
        this.fuenteIdGasto = fuenteIdGasto;
    }

    public int getCategoriaIdGasto() {
        return categoriaIdGasto;
    }

    public void setCategoriaIdGasto(int categoriaIdGasto) {
        this.categoriaIdGasto = categoriaIdGasto;
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
