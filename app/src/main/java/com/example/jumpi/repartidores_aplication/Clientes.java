package com.example.jumpi.repartidores_aplication;

public class Clientes {

    private int Foto;
    private String Nombre;
    private String Direccion;
    private String Barrio;
    private String Referencia;
    private String Telefono;
    private String Correo;

    public Clientes() {

    }

    public Clientes(int foto, String nombre, String direccion, String barrio,String referencia, String telefono, String correo) {
        Foto = foto;
        Nombre = nombre;
        Direccion = direccion;
        Barrio = barrio;
        Referencia = referencia;
        Telefono = telefono;
        Correo = correo;




    }

    //Getter


    public int getFoto() {
        return Foto;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getBarrio() {
        return Barrio;
    }

    public String getReferencia() {return Referencia;}

    public String getTelefono() {return Telefono;}

    public String getCorreo() {return Correo;}




    //Setter


    public void setFoto(int foto) {
        Foto = foto;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public void setBarrio(String barrio) {
        Barrio = barrio;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;

    }
    public void setTelefono (String telefono) {
        Telefono = telefono;
    }

    public void setCorreo (String correo) {
        Correo = correo;
    }
}

