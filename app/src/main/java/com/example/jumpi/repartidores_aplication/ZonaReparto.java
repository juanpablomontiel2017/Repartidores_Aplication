package com.example.jumpi.repartidores_aplication;

public class ZonaReparto {
    private int IdPersona;
    private int DNI;
    private String Nombre;
    private String Direccion;
    private String Barrio;
    private String Referencia;
    private String Telefono;
    private String Correo;
    private int Foto;




    ZonaReparto(int IdPersona, int DNI, String Nombre, String Direccion, String Barrio, String Referencia, String Telefono, String Correo, int Foto ){
        this.setIdPersona(IdPersona);
        this.setDNI(DNI);
        this.setNombre(Nombre);
        this.setDireccion(Direccion);
        this.setBarrio(Barrio);
        this.setReferencia(Referencia);
        this.setTelefono(Telefono);
        this.setCorreo(Correo);
        this.setFoto(Foto);


    }

    public int getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(int idPersona) {
        IdPersona = idPersona;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombreo) {
        Nombre = nombreo;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getBarrio() {
        return Barrio;
    }

    public void setBarrio(String barrio) {
        Barrio = barrio;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public int getFoto() {
        return Foto;
    }

    public void setFoto(int foto) {
        Foto = foto;
    }
}
