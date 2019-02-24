package br.edu.ifro.vilhena.abastecimentosv2;

import android.content.Intent;
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
    private List<Combustivel> tipoCombustivel;
    private Abastecimento abastecimento;


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


        final CombustivelDAO combustivelDAO = new CombustivelDAO(this);
        tipoCombustivel = combustivelDAO.listarTodos();

        String[] tiposCombustiveis = new String[tipoCombustivel.size()];
        for (int i = 0; i < tipoCombustivel.size(); i++){
            tiposCombustiveis[i] = tipoCombustivel.get(i).getTipo();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tiposCombustiveis);
        spinner.setAdapter(adapter);


        Intent intent = getIntent();
        if (intent.hasExtra("abastecimentoSelecionado")) {
            abastecimento = (Abastecimento) intent.getSerializableExtra("abastecimentoSelecionado");
            btnSalvar.setText("ALTERAR");
        } else {
            abastecimento = new Abastecimento();
            btnSalvar.setText("SALVAR");
        }

        if (abastecimento != null) {
            nomePosto.setText(abastecimento.getNomePosto());
            quilometragem.setText(String.valueOf(abastecimento.getQuilometragem()));
            valorLitro.setText(String.valueOf(abastecimento.getValorLitro()));
            quantidadeLitros.setText(String.valueOf(abastecimento.getQuantLitros()));
            total.setText(String.valueOf(abastecimento.getTotal()));
            data.setText(abastecimento.getData());




            if(abastecimento.getNomePosto() != null){
                CombustivelDAO buscarTipo = new CombustivelDAO(this);
                Combustivel combustivelRetornado = buscarTipo.retornarTipo(abastecimento.getCombustivel().getId());
                spinner.setSelection(adapter.getPosition(combustivelRetornado.getTipo()));
            }


        }




        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abastecimento.setNomePosto(nomePosto.getText().toString());
                abastecimento.setData(data.getText().toString());
                abastecimento.setQuantLitros(Double.valueOf(quantidadeLitros.getText().toString()));
                abastecimento.setQuilometragem(Float.valueOf(quilometragem.getText().toString()));
                abastecimento.setTotal(Double.valueOf(total.getText().toString()));
                abastecimento.setValorLitro(Double.valueOf(valorLitro.getText().toString()));

                CombustivelDAO buscarID = new CombustivelDAO(AbastecimentoActivity.this);
                Combustivel combustivelRetornado;
                combustivelRetornado = buscarID.retornarId(spinner.getSelectedItem().toString());

                //abastecimento.setCombustivel(combustivelRetornado);
            Toast.makeText(AbastecimentoActivity.this, (CharSequence) combustivelRetornado,Toast.LENGTH_LONG).show();

/*                AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO(AbastecimentoActivity.this);
                if (abastecimento.getId() != 0) {
                    abastecimentoDAO.alterar(abastecimento);
                } else {
                    abastecimentoDAO.inserir(abastecimento);
                }
                abastecimentoDAO.close();
                finish();*/
            }
        });


    }

}
