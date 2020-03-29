package com.example.jumpi.repartidores_aplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class ResponsableAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public ArrayList<Responsable_Patrocinio> ResponsableArrayList;
    public ArrayList<Responsable_Patrocinio> orig;

    public ResponsableAdapter(Context context, ArrayList<Responsable_Patrocinio> ResponsableArrayList) {
        super();
        this.context = context;
        this.ResponsableArrayList = ResponsableArrayList;
    }


    public class ResponsableHolder
    {
        TextView nombre;
        TextView apellido;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Responsable_Patrocinio> results = new ArrayList<Responsable_Patrocinio>();
                if (orig == null)
                    orig = ResponsableArrayList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Responsable_Patrocinio g : orig) {
                            if (g.getName().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                ResponsableArrayList = (ArrayList<Responsable_Patrocinio>) results.values;
                notifyDataSetChanged();
            }
        };
    }



    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return ResponsableArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return ResponsableArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ResponsableHolder holder;
        if(view==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.list_nombres_responsables_item, viewGroup, false);
            holder=new ResponsableHolder();
            holder.nombre=(TextView) view.findViewById(R.id.txtName);
            holder.apellido=(TextView) view.findViewById(R.id.txtApellido);
            view.setTag(holder);
        }
        else
        {
            holder=(ResponsableHolder) view.getTag();
        }

        holder.nombre.setText(ResponsableArrayList.get(i).getName());
        holder.apellido.setText(ResponsableArrayList.get(i).getApellido());

        return view;


    }


}