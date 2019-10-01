package com.example.receitasmacanudas;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.receitasmacanudas.bancoReceitas.BancoController;
import com.example.receitasmacanudas.bancoReceitas.CriaBanco;

public class CadastroReceitas extends AppCompatActivity {

    String codigo = null;
    BancoController bancoController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_receita);
        Button salvarReceita = (Button) findViewById(R.id.salvarReceita);

        codigo = this.getIntent().getStringExtra("codigo");
        if (codigo != null) {
            bancoController = new BancoController(getBaseContext());
            EditText receita = (EditText) findViewById(R.id.nomeReceita);
            EditText ingrediente = (EditText) findViewById(R.id.ingredientes);
            EditText preparo = (EditText) findViewById(R.id.preparo);
            TextView id = findViewById(R.id.idReceita);
            Cursor cursor = bancoController.carregaDadosById(Integer.parseInt(codigo));
            id.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.id)));
            receita.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.nomeReceita)));
            ingrediente.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ingredientes)));
            preparo.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.modoPreparo)));
        }

    }


    public void adicionaReceita(View view){
        BancoController bancoController = new BancoController(getBaseContext());
        TextView id = findViewById(R.id.idReceita);
        EditText editNomeReceita = (EditText) findViewById(R.id.nomeReceita);
        EditText editIngredientes = (EditText) findViewById(R.id.ingredientes);
        EditText editPreparo = (EditText) findViewById(R.id.preparo);

        String receita = editNomeReceita.getText().toString();
        String ingredientes = editIngredientes.getText().toString();
        String preparo = editPreparo.getText().toString();
        String resultado = "";
        String idSelecionado = id.getText().toString();

        if(!idSelecionado.equals("Nova receita")){
            resultado = bancoController.alteraDado(idSelecionado, receita, ingredientes, preparo);
        }else {
            resultado = bancoController.insereDados(receita, ingredientes, preparo);
        }


        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void cancelar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}