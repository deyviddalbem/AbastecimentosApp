package br.edu.ifro.vilhena.abastecimentosv2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifro.vilhena.abastecimentosv2.DAO.AbastecimentoDAO;
import br.edu.ifro.vilhena.abastecimentosv2.DAO.CombustivelDAO;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Abastecimento;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Combustivel;

public class ListarActivity extends AppCompatActivity {
    private ListView listar;
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnListarCombustiveis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);


        listar = findViewById(R.id.listaAbastecimentos);
        btnAdd = findViewById(R.id.addAbastecimento);
        btnListarCombustiveis = findViewById(R.id.activityListarBtnListarCombustiveis);


        btnListarCombustiveis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarActivity.this,ListarCombustivelActivity.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarActivity.this, AbastecimentoActivity.class);
                startActivity(intent);
            }
        });

        atualizarLista();

        registerForContextMenu(listar);

        listar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Abastecimento abastecimento = (Abastecimento) listar.getItemAtPosition(posicao);
                Intent intent = new Intent(ListarActivity.this, AbastecimentoActivity.class);
                intent.putExtra("abastecimento", abastecimento);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDeletar = menu.add("Deletar");

        menuDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Abastecimento abastecimento = (Abastecimento) listar.getItemAtPosition(info.position);
                AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO(ListarActivity.this);
                abastecimentoDAO.delete(abastecimento);
                abastecimentoDAO.close();
                atualizarLista();

                return false;
            }
        });
    }

    public void atualizarLista(){
        AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO(this);
        List<Abastecimento> abastecimentoList = abastecimentoDAO.listaAbastecimentos();
        ArrayAdapter<Abastecimento> abastecimentoArrayAdapter = new ArrayAdapter<Abastecimento>(this, android.R.layout.simple_list_item_1,abastecimentoList);
        listar.setAdapter(abastecimentoArrayAdapter);
    }
}
