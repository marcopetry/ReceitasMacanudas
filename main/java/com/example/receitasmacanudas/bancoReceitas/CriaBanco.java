package com.example.receitasmacanudas.bancoReceitas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    private static final String nomeBanco = "Banco-Receitas";
    private static final int versaoBanco = 5;
    public static final String tabelaReceitas = "Receitas";
    public static final String id = "_id";
    public static final String nomeReceita = "nomeReceita";
    public static final String ingredientes = "ingredientes";
    public static final String modoPreparo = "modoPreparo";

    public CriaBanco(Context context){
        super(context, nomeBanco, null, versaoBanco);
    }

    public void onCreate(SQLiteDatabase banco) {

        String sql = "CREATE TABLE " + tabelaReceitas + " ( "
                + id + " integer primary key autoincrement, "
                + nomeReceita + " text, "
                +ingredientes + " text, "
                + modoPreparo + " text) ";

        banco.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tabelaReceitas);
        onCreate(sqLiteDatabase);
    }
}
