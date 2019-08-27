package com.example.jumpi.repartidores_aplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class AdaptadorDeRepartidores extends BaseAdapter {

    private Context context;

    private int id_repartidor;

    public AdaptadorDeRepartidores(Context context) {
        this.context = context;
    }



    public int DevolverID(){

        return id_repartidor;
    }



    @Override
    public int getCount() {
        return Repartidores.ITEMS.length;
    }






    @Override
    public Repartidores  getItem(int position) {
        return Repartidores.ITEMS[position];
    }







    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }







    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item_repartidores, viewGroup, false);
        }

        ImageView imagen_repartidor = (ImageView) view.findViewById(R.id.imagen_repartidor);
        TextView nombre_apellido_repartidor = (TextView) view.findViewById(R.id.nombre_apellido_repartidor);

        final Repartidores item = (Repartidores) getItem(position);


        Glide.with(imagen_repartidor.getContext())
                .load(item.getImagenRepartidor())
                .into(imagen_repartidor);


        nombre_apellido_repartidor.setText(item.getNombreApellido());

        id_repartidor = item.getIdRepartidor();

        return view;
    }







}/****FIN DE LA CLASE AdaptadorDeRepartidores****/
