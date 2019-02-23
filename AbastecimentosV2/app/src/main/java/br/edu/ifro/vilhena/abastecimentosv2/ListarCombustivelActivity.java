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

import java.util.List;

import br.edu.ifro.vilhena.abastecimentosv2.DAO.CombustivelDAO;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Combustivel;

public class ListarCombustivelActivity extends AppCompatActivity {
    private ListView listaCombustiveis;
    private FloatingActionButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_combustivel);

        listaCombustiveis = findViewById(R.id.listViewCombustiveis);
        btnAdd = findViewById(R.id.combustivelActivityAddButton);

        atualizarLista();

        registerForContextMenu(listaCombustiveis);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarCombustivelActivity.this,CombustivelActivity.class);
                startActivity(intent);
            }
        });

        listaCombustiveis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Combustivel combustivel = (Combustivel) listaCombustiveis.getItemAtPosition(posicao);
                Intent intent = new Intent(ListarCombustivelActivity.this,CombustivelActivity.class);
                intent.putExtra("combustivel", combustivel);
                startActivity(intent);
            }
        });
    }

    private void atualizarLista() {
        CombustivelDAO combustivelDAO = new CombustivelDAO(this);
        List<Combustivel> combustivelList = combustivelDAO.listarTodos();

        ArrayAdapter<Combustivel> combustivelArrayAdapter = new ArrayAdapter<Combustivel>(this,android.R.layout.simple_list_item_1,combustivelList);
        listaCombustiveis.setAdapter(combustivelArrayAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDeletar = menu.add("Deletar");



        menuDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Combustivel combustivel = (Combustivel) listaCombustiveis.getItemAtPosition(info.position);
                CombustivelDAO combustivelDAO = new CombustivelDAO(ListarCombustivelActivity.this);
                combustivelDAO.deletar(combustivel);
                atualizarLista();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }
}
