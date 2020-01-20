package com.example.cuadra;

public class ModelClass {
    String numero,fecha,cuenta,monto;

    public ModelClass(String numero, String fecha, String cuenta, String monto) {
        this.numero = numero;
        this.fecha = fecha;
        this.cuenta = cuenta;
        this.monto = monto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
