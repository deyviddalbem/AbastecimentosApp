package br.edu.ifro.vilhena.abastecimentosv2;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import br.edu.ifro.vilhena.abastecimentosv2.DAO.CombustivelDAO;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Combustivel;

public class CombustivelActivity extends AppCompatActivity {
    private Button btnSalvar;
    private TextInputEditText txtTipoCombustivel;
    private Combustivel combustivel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combustivel);

        btnSalvar = findViewById(R.id.combustivelActivityBtnSalvar);
        txtTipoCombustivel = findViewById(R.id.combustivelActivityTipo);

        Intent intent = getIntent();
        if(((Intent) intent).hasExtra("combustivel")){
            combustivel = (Combustivel) intent.getSerializableExtra("combustivel");
            btnSalvar.setText("ALTERAR");
        } else{
            combustivel = new Combustivel();
            btnSalvar.setText("SALVAR");
        }

        if(combustivel != null){
            txtTipoCombustivel.setText(combustivel.getTipo());
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                combustivel.setTipo(txtTipoCombustivel.getText().toString());
                CombustivelDAO combustivelDAO = new CombustivelDAO(CombustivelActivity.this);

                if(combustivel.getId() != 0){
                    combustivelDAO.alterar(combustivel);
                } else {
                    combustivelDAO.inserir(combustivel);
                }
                combustivelDAO.close();
                Toast.makeText(CombustivelActivity.this,"Tipo de combustível salvo", Toast.LENGTH_LONG);
                finish();
            }
        });
    }
}
