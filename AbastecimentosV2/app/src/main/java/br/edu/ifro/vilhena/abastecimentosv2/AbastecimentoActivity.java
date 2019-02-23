package br.edu.ifro.vilhena.abastecimentosv2;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Text;
import android.widget.ListView;

import java.util.List;

import br.edu.ifro.vilhena.abastecimentosv2.DAO.AbastecimentoDAO;
import br.edu.ifro.vilhena.abastecimentosv2.DAO.CombustivelDAO;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Abastecimento;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Combustivel;


public class AbastecimentoActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button btnSalvar;
    private TextInputEditText nomePosto;
    private TextInputEditText quilometragem;
    private TextInputEditText valorLitro;
    private TextInputEditText quantidadeLitros;
    private TextInputEditText total;
    private TextInputEditText data;


    private ListView listarAbastecimento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abastecimento);

        nomePosto = findViewById(R.id.abastecimentoNomePosto);
        quilometragem = findViewById(R.id.abastecimentoQuilometragem);
        valorLitro = findViewById(R.id.abastecimentoValorLitro);
        quantidadeLitros = findViewById(R.id.abastecimentoQuantLitros);
        total = findViewById(R.id.abastecimentoTotal);
        data = findViewById(R.id.abastecimentoData);
        btnSalvar = findViewById(R.id.abastecimentoBtnSalvar);
        spinner = findViewById(R.id.abastecimentoSpinner);

        String[] combustiveis = {"Gasolina Comum", "Gasolina Aditivada", "Etanol"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,combustiveis);
        spinner.setAdapter(adapter);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AbastecimentoActivity.this,"Teste",Toast.LENGTH_SHORT).show();
            }
        });

        listarAbastecimento = findViewById(R.id.listaAbastecimentos);


        //AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO(AbastecimentoActivity.this);
        //Abastecimento abastecimento = new Abastecimento();
        //abastecimento.setNomePosto("Posto Cidade");
        //abastecimento.getCombustivel().setTipo("gasolina");
        //abastecimento.setQuilometragem(5789);
        //abastecimento.setQuantLitros(10);
        //abastecimento.setValorLitro(2.50);
        //abastecimento.setTotal(abastecimento.getQuantLitros() * abastecimento.getValorLitro());
        //abastecimento.setData();
        //abastecimentoDAO.inserir(abastecimento);
        //abastecimentoDAO.close();



        //List<Abastecimento> abastecimentoLista = abastecimentoDAO.listaAbastecimentos();
        //ArrayAdapter<Abastecimento> combustivelArrayAdapter = new ArrayAdapter<Abastecimento>(this,android.R.layout.simple_list_item_1,abastecimentoLista);
        //listarAbastecimento.setAdapter(combustivelArrayAdapter);

    }

}
