package com.example.jumpi.repartidores_aplication;

public class Articulos {
    private int IdArticulo;
    private String Articulo;
    private int precio;

    public Articulos(){

    }

    public Articulos(int id, String articulo, int Precio){
        IdArticulo = id;
        Articulo = articulo;
        precio = Precio;
    }

    public int getIdArticulo() {
        return IdArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        IdArticulo = idArticulo;
    }

    public String getArticulo() {
        return Articulo;
    }

    public void setArticulo(String articulo) {
        Articulo = articulo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
