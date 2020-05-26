package com.example.jumpi.repartidores_aplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BarriosRecyclerViewAdapter extends RecyclerView.Adapter<BarriosRecyclerViewAdapter.BarriosViewHolder>
        implements Filterable {


    /***************** DECLARACIÓN DE VARIABLES GLOBALES **************/

    private List<Barrios> barriosList;
    private List<Barrios> barriosListFull;



    public class BarriosViewHolder extends RecyclerView.ViewHolder {


        ImageView iv_barrio;
        TextView tv_nombre_barrio;
        TextView tv_numero_consumo;


        public BarriosViewHolder(@NonNull View itemView) {
            super(itemView);


            iv_barrio = itemView.findViewById(R.id.img_barrio);
            tv_nombre_barrio = itemView.findViewById(R.id.nombre_barrio);
            tv_numero_consumo = itemView.findViewById(R.id.numero_consumo);


        }


    }/*************** FIN DE LA CLASE BarriosViewHolder ***************/


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




    BarriosRecyclerViewAdapter(List<Barrios> barriosList) {

        this.barriosList = barriosList;
        barriosListFull = new ArrayList<>(barriosList);

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
    public BarriosRecyclerViewAdapter.BarriosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barrio,parent, false);


        final BarriosRecyclerViewAdapter.BarriosViewHolder vHolder = new BarriosRecyclerViewAdapter.BarriosViewHolder(v);



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
    public void onBindViewHolder(@NonNull BarriosViewHolder holder, int position) {



        Barrios actualItem = barriosList.get(position);


        holder.iv_barrio.setImageResource(actualItem.getFoto());
        holder.tv_nombre_barrio.setText(actualItem.getNombre());
        holder.tv_numero_consumo.setText(actualItem.getConsumo());

    }



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
        return barriosList.size();
    }


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
        return barriosFilter;
    }



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

    private Filter barriosFilter = new Filter() {


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {


            List<Barrios> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(barriosListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Barrios item : barriosListFull) {
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

            barriosList.clear();
            barriosList.addAll((List) filterResults.values);
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


}
