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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements ItemTouchHelperAdapter {





 /**** DECLARACIÓN DE VARIABLES GLOBALES ****/



    Context mContext;

    List<Clientes> mData;

    private static final int TYPE_ITEM = 0;

    private final OnStartDragListener mDragStartListener;

    OnItemClickListener mItemClickListener;

    Dialog myDialog;







    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/







    public RecyclerViewAdapter(Context mContext, List<Clientes> mData, OnStartDragListener dragListener) {
        this.mContext = mContext;
        this.mData = mData;
        mDragStartListener = dragListener;
    }









    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/










    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        final View v;

        v = LayoutInflater.from(mContext).inflate(R.layout.item_clientes, parent, false);

        final MyViewHolder vHolder = new MyViewHolder(v);

        myDialog = new Dialog(mContext);

        myDialog.setContentView(R.layout.dialog_clientes);


            vHolder.item_clientes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    final TextView dialog_apellido_tv = (TextView) myDialog.findViewById(R.id.dialog_apellido_id);

                    final TextView dialog_nombre_tv = (TextView) myDialog.findViewById(R.id.dialog_nombre_id);

                    TextView dialog_referencia_tv = (TextView) myDialog.findViewById(R.id.dialog_referencia_id);

                    TextView dialog_telefono_tv = (TextView) myDialog.findViewById(R.id.dialog_telefono_id);

                    TextView dialog_correo_tv = (TextView) myDialog.findViewById(R.id.dialog_correo_id);

                    ImageView dialog_cliente_img = (ImageView) myDialog.findViewById(R.id.dialog_img_cliente);








                    Button dialog_cliente_btnVentas = (Button) myDialog.findViewById(R.id.dialog_btn_venta);

                    dialog_cliente_btnVentas.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            Intent intentVentas = new Intent(mContext, Ventas_Activity.class);

                            intentVentas.putExtra("Apellido", dialog_apellido_tv.getText());

                            intentVentas.putExtra("Nombre", dialog_nombre_tv.getText());

                            intentVentas.putExtra("Direccion", mData.get(vHolder.getAdapterPosition()).getDireccion());

                            intentVentas.putExtra("Barrio", mData.get(vHolder.getAdapterPosition()).getBarrio());


                            mContext.startActivity(intentVentas);



                           }
                        });












                    Button dialog_cliente_btnEditar = (Button) myDialog.findViewById(R.id.dialog_btn_editar);

                    dialog_cliente_btnEditar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                        Intent intentEditar = new Intent(mContext, EditarClientes.class);
                                        
                        intentEditar.putExtra("ApellidoEC", mData.get(vHolder.getAdapterPosition()).getApellido());

                        intentEditar.putExtra("NombreEC", mData.get(vHolder.getAdapterPosition()).getNombre());

                        intentEditar.putExtra("DireccionEC", mData.get(vHolder.getAdapterPosition()).getDireccion());

                        intentEditar.putExtra("BarrioEC",  mData.get(vHolder.getAdapterPosition()).getBarrio());

                        intentEditar.putExtra("TelefonoEC",mData.get(vHolder.getAdapterPosition()).getTelefono());

                        intentEditar.putExtra("CorreoEC",  mData.get(vHolder.getAdapterPosition()).getCorreo());

                        intentEditar.putExtra("DNIEC",  mData.get(vHolder.getAdapterPosition()).getDNI());

                        intentEditar.putExtra("ReferenciaEC", mData.get(vHolder.getAdapterPosition()).getReferencia());

                        mContext.startActivity(intentEditar);

                        }
                    });












                    Button dialog_cliente_btnEliminar = (Button) myDialog.findViewById(R.id.dialog_btn_eliminar);

                    dialog_cliente_btnEliminar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {


                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        builder.setIcon(R.drawable.ic_msj_alerta);

                        builder.setTitle("Desea eliminar este cliente?!");

                        builder.setMessage("Al presionar el botón 'Aceptar' se borrará al cliente de la lista. ¿Desea continuar?");


                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                int newPosition = vHolder.getAdapterPosition();

                                                mData.remove(newPosition);

                                                notifyItemRemoved(newPosition);

                                                notifyItemRangeChanged(newPosition, mData.size());

                                                Toast.makeText(mContext, "El cliente ha sido eliminado de la lista", Toast.LENGTH_LONG).show();

                                                dialog.dismiss();


                                                   }
                                            });




                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {

                                                dialog.dismiss();
                            }
                        });



                        AlertDialog dialog = builder.create();

                        dialog.show();





                                    }

                        });

                    dialog_apellido_tv.setText(mData.get(vHolder.getAdapterPosition()).getApellido());

                    dialog_nombre_tv.setText(mData.get(vHolder.getAdapterPosition()).getNombre());

                    dialog_referencia_tv.setText(mData.get(vHolder.getAdapterPosition()).getReferencia());

                    dialog_telefono_tv.setText(mData.get(vHolder.getAdapterPosition()).getTelefono());

                    dialog_correo_tv.setText(mData.get(vHolder.getAdapterPosition()).getCorreo());

                    dialog_cliente_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getFoto());

                    myDialog.show();


                }


            });
        return vHolder;
    }







    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/




    @Override
    public int getItemViewType(int position) {


                        return TYPE_ITEM;
    }







    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/






    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {


                        final MyViewHolder holder= (MyViewHolder)viewHolder;

                        holder.tv_apellido.setText(mData.get(position).getApellido());
                        holder.tv_nombre.setText(mData.get(position).getNombre());
                        holder.tv_direccion.setText(mData.get(position).getDireccion());
                        holder.tv_barrio.setText(mData.get(position).getBarrio());
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


    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/


    @Override
    public int getItemCount() {

                        return mData.size();
    }





    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/

    public interface OnItemClickListener {

                        void onItemClick(View view, int position);

    }





    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {

                        this.mItemClickListener = mItemClickListener;

    }








    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ItemTouchHelperViewHolder{


                        private LinearLayout item_clientes;


                        private TextView tv_apellido;
                        private TextView tv_nombre;
                        private TextView tv_direccion;
                        private TextView tv_barrio;
                        private ImageView img_foto;


                        Button dialog_btn_venta;
                        Button dialog_btn_editar;
                        Button dialog_btn_eliminar;



        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/

        public MyViewHolder(View itemView) {


            super(itemView);

            item_clientes = (LinearLayout) itemView.findViewById(R.id.clientes_item_id);

            tv_apellido = (TextView) itemView.findViewById(R.id.apellido_cliente);
            tv_nombre = (TextView) itemView.findViewById(R.id.nombre_cliente);
            tv_direccion = (TextView) itemView.findViewById(R.id.direccion_cliente);
            tv_barrio = (TextView) itemView.findViewById(R.id.barrio_cliente);
            img_foto = (ImageView) itemView.findViewById(R.id.img_cliente);
            itemView.setOnClickListener(this);

        }



        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/

        @Override
        public void onClick(View view) {

            if (mItemClickListener != null) {

                mItemClickListener.onItemClick(view, getPosition());

            }

        }




        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/



        @Override
        public void onItemSelected() {

            itemView.setBackgroundColor(Color.LTGRAY);

        }



        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/
        /********************************************************************************/

        @Override
        public void onItemClear() {

            itemView.setBackgroundColor(0);

        }
    }



    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/



    @Override
    public void onItemDismiss(int position) {

                        mData.remove(position);

                        notifyItemRemoved(position);

    }






    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/




    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {

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









    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/
    /********************************************************************************/







    public void updateList(List<Clientes> list) {


        mData = list;

        notifyDataSetChanged();


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








}/********************************* FIN DE LA CLASE RecyclerViewAdapter **************************************/


