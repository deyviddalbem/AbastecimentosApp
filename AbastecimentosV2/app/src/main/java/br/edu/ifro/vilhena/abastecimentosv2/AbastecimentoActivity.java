package br.edu.ifro.vilhena.abastecimentosv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.edu.ifro.vilhena.abastecimentosv2.DAO.AbastecimentoDAO;
import br.edu.ifro.vilhena.abastecimentosv2.DAO.CombustivelDAO;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Abastecimento;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Combustivel;

public class AbastecimentoActivity extends AppCompatActivity {

    private ListView listarAbastecimento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abastecimento);

        listarAbastecimento = findViewById(R.id.listaAbastecimentos);


        AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO(AbastecimentoActivity.this);
        Abastecimento abastecimento = new Abastecimento();
        abastecimento.setNomePosto("Posto Cidade");
        abastecimento.getCombustivel().setTipo("gasolina");
        abastecimento.setQuilometragem(5789);
        abastecimento.setQuantLitros(10);
        abastecimento.setValorLitro(2.50);
        abastecimento.setTotal(abastecimento.getQuantLitros() * abastecimento.getValorLitro());
        //abastecimento.setData();
        //abastecimentoDAO.inserir(abastecimento);
        abastecimentoDAO.close();



        List<Abastecimento> abastecimentoLista = abastecimentoDAO.listaAbastecimentos();
        ArrayAdapter<Abastecimento> combustivelArrayAdapter = new ArrayAdapter<Abastecimento>(this,android.R.layout.simple_list_item_1,abastecimentoLista);
        listarAbastecimento.setAdapter(combustivelArrayAdapter);

    }
}
