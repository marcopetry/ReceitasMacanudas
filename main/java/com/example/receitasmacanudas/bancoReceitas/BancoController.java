package com.example.receitasmacanudas.bancoReceitas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {

    private CriaBanco banco;
    private SQLiteDatabase db;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String insereDados(String receita, String ingredientes, String preparo){
        ContentValues valores = new ContentValues();
        db = banco.getWritableDatabase();

        valores.put(CriaBanco.nomeReceita, receita);
        valores.put(CriaBanco.ingredientes, ingredientes);
        valores.put(CriaBanco.modoPreparo, preparo);

        long resultado = db.insert("Receitas", null, valores);
        db.close();

        if(resultado == -1){
            return "Erro ao inserir no banco.";
        }
        return "Adicionado com sucesso.";
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String [] campos = {CriaBanco.id, CriaBanco.nomeReceita, CriaBanco.ingredientes, CriaBanco.modoPreparo};

        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.tabelaReceitas, campos, null, null, null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadosById(int id) {
        Cursor cursor;
        String[] campos = {banco.id, banco.nomeReceita, banco.ingredientes, banco.modoPreparo};
        String where = banco.id + "=?";
        String[] args = {String.valueOf(id)};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.tabelaReceitas, campos, where, args, null, null, banco.id);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String alteraDado(String id, String nomeReceita, String ingredientes, String preparo) {
        ContentValues valores;
        String where;
        db = banco.getWritableDatabase();
        where = CriaBanco.id + "=?";
        String[] args = new String[]{id};
        valores = new ContentValues();
        valores.put(CriaBanco.nomeReceita, nomeReceita);
        valores.put(CriaBanco.ingredientes, ingredientes);
        valores.put(CriaBanco.modoPreparo, preparo);
        long resultado = db.update(CriaBanco.tabelaReceitas, valores, where, args);
        db.close();
        if(resultado == -1)
            return "Erro ao alterar registro";
        else
            return "Registro alterado com sucesso";
    }

    public String excluiRegistro(String idString) {
        String where = banco.id + "=?";
        db = banco.getReadableDatabase();
        long resultado = db.delete(banco.tabelaReceitas, where, new String[]{idString});
        db.close();
        if(resultado == -1)
            return "Erro ao excluir registro";
        else
            return "Registro excluído com sucesso";
    }
}
