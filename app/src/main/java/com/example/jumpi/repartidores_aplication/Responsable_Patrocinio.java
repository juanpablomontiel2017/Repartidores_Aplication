package com.example.jumpi.repartidores_aplication;

public class Responsable_Patrocinio {

    private String nombre;
    private String apellido;
    private Integer id;

    public Responsable_Patrocinio(String name, String apellido, Integer id){
        this.nombre = name;
        this.apellido = apellido;
        this.id = id;
    }

    public String getName(){
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public Integer getId() {
        return  id;
    }
}