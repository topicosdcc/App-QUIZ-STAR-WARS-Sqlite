package com.ufrr.quizvestibularufrr.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    //Construtor da classe pai
    public DataBase(Context context) {
        //Construtor da classe pai -
        super(context, "RANKQUIZ", null, 1); //Passa as informacoes necessarias - Parametros
        // Parametros (context, nome banco de dados String, Cursor null - nao esta alterando, Versao do Banco de dados)
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Resposavel pela criacao do banco de dados

        db.execSQL(getCreateContato());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Responsavel por atualizar o banco de dados

    }


    public static String getCreateContato() {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append(" CREATE TABLE IF NOT EXISTS RANKQUIZ ( ");          //Criar a tabela - Vericar se existe o banco de Dados
        sqlBuilder.append("_id                           INTEGER     NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("NOME                        TEXT, ");
        sqlBuilder.append("QUIZNAME                    TEXT, ");
        sqlBuilder.append("PONTOS                      TEXT, ");
        sqlBuilder.append("DATA                        TEXT ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }
}
