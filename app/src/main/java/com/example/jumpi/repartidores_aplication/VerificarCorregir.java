package com.example.jumpi.repartidores_aplication;

public class VerificarCorregir {

    public VerificarCorregir(){

    }

    public String corregirVariablesVacias (String editText){
        /**
         * En ocasiones el contenido de los editText se encuentra vació, por lo tanto, al convertirlos en enteros y registrarlos
         * en la base de datos genera error de entero nulo
         * Se corrige verificando que estén vaciós y se coloca cero
         */


        if (editText.isEmpty()){
            editText = "0";

        }
        return editText;

    }
}
