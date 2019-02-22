package br.edu.ifro.vilhena.abastecimentosv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifro.vilhena.abastecimentosv2.DAO.CombustivelDAO;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Combustivel;

public class ListarActivity extends AppCompatActivity {
    private ListView listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);


        listar = findViewById(R.id.listaAbastecimentos);


        CombustivelDAO combustivelDAO = new CombustivelDAO(this);
        Combustivel combustivel = new Combustivel();
        combustivel.setTipo("Gasolina Aditivada");

        combustivelDAO.inserir(combustivel);
        //combustivelDAO.deletarTudo();
        combustivelDAO.close();

        List<Combustivel> combustivelList = combustivelDAO.listarTodos();
        ArrayAdapter<Combustivel> combustivelArrayAdapter = new ArrayAdapter<Combustivel>(this,android.R.layout.simple_list_item_1,combustivelList);
        listar.setAdapter(combustivelArrayAdapter);

    }
}
