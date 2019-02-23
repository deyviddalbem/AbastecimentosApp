package br.edu.ifro.vilhena.abastecimentosv2;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);


        listar = findViewById(R.id.listaAbastecimentos);
        Combustivel combustivel = new Combustivel();
        combustivel.setId(1);
        Abastecimento teste = new Abastecimento();
        teste.setNomePosto("Tafarel");
        teste.setQuantLitros(15.3);
        teste.setQuilometragem((float) 152.6);
        teste.setValorLitro(3.49);
        teste.setCombustivel(combustivel);
        teste.setTotal(45.56);






        AbastecimentoDAO testeDAO = new AbastecimentoDAO(this);
        testeDAO.inserir(teste);
        atualizarLista();

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

                return false;
            }
        });
    }

    public void atualizarLista(){

        AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO(this);
        List<Abastecimento> abastecimentos = abastecimentoDAO.listaAbastecimentos();

        ArrayAdapter<Abastecimento> abastecimentosArrayAdapter = new ArrayAdapter<Abastecimento>(this,android.R.layout.simple_list_item_1,abastecimentos);
        listar.setAdapter(abastecimentosArrayAdapter);
        Toast.makeText(this,"Chegou no final", Toast.LENGTH_LONG).show();

    }
}
