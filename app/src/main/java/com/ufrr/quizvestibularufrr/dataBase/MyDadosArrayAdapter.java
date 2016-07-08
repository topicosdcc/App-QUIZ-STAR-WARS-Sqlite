package com.ufrr.quizvestibularufrr.dataBase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ufrr.quizvestibularufrr.R;

public class MyDadosArrayAdapter extends ArrayAdapter<Dados> {

    private int resource = 0;
    private LayoutInflater inflater;
    private Context context;

    public MyDadosArrayAdapter(Context context, int resource)
    {
        super(context, resource);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = resource;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View view = null;
        ViewHolder viewHolder = null;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();

            view = inflater.inflate(resource, parent, false);

            viewHolder.nomeJogador = (TextView)view.findViewById(R.id.Jogador);
            viewHolder.quizName = (TextView)view.findViewById(R.id.Quiz);
            viewHolder.data = (TextView)view.findViewById(R.id.Data);
            viewHolder.potuacao = (TextView)view.findViewById(R.id.Pontuacao);

            view.setTag(viewHolder);

            convertView = view;

        }
        else
        {
            viewHolder = (ViewHolder)convertView.getTag();
            view = convertView;
        }

        Dados dados = getItem(position);

        viewHolder.nomeJogador.setText(dados.getNome());
        viewHolder.quizName.setText(dados.getQuizName());
        viewHolder.data.setText(dados.getData());
        viewHolder.potuacao.setText(dados.getPontos());

        return view;

    }
    

    static class ViewHolder
    {
        TextView nomeJogador;
        TextView quizName;
        TextView data;
        TextView potuacao;
    }

}