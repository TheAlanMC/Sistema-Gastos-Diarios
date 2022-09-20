package models;

public class ModeloTransferencia {
    private int idTransferencia;
    private double montoTransferencia;
    private int cuentaOrigenTransferencia;
    private int cuentaDestinoTransferencia;
    private String descripcionTransferencia;
    private String fechaHoraTransferencia;

    public ModeloTransferencia(int idTransferencia, double montoTransferencia, int cuentaOrigenTransferencia, int cuentaDestinoTransferencia, String descripcionTransferencia, String fechaHoraTransferencia) {
        this.idTransferencia = idTransferencia;
        this.montoTransferencia = montoTransferencia;
        this.cuentaOrigenTransferencia = cuentaOrigenTransferencia;
        this.cuentaDestinoTransferencia = cuentaDestinoTransferencia;
        this.descripcionTransferencia = descripcionTransferencia;
        this.fechaHoraTransferencia = fechaHoraTransferencia;
    }

    public ModeloTransferencia(double montoTransferencia, int cuentaOrigenTransferencia, int cuentaDestinoTransferencia, String descripcionTransferencia, String fechaHoraTransferencia) {
        this.montoTransferencia = montoTransferencia;
        this.cuentaOrigenTransferencia = cuentaOrigenTransferencia;
        this.cuentaDestinoTransferencia = cuentaDestinoTransferencia;
        this.descripcionTransferencia = descripcionTransferencia;
        this.fechaHoraTransferencia = fechaHoraTransferencia;
    }

    public int getIdTransferencia() {
        return idTransferencia;
    }

    public void setIdTransferencia(int idTransferencia) {
        this.idTransferencia = idTransferencia;
    }

    public double getMontoTransferencia() {
        return montoTransferencia;
    }

    public void setMontoTransferencia(double montoTransferencia) {
        this.montoTransferencia = montoTransferencia;
    }

    public int getCuentaOrigenTransferencia() {
        return cuentaOrigenTransferencia;
    }

    public void setCuentaOrigenTransferencia(int cuentaOrigenTransferencia) {
        this.cuentaOrigenTransferencia = cuentaOrigenTransferencia;
    }

    public int getCuentaDestinoTransferencia() {
        return cuentaDestinoTransferencia;
    }

    public void setCuentaDestinoTransferencia(int cuentaDestinoTransferencia) {
        this.cuentaDestinoTransferencia = cuentaDestinoTransferencia;
    }

    public String getDescripcionTransferencia() {
        return descripcionTransferencia;
    }

    public void setDescripcionTransferencia(String descripcionTransferencia) {
        this.descripcionTransferencia = descripcionTransferencia;
    }

    public String getFechaHoraTransferencia() {
        return fechaHoraTransferencia;
    }

    public void setFechaHoraTransferencia(String fechaHoraTransferencia) {
        this.fechaHoraTransferencia = fechaHoraTransferencia;
    }
}
