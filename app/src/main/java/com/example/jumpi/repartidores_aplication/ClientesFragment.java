package com.example.jumpi.repartidores_aplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link //ClientesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link //ClientesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientesFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    //the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   // private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    //private OnFragmentInteractionListener mListener;


    private RecyclerView myrecyclerview;
    private List<Clientes> lstClientes;






    public ClientesFragment() {
        // Required empty public constructor






    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //* @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     //* @return A new instance of fragment ClientesFragment.
     */
    // TODO: Rename and change types and number of parameters

    /*
    public static ClientesFragment newInstance(String param1, String param2) {
        ClientesFragment fragment = new ClientesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        

        lstClientes = new ArrayList<>();
        lstClientes.add(new Clientes(R.drawable.ricardofort,"Ricardo Fort","Urquiza 590","Centro","Casa rosada con portón blanco","(+54) 3644 413254","rikypapa19@gmail.com"));
        lstClientes.add(new Clientes(R.drawable.lautaromartinez,"Lautaro Martinez","Mitre 190","Centro","Casa celeste con porton negro","(+54) 3644 442210", "lauti10@gmail.com"));
        lstClientes.add(new Clientes(R.drawable.ricardocenturion,"Ricardo Centurion","Sarmiento 150","Apache","Casa verde con portón de chapa","(+54) 3644 402356", "rikicentuwachin50@gmail.com"));
        lstClientes.add(new Clientes(R.drawable.leomessi,"Leo Messi","Belgrano 401","Loma Linda","Casa violeta con rejas negras", "(+54) 3734 448890","leomessi10@gmail.com"));
        lstClientes.add(new Clientes(R.drawable.omarjudis,"Omar Judis","Roque 232","San Cayetano","Casa de color rojo con portón negro","(+54) 3644 506643", "omarju@gmail.com"));



        lstClientes.add(new Clientes(R.drawable.ricardofort,"Ricardo Fort","Urquiza 590","Centro","Casa rosada con portón blanco","(+54) 3644 413254","rikypapa19@gmail.com"));
        lstClientes.add(new Clientes(R.drawable.lautaromartinez,"Lautaro Martinez","Mitre 190","Centro","Casa celeste con porton negro","(+54) 3644 442210", "lauti10@gmail.com"));
        lstClientes.add(new Clientes(R.drawable.ricardocenturion,"Ricardo Centurion","Sarmiento 150","Apache","Casa verde con portón de chapa","(+54) 3644 402356", "rikicentuwachin50@gmail.com"));
        lstClientes.add(new Clientes(R.drawable.leomessi,"Leo Messi","Belgrano 401","Loma Linda","Casa violeta con rejas negras", "(+54) 3734 448890","leomessi10@gmail.com"));
        lstClientes.add(new Clientes(R.drawable.omarjudis,"Omar Judis","Roque 232","San Cayetano","Casa de color rojo con portón negro","(+54) 3644 506643", "omarju@gmail.com"));






       /* if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        */

    }






    //Viendo la parte 2 del tutorial de Aws Rh "Fragment with Recyclerview"






    //Voy a trabajar primero con este método onCreateView según el video "Menú con Pestañas en Android Studio (Tabbed Activity), by Developeru"

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_clientes, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.clientes_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),lstClientes);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);






        return v;




        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_clientes, container, false);




    }

    //Hasta aquí






    // TODO: Rename method, update argument and hook method into UI event
    /*
    public void onButtonPressed(Uri uri) {

        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    */

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    */
}
