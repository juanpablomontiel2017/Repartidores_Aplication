package com.example.jumpi.repartidores_aplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ClientesRecyclerViewAdapter extends RecyclerView.Adapter<ClientesRecyclerViewAdapter.ClientesViewHolder> implements Filterable {


    /***************** DECLARACIÓN DE VARIABLES GLOBALES **************/

    private List<Clientes> clientesList;
    private List<Clientes> clientesListFull;
    Dialog myDialog;



    public class ClientesViewHolder extends RecyclerView.ViewHolder {


        ImageView iv_cliente;
        TextView tv_apellido_cliente;
        TextView tv_nombre_cliente;
        TextView tv_direccion_cliente;
        TextView tv_barrio_cliente;

        public ClientesViewHolder(@NonNull View itemView) {
            super(itemView);


            iv_cliente = itemView.findViewById(R.id.img_cliente);
            tv_apellido_cliente = itemView.findViewById(R.id.apellido_cliente);
            tv_nombre_cliente = itemView.findViewById(R.id.nombre_cliente);
            tv_direccion_cliente = itemView.findViewById(R.id.direccion_cliente);
            tv_barrio_cliente = itemView.findViewById(R.id.barrio_cliente);


        }


    }/*************** FIN DE LA CLASE ClientesViewHolder ***************/


    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/

    ClientesRecyclerViewAdapter(List<Clientes> clientesList) {

        this.clientesList = clientesList;
        clientesListFull = new ArrayList<>(clientesList);

    }/*************** FIN DEL CONSTRUCTOR ***************/


    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/


    @NonNull
    @Override
    public ClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_clientes,parent, false);


        final ClientesViewHolder vHolder = new ClientesViewHolder(v);

        myDialog = new Dialog(parent.getContext());

        myDialog.setContentView(R.layout.dialog_clientes);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final ImageView dialog_cliente_img = (ImageView) myDialog.findViewById(R.id.dialog_img_cliente);

                final TextView dialog_apellido_tv = (TextView) myDialog.findViewById(R.id.dialog_apellido_id);

                final TextView dialog_nombre_tv = (TextView) myDialog.findViewById(R.id.dialog_nombre_id);

                final TextView dialog_direccion_tv = (TextView) myDialog.findViewById(R.id.dialog_direccion_id);

                final TextView dialog_barrio_tv = (TextView) myDialog.findViewById(R.id.dialog_barrio_id);








                Button dialog_cliente_btnVentas = (Button) myDialog.findViewById(R.id.dialog_btn_venta);

                dialog_cliente_btnVentas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intentVentas = new Intent(parent.getContext(), RealizarVentasClientesSupervisor.class);


                        intentVentas.putExtra("Foto",clientesList.get(vHolder.getAdapterPosition()).getFoto());

                        intentVentas.putExtra("DNI",clientesList.get(vHolder.getAdapterPosition()).getDNI());

                        intentVentas.putExtra("Apellido",clientesList.get(vHolder.getAdapterPosition()).getApellido());

                        intentVentas.putExtra("Nombre",clientesList.get(vHolder.getAdapterPosition()).getNombre());

                        intentVentas.putExtra("Codigo_Area",clientesList.get(vHolder.getAdapterPosition()).getCodigo_Area());

                        intentVentas.putExtra("Telefono",clientesList.get(vHolder.getAdapterPosition()).getTelefono());

                        intentVentas.putExtra("Direccion", clientesList.get(vHolder.getAdapterPosition()).getDireccion());

                        intentVentas.putExtra("Barrio", clientesList.get(vHolder.getAdapterPosition()).getBarrio());

                        intentVentas.putExtra("Referencia", clientesList.get(vHolder.getAdapterPosition()).getReferencia());

                        intentVentas.putExtra("Correo", clientesList.get(vHolder.getAdapterPosition()).getCorreo());


                        parent.getContext().startActivity(intentVentas);



                    }
                });





                Button dialog_cliente_btnEditar = (Button) myDialog.findViewById(R.id.dialog_btn_editar);
                dialog_cliente_btnEditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intentEditar = new Intent(parent.getContext(), EditarClientes.class);


                        intentEditar.putExtra("Foto",clientesList.get(vHolder.getAdapterPosition()).getFoto());

                        intentEditar.putExtra("DNI",  clientesList.get(vHolder.getAdapterPosition()).getDNI());

                        intentEditar.putExtra("Apellido", clientesList.get(vHolder.getAdapterPosition()).getApellido());

                        intentEditar.putExtra("Nombre", clientesList.get(vHolder.getAdapterPosition()).getNombre());

                        intentEditar.putExtra("Codigo_Area",clientesList.get(vHolder.getAdapterPosition()).getCodigo_Area());

                        intentEditar.putExtra("Telefono",clientesList.get(vHolder.getAdapterPosition()).getTelefono());

                        intentEditar.putExtra("Direccion", clientesList.get(vHolder.getAdapterPosition()).getDireccion());

                        intentEditar.putExtra("Barrio",  clientesList.get(vHolder.getAdapterPosition()).getBarrio());

                        intentEditar.putExtra("Telefono",clientesList.get(vHolder.getAdapterPosition()).getTelefono());

                        intentEditar.putExtra("Correo",  clientesList.get(vHolder.getAdapterPosition()).getCorreo());

                        intentEditar.putExtra("Referencia", clientesList.get(vHolder.getAdapterPosition()).getReferencia());


                        parent.getContext().startActivity(intentEditar);

                    }
                });








                /** Muestra algunos de los datos del cliente en el diálogo **/

                dialog_cliente_img.setImageResource(clientesList.get(vHolder.getAdapterPosition()).getFoto());

                dialog_apellido_tv.setText(clientesList.get(vHolder.getAdapterPosition()).getApellido());

                dialog_nombre_tv.setText(clientesList.get(vHolder.getAdapterPosition()).getNombre());

                dialog_direccion_tv.setText(clientesList.get(vHolder.getAdapterPosition()).getDireccion());

                dialog_barrio_tv.setText(clientesList.get(vHolder.getAdapterPosition()).getBarrio());



                myDialog.show();



            }/************ FIN DEL MÉTODO onClick() ********************/


        });/************+****FIN DEL MÉTODO setOnClickListener() ******************/





        return vHolder;


    }/*************** FIN DEL MÉTODO onCreateViewHolder() ***************/


    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/


    @Override
    public void onBindViewHolder(@NonNull ClientesViewHolder holder, int position) {


        Clientes actualItem = clientesList.get(position);


        holder.iv_cliente.setImageResource(actualItem.getFoto());
        holder.tv_apellido_cliente.setText(actualItem.getApellido());
        holder.tv_nombre_cliente.setText(actualItem.getNombre());
        holder.tv_direccion_cliente.setText(actualItem.getDireccion());
        holder.tv_barrio_cliente.setText(actualItem.getBarrio());


    }/*************** FIN DEL MÉTODO onBindViewHolder() ***************/


    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/

    @Override
    public int getItemCount() {

        return clientesList.size();

    }/*************** FIN DEL MÉTODO getItemCount() ***************/

    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/


    @Override
    public Filter getFilter() {

        return clientesFilter;

    }/*************** FIN DEL MÉTODO getFilter() ***************/


    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/


    private Filter clientesFilter = new Filter() {


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {


            List<Clientes> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(clientesListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Clientes item : clientesListFull) {
                    if (item.getNombre().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;

        }



        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            clientesList.clear();
            clientesList.addAll((List) filterResults.values);
            notifyDataSetChanged();

        }/*************** FIN DEL MÉTODO publishResults() ***************/



    }; /*************** FIN DEL MÉTODO performFiltering() ***************/






    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/
    /*************************************************************************/


}/********************************* FIN DEL ADAPTADOR ClientesRecyclerViewAdapter **************************************/


