package com.example.jumpi.repartidores_aplication;

public class Clientes {





    private int IdPersona;
    private int DNI;
    private int Foto;
    private String Apellido;
    private String Nombre;
    private String Direccion;
    private String Barrio;
    private String Referencia;
    private String Telefono;
    private String Correo;



    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/





    public Clientes(int DNI, int IdPersona, int foto, String apellido, String nombre, String direccion, String barrio,String referencia, String telefono, String correo) {

        IdPersona = IdPersona;
        DNI=DNI;

        Foto = foto;
        Apellido = apellido;
        Nombre = nombre;
        Direccion = direccion;
        Barrio = barrio;
        Referencia = referencia;
        Telefono = telefono;
        Correo = correo;




    }







    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/






    /************ Método Getter ************/


    public int getIdPersona() {
        return IdPersona;
    }

    public int getFoto() {
        return Foto;
    }

    public String getApellido() {
        return Apellido;
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

    public String getTelefono() {return Telefono;}

    public String getCorreo() {return Correo;}

    public int getDNI() {
        return DNI;
    }

    public String getReferencia() {return Referencia;}










    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/






    /************ Método Setter ************/

    public void setIdPersona(int idPersona) {
        IdPersona = idPersona;
    }

    public void setFoto(int foto) {
        Foto = foto;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
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

    public void setTelefono (String telefono) {
        Telefono = telefono;
    }

    public void setCorreo (String correo) {
        Correo = correo;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;

    }








    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/
    /**********************************************************************************************/








}/************************* FIN DE LA CLASE Clientes ****************************/

