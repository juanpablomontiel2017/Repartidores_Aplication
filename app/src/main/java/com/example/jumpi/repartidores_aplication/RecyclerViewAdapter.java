package com.example.jumpi.repartidores_aplication;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    Context mContext;
    List<Clientes>  mData;
    //Esto también pertenece a la parte 3 del tutorial: "Fragment with RecyclerView Part 3 : item Click Listener Event : Show Custom dialog Box" del chabon Aws Rh
    Dialog myDialog;
    //Hasta aquí


    public RecyclerViewAdapter(Context mContext, List<Clientes> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;

        v = LayoutInflater.from(mContext).inflate(R.layout.item_clientes,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        //Esto también pertenece a la parte 3 del tutorial: "Fragment with RecyclerView Part 3 : item Click Listener Event : Show Custom dialog Box" del chabon Aws Rh"

        //Dialog Inicial
        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_clientes);


        vHolder.item_clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView dialog_nombre_tv = (TextView)myDialog.findViewById(R.id.dialog_nombre_id);
                TextView dialog_direccion_tv = (TextView) myDialog.findViewById(R.id.dialog_direccion_id);
                TextView dialog_barrio_tv = (TextView) myDialog.findViewById(R.id.dialog_barrio_id);
                TextView dialog_referencia_tv = (TextView) myDialog.findViewById(R.id.dialog_referencia_id);
                ImageView dialog_cliente_img =(ImageView) myDialog.findViewById(R.id.dialog_img_cliente);

                dialog_nombre_tv.setText(mData.get(vHolder.getAdapterPosition()).getNombre());
                dialog_direccion_tv.setText(mData.get(vHolder.getAdapterPosition()).getDireccion());
                dialog_barrio_tv.setText(mData.get(vHolder.getAdapterPosition()).getBarrio());
                dialog_referencia_tv.setText(mData.get(vHolder.getAdapterPosition()).getReferencia());
                dialog_cliente_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getFoto());


                Toast.makeText(mContext,"Test Click" + String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                myDialog.show();
            }
        });
        //Hasta aquí


        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_nombre.setText(mData.get(position).getNombre());
        holder.tv_direccion.setText(mData.get(position).getDireccion());
        holder.tv_barrio.setText(mData.get(position).getBarrio());
        holder.tv_referencia.setText(mData.get(position).getReferencia());
        holder.img_foto.setImageResource(mData.get(position).getFoto());



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

       //Esta variable privada pertenece a la parte 3 del tutorial: "Fragment with RecyclerView Part 3 : item Click Listener Event : Show Custom dialog Box" del chabon Aws Rh
       private LinearLayout item_clientes;
       //Hasta aquí

       private TextView tv_nombre;
       private TextView tv_direccion;
       private TextView tv_barrio;
       private TextView tv_referencia;
       private ImageView img_foto;


        public MyViewHolder(View itemView) {

            super(itemView);

            //Esto también pertenece a la parte 3 del tutorial: "Fragment with RecyclerView Part 3 : item Click Listener Event : Show Custom dialog Box" del chabon Aws Rh
            item_clientes = (LinearLayout) itemView.findViewById(R.id.clientes_item_id);
            //Hasta aquí

            tv_nombre = (TextView) itemView.findViewById(R.id.nombre_cliente);
            tv_direccion = (TextView) itemView.findViewById(R.id.direccion_cliente);
            tv_barrio = (TextView) itemView.findViewById(R.id.barrio_cliente);
            tv_referencia = (TextView) itemView.findViewById(R.id.referencia_cliente);
            img_foto = (ImageView) itemView.findViewById(R.id.img_cliente);

        }
    }

}
