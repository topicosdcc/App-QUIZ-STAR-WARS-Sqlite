package com.ufrr.quizvestibularufrr;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ufrr.quizvestibularufrr.dataBase.Dados;
import com.ufrr.quizvestibularufrr.dataBase.DataBase;
import com.ufrr.quizvestibularufrr.dataBase.Repositorio;

public class FragmentTab2 extends Fragment {

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private Repositorio repositorio;
    private ListView listaDados;
    private ArrayAdapter<Dados> dadosBluetoohArrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_tabs2, container, false);

        listaDados = (ListView) view.findViewById(R.id.ListView_listaDados);

        try {
            dataBase = new DataBase(getActivity());
            conn = dataBase.getWritableDatabase();
            repositorio = new Repositorio(conn);

            if (repositorio.VerificarSeoBancoDeDadosNulo() != false){
                dadosBluetoohArrayAdapter = repositorio.listarDadosDataBase(getActivity());
                listaDados.setAdapter(dadosBluetoohArrayAdapter);

            } else {
            }

        } catch(SQLException ex)
        {
            Toast.makeText(getActivity(), "Erro ao acessar o Banco de dados: "
                    + ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return view;
    }

    public static FragmentTab2 newInstance() {
        FragmentTab2 fragment = new FragmentTab2();
        return fragment;
    }

    public FragmentTab2() {

    }

}
