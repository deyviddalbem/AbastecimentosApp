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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.edu.ifro.vilhena.abastecimentosv2.DAO.AbastecimentoDAO;
import br.edu.ifro.vilhena.abastecimentosv2.DAO.CombustivelDAO;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Abastecimento;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Combustivel;

public class ListarActivity extends AppCompatActivity {
    private ListView listar;
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnListarCombustiveis;
    private FloatingActionButton btnChamarTelaResumo;
    private Button btnchamarRelatorio;
    private Button btnchamarCombustiveis;

    //
    private FloatingActionButton fab1;
    private  FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        setTitle("Abastecimentos Feitos");


        listar = findViewById(R.id.listaAbastecimentos);
        //btnAdd = findViewById(R.id.addAbastecimento);

        //btnListarCombustiveis = findViewById(R.id.activityListarBtnListarCombustiveis);
        //btnChamarTelaResumo= findViewById(R.id.btnChamarActivityResumo);

        //btnchamarRelatorio = findViewById(R.id.btnChamarRelatorio);
        //btnchamarCombustiveis = findViewById(R.id.btnChamarActivityCombustiveis);

        //
        fab = findViewById(R.id.fab);
        fab1= findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        fab.setOnClickListener(new View.OnClickListener() {


            boolean isFABOpen;
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
            private void showFABMenu(){
                isFABOpen=true;
                fab1.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
                fab2.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
                fab3.animate().translationY(-getResources().getDimension(R.dimen.standard_155));
                fab.setImageResource(R.drawable.mais2);
            }

            private void closeFABMenu(){
                isFABOpen=false;
                fab1.animate().translationY(0);
                fab2.animate().translationY(0);
                fab3.animate().translationY(0);
                fab.setImageResource(R.drawable.menos);
            }
        });




        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarActivity.this, ListarCombustivelActivity.class);
                startActivity(intent);
            }
        });

        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarActivity.this,ResumoActivity.class);
                startActivity(intent);
            }
        });
        //btnListarCombustiveis.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  Intent intent = new Intent(ListarActivity.this,ListarCombustivelActivity.class);
                //startActivity(intent);
            //}
        //});

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarActivity.this, AbastecimentoActivity.class);
                startActivity(intent);
            }
        });

        //btnChamarTelaResumo.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  Intent intent = new Intent(ListarActivity.this,ResumoActivity.class);
                //startActivity(intent);
            //}
        //});

        atualizarLista();

        registerForContextMenu(listar);

        listar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {
                Abastecimento abastecimento = (Abastecimento) listar.getItemAtPosition(posicao);
                Intent intent = new Intent(ListarActivity.this, AbastecimentoActivity.class);
                intent.putExtra("abastecimentoSelecionado", abastecimento);
                startActivity(intent);
            }
        });

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
                atualizarLista();

                return false;
            }
        });
    }

    public void atualizarLista(){
        AbastecimentoDAO abastecimentoDAO = new AbastecimentoDAO(this);
        List<Abastecimento> abastecimentoList = abastecimentoDAO.listaAbastecimentos();



        for(int i = 0; i < abastecimentoList.size(); i++){


            Double valorLitro = abastecimentoList.get(i).getValorLitro();
            String litroFormatado = String.format("%.2f", valorLitro);
            Double litroFinal = Double.parseDouble(litroFormatado);
            abastecimentoList.get(i).setValorLitro(litroFinal);


            Double quantLitros = abastecimentoList.get(i).getQuantLitros();
            String quantFormatado = String.format("%.2f", quantLitros);
            Double quantFinal = Double.parseDouble(quantFormatado);
            abastecimentoList.get(i).setQuantLitros(quantFinal);


            Double total = abastecimentoList.get(i).getTotal();
            String totalFormatado = String.format("%.2f", total);
            Double totalFinal = Double.parseDouble(totalFormatado);
            abastecimentoList.get(i).setTotal(totalFinal);
        }

        ArrayAdapter<Abastecimento> abastecimentoArrayAdapter = new ArrayAdapter<Abastecimento>(this, android.R.layout.simple_list_item_1,abastecimentoList);
        listar.setAdapter(abastecimentoArrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }
}
