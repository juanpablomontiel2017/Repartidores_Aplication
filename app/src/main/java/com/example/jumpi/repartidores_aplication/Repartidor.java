package com.example.jumpi.repartidores_aplication;

public class Repartidor {
    private String IdPersona;
    private String DNIrepartidor;
    private String Usuario;
    private String Password;

    Repartidor(String IdPersona, String DNIrepartidor, String Usuario, String Password){
        this.setIdPersona(IdPersona);
        this.setDNIrepartidor(DNIrepartidor);
        this.setUsuario(Usuario);
        this.setPassword(Password);
    }

    public String getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(String idPersona) {
        IdPersona = idPersona;
    }

    public String getDNIrepartidor() {
        return DNIrepartidor;
    }

    public void setDNIrepartidor(String DNIrepartidor) {
        this.DNIrepartidor = DNIrepartidor;
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
