package com.example.receitasmacanudas;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.receitasmacanudas.bancoReceitas.BancoController;
import com.example.receitasmacanudas.bancoReceitas.CriaBanco;

public class ViewReceita extends AppCompatActivity {

    String codigo = null;
    BancoController bancoController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_receita);

        codigo = this.getIntent().getStringExtra("codigo");
        if (codigo != null) {
            bancoController = new BancoController(getBaseContext());
            TextView receita = (TextView) findViewById(R.id.nomeReceita);
            TextView ingrediente = (TextView) findViewById(R.id.ingredientes);
            TextView preparo = (TextView) findViewById(R.id.preparo);
            TextView id = (TextView) findViewById(R.id.idReceita);
            Cursor cursor = bancoController.carregaDadosById(Integer.parseInt(codigo));
            id.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.id)));
            receita.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.nomeReceita)));
            ingrediente.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ingredientes)));
            preparo.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.modoPreparo)));
        }
    }

    public void alterarReceita(View view){
        TextView id = (TextView) findViewById(R.id.idReceita);
        String codigo = id.getText().toString();
        Intent intent = new Intent(getBaseContext(), CadastroReceitas.class);
        Bundle params = new Bundle();
        params.putString("codigo", codigo);
        intent.putExtras(params);
        startActivity(intent);
    }

    public void excluirReceita(View view){
        TextView id = (TextView) findViewById(R.id.idReceita);
        String codigo = id.getText().toString();
        String resultado;
        resultado = bancoController.excluiRegistro(codigo);
        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void voltarInicio(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}