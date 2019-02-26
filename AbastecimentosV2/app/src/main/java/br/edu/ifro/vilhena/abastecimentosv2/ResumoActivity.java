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
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.edu.ifro.vilhena.abastecimentosv2.DAO.AbastecimentoDAO;
import br.edu.ifro.vilhena.abastecimentosv2.DAO.CombustivelDAO;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Abastecimento;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Combustivel;

public class ResumoActivity extends AppCompatActivity {
    private TextView txtGastoTotal;
    private TextView txtKmTotal;
    private TextView txtCombustivelTotal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);
        setTitle("Relatorio de abastecimentos");

        txtCombustivelTotal = findViewById(R.id.activityResumoTotalCombustivel);
        txtGastoTotal = findViewById(R.id.activityResumoTotalGasto);
        txtKmTotal = findViewById(R.id.activityResumoTotalKm);

        AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO(this);
        String vTotal = abastecimentoDAO.gastoTotal().toString();
        vTotal = String.format("%.2f", Double.parseDouble(vTotal));
        txtGastoTotal.setText("R$ " + vTotal);


        String combTotal = abastecimentoDAO.gastoCombustivel().toString();
        combTotal = String.format("%.2f", Double.parseDouble(combTotal));
        txtCombustivelTotal.setText(combTotal);

    }

}
