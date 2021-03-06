package br.edu.ifro.vilhena.abastecimentosv2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifro.vilhena.abastecimentosv2.Model.Abastecimento;
import br.edu.ifro.vilhena.abastecimentosv2.Model.Combustivel;

public class AbastecimentoDAO extends SQLiteOpenHelper {
    public AbastecimentoDAO(Context context) {
        super(context, "Abastecimentos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table abastecimentos (id integer primary key, nomePosto text, " +
                "quilometragem real, valorLitro real, quantLitros real, total real, data text, tipocombustivel integer,FOREIGN KEY(tipocombustivel) REFERENCES combustiveis(id))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserir (Abastecimento abastecimento){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nomePosto",abastecimento.getNomePosto());
        dados.put("tipocombustivel",abastecimento.getCombustivel().getId());
        dados.put("quilometragem",abastecimento.getQuilometragem());
        dados.put("valorLitro ",abastecimento.getValorLitro());
        dados.put("quantLitros",abastecimento.getQuantLitros());
        dados.put("total",abastecimento.getTotal());
        dados.put("data", abastecimento.getData());
        db.insert("abastecimentos",null,dados);
    }

    public List<Abastecimento> listaAbastecimentos(){

        SQLiteDatabase db = getReadableDatabase();

        String sql = "select * from abastecimentos ORDER BY data DESC";

        Cursor c = db.rawQuery(sql, null);

        List<Abastecimento> listaAbastecimentos = new ArrayList<>();


        while (c.moveToNext()) {
            Combustivel combustivel = new Combustivel();

            Abastecimento abastecimento = new Abastecimento();

            abastecimento.setId(c.getInt(c.getColumnIndex("id")));
            abastecimento.setNomePosto(c.getString(c.getColumnIndex("nomePosto")));
            abastecimento.setQuilometragem(c.getFloat(c.getColumnIndex("quilometragem")));
            abastecimento.setValorLitro(c.getFloat(c.getColumnIndex("valorLitro")));
            abastecimento.setQuantLitros(c.getFloat(c.getColumnIndex("quantLitros")));
            abastecimento.setTotal(c.getFloat(c.getColumnIndex("total")));
            abastecimento.setData(c.getString(c.getColumnIndex("data")));
            combustivel.setId(c.getInt(c.getColumnIndex("tipocombustivel")));
            abastecimento.setCombustivel(combustivel);

            listaAbastecimentos.add(abastecimento);
        }
        return listaAbastecimentos;
    }


    public void alterar(Abastecimento abastecimento){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nomePosto",abastecimento.getNomePosto());
        dados.put("tipocombustivel",abastecimento.getCombustivel().getId());
        dados.put("quilometragem", abastecimento.getQuilometragem());
        dados.put("valorlitro",abastecimento.getValorLitro());
        dados.put("quantLitros", abastecimento.getQuantLitros());
        dados.put("total",abastecimento.getTotal());
        dados.put("data",abastecimento.getData());

        String[] parametros = {String.valueOf(abastecimento.getId())};
        db.update("abastecimentos", dados,"id = ?", parametros);
    }


    public void delete(Abastecimento abastecimento){
            SQLiteDatabase db = getWritableDatabase();
            String[] parametros = {String.valueOf(abastecimento.getId())};
            db.delete("abastecimentos", "id = ?", parametros);
        }



    public void deletarTudo(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from abastecimentos";
        db.execSQL(sql);
    }

    public List<Abastecimento> listaGastos(){

        SQLiteDatabase db = getReadableDatabase();

        String sql = "select [nomePosto], [quilometragem], [valorLitro] , [quantLitros], [total], [data], [tipoCombustivel] from abastecimentos";

        Cursor c = db.rawQuery(sql, null);

        List<Abastecimento> listaAbastecimentos = new ArrayList<>();


        while (c.moveToNext()) {
            Combustivel combustivel = new Combustivel();

            Abastecimento abastecimento = new Abastecimento();



            abastecimento.setNomePosto(c.getString(c.getColumnIndex("nomePosto")));
            abastecimento.setQuilometragem(c.getFloat(c.getColumnIndex("quilometragem")));
            abastecimento.setValorLitro(c.getFloat(c.getColumnIndex("valorLitro")));
            abastecimento.setQuantLitros(c.getFloat(c.getColumnIndex("quantLitros")));
            abastecimento.setTotal(c.getFloat(c.getColumnIndex("total")));
            abastecimento.setData(c.getString(c.getColumnIndex("data")));
            combustivel.setId(c.getInt(c.getColumnIndex("tipocombustivel")));
            abastecimento.setCombustivel(combustivel);
            listaAbastecimentos.add(abastecimento);
        }
        return listaAbastecimentos;

    }

    public String gastoTotal(){
        SQLiteDatabase db  = getReadableDatabase();
        String sql = "select sum(total) from abastecimentos";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        return c.getString(0);
    }

    public String gastoCombustivel(){
        SQLiteDatabase db  = getReadableDatabase();
        String sql = "select sum(quantLitros) from abastecimentos";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        return c.getString(0);
    }

    public String gastoKm(){
        SQLiteDatabase db  = getReadableDatabase();
        String sql = "select max(quilometragem) - min(quilometragem) from abastecimentos";
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        return c.getString(0);
    }

}
