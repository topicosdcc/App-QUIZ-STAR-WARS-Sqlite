package com.ufrr.quizvestibularufrr.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ufrr.quizvestibularufrr.R;

public class Repositorio {

    private SQLiteDatabase conn;

    public Repositorio(SQLiteDatabase conn)
    {
        this.conn = conn;
    }

    public void excluirDados (long id)
    {
        conn.delete(Dados.TABELA, " _id = ? ", new String[]
                {String.valueOf(id)});
    }

    public void inserirDados(Dados dados)
    {
        ContentValues values = inserirContentValues(dados);
        conn.insertOrThrow(dados.TABELA, null, values);
    }

    private ContentValues inserirContentValues(Dados dados)
    {
        ContentValues values = new ContentValues();
            values.put(dados.NOME    , dados.getNome());
            values.put(dados.QUIZNAME    , dados.getQuizName());
            values.put(dados.PONTOS, dados.getPontos());
            values.put(dados.DATA    , dados.getData());
        return values;
    }




    public MyDadosArrayAdapter listarDadosDataBase(Context context)
    {
        MyDadosArrayAdapter myDadosArrayAdapter = new MyDadosArrayAdapter(context, R.layout.item_bluetooth );

        //Cursor cursor  =  conn.query(Dados.TABELA, null, null, null, null, null, null);
        Cursor cursor  =  conn.query(Dados.TABELA, null, null, null, null, null, "PONTOS");

        if (cursor.getCount() > 0 ) //verifica o cursor se e nulo
        {
            //cursor.getColumnIndex() mostra as colunas na ordem de criacao
            cursor.moveToFirst();

            do { //percorra todo o cursor

                Dados dadosArray = new Dados();

                dadosArray.setId(cursor.getLong(cursor.getColumnIndex(dadosArray.ID)));
                dadosArray.setNome(cursor.getString(cursor.getColumnIndex(dadosArray.NOME)));
                dadosArray.setQuizName(cursor.getString(cursor.getColumnIndex(dadosArray.QUIZNAME)));
                dadosArray.setPontos(cursor.getString(cursor.getColumnIndex(dadosArray.PONTOS)));
                dadosArray.setData(cursor.getString(cursor.getColumnIndex(dadosArray.DATA)));

                myDadosArrayAdapter.add(dadosArray);

            }while (cursor.moveToNext()); //cursor.moveToNext() metodo que move para o proximo registro
        }

        return myDadosArrayAdapter;

    }

    public boolean VerificarSeoBancoDeDadosNulo (){
        Cursor cursor  =  conn.query(Dados.TABELA, null, null, null, null, null, null);
        if (cursor.getCount() > 0 ){ //verifica o cursor se e nulo
            return true;
        }
        else
            return false;
    }

    public int tamanhoBancoDeDados(){
        int tamanho = 0;

        Cursor cursorTamanho  =  conn.query(Dados.TABELA, null, null, null, null, null, null);

        if (cursorTamanho.getCount() > 0 )
        {
            do { //percorra todo o cursor
                tamanho += 1;
            }while (cursorTamanho.moveToNext());
        }

        return tamanho - 1;
    }


    /*
    public void InserirBancoDeDados () {

        /////////////////////////////////////////////////////////GRUPO
        ContentValues values1 = new ContentValues();
        values1.put("BAIRRO", "ARACELI SOUTO MAIOR");
        values1.put("DIASDASEMANA", "SEGUNDA | QUARTA | SEXTA");
        values1.put("HORARIO1", "6:40");
        values1.put("HORARIO2", "15:00");
        values1.put("GRUPO", "BAIRRO");
        values1.put("TURNO", "DIURNO");
        conn.insertOrThrow("COLETA", null, values1);

    }
     */


     /*
    public void atualizar(DadosBLUETOOTH dadosBluetooth)
    {
        ContentValues values = atualizarDadosBluetooth(dadosBluetooth);
        conn.update(dadosBluetooth.TABELA, values, " _id = ? ", new String[]{String.valueOf(dadosBluetooth.getId())});

    }
    */

    /*
    private ContentValues atualizarDadosBluetooth(DadosBLUETOOTH dadosPERCURSO)
    {
        ContentValues values = new ContentValues();

        values.put(dadosPERCURSO.NOME    , dadosPERCURSO.getNome());
        values.put(dadosPERCURSO.DATA    , dadosPERCURSO.getData());
        values.put(dadosPERCURSO.LOCALINICIO, dadosPERCURSO.getLocalinicio());
        values.put(dadosPERCURSO.LOCALTERMINO    , dadosPERCURSO.getLocaltermino());
        values.put(dadosPERCURSO.LONGITUDEINICIO    , dadosPERCURSO.getLongitudeinicio());
        values.put(dadosPERCURSO.LONGITUDEINICIOTERMINO    , dadosPERCURSO.getlongitudetermino());
        values.put(dadosPERCURSO.LATITUDEINICIO    , dadosPERCURSO.getLatitudeinicio());
        values.put(dadosPERCURSO.LATITUDETERMINO    , dadosPERCURSO.getLatitudetermino());
        values.put(dadosPERCURSO.DISTANCIAPERCORRIDA    , dadosPERCURSO.getDistanciapercorrida());
        values.put(dadosPERCURSO.HORARIOINICIO    , dadosPERCURSO.getHoarioinicio());
        values.put(dadosPERCURSO.HORARIOTERMINO    , dadosPERCURSO.getHoariotermino());
        values.put(dadosPERCURSO.TODOSOSLOCAISARRAY    , dadosPERCURSO.getTodososlocaisarray());
        values.put(dadosPERCURSO.TODOSOSSPEED    , dadosPERCURSO.getTodososspeed());
        values.put(dadosPERCURSO.TODOSOSLATITUDESARRAY    , dadosPERCURSO.getTodososlatitudesarray());
        values.put(dadosPERCURSO.TODOSOSLONGITUDESARRAY    , dadosPERCURSO.getTodososlongitudesarray());
        values.put(dadosPERCURSO.TIPODEATIVIDADE    , dadosPERCURSO.getTipoDeAtividade());

        return values;
    }
    */
}
