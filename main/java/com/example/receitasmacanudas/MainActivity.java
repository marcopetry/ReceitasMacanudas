package com.example.receitasmacanudas;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.receitasmacanudas.bancoReceitas.BancoController;
import com.example.receitasmacanudas.bancoReceitas.CriaBanco;

public class MainActivity extends AppCompatActivity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button botaoAdicionar = (Button) findViewById(R.id.adicionarNovaReceita);

        BancoController bancoController = new BancoController(getBaseContext());
        final Cursor cursor = bancoController.carregaDados();
        String[] nomeCampos =new String[]{CriaBanco.id, CriaBanco.nomeReceita};
        int[] idViews = new int[]{R.id.idReceita, R.id.nomeReceitaLista};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.lista_receitas, cursor, nomeCampos, idViews, 0);
        lista = findViewById(R.id.listaMenu);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String codigo = "";
                cursor.moveToPosition(i);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.id));
                Intent intent = new Intent(getBaseContext(), ViewReceita.class);
                Bundle params = new Bundle();
                params.putString("codigo", codigo);
                intent.putExtras(params);
                startActivity(intent);
            }
        });

    }

    public void adicionarNovaReceita(View view) {
        Intent intent = new Intent(this, CadastroReceitas.class);
        startActivity(intent);
    }
}

