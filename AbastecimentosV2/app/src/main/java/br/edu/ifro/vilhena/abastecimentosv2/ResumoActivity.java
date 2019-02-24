package br.edu.ifro.vilhena.abastecimentosv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.edu.ifro.vilhena.abastecimentosv2.DAO.AbastecimentoDAO;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Abastecimento;

public class ResumoActivity extends AppCompatActivity {

      private ListView listarGastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        listarGastos = findViewById(R.id.listaDeGastos);

        AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO(this);
        List<Abastecimento> abastecimentoList = abastecimentoDAO.listaAbastecimentos();

        ArrayAdapter<Abastecimento> abastecimentoArrayAdapter = new ArrayAdapter<Abastecimento>(this, android.R.layout.simple_expandable_list_item_1,abastecimentoList);
        listarGastos.setAdapter(abastecimentoArrayAdapter);

    }
}
