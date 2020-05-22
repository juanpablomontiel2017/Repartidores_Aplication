package com.example.jumpi.repartidores_aplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ClientesRecyclerViewAdapter_ListaDeClientesDelDía extends RecyclerView.Adapter<ClientesRecyclerViewAdapter_ListaDeClientesDelDía.ClientesViewHolder>
        {


    /***************** DECLARACIÓN DE VARIABLES GLOBALES **************/

    private List<Clientes> clientesList;
    private List<Clientes> clientesListFull;
    Dialog myDialog;



    public class ClientesViewHolder extends RecyclerView.ViewHolder {


        ImageView iv_cliente;


        public ClientesViewHolder(@NonNull View itemView) {
            super(itemView);


            iv_cliente = itemView.findViewById(R.id.img_cliente_uno);



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

    ClientesRecyclerViewAdapter_ListaDeClientesDelDía(List<Clientes> clientesList) {

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
    public ClientesRecyclerViewAdapter_ListaDeClientesDelDía.ClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dialog_img_clientes_mapa_repartidores,parent, false);

        final ClientesRecyclerViewAdapter_ListaDeClientesDelDía.ClientesViewHolder vHolder = new ClientesRecyclerViewAdapter_ListaDeClientesDelDía.ClientesViewHolder(v);

        myDialog = new Dialog(parent.getContext());

        myDialog.setContentView(R.layout.dialog_cliente_mapa_repartidores);


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final ImageView dialog_cliente_img = (ImageView) myDialog.findViewById(R.id.img_cliente_mapa_repartidores);

                final TextView dialog_apellido_tv = (TextView) myDialog.findViewById(R.id.apellido_cliente_mapa_repartidores);

                final TextView dialog_nombre_tv = (TextView) myDialog.findViewById(R.id.nombre_cliente_mapa_repartidores);

                final TextView dialog_direccion_tv = (TextView) myDialog.findViewById(R.id.direccion_cliente_mapa_repartidores);

                final TextView dialog_barrio_tv = (TextView) myDialog.findViewById(R.id.barrio_cliente_repartidores);

                final TextView dialog_referencia_tv = (TextView) myDialog.findViewById(R.id.referencia_cliente_repartidores);




                Button dialog_cliente_btnEditar = (Button) myDialog.findViewById(R.id.button_editar_cliente_dialog_mapa_repartidores);
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

                dialog_referencia_tv.setText(clientesList.get(vHolder.getAdapterPosition()).getReferencia());


                myDialog.show();



            }/************ FIN DEL MÉTODO onClick() ********************/


        }); /****************FIN DEL MÉTODO setOnClickListener() ******************/


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








}/********************************* FIN DEL ADAPTADOR ClientesRecyclerViewAdapter **************************************/

