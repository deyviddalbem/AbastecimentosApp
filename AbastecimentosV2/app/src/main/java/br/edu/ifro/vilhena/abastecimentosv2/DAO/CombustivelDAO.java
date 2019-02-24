package br.edu.ifro.vilhena.abastecimentosv2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifro.vilhena.abastecimentosv2.Model.Combustivel;

public class CombustivelDAO extends SQLiteOpenHelper {


    public CombustivelDAO(Context context) {
        super(context, "Abastecimento", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table combustiveis (id integer primary key, tipo text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void inserir(Combustivel combustivel){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("tipo", combustivel.getTipo());
        db.insert("combustiveis",null,dados);
    }

    public List<Combustivel> listarTodos(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from combustiveis";
        Cursor c = db.rawQuery(sql, null);
        List<Combustivel> lista = new ArrayList<>();
        while (c.moveToNext()){
            Combustivel combustivel = new Combustivel();
            combustivel.setId(c.getInt(c.getColumnIndex("id")));
            combustivel.setTipo(c.getString(c.getColumnIndex("tipo")));
            lista.add(combustivel);
        }
        return lista;
    }

    public void deletar(Combustivel combustivel){
        SQLiteDatabase db = getWritableDatabase();
        String[] parametros = {String.valueOf(combustivel.getId())};
        db.delete("combustiveis", "id = ?", parametros);
    }

    public void deletarTudo(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from combustiveis";
        db.execSQL(sql);
    }

    public void alterar(Combustivel combustivel) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("tipo", combustivel.getTipo());
        String[] parametros = {String.valueOf(combustivel.getId())};
        db.update("combustiveis",dados, "id = ?", parametros);
    }

}
