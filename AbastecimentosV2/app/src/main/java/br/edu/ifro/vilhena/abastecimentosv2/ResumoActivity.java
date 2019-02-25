package br.edu.ifro.vilhena.abastecimentosv2;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Text;

import java.util.List;

import br.edu.ifro.vilhena.abastecimentosv2.DAO.AbastecimentoDAO;
import br.edu.ifro.vilhena.abastecimentosv2.DAO.CombustivelDAO;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Abastecimento;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Combustivel;

public class ResumoActivity extends AppCompatActivity {

      private ListView listarGastos;
    private TextInputEditText nomePosto;
    private TextInputEditText quilometragem;
    private TextInputEditText valorLitro;
    private TextInputEditText quantidadeLitros;
    private TextInputEditText total;
    private TextInputEditText data;
    private List<Combustivel> tipoCombustivel;
    private Abastecimento abastecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
        setTitle("Relatorio de abastecimentos");


        nomePosto = findViewById(R.id.abastecimentoNomePosto);
        quilometragem = findViewById(R.id.abastecimentoQuilometragem);
        valorLitro = findViewById(R.id.abastecimentoValorLitro);
        quantidadeLitros = findViewById(R.id.abastecimentoQuantLitros);
        total = findViewById(R.id.abastecimentoTotal);
        data = findViewById(R.id.abastecimentoData);

        listarGastos = findViewById(R.id.listaDeGastos);


        AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO(this);
        List<Abastecimento> abastecimentoList = abastecimentoDAO.listaAbastecimentos();
        ArrayAdapter<Abastecimento> abastecimentoArrayAdapter = new ArrayAdapter<Abastecimento>(this, android.R.layout.simple_expandable_list_item_1,abastecimentoList);
        listarGastos.setAdapter(abastecimentoArrayAdapter);

        registerForContextMenu(listarGastos);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDeletar = menu.add("Deletar");

    }
}
