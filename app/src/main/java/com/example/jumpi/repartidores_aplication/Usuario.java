package com.example.jumpi.repartidores_aplication;

public class Usuario {
    private int IdPersona;
    private int DNI;
    private String Usuario;
    private String Password;

    Usuario(int IdPersona, int DNI, String Usuario, String Password){
        this.setIdPersona(IdPersona);
        this.setDNI(DNI);
        this.setUsuario(Usuario);
        this.setPassword(Password);
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

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
