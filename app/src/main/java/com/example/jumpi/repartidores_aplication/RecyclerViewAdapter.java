package com.example.jumpi.repartidores_aplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import android.support.v4.view.MotionEventCompat;

import android.support.v4.app.Fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements ItemTouchHelperAdapter {//implements View.OnClickListener { //implements View.OnClickListener { //implements View.OnClickListener {



    Context mContext;
    List<Clientes> mData;
    private static final int TYPE_ITEM = 0;
   // private final LayoutInflater mInflater;
    private final OnStartDragListener mDragStartListener;
    OnItemClickListener mItemClickListener;

    //Esto también pertenece a la parte 3 del tutorial: "Fragment with RecyclerView Part 3 : item Click Listener Event : Show Custom dialog Box" del chabon Aws Rh
    Dialog myDialog;

    //Hasta aquí


    public RecyclerViewAdapter(Context mContext, List<Clientes> mData, OnStartDragListener dragListener) {
        this.mContext = mContext;
        this.mData = mData;
        mDragStartListener = dragListener;
    }








    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;

        v = LayoutInflater.from(mContext).inflate(R.layout.item_clientes, parent, false);
        final MyViewHolder vHolder = new MyViewHolder(v);


        //Esto también pertenece a la parte 3 del tutorial: "Fragment with RecyclerView Part 3 : item Click Listener Event : Show Custom dialog Box" del chabon Aws Rh"
        //Dialog Inicial
        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_clientes);


            vHolder.item_clientes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final TextView dialog_nombre_tv = (TextView) myDialog.findViewById(R.id.dialog_nombre_id);
                    //TextView dialog_direccion_tv = (TextView) myDialog.findViewById(R.id.dialog_direccion_id);
                    //TextView dialog_barrio_tv = (TextView) myDialog.findViewById(R.id.dialog_barrio_id);
                    TextView dialog_referencia_tv = (TextView) myDialog.findViewById(R.id.dialog_referencia_id);
                    TextView dialog_telefono_tv = (TextView) myDialog.findViewById(R.id.dialog_telefono_id);
                    TextView dialog_correo_tv = (TextView) myDialog.findViewById(R.id.dialog_correo_id);
                    ImageView dialog_cliente_img = (ImageView) myDialog.findViewById(R.id.dialog_img_cliente);

                    //PRUEBA (Mati)
                    Button dialog_cliente_btnVentas = (Button) myDialog.findViewById(R.id.dialog_btn_venta);
                    dialog_cliente_btnVentas.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            Intent intentVentas = new Intent(mContext, Ventas_Activity.class);

                            intentVentas.putExtra("Nombre", dialog_nombre_tv.getText());

                            mContext.startActivity(intentVentas);



                        }
                    });


                    Button dialog_cliente_btnEditar = (Button) myDialog.findViewById(R.id.dialog_btn_editar);
                    dialog_cliente_btnEditar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            Intent intentEditar = new Intent(mContext, EditarClientes.class);
                            mContext.startActivity(intentEditar);


                        }
                    });








                    Button dialog_cliente_btnEliminar = (Button) myDialog.findViewById(R.id.dialog_btn_eliminar);
                    dialog_cliente_btnEliminar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            Intent intentEliminar = new Intent(mContext, Dialog_Eliminar_Clientes.class);
                            mContext.startActivity(intentEliminar);


                        }
                    });


                    dialog_nombre_tv.setText(mData.get(vHolder.getAdapterPosition()).getNombre());
                    //dialog_direccion_tv.setText(mData.get(vHolder.getAdapterPosition()).getDireccion());
                    //dialog_barrio_tv.setText(mData.get(vHolder.getAdapterPosition()).getBarrio());
                    dialog_referencia_tv.setText(mData.get(vHolder.getAdapterPosition()).getReferencia());
                    dialog_telefono_tv.setText(mData.get(vHolder.getAdapterPosition()).getTelefono());
                    dialog_correo_tv.setText(mData.get(vHolder.getAdapterPosition()).getCorreo());
                    dialog_cliente_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getFoto());


                    Toast.makeText(mContext, "Test Click" + String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                    myDialog.show();


                }


            });


            return vHolder;

    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {


        final MyViewHolder holder= (MyViewHolder)viewHolder;

        holder.tv_nombre.setText(mData.get(position).getNombre());
        holder.tv_direccion.setText(mData.get(position).getDireccion());
        holder.tv_barrio.setText(mData.get(position).getBarrio());
        //holder.tv_referencia.setText(mData.get(position).getReferencia());
        holder.img_foto.setImageResource(mData.get(position).getFoto());
        holder.img_foto.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ItemTouchHelperViewHolder{// implements View.OnClickListener {//implements View.OnClickListener {//implements View.OnClickListener {//implements View.OnClickListener {


        //Esta variable privada pertenece a la parte 3 del tutorial: "Fragment with RecyclerView Part 3 : item Click Listener Event : Show Custom dialog Box" del chabon Aws Rh
        private LinearLayout item_clientes;
        //Hasta aquí



        private TextView tv_nombre;
        private TextView tv_direccion;
        private TextView tv_barrio;
        //private TextView tv_referencia;
        //private TextView tv_telefono;
        //private TextView tv_correo;
        private ImageView img_foto;


        //PRUEBA DEL VIDEO: "Eventos en cada elemento de un RecyclerView| Abrir un nuevo activity y pasarle valores" by Programación y más
        Button dialog_btn_venta;
        Button dialog_btn_editar;
        Button dialog_btn_eliminar;
        //Hasta aquí




        public MyViewHolder(View itemView) {


            super(itemView);

            //Esto también pertenece a la parte 3 del tutorial: "Fragment with RecyclerView Part 3 : item Click Listener Event : Show Custom dialog Box" del chabon Aws Rh
            item_clientes = (LinearLayout) itemView.findViewById(R.id.clientes_item_id);
            //Hasta aquí

            tv_nombre = (TextView) itemView.findViewById(R.id.nombre_cliente);
            tv_direccion = (TextView) itemView.findViewById(R.id.direccion_cliente);
            tv_barrio = (TextView) itemView.findViewById(R.id.barrio_cliente);
            //tv_referencia = (TextView) itemView.findViewById(R.id.referencia_cliente);
            //tv_telefono = (TextView) itemView.findViewById(R.id.te)
            img_foto = (ImageView) itemView.findViewById(R.id.img_cliente);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, getPosition());
            }
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }

    @Override
    public void onItemDismiss(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //Log.v("", "Log position" + fromPosition + " " + toPosition);
        if (fromPosition < mData.size() && toPosition < mData.size()) {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mData, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mData, i, i - 1);
                }
            }
            notifyItemMoved(fromPosition, toPosition);
        }
        return true;
    }

    public void updateList(List<Clientes> list) {
        mData = list;
        notifyDataSetChanged();
    }

    //Hasta aquí


}





