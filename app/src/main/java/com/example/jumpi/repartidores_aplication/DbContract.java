package com.example.jumpi.repartidores_aplication;

public class DbContract {

    /**
     * En esta clase se especifican los nombres de las tablas de la base de datos y sus respectivos campos
     *
     */


    public static final int SYNC_STATUS_OK = 0;
    public static final int SYNC_STATUS_FAILED = 1;
    public static final String SERVER_URL = "https://aquavital.000webhostapp.com/login/login.php";
    public static final String UI_UPDATE_BROADCAST = "com.example.repartidores_aplication.uiupdatebroadcast";

    public static final String DATABASE_NAME = "aquavitaldb";
    public static final String TABLE_NAME_USUARIO = "usuario";
    public static final String ID = "id";
    public static final String DNI = "dni";
    public static final String USUARIO = "usuario";
    public static final String PASSWORD = "contrasena";
    public static final String TIPO_USUARIO = "tipousuario";
    public static final String SYNC_STATUS = "syncstatus";
    public static final int ENTERO_NULO = 9999;
    public static final int DIA_OK = 1;
    public static final int DIA_FAIL = 0;





    /**
     *   TABLA ZONA DE REPARTO
     */


    public static final String TABLE_NAME_ZONA_REPARTO= "zonareparto";
    public static final String DIRECCION = "direccion";
    public static final String BARRIO = "barrio";
    public static final String REFERENCIA = "referencia";
    public static final String TELEFONO = "telefono";
    public static final String CORREO = "correo";
    public static final String FOTO = "foto";
    public static final String NOMBRE = "nombre";
    public static final String APELLIDO = "apellido";


    public static final String LUNES = "lunes";
    public static final String MARTES = "martes";
    public static final String MIERCOLES = "miercoles";
    public static final String JUEVES = "jueves";
    public static final String VIERNES = "viernes";
    public static final String SABADO = "sabado";

    /**
     *   TABLA ARTÍCULO
     */

    public static final String TABLE_NAME_ARTICULO = "articulo";
    public static final String ARTICULO = "articulo";
    public static final String PRECIO = "precio";

    /**
     *   TABLA VENTAS
     */

    public static final String TABLE_NAME_VENTA = "venta";
    public static final String ENVASE_LLENO = "envase_lleno";
    public static final String ENVASE_VACIO = "envase_vacio";
    public static final String CANILLA = "canilla";
    public static final String DISPENSER_ELECTRICO = "dispenser_electrico";
    public static final String DISPENSER_PLASTICO = "dispenser_plastico";
    public static final String ENVASE_VENTA = "envase_venta";
    public static final String ENTREGA = "entrega";

    public static final String ID_CLIENTE = "id_cliente";
    public static final String FECHA = "fecha";





}/******************************** FIN DE LA CLASE DbContract *******************************/
